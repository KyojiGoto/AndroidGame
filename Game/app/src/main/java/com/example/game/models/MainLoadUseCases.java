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

package com.example.game.models;

import com.example.game.models.interfaces.LoadGameListener;

import java.io.File;

import static com.example.game.BaseActivity.account;

public class MainLoadUseCases {
    /**
     * Performs actions related to starting the first game
     * @param contextFile the file the database is saved at
     */
    public void game1(LoadGameListener listener, File contextFile) {
        account.resetValues(contextFile);
        listener.directToGame1();
    }

    /**
     * Performs actions related to starting the second game
     * @param contextFile the file the database is saved at
     */
    public void game2(LoadGameListener listener, File contextFile) {
        while (account.getLastAttemptedLevel() != 2){
            account.incrementLevel(contextFile);
        }
        listener.directToGame2();
    }

    /**
     * Performs actions related to starting the third game
     * @param contextFile the file the database is saved at
     */
    public void game3(LoadGameListener listener, File contextFile) {
        while (account.getLastAttemptedLevel() != 3){
            account.incrementLevel(contextFile);
        }
        listener.directToGame3();
    }

    /**
     * Performs actions related to resuming the game
     * @param contextFile the file the database is saved at
     */
    public void resume(LoadGameListener listener, File contextFile){
        int level = account.getLastAttemptedLevel();

        switch (level){
            case 0:
                listener.directToGame1();
                break;
            case 1:
                listener.directToAfterGame1();
                break;
            case 2:
                listener.directToGame2();
                break;
            case 3:
                listener.directToGame3();
                break;
            case 4:
                listener.directToAfterGame3();
                break;
        }
    }
}
