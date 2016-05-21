package com.raivis.develops.lpd_personigais_budzeta_planotais;

import android.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import android.app.DatePickerDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class RegisterActivity extends AppCompatActivity {

    //For DB
    DBHandler db;
    //User info fields
    EditText newUserFirstName;
    EditText newUserSurname;
    EditText newUserBirthday;
    EditText newUserEmail;
    EditText newUserPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DBHandler(this);

        //Casting input fields
        newUserFirstName = (EditText)findViewById(R.id.nameInput);
        newUserSurname = (EditText)findViewById(R.id.surnameInput);
//        TODO for birthday need DatePickerDialog!
        newUserBirthday = (EditText)findViewById(R.id.birthDateInput);
        if(newUserBirthday != null){
            newUserBirthday.setFocusable(false);//Good way to make readonly fields

        }

        newUserEmail = (EditText)findViewById(R.id.emailInput);
        newUserPassword = (EditText)findViewById(R.id.passwordInput);
    }

    public void pressOnConfirmRegistration(View view) {
//        TODO Check if user have fill all fields
        boolean result = db.addNewUser(new User(newUserFirstName.getText().toString(),
                newUserSurname.getText().toString(),
                newUserBirthday.getText().toString(),
                newUserEmail.getText().toString(),
                newUserPassword.getText().toString()));

        if(result){
            Toast.makeText(this, "Lietotājs pievienots", Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this, "Lietotājs nav pievienots", Toast.LENGTH_LONG).show();
        }

        newUserFirstName.setText("");
        newUserSurname.setText("");
        newUserBirthday.setText("");
        newUserEmail.setText("");
        newUserPassword.setText("");
    }

    public void pressOnDatePicker(View view) {
        //Show Date picker fragment
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
    }
}
