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

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.util.DisplayMetrics;

import com.example.game.R;

/**
 * A class that creates all the objects to be displayed on the screen.
 */
class ObjectBuilder {

    /**
     * The Resources needed to access some files in creating objects.
     */
    private Resources res;

    /**
     * The height of the screen.
     */
    private int screenHeight;
    /**
     * The width of the screen.
     */
    private int screenWidth;

    /**
     * Constructor.
     *
     * @param res The Resources needed to access some files in creating objects.
     */
    ObjectBuilder(Resources res) {
        DisplayMetrics display = res.getDisplayMetrics();
        screenHeight = display.heightPixels;
        screenWidth = display.widthPixels;
        this.res = res;
    }

    /**
     * Creates a Character for the Player. 1 Bitmap split into 5 frames and resized.
     *
     * @return player : The character represented by the user.
     */
    CharacterObject createPlayer() {
        CharacterObject player = new CharacterObject();
        Bitmap[] sprites = new Bitmap[5];
        Matrix m = new Matrix();
        m.preScale(1, 1);
        Bitmap characterSprite = BitmapFactory.decodeResource(res, R.drawable.charactersprite);
        int w = characterSprite.getWidth() / 5;
        Bitmap f11 = Bitmap.createBitmap(characterSprite, 0, 0, w,
                characterSprite.getHeight(), m, false);
        Bitmap f22 = Bitmap.createBitmap(characterSprite, w, 0, w, characterSprite.getHeight(),
                m, false);
        Bitmap f33 = Bitmap.createBitmap(characterSprite, 2 * w, 0, w,
                characterSprite.getHeight(), m, false);
        Bitmap f44 = Bitmap.createBitmap(characterSprite, 3 * w, 0, w,
                characterSprite.getHeight(), m, false);
        Bitmap f55 = Bitmap.createBitmap(characterSprite, 4 * w, 0, w,
                characterSprite.getHeight(), m, false);
        Bitmap resizedBitmap1 = Bitmap.createScaledBitmap(f11, 400, 400,
                false);
        Bitmap resizedBitmap2 = Bitmap.createScaledBitmap(f22, 400, 400,
                false);
        Bitmap resizedBitmap3 = Bitmap.createScaledBitmap(f33, 400, 400,
                false);
        Bitmap resizedBitmap4 = Bitmap.createScaledBitmap(f44, 400, 400,
                false);
        Bitmap resizedBitmap5 = Bitmap.createScaledBitmap(f55, 400, 400,
                false);

        sprites[0] = resizedBitmap1;
        sprites[1] = resizedBitmap2;
        sprites[2] = resizedBitmap3;
        sprites[3] = resizedBitmap4;
        sprites[4] = resizedBitmap5;

        player.setSpriteAnimate(sprites);
        player.setSprite(sprites[0]);
        player.setX(screenWidth / 10);
        player.setY(3 * screenHeight / 5);
        return player;
    }

    /**
     * Creates a Character for the Enemy. 1 Bitmap split into 5 frames and resized.
     *
     * @return player : The character represented by the computer.
     */
    CharacterObject createEnemy() {
        CharacterObject enemy = new CharacterObject();
        Bitmap[] sprites = new Bitmap[5];
        Matrix m = new Matrix();
        m.preScale(1, 1);
        Bitmap enemySprite = BitmapFactory.decodeResource(res, R.drawable.enemysprite);
        int w = enemySprite.getWidth() / 5;
        Bitmap f11 = Bitmap.createBitmap(enemySprite, 0, 0, w, enemySprite.getHeight(),
                m, false);
        Bitmap f22 = Bitmap.createBitmap(enemySprite, w, 0, w, enemySprite.getHeight(), m,
                false);
        Bitmap f33 = Bitmap.createBitmap(enemySprite, 2 * w, 0, w, enemySprite.getHeight(),
                m, false);
        Bitmap f44 = Bitmap.createBitmap(enemySprite, 3 * w, 0, w, enemySprite.getHeight(),
                m, false);
        Bitmap f55 = Bitmap.createBitmap(enemySprite, 4 * w, 0, w, enemySprite.getHeight()
                , m, false);
        Bitmap resizedBitmap1 = Bitmap.createScaledBitmap(f11, 400, 400,
                false);
        Bitmap resizedBitmap2 = Bitmap.createScaledBitmap(f22, 400, 400,
                false);
        Bitmap resizedBitmap3 = Bitmap.createScaledBitmap(f33, 400, 400,
                false);
        Bitmap resizedBitmap4 = Bitmap.createScaledBitmap(f44, 400, 400,
                false);
        Bitmap resizedBitmap5 = Bitmap.createScaledBitmap(f55, 400, 400,
                false);
        sprites[0] = resizedBitmap5;
        sprites[1] = resizedBitmap4;
        sprites[2] = resizedBitmap3;
        sprites[3] = resizedBitmap2;
        sprites[4] = resizedBitmap1;
        enemy.setSpriteAnimate(sprites);
        enemy.setSprite(sprites[0]);
        enemy.setX(3 * screenWidth / 5);
        enemy.setY(3 * screenHeight / 5);
        return enemy;
    }

    /**
     * Creates a Health Bar for the Enemy. Red text. Placed top right of screen.
     *
     * @return enemyHealth : The health bar for the enemy.
     */
    HealthBarObject createEnemyHealthBar() {
        HealthBarObject enemyHealth = new HealthBarObject();
        enemyHealth.setX(3 * screenWidth / 5);
        enemyHealth.setY(screenHeight / 10);
        enemyHealth.setColor(Color.RED);
        enemyHealth.setPlayerName(res.getString(R.string.enemy) + res.getString(R.string.hp));
        enemyHealth.setTextSize(50);
        return enemyHealth;
    }

    /**
     * Creates a Health Bar for the Player. Green text. Placed top left of screen.
     *
     * @return playerHealth : The health bar for the player.
     */
    HealthBarObject createPlayerHealthBar() {
        HealthBarObject playerHealth = new HealthBarObject();
        playerHealth.setX(screenWidth / 10);
        playerHealth.setY(screenHeight / 10);
        playerHealth.setColor(Color.GREEN);
        playerHealth.setPlayerName(res.getString(R.string.player) + res.getString(R.string.hp));
        playerHealth.setTextSize(50);
        return playerHealth;
    }

    /**
     * Creates a Attack ButtonObject. Gray background. White Text. Placed at bottom left.
     *
     * @return attackButtonObject : The attack button.
     */
    ButtonObject createAttackButton() {
        ButtonObject attackButtonObject = new ButtonObject();
        attackButtonObject.setButton(new Rect(0, 4 * screenHeight / 5,
                screenWidth / 2, screenHeight));
        attackButtonObject.setBtnColor(Color.GRAY);
        attackButtonObject.setTextColor(Color.WHITE);
        attackButtonObject.setBtnName(res.getString(R.string.attack));
        attackButtonObject.setX(screenWidth / 10);
        attackButtonObject.setY(9 * screenHeight / 10);
        return attackButtonObject;
    }

    /**
     * Creates a Defend ButtonObject. Gray background. White Text. Placed at bottom right.
     *
     * @return defendButton : The defend button.
     */
    ButtonObject createDefendButton() {
        ButtonObject defendButtonObject = new ButtonObject();
        defendButtonObject.setButton(new Rect(screenWidth / 2, 4 * screenHeight / 5,
                screenWidth, screenHeight));
        defendButtonObject.setBtnColor(Color.GRAY);
        defendButtonObject.setTextColor(Color.WHITE);
        defendButtonObject.setBtnName(res.getString(R.string.defend));
        defendButtonObject.setX(7 * screenWidth / 10);
        defendButtonObject.setY(9 * screenHeight / 10);
        return defendButtonObject;
    }

    /**
     * Creates a Text to show Player and Enemy moves. Initially white text. Placed in the middle.
     *
     * @return moveTextObject : The text showing the moves of the characters.
     */
    MoveTextObject createMoveText() {
        MoveTextObject moveTextObject = new MoveTextObject();
        moveTextObject.setTextColor(Color.WHITE);
        moveTextObject.setMoveText("");
        moveTextObject.setX(screenWidth / 4);
        moveTextObject.setY(2 * screenHeight / 10);
        return moveTextObject;
    }

    /**
     * Creates a health potion BottleObject. Appears initially near lower middle of screen at the
     * left.
     *
     * @return healthPotion : Bottle that gives the user additional HP when activated.
     */
    BottleObject createHealthPotion() {
        BottleObject healthPotion = new BottleObject();
        Bitmap hpBottle = BitmapFactory.decodeResource(res, R.drawable.healthpotion);
        Bitmap sizedHpBottle = Bitmap.createScaledBitmap(hpBottle, 200, 200,
                false);
        healthPotion.setX(10);
        healthPotion.setY(screenHeight / 3);
        healthPotion.setSprite(sizedHpBottle);
        healthPotion.setActive(false);
        return healthPotion;
    }
}
