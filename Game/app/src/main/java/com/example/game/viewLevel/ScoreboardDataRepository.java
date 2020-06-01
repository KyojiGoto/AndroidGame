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

package com.example.game.viewLevel;

import com.example.game.models.Scoreboard;
import com.example.game.models.interfaces.ScoreboardDataRepositoryInterface;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Accesses and stores information in a database
 */
public class ScoreboardDataRepository implements ScoreboardDataRepositoryInterface {
    /**
     * Name of file
     */
    private static final String FILE_NAME = "scoreboardData.txt";

    /**
     * Saves a scoreboard to file
     * @param file file path to write in
     * @param scoreboard The scoreboard to save
     */
    public void save(File file, Scoreboard scoreboard){
        try {
            Gson gson = new Gson();
            String scoreboardString = gson.toJson(scoreboard);
            File saveFile = new File(file, FILE_NAME);
            FileWriter fileOut = new FileWriter(saveFile);
            fileOut.write(scoreboardString);
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the current scoreboard
     * @param file file path to write in
     * @return Returns the scoreboard found in the database
     */
    public Scoreboard openScoreboard(File file){
        try {
            Gson gson = new Gson();
            File saveFile = new File(file, FILE_NAME);
            FileReader loadAccountData = new FileReader(saveFile);
            BufferedReader loadAccData = new BufferedReader(loadAccountData);
            String line;
            if ((line = loadAccData.readLine()) != null) {
                Scoreboard scoreboard = gson.fromJson(line, Scoreboard.class);
                loadAccData.close();
                return scoreboard;
            }
        } catch (IOException error) {
            error.printStackTrace();
            System.out.println("Can't find scoreboard");
        }
        return null;
    }
}
