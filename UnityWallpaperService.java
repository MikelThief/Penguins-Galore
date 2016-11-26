package com.unity3d.player;

import android.os.Bundle;
import android.service.wallpaper.WallpaperService.Engine;
import android.util.Log;
import android.view.MotionEvent;
import com.unity3d.player.e.a;

public class UnityWallpaperService extends e {
    private UnityPlayer a;

    public void onCreate() {
        super.onCreate();
        this.a = new UnityPlayer(this);
    }

    public Engine onCreateEngine() {
        int i = this.a.getSettings().getInt("gles_mode", 1);
        c anonymousClass1 = new a(this, i == 2) {
            private /* synthetic */ UnityWallpaperService b;

            public final Bundle onCommand(String str, int i, int i2, int i3, Bundle bundle, boolean z) {
                String str2 = ", ";
                String str3 = ", ";
                str3 = ", ";
                str3 = ", ";
                str3 = ", ";
                str3 = ", ";
                Log.v("UnityWS", "onCommand(" + str + str2 + i + str2 + i2 + str2 + i3 + str2 + bundle + str2 + z + ")");
                return super.onCommand(str, i, i2, i3, bundle, z);
            }

            public final void onDesiredSizeChanged(int i, int i2) {
                super.onDesiredSizeChanged(i, i2);
                Log.v("UnityWS", "onDesiredSizeChanged(" + i + ", " + i2 + ")");
            }

            public final void onOffsetsChanged(float f, float f2, float f3, float f4, int i, int i2) {
                String str = ", ";
                super.onOffsetsChanged(f, f2, f3, f4, i, i2);
                String str2 = ", ";
                str2 = ", ";
                str2 = ", ";
                str2 = ", ";
                str2 = ", ";
                Log.v("UnityWS", "onOffsetsChanged(" + f + str + f2 + str + f3 + str + f4 + str + i + str + i2 + ")");
            }

            public final void onTouchEvent(MotionEvent motionEvent) {
                this.b.a.onTouchEvent(motionEvent);
            }

            public final void onVisibilityChanged(boolean z) {
                super.onVisibilityChanged(z);
                Log.v("UnityWS", "onVisibilityChanged(" + z + ")");
                if (z) {
                    this.b.a.resume();
                } else {
                    this.b.a.pause();
                }
            }
        };
        this.a.init(anonymousClass1, i);
        anonymousClass1.setTouchEventsEnabled(true);
        return anonymousClass1;
    }

    public void onDestroy() {
        super.onDestroy();
        this.a.quit();
    }
}
