package com.acciojob.BookMyShow.Requests;

import lombok.Data;

@Data
public class AddUserRequest {

    private String userName;
    private int age;
    private String phoneNumber;
    private String emailId;

}
