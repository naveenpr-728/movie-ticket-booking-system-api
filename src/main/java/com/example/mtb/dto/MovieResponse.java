package com.example.mtb.dto;

import com.example.mtb.enums.Certificate;
import com.example.mtb.enums.Genre;

import java.time.Duration;
import java.util.Set;

public record MovieResponse(
        String movieId,
        String title,
        String description,
        String ratings,
        Duration runtime,
        Certificate certificate,
        Genre genre,
        Set<String> castList
) {
}
