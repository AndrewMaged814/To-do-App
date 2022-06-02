package com.example.myapplication.user.userTypes;

 abstract public class User {
     private String name, username, password,gender;
     private boolean firstTimeLogin;

     private int age;


    public User() {
    }
    public User(String username, String password) {
        this.username = username;
        this.password = password;

    }

    public User(String name, String username, String password, int age,String gender,boolean firstTimeLogin) {
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
 }
