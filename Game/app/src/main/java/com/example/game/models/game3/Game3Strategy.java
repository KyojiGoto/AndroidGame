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
 * An interface that represents the game logic.
 */
interface Game3Strategy {

    /**
     * The enemy's response to a player choosing attack.
     */
    int enemyAttack();

    /**
     * The enemy's response to a player choosing defend.
     */
    int enemyDefend();

    /**
     * The player choosing attack. Also decides whether a health potion should be activated here.
     *
     * @param healthPotion A health potion that increases player HP.
     */
    int playerAttack(BottleObject healthPotion);

    /**
     * The player choosing to defend.
     */
    int playerDefend();
}
