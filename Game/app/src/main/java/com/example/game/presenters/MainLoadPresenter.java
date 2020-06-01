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

package com.example.game.presenters;

import com.example.game.models.MainLoadUseCases;
import com.example.game.models.interfaces.LoadGameActions;
import com.example.game.models.interfaces.LoadGameListener;

import java.io.File;

/**
 * Interacts between the class which loads the games and its use cases.
 */
public class MainLoadPresenter implements LoadGameListener {

    /**
     * The the class which loads the game.
     */
    private LoadGameActions loadGameActions;

    /**
     * The use case class.
     */
    private MainLoadUseCases mainLoadUseCases;

    public MainLoadPresenter(LoadGameActions loadGameActions, MainLoadUseCases mainLoadUseCases){
        this.loadGameActions = loadGameActions;
        this.mainLoadUseCases = mainLoadUseCases;
    }

    /**
     * Delegates performing actions related to starting the first game to the Model
     * @param contextFile the file the database is saved at
     */
    public void game1(File contextFile) {
        mainLoadUseCases.game1(this, contextFile);
    }

    /**
     * Delegates performing actions related to starting the second game to the Model
     * @param contextFile the file the database is saved at
     */
    public void game2(File contextFile) {
        mainLoadUseCases.game2(this, contextFile);
    }

    /**
     * Delegates performing actions related to starting the third game to the Model
     * @param contextFile the file the database is saved at
     */
    public void game3(File contextFile) {
        mainLoadUseCases.game3(this, contextFile);
    }

    /**
     * Delegates performing actions related to resuming the game to the Model
     * @param contextFile the file the database is saved at
     */
    public void resume(File contextFile){
        mainLoadUseCases.resume(this, contextFile);
    }

    @Override
    public void directToGame1() {
        loadGameActions.toGame1();
    }

    @Override
    public void directToGame2() {
        loadGameActions.toGame2();
    }

    @Override
    public void directToGame3() {
        loadGameActions.toGame3();
    }

    @Override
    public void directToAfterGame1(){
        loadGameActions.toAfterGame1();
    }

    @Override
    public void directToAfterGame3() {
        loadGameActions.toAfterGame3();
    }
}
