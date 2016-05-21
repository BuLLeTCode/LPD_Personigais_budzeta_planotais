package com.raivis.develops.lpd_personigais_budzeta_planotais;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static java.lang.String.format;

/**
 * Created by raivis on 21/05/2016.
 */
public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener{

    EditText birthDay;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use current date as default value for Dialog
        final Calendar c = Calendar.getInstance();

        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        //
        birthDay = (EditText)getActivity().findViewById(R.id.birthDateInput);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        //What will happen, when user set date on DatePickerDialog
        String date = Integer.toString(dayOfMonth) + "." + Integer.toString(monthOfYear + 1) +
                "." + Integer.toString(year);//Store this in string, so IDE doesnt
        birthDay.setText(date);
    }
}
