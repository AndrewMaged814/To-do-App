package com.example.myapplication.user;

import com.example.myapplication.user.userTypes.User;

public interface ValidatesLogin {
    boolean validateLogin( String username, String password);
    boolean authenticate(User user);
    boolean checkAdminPrivileges(String username, String password);
}
