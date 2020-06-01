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

import com.example.game.models.interfaces.ScoreboardDataRepositoryInterface;

import java.io.File;
import java.util.ArrayList;
import java.util.Stack;

/**
 * A scoreboard
 */
public class Scoreboard {
    final private Stack<Pair<Account, Integer>> rank;

    /**
     * Scoreboard constructor
     */
    Scoreboard(){
        rank = new Stack<>();
    }

    /**
     * @return A scoreboard in a list format, where the index + 1 represents the rank of Account,
     * contained in Pair format, where the first item in the pair is the account
     * and the second is the account score
     */
    ArrayList<Pair<Account, Integer>> getScoreboardList(){
        Stack<Pair<Account, Integer>> tempStack = new Stack<>();
        ArrayList<Pair<Account, Integer>> scoreboard = new ArrayList<>();
        while(!rank.isEmpty()){
            Pair<Account, Integer> currentAccount = rank.pop();
            tempStack.push(currentAccount);
            scoreboard.add(currentAccount);
        }
        while(!tempStack.isEmpty()){
            Pair<Account, Integer> currentAccount = tempStack.pop();
            rank.push(currentAccount);
        }
        return scoreboard;
    }

    /**
     * Inserts the account and score into the scoreboard
     * @param account The account
     * @param score The account's score
     * @param file The file to save data to
     * @param scoreboardDataRepositoryInterface The interface that accesses file
     * @return True iff the account was added to the scoreboard. false otherwise.
     */
    boolean addScore(Account account, int score, File file, ScoreboardDataRepositoryInterface
            scoreboardDataRepositoryInterface){
        Pair<Account, Integer> accountIntegerPair = new Pair<>(account, score);
        int accountRank = findAccountRank(score);
        if(accountRank != 0) {
            placeAccount(accountIntegerPair, accountRank);
            scoreboardDataRepositoryInterface.save(file, this);
            return true;
        } else {
            return false;
        }
    }


    /**
     * Places an account into the scoreboard
     * @param accountIntegerPair The Pair of account and score
     * @param accountRank The rank of the account, once added in the scoreboard
     */
    private void placeAccount(Pair<Account, Integer> accountIntegerPair, int accountRank) {
        Stack<Pair<Account, Integer>> tempStack = new Stack<>();
        while(!rank.isEmpty()){
            Pair<Account, Integer> currentAccount = rank.pop();
            if(accountRank == tempStack.size() + 1){
                tempStack.push(accountIntegerPair);
            }
            if (tempStack.size() < 5){
                tempStack.push(currentAccount);
            }
        }
        if(accountRank == tempStack.size() + 1){
            tempStack.push(accountIntegerPair);
        }
        while(!tempStack.isEmpty()){
            Pair<Account, Integer> currentAccount = tempStack.pop();
            rank.push(currentAccount);
        }
    }

    /**
     * Finds the rank of the account based on a score
     * @param score Account's score
     * @return the rank of the account in the scoreboard
     */
    private int findAccountRank(int score) {
        if(rank.isEmpty()){
            return 1;
        } else if(isScoreHighEnough(score)){
            int count = 0;
            Stack<Pair<Account, Integer>> tempStack = new Stack<>();
            while(!rank.isEmpty()){
                Pair<Account, Integer> currentAccount = rank.pop();
                if(currentAccount.getSecond() < score){
                    count++;
                }
                tempStack.push(currentAccount);
            }
            while(!tempStack.isEmpty()){
                Pair<Account, Integer> currentAccount= tempStack.pop();
                rank.push(currentAccount);
            }
            return rank.size() + 1 - count; //
        }else{
            //score is not high enough to be in the scoreBoard
            return 0;
        }
    }

    /**
     * Finds whether or not the score is high enough to be entered in the scoreboard
     * @param score Account's score
     * @return True iff score can be added to scoreboard, false otherwise.
     */
    private boolean isScoreHighEnough(int score) {
        boolean isScoreHighEnough = false;
        Stack<Pair<Account, Integer>> tempStack = new Stack<>();
        while(!rank.isEmpty()){
            Pair<Account, Integer> currentAccount= rank.pop();
            if(currentAccount.getSecond() < score){
                isScoreHighEnough = true;
            }
            tempStack.push(currentAccount);
        }
        while(!tempStack.isEmpty()){
            Pair<Account, Integer> currentAccount= tempStack.pop();
            rank.push(currentAccount);
        }
        if(rank.size() < 5){
            isScoreHighEnough = true;
        }
        return isScoreHighEnough;
    }


}
