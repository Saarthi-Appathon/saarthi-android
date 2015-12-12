package com.appathon.saarthi.saarthi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {

    public final static String CURRENT_LATITUDE = "com.example.shoyeb.nearbyplaces.CURRENT_LATITUDE";
    public final static String CURRENT_LONGITUDE = "com.example.shoyeb.nearbyplaces.CURRENT_LONGITUDE";
    public final static String SELECTED_PLACE = "com.example.shoyeb.nearbyplaces.SELECTED_PLACE";
    public final static String SEARCH_RADIUS = "com.example.shoyeb.nearbyplaces.SEARCH_RADIUS";

    GPSTracker gps;
    double latitude;
    double longitude;
    Button b1, b2;
    EditText ed1, ed2;
    TextView tx1, tx2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        b1 = (Button) findViewById(R.id.button);
        b2 = (Button) findViewById(R.id.button2);
        ed1 = (EditText) findViewById(R.id.editText);
        ed2 = (EditText) findViewById(R.id.editText2);
        tx1 = (TextView) findViewById(R.id.textView2);
        tx2 = (TextView) findViewById(R.id.textView6);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ed1.getText().toString().equals("arjun") &&

                        ed2.getText().toString().equals("password")) {
                    Toast.makeText(getApplicationContext(), "Redirecting...", Toast.LENGTH_SHORT).show();
                    openMyLocation(v);
                } else {
                    Toast.makeText(getApplicationContext(), "Wrong Credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ed1.getText().toString().equals("krishna") &&
                        ed2.getText().toString().equals("password")) {
                    Toast.makeText(getApplicationContext(), "Redirecting...", Toast.LENGTH_SHORT).show();
                    openRoutes(v);
                } else {
                    Toast.makeText(getApplicationContext(), "Wrong Credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tx1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                openRegisrationForm(v);
            }
        });

        tx2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* Temporary ticket activity check */
                openTicketWindow(v);
            }
        });
    }

    /**
     * Called when the user clicks the Send button
     */
    public void openMyLocation(View view) {
        getGpsCords();

        Intent intent = new Intent(this, MapsActivity.class);

        intent.putExtra(CURRENT_LATITUDE, latitude);
        intent.putExtra(CURRENT_LONGITUDE, longitude);
        intent.putExtra(SELECTED_PLACE, "bus_station");

        startActivity(intent);
    }


    /*
     * Get Users GPS coordinates
     */
    public void getGpsCords()
    {
        gps = new GPSTracker(LoginActivity.this);

        // check if GPS enabled
        if(gps.canGetLocation())
        {
            latitude = gps.getLatitude();
            longitude = gps.getLongitude();
            //Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
        }
        else
        {
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
        }
    }

    public void openRoutes(View view) {
        // Do something in response to button
    }

    public void openTicketWindow(View view) {

        Intent intent = new Intent(this, BookTicketActivity.class);
        startActivity(intent);
    }

    public void openRegisrationForm(View view) {

        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }
}

//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//
//public class LoginActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//    }
//}
