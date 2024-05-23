package com.acciojob.BookMyShow.Responses;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
public class TheatersResponse {
    private LocalDate showDate;
    private LocalTime showTime;
    private String movieName;
    private String theaterName;

}
