package com.example.okmac.retrokit.utils;

import android.content.Context;

public class ProfilePreferences extends PrefManager {
    public ProfilePreferences(Context context, String prefName) {
        super(context, prefName);
    }

    public ProfilePreferences(Context context) {
        super(context,"meh");
    }
}
