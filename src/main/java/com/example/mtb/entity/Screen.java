package com.example.mtb.entity;

import com.example.mtb.enums.ScreenType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@ToString
public class Screen {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "screen-id",updatable = false,nullable = false)
    private String screenId;

    @Column(name = "screen-type",updatable = false,nullable = false)
    @Enumerated(EnumType.STRING)
    private ScreenType screenType;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "no-of-rows")
    private Integer noOfRows;

    @ManyToOne
    private Theater theater;

    @OneToMany(mappedBy = "screen", cascade = CascadeType.PERSIST ,fetch = FetchType.EAGER)
    @OrderBy(value = "name")
    @JsonIgnore
    private List<Seat> seats;


    @CreatedDate
    @Column(name = "created_at")
    private Instant createdAt;

    @LastModifiedDate
    @Column(name = "updated",updatable = false)
    private Instant updatedAt;

    @Column(name = "created_by")
    @CreatedBy
    private String createdBy;


}
