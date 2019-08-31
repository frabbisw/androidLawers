package com.example.bdlawservice.auth;

import android.content.Intent;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.bdlawservice.R;
import com.example.bdlawservice.cache_management.AuthenticationCache;
import com.example.bdlawservice.entity.User;
import com.example.bdlawservice.home.HomeActivity;
import com.example.bdlawservice.static_classes.UserHolder;

public class LoginActivity extends AppCompatActivity {
    ProgressBar progressBar;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressBar = findViewById(R.id.load_bar);
        loginButton = findViewById(R.id.login_button);

        User user = AuthenticationCache.getUserFromCache(getApplicationContext());
        if (user!=null){
            UserHolder.setUser(user);
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void login(View view) {
        progressBar.setVisibility(ContentLoadingProgressBar.VISIBLE);
        loginButton.setEnabled(false);

        EditText loginText = findViewById(R.id.login_mobile);
        EditText passwordText = findViewById(R.id.login_password);

        //Toast.makeText(getApplicationContext(), loginText.getText().toString()+"\n"+passwordText.getText().toString(), Toast.LENGTH_SHORT).show();

        LoginPost loginPost = new LoginPost(getApplicationContext(), new VolleyCallBack() {
            @Override
            public void action(int success, User user) {
                if(success==1){
                    UserHolder.setUser(user);
                    AuthenticationCache.setUserToCache(getApplicationContext(),user);
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                    progressBar.setVisibility(ContentLoadingProgressBar.GONE);
                    loginButton.setEnabled(true);
                    finish();
                }
                else {
                    progressBar.setVisibility(ContentLoadingProgressBar.GONE);
                    loginButton.setEnabled(true);
                    Toast.makeText(getApplicationContext(), "No user found", Toast.LENGTH_SHORT).show();
                }
                super.action(success, user);
            }
        });
        loginPost.sendPostRequest(loginText.getText().toString(), passwordText.getText().toString());
    }
}
