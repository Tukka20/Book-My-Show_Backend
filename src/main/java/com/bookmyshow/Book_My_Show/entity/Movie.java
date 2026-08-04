package com.bookmyshow.Book_My_Show.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "movies")
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Movie {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false,columnDefinition ="TEXT")
    private String description;

    @Column(nullable = false)
    private String certificate;

    @Column(nullable = false)
    private Integer durationMins;

    @Column(nullable = false)
    private String language;

    @Column(nullable = false)
    private String genre;

    @Column(nullable = false)
    private LocalDate releaseDate;

    private String posterUrl;


    @OneToMany(mappedBy = "movie",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
    private List<Show> shows=new ArrayList<>();









}




