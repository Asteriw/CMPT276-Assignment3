package ca.sfu.epsilon.bomblocator;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class AboutScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_screen);
        setupBackButton();
    }

    private void setupBackButton() {
        Button backButton = (Button) findViewById(R.id.aboutbackbutton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AboutScreen.this.finish();
            }
        });
    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, AboutScreen.class);
    }
}
