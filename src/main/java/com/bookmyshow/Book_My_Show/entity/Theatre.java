package com.bookmyshow.Book_My_Show.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="theatres")
@Builder
@AllArgsConstructor
@NoArgsConstructor


public class Theatre {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;


    @Column(nullable = false)
    private String pinCode;


    @OneToMany(mappedBy = "theatre",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
    private List<Screen> screens=new ArrayList<>();








}
