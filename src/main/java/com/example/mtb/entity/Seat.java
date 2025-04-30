package com.example.mtb.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@ToString

public class Seat {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "seat-id",nullable = false,updatable = false)
    private String seatId;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Screen screen;


    private Boolean isDelete;
    private Instant deletedAt;

    @CreatedDate
    private Instant createdAt;
}
