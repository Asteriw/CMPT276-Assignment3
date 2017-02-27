package ca.sfu.epsilon.bomblocator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import static ca.sfu.epsilon.bomblocator.R.id.btnLaunchGame;
import static ca.sfu.epsilon.bomblocator.R.id.splashbackground;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        setupStartButton();
        setupAnimations();
    }

    private void setupAnimations() {
        ImageView bomb = (ImageView) findViewById(R.id.splashbomb1);
        ImageView bomb2 = (ImageView) findViewById(R.id.splashbomb2);
        ImageView bomb3 = (ImageView) findViewById(R.id.splashbomb3);
        ImageView bomb4 = (ImageView) findViewById(R.id.splashbomb4);
        TranslateAnimation slide1 = new TranslateAnimation(-100, -100, -400f, 900f);
        TranslateAnimation slide2 = new TranslateAnimation(0, 0, -700f, 900f);
        TranslateAnimation slide3 = new TranslateAnimation(0, 0, -500f, 900f);
        TranslateAnimation slide4 = new TranslateAnimation(75, 75, -800f, 900f);
        slide1.setInterpolator(new LinearInterpolator());
        slide1.setDuration(3500);
        slide1.setRepeatCount(Animation.INFINITE);
        slide2.setInterpolator(new LinearInterpolator());
        slide2.setDuration(2750);
        slide2.setRepeatCount(Animation.INFINITE);
        slide3.setInterpolator(new LinearInterpolator());
        slide3.setDuration(3250);
        slide3.setRepeatCount(Animation.INFINITE);
        slide4.setInterpolator(new LinearInterpolator());
        slide4.setDuration(3750);
        slide4.setRepeatCount(Animation.INFINITE);
        bomb.startAnimation(slide1);
        bomb2.startAnimation(slide2);
        bomb3.startAnimation(slide3);
        bomb4.startAnimation(slide4);
    }

    private void setupStartButton() {
        Button startMenu = (Button) findViewById(btnLaunchGame);
            startMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent mainMenuIntent = MainMenu.makeIntent(SplashScreen.this);
                    startActivity(mainMenuIntent);
                    SplashScreen.this.finish();
                }
            });
    }
}
