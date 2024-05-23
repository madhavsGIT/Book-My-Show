package com.acciojob.BookMyShow.Requests;

import com.acciojob.BookMyShow.Enum.Language;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AddMovieRequest {

        private String movieName;
        private Language language;
        private double duration;
        private double rating;
        private LocalDate releaseDate;

}
