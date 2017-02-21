package ca.sfu.epsilon.bomblocator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static ca.sfu.epsilon.bomblocator.R.id.btnLaunchGame;

public class SplashScreen extends AppCompatActivity {

    final int REQUEST_CODE_NEWGAME = 666;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        setupStartButton();
    }

    private void setupStartButton() {
        Button startAddPot = (Button) findViewById(btnLaunchGame);
            startAddPot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent mainMenuIntent = MainMenu.makeIntent(SplashScreen.this);
                    startActivityForResult(mainMenuIntent, REQUEST_CODE_NEWGAME);
                }
            });
    }

}
