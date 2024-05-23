package com.acciojob.BookMyShow.Requests;

import lombok.Data;

@Data
public class AddTheaterSeatsRequest {

    private int theaterId;
    private int noOfClassicSeats;
    private int noOfPremiumSeats;

}
