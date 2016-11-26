package com.unity3d.player;

class g implements Runnable {
    private /* synthetic */ UnityPlayer a;

    g(UnityPlayer unityPlayer) {
        this.a = unityPlayer;
    }

    public final void run() {
        this.a.nativeDeviceOrientation(this.a.T);
    }
}
