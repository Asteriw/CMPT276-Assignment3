package ca.sfu.epsilon.bomblocator;

import android.util.Log;

/**
 * Created by ALPHA on 2017-02-22.
 */

public class TotalArray {
    private int[][] totalArray;
    private int rows;
    private int cols;

    public TotalArray(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        this.totalArray = new int[rows][cols];
    }

    public void populateTotalArray(MineArray mineArray){
        int i = 0;
        int j = 0;
        int temp = 0;
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

    public void setValue(int row, int col, int value){
        totalArray[row][col] = value;
    }

    public void printArray(){
        Log.i("Random", "" + totalArray[0][0] + " ," + totalArray[0][1] + " ," + totalArray[0][2] + " ," + totalArray[0][3] + " ,"+ totalArray[0][4] + " ,"+ totalArray[0][5]);
        Log.i("Random", "" + totalArray[1][0] + " ," + totalArray[1][1] + " ," + totalArray[1][2] + " ," + totalArray[1][3] + " ,"+ totalArray[1][4] + " ,"+ totalArray[1][5]);
        Log.i("Random", "" + totalArray[2][0] + " ," + totalArray[2][1] + " ," + totalArray[2][2] + " ," + totalArray[2][3] + " ,"+ totalArray[2][4] + " ,"+ totalArray[2][5]);
        Log.i("Random", "" + totalArray[3][0] + " ," + totalArray[3][1] + " ," + totalArray[3][2] + " ," + totalArray[3][3] + " ,"+ totalArray[3][4] + " ,"+ totalArray[3][5]);
    }
}
