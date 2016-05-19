package com.raivis.develops.lpd_personigais_budzeta_planotais;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class ViewMoneyFlowActivity extends AppCompatActivity {

    //Dropdown imitation - Spinner :)
    Spinner dropdown;
    //Display all elements in ListView - i think.
    ListView listView;
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

        db = new DBHandler(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
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
    }

    private void displaySelectedOption(String option)
    {
        String[] displayInformation = {};
        int activeIndex = 0;
        double totalSum = 0;

        switch (option)
        {
            case "Izdevumi":
//                TODO make same function in DBHandler what return result...
                List<Outcome> outcomes = db.getAllOutcomes();
                displayInformation = new String[outcomes.size()+1];


                for(Outcome out : outcomes){
                    String[]activeOut = out.getOutcomeInfo();
                    totalSum += Double.parseDouble(activeOut[1]);
                    displayInformation[activeIndex] = activeOut[0] + " " + activeOut[1] + " €";
                    ++activeIndex;
                }

                //Display total sum at end
                displayInformation[activeIndex] = "Kopā izdevumi: " + totalSum + " €";

                if(outcomes.size() == 0)
                {
                    displayInformation = new String[]{"Nav pievienots neviens izdevums"};
                }

                break;
            case "Ienākumi":
                List<Income> incomes = db.getAllIncomes();
                displayInformation = new String[incomes.size()+1];

                for(Income in : incomes){
                    String[]activeOut = in.getIncomeInfo();
                    totalSum += Double.parseDouble(activeOut[1]);
                    displayInformation[activeIndex] = activeOut[0] + " " + activeOut[1] + " €";
                    ++activeIndex;
                }

                //Display total sum at end
                displayInformation[activeIndex] = "Kopā ienākumi: " + totalSum + " €";

                if(incomes.size() == 0)
                {
                    displayInformation = new String[]{"Nav pievienots neviens ienākums"};
                }

                break;
        }

        final ListAdapter theAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_expandable_list_item_1,displayInformation);

        listView.setAdapter(theAdapter);
    }
}
