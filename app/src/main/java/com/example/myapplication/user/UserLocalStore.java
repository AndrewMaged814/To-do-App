package com.example.myapplication.user;

import android.content.Context;
import android.content.SharedPreferences;

// this class allows us to store user data on a file

public class UserLocalStore {
    /*we need to provide the name of
    the file where the user details will be
    stored when they've logged in */
    public static final String USER_DETAILS = "userDetails";

    private static final String IS_LOGIN = "IsLoggedIn";

    public static final String KEY_NAME = "name";
    public static final String KEY_AGE = "age";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";


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
        editor.putString(KEY_AGE, user.age);
        editor.putString(KEY_NAME, user.name);
        editor.putString(KEY_USERNAME, user.username);
        editor.putString(KEY_PASSWORD, user.password);
        editor.commit();
    }

    //gets data of the current user which is logged on
    public User getLoggedInUser() {
        String name = userLocalDatabase.getString("name", "");
        String age = userLocalDatabase.getString("age", "");
        String username = userLocalDatabase.getString("username", "");
        String password = userLocalDatabase.getString("password", "");

        return new NormalUser(name, username, password, age);
    }


    //if the user is logged in loggedIn is set to true otherwise false

    public void setUserLoggedIn(boolean loggedIn) {
        editor.putBoolean(IS_LOGIN, loggedIn);
        editor.commit();

    }

    public void RemoveUser() {
        editor.clear();
        editor.apply();
    }

}
