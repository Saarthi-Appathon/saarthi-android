package com.appathon.saarthi.saarthi;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Button b1;
        final EditText ed1, ed2, ed4, ed3;

        b1 = (Button) findViewById(R.id.button);
        ed1 = (EditText) findViewById(R.id.editText0);
        ed2 = (EditText) findViewById(R.id.editText1);
        ed3 = (EditText) findViewById(R.id.editText2);
        ed4 = (EditText) findViewById(R.id.editText3);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ed1.getText().toString().trim().length() == 0 && ed4.getText().toString().trim().length() == 0 && ed3.getText().toString().trim().length() == 0 && ed2.getText().toString().trim().length() == 0)
                {
                    Toast.makeText(getApplicationContext(), "Please fill all credentials!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    registrationSuccessfull(v);
                }
            }
        });
    }

    public void registrationSuccessfull(View v)
    {
        Intent intent = new Intent (this, RegistrationComplete.class);
        startActivity(intent);
    }

}


