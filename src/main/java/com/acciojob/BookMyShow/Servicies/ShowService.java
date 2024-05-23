package com.acciojob.BookMyShow.Servicies;


import com.acciojob.BookMyShow.Enum.SeatStatus;
import com.acciojob.BookMyShow.Models.*;
import com.acciojob.BookMyShow.Repositories.MovieRepository;
import com.acciojob.BookMyShow.Repositories.ShowRepository;
import com.acciojob.BookMyShow.Repositories.ShowSeatsRepository;
import com.acciojob.BookMyShow.Repositories.TheaterRepository;
import com.acciojob.BookMyShow.Requests.AddShowRequest;
import com.acciojob.BookMyShow.Responses.ShowListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowService {

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private TheaterRepository theaterRepository;

    @Autowired
    private ShowSeatsRepository showSeatsRepository;



    public String addShow(AddShowRequest showRequest) {

        //get the movie
        Movie movie = movieRepository.findMovieByMovieName(showRequest.getMovieName());

        //get the theater
        Theater theater = theaterRepository.findById(showRequest.getTheaterId()).get();



        Show show = Show.builder().showDate(showRequest.getShowData())
                .showTime(showRequest.getShowTime())
                .movie(movie)
                .theater(theater)
                .build();

        showRepository.save(show);



        //associating corresponding showSeats
        List<TheaterSeats> theaterSeatsList = theater.getTheaterSeatList();
        List<ShowSeats> showSeatsList = new ArrayList<>();




        for(TheaterSeats theaterSeat : theaterSeatsList) {
            ShowSeats showSeats = ShowSeats.builder().seatNO(theaterSeat.getSeatNO())
                    .seatType(theaterSeat.getSeatType())
                    .seatStatus(SeatStatus.AVAILABLE)
                    .isFoodAtached(Boolean.FALSE)
                    .shows(show)

                    .build();
            showSeatsList.add(showSeats);
        }

        show.setShowSeatsList(showSeatsList);

        showSeatsRepository.saveAll(showSeatsList);

        return "This Show has been saved to Db";
    }

    public ShowListResponse getShowList(){

        ShowListResponse showListResponse = ShowListResponse.builder().showList(showRepository.findAll()).build();
        return showListResponse;
    }

}
