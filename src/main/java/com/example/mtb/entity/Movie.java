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
import java.util.Set;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@ToString
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "movie_id")
    private String movieId;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @ElementCollection
    private Set<String> castList;

    @Column(name = "runtime")
    private Duration runtime;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "genre")
    private Genre genre;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "certificate")
    private Certificate certificate;

    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY)
    private List<Show> shows;
}
