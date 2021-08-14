package com.example.csp;

public class User {
    //possibly add phone number
    public String fullName, emailAdd, phoneNo;


    public User(){}

    public User(String fullName, String phoneNo, String emailAdd){
        this.fullName = fullName;
        this.phoneNo = phoneNo;
        this.emailAdd = emailAdd;
    }
}
