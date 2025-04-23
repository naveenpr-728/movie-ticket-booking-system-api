package com.example.mtb.entity;

import com.example.mtb.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


@Entity
@Getter
@Setter
@ToString
@EntityListeners(AuditingEntityListener.class)
@Inheritance(strategy = InheritanceType.JOINED)
public class UserDetails {
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
    private boolean isDelete;
    private LocalDateTime deletedAt;

    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;


}
