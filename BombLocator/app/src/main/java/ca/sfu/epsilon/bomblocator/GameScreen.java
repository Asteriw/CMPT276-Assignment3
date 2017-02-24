package ca.sfu.epsilon.bomblocator;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
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

    int rows = 4;
    int cols = 6;
    int mineCount;
    MineArray mineArray = new MineArray(rows, cols);
    TotalArray totalArray = new TotalArray(rows, cols);
    ClickedArray clickedArray = new ClickedArray(rows, cols);
    Button[][] buttonArray = new Button[rows][cols];

    int scans;
    int highScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);
        loadSettings();
        setupArrays();
        setupButtons();
        updateTextViews();
    }

    private void  updateTextViews() {
        TextView defuserAmount = (TextView) findViewById(R.id.defuserField);
        TextView highScoreTV = (TextView) findViewById(R.id.scoreField);
        defuserAmount.setText(String.valueOf(scans));
        highScoreTV.setText(String.valueOf(highScore));
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
            Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), ca.sfu.epsilon.bomblocator.R.mipmap.bomb);
            Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, tempWidth, tempHeight, true);
            Resources resource = getResources();
            button.setBackground(new BitmapDrawable(resource, scaledBitmap));
            mineArray.setValue(row, col, 0);
            mineCount--;
            totalArray.populateTotalArray(mineArray);
            updateClickedText();
            clickedArray.setValue(row, col, 2);
            if (mineCount == 0){//ends the game when all mines found
                saveHighScore(highScore);
                finish();
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
        editor.putInt(SHAREDPREF_ITEM_HIGHSCORE, highScore);
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
        setupMineArray();
        setupTotalsArray(mineArray);
        setupClickedArray();
        printArrays();
    }

    private void setupClickedArray() {

    }

    private void printArrays() {
        mineArray.printArray();
        totalArray.printArray();
    }

    private void setupTotalsArray(MineArray mineArray) {
        totalArray.populateTotalArray(mineArray);
    }

    private void loadSettings(){
        SharedPreferences preferences = getSharedPreferences(SHAREDPREF_SET, MODE_PRIVATE);
        highScore = preferences.getInt(SHAREDPREF_ITEM_HIGHSCORE, 100);
        cols = preferences.getInt(SHAREDPREF_ITEM_GRIDWIDTH, 6);
        rows = preferences.getInt(SHAREDPREF_ITEM_GRIDHEIGHT, 4);
        mineCount = preferences.getInt(SHAREDPREF_ITEM_MINECOUNT, 12);
    }

    private void setupMineArray() {
        mineArray.populateMineArray(mineCount);
    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, GameScreen.class);
    }
}
