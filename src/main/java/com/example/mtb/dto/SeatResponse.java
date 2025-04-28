package com.example.mtb.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record SeatResponse(
        String seatId,
        String name
) {
}
