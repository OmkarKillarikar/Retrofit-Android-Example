package com.example.okmac.retrokit.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {
    private Context context;
    private String prefName;

    public PrefManager(Context context, String prefName) {
        this.context = context;
        this.prefName = prefName;
    }

    /**
     * Puts given key-value pair to the Shared Preferences.
     *
     * @param key
     * @param value - String
     */
    public void put(String key, String value) {
        SharedPreferences.Editor edit = context.getSharedPreferences(prefName, Context.MODE_PRIVATE).edit();
        edit.putString(key, value).apply();
    }

    /**
     * Puts given key-value pair to the Shared Preferences.
     *
     * @param key
     * @param value - boolean
     */
    public void put(String key, boolean value) {
        SharedPreferences.Editor edit = context.getSharedPreferences(prefName, Context.MODE_PRIVATE).edit();
        edit.putBoolean(key, value).apply();
    }

    /**
     * Puts given key-value pair to the Shared Preferences.
     *
     * @param key
     * @param value - long
     */
    public void put(String key, long value) {
        SharedPreferences.Editor edit = context.getSharedPreferences(prefName, Context.MODE_PRIVATE).edit();
        edit.putLong(key, value).apply();
    }

    /**
     * Puts given key-value pair to the Shared Preferences.
     *
     * @param key
     * @param value - int
     */
    public void put(String key, int value) {
        SharedPreferences.Editor edit = context.getSharedPreferences(prefName, Context.MODE_PRIVATE).edit();
        edit.putInt(key, value).apply();
    }

    /**
     * Puts given key-value pair to the Shared Preferences.
     *
     * @param key
     * @param value - float
     */
    public void put(String key, float value) {
        SharedPreferences.Editor edit = context.getSharedPreferences(prefName, Context.MODE_PRIVATE).edit();
        edit.putFloat(key, value).apply();
    }

    /**
     * Returns String value for the given key, null if no value is found.
     *
     * @param key
     * @return String
     */
    public String getString(String key) {
        if (context != null) {
            return context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
                    .getString(key, null);
        }
        return "";
    }

    /**
     * Returns String value for the given key, null if no value is found.
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public String getString(String key, String defaultValue) {
        if (context != null) {
            return context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
                    .getString(key, defaultValue);
        }
        return "";
    }


    /**
     * Returns boolean value for the given key, can set default value as well.
     *
     * @param key
     * @param defaultValue
     * @return boolean
     */
    public boolean getBoolean(String key, boolean defaultValue) {
        if (context != null) {
            return context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
                    .getBoolean(key, defaultValue);
        }
        return defaultValue;
    }

    /**
     * Returns long value for the given key, -1 if no value is found.
     *
     * @param key
     * @return long
     */
    public long getLong(String key, long defaultValue) {
        if (context != null) {
            return context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
                    .getLong(key, defaultValue);
        }
        return defaultValue;
    }

    /**
     * Returns int value for the given key.
     *
     * @param key
     * @return long
     */
    public int getInt(String key, int defaultVal) {
        if (context != null) {
            return context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
                    .getInt(key, defaultVal);
        }
        return defaultVal;
    }

    /**
     * Returns float value for the given key, defaultValue if no value is found.
     *
     * @param key
     * @param defaultValue
     * @return float
     */
    public float getFloat(String key, float defaultValue) {
        if (context != null) {
            return context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
                    .getFloat(key, defaultValue);
        }
        return defaultValue;
    }

}