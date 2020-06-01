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

import com.example.game.models.interfaces.AccountManagerInterface;
import com.example.game.models.interfaces.CreateAccountListener;

import java.io.File;

/**
 * Performs the use cases of account creation
 */
public class CreateAccountUseCases {
    /**
     * Determines the existence of a users account on the system and creates new accounts
     */
    private AccountManagerInterface accountManager;

    /**
     * Creates a new CreateAccountUseCases and initializes the Account Manager
     */
    public CreateAccountUseCases(AccountManagerInterface accountManager){
        this.accountManager = accountManager;
    }

    /**
     * Determines whether a username exists or does not exist (correct or incorrect).
     * @param username is the entered in username for an attempted login
     * @param contextFile the location of the application
     * @param createAccountListener will react to whatever result is concluded from the return of
     *                     accountManager
     */
    public void createAccount(final String username, File contextFile,
                       CreateAccountListener createAccountListener){
        Account account = accountManager.openExistingAccount(username, contextFile);
        if(account != null){
            createAccountListener.accountCreationFailed();
        } else {
            accountManager.createNewAccount(username, contextFile);
            createAccountListener.accountCreationSuccess();
        }
    }
}
