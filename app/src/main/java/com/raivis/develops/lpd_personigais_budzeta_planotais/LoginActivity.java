package com.raivis.develops.lpd_personigais_budzeta_planotais;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    //For login need input from login fields
    private EditText userMail;
    private EditText userPass;
    private Button loginButton;
    //Another variables
    private int userLoginTrysLeft = 3;//After three trys disable button


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userMail = (EditText) findViewById(R.id.userEmail);
        userPass = (EditText) findViewById(R.id.userPassword);
        loginButton = (Button) findViewById(R.id.loginButton);
    }

    //User click on register - call register activity
    public void pressOnRegister(View view) {
        Intent callRegister = new Intent(this, RegisterActivity.class);
        startActivity(callRegister);
    }

    //User try to login
    public void pressOnLogin(View view) {
        //TODO make with db - for now hardcode login! :D
        if(userMail.getText().toString().equals("user@user.lv") &&
                userPass.getText().toString().equals("pass")){
            Intent callMainMenu = new Intent(this, MainMenuActivity.class);
            callMainMenu.putExtra("userEmail", userMail.getText().toString());//To get User name
                                                                              //after login
            startActivity(callMainMenu);
        }
        else
        {
            userLoginTrysLeft -= 1;

            if(userLoginTrysLeft == 0)
            {
                loginButton.setEnabled(false);//Disable button :D
                loginButton.setBackgroundColor(Color.parseColor("#f0f0f5"));
            }
            else
            {
                //Little toast to show login is not successfull.
                Toast.makeText(this, "Ielogošanās neveiksmīga. Atlikušie mēģinājumi: " +
                userLoginTrysLeft, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
