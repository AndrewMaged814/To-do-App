package com.example.myapplication.user;

public class NormalUser extends User{
    //when Register
    public NormalUser(String name, String username, String password, String age) {
        super(name, username, password, age);
    }

    public NormalUser(String username, String password) {
        super(username, password);
    }

}
