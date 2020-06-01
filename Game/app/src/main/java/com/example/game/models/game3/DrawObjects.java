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

import com.example.game.presenters.game3.DrawManager;

/**
 * A class that draws objects.
 */
public class DrawObjects {

    /**
     * The manager class that manages all the game objects.
     */
    private final GameObjectManager gameObjectManager;

    /**
     * The manager class that draws all the objects.
     */
    private final DrawManager drawManager;

    /**
     * Constructor.
     *
     * @param gameObjectManager The manager class that manages all the game objects.
     * @param drawManager       The manager class that draws all the objects.
     */
    public DrawObjects(GameObjectManager gameObjectManager, DrawManager drawManager) {
        this.gameObjectManager = gameObjectManager;
        this.drawManager = drawManager;
    }

    /**
     * Draws all the Game 3 Objects on given canvas.
     */
    public void draw() {
        drawCharacter(gameObjectManager.getEnemy());
        drawCharacter(gameObjectManager.getPlayer());
        drawButton(gameObjectManager.getAttackButtonObject());
        drawButton(gameObjectManager.getDefendButtonObject());
        drawHealth(gameObjectManager.getEnemyHealth());
        drawHealth(gameObjectManager.getPlayerHealth());
        drawMoveText(gameObjectManager.getMoveTextObject());
        drawBottleObject(gameObjectManager.getHealthPotion());
    }

    /**
     * Draws the given character.
     *
     * @param character A character.
     */
    private void drawCharacter(CharacterObject character) {
        drawManager.drawCharacterObject(character.getSprite(), character.getX(), character.getY());
    }

    /**
     * Draws the given health bar.
     *
     * @param playerHealth Health Bar of a player.
     */
    private void drawHealth(HealthBarObject playerHealth) {
        drawManager.drawHealthBarObject(playerHealth.getHealthLevel(), playerHealth.getColor(),
                playerHealth.getTextSize(), playerHealth.getPlayerName(), playerHealth.getX(),
                playerHealth.getY());
    }

    /**
     * Draws the given button.
     *
     * @param button A button.
     */
    private void drawButton(ButtonObject button) {
        if (button.isActive()) {
            drawManager.drawButtonObject(button.getBtnColor(), button.getTextColor(),
                    button.getButton(), button.getBtnName(), button.getX(), button.getY());
        }
    }

    /**
     * Draws the given text.
     *
     * @param moveText Text showing the moves of the players.
     */
    private void drawMoveText(MoveTextObject moveText) {
        drawManager.drawMoveTextObject(moveText.getTextColor(), moveText.getMoveText(),
                moveText.getX(), moveText.getY());
    }

    /**
     * Draws the given bottle.
     *
     * @param bottle A bottle.
     */
    private void drawBottleObject(BottleObject bottle) {
        if (bottle.getActive()) {
            drawManager.drawBottleObject(bottle.getSprite(), bottle.getX(), bottle.getY());
        }
    }
}
