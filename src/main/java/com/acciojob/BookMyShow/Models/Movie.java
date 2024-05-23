package com.acciojob.BookMyShow.Models;

import com.acciojob.BookMyShow.Enum.Genre;
import com.acciojob.BookMyShow.Enum.Language;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "movies")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int movieId;

    @Column(unique = true)
    private String movieName;

    private double duration;

    private double movieRating;

    @Enumerated(value = EnumType.STRING)
    private Genre genre;

    @Enumerated(value = EnumType.STRING)
    private Language language;

    private LocalDate releaseDate;


//    @OneToMany(mappedBy = "movie" , cascade = CascadeType.ALL)
//    private List<Show> showsList = new ArrayList<>();


}
