package com.example.mtb.entity;

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

public class Theater {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "theater-id",nullable = false,updatable = false)
    private String theaterId;

    @Column(name = "name",nullable = false)
    private String name;


    @Column(name = "address",nullable = false)
    private String address;

    @Column(name= "city",nullable = false)
    private String city;

    @Column(name = "landmark",nullable = false)
    private String landmark;


    @ManyToOne
    private TheaterOwner theaterOwner;


    @OneToMany(mappedBy = "theater")
    private List<Screen> screens;

    @CreatedDate
    @Column(name = "created_at",updatable = false,nullable = false)
    private Instant createAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private Instant updatedAt;

    @Column(name = "created_by",updatable = false,nullable = false)
    @CreatedBy
    private String createdBy;

}
