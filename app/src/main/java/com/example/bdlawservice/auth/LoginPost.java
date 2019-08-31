package com.example.bdlawservice.auth;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.bdlawservice.entity.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import javax.security.auth.callback.Callback;

public class LoginPost {
    Context context;
    VolleyCallBack callBack;

    public LoginPost(Context context, VolleyCallBack callback) {
        this.context = context;
        this.callBack = callback;
    }

    public void sendPostRequest(String mobile, String password) {
        try {
            RequestQueue queue = Volley.newRequestQueue(context);

            String URL = Constants.LOGIN_URL + Constants.LOGIN_API_KEY;
            JSONObject jsonBody = new JSONObject();

            jsonBody.put("mobile", mobile);
            jsonBody.put("password", password);

            //jsonBody.put("mobile", "01767764889");
            //jsonBody.put("password", "aaaaaa");

            JsonObjectRequest jsonOblect = new JsonObjectRequest(Request.Method.POST, URL, jsonBody, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.e("FRABBI::", response.toString());

                    parseJsonRequest(response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("FRERROR:: ", error.toString());
                    Toast.makeText(context, "Login Failed", Toast.LENGTH_SHORT).show();
                    callBack.action(0, null);
                }
            });
            queue.add(jsonOblect);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        // Toast.makeText(getApplicationContext(), "done", Toast.LENGTH_LONG).show();

    }

    private void parseJsonRequest(JSONObject response) {
        try {
            String login_status = response.getString("status");
            if (login_status.equals("1")) {
                JSONObject data = (JSONObject) response.get("data");
                JSONObject jsonUser = (JSONObject) data.get("user");

                String id = jsonUser.getString("id");
                String name = jsonUser.getString("name");
                String mobile = jsonUser.getString("mobile");
                String user_status = jsonUser.getString("status");
                String api_token = jsonUser.getString("api_token");
                String address = jsonUser.getString("address");


                User user = new User(id, name, mobile, user_status, api_token, address);
                Log.e("FR:: RR:: ", user.getId() + "\nName: " + user.getName() + "\nAddress: " + user.getAddress() + "\nMobile: " + user.getMobile() + "\nAPI_TOKEN: " + user.getApi_token() + "\nStatus: " + user.getUser_status());

                callBack.action(1, user);
                return;
            }
            else {
                callBack.action(0, null);
                return;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        callBack.action(0, null);
    }
}
