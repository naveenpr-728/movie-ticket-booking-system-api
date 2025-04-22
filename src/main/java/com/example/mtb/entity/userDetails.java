package com.example.mtb.entity;

import com.example.mtb.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public class userDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String userId;
    private String username;
    @Column(unique = true)
    private String email;
    private String password;
    private String phoneNumber;
    @Enumerated(value = EnumType.STRING)
    private UserRole userRole;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate DateOfBirth;
    private long createdAt;
    private long updatedAt;


}
