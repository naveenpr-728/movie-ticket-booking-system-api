package com.example.mtb.service.serviceimpl;

import com.example.mtb.dto.MovieShowsRequest;
import com.example.mtb.dto.ShowResponse;
import com.example.mtb.dto.TheaterShowProjection;
import com.example.mtb.entity.Movie;
import com.example.mtb.entity.Screen;
import com.example.mtb.entity.Show;
import com.example.mtb.entity.Theater;
import com.example.mtb.exceptions.*;
import com.example.mtb.mapper.ShowMapper;
import com.example.mtb.repository.MovieRepository;
import com.example.mtb.repository.ScreenRepository;
import com.example.mtb.repository.ShowRepository;
import com.example.mtb.repository.TheaterRepository;
import com.example.mtb.service.ShowService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.time.Instant;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ShowServiceImpl implements ShowService {
    private final TheaterRepository theaterRepository;
    private final ShowRepository showRepository;
    private final ScreenRepository screenRepository;
    private final MovieRepository movieRepository;
    private final ShowMapper showMapper;

    @Override
    public ShowResponse addShow(String theaterId, String screenId, String movieId, Long startTime) {
        if (theaterRepository.existsById(theaterId)) {

            if (screenRepository.existsById(screenId)) {

                if (movieRepository.existsById(movieId)) {

                    Screen screen = screenRepository.findById(screenId).get();
                    Set<Show> shows = screen.getShows();

                    Movie movie = movieRepository.findById(movieId).get();

                    Instant instantStartTime = Instant.ofEpochMilli(startTime);

                    for (Show s : shows) {
                        Instant showStartTime = s.getStartsAt();
                        Instant showEndTime = s.getEndsAt();
                        Instant movieCompletionTime = instantStartTime.plus(movie.getRuntime());

                        if (!(movieCompletionTime.isBefore(showStartTime) || instantStartTime.isAfter(showEndTime))) {
                            throw new ShowTimeConflictException("Another Show is been Booked");
                        }
                    }

                    Show show = copy(new Show(), startTime, screen, movie);


                    return showMapper.showResponseMapper(show);

                }

                throw new MovieNotFoundByIdException("Movie Id not found in the database");
            }
            throw new ScreenNotFoundByIdException("Screen Id not found in the database");

        }
        throw new TheaterNotFoundByIdException("Theater Id not found in the database");
    }

    @Override
    public Page<TheaterShowProjection> fetchShows(String movieId, MovieShowsRequest showsRequest, String city) {
        ZoneId zoneId = showsRequest.zoneId() == null || showsRequest.zoneId().isBlank()
                ? ZoneId.of("UTC")
                : ZoneId.of(ZoneId.SHORT_IDS.getOrDefault(showsRequest.zoneId().toUpperCase(), "UTC"));

        if (city == null || city.isBlank()) {
            throw new CityNotFoundException("No city found by name");
        }

        Instant start = showsRequest.date().atStartOfDay(zoneId).toInstant();
        Instant end = showsRequest.date().plusDays(1).atStartOfDay(zoneId).minusNanos(1).toInstant();

        Pageable pageable = PageRequest.of(showsRequest.page() - 1, showsRequest.size());

        // Step 1: Fetch paged theater IDs
        Page<String> theaterIdsPage = showRepository.findTheaterIdsWithMatchingShowsAndCity(
                movieId, start, end, showsRequest.screenType(), city, pageable
        );

        List<String> theaterIds = theaterIdsPage.getContent();

        // Step 2: Fetch shows for those theaters
        List<Show> shows = showRepository.findShowsForTheaters(
                movieId, start, end, showsRequest.screenType(), theaterIds
        );

        // Step 3: Group shows by theater
        Map<String, List<Show>> grouped = shows.stream()
                .collect(Collectors.groupingBy(show -> show.getTheater().getTheaterId()));

        // Step 4: Map to projections
        List<TheaterShowProjection> results = theaterIds.stream()
                .map(theaterId -> {
                    Show show = grouped.get(theaterId).get(0); // get any show to extract theater
                    Theater theater = show.getTheater();

                    List<ShowResponse> showProjections = grouped.get(theaterId).stream()
                            .map(s -> new ShowResponse(
                                    s.getShowId(),
                                    s.getStartsAt(),
                                    s.getEndsAt(),
                                    s.getScreen().getScreenId(),
                                    s.getScreen().getScreenType()
                            ))
                            .toList();

                    return new TheaterShowProjection(
                            theater.getTheaterId(),
                            theater.getName(),
                            theater.getAddress(),
                            showProjections
                    );
                })
                .toList();

        return new PageImpl<>(results, pageable, theaterIdsPage.getTotalElements());
    }

    private Show copy(Show show, Long startTime, Screen screen, Movie movie) {
        show.setScreen(screen);
        show.setMovie(movie);
        Instant instantStartTime = Instant.ofEpochMilli(startTime);
        show.setStartsAt(instantStartTime);
        Instant endTime = instantStartTime.plus(movie.getRuntime());
        System.out.println(endTime);
        show.setEndsAt(endTime);
        showRepository.save(show);
        return show;

    }
}
