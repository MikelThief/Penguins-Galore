package com.unity3d.player;

import android.opengl.GLSurfaceView.Renderer;

public interface c {
    void onDestroy();

    void onPause();

    void onResume();

    void queueEvent(Runnable runnable);

    void setRenderer(Renderer renderer);
}
