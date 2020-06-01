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

import com.example.game.models.AccountHolder;
import com.example.game.models.LoginUseCases;
import com.example.game.models.interfaces.AccountDataRepositoryInterface;
import com.example.game.models.interfaces.LoginActions;
import com.example.game.models.interfaces.LoginListener;

import java.io.File;

/**
 * Presenter for LoginActivity. It communicates with LoginActivity to determine actions related
 * to logging in.
 */
public class LoginPresenter implements LoginListener {

    /**
     * UI which this presenter interacts with.
     */
    private LoginActions loginActions;

    /**
     * Use cases which directly interact with information.
     */
    private LoginUseCases loginUseCases;

    public LoginPresenter(LoginActions loginActions, LoginUseCases loginUseCases){
        this.loginActions = loginActions;
        this.loginUseCases = loginUseCases;
    }


    @Override
    public void incorrectUsername() {
        loginActions.incorrectUsername();
    }

    @Override
    public void correctUsername(AccountHolder accountHolder) {
        loginActions.moveToMainMenu(accountHolder);
    }

    /**
     * Tells the use cases to check over this login
     * @param username the entered in username for this login
     * @param contextFile the location of the application
     * @param accountDataRepository the interface which accesses the database
     */
    public void login(String username, File contextFile, AccountDataRepositoryInterface accountDataRepository){
        loginUseCases.login(username, contextFile, this, accountDataRepository);
    }
}
