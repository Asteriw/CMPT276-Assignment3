package ca.sfu.epsilon.bomblocator;

import android.support.v7.app.AppCompatActivity;

public class ClickedArray  extends AppCompatActivity {

    private int[][] clickedArray;

    public ClickedArray(int rows, int cols){
        this.clickedArray = new int[rows][cols];
        for(int i = 0; i< rows; i++){
            for(int j = 0; j< cols; j++){
                clickedArray[i][j] = 0;
            }
        }
    }

    public int getValue(int row, int col){
        return clickedArray[row][col];
    }

    public void setValue(int row, int col, int value){
        clickedArray[row][col] = value;
    }
}
