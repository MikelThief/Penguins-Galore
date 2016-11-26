package com.unity3d.player;

import android.opengl.GLSurfaceView.EGLContextFactory;
import android.opengl.GLSurfaceView.Renderer;
import android.util.Log;
import android.view.SurfaceHolder;
import java.util.ArrayList;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;
import javax.microedition.khronos.opengles.GL;
import javax.microedition.khronos.opengles.GL10;

public final class UnityGLSetup implements c {
    private static final g a = new g();
    private boolean b = true;
    private SurfaceHolder c;
    private f d;
    private c e;
    private EGLContextFactory f;
    private d g;
    private int h;

    public interface d {
        EGLSurface a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig, Object obj);

        void a(EGL10 egl10, EGLDisplay eGLDisplay, EGLSurface eGLSurface);
    }

    static class DefaultWindowSurfaceFactory implements d {
        /* synthetic */ DefaultWindowSurfaceFactory() {
            this((byte) 0);
        }

        private DefaultWindowSurfaceFactory(byte b) {
        }

        public final EGLSurface a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig, Object obj) {
            return egl10.eglCreateWindowSurface(eGLDisplay, eGLConfig, obj, null);
        }

        public final void a(EGL10 egl10, EGLDisplay eGLDisplay, EGLSurface eGLSurface) {
            egl10.eglDestroySurface(eGLDisplay, eGLSurface);
        }
    }

    public interface c {
        private int[] a;
        private /* synthetic */ UnityGLSetup b;

        c(UnityGLSetup unityGLSetup, int[] iArr) {
            int[] iArr2;
            this.b = unityGLSetup;
            if (this.b.h != 2) {
                iArr2 = iArr;
            } else {
                int length = iArr.length;
                Object obj = new int[(length + 2)];
                System.arraycopy(iArr, 0, obj, 0, length - 1);
                obj[length - 1] = 12352;
                obj[length] = 4;
                obj[length + 1] = 12344;
                Object obj2 = obj;
            }
            this.a = iArr2;
        }

        final EGLConfig a(EGL10 egl10, EGLDisplay eGLDisplay) {
            int[] iArr = new int[1];
            if (egl10.eglChooseConfig(eGLDisplay, this.a, null, 0, iArr)) {
                int i = iArr[0];
                if (i <= 0) {
                    throw new IllegalArgumentException("No configs match configSpec");
                }
                EGLConfig[] eGLConfigArr = new EGLConfig[i];
                if (egl10.eglChooseConfig(eGLDisplay, this.a, eGLConfigArr, i, iArr)) {
                    EGLConfig a = a(egl10, eGLDisplay, eGLConfigArr);
                    if (a != null) {
                        return a;
                    }
                    throw new IllegalArgumentException("No config chosen");
                }
                throw new IllegalArgumentException("eglChooseConfig#2 failed");
            }
            throw new IllegalArgumentException("eglChooseConfig failed");
        }

        EGLConfig a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig[] eGLConfigArr);
    }

    class a extends c {
        private int[] a = new int[1];
        private int b = 5;
        private int c = 6;
        private int d = 5;
        private int e = 0;
        private int f = 16;
        private int g = 0;

        public a(UnityGLSetup unityGLSetup, int i, int i2, int i3, int i4, int i5, int i6) {
            super(unityGLSetup, new int[]{12324, 5, 12323, 6, 12322, 5, 12321, 0, 12325, 16, 12326, 0, 12344});
        }

        private int a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig, int i, int i2) {
            return egl10.eglGetConfigAttrib(eGLDisplay, eGLConfig, i, this.a) ? this.a[0] : 0;
        }

        public final EGLConfig a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig[] eGLConfigArr) {
            for (EGLConfig eGLConfig : eGLConfigArr) {
                int a = a(egl10, eGLDisplay, eGLConfig, 12325, 0);
                int a2 = a(egl10, eGLDisplay, eGLConfig, 12326, 0);
                if (a >= this.f && a2 >= this.g) {
                    a = a(egl10, eGLDisplay, eGLConfig, 12324, 0);
                    int a3 = a(egl10, eGLDisplay, eGLConfig, 12323, 0);
                    int a4 = a(egl10, eGLDisplay, eGLConfig, 12322, 0);
                    a2 = a(egl10, eGLDisplay, eGLConfig, 12321, 0);
                    if (a == this.b && a3 == this.c && a4 == this.d && a2 == this.e) {
                        return eGLConfig;
                    }
                }
            }
            return null;
        }
    }

    class b implements EGLContextFactory {
        private int a;
        private /* synthetic */ UnityGLSetup b;

        private b(UnityGLSetup unityGLSetup, byte b) {
            this.b = unityGLSetup;
            this.a = 12440;
        }

        public final EGLContext createContext(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig) {
            String stringBuilder;
            String str = " ";
            if (this.b.e instanceof a) {
                a aVar = (a) this.b.e;
                int a = aVar.a(egl10, eGLDisplay, eGLConfig, 12320, 0);
                int a2 = aVar.a(egl10, eGLDisplay, eGLConfig, 12324, 0);
                int a3 = aVar.a(egl10, eGLDisplay, eGLConfig, 12323, 0);
                int a4 = aVar.a(egl10, eGLDisplay, eGLConfig, 12322, 0);
                int a5 = aVar.a(egl10, eGLDisplay, eGLConfig, 12321, 0);
                int a6 = aVar.a(egl10, eGLDisplay, eGLConfig, 12325, 0);
                int a7 = aVar.a(egl10, eGLDisplay, eGLConfig, 12326, 0);
                StringBuilder append = new StringBuilder().append(a5 == 0 ? "RGB" : "RGBA").append(a);
                String str2 = " ";
                append = append.append(str).append(Integer.toString(a2)).append(Integer.toString(a3)).append(Integer.toString(a4)).append(a5 == 0 ? "" : Integer.toString(a5));
                String str3 = " ";
                stringBuilder = append.append(str).append(Integer.toString(a6)).append("/").append(Integer.toString(a7)).toString();
            } else {
                stringBuilder = null;
            }
            Log.i("UnityGLSetup", "Creating OpenGL ES " + (this.b.h == 2 ? "2.0" : "1.x") + " context (" + stringBuilder + ")");
            int[] iArr = new int[]{this.a, this.b.h, 12344};
            EGLContext eGLContext = EGL10.EGL_NO_CONTEXT;
            if (this.b.h == 0) {
                iArr = null;
            }
            return egl10.eglCreateContext(eGLDisplay, eGLConfig, eGLContext, iArr);
        }

        public final void destroyContext(EGL10 egl10, EGLDisplay eGLDisplay, EGLContext eGLContext) {
            if (!egl10.eglDestroyContext(eGLDisplay, eGLContext)) {
                Log.e("DefaultContextFactory", "display:" + eGLDisplay + " context: " + eGLContext);
                throw new RuntimeException("eglDestroyContext failed: " + ("0x" + Integer.toHexString(egl10.eglGetError())));
            }
        }
    }

    class e {
        EGL10 a;
        EGLDisplay b;
        EGLSurface c;
        EGLConfig d;
        final /* synthetic */ UnityGLSetup e;
        private EGLContext f;

        public e(UnityGLSetup unityGLSetup) {
            this.e = unityGLSetup;
        }

        private static void a(String str, int i) {
            throw new RuntimeException(str + " failed: " + ("0x" + Integer.toHexString(i)));
        }

        public final GL a(SurfaceHolder surfaceHolder) {
            if (this.a == null) {
                throw new RuntimeException("egl not initialized");
            } else if (this.b == null) {
                throw new RuntimeException("eglDisplay not initialized");
            } else if (this.d == null) {
                throw new RuntimeException("mEglConfig not initialized");
            } else {
                if (!(this.c == null || this.c == EGL10.EGL_NO_SURFACE)) {
                    this.a.eglMakeCurrent(this.b, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT);
                    this.e.g.a(this.a, this.b, this.c);
                }
                this.c = this.e.g.a(this.a, this.b, this.d, surfaceHolder);
                if (this.c == null || this.c == EGL10.EGL_NO_SURFACE) {
                    int eglGetError = this.a.eglGetError();
                    if (eglGetError == 12299) {
                        Log.e("EglHelper", "createWindowSurface returned EGL_BAD_NATIVE_WINDOW.");
                        return null;
                    }
                    a("createWindowSurface", eglGetError);
                }
                if (!this.a.eglMakeCurrent(this.b, this.c, this.c, this.f)) {
                    a("eglMakeCurrent", this.a.eglGetError());
                }
                return this.f.getGL();
            }
        }

        public final void a() {
            this.a = (EGL10) EGLContext.getEGL();
            this.b = this.a.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
            if (this.b == EGL10.EGL_NO_DISPLAY) {
                throw new RuntimeException("eglGetDisplay failed");
            }
            if (this.a.eglInitialize(this.b, new int[2])) {
                this.d = this.e.e.a(this.a, this.b);
                this.f = this.e.f.createContext(this.a, this.b, this.d);
                if (this.f == null || this.f == EGL10.EGL_NO_CONTEXT) {
                    this.f = null;
                    a("createContext", this.a.eglGetError());
                }
                this.c = null;
                return;
            }
            throw new RuntimeException("eglInitialize failed");
        }

        public final boolean b() {
            if (!this.a.eglSwapBuffers(this.b, this.c)) {
                int eglGetError = this.a.eglGetError();
                switch (eglGetError) {
                    case 12299:
                        Log.e("EglHelper", "eglSwapBuffers returned EGL_BAD_NATIVE_WINDOW. tid=" + Thread.currentThread().getId());
                        break;
                    case 12302:
                        return false;
                    default:
                        a("eglSwapBuffers", eglGetError);
                        break;
                }
            }
            return true;
        }

        public final void c() {
            if (this.f != null) {
                this.e.f.destroyContext(this.a, this.b, this.f);
                this.f = null;
            }
            if (this.b != null) {
                this.a.eglTerminate(this.b);
                this.b = null;
            }
        }
    }

    class f extends Thread {
        private boolean a;
        private boolean b;
        private boolean c;
        private boolean d;
        private boolean e;
        private boolean f;
        private boolean g;
        private boolean h;
        private boolean i;
        private int j = 0;
        private int k = 0;
        private int l = 1;
        private boolean m = true;
        private boolean n;
        private ArrayList o = new ArrayList();
        private Renderer p;
        private e q;
        private /* synthetic */ UnityGLSetup r;

        f(UnityGLSetup unityGLSetup, Renderer renderer) {
            this.r = unityGLSetup;
            this.p = renderer;
        }

        private void f() {
            if (this.h) {
                this.h = false;
                e eVar = this.q;
                if (eVar.c != null && eVar.c != EGL10.EGL_NO_SURFACE) {
                    eVar.a.eglMakeCurrent(eVar.b, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT);
                    eVar.e.g.a(eVar.a, eVar.b, eVar.c);
                    eVar.c = null;
                }
            }
        }

        private void g() {
            if (this.g) {
                this.q.c();
                this.g = false;
                UnityGLSetup.a.c(this);
            }
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void h() {
            /*
            r21 = this;
            r2 = new com.unity3d.player.UnityGLSetup$e;
            r0 = r21;
            r0 = r0.r;
            r3 = r0;
            r2.<init>(r3);
            r0 = r2;
            r1 = r21;
            r1.q = r0;
            r2 = 0;
            r0 = r2;
            r1 = r21;
            r1.g = r0;
            r2 = 0;
            r0 = r2;
            r1 = r21;
            r1.h = r0;
            r2 = 0;
            r3 = 0;
            r4 = 0;
            r5 = 0;
            r6 = 0;
            r7 = 0;
            r8 = 0;
            r9 = 0;
            r10 = 0;
            r11 = 0;
            r12 = 0;
            r16 = r12;
            r12 = r2;
            r2 = r16;
            r17 = r10;
            r10 = r4;
            r4 = r17;
            r18 = r8;
            r8 = r6;
            r6 = r18;
            r19 = r5;
            r5 = r9;
            r9 = r19;
            r20 = r3;
            r3 = r11;
            r11 = r20;
        L_0x003f:
            r13 = com.unity3d.player.UnityGLSetup.a;	 Catch:{ all -> 0x01e4 }
            monitor-enter(r13);	 Catch:{ all -> 0x01e4 }
        L_0x0044:
            r0 = r21;
            r0 = r0.a;	 Catch:{ all -> 0x01e1 }
            r14 = r0;
            if (r14 == 0) goto L_0x005c;
        L_0x004b:
            monitor-exit(r13);	 Catch:{ all -> 0x01e1 }
            r2 = com.unity3d.player.UnityGLSetup.a;
            monitor-enter(r2);
            r21.f();	 Catch:{ all -> 0x0059 }
            r21.g();	 Catch:{ all -> 0x0059 }
            monitor-exit(r2);	 Catch:{ all -> 0x0059 }
        L_0x0058:
            return;
        L_0x0059:
            r3 = move-exception;
            monitor-exit(r2);
            throw r3;
        L_0x005c:
            r0 = r21;
            r0 = r0.o;	 Catch:{ all -> 0x01e1 }
            r14 = r0;
            r14 = r14.isEmpty();	 Catch:{ all -> 0x01e1 }
            if (r14 != 0) goto L_0x0093;
        L_0x0067:
            r0 = r21;
            r0 = r0.o;	 Catch:{ all -> 0x01e1 }
            r2 = r0;
            r14 = 0;
            r2 = r2.remove(r14);	 Catch:{ all -> 0x01e1 }
            r2 = (java.lang.Runnable) r2;	 Catch:{ all -> 0x01e1 }
            r16 = r2;
            r2 = r10;
            r10 = r9;
            r9 = r8;
            r8 = r7;
            r7 = r6;
            r6 = r5;
            r5 = r4;
            r4 = r3;
            r3 = r16;
        L_0x007f:
            monitor-exit(r13);	 Catch:{ all -> 0x01e1 }
            if (r3 == 0) goto L_0x020e;
        L_0x0082:
            r3.run();	 Catch:{ all -> 0x01e4 }
            r3 = 0;
            r16 = r3;
            r3 = r4;
            r4 = r5;
            r5 = r6;
            r6 = r7;
            r7 = r8;
            r8 = r9;
            r9 = r10;
            r10 = r2;
            r2 = r16;
            goto L_0x003f;
        L_0x0093:
            r0 = r21;
            r0 = r0.d;	 Catch:{ all -> 0x01e1 }
            r14 = r0;
            r0 = r21;
            r0 = r0.c;	 Catch:{ all -> 0x01e1 }
            r15 = r0;
            if (r14 == r15) goto L_0x00b0;
        L_0x009f:
            r0 = r21;
            r0 = r0.c;	 Catch:{ all -> 0x01e1 }
            r14 = r0;
            r0 = r14;
            r1 = r21;
            r1.d = r0;	 Catch:{ all -> 0x01e1 }
            r14 = com.unity3d.player.UnityGLSetup.a;	 Catch:{ all -> 0x01e1 }
            r14.notifyAll();	 Catch:{ all -> 0x01e1 }
        L_0x00b0:
            r0 = r21;
            r0 = r0.i;	 Catch:{ all -> 0x01e1 }
            r14 = r0;
            if (r14 == 0) goto L_0x00c4;
        L_0x00b7:
            r21.f();	 Catch:{ all -> 0x01e1 }
            r21.g();	 Catch:{ all -> 0x01e1 }
            r5 = 0;
            r0 = r5;
            r1 = r21;
            r1.i = r0;	 Catch:{ all -> 0x01e1 }
            r5 = 1;
        L_0x00c4:
            if (r9 == 0) goto L_0x00cd;
        L_0x00c6:
            r21.f();	 Catch:{ all -> 0x01e1 }
            r21.g();	 Catch:{ all -> 0x01e1 }
            r9 = 0;
        L_0x00cd:
            r0 = r21;
            r0 = r0.h;	 Catch:{ all -> 0x01e1 }
            r14 = r0;
            if (r14 == 0) goto L_0x00fa;
        L_0x00d4:
            r0 = r21;
            r0 = r0.d;	 Catch:{ all -> 0x01e1 }
            r14 = r0;
            if (r14 == 0) goto L_0x00fa;
        L_0x00db:
            r21.f();	 Catch:{ all -> 0x01e1 }
            r14 = com.unity3d.player.UnityGLSetup.a;	 Catch:{ all -> 0x01e1 }
            r14.a();	 Catch:{ all -> 0x01e1 }
            r21.g();	 Catch:{ all -> 0x01e1 }
            r14 = com.unity3d.player.UnityGLSetup.a;	 Catch:{ all -> 0x01e1 }
            r14 = r14.b();	 Catch:{ all -> 0x01e1 }
            if (r14 == 0) goto L_0x00fa;
        L_0x00f2:
            r0 = r21;
            r0 = r0.q;	 Catch:{ all -> 0x01e1 }
            r14 = r0;
            r14.c();	 Catch:{ all -> 0x01e1 }
        L_0x00fa:
            r0 = r21;
            r0 = r0.e;	 Catch:{ all -> 0x01e1 }
            r14 = r0;
            if (r14 != 0) goto L_0x011f;
        L_0x0101:
            r0 = r21;
            r0 = r0.f;	 Catch:{ all -> 0x01e1 }
            r14 = r0;
            if (r14 != 0) goto L_0x011f;
        L_0x0108:
            r0 = r21;
            r0 = r0.h;	 Catch:{ all -> 0x01e1 }
            r14 = r0;
            if (r14 == 0) goto L_0x0112;
        L_0x010f:
            r21.f();	 Catch:{ all -> 0x01e1 }
        L_0x0112:
            r14 = 1;
            r0 = r14;
            r1 = r21;
            r1.f = r0;	 Catch:{ all -> 0x01e1 }
            r14 = com.unity3d.player.UnityGLSetup.a;	 Catch:{ all -> 0x01e1 }
            r14.notifyAll();	 Catch:{ all -> 0x01e1 }
        L_0x011f:
            r0 = r21;
            r0 = r0.e;	 Catch:{ all -> 0x01e1 }
            r14 = r0;
            if (r14 == 0) goto L_0x013a;
        L_0x0126:
            r0 = r21;
            r0 = r0.f;	 Catch:{ all -> 0x01e1 }
            r14 = r0;
            if (r14 == 0) goto L_0x013a;
        L_0x012d:
            r14 = 0;
            r0 = r14;
            r1 = r21;
            r1.f = r0;	 Catch:{ all -> 0x01e1 }
            r14 = com.unity3d.player.UnityGLSetup.a;	 Catch:{ all -> 0x01e1 }
            r14.notifyAll();	 Catch:{ all -> 0x01e1 }
        L_0x013a:
            if (r6 == 0) goto L_0x0150;
        L_0x013c:
            r6 = 0;
            r7 = 0;
            r14 = 1;
            r0 = r14;
            r1 = r21;
            r1.n = r0;	 Catch:{ all -> 0x01e1 }
            r14 = com.unity3d.player.UnityGLSetup.a;	 Catch:{ all -> 0x01e1 }
            r14.notifyAll();	 Catch:{ all -> 0x01e1 }
            r16 = r7;
            r7 = r6;
            r6 = r16;
        L_0x0150:
            r14 = r21.i();	 Catch:{ all -> 0x01e1 }
            if (r14 == 0) goto L_0x0205;
        L_0x0156:
            r0 = r21;
            r0 = r0.g;	 Catch:{ all -> 0x01e1 }
            r14 = r0;
            if (r14 != 0) goto L_0x0160;
        L_0x015d:
            if (r5 == 0) goto L_0x01bd;
        L_0x015f:
            r5 = 0;
        L_0x0160:
            r0 = r21;
            r0 = r0.g;	 Catch:{ all -> 0x01e1 }
            r14 = r0;
            if (r14 == 0) goto L_0x017b;
        L_0x0167:
            r0 = r21;
            r0 = r0.h;	 Catch:{ all -> 0x01e1 }
            r14 = r0;
            if (r14 != 0) goto L_0x017b;
        L_0x016e:
            r8 = 1;
            r0 = r8;
            r1 = r21;
            r1.h = r0;	 Catch:{ all -> 0x01e1 }
            r8 = 1;
            r10 = 1;
            r16 = r10;
            r10 = r8;
            r8 = r16;
        L_0x017b:
            r0 = r21;
            r0 = r0.h;	 Catch:{ all -> 0x01e1 }
            r14 = r0;
            if (r14 == 0) goto L_0x0205;
        L_0x0182:
            r0 = r21;
            r0 = r0.r;	 Catch:{ all -> 0x01e1 }
            r14 = r0;
            r14 = r14.b;	 Catch:{ all -> 0x01e1 }
            if (r14 == 0) goto L_0x01fe;
        L_0x018d:
            r3 = 1;
            r0 = r21;
            r0 = r0.j;	 Catch:{ all -> 0x01e1 }
            r4 = r0;
            r0 = r21;
            r0 = r0.k;	 Catch:{ all -> 0x01e1 }
            r7 = r0;
            r8 = 1;
            r0 = r21;
            r0 = r0.r;	 Catch:{ all -> 0x01e1 }
            r14 = r0;
            r15 = 0;
            r14.b = r15;	 Catch:{ all -> 0x01e1 }
            r16 = r7;
            r7 = r8;
            r8 = r3;
            r3 = r16;
        L_0x01a8:
            r14 = com.unity3d.player.UnityGLSetup.a;	 Catch:{ all -> 0x01e1 }
            r14.notifyAll();	 Catch:{ all -> 0x01e1 }
            r16 = r2;
            r2 = r10;
            r10 = r9;
            r9 = r8;
            r8 = r7;
            r7 = r6;
            r6 = r5;
            r5 = r4;
            r4 = r3;
            r3 = r16;
            goto L_0x007f;
        L_0x01bd:
            r14 = com.unity3d.player.UnityGLSetup.a;	 Catch:{ all -> 0x01e1 }
            r0 = r14;
            r1 = r21;
            r14 = r0.b(r1);	 Catch:{ all -> 0x01e1 }
            if (r14 == 0) goto L_0x0160;
        L_0x01ca:
            r0 = r21;
            r0 = r0.q;	 Catch:{ RuntimeException -> 0x01f2 }
            r11 = r0;
            r11.a();	 Catch:{ RuntimeException -> 0x01f2 }
            r11 = 1;
            r0 = r11;
            r1 = r21;
            r1.g = r0;	 Catch:{ all -> 0x01e1 }
            r11 = 1;
            r14 = com.unity3d.player.UnityGLSetup.a;	 Catch:{ all -> 0x01e1 }
            r14.notifyAll();	 Catch:{ all -> 0x01e1 }
            goto L_0x0160;
        L_0x01e1:
            r2 = move-exception;
            monitor-exit(r13);	 Catch:{ all -> 0x01e4 }
            throw r2;	 Catch:{ all -> 0x01e4 }
        L_0x01e4:
            r2 = move-exception;
            r3 = com.unity3d.player.UnityGLSetup.a;
            monitor-enter(r3);
            r21.f();	 Catch:{ all -> 0x0286 }
            r21.g();	 Catch:{ all -> 0x0286 }
            monitor-exit(r3);	 Catch:{ all -> 0x0286 }
            throw r2;
        L_0x01f2:
            r2 = move-exception;
            r3 = com.unity3d.player.UnityGLSetup.a;	 Catch:{ all -> 0x01e1 }
            r0 = r3;
            r1 = r21;
            r0.c(r1);	 Catch:{ all -> 0x01e1 }
            throw r2;	 Catch:{ all -> 0x01e1 }
        L_0x01fe:
            r14 = 0;
            r0 = r14;
            r1 = r21;
            r1.m = r0;	 Catch:{ all -> 0x01e1 }
            goto L_0x01a8;
        L_0x0205:
            r14 = com.unity3d.player.UnityGLSetup.a;	 Catch:{ all -> 0x01e1 }
            r14.wait();	 Catch:{ all -> 0x01e1 }
            goto L_0x0044;
        L_0x020e:
            if (r2 == 0) goto L_0x0233;
        L_0x0210:
            r0 = r21;
            r0 = r0.q;	 Catch:{ all -> 0x01e4 }
            r2 = r0;
            r0 = r21;
            r0 = r0.r;	 Catch:{ all -> 0x01e4 }
            r12 = r0;
            r12 = r12.c;	 Catch:{ all -> 0x01e4 }
            r2 = r2.a(r12);	 Catch:{ all -> 0x01e4 }
            r2 = (javax.microedition.khronos.opengles.GL10) r2;	 Catch:{ all -> 0x01e4 }
            if (r2 == 0) goto L_0x0275;
        L_0x0226:
            r12 = com.unity3d.player.UnityGLSetup.a;	 Catch:{ all -> 0x01e4 }
            r12.a(r2);	 Catch:{ all -> 0x01e4 }
            r12 = 0;
            r16 = r12;
            r12 = r2;
            r2 = r16;
        L_0x0233:
            if (r11 == 0) goto L_0x0245;
        L_0x0235:
            r0 = r21;
            r0 = r0.p;	 Catch:{ all -> 0x01e4 }
            r11 = r0;
            r0 = r21;
            r0 = r0.q;	 Catch:{ all -> 0x01e4 }
            r13 = r0;
            r13 = r13.d;	 Catch:{ all -> 0x01e4 }
            r11.onSurfaceCreated(r12, r13);	 Catch:{ all -> 0x01e4 }
            r11 = 0;
        L_0x0245:
            if (r9 == 0) goto L_0x0250;
        L_0x0247:
            r0 = r21;
            r0 = r0.p;	 Catch:{ all -> 0x01e4 }
            r9 = r0;
            r9.onSurfaceChanged(r12, r5, r4);	 Catch:{ all -> 0x01e4 }
            r9 = 0;
        L_0x0250:
            r0 = r21;
            r0 = r0.p;	 Catch:{ all -> 0x01e4 }
            r13 = r0;
            r13.onDrawFrame(r12);	 Catch:{ all -> 0x01e4 }
            r0 = r21;
            r0 = r0.q;	 Catch:{ all -> 0x01e4 }
            r13 = r0;
            r13 = r13.b();	 Catch:{ all -> 0x01e4 }
            if (r13 != 0) goto L_0x0264;
        L_0x0263:
            r10 = 1;
        L_0x0264:
            if (r8 == 0) goto L_0x0289;
        L_0x0266:
            r7 = 1;
            r16 = r3;
            r3 = r4;
            r4 = r5;
            r5 = r6;
            r6 = r7;
            r7 = r8;
            r8 = r9;
            r9 = r10;
            r10 = r2;
            r2 = r16;
            goto L_0x003f;
        L_0x0275:
            r2 = com.unity3d.player.UnityGLSetup.a;
            monitor-enter(r2);
            r21.f();	 Catch:{ all -> 0x0283 }
            r21.g();	 Catch:{ all -> 0x0283 }
            monitor-exit(r2);	 Catch:{ all -> 0x0283 }
            goto L_0x0058;
        L_0x0283:
            r3 = move-exception;
            monitor-exit(r2);
            throw r3;
        L_0x0286:
            r2 = move-exception;
            monitor-exit(r3);
            throw r2;
        L_0x0289:
            r16 = r3;
            r3 = r4;
            r4 = r5;
            r5 = r6;
            r6 = r7;
            r7 = r8;
            r8 = r9;
            r9 = r10;
            r10 = r2;
            r2 = r16;
            goto L_0x003f;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.unity3d.player.UnityGLSetup.f.h():void");
        }

        private boolean i() {
            return !this.d && this.e && this.j > 0 && this.k > 0 && (this.m || this.l == 1);
        }

        public final void a() {
            synchronized (UnityGLSetup.a) {
                this.r.c = null;
                this.e = false;
                UnityGLSetup.a.notifyAll();
                while (!this.f && !this.b) {
                    try {
                        UnityGLSetup.a.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        public final void a(int i, int i2) {
            synchronized (UnityGLSetup.a) {
                this.j = i;
                this.k = i2;
                this.r.b = true;
                this.m = true;
                this.n = false;
                UnityGLSetup.a.notifyAll();
                while (!this.b && !this.d && !this.n && this.r.d != null) {
                    f g = this.r.d;
                    Object obj = (g.g && g.h && g.i()) ? 1 : null;
                    if (obj != null) {
                        try {
                            UnityGLSetup.a.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
            }
        }

        public final void a(SurfaceHolder surfaceHolder) {
            synchronized (UnityGLSetup.a) {
                this.r.c = surfaceHolder;
                this.e = true;
                UnityGLSetup.a.notifyAll();
                while (this.f && !this.b) {
                    try {
                        UnityGLSetup.a.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        public final void a(Runnable runnable) {
            if (runnable == null) {
                throw new IllegalArgumentException("r must not be null");
            }
            synchronized (UnityGLSetup.a) {
                this.o.add(runnable);
                UnityGLSetup.a.notifyAll();
            }
        }

        public final void b() {
            synchronized (UnityGLSetup.a) {
                this.c = true;
                UnityGLSetup.a.notifyAll();
                while (!this.b && !this.d) {
                    try {
                        UnityGLSetup.a.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        public final void c() {
            synchronized (UnityGLSetup.a) {
                this.c = false;
                this.m = true;
                this.n = false;
                UnityGLSetup.a.notifyAll();
                while (!this.b && this.d && !this.n) {
                    try {
                        UnityGLSetup.a.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        public final void d() {
            synchronized (UnityGLSetup.a) {
                this.a = true;
                UnityGLSetup.a.notifyAll();
                while (!this.b) {
                    try {
                        UnityGLSetup.a.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        public final void e() {
            this.i = true;
            UnityGLSetup.a.notifyAll();
        }

        public final void run() {
            setName("GLThread " + getId());
            try {
                h();
            } catch (InterruptedException e) {
            } finally {
                UnityGLSetup.a.a(this);
            }
        }
    }

    static class g {
        private boolean a;
        private int b;
        private boolean c;
        private boolean d;
        private f e;

        /* synthetic */ g() {
            this((byte) 0);
        }

        private g(byte b) {
        }

        private void c() {
            if (!this.a) {
                this.b = 131072;
                if (this.b >= 131072) {
                    this.d = true;
                }
                this.a = true;
            }
        }

        public final synchronized void a(f fVar) {
            fVar.b = true;
            if (this.e == fVar) {
                this.e = null;
            }
            notifyAll();
        }

        public final synchronized void a(GL10 gl10) {
            if (!this.c) {
                c();
                if (this.b < 131072) {
                    this.d = !gl10.glGetString(7937).startsWith("Q3Dimension MSM7500 ");
                    notifyAll();
                }
                this.c = true;
            }
        }

        public final synchronized boolean a() {
            return true;
        }

        public final synchronized boolean b() {
            c();
            return !this.d;
        }

        public final boolean b(f fVar) {
            if (this.e == fVar || this.e == null) {
                this.e = fVar;
                notifyAll();
                return true;
            }
            c();
            if (this.d) {
                return true;
            }
            if (this.e != null) {
                this.e.e();
            }
            return false;
        }

        public final void c(f fVar) {
            if (this.e == fVar) {
                this.e = null;
            }
            notifyAll();
        }
    }

    private void c() {
        if (this.d != null) {
            throw new IllegalStateException("setRenderer has already been called for this instance.");
        }
    }

    public final void a() {
        Log.d("UnityGLSetup", "onSurfaceDestroyed()");
        this.d.a();
    }

    public final void a(int i) {
        c();
        this.h = i;
    }

    public final void a(int i, int i2) {
        Log.d("UnityGLSetup", "onSurfaceChanged(" + i + ", " + i2 + ")");
        this.d.a(i, i2);
    }

    public final void a(SurfaceHolder surfaceHolder) {
        Log.d("UnityGLSetup", "onSurfaceCreated()");
        this.d.a(surfaceHolder);
    }

    public final void onDestroy() {
        this.d.d();
    }

    public final void onPause() {
        this.d.b();
    }

    public final void onResume() {
        this.d.c();
    }

    public final void queueEvent(Runnable runnable) {
        this.d.a(runnable);
    }

    public final void setRenderer(Renderer renderer) {
        c();
        if (this.e == null) {
            c aVar = new a(this, 5, 6, 5, 0, 16, 0);
            c();
            this.e = aVar;
        }
        if (this.f == null) {
            this.f = new b();
        }
        if (this.g == null) {
            this.g = new DefaultWindowSurfaceFactory();
        }
        this.d = new f(this, renderer);
        this.d.start();
    }
}
