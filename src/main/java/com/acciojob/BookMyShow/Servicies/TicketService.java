package com.acciojob.BookMyShow.Servicies;

import com.acciojob.BookMyShow.Enum.SeatStatus;
import com.acciojob.BookMyShow.Enum.SeatType;
import com.acciojob.BookMyShow.Models.Show;
import com.acciojob.BookMyShow.Models.ShowSeats;
import com.acciojob.BookMyShow.Models.Ticket;
import com.acciojob.BookMyShow.Models.User;
import com.acciojob.BookMyShow.Repositories.ShowRepository;
import com.acciojob.BookMyShow.Repositories.ShowSeatsRepository;
import com.acciojob.BookMyShow.Repositories.TicketRepository;
import com.acciojob.BookMyShow.Repositories.UserRepository;
import com.acciojob.BookMyShow.Requests.BookTicketRequest;
import com.acciojob.BookMyShow.Responses.TicketResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private ShowSeatsRepository showSeatsRepository;

    @Autowired
    private UserRepository userRepository;


    public String bookTicket(BookTicketRequest ticketRequest){

        //getting show

        Show show = showRepository.findById(ticketRequest.getShowId()).get();

        //getting user

        User user = userRepository.findById(ticketRequest.getUserId()).get();

        //get seats list and mark the seats booked if contains and calculate totalamount

        Integer totalAmount = 0;

        List<ShowSeats> showSeatsList = show.getShowSeatsList();

        for(ShowSeats showSeats : showSeatsList) {
            String seatNo = showSeats.getSeatNO();
            if(ticketRequest.getRequestedSeats().contains(seatNo)){
                showSeats.setSeatStatus(SeatStatus.BOOKED);


                   if(showSeats.getSeatType().equals(SeatType.CLASSIC)){

                        totalAmount += 100;
                    }
                    else {
                        totalAmount += 200;
                    }
                    if(ticketRequest.isFoodAndBeverages()){
                        totalAmount += 50;
                        showSeats.setFoodAtached(true);
                    }

            }

        }

        //create ticket entity and update the attributes
        Ticket ticket = Ticket.builder().showDate(show.getShowDate())
                .showTime(show.getShowTime())
                .theaterName(show.getTheater().getTheaterName())
                .movieName(show.getMovie().getMovieName())
                .totalAmount(totalAmount)
                .bookedDate(LocalDate.now())
                .bookedSeats(ticketRequest.getRequestedSeats().toString())
                .show(show)
                .user(user)
                .build();

        showSeatsRepository.saveAll(showSeatsList);
        ticket = ticketRepository.save(ticket);

        return ticket.getTicketId();

    }

    public TicketResponse generateTicket(String ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId).get();

        TicketResponse tickectResponse = TicketResponse.builder()
                .bookedSeats(ticket.getBookedSeats())
                .movieName(ticket.getMovieName())
                .theaterName(ticket.getTheaterName())
                .showDate(ticket.getShowDate())
                .showTime(ticket.getShowTime())
                .bookedDate(LocalDate.now())
                .totalAmount(ticket.getTotalAmount())
                .build();

        return tickectResponse;
    }

    public Double calculateTotalRevenue(){

        //make a list of all booked tickets
        //find the total amts and sum them up

        Double revenue = 0.00;
         List<Ticket> ticketList = ticketRepository.findAll();
         for(Ticket ticket : ticketList) {
            revenue += ticket.getTotalAmount();

         }
        return revenue;

    }

    public Double calculateTotalRevenueOfTheater(String theaterName){
        Double revenue = 0.00;
        List<Ticket> ticketList = ticketRepository.findAll();
        for(Ticket ticket : ticketList) {
            if(ticket.getTheaterName().equals(theaterName)) {
                revenue += ticket.getTotalAmount();
            }
        }
        return revenue;
    }

    public Double calulateTotalRevenueOfTheaterOnADay(String theaterName, LocalDate date){
        Double revenue = 0.00;
        List<Ticket> ticketList = ticketRepository.findAll();
        for(Ticket ticket : ticketList) {
            if(ticket.getTheaterName().equals(theaterName) && ticket.getBookedDate().equals(date)) {
                revenue += ticket.getTotalAmount();
            }
        }
        return revenue;
    }




}
