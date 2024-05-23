package com.acciojob.BookMyShow.Controllers;

import com.acciojob.BookMyShow.Requests.BookTicketRequest;
import com.acciojob.BookMyShow.Responses.TicketResponse;
import com.acciojob.BookMyShow.Servicies.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping("book-ticket")
    public String bookTicket(@RequestBody BookTicketRequest ticketRequest) {
        return ticketService.bookTicket(ticketRequest);
    }

    @GetMapping("/generate-ticket")
    public TicketResponse generateTicket(@RequestParam("ticketId") String ticketId){
        return ticketService.generateTicket(ticketId);
    }

    //grossRevenue
    @GetMapping("get-total-revenue")
    public Double calulateTotalRevenue(){
        return ticketService.calculateTotalRevenue();
    }

    //Revenue of particularTheater
    @GetMapping("get-revenue-of-theater")
    public Double calculateTotalRevenueOfTheater(@RequestParam("theaterName") String theaterName){
        return ticketService.calculateTotalRevenueOfTheater(theaterName);
    }

    //Revenue of particularTheater on given day
    @GetMapping("get-revenue-of-theater-on-day")
    public Double calculateTotalRevenueOfTheaterOnDay(@RequestParam("theaterName") String theaterName,
                                                     @RequestParam("date")LocalDate date){

        return ticketService.calulateTotalRevenueOfTheaterOnADay(theaterName,date);
    }


}
