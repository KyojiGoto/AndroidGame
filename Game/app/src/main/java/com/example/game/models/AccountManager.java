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

import java.io.File;

/**
 * Account manager that manages the creation and opening of accounts
 */
public class AccountManager implements AccountManagerInterface {

    /**
     * Object which accesses and edits the database.
     */
    private AccountDataRepositoryInterface accountDataRepository;

    /**
     * Instantiates and returns a new AccountManager
     * @param accountDataRepositoryInterface the object used to edit and recieve information from
     *                                       the database.
     */
    public AccountManager(AccountDataRepositoryInterface accountDataRepositoryInterface){
        accountDataRepository = accountDataRepositoryInterface;
    }

    /**
     * Creates new account with given login. Activates from Create Account button. Takes login from
     * Enter Login field. Uses default values for customization settings and save data. Creates save
     * file if missing.
     *
     * @param login of the Account.
     * @param contextFile the file in which the database is located
     */
    @Override
    public void createNewAccount(String login, File contextFile) {
        accountDataRepository.createNewAccount(login, contextFile);
    }

    /**
     * Creates account that was earlier saved to file. Activates from Select Account button. Takes
     * login from Enter Login field. Uses saved values for customization settings and save data.
     *
     * @param login of the Account.
     * @param contextFile the file in which the database is located
     * @return Array[boolean][Account]: account found => loaded Account, save file or account
     * missing => null.
     */
    @Override
    public Account openExistingAccount(String login, File contextFile) {
        return accountDataRepository.openExistingAccount(login, contextFile);
    }

    /** Deletes all accounts
     * @param contextFile the file in which the database is located
     */
    public void deleteAccountData(File contextFile) {
        accountDataRepository.deleteAccountData(contextFile);
    }
}