package com.plter.adbrowser.prefs;

import java.util.prefs.BackingStoreException;

/**
 * Created by plter on 2/17/16.
 */
public class Preferences {


    private static Preferences __ins = null;

    public static Preferences getInstance() {
        if (__ins == null) {
            __ins = new Preferences();
        }

        return __ins;
    }


    private java.util.prefs.Preferences preferences = java.util.prefs.Preferences.userRoot().node("Adbrowser");

    private Preferences() {
    }

    public void saveAdbPath(String adbPath) {
        preferences.put("adbPath", adbPath);
        try {
            preferences.flush();
        } catch (BackingStoreException e) {
            e.printStackTrace();
        }
    }

    public String readAdbPath() {
        return preferences.get("adbPath", null);
    }

}
