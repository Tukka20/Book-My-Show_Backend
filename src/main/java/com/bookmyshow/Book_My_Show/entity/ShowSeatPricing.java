package com.bookmyshow.Book_My_Show.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "show_seat_pricing",
                uniqueConstraints = {@UniqueConstraint(columnNames = {"show_id", "seat_type_id"})})

public class ShowSeatPricing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(nullable = false)
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "show_id",nullable = false)
    private Show show;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seat_type_id",nullable = false)
    private SeatType seatType;


}
