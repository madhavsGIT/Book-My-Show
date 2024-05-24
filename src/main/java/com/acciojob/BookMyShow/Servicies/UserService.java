package com.acciojob.BookMyShow.Servicies;

import com.acciojob.BookMyShow.Models.Ticket;
import com.acciojob.BookMyShow.Models.User;
import com.acciojob.BookMyShow.Repositories.TicketRepository;
import com.acciojob.BookMyShow.Repositories.UserRepository;
import com.acciojob.BookMyShow.Requests.AddUserRequest;
import com.acciojob.BookMyShow.Responses.BookingHistoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    public String addUser(AddUserRequest userRequest) {

         User user = User.builder().userName(userRequest.getUserName())
                 .age(userRequest.getAge())
                 .phoneNumber(userRequest.getPhoneNumber())
                 .emailId(userRequest.getEmailId())
                 .build();

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(userRequest.getEmailId());
        mailMessage.setFrom("springoptional@gmail.com");
        mailMessage.setSubject("Welcome to Book My Show!");

        String body = "Hello "+userRequest.getUserName()+ "\n" +"your BookMyShow account has been created successfully."+
                "\n" + "Book tickets and enjoy watching new movies !" + "\n"+
                "Thank you";
        mailMessage.setText(body);

        javaMailSender.send(mailMessage);

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
