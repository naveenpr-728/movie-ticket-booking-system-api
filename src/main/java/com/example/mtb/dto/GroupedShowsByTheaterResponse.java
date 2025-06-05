package com.example.mtb.dto;

import java.util.List;

public record GroupedShowsByTheaterResponse(
        TheaterResponse theater,
        List<ShowResponse> shows
) {
}
