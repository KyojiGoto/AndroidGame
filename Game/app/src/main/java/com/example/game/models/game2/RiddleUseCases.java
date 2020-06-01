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

package com.example.game.models.game2;

import com.example.game.BaseActivity;
import com.example.game.models.game2.Interfaces.RiddleListener;

import java.io.File;
import java.util.ArrayList;

/**
 * Use cases for answering multiple choice riddles
 */
public class RiddleUseCases {

    /**
     * Determines whether the clicked answer is right or wrong, updates account information, and
     * tells the listener to react accordingly.
     *
     * @param riddleStrings  The array containing all answers to the riddle and which answer is
     *                       correct
     * @param clickedString  the String for the riddle which was clicked on (selected)
     * @param riddleListener the listener which reacts to the result of a right or wrong answer
     * @param contextFile    the File which any changed data will be saved to
     */
    public void actOnCorrectness(String[] riddleStrings, String clickedString,
                                 RiddleListener riddleListener, File contextFile) {
        for (int i = 0; i < 4; i++) {
            if (riddleStrings[i + 2].equals(clickedString)) {
                if (i == Integer.parseInt(riddleStrings[1])) {
                    BaseActivity.account.incrementScore(5, contextFile);
                    riddleListener.rightAnswer();
                } else {
                    BaseActivity.account.decrementHitPoints(8, contextFile);
                    riddleListener.wrongAnswer();
                }
            }
        }
    }

    /**
     * Sends player to game over screen if they have no hit points left, or sends them to the end
     * of the game or the next riddle depending on how many riddles are left if they still have hit
     * points left.
     *
     * @param remainingRiddles the list of remaining riddles numbers
     * @param riddleListener   the listener which reacts to the result of a right or wrong answer
     * @param contextFile      the File which any changed data will be saved to
     */
    public void activateNextRiddle(ArrayList<Integer> remainingRiddles, RiddleListener riddleListener, File contextFile) {
        if (BaseActivity.account.getHitPoints() <= 0) {
            BaseActivity.account.incrementGamesPlayed(contextFile);
            riddleListener.noLivesLeft();
        } else if (remainingRiddles.size() == 0) {
            BaseActivity.account.incrementGamesPlayed(contextFile);
            BaseActivity.account.incrementLevel(contextFile);
            riddleListener.endOfRiddles();
        } else {
            riddleListener.moreRiddles();
        }
    }
}
