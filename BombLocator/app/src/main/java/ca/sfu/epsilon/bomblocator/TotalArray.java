package ca.sfu.epsilon.bomblocator;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class TotalArray extends AppCompatActivity {

    private int[][] totalArray;
    private int rows;
    private int cols;

    public TotalArray(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        this.totalArray = new int[rows][cols];
    }

    public void populateTotalArray(MineArray mineArray){
        int i;
        int j;
        int temp;
        int[] rowTotals = new int[rows];
        int[] colTotals = new int[cols];
        for(i=0;i<rows;i++){
            temp = 0;
            for(j=0;j<cols;j++) {
                temp = temp + mineArray.getValue(i,j);
            }
            rowTotals[i] = temp;
        }
        for(i=0;i<cols;i++){
            temp = 0;
            for(j=0;j<rows;j++) {
                temp = temp + mineArray.getValue(j,i);
            }
            colTotals[i] = temp;
        }
        for(i=0;i<rows;i++){
            for(j=0;j<cols;j++) {
                totalArray[i][j] = rowTotals[i] + colTotals[j];
                if (mineArray.getValue(i,j) == 1){
                    totalArray[i][j]--;
                }
            }
        }
    }

    public int getValue(int row, int col){
        return totalArray[row][col];
    }
}
