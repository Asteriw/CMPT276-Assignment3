package ca.sfu.epsilon.bomblocator;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class GameScreen extends AppCompatActivity {

    private static final String SHAREDPREF_SET = "BombLocator";
    private static final String SHAREDPREF_ITEM_HIGHSCORE = "HighScore";
    private static final String SHAREDPREF_ITEM_GRIDWIDTH = "GridWidth";
    private static final String SHAREDPREF_ITEM_GRIDHEIGHT = "GridHeight";
    private static final String SHAREDPREF_ITEM_MINECOUNT = "MineCount";

    int rows;
    int cols;
    int mineCount;
    MineArray mineArray;
    TotalArray totalArray;
    ClickedArray clickedArray;
    Button[][] buttonArray;

    int startMinecount;
    int scans;
    int highScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);
        loadSettings();
        updateTextViews();
        setupArrays();
        setupButtons();
    }

    private void  updateTextViews() {
        TextView defuserAmount = (TextView) findViewById(R.id.defuserField);
        TextView highScoreTV = (TextView) findViewById(R.id.scoreField);
        defuserAmount.setText(String.valueOf(scans));
        if (highScore == 100 || highScore == 0){
            highScoreTV.setText(R.string.highscore_none);
        } else {
            highScoreTV.setText(String.valueOf(highScore));
        }
    }

    private void setupButtons() {
        TableLayout table = (TableLayout) findViewById(R.id.gameTable);
        for(int i = 0; i< rows; i++){
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f
                ));
            table.addView(tableRow);
            for (int j = 0; j< cols; j++){
                final int FINAL_ROW = i;
                final int FINAL_COL = j;
                Button button = new Button(this);
                button.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f
                ));
                button.setPadding(0,0,0,0);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        buttonClicked(FINAL_ROW, FINAL_COL);
                    }
                });
                tableRow.addView(button);
                buttonArray[i][j] = button;
            }
        }
    }

    private void buttonClicked(int row, int col) {
        lockButtonSizes();
        Button button = buttonArray[row][col];
        if(mineArray.getValue(row,col) == 1){
            int tempWidth = button.getWidth();
            int tempHeight = button.getHeight();
            Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.found_bomb);
            Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, tempWidth, tempHeight, true);
            Resources resource = getResources();
            button.setBackground(new BitmapDrawable(resource, scaledBitmap));
            mineArray.setValue(row, col, 0);
            mineCount--;
            totalArray.populateTotalArray(mineArray);
            updateClickedText();
            clickedArray.setValue(row, col, 2);
            if (mineCount == 0){//ends the game when all mines found
                if (scans < highScore) {
                    TextView highScoreTV = (TextView) findViewById(R.id.scoreField);
                    highScoreTV.setText(String.valueOf(scans));
                    new AlertDialog.Builder(GameScreen.this)
                            .setMessage("Congratulations! You set a new high score of " + scans + " for the " + rows + "x" + cols + " grid with " + startMinecount + " bombs!")
                            .setPositiveButton(R.string.OK, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    GameScreen.this.finish();
                                }
                            })
                            .show();
                    saveHighScore(scans);
                } else {
                    new AlertDialog.Builder(GameScreen.this)
                            .setMessage(R.string.win_nohighscore)
                            .setPositiveButton(R.string.OK, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    GameScreen.this.finish();
                                }
                            })
                            .show();
                }
            }
        } else {
            if (clickedArray.getValue(row, col) == 0){
                clickedArray.setValue(row, col, 1);
                scans++;
                updateTextViews();
                button.setText(String.valueOf(totalArray.getValue(row,col)));
            } else if (clickedArray.getValue(row, col) == 2){//Already displayed
                scans++;
                updateTextViews();
                clickedArray.setValue(row, col, 1);
                updateButtonText(row, col, button);
                button.setTextColor(Color.parseColor("#DDDDDD"));
            }
        }
    }

    private void saveHighScore(int highScore) {
        SharedPreferences preferences = getSharedPreferences(SHAREDPREF_SET, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(SHAREDPREF_ITEM_HIGHSCORE+rows+cols+startMinecount, highScore);
        editor.apply();
    }

    private void updateClickedText() {
        for(int i = 0; i< rows; i++){
            for(int j = 0; j< cols; j++){
                if (clickedArray.getValue(i, j) == 1){
                    updateButtonText(i, j, buttonArray[i][j]);
                }
            }
        }
    }

    private void updateButtonText(int row, int col, Button button) {
        button.setText(String.valueOf(totalArray.getValue(row,col)));
    }

    private void lockButtonSizes() {
        for (int i = 0; i < rows; i++){
            for (int j = 0; j< cols; j++){
                Button button = buttonArray[i][j];

                int tempWidth = button.getWidth();
                button.setMaxWidth(tempWidth);
                button.setMinWidth(tempWidth);

                int tempHeight = button.getHeight();
                button.setMaxHeight(tempHeight);
                button.setMinHeight(tempHeight);
            }
        }
    }

    private void setupArrays() {
        mineArray = new MineArray(rows, cols);
        totalArray = new TotalArray(rows, cols);
        clickedArray = new ClickedArray(rows, cols);
        buttonArray = new Button[rows][cols];
        setupMineArray();
        setupTotalsArray(mineArray);
    }

    private void setupTotalsArray(MineArray mineArray) {
        totalArray.populateTotalArray(mineArray);
    }

    private void loadSettings(){
        SharedPreferences preferences = getSharedPreferences(SHAREDPREF_SET, MODE_PRIVATE);
        cols = preferences.getInt(SHAREDPREF_ITEM_GRIDWIDTH, 6);
        rows = preferences.getInt(SHAREDPREF_ITEM_GRIDHEIGHT, 4);
        mineCount = preferences.getInt(SHAREDPREF_ITEM_MINECOUNT, 6);
        highScore = preferences.getInt(SHAREDPREF_ITEM_HIGHSCORE+rows+cols+mineCount, 100);
        startMinecount = mineCount;
    }

    private void setupMineArray() {
        mineArray.populateMineArray(mineCount);
    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, GameScreen.class);
    }
}
