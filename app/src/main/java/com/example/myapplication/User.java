package com.example.myapplication;

public class User {
    String name, username, password,age;

    //when creating a new user
//when Register
    public User(String name, String username, String password, String age) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.age = age;
    }

    //when Login
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.age = "";
        this.name = "";
    }
}
