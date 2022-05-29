package com.example.myapplication.HomeActivity.Notification;

public class InitialNotify implements Notifications
{

    @Override
    public String setTitle() {
        return "Lets Go!";
    }

    @Override
    public String setMessage() {
        return "You can do this, just stay focused.";
    }
}
