package com.appathon.saarthi.saarthi;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;

public class PaymentActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        Button b1;
        b1 = (Button) findViewById(R.id.button);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
              openTicket(v);
            }
        });

    }

    public void openTicket(View v)
    {
        Intent intent = new Intent(this, Ticket.class);
        startActivity(intent);
    }

}
