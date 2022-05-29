package com.example.myapplication.user.userTypes;

public class Admin extends User{
    private final static String adminUsername ="admin";
    private final static String adminPassword ="admin";
    private boolean Adminprivilege;

    public boolean getAdminprivilege() {
        return Adminprivilege;
    }

    public void setAdminprivilege(boolean Adminprivileges) {
        this.Adminprivilege = Adminprivileges;
    }

    public static String getAdminUsername() {
        return adminUsername;
    }
    public static String getAdminPassword() {
        return adminPassword;
    }

    public Admin() {
    }

}
