package com.example.mtb.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record SeatResponse(
        List<String> seatId,
        List<String> name
) {
}
