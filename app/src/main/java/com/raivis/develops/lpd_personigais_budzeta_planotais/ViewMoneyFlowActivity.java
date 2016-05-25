package com.raivis.develops.lpd_personigais_budzeta_planotais;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

public class ViewMoneyFlowActivity extends AppCompatActivity {

    //Dropdown imitation - Spinner :)
    Spinner dropdown;
    //Display all elements in ListView - i think.
    ListView listView;
    TableLayout layout;
    //Options for dropdown
    String[] options = new String[]{"", "Izdevumi", "Ienākumi"};
    //Also work with DB
    DBHandler db;//TODO make this static

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_money_flow);

        dropdown = (Spinner)findViewById(R.id.displayOptionsSpinner);
        listView = (ListView)findViewById(R.id.moneyFlowListView);
        layout = (TableLayout)findViewById(R.id.layoutXML);

        db = new DBHandler(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, options);

        dropdown.setAdapter(adapter);
        //To display items according to selected option
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                displaySelectedOption(dropdown.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

//        TableLayout tableLayout = new TableLayout(this);
//        tableLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_BEGINNING);
//        tableLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
//        tableLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_END);
//        tableLayout.setShrinkAllColumns(true);
//        tableLayout.setStretchAllColumns(true);


        /*
        TableRow titleRow2 = new TableRow(this);

        TextView titler1 = new TextView(this);
        titler1.setText("Divi");

        titleRow2.addView(titler1);

        layout.addView(titleRow2);
            */
        //setContentView(tableLayout);
    }

    private void displaySelectedOption(String option)
    {
//        String[] displayInformation = {};
//        int activeIndex = 0;
        double totalSum = 0;

        RemoveNewTableRows();
        AddNewTableRow(getString(R.string.nosaukums), getString(R.string.apjoms),
                getString(R.string.datums));//Cheating. :D

        switch (option)
        {
            case "Izdevumi":
//                TODO make same function in DBHandler what return result...
                List<Outcome> outcomes = db.getAllOutcomes();
//                displayInformation = new String[outcomes.size()+1];

                for(Outcome out : outcomes){
                    /*
                    String[]activeOut = out.getOutcomeInfo();
                    totalSum += Double.parseDouble(activeOut[1]);
                    displayInformation[activeIndex] = activeOut[0] + "  " + activeOut[1] + " €" +
                    " " + activeOut[2];
                    */
                    String[]activeOut = out.getOutcomeInfo();
                    totalSum += Double.parseDouble(activeOut[1]);

                    AddNewTableRow(activeOut[0], activeOut[1], activeOut[2]);

//                    ++activeIndex;
                }

                if(outcomes.size() == 0)
                {
//                    displayInformation = new String[]{"Nav pievienots neviens izdevums"};
                    AddNewTableRow(getString(R.string.nav_izdevumu), "", "");
                }
                else
                {
                    //Display total sum at end
                    //displayInformation[activeIndex] = "Kopā izdevumi: " + totalSum + " €";
                    AddNewTableRow("", String.format(Locale.ENGLISH, "%.2f",
                            totalSum), "");//String.format is better solution
                }

                break;
            case "Ienākumi":
                List<Income> incomes = db.getAllIncomes();
//                displayInformation = new String[incomes.size()+1];

                for(Income in : incomes){
                    String[]activeOut = in.getIncomeInfo();
                    totalSum += Double.parseDouble(activeOut[1]);
//                    displayInformation[activeIndex] = activeOut[0] + " " + activeOut[1] + " €";

                    AddNewTableRow(activeOut[0], activeOut[1], activeOut[2]);

//                    ++activeIndex;
                }

                if(incomes.size() == 0)
                {
//                    displayInformation = new String[]{"Nav pievienots neviens ienākums"};
                    AddNewTableRow(getString(R.string.nav_ienakumu), "", "");
                }
                else
                {
                    //Display total sum at end
//                displayInformation[activeIndex] = "Kopā ienākumi: " + totalSum + " €";
                    AddNewTableRow("", String.format(Locale.ENGLISH, "%.2f",
                            totalSum), "");//String.format is better solution
                }

                break;
        }
        /*
        final ListAdapter theAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_expandable_list_item_1,displayInformation);

        listView.setAdapter(theAdapter);
        */
    }

    private void AddNewTableRow(String firstColumn, String secondColumn, String thirdColumn)
    {
        TableRow titleRow = new TableRow(this);

        TextView title1 = new TextView(this);
        title1.setText(firstColumn);

        TextView title2 = new TextView(this);

        if(!secondColumn.equals(""))
        {
            title2.setText(secondColumn + " €");

        }

        if(firstColumn.equals("") && thirdColumn.equals(""))
        {
            title2.setTextColor(Color.RED);
        }

        TextView title3 = new TextView(this);
        title3.setText(thirdColumn);
//        TODO think about Layout...
        TableRow.LayoutParams params1 = new TableRow.LayoutParams();
        params1.rightMargin = 30;
        params1.gravity = Gravity.LEFT;

        TableRow.LayoutParams params2 = new TableRow.LayoutParams();
        params2.leftMargin =  30;
        params2.rightMargin = 30;
        params2.gravity = Gravity.CENTER;

        TableRow.LayoutParams params3 = new TableRow.LayoutParams();
        params3.leftMargin = 30;
        params3.gravity = Gravity.RIGHT;

        titleRow.addView(title1, params1);
        titleRow.addView(title2, params2);
        titleRow.addView(title3, params3);

        layout.addView(titleRow);
    }

    private void RemoveNewTableRows()
    {
        //i == 1 - because no need to delete table title.
//        for(int i = 1; i<layout.getChildCount(); i++)
//        {
//            layout.removeViewAt(i);
//        }
        layout.removeAllViews();
    }
}
