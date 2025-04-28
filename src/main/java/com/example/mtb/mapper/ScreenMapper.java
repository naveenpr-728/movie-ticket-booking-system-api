package com.example.mtb.mapper;

import com.example.mtb.dto.ScreenResponse;
import com.example.mtb.dto.SeatResponse;
import com.example.mtb.entity.Screen;
import com.example.mtb.entity.Seat;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class ScreenMapper {

    public ScreenResponse screenResponseMapper(Screen screen) {
        if (screen == null)
            return null;
        return new ScreenResponse(
                screen.getScreenId(),
                screen.getScreenType(),
                screen.getCapacity(),
                screen.getNoOfRows(),
                +6seatResponseMapper(screen.getSeats())

        );
    }
    private SeatResponse seatResponseMapper (List<Seat> seats){
        List<String> seatId = new LinkedList<>();
        List<String> seatName = new LinkedList<>();
        for (Seat seat : seats){
            seatId.add(seat.getSeatId());
            seatName.add(seat.getName());
        }
        return SeatResponse.builder()
                .name(seatName)
                .seatId(seatId)
                .build();
    }
}