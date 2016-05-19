package com.raivis.develops.lpd_personigais_budzeta_planotais;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class IncomeAddActivity extends AppCompatActivity {

    //DB
    DBHandler db;

    //SharedPreferences to get active user
    SharedPreferences preferences;

    //Input fields
    EditText incomeName;
    EditText incomeAmount;
    EditText incomeDate;
    EditText incomeSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_add);

        db = new DBHandler(this);

        preferences = getSharedPreferences("userSeason",
                Context.MODE_PRIVATE);

        //casting input fields
        incomeName = (EditText)findViewById(R.id.incomeNameInput);
        incomeAmount = (EditText)findViewById(R.id.incomeCapacityInput);
        incomeDate = (EditText)findViewById(R.id.incomeDateInput);
        incomeSource = (EditText)findViewById(R.id.incomeSourceInput);
    }

    public void pressOnIncomeAdd(View view) {
//        TODO Check if user have fill all fields

        boolean result = db.addNewIncome(new Income(incomeName.getText().toString(),
                Double.parseDouble(incomeAmount.getText().toString()),
                incomeDate.getText().toString(), incomeSource.getText().toString(),
                preferences.getString("userEmail","")));

        if(result){
            Toast.makeText(this, R.string.income_sucess, Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this, R.string.income_not_sucess, Toast.LENGTH_LONG).show();
        }

        incomeName.setText("");
        incomeAmount.setText("");
        incomeDate.setText("");
        incomeSource.setText("");
    }
}
