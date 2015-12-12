package com.appathon.saarthi.saarthi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MyWalletActivity extends Activity {
    //reset progress bar status
    int progressBarStatus = 0;
    long progress = 0;
    ProgressDialog progressBar;
    private Handler progressBarHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_wallet);
        Button b1, b2;
        b1 = (Button) findViewById(R.id.button1);
        b2 = (Button) findViewById(R.id.button2);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paymentSuccessfull(v);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "Redirecting...", Toast.LENGTH_SHORT).show();
                openRechargeWindow(v);
            }
        });
    }

    public void paymentSuccessfull(View v) {

        progressBar = new ProgressDialog(v.getContext());
        progressBar.setCancelable(false);
        progressBar.setMessage("Redirecting ...");
        progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressBar.setProgress(0);
        progressBar.setMax(100);
        progressBar.show();

        //reset progress bar status
        progressBarStatus = 0;
        //reset filesize
        progress = 0;

        new Thread(new Runnable() {
            public void run() {
                while (progressBarStatus < 100) {

                    // process some tasks
                    progressBarStatus = incrementProgress();
                    try {
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    // Update the progress bar
                    progressBarHandler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progressBarStatus);
                        }
                    });
                }

                // ok, file is downloaded,
                if (progressBarStatus >= 100) {

                    // sleep 2 seconds, so that you can see the 100%
                    try {
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // close the progress bar dialog
                    progressBar.dismiss();
                }
            }
        }).start();

        Intent intent = new Intent(this, PaymentActivity.class);
        startActivity(intent);
    }


    public void openRechargeWindow(View view)
    {

        Toast.makeText(getApplicationContext(), "Redirecting..", Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent(this, RechargeActivity.class);
//        startActivity(intent);
    }

    public int incrementProgress() {
        while (progress <= 1000000) {
            progress++;
            if (progress == 100000) {
                return 10;
            } else if (progress == 200000) {
                return 20;
            } else if (progress == 300000) {
                return 30;
            }
        }
        return 100;
    }

}
