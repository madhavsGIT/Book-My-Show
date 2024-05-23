package com.acciojob.BookMyShow.Responses;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
public class BookingHistoryResponse {

    private LocalDate bookedDate;
    private String movieName;
    private String theaterName;
    private LocalDate showDate;
    private LocalTime showTime;
}
