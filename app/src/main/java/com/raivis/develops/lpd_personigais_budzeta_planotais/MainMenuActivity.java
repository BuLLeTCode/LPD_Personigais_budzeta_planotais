package com.raivis.develops.lpd_personigais_budzeta_planotais;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    public void pressOnLookMoneyFlow(View view) {
        startActivity(
                IntentGenerator.returnNewIntentForActivity(this,
                        ViewMoneyFlowActivity.class));
    }

    public void pressOnAddMoneyFlow(View view) {
        startActivity(IntentGenerator.returnNewIntentForActivity(this,
                MoneyFlowActivity.class));
    }

    public void pressOnViewBudgetReview(View view) {
        startActivity(IntentGenerator.returnNewIntentForActivity(this,
                BudgetReviewActivity.class));
    }

    public void pressOnLogOut(View view) {
        SharedPreferences sharedpreferences = sharedpreferences = getSharedPreferences("userSeason",
                Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedpreferences.edit();

        editor.putString("userEmail", "");

        editor.apply();

        startActivity(IntentGenerator.returnNewIntentForActivity(this,
                LoginActivity.class));
    }
}
