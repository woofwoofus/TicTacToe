package com.example.jgeestman.tictactoe;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;



import java.util.Arrays;

public class TicTacToe extends Activity {

    protected int turn;
    protected int[] totVisited;
    protected boolean game;
    protected int counter;
//    protected int[] p1Visited;
//    protected int[] p2Visited;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe);

        setGame();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tic_tac_toe, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setGame(View View) {
        setGame();
    }

    public void setGame() {
        counter = 0;
        turn = 1;
        totVisited = new int[9];
        Arrays.fill(totVisited, 0);
        game = true;
        ((TextView)findViewById(R.id.WinnerBox)).setText(" ");
        findViewById(R.id.ReplayButton).setVisibility(View.INVISIBLE);
        emptyButtons();
    }

    public void emptyButtons() {
        Resources res = getResources();

        findViewById(R.id.B1).setBackground(res.getDrawable(R.drawable.empty, null));
        findViewById(R.id.B2).setBackground(res.getDrawable(R.drawable.empty, null));
        findViewById(R.id.B3).setBackground(res.getDrawable(R.drawable.empty, null));
        findViewById(R.id.B4).setBackground(res.getDrawable(R.drawable.empty, null));
        findViewById(R.id.B5).setBackground(res.getDrawable(R.drawable.empty, null));
        findViewById(R.id.B6).setBackground(res.getDrawable(R.drawable.empty, null));
        findViewById(R.id.B7).setBackground(res.getDrawable(R.drawable.empty, null));
        findViewById(R.id.B8).setBackground(res.getDrawable(R.drawable.empty, null));
        findViewById(R.id.B9).setBackground(res.getDrawable(R.drawable.empty, null));

    }

    public void buttonHandler(View view) {
        if (game) {
            Resources res = getResources();
            Object tag = view.getTag();
            String numButton = tag.toString();
            int loc = Integer.parseInt(numButton);
            if (totVisited[loc] == 0) {
                if (turn == 1) {
                    view.setBackground(res.getDrawable(R.drawable.circle, null));
                    totVisited[loc] = 1;
                    turn = 2;
                } else {
                    view.setBackground(res.getDrawable(R.drawable.square, null));
                    totVisited[loc] = 2;
                    turn = 1;
                }
                counter++;
                checkWinner();
            }
        }
    }

    private void checkWinner() {
        int winner = getWinner();
        if (winner > 0) {
            ((TextView) findViewById(R.id.WinnerBox)).setText("Player " + winner + " is the winner!");
            game = false;
            findViewById(R.id.ReplayButton).setVisibility(View.VISIBLE);
        } else if (counter == 9) {
            ((TextView) findViewById(R.id.WinnerBox)).setText("It's a tie!");
            game = false;
            findViewById(R.id.ReplayButton).setVisibility(View.VISIBLE);
        }
    }

    private int getWinner() {
        int winner = 0;
        if (totVisited[0] == totVisited[1] && totVisited[1] == totVisited[2]) {
            winner = totVisited[0];
            if (winner>0) return winner;
        } if (totVisited[3] == totVisited[4] && totVisited[4] == totVisited[5]){
            winner = totVisited[3];
            if (winner>0) return winner;
        } if (totVisited[6] == totVisited[7] && totVisited[7] == totVisited[8]){
            winner = totVisited[6];
            if (winner>0) return winner;
        } if (totVisited[0] == totVisited[3] && totVisited[3] == totVisited[6]){
            winner = totVisited[0];
            if (winner>0) return winner;
        } if (totVisited[1] == totVisited[4] && totVisited[4] == totVisited[7]){
            winner = totVisited[1];
            if (winner>0) return winner;
        } if (totVisited[2] == totVisited[5] && totVisited[5] == totVisited[8]){
            winner = totVisited[2];
            if (winner>0) return winner;
        } if (totVisited[0] == totVisited[4] && totVisited[4] == totVisited[8]){
            winner = totVisited[0];
            if (winner>0) return winner;
        } if (totVisited[2] == totVisited[4] && totVisited[4] == totVisited[6]){
            winner = totVisited[2];
            if (winner>0) return winner;
        }
        return winner;
    }
}
