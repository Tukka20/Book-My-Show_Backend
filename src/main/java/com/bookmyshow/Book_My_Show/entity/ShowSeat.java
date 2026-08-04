package com.bookmyshow.Book_My_Show.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name="show_seats",
uniqueConstraints = {@UniqueConstraint(columnNames = {"show_id","seat_id"})})
@Builder
@AllArgsConstructor
@NoArgsConstructor


public class ShowSeat {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private BigDecimal price;


    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name = "show_id",nullable = false)
    private Show show;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="seat_id",nullable = false)
    private Seat seat;


    @OneToMany(mappedBy = "showSeat",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<BookingSeat> bookingSeats=new ArrayList<>();
    



}
