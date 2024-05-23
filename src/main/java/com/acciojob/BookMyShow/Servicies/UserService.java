package com.acciojob.BookMyShow.Servicies;

import com.acciojob.BookMyShow.Models.Ticket;
import com.acciojob.BookMyShow.Models.User;
import com.acciojob.BookMyShow.Repositories.TicketRepository;
import com.acciojob.BookMyShow.Repositories.UserRepository;
import com.acciojob.BookMyShow.Requests.AddUserRequest;
import com.acciojob.BookMyShow.Responses.BookingHistoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TicketRepository ticketRepository;

    public String addUser(AddUserRequest userRequest) {
         User user = User.builder().userName(userRequest.getUserName())
                 .age(userRequest.getAge())
                 .phoneNumber(userRequest.getPhoneNumber())
                 .emailId(userRequest.getEmailId())
                 .build();

         userRepository.save(user);

         return "This user has been saved to Db with userId "+user.getUserId();
    }

    public List<BookingHistoryResponse> userBookingHistory(String userName){


        List<Ticket> ticketList = ticketRepository.findAll();
        List<BookingHistoryResponse> bookingHistoryList = new ArrayList<>();

        for(Ticket ticket : ticketList) {
            if(ticket.getUser().getUserName().equals(userName)) {
                BookingHistoryResponse bookingHistory = BookingHistoryResponse.builder()
                        .bookedDate(ticket.getBookedDate())
                        .movieName(ticket.getMovieName())
                        .theaterName(ticket.getTheaterName())
                        .showDate(ticket.getShowDate())
                        .showTime(ticket.getShowTime())
                        .build();

                bookingHistoryList.add(bookingHistory);
            }
        }
        return bookingHistoryList;
    }

}
