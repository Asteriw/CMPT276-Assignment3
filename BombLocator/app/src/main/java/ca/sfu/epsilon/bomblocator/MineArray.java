package ca.sfu.epsilon.bomblocator;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MineArray  extends AppCompatActivity {

    private int rows;
    private int cols;
    private int[][] mineArray;

    public MineArray(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        this.mineArray = new int[rows][cols];
    }

    public void populateMineArray(int mineCount) {
        int arrayCol;
        int arrayRow;
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
}
