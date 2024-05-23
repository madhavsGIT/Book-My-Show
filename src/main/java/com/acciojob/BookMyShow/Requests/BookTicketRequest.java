package com.acciojob.BookMyShow.Requests;

import com.acciojob.BookMyShow.Enum.SeatType;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class BookTicketRequest {

    private int userId;
    private int showId;
    private List<String> requestedSeats;
    private boolean foodAndBeverages;


}
