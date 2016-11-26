package com.unity3d.player;

import android.opengl.GLSurfaceView.EGLConfigChooser;
import android.opengl.GLSurfaceView.EGLContextFactory;
import android.opengl.GLSurfaceView.EGLWindowSurfaceFactory;
import android.opengl.GLSurfaceView.GLWrapper;
import android.opengl.GLSurfaceView.Renderer;
import android.service.wallpaper.WallpaperService;
import android.service.wallpaper.WallpaperService.Engine;
import android.util.Log;
import android.view.SurfaceHolder;
import java.util.ArrayList;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;
import javax.microedition.khronos.opengles.GL;

public class e extends WallpaperService {
    private static int[] a = new int[1];

    public class a extends Engine implements c {
        protected int[] a;
        private e b;
        private EGLConfigChooser c;
        private EGLContextFactory d;
        private EGLWindowSurfaceFactory e;
        private boolean f;

        abstract class a implements EGLConfigChooser {
            private /* synthetic */ a a;

            public a(a aVar, int[] iArr) {
                this.a = aVar;
                aVar.a = iArr;
            }

            abstract EGLConfig a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig[] eGLConfigArr);

            public EGLConfig chooseConfig(EGL10 egl10, EGLDisplay eGLDisplay) {
                int[] iArr = new int[1];
                egl10.eglChooseConfig(eGLDisplay, this.a.a, null, 0, iArr);
                int i = iArr[0];
                if (i <= 0) {
                    throw new IllegalArgumentException("No configs match configSpec");
                }
                EGLConfig[] eGLConfigArr = new EGLConfig[i];
                egl10.eglChooseConfig(eGLDisplay, this.a.a, eGLConfigArr, i, iArr);
                EGLConfig a = a(egl10, eGLDisplay, eGLConfigArr);
                if (a != null) {
                    return a;
                }
                throw new IllegalArgumentException("No config chosen");
            }
        }

        class b extends a {
            protected int a;
            protected int b;
            protected int c;
            private int[] d;
            private int e;
            private int f;
            private int g;

            public b(a aVar, boolean z, int i) {
                int[] iArr = new int[15];
                iArr[0] = 12324;
                iArr[1] = 4;
                iArr[2] = 12323;
                iArr[3] = 4;
                iArr[4] = 12322;
                iArr[5] = 4;
                iArr[6] = 12321;
                iArr[7] = 0;
                iArr[8] = 12325;
                iArr[9] = 16;
                iArr[10] = 12326;
                iArr[11] = 0;
                iArr[12] = 12352;
                iArr[13] = z ? 4 : 1;
                iArr[14] = 12344;
                super(aVar, iArr);
                this.d = new int[1];
                this.a = 4;
                this.b = 4;
                this.c = 4;
                this.e = 0;
                this.f = 16;
                this.g = 0;
            }

            private int a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig, int i) {
                return egl10.eglGetConfigAttrib(eGLDisplay, eGLConfig, i, this.d) ? this.d[0] : 0;
            }

            public final EGLConfig a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig[] eGLConfigArr) {
                int i = 1000;
                EGLConfig eGLConfig = null;
                for (EGLConfig eGLConfig2 : eGLConfigArr) {
                    int a = a(egl10, eGLDisplay, eGLConfig2, 12325);
                    int a2 = a(egl10, eGLDisplay, eGLConfig2, 12326);
                    if (a >= this.f && a2 >= 0) {
                        a = ((Math.abs(a(egl10, eGLDisplay, eGLConfig2, 12324) - this.a) + Math.abs(a(egl10, eGLDisplay, eGLConfig2, 12323) - this.b)) + Math.abs(a(egl10, eGLDisplay, eGLConfig2, 12322) - this.c)) + Math.abs(a(egl10, eGLDisplay, eGLConfig2, 12321) - 0);
                        if (a < i) {
                            i = a;
                            eGLConfig = eGLConfig2;
                        }
                    }
                }
                return eGLConfig;
            }
        }

        class c implements EGLContextFactory {
            private boolean a = false;

            c(boolean z) {
                this.a = z;
            }

            public final EGLContext createContext(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig) {
                String str = " ";
                int a = e.a(egl10, eGLDisplay, eGLConfig, 12320);
                int a2 = e.a(egl10, eGLDisplay, eGLConfig, 12324);
                int a3 = e.a(egl10, eGLDisplay, eGLConfig, 12323);
                int a4 = e.a(egl10, eGLDisplay, eGLConfig, 12322);
                int a5 = e.a(egl10, eGLDisplay, eGLConfig, 12321);
                int a6 = e.a(egl10, eGLDisplay, eGLConfig, 12325);
                int a7 = e.a(egl10, eGLDisplay, eGLConfig, 12326);
                StringBuilder append = new StringBuilder().append(a5 == 0 ? "RGB" : "RGBA").append(a);
                String str2 = " ";
                append = append.append(str).append(Integer.toString(a2)).append(Integer.toString(a3)).append(Integer.toString(a4)).append(a5 == 0 ? "" : Integer.toString(a5));
                String str3 = " ";
                Log.i("GLwallpaper", "Creating OpenGL ES " + (this.a ? "2.0" : "1.x") + " context (" + append.append(str).append(Integer.toString(a6)).append("/").append(Integer.toString(a7)).toString() + ")");
                int[] iArr = new int[3];
                iArr[0] = 12440;
                iArr[1] = this.a ? 2 : 1;
                iArr[2] = 12344;
                return egl10.eglCreateContext(eGLDisplay, eGLConfig, EGL10.EGL_NO_CONTEXT, iArr);
            }

            public final void destroyContext(EGL10 egl10, EGLDisplay eGLDisplay, EGLContext eGLContext) {
                egl10.eglDestroyContext(eGLDisplay, eGLContext);
            }
        }

        class d {
            EGLConfig a;
            private EGL10 b;
            private EGLDisplay c;
            private EGLSurface d;
            private EGLContext e;
            private EGLConfigChooser f;
            private EGLContextFactory g;
            private EGLWindowSurfaceFactory h;
            private GLWrapper i;

            public d(EGLConfigChooser eGLConfigChooser, EGLContextFactory eGLContextFactory, EGLWindowSurfaceFactory eGLWindowSurfaceFactory, GLWrapper gLWrapper) {
                this.f = eGLConfigChooser;
                this.g = eGLContextFactory;
                this.h = eGLWindowSurfaceFactory;
                this.i = gLWrapper;
            }

            public final GL a(SurfaceHolder surfaceHolder) {
                if (!(this.d == null || this.d == EGL10.EGL_NO_SURFACE)) {
                    this.b.eglMakeCurrent(this.c, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT);
                    this.h.destroySurface(this.b, this.c, this.d);
                }
                this.d = this.h.createWindowSurface(this.b, this.c, this.a, surfaceHolder);
                if (this.d == null || this.d == EGL10.EGL_NO_SURFACE) {
                    throw new RuntimeException("createWindowSurface failed");
                } else if (this.b.eglMakeCurrent(this.c, this.d, this.d, this.e)) {
                    GL gl = this.e.getGL();
                    return this.i != null ? this.i.wrap(gl) : gl;
                } else {
                    throw new RuntimeException("eglMakeCurrent failed.");
                }
            }

            public final void a() {
                String str = "EglHelper";
                if (this.b == null) {
                    String str2 = "EglHelper";
                    Log.d(str + 0, "getting new EGL");
                    this.b = (EGL10) EGLContext.getEGL();
                }
                if (this.c == null) {
                    str2 = "EglHelper";
                    Log.d(str + 0, "getting new display");
                    this.c = this.b.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
                }
                if (this.a == null) {
                    str2 = "EglHelper";
                    Log.d(str + 0, "getting new config");
                    this.b.eglInitialize(this.c, new int[2]);
                    this.a = this.f.chooseConfig(this.b, this.c);
                }
                if (this.e == null) {
                    str2 = "EglHelper";
                    Log.d(str + 0, "creating new context");
                    this.e = this.g.createContext(this.b, this.c, this.a);
                    if (this.e == null || this.e == EGL10.EGL_NO_CONTEXT) {
                        throw new RuntimeException("createContext failed");
                    }
                }
                this.d = null;
            }

            public final boolean b() {
                this.b.eglSwapBuffers(this.c, this.d);
                return this.b.eglGetError() != 12302;
            }

            public final void c() {
                if (this.d != null && this.d != EGL10.EGL_NO_SURFACE) {
                    this.b.eglMakeCurrent(this.c, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT);
                    this.h.destroySurface(this.b, this.c, this.d);
                    this.d = null;
                }
            }

            public final void d() {
                if (this.e != null) {
                    this.g.destroyContext(this.b, this.c, this.e);
                    this.e = null;
                }
                if (this.c != null) {
                    this.b.eglTerminate(this.c);
                    this.c = null;
                }
            }
        }

        class e extends Thread {
            public boolean a = false;
            private final a b = new a();
            private e c;
            private EGLConfigChooser d;
            private EGLContextFactory e;
            private EGLWindowSurfaceFactory f;
            private GLWrapper g;
            private SurfaceHolder h;
            private boolean i = true;
            private boolean j;
            private boolean k;
            private boolean l;
            private boolean m;
            private int n = 0;
            private int o = 0;
            private int p = 1;
            private boolean q = true;
            private boolean r;
            private Renderer s;
            private ArrayList t = new ArrayList();
            private d u;
            private /* synthetic */ a v;

            class a {
                private /* synthetic */ e a;

                private a(e eVar, byte b) {
                    this.a = eVar;
                }

                public final synchronized void a(e eVar) {
                    eVar.a = true;
                    if (this.a.c == eVar) {
                        this.a.c = null;
                    }
                    notifyAll();
                }

                public final synchronized boolean b(e eVar) {
                    boolean z;
                    if (this.a.c == eVar || this.a.c == null) {
                        this.a.c = eVar;
                        notifyAll();
                        z = true;
                    } else {
                        z = false;
                    }
                    return z;
                }

                public final synchronized void c(e eVar) {
                    if (this.a.c == eVar) {
                        this.a.c = null;
                    }
                    notifyAll();
                }
            }

            e(a aVar, Renderer renderer, EGLConfigChooser eGLConfigChooser, EGLContextFactory eGLContextFactory, EGLWindowSurfaceFactory eGLWindowSurfaceFactory, GLWrapper gLWrapper) {
                this.v = aVar;
                this.s = renderer;
                this.d = eGLConfigChooser;
                this.e = eGLContextFactory;
                this.f = eGLWindowSurfaceFactory;
                this.g = null;
            }

            private void e() {
                if (this.m) {
                    this.m = false;
                    this.u.c();
                    this.b.c(this);
                }
            }

            /* JADX WARNING: inconsistent code. */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            private void f() {
                /*
                r12 = this;
                r10 = 0;
                r9 = 1;
                r0 = new com.unity3d.player.e$a$d;
                r1 = r12.d;
                r2 = r12.e;
                r3 = r12.f;
                r4 = r12.g;
                r0.<init>(r1, r2, r3, r4);
                r12.u = r0;
                r0 = 0;
                r1 = r9;
                r2 = r0;
                r0 = r9;
            L_0x0015:
                r3 = r12.g();	 Catch:{ all -> 0x00da }
                if (r3 != 0) goto L_0x0121;
            L_0x001b:
                r3 = r12.b;	 Catch:{ all -> 0x00da }
                monitor-enter(r3);	 Catch:{ all -> 0x00da }
                r4 = r10;
            L_0x001f:
                r5 = r12.j;	 Catch:{ all -> 0x00d7 }
                if (r5 == 0) goto L_0x0026;
            L_0x0023:
                r12.e();	 Catch:{ all -> 0x00d7 }
            L_0x0026:
                r5 = r12.k;	 Catch:{ all -> 0x00d7 }
                if (r5 != 0) goto L_0x004b;
            L_0x002a:
                r5 = r12.l;	 Catch:{ all -> 0x00d7 }
                if (r5 != 0) goto L_0x0039;
            L_0x002e:
                r12.e();	 Catch:{ all -> 0x00d7 }
                r5 = 1;
                r12.l = r5;	 Catch:{ all -> 0x00d7 }
                r5 = r12.b;	 Catch:{ all -> 0x00d7 }
                r5.notifyAll();	 Catch:{ all -> 0x00d7 }
            L_0x0039:
                r5 = r12.a;	 Catch:{ all -> 0x00d7 }
                if (r5 == 0) goto L_0x0067;
            L_0x003d:
                monitor-exit(r3);	 Catch:{ all -> 0x00d7 }
                r0 = r12.b;
                monitor-enter(r0);
                r12.e();	 Catch:{ all -> 0x0064 }
                r1 = r12.u;	 Catch:{ all -> 0x0064 }
                r1.d();	 Catch:{ all -> 0x0064 }
                monitor-exit(r0);	 Catch:{ all -> 0x0064 }
            L_0x004a:
                return;
            L_0x004b:
                r5 = r12.m;	 Catch:{ all -> 0x00d7 }
                if (r5 != 0) goto L_0x0039;
            L_0x004f:
                r5 = r12.b;	 Catch:{ all -> 0x00d7 }
                r5 = r5.b(r12);	 Catch:{ all -> 0x00d7 }
                if (r5 == 0) goto L_0x0039;
            L_0x0057:
                r4 = 1;
                r12.m = r4;	 Catch:{ all -> 0x00d7 }
                r4 = r12.u;	 Catch:{ all -> 0x00d7 }
                r4.a();	 Catch:{ all -> 0x00d7 }
                r4 = 1;
                r12.q = r4;	 Catch:{ all -> 0x00d7 }
                r4 = r9;
                goto L_0x0039;
            L_0x0064:
                r1 = move-exception;
                monitor-exit(r0);
                throw r1;
            L_0x0067:
                r5 = r12.r;	 Catch:{ all -> 0x00d7 }
                if (r5 == 0) goto L_0x0094;
            L_0x006b:
                r5 = 0;
                r12.r = r5;	 Catch:{ all -> 0x00d7 }
                r5 = r9;
                r6 = r10;
                r7 = r10;
                r8 = r10;
            L_0x0072:
                monitor-exit(r3);	 Catch:{ all -> 0x00d7 }
                if (r5 == 0) goto L_0x00e8;
            L_0x0075:
                r3 = r12.h();	 Catch:{ all -> 0x00da }
                if (r3 == 0) goto L_0x0015;
            L_0x007b:
                r3.run();	 Catch:{ all -> 0x00da }
                r3 = r12.g();	 Catch:{ all -> 0x00da }
                if (r3 == 0) goto L_0x0075;
            L_0x0084:
                r0 = r12.b;
                monitor-enter(r0);
                r12.e();	 Catch:{ all -> 0x0091 }
                r1 = r12.u;	 Catch:{ all -> 0x0091 }
                r1.d();	 Catch:{ all -> 0x0091 }
                monitor-exit(r0);	 Catch:{ all -> 0x0091 }
                goto L_0x004a;
            L_0x0091:
                r1 = move-exception;
                monitor-exit(r0);
                throw r1;
            L_0x0094:
                r5 = r12.j;	 Catch:{ all -> 0x00d7 }
                if (r5 != 0) goto L_0x00d0;
            L_0x0098:
                r5 = r12.k;	 Catch:{ all -> 0x00d7 }
                if (r5 == 0) goto L_0x00d0;
            L_0x009c:
                r5 = r12.m;	 Catch:{ all -> 0x00d7 }
                if (r5 == 0) goto L_0x00d0;
            L_0x00a0:
                r5 = r12.n;	 Catch:{ all -> 0x00d7 }
                if (r5 <= 0) goto L_0x00d0;
            L_0x00a4:
                r5 = r12.o;	 Catch:{ all -> 0x00d7 }
                if (r5 <= 0) goto L_0x00d0;
            L_0x00a8:
                r5 = r12.q;	 Catch:{ all -> 0x00d7 }
                if (r5 != 0) goto L_0x00b0;
            L_0x00ac:
                r5 = r12.p;	 Catch:{ all -> 0x00d7 }
                if (r5 != r9) goto L_0x00d0;
            L_0x00b0:
                r5 = r12.i;	 Catch:{ all -> 0x00d7 }
                r6 = r12.n;	 Catch:{ all -> 0x00d7 }
                r7 = r12.o;	 Catch:{ all -> 0x00d7 }
                r8 = 0;
                r12.i = r8;	 Catch:{ all -> 0x00d7 }
                r8 = 0;
                r12.q = r8;	 Catch:{ all -> 0x00d7 }
                r8 = r12.k;	 Catch:{ all -> 0x00d7 }
                if (r8 == 0) goto L_0x013a;
            L_0x00c0:
                r8 = r12.l;	 Catch:{ all -> 0x00d7 }
                if (r8 == 0) goto L_0x013a;
            L_0x00c4:
                r5 = 0;
                r12.l = r5;	 Catch:{ all -> 0x00d7 }
                r5 = r12.b;	 Catch:{ all -> 0x00d7 }
                r5.notifyAll();	 Catch:{ all -> 0x00d7 }
                r5 = r10;
                r8 = r6;
                r6 = r9;
                goto L_0x0072;
            L_0x00d0:
                r5 = r12.b;	 Catch:{ all -> 0x00d7 }
                r5.wait();	 Catch:{ all -> 0x00d7 }
                goto L_0x001f;
            L_0x00d7:
                r0 = move-exception;
                monitor-exit(r3);	 Catch:{ all -> 0x00da }
                throw r0;	 Catch:{ all -> 0x00da }
            L_0x00da:
                r0 = move-exception;
                r1 = r12.b;
                monitor-enter(r1);
                r12.e();	 Catch:{ all -> 0x0132 }
                r2 = r12.u;	 Catch:{ all -> 0x0132 }
                r2.d();	 Catch:{ all -> 0x0132 }
                monitor-exit(r1);	 Catch:{ all -> 0x0132 }
                throw r0;
            L_0x00e8:
                if (r4 == 0) goto L_0x0137;
            L_0x00ea:
                r1 = r9;
                r3 = r9;
            L_0x00ec:
                if (r1 == 0) goto L_0x0135;
            L_0x00ee:
                r0 = r12.u;	 Catch:{ all -> 0x00da }
                r1 = r12.h;	 Catch:{ all -> 0x00da }
                r0 = r0.a(r1);	 Catch:{ all -> 0x00da }
                r0 = (javax.microedition.khronos.opengles.GL10) r0;	 Catch:{ all -> 0x00da }
                r1 = r0;
                r0 = r9;
            L_0x00fa:
                if (r3 == 0) goto L_0x013f;
            L_0x00fc:
                r2 = r12.s;	 Catch:{ all -> 0x00da }
                r3 = r12.u;	 Catch:{ all -> 0x00da }
                r3 = r3.a;	 Catch:{ all -> 0x00da }
                r2.onSurfaceCreated(r1, r3);	 Catch:{ all -> 0x00da }
                r2 = r10;
            L_0x0106:
                if (r0 == 0) goto L_0x010e;
            L_0x0108:
                r0 = r12.s;	 Catch:{ all -> 0x00da }
                r0.onSurfaceChanged(r1, r8, r7);	 Catch:{ all -> 0x00da }
                r0 = r10;
            L_0x010e:
                if (r8 <= 0) goto L_0x011c;
            L_0x0110:
                if (r7 <= 0) goto L_0x011c;
            L_0x0112:
                r3 = r12.s;	 Catch:{ all -> 0x00da }
                r3.onDrawFrame(r1);	 Catch:{ all -> 0x00da }
                r3 = r12.u;	 Catch:{ all -> 0x00da }
                r3.b();	 Catch:{ all -> 0x00da }
            L_0x011c:
                r11 = r2;
                r2 = r1;
                r1 = r11;
                goto L_0x0015;
            L_0x0121:
                r0 = r12.b;
                monitor-enter(r0);
                r12.e();	 Catch:{ all -> 0x012f }
                r1 = r12.u;	 Catch:{ all -> 0x012f }
                r1.d();	 Catch:{ all -> 0x012f }
                monitor-exit(r0);	 Catch:{ all -> 0x012f }
                goto L_0x004a;
            L_0x012f:
                r1 = move-exception;
                monitor-exit(r0);
                throw r1;
            L_0x0132:
                r0 = move-exception;
                monitor-exit(r1);
                throw r0;
            L_0x0135:
                r1 = r2;
                goto L_0x00fa;
            L_0x0137:
                r3 = r1;
                r1 = r6;
                goto L_0x00ec;
            L_0x013a:
                r8 = r6;
                r6 = r5;
                r5 = r10;
                goto L_0x0072;
            L_0x013f:
                r2 = r3;
                goto L_0x0106;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.unity3d.player.e.a.e.f():void");
            }

            private boolean g() {
                boolean z;
                synchronized (this.b) {
                    z = this.a;
                }
                return z;
            }

            private Runnable h() {
                synchronized (this) {
                    if (this.t.size() > 0) {
                        Runnable runnable = (Runnable) this.t.remove(0);
                        return runnable;
                    }
                    return null;
                }
            }

            public final void a() {
                synchronized (this.b) {
                    this.k = false;
                    this.b.notifyAll();
                    while (!this.l && isAlive() && !this.a) {
                        try {
                            this.b.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
            }

            public final void a(int i, int i2) {
                synchronized (this.b) {
                    this.n = i;
                    this.o = i2;
                    this.i = true;
                    this.b.notifyAll();
                }
            }

            public final void a(SurfaceHolder surfaceHolder) {
                this.h = surfaceHolder;
                synchronized (this.b) {
                    this.k = true;
                    this.b.notifyAll();
                }
            }

            public final void a(Runnable runnable) {
                synchronized (this) {
                    this.t.add(runnable);
                    synchronized (this.b) {
                        this.r = true;
                        this.b.notifyAll();
                    }
                }
            }

            public final void b() {
                synchronized (this.b) {
                    this.j = true;
                    this.b.notifyAll();
                }
            }

            public final void c() {
                synchronized (this.b) {
                    this.j = false;
                    this.q = true;
                    this.b.notifyAll();
                }
            }

            public final void d() {
                synchronized (this.b) {
                    this.a = true;
                    this.b.notifyAll();
                }
                try {
                    join();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            public final void run() {
                setName("GLThread " + getId());
                try {
                    f();
                } catch (InterruptedException e) {
                } finally {
                    this.b.a(this);
                }
            }
        }

        class f extends b {
            public f(a aVar, boolean z) {
                super(aVar, z, 16);
                this.a = 5;
                this.b = 6;
                this.c = 5;
            }
        }

        class g implements EGLWindowSurfaceFactory {
            g() {
            }

            public final EGLSurface createWindowSurface(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig, Object obj) {
                EGLSurface eGLSurface = null;
                while (eGLSurface == null) {
                    try {
                        eGLSurface = egl10.eglCreateWindowSurface(eGLDisplay, eGLConfig, obj, null);
                        if (eGLSurface == null) {
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException e) {
                            }
                        }
                    } catch (Throwable th) {
                        if (eGLSurface == null) {
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException e2) {
                            }
                        }
                    }
                }
                return eGLSurface;
            }

            public final void destroySurface(EGL10 egl10, EGLDisplay eGLDisplay, EGLSurface eGLSurface) {
                egl10.eglDestroySurface(eGLDisplay, eGLSurface);
            }
        }

        public a(e eVar, boolean z) {
            super(eVar);
            this.f = z;
        }

        public void onCreate(SurfaceHolder surfaceHolder) {
            super.onCreate(surfaceHolder);
        }

        public void onDestroy() {
            super.onDestroy();
            this.b.d();
        }

        public void onPause() {
            this.b.b();
        }

        public void onResume() {
            this.b.c();
        }

        public void onSurfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
            this.b.a(i2, i3);
            super.onSurfaceChanged(surfaceHolder, i, i2, i3);
        }

        public void onSurfaceCreated(SurfaceHolder surfaceHolder) {
            Log.d("UnityGLEngine", "onSurfaceCreated()");
            this.b.a(surfaceHolder);
            super.onSurfaceCreated(surfaceHolder);
        }

        public void onSurfaceDestroyed(SurfaceHolder surfaceHolder) {
            Log.d("UnityGLEngine", "onSurfaceDestroyed()");
            this.b.a();
            super.onSurfaceDestroyed(surfaceHolder);
        }

        public void onVisibilityChanged(boolean z) {
            if (z) {
                onResume();
            } else {
                onPause();
            }
            super.onVisibilityChanged(z);
        }

        public void queueEvent(Runnable runnable) {
            this.b.a(runnable);
        }

        public void setRenderer(Renderer renderer) {
            if (this.b != null) {
                throw new IllegalStateException("setRenderer has already been called for this instance.");
            }
            if (this.c == null) {
                this.c = new f(this, this.f);
            }
            if (this.d == null) {
                this.d = new c(this.f);
            }
            if (this.e == null) {
                this.e = new g();
            }
            this.b = new e(this, renderer, this.c, this.d, this.e, null);
            this.b.start();
        }
    }

    static /* synthetic */ int a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig, int i) {
        return egl10.eglGetConfigAttrib(eGLDisplay, eGLConfig, i, a) ? a[0] : 0;
    }

    public Engine onCreateEngine() {
        return null;
    }
}
