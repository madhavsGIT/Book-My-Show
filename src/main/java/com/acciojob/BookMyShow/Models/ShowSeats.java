package com.acciojob.BookMyShow.Models;


import com.acciojob.BookMyShow.Enum.SeatStatus;
import com.acciojob.BookMyShow.Enum.SeatType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShowSeats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seatId;
    private String seatNO;

    @Enumerated(value = EnumType.STRING)
    private SeatStatus seatStatus;

    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;

    private boolean isFoodAtached;

    @JoinColumn
    @ManyToOne
    private Show shows;





}
