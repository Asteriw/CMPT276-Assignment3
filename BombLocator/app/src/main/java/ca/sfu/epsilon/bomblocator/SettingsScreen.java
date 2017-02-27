package ca.sfu.epsilon.bomblocator;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class SettingsScreen extends AppCompatActivity {

    private static final String SHAREDPREF_SET = "BombLocator";
    private static final String SHAREDPREF_ITEM_HIGHSCORE = "HighScore";
    private static final String SHAREDPREF_ITEM_GRIDWIDTH = "GridWidth";
    private static final String SHAREDPREF_ITEM_GRIDHEIGHT = "GridHeight";
    private static final String SHAREDPREF_ITEM_MINECOUNT = "MineCount";
    private static final String SHAREDPREF_ITEM_MINESPINNER = "MineSpinner";
    private static final String SHAREDPREF_ITEM_SIZESPINNER = "SizeSpinner";

    int sizeSpinner;
    int width;
    int height;
    int minesSpinner;
    int minesVal;
    int highScore;
    int initialsize = 0;
    int initialmines = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_screen);
        loadSettings();
        setupSpinners();
        setupReset();
        setupBackButton();
    }

    private void loadSettings() {
        SharedPreferences preferences = getSharedPreferences(SHAREDPREF_SET, MODE_PRIVATE);
        width = preferences.getInt(SHAREDPREF_ITEM_GRIDWIDTH, 6);
        height = preferences.getInt(SHAREDPREF_ITEM_GRIDHEIGHT, 4);
        minesVal = preferences.getInt(SHAREDPREF_ITEM_MINECOUNT, 6);
    }

    //setupSpinners creates the two dropdown menus, reading values off of string arrays located in the strings.xml.
    // When an item is selected, the spinners call their own SpinnerListener(function described below).
    //.setSelection makes the spinners display the value at the specified "sizeSpinner/minesSpinner" position.
    private void setupSpinners() {
        Spinner BSizeSpinner = (Spinner) findViewById(R.id.BSizeSpinner);
        ArrayAdapter<CharSequence> BSizeAdapter = ArrayAdapter.createFromResource(this, R.array.board_sizes, android.R.layout.simple_spinner_item);
        BSizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        BSizeSpinner.setOnItemSelectedListener(new SpinnerListener1());
        BSizeSpinner.setAdapter(BSizeAdapter);
        BSizeSpinner.setSelection(sizeSpinner);

        Spinner MineNumSpinner = (Spinner) findViewById(R.id.MineNumSpinner);
        ArrayAdapter<CharSequence> MineNumAdapter = ArrayAdapter.createFromResource(this, R.array.mine_amounts, android.R.layout.simple_spinner_item);
        MineNumAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        MineNumSpinner.setOnItemSelectedListener(new SpinnerListener2());
        MineNumSpinner.setAdapter(MineNumAdapter);
        MineNumSpinner.setSelection(minesSpinner);
    }

    //A reset button, that, when clicked, brings up an alert dialog with a yes/no choice.
    public void setupReset() {
        Button resetButton = (Button) findViewById(R.id.resetbutton);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(SettingsScreen.this)
                        .setMessage(R.string.resetConfirmation)
                        .setCancelable(true)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                highScore = 100;
                                SharedPreferences preferences = getSharedPreferences(SHAREDPREF_SET, MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferences.edit();
                                for (int i = 5; i<25; i=i+5){
                                    if (i == 5){
                                        editor.putInt(SHAREDPREF_ITEM_HIGHSCORE+4+6+6, 100);
                                    } else {
                                        editor.putInt(SHAREDPREF_ITEM_HIGHSCORE+4+6+i, 100);
                                    }
                                    editor.putInt(SHAREDPREF_ITEM_HIGHSCORE+5+10+i, 100);
                                    editor.putInt(SHAREDPREF_ITEM_HIGHSCORE+6+15+i, 100);
                                }
                                editor.apply();
                                SettingsScreen.this.finish();
                            }
                        })
                        .setNegativeButton(R.string.no, null)
                        .show();
            }
        });
    }
    
    private void setupBackButton() {
        Button backButton = (Button) findViewById(R.id.backbutton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveSettings();
                SettingsScreen.this.finish();
            }
        });
    }

    private void saveSettings() {
        SharedPreferences preferences = getSharedPreferences(SHAREDPREF_SET, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(SHAREDPREF_ITEM_MINECOUNT, minesVal);
        editor.putInt(SHAREDPREF_ITEM_GRIDHEIGHT, height);
        editor.putInt(SHAREDPREF_ITEM_GRIDWIDTH, width);
        editor.putInt(SHAREDPREF_ITEM_MINESPINNER, minesSpinner);
        editor.putInt(SHAREDPREF_ITEM_SIZESPINNER, sizeSpinner);
        editor.apply();
    }

    //The SpinnerListeners are called when the user clicks on a spinner and selects a value.
    //When the Settings activity first launches, the "initial" variables stop the Listeners from changing the default values of sizeSpinner and minesSpinner.
    //The "initial" variables are what allow the spinners to change the default values displayed on the spinners.
    //After the initial setup, later calls on SpinnerListeners will change the sizeSpinner and minesSpinner variables and save them, thus changing the game settings.
    private class SpinnerListener1 implements AdapterView.OnItemSelectedListener {

        public void onItemSelected (AdapterView<?> parent, View view, int position, long id) {
            if (initialsize == 0) {
                initialsize++;
            }
            int selectedSettingNum = parent.getSelectedItemPosition();
            if (selectedSettingNum == 0) {
                sizeSpinner = 0;
                width = 6;
                height = 4;
            }
            if (selectedSettingNum == 1) {
                sizeSpinner = 1;
                width = 10;
                height = 5;
            }
            if (selectedSettingNum == 2) {
                sizeSpinner = 2;
                width = 15;
                height = 6;
            }
        }

        public void onNothingSelected (AdapterView<?> parent) {
        }
    }

    private class SpinnerListener2 implements AdapterView.OnItemSelectedListener {

        public void onItemSelected (AdapterView<?> parent, View view, int position, long id) {
            if (initialmines == 0) {
                initialmines++;
            }
            int selectedSettingNum = parent.getSelectedItemPosition();
            String selectedSettingName = parent.getItemAtPosition(position).toString();
            if (selectedSettingNum == 0) {
                minesSpinner = 0;
                minesVal = 6;
            }
            if (selectedSettingNum == 1) {
                minesSpinner = 1;
                minesVal = 10;
            }
            if (selectedSettingNum == 2) {
                minesSpinner = 2;
                minesVal = 15;
            }
            if (selectedSettingNum == 3) {
                minesSpinner = 3;
                minesVal = 20;
            }
        }

        public void onNothingSelected (AdapterView<?> parent) {
        }
    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, SettingsScreen.class);
    }
}
