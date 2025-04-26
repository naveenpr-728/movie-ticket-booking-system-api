package com.example.mtb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class TheaterOwner extends UserDetails {

    @OneToMany(mappedBy ="theaterOwner")
    private List<Theater> theater;
}

