package com.acciojob.BookMyShow.Servicies;

import com.acciojob.BookMyShow.Enum.SeatType;
import com.acciojob.BookMyShow.Models.Show;
import com.acciojob.BookMyShow.Models.Theater;
import com.acciojob.BookMyShow.Models.TheaterSeats;
import com.acciojob.BookMyShow.Repositories.TheaterRepository;
import com.acciojob.BookMyShow.Repositories.TheaterSeatRepository;
import com.acciojob.BookMyShow.Requests.AddTheaterRequest;
import com.acciojob.BookMyShow.Requests.AddTheaterSeatsRequest;
import com.acciojob.BookMyShow.Responses.MoviesResponse;
import com.acciojob.BookMyShow.Responses.ShowListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheaterService {

    @Autowired
    private TheaterRepository theaterRepository;

    @Autowired
    private TheaterSeatRepository theaterSeatRepository;

    @Autowired
    private ShowService showService;

    public String addTheater(AddTheaterRequest theaterRequest){

        Theater theater = Theater.builder().theaterName(theaterRequest.getTheaterName())
                .address(theaterRequest.getAddress())

                .build();
        theater = theaterRepository.save(theater);

        return "This Theater has been added to Db with theaterid "+ theater.getTheaterId();

    }

    public String associateTheaterSeats(AddTheaterSeatsRequest theaterSeatsRequest) {
        int theaterId = theaterSeatsRequest.getTheaterId();
        int noOfClassicSeats = theaterSeatsRequest.getNoOfClassicSeats();
        int noOfPremiumSeats = theaterSeatsRequest.getNoOfPremiumSeats();

        List<TheaterSeats> theaterSeatsList = new ArrayList<>();

        // get theater entity
        Theater theater = theaterRepository.findById(theaterId).get();

        //generate seats for classic
        int rowsInClassicSeats = noOfClassicSeats / 10;
        int seatsInLastRowOfClassic = noOfClassicSeats % 10;
        int row;

        for(row = 1; row <= rowsInClassicSeats; row++) {
            for(int j = 1; j <= 10; j++) {
                char ch = (char)('A' + row - 1);
                String seatNo = "" + ch + j;

                TheaterSeats theaterSeats = TheaterSeats.builder().seatNO(seatNo)
                        .seatType(SeatType.CLASSIC)
                        .theater(theater)
                        .build();
                theaterSeatsList.add(theaterSeats);
            }
        }

        //lastrow
        for(int j = 1; j <= seatsInLastRowOfClassic; j++) {
            char ch = (char)('A' + row - 1);
            String seatNo = "" + ch + j;

            TheaterSeats theaterSeats = TheaterSeats.builder().seatNO(seatNo)
                    .seatType(SeatType.CLASSIC)
                    .theater(theater)
                    .build();
            theaterSeatsList.add(theaterSeats);

        }

        //generating seats for premium
        int rowsInPremiumSeats = noOfPremiumSeats / 10;
        int seatsInLastRowOfPremium = noOfPremiumSeats % 10;
        int currentRow = row;

        if(seatsInLastRowOfClassic > 0){
            currentRow++;
        }

        for(row = currentRow; row <= rowsInPremiumSeats + currentRow - 1; row++) {
            for(int j = 1; j <= 10; j++) {
                char ch = (char)('A' + row - 1);
                String seatNo = "" + ch + j;

                TheaterSeats theaterSeats = TheaterSeats.builder().seatNO(seatNo)
                        .seatType(SeatType.PREMIUM)
                        .theater(theater)
                        .build();
                theaterSeatsList.add(theaterSeats);
            }
        }

        //lastrow
        for(int j = 1; j <= seatsInLastRowOfPremium; j++) {
            char ch = (char)('A' + row - 1);
            String seatNo = "" + ch + j;

            TheaterSeats theaterSeats = TheaterSeats.builder().seatNO(seatNo)
                    .seatType(SeatType.PREMIUM)
                    .theater(theater)
                    .build();
            theaterSeatsList.add(theaterSeats);

        }

        //saving to Db

        theater.setTheaterSeatList(theaterSeatsList);
        theaterRepository.save(theater);

        theaterSeatRepository.saveAll(theaterSeatsList);
        return "The theater seats have been associated";

    }


    public List<MoviesResponse> showMovies( String theaterName){

        // get theater by name
        Theater theater = theaterRepository.findByTheaterName(theaterName);

        // find showlist
        ShowListResponse showListResponse = showService.getShowList();
        List<Show> showList = showListResponse.getShowList();
        // iterate shows in theater
        List<MoviesResponse> responseList = new ArrayList<>();

        for(Show show : showList) {
            if(show.getTheater().equals(theater)) {
                MoviesResponse moviesResponse = MoviesResponse.builder().movieName(show.getMovie().getMovieName())
                        .showTime(show.getShowTime())
                        .showDate(show.getShowDate())
                        .theaterName(show.getTheater().getTheaterName())
                        .build();
                responseList.add(moviesResponse);
            }
        }
        return responseList;

    }

}
