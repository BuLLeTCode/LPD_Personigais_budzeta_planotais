package com.raivis.develops.lpd_personigais_budzeta_planotais;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class OutcomeTypesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outcome_types);
    }

    public void pressOnCheck(View view) {
//        TODO nākotnē!
    }

    public void pressOnBill(View view) {
//        TODO nākotnē!
    }

    public void pressOnManual(View view) {
        startActivity(IntentGenerator.returnNewIntentForActivity(this,
                IncomeAddActivity.class));
    }
}
