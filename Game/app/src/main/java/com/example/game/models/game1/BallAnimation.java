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

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * BallAnimation class. Handles the animation of the player-controlled ball in the game.
 */
class BallAnimation implements Animation {

    // === Instance Variables ===

    private Bitmap[] frames; // The images.
    private int frameIndex; // The index associated with frames.
    private boolean isPlaying = false; // Whether or not the game is playing.
    private float frameTime; // The time in between frames.
    private long lastFrame; // Last frame of the animation

    /**
     * Creates a new Animation.
     *
     * @param frames   the images.
     * @param animTime the animation time.
     */
    BallAnimation(Bitmap[] frames, float animTime) {
        this.frames = frames;
        frameIndex = 0;
        frameTime = animTime / frames.length;
        lastFrame = System.currentTimeMillis();
    }

    /**
     * A getter method for isPlaying.
     */
    @Override
    public boolean isPlaying() {
        return isPlaying;
    }

    /**
     * Sets isPlaying to true, frameIndex to 0, and lastFrame to the current time in milliseconds.
     */
    @Override
    public void play() {
        isPlaying = true;
        frameIndex = 0;
        lastFrame = System.currentTimeMillis();
    }

    /**
     * Sets isPlaying to false.
     */
    @Override
    public void stop() {
        isPlaying = false;
    }

    /**
     * Draws this animation on the canvas.
     *
     * @param canvas      the canvas on which to draw.
     * @param destination the rectangle that will be "drawn" on the canvas.
     */
    @Override
    public void draw(Canvas canvas, Rect destination) {
        if (!isPlaying) {
            return;
        }

        scaleRect(destination);

        canvas.drawBitmap(frames[frameIndex], null, destination, new Paint());
    }

    /**
     * Scales the given rectangle for the draw method.
     *
     * @param rect the rectangle to be scaled.
     */
    private void scaleRect(Rect rect) {
        float whRatio = (float) (frames[frameIndex].getWidth()) / frames[frameIndex].getHeight();
        if (rect.width() > rect.height()) {
            rect.left = rect.right - (int) (rect.height() * whRatio);
        } else {
            rect.top = rect.bottom - (int) (rect.width() * (1 / whRatio));
        }
    }

    /**
     * Updates this animation.
     */
    @Override
    public void update() {
        if (!isPlaying) {
            return;
        }

        if (System.currentTimeMillis() - lastFrame > frameTime * 1000) {
            frameIndex++;
            if (frameIndex >= frames.length) {
                frameIndex = 0;
            }
            lastFrame = System.currentTimeMillis();
        }
    }
}
