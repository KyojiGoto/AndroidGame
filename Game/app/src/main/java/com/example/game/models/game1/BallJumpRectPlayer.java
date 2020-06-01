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
import android.graphics.Point;
import android.graphics.Rect;

/**
 * RectPlayer class. The actual player the user will control.
 */
class BallJumpRectPlayer implements RectPlayer {

    /**
     * Instance variables
     */
    private Rect rectangle;
    private AnimationManager animManager;

    /**
     * Constructor
     *
     * @param rectangle - the players rectangle
     */
    BallJumpRectPlayer(Rect rectangle) {
        this.rectangle = rectangle;
        AnimationManagerFactory animationManagerFactory = ModelFactories.ANIMATION_MANAGER_FACTORY;
        animManager = animationManagerFactory.makeAnimationManagerImpl();
    }

    /**
     * @return - this players rectangle
     */
    @Override
    public Rect getRectangle() {
        return rectangle;
    }

    @Override
    public void draw(Canvas canvas) {
        // Paint paint = new Paint();
        // paint.setColor(color);
        // canvas.drawRect(rectangle, paint);
        animManager.draw(canvas, rectangle);
    }

    public void update() {
        animManager.update();
    }

    /**
     * @param point - Take in coordinates and move player to those coordinates
     */
    @Override
    public void update(Point point) {
        float oldLeft = rectangle.left;

        rectangle.set(point.x - rectangle.width() / 2, point.y - rectangle.height() / 2, point.x + rectangle.width() / 2, point.y + rectangle.height() / 2);

        int state = 0;
        if (rectangle.left - oldLeft > 5) {
            state = 1;
        } else if (rectangle.left - oldLeft < -5) {
            state = 2;
        }

        animManager.playAnim(state);
        animManager.update();
    }
}
