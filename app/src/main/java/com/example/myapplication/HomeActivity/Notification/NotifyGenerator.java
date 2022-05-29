package com.example.myapplication.HomeActivity.Notification;

public class NotifyGenerator
{
    InitialNotify IN = new InitialNotify();
    motivationalNotify MN= new motivationalNotify();
    ReminderNotify RN= new ReminderNotify();
    String Message;
    String Title;


    public String MGenerator (int i)
    {

        if (i == 0)
        {

            Message = IN.setMessage();

        }

        else if (i == 1)
        {
            Message = MN.setMessage();

        }

        else
        {
            Message = RN.setMessage();

        }


        return Message;

    }


    public String TGenerator (int i)
    {
        if (i==0)
        {
            Title = IN.setTitle();

        }

        else if (i == 1)
        {
            Title = MN.setTitle();

        }

        else
        {
            Title = RN.setTitle();

        }

        return Title;

    }

}
