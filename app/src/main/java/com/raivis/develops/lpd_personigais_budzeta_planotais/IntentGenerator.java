package com.raivis.develops.lpd_personigais_budzeta_planotais;

import android.content.Context;
import android.content.Intent;

/**
 * Created by raivis on 17/05/2016.
 */
public class IntentGenerator {
    public static Intent returnNewIntentForActivity(Context activityContext, Class activityClass)
    {
        Intent newIntent = new Intent(activityContext, activityClass);

        return newIntent;
    }
}
