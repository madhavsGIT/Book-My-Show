package com.acciojob.BookMyShow.Controllers;

import com.acciojob.BookMyShow.Models.Show;
import com.acciojob.BookMyShow.Requests.AddShowRequest;
import com.acciojob.BookMyShow.Responses.ShowListResponse;
import com.acciojob.BookMyShow.Servicies.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("show")
public class ShowController {

    @Autowired
    private ShowService showService;

    @PostMapping("add")
    public ResponseEntity addShow(@RequestBody AddShowRequest  showRequest) {

           String  response = showService.addShow(showRequest);


        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("get-showsList")
    public ShowListResponse getShowsList(){
        return showService.getShowList();
    }
}

