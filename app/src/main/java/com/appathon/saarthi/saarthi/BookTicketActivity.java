package com.appathon.saarthi.saarthi;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import java.util.ArrayList;
import java.util.List;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class BookTicketActivity extends Activity implements AdapterView.OnItemSelectedListener
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_ticket);

        // Spinner element
        Spinner sp1, sp2;
        Button b1;

        sp1 = (Spinner) findViewById(R.id.spinner1);
        sp2 = (Spinner) findViewById(R.id.spinner2);
        b1 = (Button) findViewById(R.id.button);

        // Spinner click listener
        sp1.setOnItemSelectedListener(this);
        sp2.setOnItemSelectedListener(this);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (true)
                {
                    openMyWallet(v);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Invalid Selection", Toast.LENGTH_SHORT).show();
                }
            }
        });
        // Spinner Drop down elements
//        List<String> categories = new ArrayList<String>();
//        categories.add("Automobile");
//        categories.add("Business Services");
//        categories.add("Computers");
//        categories.add("Education");
//        categories.add("Personal");
//        categories.add("Travel");
//
//        // Creating adapter for spinner
//        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
//
//        // Drop down layout style - list view with radio button
//        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

//        // attaching data adapter to spinner
//        sp1.setAdapter(dataAdapter);
//        sp2.setAdapter(dataAdapter);
    }


    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
    }
    public void onNothingSelected(AdapterView<?> arg0)
    {
        // TODO Auto-generated method stub
    }

    public void openMyWallet (View v)
    {
        Intent intent = new Intent(this, MyWalletActivity.class);
        startActivity(intent);
    }

}



