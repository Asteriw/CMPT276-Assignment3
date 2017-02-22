package ca.sfu.epsilon.bomblocator;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
    private static final int SHAREDPREF_ITEM_HIGHSCORE = 100;
    private static final int SHAREDPREF_ITEM_GRIDSIZE = 24;
    int size = 0;
    int mines = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_screen);

        setupSpinners();
        setupReset();
        setupSaveButton();
    }

    private void setupSpinners() {
        Spinner BSizeSpinner = (Spinner) findViewById(R.id.BSizeSpinner);
        ArrayAdapter<CharSequence> BSizeAdapter = ArrayAdapter.createFromResource(this, R.array.board_sizes, android.R.layout.simple_spinner_item);
        BSizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        BSizeSpinner.setOnItemSelectedListener(new SpinnerListener());
        BSizeSpinner.setAdapter(BSizeAdapter);

        Spinner MineNumSpinner = (Spinner) findViewById(R.id.MineNumSpinner);
        ArrayAdapter<CharSequence> MineNumAdapter = ArrayAdapter.createFromResource(this, R.array.mine_amounts, android.R.layout.simple_spinner_item);
        MineNumAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        MineNumSpinner.setOnItemSelectedListener(new SpinnerListener());
        MineNumSpinner.setAdapter(MineNumAdapter);
    }

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
                                SettingsScreen.this.finish();
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });
    }

    private void setupSaveButton() {
        Button backButton = (Button) findViewById(R.id.backbutton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Settings", "It should have saved the options.");
            }
        });
    }

    private class SpinnerListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected (AdapterView<?> parent, View view, int position, long id) {
            int selectedSettingNum = parent.getSelectedItemPosition();
            String selectedSettingName = parent.getItemAtPosition(position).toString();
            String adapterName = parent.getAdapter().toString();
            Log.i("Settings", "Setting number: " + selectedSettingNum + " is: " + selectedSettingName + ".");
            Log.i("Settings", "Adapter name is: " + adapterName);
            if (adapterName.equals("android.widget.ArrayAdapter@4ca88ce")) {
                if (selectedSettingNum == 0) {
                    size = 0;
                }
                if (selectedSettingNum == 1) {
                    size = 1;
                }
                if (selectedSettingNum == 2) {
                    size = 2;
                }
            }
            else if (adapterName.equals("android.widget.ArrayAdapter@d3f33ef")) {
                if (selectedSettingNum == 0) {
                    mines = 0;
                }
                if (selectedSettingNum == 1) {
                    mines = 1;
                }
                if (selectedSettingNum == 2) {
                    mines = 2;
                }
                if (selectedSettingNum == 3) {
                    mines = 3;
                }
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
