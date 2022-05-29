package com.example.myapplication.HomeActivity.Notification;

public class ReminderNotify implements Notifications
{

    @Override
    public String setTitle()
    {
        return "Take a Break";
    }

    @Override
    public String setMessage()
    {
        return "You have been doing great \n" +
                "Move your legs and drink some water";
    }
}
