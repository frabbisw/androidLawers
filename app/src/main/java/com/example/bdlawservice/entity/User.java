package com.example.bdlawservice.entity;

public class User {
    private String id;
    private String name;
    private String mobile;
    private String user_status;
    private String api_token;
    private String address;

    public User(String id, String name, String mobile, String user_status, String api_token, String address) {
        this.id = id;
        this.name = name;
        this.mobile = mobile;
        this.user_status = user_status;
        this.api_token = api_token;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUser_status() {
        return user_status;
    }

    public void setUser_status(String user_status) {
        this.user_status = user_status;
    }

    public String getApi_token() {
        return api_token;
    }

    public void setApi_token(String api_token) {
        this.api_token = api_token;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
