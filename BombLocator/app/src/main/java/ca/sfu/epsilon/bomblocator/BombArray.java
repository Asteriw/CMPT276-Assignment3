package ca.sfu.epsilon.bomblocator;

import android.support.v7.app.AppCompatActivity;

public class BombArray extends AppCompatActivity {

    private int rows;
    private int cols;
    private int[][] bombArray;

    public BombArray(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        this.bombArray = new int[rows][cols];
    }

    public void populateBombArray(int bombCount) {
        int arrayCol;
        int arrayRow;
        int i = 0;
        while (i < bombCount) {
            arrayRow = (int) (Math.random() * rows);
            arrayCol = (int) (Math.random() * cols);
            if (bombArray[arrayRow][arrayCol] == 0) {
                bombArray[arrayRow][arrayCol] = 1;
                i++;
            }
        }
    }

    public int getValue(int row, int col){
        return bombArray[row][col];
    }

    public void setValue(int row, int col, int value){
        bombArray[row][col] = value;
    }
}
