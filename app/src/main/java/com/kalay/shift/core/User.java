package com.kalay.shift.core;

public class User {
    private String userName; 
    private Gender gender;

    public User(String userName, Gender gender){
        this.userName = userName;
        this.gender = gender;
    }
    public String getUserName() {
        return userName;
    }
    public Gender getGender(){
        return gender;
    }
}
