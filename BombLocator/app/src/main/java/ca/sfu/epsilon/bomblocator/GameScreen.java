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
    MineArray mineArray = new MineArray(ROWS, COLS);

    TotalArray totalArray = new TotalArray(ROWS, COLS);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);
        setupArrays();
        setupButtons();
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
