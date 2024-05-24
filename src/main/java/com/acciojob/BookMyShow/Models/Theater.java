package com.acciojob.BookMyShow.Models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "theaters")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Theater {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int theaterId;

    @Column(unique = true)
    private String theaterName;

    private String address;

    //bidirectional mapping
    @OneToMany(mappedBy = "theater" , cascade = CascadeType.ALL)
    private List<TheaterSeats> theaterSeatList = new ArrayList<>();





}
