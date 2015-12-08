package com.unity3d.player;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import java.util.concurrent.atomic.AtomicBoolean;

class PlayerPrefs {
    private SharedPreferences a;
    private Editor b;
    private AtomicBoolean c = new AtomicBoolean(false);

    PlayerPrefs(SharedPreferences sharedPreferences) {
        this.a = sharedPreferences;
        this.b = this.a.edit();
        InitPlayerPrefs();
    }

    private void DeleteAll() {
        this.b.clear();
        this.c.set(true);
    }

    private void DeleteKey(String str) {
        this.b.remove(str);
        this.c.set(true);
    }

    private float GetFloat(String str, float f) {
        Sync();
        return this.a.getFloat(str, f);
    }

    private int GetInt(String str, int i) {
        Sync();
        return this.a.getInt(str, i);
    }

    private String GetString(String str, String str2) {
        Sync();
        return this.a.getString(str, str2);
    }

    private boolean HasKey(String str) {
        Sync();
        return this.a.contains(str);
    }

    private final native void InitPlayerPrefs();

    private boolean SetFloat(String str, float f) {
        this.b.putFloat(str, f);
        this.c.set(true);
        return true;
    }

    private boolean SetInt(String str, int i) {
        this.b.putInt(str, i);
        this.c.set(true);
        return true;
    }

    private boolean SetString(String str, String str2) {
        this.b.putString(str, str2);
        this.c.set(true);
        return true;
    }

    private void Sync() {
        if (this.c.getAndSet(false)) {
            this.b.commit();
        }
    }
}
