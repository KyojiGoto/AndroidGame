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

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * A class that manages all the drawing if the games objects.
 */
public class DrawManager {

    /**
     * The canvas to draw the objects on.
     */
    private Canvas canvas;

    /**
     * Getter for the canvas.
     *
     * @return canvas : The canvas to draw the objects on.
     */
    public Canvas getCanvas() {
        return canvas;
    }

    /**
     * Setter for the canvas.
     *
     * @param canvas : The canvas to draw the objects on.
     */
    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    /**
     * The paint to use to draw with.
     */
    private Paint paint;

    /**
     * Constructor.
     *
     * @param canvas The canvas to draw the objects on.
     * @param paint  The paint to use to draw with.
     */
    DrawManager(Canvas canvas, Paint paint) {
        this.canvas = canvas;
        this.paint = paint;
    }

    /**
     * Drawing a character object.
     *
     * @param sprite The Bitmap to draw.
     * @param x      The x position to place the sprite at.
     * @param y      The y position to place the sprite at.
     */
    public void drawCharacterObject(Bitmap sprite, int x, int y) {
        canvas.drawBitmap(sprite, x, y, paint);
    }


    /**
     * Drawing a health bar object.
     *
     * @param healthLevel The level of the health bar.
     * @param color       The color of the health bar.
     * @param textSize    The size of text to draw with.
     * @param playerName  The name of the player who's health bar this is.
     * @param x           The x position to draw at.
     * @param y           The y position to draw at.
     */
    public void drawHealthBarObject(int healthLevel, int color, int textSize, String playerName,
                                    int x, int y) {
        paint.setColor(color);
        paint.setTextSize(textSize);
        canvas.drawText(playerName.toUpperCase() + healthLevel, x, y, paint);
    }

    /**
     * Drawing a button object.
     *
     * @param btnColor  The color of the button.
     * @param textColor The color to draw the text with.
     * @param button    The outline of the button draw.
     * @param btnName   The name of the button.
     * @param x         The x position to place the button at.
     * @param y         The y position to place the button at.
     */
    public void drawButtonObject(int btnColor, int textColor, Rect button, String btnName,
                                 int x, int y) {
        paint.setColor(btnColor);
        canvas.drawRect(button, paint);
        paint.setColor(textColor);
        canvas.drawText(btnName, x, y, paint);
    }

    /**
     * Drawing a move text object.
     *
     * @param textColor The color to draw the text with.
     * @param moveText  The text to draw.
     * @param x         The x position to draw at.
     * @param y         The y position to draw at.
     */
    public void drawMoveTextObject(int textColor, String moveText, int x, int y) {
        paint.setColor(textColor);
        canvas.drawText(moveText, x, y, paint);
    }

    /**
     * Drawing a bottle object.
     *
     * @param sprite The Bitmap to draw.
     * @param x      The x position to place the sprite at.
     * @param y      The y position to place the sprite at.
     */
    public void drawBottleObject(Bitmap sprite, int x, int y) {
        canvas.drawBitmap(sprite, x, y, paint);
    }
}
