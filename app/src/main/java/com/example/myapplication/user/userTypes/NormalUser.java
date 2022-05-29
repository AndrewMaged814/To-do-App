package com.example.myapplication.user.userTypes;

public class NormalUser extends User{
    //accepted or pending
    protected static boolean status;

    public NormalUser() {
    }

    public NormalUser(String username, String password) {
        super(username, password);
    }

    public NormalUser(String name, String username, String password, int age,String gender) {
        super(name, username, password, age,gender);
    }
    public static boolean getStatus() {
        return status;
    }

    public static void setStatus(boolean status) {
        NormalUser.status = status;
    }


}
