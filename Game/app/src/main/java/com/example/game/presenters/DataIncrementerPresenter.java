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

import com.example.game.models.DataIncrementerUseCases;
import com.example.game.models.interfaces.DataIncrementerActions;
import com.example.game.models.interfaces.DataIncrementerListener;

import java.io.File;

public class DataIncrementerPresenter implements DataIncrementerListener {
    private DataIncrementerUseCases useCases;
    private DataIncrementerActions actions;

    public DataIncrementerPresenter(DataIncrementerActions dataIncrementerActions, DataIncrementerUseCases useCases){
        this.useCases = useCases;
        this.actions = dataIncrementerActions;
    }

    public void incrementLevel(File contextFile){
        useCases.incrementThenNext(this, contextFile);
    }

    public void decrementLevel(File contextFile){
        useCases.decrementThenAgain(this, contextFile);
    }

    @Override
    public void doAgain() {
        actions.toRetry();
    }

    @Override
    public void next() {
        actions.toNext();
    }
}
