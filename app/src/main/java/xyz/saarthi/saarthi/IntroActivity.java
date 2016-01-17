package xyz.saarthi.saarthi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.github.paolorotolo.appintro.AppIntro2;

public class IntroActivity extends AppIntro2 {
    @Override
    public void init(Bundle savedInstanceState) {

        addSlide(UtilityIntroSlideTemplate.newInstance(R.layout.slide_intro_one));
        addSlide(UtilityIntroSlideTemplate.newInstance(R.layout.slide_intro_two));
        addSlide(UtilityIntroSlideTemplate.newInstance(R.layout.slide_intro_three));
        addSlide(UtilityIntroSlideTemplate.newInstance(R.layout.slide_intro_four));

    }

    private void loadMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onDonePressed() {
        loadMainActivity();
    }

    @Override
    public void onNextPressed() {

    }

    @Override
    public void onSlideChanged() {

    }

    public void getStarted(View v) {
        loadMainActivity();
    }
}