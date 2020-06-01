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
 * A Move Text Game 3 Object class that displays the Move a Character makes
 */
class MoveTextObject extends Game3Object {

    /**
     * The color of the Text to display.
     */
    private int textColor;

    /**
     * The Text to display.
     */
    private String moveText;

    /**
     * Getter for the the color of the Text to display.
     *
     * @return textColor : The color of the Text to display.
     */
    int getTextColor() {
        return textColor;
    }

    /**
     * Setter for the the color of the Text to display.
     *
     * @param textColor The color of the Text to display.
     */
    void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    /**
     * Getter for the Text to display.
     *
     * @return moveText : The Text to display.
     */
    String getMoveText() {
        return moveText;
    }

    /**
     * Setter for the Text to display.
     *
     * @param moveText : The Text to display.
     */
    void setMoveText(String moveText) {
        this.moveText = moveText;
    }

    /**
     * Overridden method that updates the Move Text object.
     */
    @Override
    void update() {

    }

    /**
     * Overloaded method for update. Updates the text to display (the move the Character makes).
     *
     * @param displayText The text to display (the move the Character makes).
     * @param color       The color of the text to display.
     */
    void update(String displayText, int color) {
        setTextColor(color);
        setMoveText(displayText);

    }

}
