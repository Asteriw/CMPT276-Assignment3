package ca.sfu.epsilon.bomblocator;

import java.util.Random;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class GameScreen extends AppCompatActivity {




    private static final String SHAREDPREF_SET = "BombLocator";
    private static final int SHAREDPREF_ITEM_HIGHSCORE = 100;
    private static final int SHAREDPREF_ITEM_GRIDSIZE = 24;

    int rows = 4;
    int cols = 6;
    int mineCount = 12;
    int[][] mineArray = new int[rows][cols];
    int[][] totalArray = new int[rows][cols];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        setupArrays();
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
        int[] rowTotals = new int[rows];
        int[] colTotals = new int[cols];
        for(i=0;i<rows;i++){
            temp = 0;
            for(j=0;j<cols;j++) {
                temp = temp + mineArray[i][j];
            }
            rowTotals[i] = temp;
        }
        for(i=0;i<cols;i++){
            temp = 0;
            for(j=0;j<rows;j++) {
                temp = temp + mineArray[j][i];
            }
            colTotals[i] = temp;
        }
        for(i=0;i<rows;i++){
            for(j=0;j<cols;j++) {
                totalArray[i][j] = rowTotals[i] + colTotals[j];
            }
        }
    }

    private void setupMineArray() {
        int arrayCol = 0;
        int arrayRow = 0;
        int i = 0;
        while(i<mineCount){
            arrayRow = (int )(Math.random()*rows);
            arrayCol = (int )(Math.random()*cols);
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
