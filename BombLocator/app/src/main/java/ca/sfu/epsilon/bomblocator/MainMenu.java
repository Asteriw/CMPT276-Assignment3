package ca.sfu.epsilon.bomblocator;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import static ca.sfu.epsilon.bomblocator.R.id.btnAbout;
import static ca.sfu.epsilon.bomblocator.R.id.btnLaunchGame;
import static ca.sfu.epsilon.bomblocator.R.id.btnSettings;
import static ca.sfu.epsilon.bomblocator.R.id.btnStartGame;

public class MainMenu extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        setupButtons();
    }

    private void setupButtons() {
        Button startGame = (Button) findViewById(btnStartGame);
        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newGameIntent = GameScreen.makeIntent(MainMenu.this);
                startActivity(newGameIntent);
            }
        });
        Button startSettings = (Button) findViewById(btnSettings);
        startSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent settingsIntent = SettingsScreen.makeIntent(MainMenu.this);
                startActivity(settingsIntent);
            }
        });
        Button startAbout = (Button) findViewById(btnAbout);
        startAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aboutIntent = AboutScreen.makeIntent(MainMenu.this);
                startActivity(aboutIntent);
            }
        });
    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, MainMenu.class);
    }

}
