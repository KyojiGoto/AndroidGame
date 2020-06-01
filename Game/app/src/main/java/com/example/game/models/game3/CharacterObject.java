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

import android.graphics.Bitmap;

/**
 * Character Object class. Subclass of abstract class Game3Object.
 */
class CharacterObject extends Game3Object {

    /**
     * The physical appearance of the Character as a Bitmap.
     */
    private Bitmap sprite;

    /**
     * The Bitmaps representing each frame of the Character when Animated.
     */
    private Bitmap[] spriteAnimate = new Bitmap[4];

    /**
     * Setter for spriteAnimate.
     *
     * @param spriteAnimate The Bitmaps representing each frame of the Character when Animated.
     */
    void setSpriteAnimate(Bitmap[] spriteAnimate) {
        this.spriteAnimate = spriteAnimate;
    }

    /**
     * The current frame the animation is on.
     */
    private int spriteFrame = 0;

    /**
     * Getter for the Character sprite.
     *
     * @return sprite : The physical appearance of the Character.
     */
    Bitmap getSprite() {
        return sprite;
    }

    /**
     * Setter for the Character sprite.
     *
     * @param sprite The physical appearance of the Character.
     */
    void setSprite(Bitmap sprite) {
        this.sprite = sprite;
    }

    /**
     * Overrides update for the CharacterObject from GameObject class. Changes the frame of the
     * animation.
     */
    @Override
    void update() {
        if (spriteFrame == 4) {
            spriteFrame = 0;
        } else {
            spriteFrame++;
        }
        sprite = spriteAnimate[spriteFrame];
    }
}
