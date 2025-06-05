package com.example.mtb.service.serviceimpl;

import com.example.mtb.dto.BookingResponse;
import com.example.mtb.repository.BookingRepository;
import com.example.mtb.service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    @Override
    public BookingResponse createBooking() {
        return null;
    }
}
