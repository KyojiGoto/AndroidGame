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
import android.view.View;
import android.widget.TextView;

import com.example.game.BaseActivity;
import com.example.game.R;
import com.example.game.viewLevel.MainActivity;

/**
 * Activity Class for Game 3
 */
public class Game3Activity extends BaseActivity {

    /**
     * Text displaying player stats
     */
    TextView lives, scores;

    /**
     * Code to execute when the Activity is created.
     *
     * @param savedInstanceState A Bundle containing possibly previous states of this Activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Sets the view for this Activity.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game3);

        //Customizes the Activity based on User preference.
        assert BaseActivity.account != null;
        getWindow().getDecorView().setBackgroundResource(BaseActivity.account.getBackground());

        lives = findViewById(R.id.livesText_Game3Activity);
        lives.setText(String.valueOf(BaseActivity.account.getHitPoints()));

        scores = findViewById(R.id.scoreText_Game3Activity);
        scores.setText(String.valueOf(BaseActivity.account.getCurrentScore()));
    }

    /**
     * Called when the user taps the "Main Menu" button. Takes user to Main Menu.
     *
     * @param view The View of the Activity.
     */
    public void toMainMenu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * Called when the user taps the "Easy" button. Takes user to Game 3 in easy mode.
     *
     * @param view The View of the Activity.
     */
    public void playGameEasy(View view) {
        Intent intent = new Intent(this, Game3PlayActivity.class);
        intent.putExtra("DIFFICULTY", "easy");
        startActivity(intent);
    }

    /**
     * Called when the user taps the "Normal" button. Takes user to Game 3 in normal mode.
     *
     * @param view The View of the Activity.
     */
    public void playGameNormal(View view) {
        Intent intent = new Intent(this, Game3PlayActivity.class);
        intent.putExtra("DIFFICULTY", "normal");
        startActivity(intent);
    }

    /**
     * Called when the user taps the "Hard" button. Takes user to Game 3 in hard mode.
     *
     * @param view The View of the Activity.
     */
    public void playGameHard(View view) {
        Intent intent = new Intent(this, Game3PlayActivity.class);
        intent.putExtra("DIFFICULTY", "hard");
        startActivity(intent);
    }
}
