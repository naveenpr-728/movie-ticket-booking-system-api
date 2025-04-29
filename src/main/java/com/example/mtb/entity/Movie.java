package com.example.mtb.entity;

import com.example.mtb.enums.Certificate;
import com.example.mtb.enums.Genre;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Duration;
import java.util.List;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@ToString
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String movieId;
    private String title;
    private String description;
    private String cast;

    private Duration runtime;

    @Enumerated(value = EnumType.STRING)
    private Genre genre;
    @Enumerated(value = EnumType.STRING)
    private Certificate certificate;
}
