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

package com.example.game.viewLevel.game1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.game.BaseActivity;
import com.example.game.R;
import com.example.game.models.DataIncrementerUseCases;
import com.example.game.models.interfaces.DataIncrementerActions;
import com.example.game.presenters.DataIncrementerPresenter;
import com.example.game.viewLevel.MainActivity;
import com.example.game.viewLevel.game2.Game2Activity;

/**
 * GameOverActivity class. This is what the player will see when they lose.
 */
public class GameOverActivity extends BaseActivity implements DataIncrementerActions {

    /**
     * Text displaying the addiction counter (so the number of games played including retries)
     */
    private TextView lives, scores;

    /**
     * Presenter which manages interactions with this view.
     */
    private DataIncrementerPresenter presenter;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        // Score Labels
        TextView scoreLabel = findViewById(R.id.scoreLabel);
        TextView highScoreLabel = findViewById(R.id.highScoreLabel);

        // Get score from players session
        int score = getIntent().getIntExtra("SCORE", 0);

        // Change scoreLabel text to show what player got after session
        scoreLabel.setText("Score : " + score);

        // Saving high scores
        SharedPreferences settings = getSharedPreferences("HIGH_SCORE", Context.MODE_PRIVATE);
        int highScore = settings.getInt("HIGH_SCORE", 0);

        // If score is greater than highScore update highScore
        if (score > highScore) {
            highScoreLabel.setText("High Score : " + score);

            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("HIGH_SCORE", score);
            editor.apply();

        } else {
            highScoreLabel.setText("High Score : " + highScore);
        }

        assert account != null;
        getWindow().getDecorView().setBackgroundResource(account.getBackground());

        lives = findViewById(R.id.livesText_GameOverActivity);
        lives.setText(String.valueOf(account.getHitPoints()));

        scores = findViewById(R.id.scoreText_GameOverActivity);
        scores.setText(String.valueOf(account.getCurrentScore()));

        presenter = new DataIncrementerPresenter(this,
                new DataIncrementerUseCases());
    }

    /**
     * Called when the user taps the "Retry" button
     */
    public void retry(View view) {
        presenter.decrementLevel(getApplicationContext().getFilesDir());
    }

    /**
     * Called when the user taps the "To Game Two" button
     */
    public void nextGame(View view) {
        presenter.incrementLevel(getApplicationContext().getFilesDir());
    }

    /**
     * Called when the user taps the "To Main Menu" button
     */
    public void toMainMenu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void toRetry() {
        Intent intent = new Intent(this, BallJumperActivity.class);
        String difficultyLevel = getIntent().getStringExtra("difficulty");
        if (difficultyLevel == null){
            difficultyLevel = "normal";
        }
        intent.putExtra("difficulty", difficultyLevel);
        startActivity(intent);
    }

    @Override
    public void toNext() {
        Intent intent = new Intent(this, Game2Activity.class);
        startActivity(intent);
    }
}
