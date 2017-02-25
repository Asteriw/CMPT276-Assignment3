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

    int size = 2;
    int width = 6;
    int height = 4;
    int mines = 2;
    int minesVal;
    int highScore;
    int initialsize = 0;
    int initialmines = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_screen);

        setupSpinners();
        setupReset();
        setupBackButton();
    }

    //setupSpinners creates the two dropdown menus, reading values off of string arrays located in the strings.xml.
    // When an item is selected, the spinners call their own SpinnerListener(function described below).
    //.setSelection makes the spinners display the value at the specified "size/mines" position.
    private void setupSpinners() {
        Spinner BSizeSpinner = (Spinner) findViewById(R.id.BSizeSpinner);
        ArrayAdapter<CharSequence> BSizeAdapter = ArrayAdapter.createFromResource(this, R.array.board_sizes, android.R.layout.simple_spinner_item);
        BSizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        BSizeSpinner.setOnItemSelectedListener(new SpinnerListener1());
        BSizeSpinner.setAdapter(BSizeAdapter);
        BSizeSpinner.setSelection(size);

        Spinner MineNumSpinner = (Spinner) findViewById(R.id.MineNumSpinner);
        ArrayAdapter<CharSequence> MineNumAdapter = ArrayAdapter.createFromResource(this, R.array.mine_amounts, android.R.layout.simple_spinner_item);
        MineNumAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        MineNumSpinner.setOnItemSelectedListener(new SpinnerListener2());
        MineNumSpinner.setAdapter(MineNumAdapter);
        MineNumSpinner.setSelection(mines);
    }

    //A reset button, that, when clicked, brings up an alert dialog with a yes/no choice.
    public void setupReset() {
        Button resetButton = (Button) findViewById(R.id.resetbutton);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(SettingsScreen.this)
                        .setMessage("Are you sure you want to reset progress?")
                        .setCancelable(true)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                highScore = 100;
                                SharedPreferences preferences = getSharedPreferences(SHAREDPREF_SET, MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putInt(SHAREDPREF_ITEM_HIGHSCORE, highScore);
                                editor.apply();
                                SettingsScreen.this.finish();
                            }
                        })
                        .setNegativeButton("No", null)
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
        editor.putInt(SHAREDPREF_ITEM_HIGHSCORE, highScore);
        editor.apply();
    }

    //The SpinnerListeners are called when the user clicks on a spinner and selects a value.
    //When the Settings activity first launches, the "initial" variables stop the Listeners from changing the default values of size and mines.
    //The "initial" variables are what allow the spinners to change the default values displayed on the spinners.
    //After the initial setup, later calls on SpinnerListeners will change the size and mines variables and save them, thus changing the game settings.
    private class SpinnerListener1 implements AdapterView.OnItemSelectedListener {

        public void onItemSelected (AdapterView<?> parent, View view, int position, long id) {
            if (initialsize == 0) {
                initialsize++;
                return;
            }
            int selectedSettingNum = parent.getSelectedItemPosition();
            String selectedSettingName = parent.getItemAtPosition(position).toString();
            Log.i("Settings", "Size number: " + selectedSettingNum + " is: " + selectedSettingName + ".");
            if (selectedSettingNum == 0) {
                size = 0;
                width = 6;
                height = 4;
            }
            if (selectedSettingNum == 1) {
                size = 1;
                width = 10;
                height = 5;
            }
            if (selectedSettingNum == 2) {
                size = 2;
                width = 15;
                height = 6;
            }
            Log.i("Settings", "Size and Mines are: " + size + " and " + mines);
        }

        public void onNothingSelected (AdapterView<?> parent) {
        }
    }

    private class SpinnerListener2 implements AdapterView.OnItemSelectedListener {

        public void onItemSelected (AdapterView<?> parent, View view, int position, long id) {
            if (initialmines == 0) {
                initialmines++;
                return;
            }
            int selectedSettingNum = parent.getSelectedItemPosition();
            String selectedSettingName = parent.getItemAtPosition(position).toString();
            Log.i("Settings", "Mines number: " + selectedSettingNum + " is: " + selectedSettingName + ".");
            if (selectedSettingNum == 0) {
                mines = 0;
                minesVal = 6;
            }
            if (selectedSettingNum == 1) {
                mines = 1;
                minesVal = 10;
            }
            if (selectedSettingNum == 2) {
                mines = 2;
                minesVal = 15;
            }
            if (selectedSettingNum == 3) {
                mines = 3;
                minesVal = 20;
            }
            Log.i("Settings", "Size and Mines are: " + size + " and " + mines);
        }

        public void onNothingSelected (AdapterView<?> parent) {
        }
    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, SettingsScreen.class);
    }
}
