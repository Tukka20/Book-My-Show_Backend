package com.bookmyshow.Book_My_Show.entity;


import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "shows")
@Builder
@AllArgsConstructor
@NoArgsConstructor


public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private LocalDateTime endTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id",nullable = false)
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="screen_id",nullable = false)
    private Screen screen;

    @Builder.Default
    @OneToMany(mappedBy = "show",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Booking> bookings = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "show",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<ShowSeat> showSeats = new ArrayList<>();


    @Builder.Default
    @OneToMany(mappedBy = "show",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<ShowSeatPricing> pricing = new ArrayList<>();



}
