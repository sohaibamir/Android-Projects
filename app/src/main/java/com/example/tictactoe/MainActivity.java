package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    boolean gameActive = true;
    //For Players:
    // 0 -> X
    // 1 -> O
    int activePlayer = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    //Game State:
    // 0 -> X
    // 1 -> O
    // 2 -> Null
    int [][] winPositions = {{0,1,2}, {3,4,5}, {6,7,8},
                             {0,4,8}, {2,4,6}, {0,3,6},
                             {1,4,7}, {2,5,8}};

    public void playerTap(View view){
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());
        if(!gameActive){
            gameReset(view);
        }
        if(gameState[tappedImage] == 2 && gameActive)
        {
            gameState[tappedImage] = activePlayer;
            img.setTranslationY(-1000f);
            if (activePlayer == 0)
            {
                img.setImageResource(R.drawable.x);
                activePlayer = 1;
                TextView textView2 = findViewById(R.id.textView2);
                textView2.setText("O's Turn - Tap to play");
            }
            else
            {
                img.setImageResource(R.drawable.o);
                activePlayer = 0;
                TextView textView2 = findViewById(R.id.textView2);
                textView2.setText("X's Turn - Tap to play");
            }
            img.animate().translationYBy(1000f).setDuration(150);
        }
        //Checking if player has won
        for(int[] elements : winPositions){
            if(gameState[elements[0]] == gameState[elements[1]] && gameState[elements[1]] == gameState[elements[2]]
            && gameState[elements[0]] != 2){
                //Someone has won - Find out who
                String winner;
                gameActive = false;
                if(gameState[elements[0]] == 0){
                    winner = "X has won";
                }
                else{
                    winner = "O has won";
                }
                //Update textView for the winner
                TextView textView2 = findViewById(R.id.textView2);
                textView2.setText(winner);
            }
        }
        boolean EmptySquare = false;
        for(int state : gameState){
            if (state == 2){
                EmptySquare = true;
                break;
            }
        }
        if(!EmptySquare && gameActive){
            //game is a draw
            gameActive = false;
            String winner = "No one won";
            TextView textView2 = findViewById(R.id.textView2);
            textView2.setText(winner);
        }
    }
    public void gameReset(View view){
        gameActive = true;
        activePlayer = 0;
        for (int i = 0; i < gameState.length; i++){
            gameState[i] = 2;
        }
        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView9)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}