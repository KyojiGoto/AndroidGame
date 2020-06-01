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

/**
 * The game statistic data for an account.
 */
class GameData {
    /**
     * Account's save data:
     * */
    private int lastAttemptedLevel;
    private int hitPoints;
    private int currentScore;
    private int gamesPlayed;

    GameData(){
        lastAttemptedLevel = 0;
        hitPoints = 200;
        currentScore = 0;
        gamesPlayed = 0;
    }

    /**
     * @return the last attempted level.
     */
    int getLastAttemptedLevel() {
        return lastAttemptedLevel;
    }

    /**
     * @return the hit points.
     */
    int getHitPoints() {
        return hitPoints;
    }

    /**
     * @return the current score.
     */
    int getCurrentScore() {
        return currentScore;
    }

    /**
     * @return the number of games played.
     */
    int getGamesPlayed() {
        return gamesPlayed;
    }


    /**
     * Increments level or resets it where appropriate and records it
     */
    void incrementLevel() {
        if (lastAttemptedLevel < 4){
            lastAttemptedLevel += 1;
        } else {
            lastAttemptedLevel = 0;
        }
    }

    /**
     * Decrements level for purposes of retrying where appropriate and records it
     */
    void decrementLevel() {
        if (lastAttemptedLevel > 0){
            lastAttemptedLevel -= 1;
        }
    }

    /**
     * Reduces hitpoints by a set amount and records it
     * @param reduce the amount by which hit points are reduced
     */
    void decrementHitPoints(int reduce) {
        hitPoints -= reduce;
    }

    /**
     * Changes score by amount add
     * @param add the amount to be added to the score
     */
    void incrementScore(int add) {
        currentScore += add;
    }

    /**
     * Increments the number of times the games are played on this account
     */
    void incrementGamesPlayed() {
        gamesPlayed += 1;
    }

    /**
     * Sets this account's stats to starting ones
     */
    void resetData(){
        lastAttemptedLevel = 0;
        hitPoints = 200;
        currentScore = 0;
    }
}
