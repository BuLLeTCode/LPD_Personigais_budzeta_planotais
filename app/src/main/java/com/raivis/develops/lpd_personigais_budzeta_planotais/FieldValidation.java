package com.raivis.develops.lpd_personigais_budzeta_planotais;

import android.app.Activity;
import android.text.TextUtils;
import android.widget.EditText;

/*
 * Created by raivis on 22/05/2016.
 */
public class FieldValidation {

    public static boolean CheckFieldValidation(Integer[] fields, Activity activity)
    {
        for (Integer fieldID: fields) {
            EditText fieldText = (EditText) activity.findViewById(fieldID);

            if(TextUtils.isEmpty(fieldText.getText().toString()))
            {
                fieldText.setError(activity.getString(R.string.field_is_empty));
                return true;
            }
        }

        return false;
    }
}
