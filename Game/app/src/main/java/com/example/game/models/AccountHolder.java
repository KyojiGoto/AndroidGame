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

import java.io.File;

/**
 * Account holder layer which holds an Account and an AccountDataRepositoryInterface. It's purpose
 * is to pass the AccountDataRepositoryInterface to the Account for use in it's methods.
 *
 * Note: This class is necessary due to the way that the data is saved/loaded for the Account, as
 * the AccountDataRepositoryInterface is an interface and can't be directly instantiated.
 */
public class AccountHolder{
    /**
     * The account held by the account holder. It is the currently logged in account.
     */
    private Account account;

    /**
     * The Object with methods that directly access and edit the database.
     */
    private AccountDataRepositoryInterface accountDataRepositoryInterface;

    /**
     * Instantiates and returns a new AccountHolder.
     * @param account the logged in account.
     * @param accountDataRepositoryInterface the interface which allows for accessing data from
     *                                       the database.
     */
    AccountHolder(Account account, AccountDataRepositoryInterface accountDataRepositoryInterface){
        this.account = account;
        this.accountDataRepositoryInterface = accountDataRepositoryInterface;
    }

    /**
     * @return the held Account's login (which is a string).
     */
    public String getLogin() {
        return account.getLogin();
    }

    /**
     * Sets this held account's background colour to selected colour and records it
     * @param colour the number that respresents a background colour for this account
     * @param contextFile an access to the current file state of the app
     */
    public void setBackground(String colour, File contextFile) {
        account.setBackground(colour, contextFile, accountDataRepositoryInterface);
    }

    /**
     * @return the held Accounts background colour setting.
     */
    public int getBackground(){
        return account.getBackground();
    }

    /**
     * Sets this held account's language and records the setting
     * @param language the number that respresents a language for this account
     * @param contextFile an access to the current file state of the app
     */
    public void setLanguage(String language, File contextFile) {
        account.setLanguage(language, contextFile, accountDataRepositoryInterface);
    }

    /**
     * @return the held Account's language setting.
     */
    public String getLanguage(){
        return account.getLanguage();
    }


    /**
     * Sets this held account's icon
     * @param icon the icon setting
     * @param contextFile an access to the current file state of the app
     */
    public void setIcon(String icon, File contextFile) {
        account.setIcon(icon, contextFile, accountDataRepositoryInterface);
    }

    /**
     * @return the held Account's icon
     */
    public int getIcon(){
        return account.getIcon();
    }


    /**
     * Increments level or resets it where appropriate and records it on the held account
     * @param contextFile an access to the current file state of the app
     */
    public void incrementLevel(File contextFile) {
        account.incrementLevel(contextFile, accountDataRepositoryInterface);
    }

    /**
     * Decrements level for purposes of retrying where appropriate and records it on the held
     * account
     * @param contextFile an access to the current file state of the app
     */
    public void decrementLevel(File contextFile) {
        account.decrementLevel(contextFile, accountDataRepositoryInterface);
    }

    /**
     * Reduces hitpoints by a set amount and records it on the held account
     * @param reduce the amount by which hit points are reduced
     * @param contextFile an access to the current file state of the app
     */
    public void decrementHitPoints(int reduce, File contextFile) {
        account.decrementHitPoints(reduce, contextFile, accountDataRepositoryInterface);
    }

    /**
     * Changes score by amount add on the held account
     * @param add the amount to be added to the score
     * @param contextFile an access to the current file state of the app
     */
    public void incrementScore(int add, File contextFile) {
        account.incrementScore(add, contextFile, accountDataRepositoryInterface);
    }

    /**
     * Increments the number of times the games are played on this held account
     * @param contextFile an access to the current file state of the app
     */
    public void incrementGamesPlayed(File contextFile) {
        account.incrementGamesPlayed(contextFile, accountDataRepositoryInterface);
    }

    /**
     * Sets this held account's stats to starting ones
     * @param contextFile an access to the current file state of the app
     */
    public void resetValues(File contextFile){
        account.resetValues(contextFile, accountDataRepositoryInterface);
    }

    /**
     * @return the held account's last level attempted.
     */
    public int getLastAttemptedLevel() {
        return account.getLastAttemptedLevel();
    }

    /**
     * @return the held account's current hit points.
     */
    public int getHitPoints() {
        return account.getHitPoints();
    }

    /**
     * @return the held account's current score.
     */
    public int getCurrentScore() {
        return account.getCurrentScore();
    }

    /**
     * @return the held account's current total number of games played.
     */
    public int getGamesPlayed() {
        return account.getGamesPlayed();
    }

    /**
     * @return the held account.
     */
    public Account getAccount() {
        return account;
    }
}
