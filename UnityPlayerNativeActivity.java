package com.unity3d.player;

import android.app.NativeActivity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.SurfaceHolder;

public class UnityPlayerNativeActivity extends NativeActivity {
    private boolean isPaused = true;
    private UnityGLSetup mGLSetup;
    private UnityPlayer mUnityPlayer;

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.mUnityPlayer.configurationChanged(configuration);
    }

    protected void onCreate(Bundle bundle) {
        this.mUnityPlayer = new UnityPlayer(this);
        super.onCreate(bundle);
        setTheme(16973831);
        getWindow().setFormat(4);
        getWindow().setFlags(1024, 1024);
        int i = this.mUnityPlayer.getSettings().getInt("gles_mode", 1);
        this.mGLSetup = new UnityGLSetup();
        this.mGLSetup.a(i);
        this.mUnityPlayer.init(this.mGLSetup, i);
    }

    protected void onDestroy() {
        super.onDestroy();
        this.mUnityPlayer.quit();
    }

    public void onGlobalLayout() {
        super.onGlobalLayout();
        if (!this.isPaused) {
            this.mUnityPlayer.resume();
        }
    }

    protected void onPause() {
        super.onPause();
        this.isPaused = true;
        this.mUnityPlayer.pause();
    }

    protected void onResume() {
        super.onResume();
        this.isPaused = false;
    }

    public void onWindowFocusChanged(boolean z) {
        getWindow().setFormat(4);
        super.onWindowFocusChanged(z);
    }

    public void setRequestedOrientation(int i) {
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        super.surfaceChanged(surfaceHolder, i, i2, i3);
        this.mGLSetup.a(i2, i3);
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        super.surfaceCreated(surfaceHolder);
        this.mGLSetup.a(surfaceHolder);
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        super.surfaceDestroyed(surfaceHolder);
        this.mGLSetup.a();
    }
}
