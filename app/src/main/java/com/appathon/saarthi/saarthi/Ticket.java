package com.appathon.saarthi.saarthi;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.ImageView;

public class Ticket extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);
        setActivityBackgroundColor(1);
        ImageView qrcode;
        qrcode = (ImageView) findViewById(R.id.imageView1);
        Bitmap bMap = BitmapFactory.decodeResource(getResources(), R.drawable.qrcode);
        qrcode.setImageBitmap(bMap);
        //qrcode.setImageResource(R.drawable.qrcode);
    }

    public void setActivityBackgroundColor(int color) {
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(color);
    }

}
