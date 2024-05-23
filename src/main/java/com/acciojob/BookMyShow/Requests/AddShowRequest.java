package com.acciojob.BookMyShow.Requests;

import com.acciojob.BookMyShow.Models.Movie;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class AddShowRequest {

    private LocalDate showData;
    private LocalTime showTime;

    private String movieName;
    private Integer theaterId;
}
