package ca.sfu.epsilon.bomblocator;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

public class GameScreen extends AppCompatActivity {

    private static final String SHAREDPREF_SET = "BombLocator";
    private static final int SHAREDPREF_ITEM_HIGHSCORE = 100;
    private static final int SHAREDPREF_ITEM_GRIDSIZE = 24;

    private final int ROWS = 4;
    private final int COLS = 6;
    private final int MINE_COUNT = 12;
    int[][] mineArray = new int[ROWS][COLS];
    int[][] totalArray = new int[ROWS][COLS];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);
        setupButtons();
        setupArrays();
    }

    private void setupButtons() {
        TableLayout table = (TableLayout) findViewById(R.id.gameTable);
        for(int i = 0; i< ROWS; i++){
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f
                ));
            table.addView(tableRow);
            for (int j = 0; j< COLS; j++){
                Button button = new Button(this);
                button.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f
                ));
                tableRow.addView(button);
            }
        }
    }

    private void setupArrays() {
        setupMineArray();
        setupTotalsArray();
        printArray();
    }

    private void printArray() {
        Log.i("Random", "" + mineArray[0][0] + " ," + mineArray[0][1] + " ," + mineArray[0][2] + " ," + mineArray[0][3] + " ,"+ mineArray[0][4] + " ,"+ mineArray[0][5]);
        Log.i("Random", "" + mineArray[1][0] + " ," + mineArray[1][1] + " ," + mineArray[1][2] + " ," + mineArray[1][3] + " ,"+ mineArray[1][4] + " ,"+ mineArray[1][5]);
        Log.i("Random", "" + mineArray[2][0] + " ," + mineArray[2][1] + " ," + mineArray[2][2] + " ," + mineArray[2][3] + " ,"+ mineArray[2][4] + " ,"+ mineArray[2][5]);
        Log.i("Random", "" + mineArray[3][0] + " ," + mineArray[3][1] + " ," + mineArray[3][2] + " ," + mineArray[3][3] + " ,"+ mineArray[3][4] + " ,"+ mineArray[3][5]);
        Log.i("Random", "" + totalArray[0][0] + " ," + totalArray[0][1] + " ," + totalArray[0][2] + " ," + totalArray[0][3] + " ,"+ totalArray[0][4] + " ,"+ totalArray[0][5]);
        Log.i("Random", "" + totalArray[1][0] + " ," + totalArray[1][1] + " ," + totalArray[1][2] + " ," + totalArray[1][3] + " ,"+ totalArray[1][4] + " ,"+ totalArray[1][5]);
        Log.i("Random", "" + totalArray[2][0] + " ," + totalArray[2][1] + " ," + totalArray[2][2] + " ," + totalArray[2][3] + " ,"+ totalArray[2][4] + " ,"+ totalArray[2][5]);
        Log.i("Random", "" + totalArray[3][0] + " ," + totalArray[3][1] + " ," + totalArray[3][2] + " ," + totalArray[3][3] + " ,"+ totalArray[3][4] + " ,"+ totalArray[3][5]);
    }

    private void setupTotalsArray() {
        int i = 0;
        int j = 0;
        int temp = 0;
        int[] rowTotals = new int[ROWS];
        int[] colTotals = new int[COLS];
        for(i=0;i<ROWS;i++){
            temp = 0;
            for(j=0;j<COLS;j++) {
                temp = temp + mineArray[i][j];
            }
            rowTotals[i] = temp;
        }
        for(i=0;i<COLS;i++){
            temp = 0;
            for(j=0;j<ROWS;j++) {
                temp = temp + mineArray[j][i];
            }
            colTotals[i] = temp;
        }
        for(i=0;i<ROWS;i++){
            for(j=0;j<COLS;j++) {
                totalArray[i][j] = rowTotals[i] + colTotals[j];
                if (mineArray[i][j] == 1){
                    totalArray[i][j]--;
                }
            }
        }
    }

    private void setupMineArray() {
        int arrayCol = 0;
        int arrayRow = 0;
        int i = 0;
        while(i<MINE_COUNT){
            arrayRow = (int )(Math.random()*ROWS);
            arrayCol = (int )(Math.random()*COLS);
            if (mineArray[arrayRow][arrayCol] == 0){
                mineArray[arrayRow][arrayCol] = 1;
                i++;
            }
        }
    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, GameScreen.class);
    }
}
