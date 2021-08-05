package com.example.daysevenexfive;

public class UserDetails {
    String name,location,dob,bgroup,last_donated,mobile;
    public String getBgroup() {
        return bgroup;
    }
    public void setBgroup(String bgroup) {
        this.bgroup=bgroup;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getLast_donated() {
        return last_donated;
    }

    public void setLast_donated(String last_donated) {
        this.last_donated = last_donated;
    }

}
