package com.acciojob.BookMyShow.Controllers;

import com.acciojob.BookMyShow.BookMyShowApplication;
import com.acciojob.BookMyShow.Requests.AddUserRequest;
import com.acciojob.BookMyShow.Responses.BookingHistoryResponse;
import com.acciojob.BookMyShow.Servicies.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("add")
    public String addUser(@RequestBody AddUserRequest userRequest) {

        String response = userService.addUser(userRequest);
        return response;

    }

    @GetMapping("my-booking-history")
    public List<BookingHistoryResponse> showBookingHistory(@RequestParam("userName") String userName) {
        return userService.userBookingHistory(userName);
    }
}
