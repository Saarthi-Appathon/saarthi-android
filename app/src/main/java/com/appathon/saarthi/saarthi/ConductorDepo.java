package com.appathon.saarthi.saarthi;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ConductorDepo extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_conductor_depo);
        retrieveDepoList();
        Spinner sItems = (Spinner) findViewById(R.id.depo_names);
        if (sItems != null) {
            sItems.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String routeFor = ((TextView) view).getText().toString();
                    fillRouteList(retrieveRouteList(routeFor));
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
        Button b1 = (Button) findViewById(R.id.select_route);
        if (b1 != null) {
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startTasksActivity();
                }
            });
        }
    }


    protected void startTasksActivity() {
        ListView sItems = (ListView) findViewById(R.id.routeList);
        int item = sItems.getCheckedItemPosition();
        if (item == AdapterView.INVALID_POSITION)
            return;
        String routeFor = (String)sItems.getItemAtPosition(item);
        if (routeFor != null) {
            Intent intent = new Intent(ConductorDepo.this, ConductorTasks.class);

            Bundle b = new Bundle();
            b.putString("route", routeFor); //Your id
            intent.putExtras(b); //Put your id to your next Intent
            startActivity(intent);
            finish();
        }
    }

    protected void retrieveDepoList() {
        List<String> spinnerArray =  new ArrayList<String>();
        spinnerArray.add("Kothrud");
        spinnerArray.add("Wakad");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItems = (Spinner) findViewById(R.id.depo_names);
        if (sItems != null)
            sItems.setAdapter(adapter);
    }

    private void fillRouteList(List<String> listOfRoutes) {
        // Getting object reference to listview of main.xml
        ListView listView = (ListView) findViewById(R.id.routeList);

        // Instantiating array adapter to populate the listView
        // The layout android.R.layout.simple_list_item_single_choice creates radio button for each listview item
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_single_choice, listOfRoutes);
        listView.setAdapter(adapter);
    }

    private List<String> retrieveRouteList(String route) {
        List<String> spinnerArray =  new ArrayList<String>();
        if (route.equals("Wakad")) {
            spinnerArray.add("Bus 203: Wakad Police Station - Annabhau Sathenagar - Archana Restaurant");
            spinnerArray.add("Bus 103: Wakad Police Station - B.u.bhandari - Hinjewadi Jakatnaka");
        } else {
            spinnerArray.add("Bus 99: Kothrud Depot - Bharatinagar - Kachra Depot - Gurujan Society - Shashtrinagar");
            spinnerArray.add("Bus 100: Kothrud Depot - Bharatinagar - Kachra Depot - Vanaz Corner - Jai Bhavani Nagar");
        }
        return spinnerArray;
    }

}
