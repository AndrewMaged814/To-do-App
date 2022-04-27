package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;

// this class allows us to store user data on a file

public class UserLocalStore {
    /* the name we need to provide the name of
    the file where the user details will be
    stored when they've logged in */
    public static final String SP_NAME= "userDetails";

    //SharedPreferences allows data to be stored locally in a file
    SharedPreferences userLocalDatabase;

    //SharedPreferences need to know where the app is located
    // and the only way to know that by a Context.
    public UserLocalStore (Context context) {
        userLocalDatabase = context.getSharedPreferences(SP_NAME,0);

    }
    public void storeUserData(User user){
        //this allows to edit what is stored
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putInt("age", user.age);
        spEditor.putString("name", user.name);
        spEditor.putString("username", user.username);
        spEditor.putString("password", user.password);
        spEditor.commit();
    }
    //gets data of the current user which is logged on
    public User getLoggedInUser(){
        String name = userLocalDatabase.getString("name","");
        int age = userLocalDatabase.getInt("age",-1);
        String username = userLocalDatabase.getString("username","");
        String password = userLocalDatabase.getString("password","");

        return new User(name,username,password,age);
    }
    //if the user is logged in loggedIn is set to true otherwise false
    public void setUserLoggedIn(Boolean loggedIn){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putBoolean("loggedIn",loggedIn);
        spEditor.commit();
    }
    public boolean getUserLoggedIn(){
        if(userLocalDatabase.getBoolean("loggedin",false)==true)
            return true;
        else
            return false;
    }
    public void clearUserData(){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.clear();
        spEditor.commit();
    }

}
