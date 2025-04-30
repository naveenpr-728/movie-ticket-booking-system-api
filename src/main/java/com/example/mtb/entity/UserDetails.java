package com.example.mtb.entity;

import com.example.mtb.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
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
    @Column(name = "user_id")
    private String userId;
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "phone_number", updatable = true, length = 10, nullable = false)
    private String phoneNumber;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "user_role", updatable = false, nullable = false)
    private UserRole userRole;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date_of_birth", nullable = false)
    private LocalDate DateOfBirth;

    @Column(name = "is_deleted")
    private boolean isDelete;
    @Column(name = "deleted_at")
    private Instant deletedAt;

    @CreatedDate
    @Column(name = "created-at", nullable = false)
    private Instant createdAt;
    @LastModifiedDate
    @Column(name = "updated-at")
    private Instant updatedAt;

    @Column(name = "created_by", updatable = false)
    @CreatedBy
    private String createdBy;


}
