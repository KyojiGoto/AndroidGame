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

package com.example.game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game.models.ActivityDataUseCases;
import com.example.game.models.interfaces.ActivityDataResponseActions;
import com.example.game.presenters.ActivityDataPresenter;
import com.example.game.viewLevel.MainActivity;

public class GameEnd extends AppCompatActivity implements ActivityDataResponseActions {
    /**
     * Text displaying player stats
     */
    private TextView lives, scores;

    private ActivityDataPresenter presenter;

    /**
     * Code to execute when the Activity is created.
     *
     * @param savedInstanceState A Bundle containing possibly previous states of this Activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_end);

        //Customizes the Activity based on User preference.
        getWindow().getDecorView().setBackgroundResource(BaseActivity.account.getBackground());

        lives = findViewById(R.id.livesText_GameEnd);
        lives.setText(String.valueOf(BaseActivity.account.getHitPoints()));

        scores = findViewById(R.id.scoreText_GameEnd);
        scores.setText(String.valueOf(BaseActivity.account.getCurrentScore()));

        presenter = new ActivityDataPresenter(this, new ActivityDataUseCases());
    }

    public void resetAndMoveTo(View view) {
        presenter.resetDataValues(getApplicationContext().getFilesDir());
    }

    @Override
    public void reactToReset() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
