package com.dmt.sannguyen.lgwfb;

public class BeYeu{
    private int BabyID;
    private String Name;
    private String DateOfBirth;
    private String Avatar;
    private String Gender;
    private int Age_Hour;
    private String Age_Display;


    public BeYeu(int babyID, String name, String dateOfBirth, String avatar, String gender, int age_Hour, String age_Display) {
        BabyID = babyID;
        Name = name;
        DateOfBirth = dateOfBirth;
        Avatar = avatar;
        Gender = gender;
        Age_Hour = age_Hour;
        Age_Display = age_Display;
    }

    public int getBabyID() {
        return BabyID;
    }

    public void setBabyID(int babyID) {
        BabyID = babyID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }

    public String getAvatar() {
        return Avatar;
    }

    public void setAvatar(String avatar) {
        Avatar = avatar;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public int getAge_Hour() {
        return Age_Hour;
    }

    public void setAge_Hour(int age_Hour) {
        Age_Hour = age_Hour;
    }

    public String getAge_Display() {
        return Age_Display;
    }

    public void setAge_Display(String age_Display) {
        Age_Display = age_Display;
    }
}
