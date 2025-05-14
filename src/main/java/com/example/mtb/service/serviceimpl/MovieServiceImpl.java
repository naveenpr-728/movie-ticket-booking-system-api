package com.example.mtb.service.serviceimpl;

import com.example.mtb.dto.MovieResponse;
import com.example.mtb.entity.Feedback;
import com.example.mtb.entity.Movie;
import com.example.mtb.exceptions.MovieNotFoundByIdException;
import com.example.mtb.mapper.MovieMapper;
import com.example.mtb.repository.MovieRepository;
import com.example.mtb.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MovieServiceImpl implements MovieService {


    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    @Override
    public MovieResponse getMovie(String movieId) {
        if (movieRepository.existsById(movieId)){

            Movie movie = movieRepository.findById(movieId).get();
            List<Feedback> feedbacks = movie.getFeedbacks();

            double avgRatings = 0;

            for(Feedback feedback : feedbacks){
                avgRatings+=feedback.getRating();
            }
            avgRatings/= feedbacks.size();


            return movieMapper.movieResponseMapper(movie, avgRatings);
        }
        throw new MovieNotFoundByIdException("Movie not found in Database");
    }
}
