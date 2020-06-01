/*
 * MIT License
 *
 * Copyright (c) 2019 Chirag Rana, Clifton Sahota, Kyoji Goto, Jason Liu, Ruemu Digba, Stanislav
 * Chirikov
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.example.game.viewLevel.game3;

import android.content.Intent;
import android.os.Bundle;

import com.example.game.BaseActivity;
import com.example.game.GameOver;

/**
 * Play Activity for Game 3.
 */
public class Game3PlayActivity extends BaseActivity {

    /**
     * Game Loop for this Game.
     */
    private Game3View game3View;

    /**
     * Code to execute when the Activity is created.
     *
     * @param savedInstanceState A Bundle containing possibly previous states of this Activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Creates view for Game 3.
        super.onCreate(savedInstanceState);
        String difficulty = getIntent().getStringExtra("DIFFICULTY");
        game3View = new Game3View(this, difficulty);
        setContentView(game3View);

        //Customizes the Activity based on User preference.
        getWindow().getDecorView().setBackgroundResource(account.getBackground());
    }

    /**
     * Handles cases when Activity is paused.
     */
    @Override
    protected void onPause() {
        super.onPause();
        game3View.pause();
    }

    /**
     * Handles cases when Activity is resumed.
     */
    @Override
    protected void onResume() {
        super.onResume();
        game3View.resume();
    }

    /**
     * Handles the case when the game is done.
     *
     * @param winner   The winner of the game.
     * @param numMoves The number of moves the Player played.
     */
    protected void gameOver(String winner, int numMoves) {
        Intent intent = new Intent(this, Game3ExitActivity.class);
        intent.putExtra("EXTRA_WINNER", winner);
        intent.putExtra("EXTRA_MOVES", numMoves);

        if (account.getHitPoints() <= 0) {
            //Ran out of lives
            intent = new Intent(this, GameOver.class);
        }
        startActivity(intent);
    }

    /**
     * Handles cases when Activity is stopped.
     */
    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
