package com.bookmyshow.Book_My_Show.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "booking_seats",
uniqueConstraints = {@UniqueConstraint(columnNames = {"booking_id","show_seat_id"})})
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class BookingSeat {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id",nullable = false)
    private Booking booking;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "show_seat_id",nullable = false)
    private ShowSeat showSeat;

    @Column(nullable = false)
    private BigDecimal ticketPrice;

    @Column(nullable = false)
    private String status;





}
