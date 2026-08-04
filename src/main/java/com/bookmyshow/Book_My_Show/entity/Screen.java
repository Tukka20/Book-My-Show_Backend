package com.bookmyshow.Book_My_Show.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "screens",
        uniqueConstraints = {@UniqueConstraint(columnNames={"theatre_id","name"})})
@Builder
@AllArgsConstructor
@NoArgsConstructor


public class Screen {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer totalSeats;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theatre_id",nullable = false)
    private Theatre theatre;


    @Builder.Default
   @OneToMany(mappedBy = "screen",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
    private List<Seat> seats=new ArrayList<>();


    @Builder.Default
    @OneToMany(mappedBy = "screen",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
    private List<Show> shows=new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "screen",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<SeatType> seatTypes=new ArrayList<>();






}
