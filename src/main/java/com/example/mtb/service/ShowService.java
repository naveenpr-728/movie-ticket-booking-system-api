package com.example.mtb.service;

import com.example.mtb.dto.MovieShowsRequest;
import com.example.mtb.dto.ShowResponse;
import com.example.mtb.dto.TheaterShowProjection;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;

public interface ShowService {

    ShowResponse addShow(String theaterId, String Screenid, String movieId, @NotNull Long startTime);

    Page<TheaterShowProjection> fetchShows(String movieId, MovieShowsRequest showsRequest, String city);
}
