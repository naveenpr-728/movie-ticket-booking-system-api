package com.example.mtb.controller;

import com.example.mtb.dto.BookingResponse;
import com.example.mtb.service.BookingService;
import com.example.mtb.utility.ResponseStructure;
import com.example.mtb.utility.RestResponseBuilder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class BookingController {

    private final BookingService bookingService;
    private final RestResponseBuilder responseBuilder;

    @PostMapping("movies/{movieId}/shows/{showId}/")
    public ResponseEntity<ResponseStructure<BookingResponse>> createBooking() {
        BookingResponse response = bookingService.createBooking();
        return responseBuilder.sucess(HttpStatus.OK, "Booking Successfull", response);
    }
}
