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

package com.example.game.viewLevel.game3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceView;

import com.example.game.R;
import com.example.game.presenters.game3.Game3PresenterFacadeImpl;

import java.util.concurrent.TimeUnit;

/**
 * Game View class for Game 3.
 */
public class Game3View extends SurfaceView implements Runnable {

    private final Context context;
    /**
     * Checks if the game is still in play.
     */
    private boolean isPlaying = true;

    /**
     * Game thread.
     */
    private Thread thread;

    /**
     * The paint used on the canvas.
     */
    private Paint paint;

    /**
     * The canvas to draw on.
     */
    private Canvas canvas = getHolder().lockCanvas();

    /**
     * Instance that manages all the objects in the Game.
     */
    private Game3PresenterFacadeImpl game3Presenter;

    /**
     * The instance of the class that called created an instance of this class.
     */
    private final Context activityContext;

    /**
     * Game3View constructor.
     *
     * @param context the instance of the class that called created an instance of this class.
     */
    public Game3View(Context context, String difficulty) {
        super(context);
        this.context = context;
        activityContext = context;
        paint = new Paint();

        game3Presenter = new Game3PresenterFacadeImpl(getResources(), difficulty, canvas, paint) {
        };

        //Creates all the game objects that are needed for this Game.
        game3Presenter.initializeGameObjects();
    }

    /**
     * Runs the thread.
     */
    @Override
    public void run() {
        while (isPlaying) {


            //Updates the game objects.
            update();

            //Checks if Game has ended. If it has, it breaks from the Game Loop.
            if (checkGameEnded()) {
                break;
            }
            //Draws the game objects
            draw();

            //Gives the user 1 second to see how much HP damage they did to the Enemy.
            if (!game3Presenter.getTurn()) {
                try {
                    TimeUnit.MILLISECONDS.sleep(game3Presenter.getWaitTime());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }


            //Thread pauses to get a Frame Rate.
            sleep();
        }

        //Ends the game and given method takes User to Game 3 Exit Activity.
        ((Game3PlayActivity) activityContext).gameOver(game3Presenter.checkWinner(),
                game3Presenter.getNumMoves());
    }

    /**
     * Checks if game is done.
     */
    private boolean checkGameEnded() {
        return game3Presenter.gameEnded(context);
    }

    /**
     * Updates the game based on the game situation.
     */
    private void update() {
        game3Presenter.update();
    }

    /**
     * Draws the game objects on the screen.
     */
    private void draw() {
        //Checks of the surface is valid to draw on.
        if (getHolder().getSurface().isValid()) {

            //Locks the canvas, sets Grey Background, then draw objects (handled by
            // gameObjectManager.draw) and then unlocks the canvas.
            this.canvas = getHolder().lockCanvas();
            game3Presenter.setCanvas(this.canvas);
            super.draw(canvas);
            //canvas.drawColor(Color.BLACK);
            Bitmap b1 = BitmapFactory.decodeResource(getResources(), R.drawable.gamebackground2);
            Bitmap b2 = Bitmap.createScaledBitmap(b1, getWidth(), getHeight(), false);
            canvas.drawBitmap(b2, 0, 0, null);
            game3Presenter.draw();
            getHolder().unlockCanvasAndPost(canvas);
        }
    }

    /**
     * Pauses the thread for 17 milliseconds (1000 milliseconds / 60) to achieve frame rate of 60
     * FPS.
     */
    private void sleep() {
        try {
            Thread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Resumes the thread.
     */
    void resume() {
        isPlaying = true;
        thread = new Thread(this);
        thread.start();
    }

    /**
     * Pauses the thread.
     */
    void pause() {
        try {
            isPlaying = false;
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads user's touch input and gameObjectManager handles the cases.
     *
     * @param event The user's movement.
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            float touchX = event.getX();
            float touchY = event.getY();
            game3Presenter.readTouch(touchX, touchY);
        }
        return super.onTouchEvent(event);
    }
}
