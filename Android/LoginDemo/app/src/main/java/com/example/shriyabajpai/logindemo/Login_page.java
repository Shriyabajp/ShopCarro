package com.example.shriyabajpai.logindemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login_page extends AppCompatActivity {
    private EditText etname;
    private EditText ptpassword;
    private Button btlogin;
    private IUser iUser;
    String email;
    String password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iUser = AppController.getInstance().getClient().create(IUser.class);
        setupUIviews();
        btlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    User user = new User(email, password);

                    login(user);
                }
                else {
                    Toast.makeText(Login_page.this,"validation_failed",Toast.LENGTH_SHORT).show();

                }

            }
        });


    }

    private void login(User user) {
        Call<Boolean> call = iUser.doLogin(user);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.body()) {
                    Intent intent = new Intent(Login_page.this, Homepage.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(Login_page.this, "SUCCESS", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Toast.makeText(Login_page.this, "FAILURE", Toast.LENGTH_SHORT).show();

            }
        });


    }

    private void setupUIviews() {
        etname = (EditText) findViewById(R.id.etName);
        ptpassword = (EditText) findViewById(R.id.etPassword);

        btlogin = (Button) findViewById(R.id.btnLogin);

    }

    private boolean validate() {

        email = this.etname.getText().toString();
        password = this.ptpassword.getText().toString();
        if (email.isEmpty() || password.isEmpty()) {
            etname.setError("you have not entered anything");
            ptpassword.setError("you have not entered anything");
            return false;
        }
        return isValidEmail(email) && isValidPassword(password);


    }

    public boolean isValidEmail(CharSequence email) {
        if (email == null) {
            return false;
        }
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public boolean isValidPassword(String password) {

        if (password.length() < 8 || password == null) {
            return false;
        }
        return true;
    }
}


