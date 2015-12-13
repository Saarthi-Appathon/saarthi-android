package com.appathon.saarthi.saarthi;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IssueTicket extends Activity {

    private ProgressDialog mProgressDialog;

    protected void processTicketIssue() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setIndeterminate(false);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        class ProgressTask extends AsyncTask<Long, Void, Void> {
            @Override
            protected void onPreExecute() {
                mProgressDialog.show();
            }

            @Override
            protected Void doInBackground(Long... arg0) {
                //my stuff is here
                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                mProgressDialog.dismiss();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue_ticket);
        Bundle b = getIntent().getExtras();
        String route = b.getString("route");
        String lastStop = b.getString("last_stop");
        final String hasBusDeparted = b.getString("departed");
        List<String> routeList = getStops(route);
        filterStopList(routeList, hasBusDeparted == null || hasBusDeparted.length() == 0 ? null : lastStop);
        Button showTiming = (Button) findViewById(R.id.issueticket);
        showTiming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String routeId = getSelectedRoute();
                //start
                issueTicket();
            }
        });

        EditText passenger_count = (EditText) findViewById(R.id.passenger_count);
        passenger_count.setText("1");

        showTiming.setEnabled(false);

        Spinner spinner = (Spinner) findViewById(R.id.enter_dest);
        if (hasBusDeparted != null && hasBusDeparted.length() > 0) {
            Spinner timingspin = (Spinner) findViewById(R.id.timing);
            timingspin.setEnabled(false);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                String departedTime = hasBusDeparted;
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    CheckedTextView spin = (CheckedTextView)view;
                    String routeName = (String)spin.getText();
                    findAvailability(departedTime, routeName);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        } else {
            Spinner timingspin = (Spinner) findViewById(R.id.timing);
            timingspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    CheckedTextView spin = (CheckedTextView)view;
                    String timeStamp = (String)spin.getText();
                    Spinner dest = (Spinner) findViewById(R.id.enter_dest);
                    findAvailability((String)dest.getSelectedItem(), timeStamp);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    CheckedTextView spin = (CheckedTextView)view;
                    String routeName = (String)spin.getText();
                    populateTiming(routeName);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }

    }

    private void findAvailability(String departedTime, String routeName) {
        TextView timingspin = (TextView) findViewById(R.id.availability);
        timingspin.setText("Availability: Yes");
        Button showTiming = (Button) findViewById(R.id.issueticket);
        showTiming.setEnabled(true);
    }

    private void populateTiming(String route) {
        Spinner timingspin = (Spinner) findViewById(R.id.timing);
        List<String> l = getTimings(route);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_single_choice, l);
        timingspin.setAdapter(adapter);
    }

    private void issueTicket() {
        EditText passenger_count = (EditText) findViewById(R.id.passenger_count);
        Intent intent = new Intent(this, TicketPrint.class);
        Spinner spinner = (Spinner) findViewById(R.id.enter_dest);
        Bundle b = new Bundle();

        if (passenger_count.getText().length() == 0)
            return;

        b.putString("passenger_count", passenger_count.getText().toString());
        b.putString("destination", spinner.getSelectedItem().toString());
        intent.putExtras(b); //Put your id to your next Intent
        startActivity(intent);
        finish();
    }

    private List<String> getStops(String route) {
        int index = route.indexOf(':');
        List<String> l = new ArrayList<String>(Arrays.asList(route.substring(index+1).split("-")));
        return l;
    }

    private List<String> getTimings(String route) {
        List<String> l = new ArrayList<String>();
        l.add("5:50am");
        l.add("10:30am");
        l.add("12:30am");
        return l;
    }

    private void filterStopList(List<String> routeList, String st) {
        int i = 0;
        if (st != null) {
            for (; i < routeList.size(); i++) {
                String stop = routeList.get(i);
                if (stop.equals(st)) {
                    break;
                }
            }
        }
        List<String> l = new ArrayList<String>();
        Spinner spinner = (Spinner) findViewById(R.id.enter_dest);
        for (; i < routeList.size(); i++) {
            l.add(routeList.get(i));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_single_choice, l);
        spinner.setAdapter(adapter);
    }
}
