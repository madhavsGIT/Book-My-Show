package com.acciojob.BookMyShow.Models;


import com.acciojob.BookMyShow.Enum.SeatType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TheaterSeats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int theaterSeatId;

    private String seatNO;

    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;

    @JoinColumn
    @ManyToOne
    private Theater theater;
}
