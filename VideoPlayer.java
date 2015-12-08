package com.unity3d.player;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.MediaController;
import android.widget.MediaController.MediaPlayerControl;

public class VideoPlayer extends Activity implements OnBufferingUpdateListener, OnCompletionListener, OnPreparedListener, OnVideoSizeChangedListener, Callback, MediaPlayerControl {
    private int a;
    private int b;
    private int c;
    private int d;
    private MediaPlayer e;
    private MediaController f;
    private SurfaceView g;
    private SurfaceHolder h;
    private String i;
    private int j;
    private int k;
    private boolean l;
    private boolean m = false;
    private boolean n = false;
    private FrameLayout o;
    private int p = 0;
    private boolean q = false;
    private int r = 0;
    private WakeLock s = null;

    private void a() {
        if (this.e != null) {
            this.e.release();
            this.e = null;
        }
        this.c = 0;
        this.d = 0;
        this.n = false;
        this.m = false;
    }

    private void b() {
        if (!isPlaying()) {
            WindowManager windowManager = (WindowManager) getSystemService("window");
            this.a = windowManager.getDefaultDisplay().getWidth();
            this.b = windowManager.getDefaultDisplay().getHeight();
            int i = this.a;
            int i2 = this.b;
            int i3;
            if (this.k == 1 || this.k == 2) {
                float f = ((float) this.c) / ((float) this.d);
                if (((float) this.a) / ((float) this.b) <= f) {
                    i2 = i;
                    i = (int) (((float) this.a) / f);
                } else {
                    i3 = i2;
                    i2 = (int) (((float) this.b) * f);
                    i = i3;
                }
            } else if (this.k == 0) {
                i = this.c;
                i2 = i;
                i = this.d;
            } else {
                i3 = i2;
                i2 = i;
                i = i3;
            }
            "frameWidth = " + i2 + "; frameHeight = " + i;
            this.o.updateViewLayout(this.g, new LayoutParams(i2, i, 17));
            if (!this.q) {
                start();
            }
        }
    }

    public boolean canPause() {
        return true;
    }

    public boolean canSeekBackward() {
        return true;
    }

    public boolean canSeekForward() {
        return true;
    }

    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
    }

    public int getBufferPercentage() {
        return this.l ? this.p : 100;
    }

    public int getCurrentPosition() {
        return this.e == null ? 0 : this.e.getCurrentPosition();
    }

    public int getDuration() {
        return this.e == null ? 0 : this.e.getDuration();
    }

    public boolean isPlaying() {
        Object obj = (this.n && this.m) ? 1 : null;
        return this.e == null ? obj == null : this.e.isPlaying() || obj == null;
    }

    public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
        "onBufferingUpdate percent:" + i;
        this.p = i;
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        finish();
    }

    protected void onCreate(Bundle bundle) {
        String str = "fileName";
        super.onCreate(bundle);
        Bundle extras = getIntent().getExtras();
        String str2 = "fileName";
        if (extras.getString(str).length() == 0) {
            finish();
            return;
        }
        setTheme(16973831);
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        setRequestedOrientation(extras.getInt("screenOrientation"));
        if (extras.getBoolean("wakeLock")) {
            this.s = ((PowerManager) getSystemService("power")).newWakeLock(26, "videowakelock");
            this.s.acquire();
        }
        str2 = "fileName";
        str2 = extras.getString(str);
        int i = extras.getInt("backgroundColor");
        int i2 = extras.getInt("controlMode");
        int i3 = extras.getInt("scalingMode");
        boolean z = extras.getBoolean("isURL");
        if (str2.length() == 0) {
            finish();
            return;
        }
        this.o = new FrameLayout(this);
        this.g = new SurfaceView(this);
        this.h = this.g.getHolder();
        this.h.addCallback(this);
        this.h.setType(3);
        this.o.setBackgroundColor(i);
        this.o.addView(this.g);
        setContentView(this.o);
        this.i = str2;
        this.j = i2;
        this.k = i3;
        this.l = z;
        "fileName: " + this.i;
        "backgroundColor: " + i;
        "controlMode: " + this.j;
        "scalingMode: " + this.k;
        "isURL: " + this.l;
    }

    protected void onDestroy() {
        super.onDestroy();
        a();
        if (this.s != null) {
            this.s.release();
        }
        this.s = null;
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 26 || i == 82 || i == 25 || i == 24 || i == 0) {
            return super.onKeyDown(i, keyEvent);
        }
        if (this.f != null && (i == 23 || i == 19 || i == 20 || i == 21 || i == 22)) {
            return this.f.onKeyDown(i, keyEvent);
        }
        "onKeyDown : " + i + " + " + keyEvent;
        finish();
        return true;
    }

    protected void onPause() {
        super.onPause();
        if (!this.q) {
            pause();
            this.q = false;
        }
        if (this.e != null) {
            this.r = this.e.getCurrentPosition();
        }
    }

    public void onPrepared(MediaPlayer mediaPlayer) {
        this.n = true;
        if (this.n && this.m) {
            b();
        }
    }

    protected void onResume() {
        super.onResume();
        if (!this.q) {
            start();
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.f != null) {
            return this.f.onTouchEvent(motionEvent);
        }
        int action = motionEvent.getAction() & 255;
        if (this.j == 2 && action == 0) {
            "onTouchEvent : " + motionEvent;
            finish();
        }
        return super.onTouchEvent(motionEvent);
    }

    public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
        "onVideoSizeChanged called " + i + "x" + i2;
        if (i == 0 || i2 == 0) {
            "invalid video width(" + i + ") or height(" + i2 + ")";
            return;
        }
        this.m = true;
        this.c = i;
        this.d = i2;
        if (this.n && this.m) {
            b();
        }
    }

    public void pause() {
        if (this.e != null) {
            this.e.pause();
            this.q = true;
        }
    }

    public void seekTo(int i) {
        if (this.e != null) {
            this.e.seekTo(i);
        }
    }

    public void start() {
        if (this.e != null) {
            this.e.start();
            this.q = false;
        }
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        "surfaceChanged called " + i + " " + i2 + "x" + i3;
        this.a = i2;
        this.b = i3;
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        a();
        try {
            this.e = new MediaPlayer();
            if (this.l) {
                this.e.setDataSource(this.i);
            } else {
                AssetFileDescriptor openFd = getResources().getAssets().openFd(this.i);
                this.e.setDataSource(openFd.getFileDescriptor(), openFd.getStartOffset(), openFd.getLength());
            }
            this.e.setDisplay(this.h);
            this.e.setOnBufferingUpdateListener(this);
            this.e.setOnCompletionListener(this);
            this.e.setOnPreparedListener(this);
            this.e.setOnVideoSizeChangedListener(this);
            this.e.setAudioStreamType(3);
            this.e.prepare();
            if (this.j == 0 || this.j == 1) {
                this.f = new MediaController(this);
                this.f.setMediaPlayer(this);
                this.f.setAnchorView(this.g);
                this.f.setEnabled(true);
                this.f.show();
            }
        } catch (Exception e) {
            "error: " + e.getMessage() + e;
        }
        seekTo(this.r);
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        a();
    }
}
