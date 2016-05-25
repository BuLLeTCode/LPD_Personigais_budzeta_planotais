package com.raivis.develops.lpd_personigais_budzeta_planotais;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MoneyFlowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_flow);
    }

    public void pressOnOutcome(View view) {
        startActivity(IntentGenerator.returnNewIntentForActivity(this, OutcomeTypesActivity.class));
    }

    public void pressOnIncome(View view) {
        startActivity(IntentGenerator.returnNewIntentForActivity(this, IncomeAddActivity.class));
    }
}
