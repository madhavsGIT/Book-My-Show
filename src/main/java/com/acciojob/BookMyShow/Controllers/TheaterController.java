package com.acciojob.BookMyShow.Controllers;

import com.acciojob.BookMyShow.Models.Show;
import com.acciojob.BookMyShow.Requests.AddTheaterRequest;
import com.acciojob.BookMyShow.Requests.AddTheaterSeatsRequest;
import com.acciojob.BookMyShow.Responses.MoviesResponse;
import com.acciojob.BookMyShow.Servicies.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("theater")
public class TheaterController {

    @Autowired
    private TheaterService theaterService;

    @PostMapping("add")
    public ResponseEntity addTheater(@RequestBody AddTheaterRequest theaterRequest){
        String response = theaterService.addTheater(theaterRequest);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PutMapping("associateSeats")
    public ResponseEntity associateSeats(@RequestBody AddTheaterSeatsRequest theaterSeatsRequest){
        String response = theaterService.associateTheaterSeats(theaterSeatsRequest);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    // get movies of perticular theater

    @GetMapping("get-movies-list")
    public List<MoviesResponse> getmoviesListOfTheater(@RequestParam("theaterName") String thaterName){
        return theaterService.showMovies(thaterName);
    }

    // calculate revenue
//    @GetMapping("calculate-revenue")
//    public int calucateRevenueOfTheater(@RequestParam("theaterName") String theaterName){
//
//    }

}
