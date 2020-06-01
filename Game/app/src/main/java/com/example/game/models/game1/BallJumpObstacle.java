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

package com.example.game.models.game1;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;


/**
 * Obstacle class. These are the individual obstacles to be spawned by ObstacleManager
 */
class BallJumpObstacle implements Obstacle {

    /**
     * Instance variables
     */
    private Rect rectangle;
    private int color;

    /**
     * Constructor
     *
     * @param rectHeight - the height of the obstacle
     * @param color      - the color of the obstacle
     * @param startX     - the starting x position of the obstacle
     * @param startY     - the starting y position of the obstacle
     */
    BallJumpObstacle(int rectHeight, int color, int startX, int startY) {
        this.color = color;
        //l,t,r,b
        rectangle = new Rect(startX, startY, startX + 100, startY + rectHeight);
    }

    /**
     * @return - rectangle
     */
    public Rect getRectangle() {
        return rectangle;
    }

    /**
     * @param y - Move obstacle vertical position by an increment of y
     */
    @Override
    public void incrementY(float y) {
        rectangle.top += y;
        rectangle.bottom += y;
    }

    /**
     * @param player - RectPlayer player to be checked collision with
     * @return - return true iff player's rectangle intersects with obstacles rectangle
     */
    @Override
    public boolean playerCollide(RectPlayer player) {
        return Rect.intersects(rectangle, player.getRectangle());
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(rectangle, paint);
    }

    @Override
    public void update() {

    }
}
