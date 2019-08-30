package com.example.bdlawservice.auth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bdlawservice.R;
import com.example.bdlawservice.entity.User;
import com.example.bdlawservice.home.HomeActivity;
import com.example.bdlawservice.static_classes.UserHolder;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void login(View view) {
        EditText loginText = findViewById(R.id.login_mobile);
        EditText passwordText = findViewById(R.id.login_password);

        //Toast.makeText(getApplicationContext(), loginText.getText().toString()+"\n"+passwordText.getText().toString(), Toast.LENGTH_SHORT).show();

        LoginPost loginPost = new LoginPost(getApplicationContext(), new VolleyCallBack() {
            @Override
            public void action(int success, User user) {
                if(success==1){
                    UserHolder.setUser(user);
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(getApplicationContext(), "No user found", Toast.LENGTH_SHORT).show();
                }
                super.action(success, user);
            }
        });
        loginPost.sendPostRequest(loginText.getText().toString(), passwordText.getText().toString());
    }
}
