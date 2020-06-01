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

package com.example.game.presenters.game1;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;

import com.example.game.models.game1.Constants;
import com.example.game.models.game1.ModelFactories;
import com.example.game.models.game1.Obstacle;
import com.example.game.models.game1.ObstacleManager;
import com.example.game.models.game1.ObstacleManagerFactory;
import com.example.game.models.game1.OrientationData;
import com.example.game.models.game1.OrientationDataFactory;
import com.example.game.models.game1.RectPlayer;
import com.example.game.models.game1.RectPlayerFactory;
import com.example.game.viewLevel.game1.BallJumperActivity;

import java.util.ArrayList;

/**
 * GameplayScene class. Handles drawing & updating the objects, controls, and game over
 */
class GameplayScene implements Scene {

    /**
     * Instance Variables
     */
    private RectPlayer player; // player object
    private Point playerPoint; // player coordinates
    private ObstacleManager obstacleManager; // obstacle spawner
    private boolean movingPlayer = false; // check whether player is moving
    private boolean gameOver = false; // check whether the game is over
    private int score; // Score for the game
    private int lives; // Lives for the game
    private double grav; // gravity for game
    private int hitPoints;
    private String difficulty;
    private ObstacleManagerFactory obstacleManagerFactory;
    // Allow for tilt controls
    private OrientationData orientationData;
    private long frameTime;

    /**
     * Constructor for GameplayScene. Instansiates player, playerPoint, obstacles, and lives.
     */
    GameplayScene() {
        RectPlayerFactory rectPlayerFactory = ModelFactories.RECT_PLAYER_FACTORY;
        player = rectPlayerFactory.makeBallJumpRectPlayer(new Rect(100, 100, 200, 200));
        playerPoint = new Point(Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT / 4);
        player.update(playerPoint);
        obstacleManagerFactory = ModelFactories.OBSTACLE_MANAGER_FACTORY;
        obstacleManager = obstacleManagerFactory.makeObstacleManagerImpl(1000, 75, Color.BLACK);
        lives = 3;
        // Initialize for tilt controls
        OrientationDataFactory orientationDataFactory = ModelFactories.ORIENTATION_DATA_FACTORY;
        orientationData = orientationDataFactory.makeOrientationDataImp();
        orientationData.register();
        frameTime = System.currentTimeMillis();
    }

    @Override
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
        obstacleManager.setDifficulty(difficulty);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        player.draw(canvas); // draw player
        obstacleManager.draw(canvas); // draw obstacles
        Paint paint = new Paint();
        paint.setTextSize(100);
        paint.setColor(Color.MAGENTA);
        // Draw score
        canvas.drawText("" + score, 50, 50 + paint.descent() - paint.ascent(), paint);
        // Draw lives
        paint.setColor(Color.GREEN);
        canvas.drawText("Lives: " + lives, Constants.SCREEN_WIDTH / 2, 50 + paint.descent() - paint.ascent(), paint);
        paint.setColor(Color.BLACK);
        canvas.drawText(new String(new char[Constants.SCREEN_WIDTH]).replace("\0", "^"), 0, (float) 0.95 * Constants.SCREEN_HEIGHT, paint);
        canvas.drawText(new String(new char[Constants.SCREEN_WIDTH]).replace("\0", "v"), 0, (float) 0.013 * Constants.SCREEN_HEIGHT, paint);
    }

    @Override
    public void update() {
        if (!gameOver) {

            // Move player based on how controller tilts their device
            if(frameTime < Constants.INIT_TIME)
                frameTime = Constants.INIT_TIME;
            int elapsedTime = (int)(System.currentTimeMillis() - frameTime);
            frameTime = System.currentTimeMillis();
            if(orientationData.getOrientation() != null && orientationData.getStartOrientation() != null) {
                float roll = orientationData.getOrientation()[2];

                float xSpeed = 2 * roll * Constants.SCREEN_WIDTH/1000f;

                playerPoint.x += Math.abs(xSpeed*elapsedTime) > 5 ? xSpeed*elapsedTime : 0;
            }

            // Keep player within boundaries
            if (playerPoint.x < 0) {
                playerPoint.x = 0;
            } else if (playerPoint.x > Constants.SCREEN_WIDTH) {
                playerPoint.x = Constants.SCREEN_WIDTH;
            }

            if (playerPoint.y < 0) {
                grav = 0.5;
                gameOver = true;
                lives--;
                // If player has no lives go to GameOverActivity
                if (lives == 0) {
                    ((BallJumperActivity) Constants.CURRENT_CONTEXT).gameOver(score, hitPoints, difficulty);
                } else {
                    reset();
                }
            }
            // If player falls off screen lose a life
            else if (playerPoint.y > Constants.SCREEN_HEIGHT) {
                grav = 0.5;
                gameOver = true;
                lives--;
                // If player has no lives go to GameOverActivity
                if (lives == 0) {
                    ((BallJumperActivity) Constants.CURRENT_CONTEXT).gameOver(score, hitPoints, difficulty);
                } else {
                    reset();
                }
            }

            ArrayList<Obstacle> obstacles = obstacleManager.getObstacles();
            // If obstacle goes off screen remove it, then add to hitPoints
            if (obstacles.get(obstacles.size() - 1).getRectangle().bottom <= 0) {
                obstacles.remove(obstacles.size() - 1);
                hitPoints++;
            }

            obstacleManager.update();

            if (obstacleManager.playerCollide(player)) {
                obstacles.remove(Constants.hitTile);
                obstacleManager.addObstacle();
                score++;
                grav = 0;
                playerPoint.y -= grav;
                grav -= 25;
                player.update(playerPoint);
            } else {
                playerPoint.y += grav;
                grav += 1;
                player.update(playerPoint);
            }
        }
    }

    @Override
    public void receiveTouch(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (!gameOver) {
                    movingPlayer = true;
                    playerPoint.set((int) event.getX(), playerPoint.y);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (!gameOver && movingPlayer) {
                    playerPoint.set((int) event.getX(), playerPoint.y);
                }
                break;
            case MotionEvent.ACTION_UP:
                movingPlayer = false;
                break;
        }
    }





    /**
     * Reset whenever player dies
     */
    private void reset() {
        playerPoint = new Point(Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT / 4);
        player.update(playerPoint);
        obstacleManager = obstacleManagerFactory.makeObstacleManagerImpl(1000, 75, Color.BLACK);
        obstacleManager.setDifficulty(difficulty);
        movingPlayer = false;
        gameOver = false;
    }
}