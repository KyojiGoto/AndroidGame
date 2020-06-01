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

/**
 * Scoreboard holder layer which holds a Scoreboard and an ScoreboardDataRepositoryInterface. It's
 * purpose is to pass the ScoreboardDataRepositoryInterface to the Scoreboard for use in it's
 * methods.
 *
 * Note: This class is necessary due to the way that the data is saved/loaded for the Scoreboard, as
 * the ScoreboardDataRepositoryInterface is an interface and can't be directly instantiated.
 */
public class ScoreboardHolder {

    /**
     * Scoreboard being held
     */
    final private Scoreboard scoreboard;
    /**
     * ScoreboardDataRepositoryInterface being held
     */
    final private ScoreboardDataRepositoryInterface scoreboardDataRepositoryInterface;

    public ScoreboardHolder(File file, ScoreboardDataRepositoryInterface
            scoreboardDataRepositoryInterface){
        this.scoreboardDataRepositoryInterface = scoreboardDataRepositoryInterface;

        if(this.scoreboardDataRepositoryInterface.openScoreboard(file) == null){
            this.scoreboardDataRepositoryInterface.save(file, new Scoreboard());
            this.scoreboard = this.scoreboardDataRepositoryInterface.openScoreboard(file);
        }
        else{
            this.scoreboard = this.scoreboardDataRepositoryInterface.openScoreboard(file);
        }
    }

    /**
     * @return A scoreboard in a list format, where the index + 1 represents the rank of Account,
     * contained in Pair format, where the first item in the pair is the account
     * and the second is the account score
     */
    public ArrayList<Pair<Account, Integer>> getScoreboardList(){
        return scoreboard.getScoreboardList();
    }

    /**
     * Inserts the account and score into the scoreboard
     * @param account The account
     * @param score The account's score
     * @param file The file to save data to
     * @return True iff the account was added to the scoreboard. false otherwise.
     */
    public boolean addScore(Account account, int score, File file){
        return scoreboard.addScore(account, score, file, scoreboardDataRepositoryInterface);
    }
}
