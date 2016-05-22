package com.raivis.develops.lpd_personigais_budzeta_planotais;

import android.app.DialogFragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ManualOutcomeActivity extends AppCompatActivity {

    //DB
    DBHandler db;

    //Input fields
    EditText outcomeName;
    EditText outcomeAmount;
    EditText outcomeDate;

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_outcome);

        db = new DBHandler(this);

        preferences = getSharedPreferences("userSeason",
                Context.MODE_PRIVATE);

        //cast input fields
        outcomeName = (EditText)findViewById(R.id.outcomeNameInput);
        outcomeAmount = (EditText)findViewById(R.id.outcomeCapacityInput);
        outcomeDate = (EditText)findViewById(R.id.outcomeDateInput);
        //Readonly
        if(outcomeDate != null)
        {
            outcomeDate.setFocusable(false);
        }
    }

    public void pressOnAddOutcome(View view) {

        Integer[] activityFields = {R.id.outcomeNameInput, R.id.outcomeCapacityInput,
                R.id.outcomeDateInput};
        //If return true, problem with field. :(
        if(FieldValidation.CheckFieldValidation(activityFields, this))
        {
            return;
        }

        boolean result = db.addNewOutcome(new Outcome(outcomeName.getText().toString(),
                Double.parseDouble(outcomeAmount.getText().toString()),
                outcomeDate.getText().toString(), preferences.getString("userEmail","")));

        if(result){
            Toast.makeText(this, "Izdevums pievienots", Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this, "Izdevums nav pievienots", Toast.LENGTH_LONG).show();
        }

        outcomeName.setText("");
        outcomeAmount.setText("");
        outcomeDate.setText("");
    }

    public void pressOnOutcomeDate(View view) {
        DialogFragment newFragment = new DatePickerFragment();

        Bundle args = new Bundle();
        args.putInt("EditTextId", R.id.outcomeDateInput);
        newFragment.setArguments(args);

        newFragment.show(getFragmentManager(), "datePicker");
    }
}
