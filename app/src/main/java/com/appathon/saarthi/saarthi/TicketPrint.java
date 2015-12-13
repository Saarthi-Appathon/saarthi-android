package com.appathon.saarthi.saarthi;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class TicketPrint extends Activity {
    static int ticket_no = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_print);
        Bundle b = getIntent().getExtras();
        String passenger_count = b.getString("passenger_count");
        String destination = b.getString("destination");
        TextView ticketNo = (TextView) findViewById(R.id.ticket_no);
        TextView ticketDetials = (TextView) findViewById(R.id.ticket_details);
        ticket_no++;
        ticketNo.setText("Ticket #" + Integer.toString(ticket_no));
        int money = 1;
        if (passenger_count.length() != 0)
            money = Integer.parseInt(passenger_count) * 250;
        else
            passenger_count = "1";
        ticketDetials.setText(
        "Passenger Count: " + passenger_count + "\n" +
                "Fare: " + "\u20B9 250 x " + passenger_count + " = \u20B9 " + Integer.toString(money) + "\n" +
        "Destination: " + destination);
    }
}
