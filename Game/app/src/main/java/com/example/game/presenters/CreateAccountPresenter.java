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

import com.example.game.models.CreateAccountUseCases;
import com.example.game.models.interfaces.CreateAccountActions;
import com.example.game.models.interfaces.CreateAccountListener;

import java.io.File;

/**
 * Presenter layer for account creations which communicates between the UI and Use Cases.
 */
public class CreateAccountPresenter implements CreateAccountListener {
    /**
     * UI which this presenter interacts with.
     */
    private CreateAccountActions createAccountActions;

    /**
     * Use cases which directly interact with information.
     */
    private CreateAccountUseCases createAccountUseCases;

    /**
     * Creates a new CreateAccountPresenter
     * @param createAccountActions actions which the UI can perform
     * @param createAccountUseCases interacts directly with the data
     */
    public CreateAccountPresenter(CreateAccountActions createAccountActions,
                           CreateAccountUseCases createAccountUseCases){
        this.createAccountActions = createAccountActions;
        this.createAccountUseCases = createAccountUseCases;
    }

    /**
     * Tells the use cases to check over this login.
     * @param username the entered in username for this login
     * @param contextFile the location of the application
     */
    public void createAccount(String username, File contextFile){
        createAccountUseCases.createAccount(username, contextFile, this);
    }

    /**
     * Actions when account creation has failed.
     */
    @Override
    public void accountCreationFailed() {
        createAccountActions.accountCreationFailed();
    }

    /**
     * Actions on account creation success.
     */
    @Override
    public void accountCreationSuccess() {
        createAccountActions.accountCreationSuccess();
    }
}
