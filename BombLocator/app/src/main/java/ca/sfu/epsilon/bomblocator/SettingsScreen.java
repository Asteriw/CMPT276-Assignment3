package ca.sfu.epsilon.bomblocator;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SettingsScreen extends AppCompatActivity {

    private static final String SHAREDPREF_SET = "BombLocator";
    private static final int SHAREDPREF_ITEM_HIGHSCORE = 100;
    private static final int SHAREDPREF_ITEM_GRIDSIZE = 24;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_screen);
    }



    public static Intent makeIntent(Context context) {
        return new Intent(context, SettingsScreen.class);
    }
}
