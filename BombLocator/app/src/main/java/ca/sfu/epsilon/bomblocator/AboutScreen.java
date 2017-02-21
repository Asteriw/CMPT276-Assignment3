package ca.sfu.epsilon.bomblocator;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AboutScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_screen);
    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, AboutScreen.class);
    }
}
