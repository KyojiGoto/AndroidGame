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

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.game.BaseActivity;
import com.example.game.R;
import com.example.game.viewLevel.MainActivity;

public class Game1Activity extends BaseActivity {
    /**
     * The main UI for Game1.


    // === Instance Variables ===

    /**
     * Creates layout that is displayed on screen.
     *
     * @param savedInstanceState the saved instance state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game1);
        ViewFactories.GAME1_VIEW_FACTORY = new Game1ViewFactoryImpl();
        ViewFactories.MAIN_THREAD_FACTORY = new MainThreadFactoryImpl();
        assert BaseActivity.account!= null;
        getWindow().getDecorView().setBackgroundResource(BaseActivity.account.getBackground());
    }

    /**
     * Called when the user taps the "Play" button
     *
     * @param view the view that is connected to this method.
     */
    public void playGameEasy(View view) {
        Intent intent = new Intent(this, BallJumperActivity.class);
        intent.putExtra("difficulty", "easy");
        startActivity(intent);
    }

    public void playGameNormal(View view) {
        Intent intent = new Intent(this, BallJumperActivity.class);
        intent.putExtra("difficulty", "normal");
        startActivity(intent);
    }

    public void playGameHard(View view) {
        Intent intent = new Intent(this, BallJumperActivity.class);
        intent.putExtra("difficulty", "hard");
        startActivity(intent);
    }

    /**
     * Called when the user taps the "To Main Menu" button
     *
     * @param view the view that is connected to this method.
     */
    public void toMainMenu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
