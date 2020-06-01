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

import com.example.game.R;

import java.io.Serializable;

/**
 * Customizations used by an account
 */
public class Customization implements Serializable {

    /**
     * Language values for an account
     */
    private enum Language {
        ENGLISH ("en"),
        FRENCH ("fr"),
        RUSSIAN ("ru");

        private final String language;

        Language(String language){
            this.language = language;
        }

        public String getLanguage(){
            return this.language;
        }
    }

    /**
     * Colour values for an account
     */
    private enum Colour {
        GREY (R.color.background2),
        RED (R.color.background1);

        private final int colour;

        Colour(int colour) {
            this.colour = colour;
        }

        public int getColour(){
            return this.colour;
        }
    }

    /**
     * Icon values for an account
     */
    private enum Icon {
        MALE (R.drawable.user_male),
        FEMALE (R.drawable.user_female),
        ROBOT (R.drawable.robot);

        private final int icon;

        Icon(int icon) {
            this.icon = icon;
        }

        public int getIcon(){
            return this.icon;
        }
    }

    private Language currentLanguage;
    private Colour currentColour;
    private Icon currentIcon;

    /**
     * Constructor for customization options
     */
    public Customization() {
        this.currentLanguage = Language.ENGLISH;
        this.currentColour = Colour.GREY;
        this.currentIcon = Icon.ROBOT;
    }

    /**
     * @return current language customization option
     */
    public String getCurrentLanguage() {
        return currentLanguage.language;
    }

    /**
     * Sets a new language to customization options
     * @param currentLanguage String of new language
     */
    public void setCurrentLanguage(String currentLanguage) {
        for(Language l: Language.values())
            if(l.toString().equals(currentLanguage.toUpperCase())) {
                this.currentLanguage = l;
            }
    }

    /**
     * @return current colour customization option
     */
    public int getCurrentColour() {
        return currentColour.colour;
    }

    /**
     * Sets a new colour to customization options
     * @param currentColour String of new colour
     */
    public void setCurrentColour(String currentColour) {
        for(Colour c: Colour.values())
            if(c.toString().equals(currentColour.toUpperCase())) {
                this.currentColour = c;
            }
    }

    /**
     * @return current icon customization option
     */
    public int getCurrentIcon() {
        return currentIcon.icon;
    }

    /**
     * Sets a new icon to customization options
     * @param currentIcon String of new icon
     */
    public void setCurrentIcon(String currentIcon) {
        for(Icon i: Icon.values())
            if(i.toString().equals(currentIcon.toUpperCase())) {
                this.currentIcon = i;
            }
    }
}
