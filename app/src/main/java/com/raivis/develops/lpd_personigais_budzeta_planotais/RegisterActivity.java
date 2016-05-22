package com.raivis.develops.lpd_personigais_budzeta_planotais;

import android.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import android.app.DatePickerDialog;

import java.text.DateFormat;
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
        //Good way to make readonly fields
        if(newUserBirthday != null){
            newUserBirthday.setFocusable(false);
        }

        newUserEmail = (EditText)findViewById(R.id.emailInput);
        newUserPassword = (EditText)findViewById(R.id.passwordInput);
    }

    public void pressOnConfirmRegistration(View view) {

        //Checking if all fieds are fill - maybe only one error message?
        Integer[] activityFields = {R.id.nameInput, R.id.surnameInput, R.id.birthDateInput,
        R.id.emailInput, R.id.passwordInput};
        //If return true, problem with field. :(
        if(FieldValidation.CheckFieldValidation(activityFields, this))
        {
            return;
        }

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

        Bundle args = new Bundle();
        args.putInt("EditTextId", R.id.birthDateInput);
        newFragment.setArguments(args);

        newFragment.show(getFragmentManager(), "datePicker");
    }
}
