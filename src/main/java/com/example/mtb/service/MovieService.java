package com.example.mtb.service;

import com.example.mtb.dto.MovieResponse;

public interface MovieService {
    MovieResponse getMovie(String movieId);
}
