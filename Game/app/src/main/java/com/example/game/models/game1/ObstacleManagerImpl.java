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

import java.util.ArrayList;

/**
 * ObstacleManager class. Creates obstacles to be populated in game.
 */
class ObstacleManagerImpl implements ObstacleManager {
    // higher index = lower on screen = higher y value

    /**
     * Instance variables
     */
    private ArrayList<Obstacle> obstacles;
    private int obstacleGap;
    private int obstacleHeight;
    private int color;
    private long startTime;
    private String difficulty;
    private ObstacleFactory obstacleFactory;

    /**
     * Constructor for ObstacleManager
     *
     * @param obstacleGap    - gap between spawning obstacles
     * @param obstacleHeight - height of obstacles
     * @param color          - color of obstacles
     */
    ObstacleManagerImpl(int obstacleGap, int obstacleHeight, int color) {
        this.obstacleGap = obstacleGap;
        this.obstacleHeight = obstacleHeight;
        this.color = color;
        obstacles = new ArrayList<>();
        obstacleFactory = ModelFactories.OBSTACLE_FACTORY;
        populateObstacles();
    }

    @Override
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * @param player - RectPlayer player to check collision with any of the obstacles
     * @return - true iff any obstacle in obstacles collides with player
     */
    @Override
    public boolean playerCollide(RectPlayer player) {
        for (Obstacle ob : obstacles) {
            if (ob.playerCollide(player)) {
                Constants.hitTile = ob;
                return true;
            }
        }
        return false;
    }

    /**
     * Add obstacles below each other to bottom of the screen.
     */
    private void populateObstacles() {
        int currY = Constants.SCREEN_HEIGHT * 2;

        while (currY > Constants.SCREEN_HEIGHT) {
            int xStart = (int) (Math.random() * (Constants.SCREEN_WIDTH - 100));
            obstacles.add(obstacleFactory.makeBallJumpObstacle(obstacleHeight, color, xStart, currY));
            currY -= obstacleHeight + obstacleGap;
        }
    }

    @Override
    public void addObstacle() {
        int xStart = (int) (Math.random() * (Constants.SCREEN_WIDTH - 100));
        obstacles.add(0, obstacleFactory.makeBallJumpObstacle(obstacleHeight, color, xStart, Constants.SCREEN_HEIGHT));
    }

    /**
     * Loop through ArrayList of obstacles and move them. Check if obstacle is off screen on the
     * top, then add new obstacle objects to the bottom.
     */
    @Override
    public void update() {
        if (startTime < Constants.INIT_TIME) {
            startTime = Constants.INIT_TIME;
        }
        int elapseTime = (int) (System.currentTimeMillis() - startTime);
        startTime = System.currentTimeMillis();

        if (difficulty.equals("easy")) {
            for (Obstacle ob : obstacles) {
                ob.incrementY(-1 * elapseTime);
            }
        } else if (difficulty.equals("normal")) {
            for (Obstacle ob : obstacles) {
                ob.incrementY(-2 * elapseTime);
            }
        } else {
            for (Obstacle ob : obstacles) {
                ob.incrementY(-3 * elapseTime);
            }
        }

        if (obstacles.get(obstacles.size() - 1).getRectangle().bottom <= 0) {
            addObstacle();
        }
    }

    /**
     * Getter for list of obstacles
     **/
    @Override
    public ArrayList<Obstacle> getObstacles() {
        return obstacles;
    }

    /**
     * @param canvas - draw to Canvas canvas
     */
    @Override
    public void draw(Canvas canvas) {
        for (Obstacle ob : obstacles) {
            ob.draw(canvas);
        }
    }
}
