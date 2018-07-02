package com.example.okmac.retrokit.utils;

import android.content.Context;

import java.io.Serializable;

public class Logger implements Serializable {

    private String tag;

    /**
     * Initializes logger. Logs are disabled for release builds
     * during initialization.
     *
     * @param context
     */
    public static void init(Context context) {
        LogUtil.init(context);
    }

    private Logger() {
    }

    public Logger(Class<?> cls) {
        this.tag = cls.getName();
    }

    public Logger(String tag) {
        this.tag = tag;
    }

    /**
     * Prints the stack trace for the given throwable instance for debug build.
     *
     * @param ex
     */
    public void error(Throwable ex) {
        LogUtil.error(this.tag, "", ex);
    }

    public void warn(String log) {
        LogUtil.warn(this.tag, log);
    }

    public void debug(String log) {
        LogUtil.debug(this.tag, log);
    }
}
