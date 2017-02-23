package ca.sfu.epsilon.bomblocator;

import android.util.Log;

/**
 * Created by ALPHA on 2017-02-22.
 */

public class MineArray {
    private int rows;
    private int cols;
    private int[][] mineArray;

    public MineArray(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        this.mineArray = new int[rows][cols];
    }

    public void populateMineArray(int mineCount) {
        int arrayCol = 0;
        int arrayRow = 0;
        int i = 0;
        while (i < mineCount) {
            arrayRow = (int) (Math.random() * rows);
            arrayCol = (int) (Math.random() * cols);
            if (mineArray[arrayRow][arrayCol] == 0) {
                mineArray[arrayRow][arrayCol] = 1;
                i++;
            }
        }
    }

    public int getValue(int row, int col){
        return mineArray[row][col];
    }

    public void setValue(int row, int col, int value){
        mineArray[row][col] = value;
    }

    public void printArray(){
        Log.i("Random", "" + mineArray[0][0] + " ," + mineArray[0][1] + " ," + mineArray[0][2] + " ," + mineArray[0][3] + " ,"+ mineArray[0][4] + " ,"+ mineArray[0][5]);
        Log.i("Random", "" + mineArray[1][0] + " ," + mineArray[1][1] + " ," + mineArray[1][2] + " ," + mineArray[1][3] + " ,"+ mineArray[1][4] + " ,"+ mineArray[1][5]);
        Log.i("Random", "" + mineArray[2][0] + " ," + mineArray[2][1] + " ," + mineArray[2][2] + " ," + mineArray[2][3] + " ,"+ mineArray[2][4] + " ,"+ mineArray[2][5]);
        Log.i("Random", "" + mineArray[3][0] + " ," + mineArray[3][1] + " ," + mineArray[3][2] + " ," + mineArray[3][3] + " ,"+ mineArray[3][4] + " ,"+ mineArray[3][5]);
    }
}
