package ca.sfu.epsilon.bomblocator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static ca.sfu.epsilon.bomblocator.R.id.btnLaunchGame;

public class SplashScreen extends AppCompatActivity {

    private static final String SHAREDPREF_SET = "BombLocator";
    private static final int SHAREDPREF_ITEM_HIGHSCORE = 100;
    private static final int SHAREDPREF_ITEM_GRIDSIZE = 24;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        setupStartButton();
    }

    private void setupStartButton() {
        Button startMenu = (Button) findViewById(btnLaunchGame);
            startMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent mainMenuIntent = MainMenu.makeIntent(SplashScreen.this);
                    startActivity(mainMenuIntent);
                }
            });
    }

    //SharedPreferences preferences = getSharedPreferences(SHAREDPREF_SET, MODE_PRIVATE);

}
