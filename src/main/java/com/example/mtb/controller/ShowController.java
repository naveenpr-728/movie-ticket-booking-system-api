package com.example.mtb.controller;

import com.example.mtb.dto.MovieShowsRequest;
import com.example.mtb.dto.ShowResponse;
import com.example.mtb.dto.TheaterShowProjection;
import com.example.mtb.service.ShowService;
import com.example.mtb.utility.ResponseStructure;
import com.example.mtb.utility.RestResponseBuilder;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Validated
public class ShowController {

    private final ShowService showService;
    private final RestResponseBuilder responseBuilder;

    @PostMapping("theaters/{theaterId}/screens/{screenId}/shows")
    @PreAuthorize("hasAuthority('THEATER_OWNER')")
    public ResponseEntity<ResponseStructure<ShowResponse>> addShow(@PathVariable String theaterId, @PathVariable String screenId, String movieId ,@NotNull Long startTime) {
        ShowResponse showResponse =showService.addShow(theaterId,screenId,movieId,startTime);
        return responseBuilder.sucess(HttpStatus.OK,"shows created successfully",showResponse);
    }


    @GetMapping("movies/{movieId}/shows")
    public ResponseEntity<ResponseStructure<Page<TheaterShowProjection>>> fetchShows(
            @PathVariable String movieId,
            @RequestBody MovieShowsRequest showsRequest,
            @RequestHeader(value = "X-City", required = false)  String city // Header for city
    ) {
        Page<TheaterShowProjection> shows  = showService.fetchShows(movieId, showsRequest, city);
        return responseBuilder.sucess(HttpStatus.OK, "Fetched Succesfully", shows);
    }
}
