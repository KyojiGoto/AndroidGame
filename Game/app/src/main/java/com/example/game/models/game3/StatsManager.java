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

package com.example.game.models.game3;

/**
 * A class that manages the statistics for the game.
 */
public class StatsManager {

    /**
     * The score of the game.
     */
    private int hitPoints;

    /**
     * Getter for the score of the game.
     *
     * @return hitPoints : The score of the game.
     */
    public int getHitPoints() {
        return hitPoints;
    }

    /**
     * Setter for the score of the game.
     *
     * @param hitPoints : The score of the game.
     */
    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    /**
     * The number of moves the player took to win/lose.
     */
    private int numMoves;

    /**
     * Getter for the number of moves.
     *
     * @return numMoves : The number of moves the player took to win/lose.
     */
    public int getNumMoves() {
        return numMoves;
    }

    /**
     * Setter for the number of moves.
     *
     * @param numMoves : The number of moves the player took to win/lose.
     */
    public void setNumMoves(int numMoves) {
        this.numMoves = numMoves;
    }

}
