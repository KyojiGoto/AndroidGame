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

package com.example.game.presenters.game3;

import android.content.Context;

/**
 * An interface that represents the facade for Game3Presenter.
 */
public interface Game3PresenterFacade {

    /**
     * Creates all the game objects.
     */
    void initializeGameObjects();

    /**
     * Draws all the game objects.
     */
    void draw();

    /**
     * Updates all the game objects.
     */
    void update();

    /**
     * Reads the user touch input.
     *
     * @param touchX The x position the user touched on the screen.
     * @param touchY The y position the user touched on the screen.
     */
    void readTouch(float touchX, float touchY);

    /**
     * Checks the winner of the game.
     *
     * @return The winner of the game.
     */
    String checkWinner();

    /**
     * Gets number of moves to win/lose the game.
     *
     * @return The number of moves to win/lose the game.
     */
    int getNumMoves();

    /**
     * Gets the score of the game.
     *
     * @return The score of the game.
     */
    int getHitPoints();

    /**
     * Gets the wait time to pause the game to allow for animations.
     *
     * @return The wait time to pause the game to allow for animations
     */
    int getWaitTime();

    /**
     * Determines whether it's the player's turn or the enemy's turn.
     *
     * @return Whether it's the player's turn or the enemy's turn.
     */
    boolean getTurn();

    /**
     * Updates the score of the game.
     *
     * @return The score of the game.
     */
    int updateHitpoints();

    /**
     * Determines whether game 3 has ended or not.
     *
     * @return Whether game 3 has ended or not.
     */
    boolean gameEnded(Context context);

}
