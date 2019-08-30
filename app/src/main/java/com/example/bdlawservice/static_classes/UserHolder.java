package com.example.bdlawservice.static_classes;

import com.example.bdlawservice.entity.User;

public class UserHolder {
    static User user;
    public static void setUser(User u){
        user=u;
    }
    public static User getUser() {
        return user;
    }
}
