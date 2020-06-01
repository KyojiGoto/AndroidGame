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

package com.example.game.viewLevel.game2;

//image used (free license: Image by OpenClipart-Vectors from Pixabay):
//https://pixabay.com/illustrations/road-sign-attention-right-of-way-808733/

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.game.BaseActivity;
import com.example.game.R;
import com.example.game.viewLevel.MainActivity;

public class Game2Activity extends BaseActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_game2_title);

    getWindow().getDecorView().setBackgroundResource(account.getBackground());
  }

  /**
   *
   * @param view the view of button clicked
   * @param type the type of questions to be asked
   */
  public void toRiddles(View view, String type) {
    Intent intent = new Intent(this, RiddleActivity.class);
    intent.putExtra("type", type);
    startActivity(intent);
  }

  /**
   * Have easy trivia questions as the questions.
   * @param view the button clicked
   */
  public void easyTrivia(View view){
    toRiddles(view, "easyTrivia");
  }

  /**
   * Have hard trivia questions as the questions.
   * @param view the button clicked
   */
  public void hardTrivia(View view){
    toRiddles(view, "hardTrivia");
  }

  /**
   * Have riddle based questions as the questions.
   * @param view the button clicked
   */
  public void riddle(View view){
    toRiddles(view, "riddle");
  }

  /**
   * Called when the user taps the "To Main Menu" button. Takes User to main menu of the App.
   *
   * @param view The View of this Activity.
   */
  public void toMainMenu(View view) {
    Intent intent = new Intent(this, MainActivity.class);
    startActivity(intent);
  }
}
