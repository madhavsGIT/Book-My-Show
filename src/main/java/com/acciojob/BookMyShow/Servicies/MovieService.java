package com.acciojob.BookMyShow.Servicies;


import com.acciojob.BookMyShow.Models.Movie;
import com.acciojob.BookMyShow.Models.Show;
import com.acciojob.BookMyShow.Repositories.MovieRepository;
import com.acciojob.BookMyShow.Repositories.ShowRepository;
import com.acciojob.BookMyShow.Requests.AddMovieRequest;
import com.acciojob.BookMyShow.Requests.UpdateMovieRequest;
import com.acciojob.BookMyShow.Responses.RecommendMoviesResponse;
import com.acciojob.BookMyShow.Responses.ShowListResponse;
import com.acciojob.BookMyShow.Responses.TheatersResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ShowService showService;

    @Autowired
    private ShowRepository showRepository;





    public String addMovie(AddMovieRequest addMovieRequest){
       Movie movie = new Movie();
       movie.setMovieName(addMovieRequest.getMovieName());
       movie.setLanguage(addMovieRequest.getLanguage());
       movie.setDuration(addMovieRequest.getDuration());
       movie.setMovieRating(addMovieRequest.getRating());
       movie.setReleaseDate(addMovieRequest.getReleaseDate());

       movie = movieRepository.save(movie);
       return "This Movie has been added to the DB with movieId "+movie.getMovieId();
    }

    public String updateMovieAttributes(UpdateMovieRequest movieRequest){

        //findmovie

        Movie movie = movieRepository.findMovieByMovieName(movieRequest.getMovieName());

        //update the attributes

        movie.setLanguage(movieRequest.getNewLanguage());
        movie.setMovieRating(movieRequest.getNewRating());

        //save to db

        movieRepository.save(movie);

        return "This Movie has been updated";


    }



    public List<TheatersResponse> showTheaters(String movieName) {

        // get movie by name
        Movie movie = movieRepository.findMovieByMovieName(movieName);

        // find showlist
        ShowListResponse showListResponse = showService.getShowList();
        List<Show> showList = showListResponse.getShowList();
        // iterate shows in theater
        List<TheatersResponse> responseList = new ArrayList<>();

        for (Show show : showList) {
            if (show.getMovie().equals(movie)) {
                TheatersResponse theatersResponse = TheatersResponse.builder().movieName(show.getMovie().getMovieName())
                        .showTime(show.getShowTime())
                        .showDate(show.getShowDate())
                        .theaterName(show.getTheater().getTheaterName())
                        .build();
                responseList.add(theatersResponse);
            }
        }
        return responseList;
    }

    public List<RecommendMoviesResponse> showMovieList(){


        List<RecommendMoviesResponse> moviesList = new ArrayList<>();
        LocalDate currentDate = LocalDate.now();
        List<Show> showList = showRepository.findAll();
        for(Show show : showList) {
            if(!show.getShowDate().isBefore(currentDate) ){
                RecommendMoviesResponse recommendMovie = RecommendMoviesResponse.builder()
                        .movieName(show.getMovie().getMovieName())
                        .theaterName(show.getTheater().getTheaterName())
                        .showDate(show.getShowDate())
                        .showTime(show.getShowTime())
                        .build();

                moviesList.add(recommendMovie);
            }
        }
        return moviesList;
    }



    }
