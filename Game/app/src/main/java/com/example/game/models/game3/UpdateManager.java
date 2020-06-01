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
import android.graphics.Color;
import android.util.DisplayMetrics;

import com.example.game.R;

/**
 * A class that updates the Game.
 */
public class UpdateManager {

    /**
     * Additional resources for the game.
     */
    private final Resources res;

    /**
     * A class that manages the statistics of the game.
     */
    private final StatsManager statsManager;

    /**
     * Checks whether the Player decides to attack or not.
     */
    private Boolean attack = false;

    /**
     * Checks whether the Player decides to defend or not.
     */
    private Boolean defend = false;

    /**
     * Decides whether to animate or not.
     */
    private boolean animate;

    /**
     * The current frame the animation is on.
     */
    private int animateFrame = 0;

    /**
     * The time to pause the game for to allow for animations.
     */
    private int waitTime = 0;

    /**
     * Getter for the wait time.
     *
     * @return waitTime : The time to pause the game for.
     */
    public int getWaitTime() {
        return waitTime;
    }

    /**
     * The damage the player's health takes.
     */
    private int hpDamage = 0;

    /**
     * The strategy employed for this game.
     */
    private Game3Strategy gameStrategy;

    /**
     * Checks who's turn it is. (Player or Enemy).
     */
    private Boolean isTurn = true;

    /**
     * The width of the screen.
     */
    private final int screenWidth;

    /**
     * The class that manages all the objects in the game.
     */
    private final GameObjectManager gameObjectManager;

    /**
     * Getter for the turn.
     *
     * @return isTurn : Checks who's turn it is. (Player or Enemy).
     */
    public Boolean getTurn() {
        return isTurn;
    }

    /**
     * Constructor.
     *
     * @param res               Additional resources for the game.
     * @param gameObjectManager The class that manages all the objects in the game.
     * @param gameDifficulty    The difficulty level for this game.
     * @param statsManager      A class that manages the statistics of the game.
     */
    public UpdateManager(GameObjectManager gameObjectManager, Resources res, String gameDifficulty,
                         StatsManager statsManager) {
        this.gameObjectManager = gameObjectManager;
        this.res = res;
        DisplayMetrics display = res.getDisplayMetrics();
        screenWidth = display.widthPixels;
        if (gameDifficulty.equals("easy")) {
            this.gameStrategy = new Game3EasyStrategy();
        } else if (gameDifficulty.equals("normal")) {
            this.gameStrategy = new Game3NormalStrategy();
        } else {
            this.gameStrategy = new Game3HardStrategy();
        }
        this.statsManager = statsManager;
    }

    /**
     * Updates the GameObjects that require updates.
     */
    public void update() {
        gameObjectManager.getHealthPotion().update(screenWidth);
        if (isTurn) {
            //Prints the Damage the enemy did to the player.
            gameObjectManager.getMoveTextObject().update(res.getString(R.string.player_took) + hpDamage +
                    res.getString(R.string.damage), Color.RED);
            gameObjectManager.getAttackButtonObject().setActive(true);
            gameObjectManager.getDefendButtonObject().setActive(true);
        } else {
            //deactivates the Attack and Defend button briefly.
            gameObjectManager.getAttackButtonObject().setActive(false);
            gameObjectManager.getDefendButtonObject().setActive(false);
            int damage = 0;
            if (!animate) {
                if (attack) {
                    damage = gameStrategy.playerAttack(gameObjectManager.getHealthPotion());
                    hpDamage = gameStrategy.enemyAttack();
                    attack = false;
                }
                if (defend) {
                    damage = gameStrategy.playerDefend();
                    hpDamage = gameStrategy.enemyDefend();
                    defend = false;
                }
                //Updates the damage done to the enemy based on the employed game logic.
                gameObjectManager.getEnemyHealth().update(damage);

                //Prints the damage done to the enemy.
                gameObjectManager.getMoveTextObject().update(res.getString(R.string.enemy_took) + damage +
                        res.getString(R.string.damage), Color.GREEN);
                //Updates the damage done to the player based on the employed game logic.
                gameObjectManager.getPlayerHealth().update(hpDamage);

            }
            animate = true;
        }
        //Updates the animation of the characters
        if (animate) {
            CharacterObject[] players = new CharacterObject[]{gameObjectManager.getEnemy(),
                    gameObjectManager.getPlayer()};
            animatePlayer(players, 100);

        }
    }

    /**
     * Animates the given characters with the given frame rate.
     *
     * @param characters The characters to animate.
     * @param frameRate  The time to wait to change frames.
     */
    private void animatePlayer(CharacterObject[] characters, int frameRate) {
        animateFrame++;
        //updates the frame of each character.
        for (CharacterObject character : characters) {
            character.update();
        }
        waitTime = frameRate;
        //resets the values when animation is over.
        if (animateFrame == 5) {
            animate = false;
            waitTime = 0;
            animateFrame = 0;
            isTurn = true;
        }
    }

    /**
     * Updates the game based on where the user touched the screen.
     *
     * @param touchX The x position the user touched on the screen.
     * @param touchY The y position the user touched on the screen.
     */
    public void onTouchEventHelper(float touchX, float touchY) {
        if (isTurn) {

            //Checks if the AttackButton was tapped. If so, player's turn is over.
            if (buttonPressed(gameObjectManager.getAttackButtonObject(), touchX, touchY)) {
                attack = true;
                isTurn = false;
                statsManager.setNumMoves(statsManager.getNumMoves() + 1);
            }

            //Checks if the DefendButton was tapped. If so, player's turn is over.
            if (buttonPressed(gameObjectManager.getDefendButtonObject(), touchX, touchY)) {
                defend = true;
                isTurn = false;
                statsManager.setNumMoves(statsManager.getNumMoves() + 1);
            }

        }
        //Checks if the health Potion is pressed. If so, adds 5 HP to player.
        if (bottlePressed(gameObjectManager.getHealthPotion(), touchX, touchY)) {
            if (gameObjectManager.getPlayerHealth().getHealthLevel() > 95) {
                gameObjectManager.getPlayerHealth().setHealthLevel(100);
            } else {
                gameObjectManager.getPlayerHealth().update(-5);
            }
            gameObjectManager.getHealthPotion().setActive(false);
        }
    }

    /**
     * Checks if the given button was pressed.
     *
     * @param buttonObject The button to check if it was pressed.
     * @param touchX       The x position the user touched on the screen.
     * @param touchY       The y position the user touched on the screen.
     * @return Determines if the button was pressed or not.
     */
    private Boolean buttonPressed(ButtonObject buttonObject, float touchX, float touchY) {
        return buttonObject.getButton().left <= touchX && touchX <=
                buttonObject.getButton().right && buttonObject.getButton().top <= touchY &&
                touchY <= buttonObject.getButton().bottom;
    }

    /**
     * Checks if the given bottle was pressed.
     *
     * @param bottleObject The bottle to check if it was pressed.
     * @param touchX       The x position the user touched on the screen.
     * @param touchY       The y position the user touched on the screen.
     * @return Determines if the bottle was pressed or not.
     */
    private Boolean bottlePressed(BottleObject bottleObject, float touchX, float touchY) {
        if (bottleObject.getActive()) {
            return bottleObject.getLeft() <= touchX && touchX <=
                    bottleObject.getRight() && bottleObject.getTop() <= touchY &&
                    touchY <= bottleObject.getBottom();
        } else {
            return false;
        }
    }

    /**
     * Updates the hitpoints after the game is done.
     *
     * @return The score of the game.
     */
    public int updateHitpoints() {
        if (gameObjectManager.getPlayerHealth().getHealthLevel() == 0 &&
                gameObjectManager.getEnemyHealth().getHealthLevel() != 0) {
            // no change in score if the player lost.
            statsManager.setHitPoints(0);
        } else {
            //Score is the remaining health left
            statsManager.setHitPoints(gameObjectManager.getPlayerHealth().getHealthLevel());
        }
        return statsManager.getHitPoints();
    }

}
