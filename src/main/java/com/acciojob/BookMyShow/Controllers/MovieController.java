package com.acciojob.BookMyShow.Controllers;


import com.acciojob.BookMyShow.Requests.AddMovieRequest;
import com.acciojob.BookMyShow.Requests.UpdateMovieRequest;
import com.acciojob.BookMyShow.Responses.RecommendMoviesResponse;
import com.acciojob.BookMyShow.Responses.TheatersResponse;
import com.acciojob.BookMyShow.Servicies.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping("/add")
    public ResponseEntity addMovie(@RequestBody AddMovieRequest movieRequest){
        String response = movieService.addMovie(movieRequest);

        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PutMapping("update")
    public ResponseEntity updateMovieAttributes(@RequestBody UpdateMovieRequest movieRequest){
        String response = movieService.updateMovieAttributes(movieRequest);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("get-theaters-Of-movie")
    public List<TheatersResponse> gettheatersOfMovie(@RequestParam("movieName") String movieName){
      return movieService.showTheaters(movieName);
    }


    @GetMapping("recommend-movies")
    public List<RecommendMoviesResponse> showMovies(){
        return movieService.showMovieList();
    }


}
