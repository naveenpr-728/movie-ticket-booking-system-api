package com.example.mtb.repository;

import com.example.mtb.entity.Screen;
import com.example.mtb.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, String> {

    List<Seat> findByScreenAndIsDeleteFalse(Screen screen);
}
