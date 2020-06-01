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

import static java.lang.StrictMath.max;

/**
 * A Health Bar Game 3 Object class.
 */
class HealthBarObject extends Game3Object {

    /**
     * Color of the Health Bar
     */
    private int color;

    /**
     * The size of the text that displays the Health
     */
    private int textSize;

    /**
     * The name of the Player who is represented by this Health Bar.
     */
    private String playerName;

    /**
     * Current level of the Health Bar, initialized to 100.
     */
    private int healthLevel = 100;

    /**
     * Getter for the health level of the Health Bar.
     *
     * @return healthLevel : The health level of the Health Bar.
     */
    int getHealthLevel() {
        return healthLevel;
    }

    /**
     * Setter for the health level of this Health Bar.
     *
     * @param healthLevel The health level of this Health Bar.
     */
    void setHealthLevel(int healthLevel) {
        this.healthLevel = healthLevel;
    }

    /**
     * Getter for the player name.
     *
     * @return playerName: The name of the Player with this Health Bar.
     */
    String getPlayerName() {
        return playerName;
    }

    /**
     * Setter for player name.
     *
     * @param playerName The name of the Player with this Health Bar.
     */
    void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    /**
     * Getter for the text size that displays the health level.
     *
     * @return textSize : the size of the text that displays the health level.
     */
    int getTextSize() {
        return textSize;
    }

    /**
     * Setter for the text size that displays the health level.
     *
     * @param textSize : the size of the text that displays the health level.
     */
    void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    /**
     * Getter for the color of the Health Bar.
     *
     * @return color : the color of the Health Bar.
     */
    int getColor() {
        return color;
    }

    /**
     * Setter for the color of the Health Bar.
     *
     * @param color The color of the Health Bar.
     */
    void setColor(int color) {
        this.color = color;
    }

    /**
     * Overridden method that updates the Health Bar object.
     */
    @Override
    void update() {

    }

    /**
     * Overloaded method for update. Updates the health level of this Health Bar.
     *
     * @param damage The amount of health to reduce from the health level.
     */
    void update(int damage) {
        setHealthLevel(max(0, getHealthLevel() - damage));
    }
}
