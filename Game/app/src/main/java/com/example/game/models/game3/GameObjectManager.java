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

/**
 * A Manager class for GameObjects.
 */
public class GameObjectManager {

    /**
     * The Player CharacterObject who will be represented by the User.
     */
    private CharacterObject player;

    /**
     * Getter for player.
     *
     * @return player : A character representing the user.
     */
    public CharacterObject getPlayer() {
        return player;
    }

    /**
     * The Enemy CharacterObject who will be represented by the Computer AI.
     */
    private CharacterObject enemy;

    /**
     * Getter for enemy.
     *
     * @return enemy : A character representing the computer.
     */
    public CharacterObject getEnemy() {
        return enemy;
    }

    /**
     * The Player's Health Bar.
     */
    private HealthBarObject playerHealth;

    /**
     * Getter for player's health bar.
     *
     * @return playerHealth : A health bar for the player.
     */
    public HealthBarObject getPlayerHealth() {
        return playerHealth;
    }

    /**
     * The Enemy's Health Bar.
     */
    private HealthBarObject enemyHealth;

    /**
     * Getter for enemy's health bar.
     *
     * @return enemyHealth : A health bar for the enemy.
     */
    public HealthBarObject getEnemyHealth() {
        return enemyHealth;
    }

    /**
     * The Attack ButtonObject.
     */
    private ButtonObject attackButtonObject;

    /**
     * Getter for the attack button.
     *
     * @return attackButtonObject : the attack button.
     */
    public ButtonObject getAttackButtonObject() {
        return attackButtonObject;
    }

    /**
     * The Defend ButtonObject.
     */
    private ButtonObject defendButtonObject;

    /**
     * Getter for the defend button.
     *
     * @return defendButtonObject : the defend button.
     */
    public ButtonObject getDefendButtonObject() {
        return defendButtonObject;
    }

    /**
     * A BottleObject that increases the player's HP when activated.
     */
    private BottleObject healthPotion;

    /**
     * Getter for the health potion.
     *
     * @return healthPotion : A bottle that increases player's HP when activated.
     */
    public BottleObject getHealthPotion() {
        return healthPotion;
    }

    /**
     * The object that displays the move the players make.
     */
    private MoveTextObject moveTextObject;

    /**
     * Getter for move text.
     *
     * @return moveText : A object that displays the move the players make.
     */
    public MoveTextObject getMoveTextObject() {
        return moveTextObject;
    }

    /**
     * The class that creates all the objects that will be displayed on the screen.
     */
    private ObjectBuilder objectBuilder;

    /**
     * A constructor for GameObjectManager.
     *
     * @param res Resources.
     */
    public GameObjectManager(Resources res) {
        this.objectBuilder = new ObjectBuilder(res);
    }

    /**
     * Creates all the Objects required for this game by calling corresponding methods.
     */
    public void createObjects() {
        player = objectBuilder.createPlayer();
        enemy = objectBuilder.createEnemy();
        enemyHealth = objectBuilder.createEnemyHealthBar();
        playerHealth = objectBuilder.createPlayerHealthBar();
        attackButtonObject = objectBuilder.createAttackButton();
        defendButtonObject = objectBuilder.createDefendButton();
        moveTextObject = objectBuilder.createMoveText();
        healthPotion = objectBuilder.createHealthPotion();
    }

}
