package com.example.myapplication.HomeActivity.Notification;

import androidx.annotation.NonNull;

public class motivationalNotify implements Notifications
{
    @Override
    public String setTitle()
    {
        return "You are almost there !!";
    }

    @Override
    public String setMessage()
    {
        return "Keep up the Good work !!" ;
    }
}
