package com.raivis.develops.lpd_personigais_budzeta_planotais;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class BudgetReviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_review);
    }

    public void pressOnMoneyVisualizationButton(View view) {
        startActivity(IntentGenerator.returnNewIntentForActivity(this,
                VisualitationActivity.class));
    }
}
