package com.unity3d.player;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.EGLConfigChooser;
import android.opengl.GLSurfaceView.EGLContextFactory;
import android.util.Log;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;

class d extends GLSurfaceView implements c {
    private static boolean a = false;
    private static boolean b;

    static class a implements EGLConfigChooser {
        private static final int[] a = new int[]{12324, 4, 12323, 4, 12322, 4, 12352, 4, 12344};
        private static final int[] b = new int[]{12324, 5, 12323, 6, 12322, 5, 12352, 1, 12344};
        private static int[] i = new int[1];
        private int c;
        private int d;
        private int e;
        private int f;
        private int g = 16;
        private int h = 0;

        public a(int i, int i2, int i3, int i4, int i5, int i6) {
            this.c = i;
            this.d = i2;
            this.e = i3;
            this.f = i4;
        }

        private static int a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig, int i, int i2) {
            return egl10.eglGetConfigAttrib(eGLDisplay, eGLConfig, i, i) ? i[0] : 0;
        }

        private EGLConfig a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig[] eGLConfigArr) {
            for (EGLConfig eGLConfig : eGLConfigArr) {
                int a = a(egl10, eGLDisplay, eGLConfig, 12325, 0);
                int a2 = a(egl10, eGLDisplay, eGLConfig, 12326, 0);
                if (a >= this.g && a2 >= this.h) {
                    a = a(egl10, eGLDisplay, eGLConfig, 12324, 0);
                    a2 = a(egl10, eGLDisplay, eGLConfig, 12323, 0);
                    int a3 = a(egl10, eGLDisplay, eGLConfig, 12322, 0);
                    int a4 = a(egl10, eGLDisplay, eGLConfig, 12321, 0);
                    if (a == this.c && a2 == this.d && a3 == this.e && a4 == this.f) {
                        return eGLConfig;
                    }
                }
            }
            return null;
        }

        protected static void printConfig(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig) {
            int[] iArr = new int[]{12320, 12321, 12322, 12323, 12324, 12325, 12326, 12327, 12328, 12329, 12330, 12331, 12332, 12333, 12334, 12335, 12336, 12337, 12338, 12339, 12340, 12343, 12342, 12341, 12345, 12346, 12347, 12348, 12349, 12350, 12351, 12352, 12354};
            String[] strArr = new String[]{"EGL_BUFFER_SIZE", "EGL_ALPHA_SIZE", "EGL_BLUE_SIZE", "EGL_GREEN_SIZE", "EGL_RED_SIZE", "EGL_DEPTH_SIZE", "EGL_STENCIL_SIZE", "EGL_CONFIG_CAVEAT", "EGL_CONFIG_ID", "EGL_LEVEL", "EGL_MAX_PBUFFER_HEIGHT", "EGL_MAX_PBUFFER_PIXELS", "EGL_MAX_PBUFFER_WIDTH", "EGL_NATIVE_RENDERABLE", "EGL_NATIVE_VISUAL_ID", "EGL_NATIVE_VISUAL_TYPE", "EGL_PRESERVED_RESOURCES", "EGL_SAMPLES", "EGL_SAMPLE_BUFFERS", "EGL_SURFACE_TYPE", "EGL_TRANSPARENT_TYPE", "EGL_TRANSPARENT_RED_VALUE", "EGL_TRANSPARENT_GREEN_VALUE", "EGL_TRANSPARENT_BLUE_VALUE", "EGL_BIND_TO_TEXTURE_RGB", "EGL_BIND_TO_TEXTURE_RGBA", "EGL_MIN_SWAP_INTERVAL", "EGL_MAX_SWAP_INTERVAL", "EGL_LUMINANCE_SIZE", "EGL_ALPHA_MASK_SIZE", "EGL_COLOR_BUFFER_TYPE", "EGL_RENDERABLE_TYPE", "EGL_CONFORMANT"};
            int[] iArr2 = new int[1];
            for (int i = 0; i < iArr.length; i++) {
                int i2 = iArr[i];
                Object obj = strArr[i];
                if (egl10.eglGetConfigAttrib(eGLDisplay, eGLConfig, i2, iArr2)) {
                    d.a(String.format("  %s: %d\n", new Object[]{obj, Integer.valueOf(iArr2[0])}));
                } else {
                    do {
                    } while (egl10.eglGetError() != 12288);
                }
            }
        }

        public final EGLConfig chooseConfig(EGL10 egl10, EGLDisplay eGLDisplay) {
            int[] iArr = d.b ? a : b;
            int[] iArr2 = new int[1];
            egl10.eglChooseConfig(eGLDisplay, iArr, null, 0, iArr2);
            int i = iArr2[0];
            if (i <= 0) {
                throw new IllegalArgumentException("No configs match configSpec");
            }
            EGLConfig[] eGLConfigArr = new EGLConfig[i];
            egl10.eglChooseConfig(eGLDisplay, iArr, eGLConfigArr, i, iArr2);
            return a(egl10, eGLDisplay, eGLConfigArr);
        }
    }

    static class b implements EGLContextFactory {
        private static int a = 12440;

        /* synthetic */ b() {
            this((byte) 0);
        }

        private b(byte b) {
        }

        public final EGLContext createContext(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig) {
            String str = " ";
            int a = a.a(egl10, eGLDisplay, eGLConfig, 12320, 0);
            int a2 = a.a(egl10, eGLDisplay, eGLConfig, 12324, 0);
            int a3 = a.a(egl10, eGLDisplay, eGLConfig, 12323, 0);
            int a4 = a.a(egl10, eGLDisplay, eGLConfig, 12322, 0);
            int a5 = a.a(egl10, eGLDisplay, eGLConfig, 12321, 0);
            int a6 = a.a(egl10, eGLDisplay, eGLConfig, 12325, 0);
            int a7 = a.a(egl10, eGLDisplay, eGLConfig, 12326, 0);
            StringBuilder append = new StringBuilder().append(a5 == 0 ? "RGB" : "RGBA").append(a);
            String str2 = " ";
            append = append.append(str).append(Integer.toString(a2)).append(Integer.toString(a3)).append(Integer.toString(a4)).append(a5 == 0 ? "" : Integer.toString(a5));
            String str3 = " ";
            d.a("Creating OpenGL ES " + (d.b ? "2.0" : "1.x") + " context (" + append.append(str).append(Integer.toString(a6)).append("/").append(Integer.toString(a7)).toString() + ")");
            d.a("Before eglCreateContext", egl10);
            int[] iArr = new int[3];
            iArr[0] = a;
            iArr[1] = d.b ? 2 : 1;
            iArr[2] = 12344;
            EGLContext eglCreateContext = egl10.eglCreateContext(eGLDisplay, eGLConfig, EGL10.EGL_NO_CONTEXT, iArr);
            d.a("After eglCreateContext", egl10);
            return eglCreateContext;
        }

        public final void destroyContext(EGL10 egl10, EGLDisplay eGLDisplay, EGLContext eGLContext) {
            egl10.eglDestroyContext(eGLDisplay, eGLContext);
        }
    }

    public d(Context context, int i, boolean z, boolean z2) {
        super(context);
        a = z2;
        b = i == 2;
        if (z) {
            getHolder().setFormat(-3);
        } else {
            getHolder().setFormat(-1);
        }
        setEGLContextFactory(new b());
        setEGLConfigChooser(z ? new a(8, 8, 8, 8, 16, 0) : new a(5, 6, 5, 0, 16, 0));
    }

    static /* synthetic */ void a(String str) {
        if (!a) {
            Log.d("Unity", str);
        }
    }

    static /* synthetic */ void a(String str, EGL10 egl10) {
        while (egl10.eglGetError() != 12288) {
            Log.e("Unity", String.format("%s: EGL error: 0x%x", new Object[]{str, Integer.valueOf(egl10.eglGetError())}));
        }
    }

    public void onDestroy() {
        super.onDetachedFromWindow();
    }

    protected void onDetachedFromWindow() {
        String str = "onDetachedFromWindow";
        if (!a) {
            Log.d("Unity", str);
        }
    }
}
