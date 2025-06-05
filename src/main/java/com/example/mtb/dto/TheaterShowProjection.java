package com.example.mtb.dto;

import java.util.List;

public record TheaterShowProjection(
        String theaterId,
        String theaterName,
        String address,
        List<ShowResponse> shows
) {
}
