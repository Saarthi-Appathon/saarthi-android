package xyz.saarthi.saarthi;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;

public class SplashScreenActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                /* Start of IntroActivity */
                //  Declare a new thread to do a preference check
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //  Initialize SharedPreferences
                        SharedPreferences getPrefs = PreferenceManager
                                .getDefaultSharedPreferences(getBaseContext());

                        //  Create a new boolean and preference and set it to true
                        boolean isFirstStart = getPrefs.getBoolean("firstStart", true);

                        //  If the activity has never started before...
                        if (isFirstStart) {

                            //  Launch app intro
                            Intent i = new Intent(SplashScreenActivity.this, IntroActivity.class);
                            startActivity(i);

                            //  Make a new preferences editor
                            SharedPreferences.Editor e = getPrefs.edit();

                            //  Edit preference to make it false because we don't want this to run again
                            e.putBoolean("firstStart", false);

                            //  Apply changes
                            e.apply();

                            finish();
                        }
                    }
                });
                // Start the thread
                t.start();
                /* End of IntroActivity */

                /*Main Activity*/
                Intent i = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(i);

                finish();
            }
        },1800);
    }
}
