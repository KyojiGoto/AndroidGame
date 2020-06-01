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

/** An account */
public class Account{
  /** Account's login, customization settings and save data. */
  final private String login;

  /**
   *  Account's customization
   * */
  final private Customization customization;

  /**
   * Account's save data:
   */
  final private GameData gameData;

  /**
   * Constructor for brand new Account. Activates from Create Account button. Takes login from Enter
   * Login field. Uses default values for customization settings and save data.
   *
   * @param login of the Account.
   */
  public Account(String login) {
    this.login = login;
    this.customization = new Customization();
    this.gameData = new GameData();
  }

  /**
   * @return the Account's login (which is a string).
   */
  public String getLogin() {
    return login;
  }

  /**
   * Sets this account's background colour to selected colour and records it
   * @param colour the number that represents a background colour for this account
   * @param contextFile an access to the current file state of the app
   * @param accountDataRepository the interface which accesses the database
   */
  void setBackground(String colour, File contextFile,
                            AccountDataRepositoryInterface accountDataRepository) {
    customization.setCurrentColour(colour);
    accountDataRepository.save(contextFile, this);
  }

  /**
   * @return the Account's background colour setting.
   */
  int getBackground(){
    return customization.getCurrentColour();
  }

  /**
   * Sets this account's language and records the setting
   * @param language the number that represents a language for this account
   * @param contextFile an access to the current file state of the app
   * @param accountDataRepository the interface which accesses the database
   */
  void setLanguage(String language, File contextFile,
                          AccountDataRepositoryInterface accountDataRepository) {
    customization.setCurrentLanguage(language);
    accountDataRepository.save(contextFile, this);
  }

  /**
   * @return the Account's language setting.
   */
  String getLanguage(){
    return customization.getCurrentLanguage();
  }


  /**
   * Sets this accounts icon
   * @param icon the icon setting
   * @param contextFile an access to the current file state of the app
   * @param accountDataRepository the interface which accesses the database
   */
  void setIcon(String icon, File contextFile,
                      AccountDataRepositoryInterface accountDataRepository) {
    customization.setCurrentIcon(icon);
    accountDataRepository.save(contextFile, this);
  }

  public int getIcon(){
    return customization.getCurrentIcon();
  }


  /**
   * Increments level or resets it where appropriate and records it
   * @param contextFile an access to the current file state of the app
   * @param accountDataRepository the interface which accesses the database
   */
  void incrementLevel(File contextFile,
                             AccountDataRepositoryInterface accountDataRepository) {
    gameData.incrementLevel();
    accountDataRepository.save(contextFile, this);
  }

  /**
   * Decrements level for purposes of retrying where appropriate and records it
   * @param contextFile an access to the current file state of the app
   * @param accountDataRepository the interface which accesses the database
   */
  void decrementLevel(File contextFile,
                             AccountDataRepositoryInterface accountDataRepository) {
    gameData.decrementLevel();
    accountDataRepository.save(contextFile, this);
  }

  /**
   * Reduces hitpoints by a set amount and records it
   * @param reduce the amount by which hit points are reduced
   * @param contextFile an access to the current file state of the app
   * @param accountDataRepository the interface which accesses the database
   */
  void decrementHitPoints(int reduce, File contextFile,
                                 AccountDataRepositoryInterface accountDataRepository) {
    gameData.decrementHitPoints(reduce);
    accountDataRepository.save(contextFile, this);
  }

  /** 
   * Changes score by amount add
   * @param add the amount to be added to the score
   * @param contextFile an access to the current file state of the app
   * @param accountDataRepository the interface which accesses the database
   */
  void incrementScore(int add, File contextFile,
                             AccountDataRepositoryInterface accountDataRepository) {
    gameData.incrementScore(add);
    accountDataRepository.save(contextFile, this);
  }

  /**
   * Increments the number of times the games are played on this account
   * @param contextFile an access to the current file state of the app
   * @param accountDataRepository the interface which accesses the database
   */
  void incrementGamesPlayed(File contextFile,
                                   AccountDataRepositoryInterface accountDataRepository) {
    gameData.incrementGamesPlayed();
    accountDataRepository.save(contextFile, this);
  }

  /**
   * Sets this account's stats to starting ones
   * @param contextFile an access to the current file state of the app
   * @param accountDataRepository the interface which accesses the database
   */
  void resetValues(File contextFile, AccountDataRepositoryInterface accountDataRepository){
    gameData.resetData();
    accountDataRepository.save(contextFile, this);
  }

  /**
   * @return the account's last attempted level.
   */
  int getLastAttemptedLevel() {
    return gameData.getLastAttemptedLevel();
  }

  /**
   * @return the account's current hit points.
   */
  int getHitPoints() {
    return gameData.getHitPoints();
  }

  /**
   * @return the account's current score.
   */
  int getCurrentScore() {
    return gameData.getCurrentScore();
  }

  /**
   * @return the account's current number of games played.
   */
  int getGamesPlayed() {
    return gameData.getGamesPlayed();
  }
}
