package com.bookmyshow.Book_My_Show.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "seat_types",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"screen_id", "name"})})
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SeatType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false,length = 50)
    private String  name;


    private String description;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "screen_id",nullable = false)
    private Screen screen;


    @Builder.Default
    @OneToMany(
            mappedBy = "seatType",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Seat> seats = new ArrayList<>();
}
