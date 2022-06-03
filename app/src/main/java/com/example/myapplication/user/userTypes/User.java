package com.example.myapplication.user.userTypes;

import android.content.SharedPreferences;
import com.example.myapplication.task.Activities.PersonalityTest;
import com.example.myapplication.task.SortTaskList;
import com.example.myapplication.task.Task;
import com.example.myapplication.task.taskTypes.DelayedTask;
import com.example.myapplication.task.taskTypes.Today;
import com.example.myapplication.task.taskTypes.UpComing;
import com.example.myapplication.user.Personality.Energetic;
import com.example.myapplication.user.Personality.Personality;
import com.example.myapplication.user.Personality.Procrastinator;
import com.example.myapplication.user.Personality.Workaholic;
import com.example.myapplication.user.UserLocalStore;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

abstract public class User {
    private String name, username, password, gender;
    private boolean firstTimeLogin;

    private int age;
    private static int personalityType;
    public static List<Task> eventsList;
    static Personality UserPersonality;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;


    }

    public User(String name, String username, String password, int age, String gender, boolean firstTimeLogin) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.age = age;
        this.gender = gender;
        this.firstTimeLogin = firstTimeLogin;

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean isFirstTimeLogin() {
        return firstTimeLogin;
    }

    public void setFirstTimeLogin(boolean firstTimeLogin) {
        this.firstTimeLogin = firstTimeLogin;
    }

    public static void mergeArrayList() {
        eventsList = new ArrayList<>();
        eventsList.addAll(Today.TodTasks);
        eventsList.addAll(DelayedTask.DelayedTasks);
        eventsList.addAll(UpComing.UpComingTasks);
        SortTaskList.sortByPriority(eventsList);
    }

    public static Personality PersonalityType(int personalityType) {
        if (personalityType == 1) {
            UserPersonality = new Energetic();
            UserPersonality.setName("Energetic");
            return UserPersonality;

        } else if (personalityType == 2) {
            UserPersonality = new Workaholic();
            UserPersonality.setName("Workaholic");
            return UserPersonality;

        } else {
            UserPersonality = new Procrastinator();
            UserPersonality.setName("Procrastinator");
            return UserPersonality;

        }

    }
}
