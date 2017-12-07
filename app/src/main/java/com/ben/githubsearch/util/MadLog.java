package com.ben.githubsearch.util;

import android.util.Log;

import static com.ben.githubsearch.BuildConfig.SHOW_LOGS;

public class MadLog {

    private static final String SPACE = ">>>>>>>>> ";

    public static void log(Object obj, String msg) {
        if (SHOW_LOGS) Log.d(obj.getClass().getSimpleName(), getOutput(msg));
    }

    public static void error(Object obj, String msg) {
        if (SHOW_LOGS) Log.e(obj.getClass().getSimpleName(), getOutput(msg));
    }

    public static void info(Object obj, String msg) {
        if (SHOW_LOGS) Log.i(obj.getClass().getSimpleName(), getOutput(msg));
    }

    private static String getOutput(String msg){
        return SPACE +  msg;
    }

}