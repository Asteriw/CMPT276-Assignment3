package ca.sfu.epsilon.bomblocator;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import static ca.sfu.epsilon.bomblocator.R.mipmap.bomb;

public class GameScreen extends AppCompatActivity {

    private static final String SHAREDPREF_SET = "BombLocator";
    private static final int SHAREDPREF_ITEM_HIGHSCORE = 100;
    private static final int SHAREDPREF_ITEM_GRIDSIZE = 24;

    private final int ROWS = 4;
    private final int COLS = 6;
    private final int MINE_COUNT = 12;
    MineArray mineArray = new MineArray(ROWS, COLS);
    TotalArray totalArray = new TotalArray(ROWS, COLS);
    Button[][] buttonArray = new Button[ROWS][COLS];
    //TextView defuserAmount = (TextView) findViewById(R.id.defuserField);
    //TextView highScoreTV = (TextView) findViewById(R.id.scoreField);;

    int scans = 0;
    final int highScore = SHAREDPREF_ITEM_HIGHSCORE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);
        setupArrays();
        setupButtons();
       // setupTextViews();
    }

   // private void setupTextViews() {
   //     defuserAmount.setText(scans);
   //     highScoreTV.setText(highScore);
   // }

    private void setupButtons() {
        TableLayout table = (TableLayout) findViewById(R.id.gameTable);
        for(int i = 0; i< ROWS; i++){
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    2.0f
                ));
            table.addView(tableRow);
            for (int j = 0; j< COLS; j++){
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
        //lock button sizes
        lockButtonSizes();
        if(mineArray.getValue(row,col) == 1){
            Button button = buttonArray[row][col];
            int tempWidth = button.getWidth();
            int tempHeight = button.getHeight();
            Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), ca.sfu.epsilon.bomblocator.R.mipmap.bomb);
            Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, tempWidth, tempHeight, true);
            Resources resource = getResources();
            button.setBackground(new BitmapDrawable(resource, scaledBitmap));
        } else {
            scans++;
            //defuserAmount.setText(scans);
        }
    }

    private void lockButtonSizes() {
        for (int i = 0; i < ROWS; i++){
            for (int j = 0; j< COLS; j++){
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
        printArrays();
    }

    private void printArrays() {
        mineArray.printArray();
        totalArray.printArray();
    }

    private void setupTotalsArray(MineArray mineArray) {
        totalArray.populateTotalArray(mineArray);
    }

    private void setupMineArray() {
        mineArray.populateMineArray(MINE_COUNT);
    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, GameScreen.class);
    }
}
