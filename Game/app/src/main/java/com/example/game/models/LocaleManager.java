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

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.preference.PreferenceManager;

import java.util.Locale;

public class LocaleManager {
    private static final String LANGUAGE_KEY = "language_key";
    private static final String ENGLISH = "en";
    private static final String FRENCH = "fr";
    private static final String RUSSIAN = "ru";

    private final SharedPreferences prefs;

    /**
     * Creates a LocaleManager, which saves changed to global ChangedPreferences
     */
    public LocaleManager(Context context) {
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    /**
     * sets the current language
     */
    public Context setLocale(Context context) {
        return updateResources(context, prefs.getString(LANGUAGE_KEY, ENGLISH));
    }

    /**
     * sets the language to the given language
     */
    public void setNewLocale(Context context, String language) {
        prefs.edit().putString(LANGUAGE_KEY, language).apply();
        updateResources(context, language);
    }

    /**
     * updates the language through baseActivity context
     */
    private Context updateResources(Context context, String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Resources resources = context.getResources();
        Configuration configuration = new Configuration(resources.getConfiguration());
        configuration.setLocale(locale);
        context = context.createConfigurationContext(configuration);
        return context;
    }

    /**
     * @return the string key for the English language setting,
     */
    public static String getEnglish(){
        return ENGLISH;
    }

    /**
     * @return the string key for the French language setting,
     */
    public static String getFrench(){
        return FRENCH;
    }

    /**
     * @return the string key for the Russian language setting,
     */
    public static String getRussian(){
        return RUSSIAN;
    }
}
