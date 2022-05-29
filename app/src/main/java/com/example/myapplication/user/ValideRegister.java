package com.example.myapplication.user;

import com.example.myapplication.user.userTypes.User;

public interface ValideRegister {
    boolean ValideRegister(String name, String username, String password, String age, String confirmedPassword);
    void registerUser(User user);
}
