package com.unity3d.player;

class f implements Runnable {
    private /* synthetic */ UnityPlayer a;

    f(UnityPlayer unityPlayer) {
        this.a = unityPlayer;
    }

    public final void run() {
        this.a.nativeSensor(this.a.O, this.a.P, this.a.Q, this.a.R);
    }
}
