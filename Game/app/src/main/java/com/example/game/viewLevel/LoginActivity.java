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

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.game.BaseActivity;
import com.example.game.R;
import com.example.game.models.AccountHolder;
import com.example.game.models.AccountManager;
import com.example.game.models.LoginUseCases;
import com.example.game.models.interfaces.LoginActions;
import com.example.game.presenters.LoginPresenter;

/**
 * Login activity for login the user in. Lets the user enter a username and if it exists they may
 * then sign into that account and play the game.
 */
public class LoginActivity extends BaseActivity implements LoginActions {

    /**
     * Text field which contains the user input
     */
    private EditText inputName;

    /**
     * Text displayed to show if the input is not an existing account
     */
    private TextView textView;

    /**
     * Determines the existence of a users account on the system and creates an account object for
     * them to login with.
     */
    private AccountManager accountManager = new AccountManager(new AccountDataRepository());

    /**
     * Presenter which interacts with the login for this activity.
     */
    private LoginPresenter loginPresenter;

    /**
     * Code to execute when the Activity is created.
     *
     * @param savedInstanceState A Bundle containing possibly previous states of this Activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputName = findViewById(R.id.accountNameText_LoginActivity);
        textView = findViewById(R.id.textView_LoginActivity);
        loginPresenter = new LoginPresenter(this, new LoginUseCases(accountManager));

        SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (mPreferences.getInt("Colour", 0) == 1) {
            getWindow().getDecorView().setBackgroundResource(R.color.background1);
            textView.setTextColor(getResources().getColor(R.color.background2));
        }
    }

    /**
     * Called when the user taps the "Select Account" button. Will attempt a login based off the
     * currently entered in textfield inputName
     */
    public void login(View view) {
        loginPresenter.login(inputName.getText().toString(), getApplicationContext().getFilesDir(),
                new AccountDataRepository());
    }

    /**
     * Called when the user taps the "Create Account" button
     */
    public void createAccount(View view) {
        Intent intent = new Intent(this, CreateAccountActivity.class);
        startActivity(intent);
    }

    /**
     * React to if the username is incorrect.
     */
    @Override
    public void incorrectUsername() {
        textView.setText(R.string.invalid_username);
    }

    /**
     * Moves to the main menu with account logged in.
     * @param accountHolder account layer which has the account to be logged in
     */
    @Override
    public void moveToMainMenu(AccountHolder accountHolder) {
        Intent intent = new Intent(this, MainActivity.class);
        BaseActivity.account = accountHolder;
        startActivity(intent);
    }
}
