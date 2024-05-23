package com.acciojob.BookMyShow.Models;

import com.acciojob.BookMyShow.Enum.SeatType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "tickets")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String ticketId;

    private String bookedSeats;

    private LocalDate showDate;

    private LocalTime showTime;

    private SeatType seatType;

    private String movieName;

    private String theaterName;

    private Integer totalAmount;

    private LocalDate bookedDate;

    @JoinColumn
    @ManyToOne
    private Show show;

    @JoinColumn
    @ManyToOne
    private User user;

}
