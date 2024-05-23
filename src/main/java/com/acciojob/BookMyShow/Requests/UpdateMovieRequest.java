package com.acciojob.BookMyShow.Requests;

import com.acciojob.BookMyShow.Enum.Language;
import lombok.Data;

@Data
public class UpdateMovieRequest {

    private String movieName;
    private Language newLanguage;
    private double newRating;
}
