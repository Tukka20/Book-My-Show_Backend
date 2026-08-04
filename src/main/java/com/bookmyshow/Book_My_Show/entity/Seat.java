package com.bookmyshow.Book_My_Show.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name="seats",
uniqueConstraints = {@UniqueConstraint(columnNames = {"screen_id","seatNumber"})})
@Builder
@AllArgsConstructor
@NoArgsConstructor


public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String seatNumber;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "screen_id",nullable = false)
    private Screen screen;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seat_type_id",nullable = false)
    private SeatType seatType;






}
