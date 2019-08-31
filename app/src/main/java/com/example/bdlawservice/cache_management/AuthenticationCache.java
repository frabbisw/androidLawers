package com.example.bdlawservice.cache_management;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.bdlawservice.entity.User;
import com.google.gson.Gson;

public class AuthenticationCache {
    static Gson gson = new Gson();
    private final static String userKey = "USER";
    public static void setUserToCache(Context context, User user){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();

        String userJson = new Gson().toJson(user);

        editor.putString(userKey, userJson);
        editor.commit();
    }
    public static User getUserFromCache(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String userJson = preferences.getString(userKey, null);

        User user = new Gson().fromJson(userJson, User.class);
        return user;
    }
    public static void clearUserFromCache(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString(userKey, null);
        editor.commit();
    }
}
