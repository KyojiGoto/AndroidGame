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

package com.example.game.models;

import com.example.game.models.interfaces.AccountDataRepositoryInterface;
import com.example.game.models.interfaces.AccountManagerInterface;
import com.example.game.models.interfaces.LoginListener;

import java.io.File;

/**
 * Class which performs the use cases for logging in with accounts
 */
public class LoginUseCases {
    /**
     * Interface for an account manager like object.
     */
    private AccountManagerInterface accountManager;

    public LoginUseCases(AccountManagerInterface accountManager){
        this.accountManager = accountManager;
    }

    /**
     * Determines whether a username exists or does not exist (correct or incorrect).
     * @param username is the entered in username for an attempted login
     * @param contextFile the location of the application
     * @param loginReactor will react to whatever result is concluded from the return of
     *                     accountManager
     * @param accountDataRepository the interface which accesses the database
     */
    public void login(final String username, File contextFile,
                      LoginListener loginReactor, AccountDataRepositoryInterface accountDataRepository){
        Account account = accountManager.openExistingAccount(username, contextFile);
        if(account != null){
            AccountHolder accountHolder = new AccountHolder(account, accountDataRepository);
            loginReactor.correctUsername(accountHolder);
        } else {
            loginReactor.incorrectUsername();
        }
    }
}
