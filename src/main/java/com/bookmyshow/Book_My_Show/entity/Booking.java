package com.bookmyshow.Book_My_Show.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@Table(name="bookings")
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private String bookingNumber;

    @Column(nullable = false)
    private LocalDateTime bookingDateTime;

    @Column(nullable = false)
    private String  status;

    @Column(nullable = false)
    private BigDecimal totalAmount;

    @ManyToOne
    @JoinColumn(name="user_id",nullable = false )
    private User user;

    @ManyToOne
    @JoinColumn(name="show_id",nullable = false)
    private Show show;

    @Builder.Default
    @OneToMany(mappedBy = "booking",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<BookingSeat> bookingSeats =new ArrayList<>();

    @OneToOne(mappedBy = "booking",cascade = CascadeType.ALL,orphanRemoval = true)
    private Payment payment;






}
