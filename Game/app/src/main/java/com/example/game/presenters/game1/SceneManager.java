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
import android.view.MotionEvent;

import java.util.ArrayList;

/**
 * SceneManager class. Manages the Scene
 */
public class SceneManager implements Game1Presenter {
    /**
     * Instance variables
     */
    private ArrayList<Scene> scenes = new ArrayList<>();
    private static int ACTIVE_SCENE;

    /**
     * Constructor. Adds GameplayScene to list of scenes. In phase2, more scenes will be added which
     * means ore levels.
     */
    SceneManager() {
        ACTIVE_SCENE = 0;
        SceneFactory sceneFactory = PresenterFactories.SCENE_FACTORY;
        scenes.add(sceneFactory.makeGameplayScene());
    }

    @Override
    public void setDifficulty(String difficulty) {
        for (Scene s : scenes) {
            s.setDifficulty(difficulty);
        }
    }

    /**
     * Handles the events for player interaction with phone screen
     *
     * @param event - event of the interaction with the screen (i.e., touch screen, let go of
     *              screen, etc.)
     */
    @Override
    public void receiveTouch(MotionEvent event) {
        scenes.get(ACTIVE_SCENE).receiveTouch(event);
    }

    /**
     * Update scene
     */
    @Override
    public void update() {
        scenes.get(ACTIVE_SCENE).update();
    }

    /**
     * Draw the screen
     *
     * @param canvas - draw onto Canvas canvas
     */
    @Override
    public void draw(Canvas canvas) {
        scenes.get(ACTIVE_SCENE).draw(canvas);
    }
}
