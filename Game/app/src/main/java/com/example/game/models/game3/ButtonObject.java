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

import android.graphics.Rect;

/**
 * A class for Button.
 */
class ButtonObject extends Game3Object {

    /**
     * The physical representation of the ButtonObject as a rectangle object
     */
    private Rect button;

    /**
     * Boolean that decides if the ButtonObject is active or not.
     */
    private boolean active = true;

    /**
     * The color of the ButtonObject.
     */
    private int btnColor;

    /**
     * The color of the ButtonObject label
     */
    private int textColor;

    /**
     * The label for the ButtonObject
     */
    private String btnName;

    /**
     * A getter for ButtonObject Name.
     *
     * @return btnName : The ButtonObject Name.
     */
    String getBtnName() {
        return btnName;
    }

    /**
     * Setter for ButtonObject Name.
     *
     * @param btnName The ButtonObject Name.
     */
    void setBtnName(String btnName) {
        this.btnName = btnName;
    }

    /**
     * Getter for the TextColor.
     *
     * @return textColor : The color of the ButtonObject Label.
     */
    int getTextColor() {
        return textColor;
    }

    /**
     * Setter for TextColor
     *
     * @param textColor : The color of the ButtonObject Label.
     */
    void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    /**
     * Getter for ButtonObject Color.
     *
     * @return btnColor : the color of the ButtonObject.
     */
    int getBtnColor() {
        return btnColor;
    }

    /**
     * Setter for ButtonObject Color.
     *
     * @param color The color of the ButtonObject.
     */
    void setBtnColor(int color) {
        this.btnColor = color;
    }

    /**
     * Getter for ButtonObject active.
     *
     * @return active : Checks if ButtonObject is active.
     */
    boolean isActive() {
        return active;
    }

    /**
     * Setter for ButtonObject active.
     *
     * @param active Sets the button active or not.
     */
    void setActive(boolean active) {
        this.active = active;
    }


    /**
     * Getter for physical representation of the ButtonObject.
     *
     * @return button : The physical representation of the ButtonObject.
     */
    Rect getButton() {
        return button;
    }

    /**
     * Setter for the physical representation of the ButtonObject.
     *
     * @param button The physical representation of the ButtonObject.
     */
    void setButton(Rect button) {
        this.button = button;
    }

    /**
     * Overrides update for the ButtonObject from GameObject class.
     */
    @Override
    void update() {

    }
}
