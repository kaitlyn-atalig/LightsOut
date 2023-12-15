package com.example.lightsout;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private final int gridSize = 5;
    private Button[][] buttons = new Button[gridSize][gridSize];
    private boolean[][] isLightOn = new boolean[gridSize][gridSize];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        gridLayout.setColumnCount(gridSize);
        gridLayout.setRowCount(gridSize);

        //initializing the grid and buttons
        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                buttons[row][col] = new Button(this);
                buttons[row][col].setBackgroundColor(Color.BLACK);

                //the lights all start out as off in the beginning
                isLightOn[row][col] = false;

                //this is to create the buttons and space them out evenly
                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                params.width = 0;
                params.height = 0;
                params.rowSpec = GridLayout.spec(row, 1f);
                params.columnSpec = GridLayout.spec(col, 1f);
                params.setMargins(10,10,10,10);
                buttons[row][col].setLayoutParams(params);


                //onClick Listeners for buttons
                final int finalRow = row;
                final int finalCol = col;

                buttons[row][col].setOnClickListener(new View.OnClickListener() {

                    @Override

                    public void onClick(View view) {
                        flipColors(finalRow, finalCol);
                    }
                });

                gridLayout.addView(buttons[row][col]);

            }
        }
    }

    private void flipColors(int row, int col) {

        //this is to flip the color of the button that was clicked
        isLightOn[row][col] = !isLightOn[row][col];
        updateButtonColor(row, col);

        //this is to flip the colors of the buttons adjacent to the one clicked
        if (row > 0) {
            isLightOn[row - 1][col] = !isLightOn[row - 1][col];
            updateButtonColor(row - 1, col);
        }

        if (col > 0) {
            isLightOn[row][col - 1] = !isLightOn[row][col - 1];
            updateButtonColor(row, col - 1);
        }

        if (row < gridSize - 1) {
            isLightOn[row + 1][col] = !isLightOn[row + 1][col];
            updateButtonColor(row + 1, col);
        }

        if (col < gridSize - 1) {
            isLightOn[row][col + 1] = !isLightOn[row][col + 1];
            updateButtonColor(row, col + 1);
        }

    }
    //check if game is won
    private boolean isGameWon() {
        boolean firstState = isLightOn[0][0];
        for (int row =0; row < gridSize; row++) {
            for (int col =0; col < gridSize; col++)
                if(isLightOn[row][col] != firstState) {

                //if game isn't won  return false
                return false;

            }
        }
        //the lights are all on and the player has won
        return true;
    }

    private void updateButtonColor(int row, int col) {
        if (isLightOn[row][col]) {
            buttons[row][col].setBackgroundColor(Color.WHITE);
        } else {
            buttons[row][col].setBackgroundColor(Color.BLACK);
        }
    }
}

