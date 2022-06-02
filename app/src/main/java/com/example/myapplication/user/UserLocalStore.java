package com.example.myapplication.user;

import android.content.Context;
import android.content.SharedPreferences;
import com.example.myapplication.user.userTypes.NormalUser;
import com.example.myapplication.user.userTypes.User;

// this class allows us to store user data on a file

public class UserLocalStore {
    /*we need to provide the name of
    the file where the user details will be
    stored when they've logged in */
    public static final String USER_DETAILS = "userDetails";

    public static final String KEY_NAME = "name";
    public static final String KEY_AGE = "age";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_GENDER = "gender";
    public static final String KEY_STATUS = "status";
    public static final String KEY_FIRSTTIME = "firsttime";


    //SharedPreferences allows data to be stored locally in a file
    SharedPreferences userLocalDatabase;
    SharedPreferences.Editor editor;
    Context context;

    //SharedPreferences need to know where the app is located
    // and the only way to know that by a Context.
    public UserLocalStore(Context context) {
        this.context = context;
        userLocalDatabase = context.getSharedPreferences(USER_DETAILS, 0);
        editor = userLocalDatabase.edit();

    }

    public void storeUserData(User user) {
        editor.putInt(KEY_AGE, user.getAge());
        editor.putString(KEY_NAME, user.getName());
        editor.putString(KEY_USERNAME, user.getUsername());
        editor.putString(KEY_PASSWORD, user.getPassword());
        editor.putString(KEY_GENDER, user.getGender());
        editor.putBoolean(KEY_STATUS,NormalUser.getStatus());
        editor.putBoolean(KEY_FIRSTTIME,user.isFirstTimeLogin());
        editor.commit();
    }

    //gets data of the current user which is logged on
    public User getLoggedInUser() {
        String name = userLocalDatabase.getString(KEY_NAME, "");
        int age = userLocalDatabase.getInt(KEY_AGE, 0);
        String username = userLocalDatabase.getString(KEY_USERNAME, "");
        String password = userLocalDatabase.getString(KEY_PASSWORD, "");
        String gender = userLocalDatabase.getString(KEY_GENDER, "");
        boolean firstTime = userLocalDatabase.getBoolean(KEY_FIRSTTIME, true);

        return new NormalUser(name, username, password, age,gender,firstTime);
    }

    public void setUserStatus(boolean status) {
        editor.putBoolean(KEY_STATUS, status);
        editor.commit();
    }
    public void setfirstTime(boolean status) {
        editor.putBoolean(KEY_FIRSTTIME, status);
        editor.commit();
    }
    public boolean getUserStatus(){
        return userLocalDatabase.getBoolean(KEY_STATUS, false);
    }
    public boolean getfirstTime(){
        return userLocalDatabase.getBoolean(KEY_FIRSTTIME, false);
    }
    public void RemoveUser() {
        editor.clear();
        editor.apply();
    }

}
