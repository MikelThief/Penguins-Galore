package com.unity3d.player;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

public class UnityPlayerActivity extends Activity {
    private UnityPlayer a;

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.a.configurationChanged(configuration);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTheme(16973831);
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        this.a = new UnityPlayer(this);
        this.a.init(this.a.getSettings().getInt("gles_mode", 1), false);
        View view = this.a.getView();
        setContentView(view);
        view.requestFocus();
    }

    protected void onDestroy() {
        super.onDestroy();
        this.a.quit();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return this.a.onKeyDown(i, keyEvent);
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        return this.a.onKeyUp(i, keyEvent);
    }

    protected void onPause() {
        super.onPause();
        this.a.pause();
    }

    protected void onResume() {
        super.onResume();
        this.a.resume();
    }
}
