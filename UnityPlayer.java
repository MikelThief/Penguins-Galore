package com.unity3d.player;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.opengl.GLSurfaceView.Renderer;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.os.Process;
import android.os.SystemClock;
import android.os.Vibrator;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.MimeTypeMap;
import android.widget.FrameLayout;
import com.android.vending.licensing.a;
import com.android.vending.licensing.d;
import com.android.vending.licensing.e;
import com.android.vending.licensing.h;
import com.android.vending.licensing.k;
import com.android.vending.licensing.l;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import org.fmod.FMODAudioDevice;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

public class UnityPlayer extends FrameLayout implements SensorEventListener, Renderer, TextWatcher {
    private static boolean K = false;
    private static a M;
    private static final int[] N = new int[]{1, 1, 0, 1, -1, 1, 1, 0, -1, -1, 0, 1, 1, -1, 1, 0};
    public static Activity currentActivity = null;
    private static boolean o = false;
    private static boolean p = false;
    private static int[] y;
    private int A = 0;
    private String B = null;
    private String C = null;
    private NetworkInfo D = null;
    private String E = null;
    private d F = null;
    private boolean G = false;
    private boolean H = false;
    private boolean I = false;
    private Bundle J = new Bundle();
    private boolean L = true;
    private float O;
    private float P;
    private float Q;
    private long R;
    private Runnable S = new f(this);
    private int T;
    private Runnable U = new g(this);
    private int V = 0;
    private float W = 0.0f;
    private Location X;
    private List Y = new ArrayList();
    private int Z = 0;
    b a = null;
    private boolean aa = false;
    private boolean b = false;
    private boolean c = false;
    private boolean d = false;
    private StringBuilder e = new StringBuilder();
    private Bundle f = null;
    private SharedPreferences g = null;
    private Context h;
    private c i;
    private WakeLock j;
    private SensorManager k;
    private WindowManager l;
    private FMODAudioDevice m;
    private Vibrator n = null;
    private boolean q = false;
    private boolean r = true;
    private int s;
    private int t;
    private int u = 0;
    private int v = 0;
    private float w = 1.0f;
    private float x = 1.0f;
    private boolean z = false;

    class AnonymousClass10 implements OnClickListener {
        private /* synthetic */ SharedPreferences a;

        AnonymousClass10(SharedPreferences sharedPreferences) {
            this.a = sharedPreferences;
        }

        public final void onClick(DialogInterface dialogInterface, int i) {
            if (this.a != null) {
                Editor edit = this.a.edit();
                edit.putBoolean("warningSoftFloat", true);
                edit.commit();
            }
        }
    }

    class AnonymousClass17 extends d {
        private /* synthetic */ View a;

        AnonymousClass17(Context context, int i, boolean z, boolean z2, View view) {
            this.a = view;
            super(context, i, z, z2);
        }

        public final boolean onKeyPreIme(int i, KeyEvent keyEvent) {
            return this.a.onKeyPreIme(i, keyEvent) || super.onKeyPreIme(i, keyEvent);
        }
    }

    class AnonymousClass9 implements Runnable {
        private /* synthetic */ boolean a;
        private /* synthetic */ SurfaceView b;
        private /* synthetic */ int c;
        private /* synthetic */ int d;

        AnonymousClass9(boolean z, SurfaceView surfaceView, int i, int i2) {
            this.a = z;
            this.b = surfaceView;
            this.c = i;
            this.d = i2;
        }

        public final void run() {
            if (this.a) {
                this.b.getHolder().setSizeFromLayout();
            } else {
                this.b.getHolder().setFixedSize(this.c, this.d);
            }
            this.b.invalidate();
        }
    }

    static {
        M = null;
        M = new a();
    }

    public UnityPlayer(Context context) {
        String str = "Unity";
        super(context);
        this.h = context;
        String packageName = this.h.getPackageName();
        if (context instanceof Activity) {
            currentActivity = (Activity) context;
            this.f = currentActivity.getIntent().getExtras();
            packageName = currentActivity.getLocalClassName();
        }
        this.g = context.getSharedPreferences(packageName, 0);
        boolean z = this.f != null && this.f.getBoolean("cleanedLogFile");
        K = z;
        packageName = "cpuarch.GetCpuFeatures() = " + M.a();
        if (!K) {
            String str2 = "Unity";
            Log.d(str, packageName);
        }
        packageName = "cpuarch.GetTotalMemory() = " + M.c();
        if (!K) {
            str2 = "Unity";
            Log.d(str, packageName);
        }
        b();
        System.setProperty("apk", this.h.getPackageCodePath());
        d();
    }

    public static native void UnitySendMessage(String str, String str2, String str3);

    private static float a(Class cls, Class cls2) {
        "comparing " + cls.getName() + " with " + cls2.getName();
        if (cls.equals(cls2)) {
            return 1.0f;
        }
        if (!(cls.isPrimitive() || cls2.isPrimitive())) {
            try {
                if (cls.asSubclass(cls2) != null) {
                    return 0.5f;
                }
            } catch (ClassCastException e) {
                e.toString();
            }
            try {
                if (cls2.asSubclass(cls) != null) {
                    return 0.1f;
                }
            } catch (ClassCastException e2) {
                e2.toString();
            }
        }
        return 0.0f;
    }

    private static float a(Class cls, Class[] clsArr, Class[] clsArr2) {
        int i = 0;
        if (clsArr2.length == 0) {
            return 0.1f;
        }
        int length = (clsArr == null ? 0 : clsArr.length) + 1;
        if (length != clsArr2.length) {
            "sig length mismatch " + length + " != " + clsArr2.length;
            return 0.0f;
        }
        float f = 1.0f;
        if (clsArr != null) {
            float f2 = 1.0f;
            length = 0;
            while (length < clsArr.length) {
                length++;
                f2 = a(clsArr[length], clsArr2[i]) * f2;
                i++;
            }
            f = f2;
        }
        f *= a(cls, clsArr2[clsArr2.length - 1]);
        "rank is " + f;
        return f;
    }

    private static Class a(String str, int[] iArr) {
        "parse '" + str + "' at " + iArr[0];
        while (iArr[0] < str.length()) {
            int i = iArr[0];
            iArr[0] = i + 1;
            char charAt = str.charAt(i);
            if (charAt != '(' && charAt != ')') {
                String replace;
                if (charAt == 'L') {
                    i = str.indexOf(59, iArr[0]);
                    if (i != -1) {
                        String substring = str.substring(iArr[0], i);
                        iArr[0] = i + 1;
                        replace = substring.replace('/', '.');
                        "typename = " + replace;
                        try {
                            return Class.forName(replace);
                        } catch (ClassNotFoundException e) {
                            e.toString();
                        }
                    }
                } else if (charAt == 'Z') {
                    return Boolean.TYPE;
                } else {
                    if (charAt == 'I') {
                        return Integer.TYPE;
                    }
                    if (charAt == 'F') {
                        return Float.TYPE;
                    }
                    if (charAt == 'V') {
                        return Void.TYPE;
                    }
                    if (charAt == 'B') {
                        return Byte.TYPE;
                    }
                    if (charAt == 'S') {
                        return Short.TYPE;
                    }
                    if (charAt == 'J') {
                        return Long.TYPE;
                    }
                    if (charAt == 'D') {
                        return Double.TYPE;
                    }
                    if (charAt == '[') {
                        return Array.newInstance(a(str, iArr), 0).getClass();
                    }
                    replace = "parseType; " + charAt + " is not known!";
                    if (!K) {
                        Log.d("Unity", replace);
                    }
                }
                return null;
            }
        }
        return null;
    }

    private void a(int i, boolean z) {
        int i2;
        initJni();
        this.j = ((PowerManager) this.h.getSystemService("power")).newWakeLock(26, "DoNotDimScreen");
        PlayerPrefs playerPrefs = new PlayerPrefs(this.g);
        nativeInitWWW(WWW.class);
        if (this.i == null) {
            View anonymousClass17 = new AnonymousClass17(this.h, i, z, K, this);
            anonymousClass17.setFocusable(true);
            anonymousClass17.setFocusableInTouchMode(true);
            addView(anonymousClass17);
            this.i = anonymousClass17;
        }
        this.i.setRenderer(this);
        this.k = (SensorManager) this.h.getSystemService("sensor");
        this.l = (WindowManager) this.h.getSystemService("window");
        InputStream open;
        try {
            final int i3 = this.J.getInt("splash_mode");
            open = this.h.getAssets().open("bin/Data/splash.png");
            Bitmap decodeStream = BitmapFactory.decodeStream(open);
            try {
                open.close();
            } catch (IOException e) {
            }
            final int width = decodeStream.getWidth();
            final int height = decodeStream.getHeight();
            int rowBytes = decodeStream.getRowBytes();
            int i4 = rowBytes * height;
            i2 = rowBytes / width;
            Buffer allocate = ByteBuffer.allocate(i4);
            decodeStream.copyPixelsToBuffer(allocate);
            if (!(decodeStream.getConfig() == Config.RGB_565 && i2 == 2)) {
                String str = "image error";
                if (!K) {
                    Log.d("Unity", str);
                }
            }
            final int numberOfLeadingZeros = 1 << (32 - Integer.numberOfLeadingZeros(width - 1));
            final int numberOfLeadingZeros2 = 1 << (32 - Integer.numberOfLeadingZeros(height - 1));
            i2 *= numberOfLeadingZeros;
            int i5 = i2 * numberOfLeadingZeros2;
            if (i5 != i4) {
                final ByteBuffer allocate2 = ByteBuffer.allocate(i5);
                Object array = allocate.array();
                Object array2 = allocate2.array();
                for (int i6 = 0; i6 < height; i6++) {
                    System.arraycopy(array, i6 * rowBytes, array2, i6 * i2, rowBytes);
                }
                i2 = i;
                this.i.queueEvent(new Runnable(this) {
                    private /* synthetic */ UnityPlayer h;

                    public final void run() {
                        this.h.nativeInit(i2, allocate2.array(), numberOfLeadingZeros, numberOfLeadingZeros2, width, height, i3);
                    }
                });
            } else {
                numberOfLeadingZeros = i;
                final Buffer buffer = allocate;
                this.i.queueEvent(new Runnable(this) {
                    private /* synthetic */ UnityPlayer f;

                    public final void run() {
                        this.f.nativeInit(numberOfLeadingZeros, buffer.array(), width, height, width, height, i3);
                    }
                });
            }
            decodeStream.recycle();
        } catch (Exception e2) {
            String str2 = "Unable to locate splash image. " + e2.getLocalizedMessage();
            if (!K) {
                Log.d("Unity", str2);
            }
            c();
        } catch (Throwable th) {
            try {
                open.close();
            } catch (IOException e3) {
            }
        }
        nativeSetExtras(this.f);
        if (VERSION.SDK_INT >= 9) {
            try {
                boolean isAssignableFrom = Class.forName("android.app.NativeActivity").isAssignableFrom(this.h.getClass());
                Class cls = Class.forName("android.view.InputDevice");
                int[] iArr = (int[]) cls.getDeclaredMethod("getDeviceIds", new Class[0]).invoke(cls, new Object[0]);
                for (i2 = 0; i2 < iArr.length && isAssignableFrom; i2++) {
                    Object invoke = cls.getMethod("getDevice", new Class[]{Integer.TYPE}).invoke(cls, new Object[]{Integer.valueOf(iArr[i2])});
                    if (invoke != null && (((Integer) invoke.getClass().getMethod("getSources", new Class[0]).invoke(invoke, new Object[0])).intValue() & 1048584) == 1048584) {
                        Method method = invoke.getClass().getMethod("getMotionRange", new Class[]{Integer.TYPE});
                        Object invoke2 = method.invoke(invoke, new Object[]{Integer.valueOf(0)});
                        invoke = method.invoke(invoke, new Object[]{Integer.valueOf(1)});
                        if (!(invoke2 == null || invoke == null)) {
                            method = invoke2.getClass().getMethod("getRange", new Class[0]);
                            nativeEnableTouchpad(((Float) method.invoke(invoke2, new Object[0])).floatValue(), ((Float) method.invoke(invoke, new Object[0])).floatValue());
                        }
                    }
                }
            } catch (Exception e22) {
                e22.printStackTrace();
            }
        }
        resume();
    }

    private void a(Location location) {
        if (location != null) {
            Object obj;
            Location location2 = this.X;
            if (location2 == null) {
                obj = 1;
            } else {
                long time = location.getTime() - location2.getTime();
                Object obj2 = time > 120000 ? 1 : null;
                Object obj3 = time < -120000 ? 1 : null;
                Object obj4 = time > 0 ? 1 : null;
                int i;
                if (obj2 != null) {
                    i = 1;
                } else {
                    if (obj3 == null) {
                        int accuracy = (int) (location.getAccuracy() - location2.getAccuracy());
                        obj2 = accuracy > 0 ? 1 : null;
                        obj3 = accuracy < 0 ? 1 : null;
                        accuracy = (accuracy > 200 ? 1 : 0) | (location.getAccuracy() == 0.0f ? 1 : 0);
                        String provider = location.getProvider();
                        String provider2 = location2.getProvider();
                        boolean equals = provider == null ? provider2 == null : provider.equals(provider2);
                        if (obj3 != null) {
                            i = 1;
                        } else if (obj4 != null && obj2 == null) {
                            i = 1;
                        } else if (obj4 != null && accuracy == 0 && r0) {
                            i = 1;
                        }
                    }
                    obj = null;
                }
            }
            if (obj != null) {
                this.X = location;
                nativeSetLocation((float) location.getLatitude(), (float) location.getLongitude(), (float) location.getAltitude(), location.getAccuracy(), ((double) location.getTime()) / 1000.0d);
            }
        }
    }

    private void a(Runnable runnable) {
        if (this.h instanceof Activity) {
            ((Activity) this.h).runOnUiThread(runnable);
            return;
        }
        String str = "Not running Unity from an Activity; ignored...";
        if (!K) {
            Log.d("Unity", str);
        }
    }

    static /* synthetic */ void a(String str) {
        if (!K) {
            Log.d("Unity", str);
        }
    }

    private boolean a(int i, KeyEvent keyEvent, final boolean z) {
        if (!this.q) {
            return true;
        }
        if (this.z) {
            return false;
        }
        if (i == 25 || i == 24) {
            return z ? super.onKeyDown(i, keyEvent) : super.onKeyUp(i, keyEvent);
        } else {
            int i2 = (i == 4 && keyEvent.getMetaState() == 2) ? 101 : i;
            if (i2 == 23) {
                this.i.queueEvent(new Runnable(this) {
                    private /* synthetic */ UnityPlayer b;

                    public final void run() {
                        this.b.nativeJoyButtonState(0, 0, z);
                    }
                });
                return true;
            } else if (i2 >= 99 && i2 <= 101) {
                i2 = (i2 + 1) - 99;
                this.i.queueEvent(new Runnable(this) {
                    private /* synthetic */ UnityPlayer c;

                    public final void run() {
                        this.c.nativeJoyButtonState(0, i2, z);
                    }
                });
                return true;
            } else if (i2 >= KeyEvent.getMaxKeyCode()) {
                return false;
            } else {
                int unicodeChar = keyEvent.getUnicodeChar(keyEvent.getMetaState());
                int i3 = (unicodeChar != 0 || i2 == 67) ? 0 : 1;
                if (!this.c || i3 != 0) {
                    i2 = y[i2];
                    this.i.queueEvent(new Runnable(this) {
                        private /* synthetic */ UnityPlayer c;

                        public final void run() {
                            this.c.nativeKeyState(i2, 0, z);
                        }
                    });
                    return true;
                } else if (!z) {
                    return false;
                } else {
                    if (i2 == 66 && !this.d) {
                        i2 = 1;
                    } else if (unicodeChar != 0) {
                        this.e.append((char) unicodeChar);
                        i2 = 0;
                    } else {
                        if (i2 == 67 && this.e.length() > 0) {
                            this.e.setLength(this.e.length() - 1);
                        }
                        i2 = 0;
                    }
                    reportSoftInputStr(this.e.toString(), i2);
                    return true;
                }
            }
        }
    }

    private void b() {
        String name;
        String str = "Unity";
        try {
            InputStream open = this.h.getAssets().open("bin/Data/settings.xml");
            XmlPullParserFactory newInstance = XmlPullParserFactory.newInstance();
            newInstance.setNamespaceAware(true);
            XmlPullParser newPullParser = newInstance.newPullParser();
            newPullParser.setInput(open, null);
            String str2 = null;
            String str3 = null;
            for (int eventType = newPullParser.getEventType(); eventType != 1; eventType = newPullParser.next()) {
                if (eventType == 2) {
                    name = newPullParser.getName();
                    str3 = str2;
                    for (int i = 0; i < newPullParser.getAttributeCount(); i++) {
                        if (newPullParser.getAttributeName(i).equalsIgnoreCase("name")) {
                            str3 = newPullParser.getAttributeValue(i);
                        }
                    }
                    str2 = name;
                    name = str3;
                } else if (eventType == 3) {
                    name = str2;
                    str2 = null;
                } else if (eventType != 4 || str2 == null) {
                    name = str2;
                    str2 = str3;
                } else {
                    name = str2 + " = " + newPullParser.getText() + " (" + str3 + ")";
                    if (!K) {
                        Log.d("Unity", name);
                    }
                    if (str3.equalsIgnoreCase("integer")) {
                        this.J.putInt(str2, Integer.parseInt(newPullParser.getText()));
                    } else if (str3.equalsIgnoreCase("string")) {
                        this.J.putString(str2, newPullParser.getText());
                    } else if (str3.equalsIgnoreCase("bool")) {
                        this.J.putBoolean(str2, Boolean.parseBoolean(newPullParser.getText()));
                    } else if (str3.equalsIgnoreCase("float")) {
                        this.J.putFloat(str2, Float.parseFloat(newPullParser.getText()));
                    }
                    name = null;
                    str2 = str3;
                }
                str3 = str2;
                str2 = name;
            }
        } catch (Exception e) {
            name = "Unable to locate player settings. " + e.getLocalizedMessage();
            if (!K) {
                String str4 = "Unity";
                Log.d(str, name);
            }
            c();
        }
    }

    private static Class[] b(String str) {
        int[] iArr = new int[]{0};
        ArrayList arrayList = new ArrayList();
        while (iArr[0] < str.length()) {
            Class a = a(str, iArr);
            if (a == null) {
                break;
            }
            "found " + a.getName();
            arrayList.add(a);
        }
        Class[] clsArr = new Class[arrayList.size()];
        Iterator it = arrayList.iterator();
        int i = 0;
        while (it.hasNext()) {
            Class cls = (Class) it.next();
            int i2 = i + 1;
            clsArr[i] = cls;
            "copied " + cls.getName();
            i = i2;
        }
        return clsArr;
    }

    private void c() {
        if (this.h instanceof Activity) {
            ((Activity) this.h).finish();
        }
    }

    private static void d() {
        String str;
        System.loadLibrary("mono");
        int a = M.a();
        o = true;
        if ((a & 32) == 0 || (a & 2) == 0) {
            o = false;
        } else {
            try {
                System.loadLibrary("unity");
            } catch (UnsatisfiedLinkError e) {
                str = "Unable to load libraries: " + e;
                if (!K) {
                    Log.d("Unity", str);
                }
                o = false;
            }
        }
        if (!o) {
            o = true;
            p = true;
            try {
                System.loadLibrary("unity_soft");
            } catch (UnsatisfiedLinkError e2) {
                str = "Unable to load libraries: " + e2;
                if (!K) {
                    Log.d("Unity", str);
                }
                o = false;
            }
        }
        y = new int[Math.max(110, KeyEvent.getMaxKeyCode())];
        for (a = 0; a < KeyEvent.getMaxKeyCode(); a++) {
            y[a] = 0;
        }
        y[7] = 48;
        y[8] = 49;
        y[9] = 50;
        y[10] = 51;
        y[11] = 52;
        y[12] = 53;
        y[13] = 54;
        y[14] = 55;
        y[15] = 56;
        y[16] = 57;
        y[29] = 97;
        y[30] = 98;
        y[31] = 99;
        y[32] = 100;
        y[33] = 101;
        y[34] = 102;
        y[35] = 103;
        y[36] = 104;
        y[37] = 105;
        y[38] = 106;
        y[39] = 107;
        y[40] = 108;
        y[41] = 109;
        y[42] = 110;
        y[43] = 111;
        y[44] = 112;
        y[45] = 113;
        y[46] = 114;
        y[47] = 115;
        y[48] = 116;
        y[49] = 117;
        y[50] = 118;
        y[51] = 119;
        y[52] = 120;
        y[53] = 121;
        y[54] = 122;
        y[75] = 39;
        y[77] = 64;
        y[73] = 92;
        y[55] = 44;
        y[66] = 10;
        y[70] = 61;
        y[68] = 96;
        y[71] = 91;
        y[69] = 45;
        y[56] = 46;
        y[81] = 43;
        y[18] = 35;
        y[72] = 93;
        y[74] = 59;
        y[76] = 47;
        y[62] = 32;
        y[17] = 42;
        y[61] = 9;
        y[59] = 304;
        y[60] = 303;
        y[57] = 308;
        y[58] = 307;
        y[67] = 8;
        y[20] = 274;
        y[19] = 273;
        y[21] = 276;
        y[22] = 275;
        y[4] = 27;
        y[82] = 319;
        y[26] = 320;
        y[3] = 278;
        y[1] = 310;
        y[2] = 309;
        y[84] = 283;
        y[5] = 284;
        y[6] = 285;
        y[27] = 286;
        y[80] = 287;
        y[79] = 288;
        y[102] = 304;
        y[103] = 303;
        y[104] = 308;
        y[105] = 307;
        y[108] = 13;
        y[109] = 19;
    }

    protected static Constructor getConstructorID(Class cls, String str) {
        "getConstructorID(\"" + cls.getName() + "\", \"" + str + "\")";
        Class[] b = b(str);
        float f = 0.0f;
        Constructor constructor = null;
        for (Constructor constructor2 : cls.getConstructors()) {
            float a = a(Void.TYPE, constructor2.getParameterTypes(), b);
            if (a == 1.0f) {
                return constructor2;
            }
            if (a > f) {
                f = a;
                constructor = constructor2;
            }
        }
        if (constructor != null) {
            "best match = " + constructor.getName() + " @ " + f;
        }
        return constructor;
    }

    protected static Field getFieldID(Class cls, String str, String str2, boolean z) {
        String str3 = "\", \"";
        String str4 = "\", \"";
        str4 = "\", \"";
        "getFieldID(\"" + cls.getName() + str3 + str + str3 + str2 + "\", " + (z ? "static)" : "non-static)");
        Class[] b = b(str2);
        float f = 0.0f;
        Field field = null;
        Class cls2 = cls;
        while (cls2 != null) {
            Field field2 = field;
            float f2 = f;
            for (Field field3 : cls2.getDeclaredFields()) {
                if (z == Modifier.isStatic(field3.getModifiers()) && field3.getName().compareTo(str) == 0) {
                    float a = a(field3.getType(), null, b);
                    if (a == 1.0f) {
                        return field3;
                    }
                    if (a > f2) {
                        f2 = a;
                        field2 = field3;
                    }
                }
            }
            if (cls2.isPrimitive() || cls2.isInterface() || cls2.equals(Object.class) || cls2.equals(Void.TYPE)) {
                return field2;
            }
            cls2 = cls2.getSuperclass();
            f = f2;
            field = field2;
        }
        if (field != null) {
            "best match = " + field.getName() + " @ " + f;
        }
        return field;
    }

    protected static Method getMethodID(Class cls, String str, String str2, boolean z) {
        String str3 = "\", \"";
        String str4 = "\", \"";
        str4 = "\", \"";
        "getMethodID(\"" + cls.getName() + str3 + str + str3 + str2 + "\", " + (z ? "static)" : "non-static)");
        Class[] b = b(str2);
        Class cls2 = cls;
        float f = 0.0f;
        Method method = null;
        while (cls2 != null) {
            "clazz = " + cls2.getName();
            Method method2 = method;
            float f2 = f;
            for (Method method3 : cls2.getDeclaredMethods()) {
                if (z == Modifier.isStatic(method3.getModifiers()) && method3.getName().compareTo(str) == 0) {
                    float a = a(method3.getReturnType(), method3.getParameterTypes(), b);
                    if (a == 1.0f) {
                        return method3;
                    }
                    if (a > f2) {
                        f2 = a;
                        method2 = method3;
                    }
                }
            }
            if (cls2.isPrimitive() || cls2.isInterface() || cls2.equals(Object.class) || cls2.equals(Void.TYPE)) {
                return method2;
            }
            cls2 = cls2.getSuperclass();
            f = f2;
            method = method2;
        }
        if (method != null) {
            "best match = " + method.getName() + " @ " + f;
        }
        return method;
    }

    private final native void initJni();

    private final native void nativeDeviceOrientation(int i);

    private final native void nativeDone();

    private final native void nativeEnableTouchpad(float f, float f2);

    private final native void nativeFile(String str);

    private final native String nativeGetLicenseDeviceId();

    private final native String nativeGetLicenseKey();

    private final native int nativeGetLicensePolicy();

    private final native void nativeInit(int i, byte[] bArr, int i2, int i3, int i4, int i5, int i6);

    private final native void nativeInitWWW(Class cls);

    private final native void nativeJoyButtonState(int i, int i2, boolean z);

    private final native void nativeKeyState(int i, int i2, boolean z);

    private final native void nativePause();

    private final native void nativeRecreateGfxState();

    private final native boolean nativeRender();

    private final native void nativeResize(int i, int i2);

    private final native void nativeResume();

    private final native void nativeSensor(float f, float f2, float f3, long j);

    private final native void nativeSetExtras(Bundle bundle);

    private final native void nativeSetInputString(String str);

    private final native void nativeSoftInputClosed();

    private final native void nativeTouch(int i, float f, float f2, int i2, long j, int i3);

    static /* synthetic */ void q(UnityPlayer unityPlayer) {
        unityPlayer.Z = 2;
        unityPlayer.nativeSetLocationStatus(2);
    }

    private final native void unityAndroidInit(String str, String str2);

    protected boolean Location_IsServiceEnabledByUser() {
        return !((LocationManager) this.h.getSystemService("location")).getProviders(new Criteria(), true).isEmpty();
    }

    protected void Location_SetDesiredAccuracy(float f) {
        if (f < 100.0f) {
            this.V = 1;
        } else if (f < 500.0f) {
            this.V = 1;
        } else {
            this.V = 2;
        }
    }

    protected void Location_SetDistanceFilter(float f) {
        this.W = f;
    }

    protected void Location_StartService() {
        this.aa = false;
        if (!this.Y.isEmpty()) {
            String str = "Location_StartService already started!";
            if (!K) {
                Log.d("Unity", str);
            }
        } else if (Location_IsServiceEnabledByUser()) {
            LocationManager locationManager = (LocationManager) this.h.getSystemService("location");
            this.Z = 1;
            nativeSetLocationStatus(1);
            List<String> providers = locationManager.getProviders(true);
            if (providers.isEmpty()) {
                this.Z = 3;
                nativeSetLocationStatus(3);
                return;
            }
            LocationProvider locationProvider;
            if (this.V == 2) {
                for (String provider : providers) {
                    LocationProvider provider2 = locationManager.getProvider(provider);
                    if (provider2.getAccuracy() == 2) {
                        locationProvider = provider2;
                        break;
                    }
                }
            }
            locationProvider = null;
            for (String provider3 : providers) {
                if (locationProvider == null || locationManager.getProvider(provider3).getAccuracy() != 1) {
                    a(locationManager.getLastKnownLocation(provider3));
                    LocationListener anonymousClass15 = new LocationListener(this) {
                        private /* synthetic */ UnityPlayer a;

                        {
                            this.a = r1;
                        }

                        public final void onLocationChanged(Location location) {
                            UnityPlayer.q(this.a);
                            this.a.a(location);
                        }

                        public final void onProviderDisabled(String str) {
                            this.a.X = null;
                        }

                        public final void onProviderEnabled(String str) {
                        }

                        public final void onStatusChanged(String str, int i, Bundle bundle) {
                        }
                    };
                    locationManager.requestLocationUpdates(provider3, 0, this.W, anonymousClass15, this.h.getMainLooper());
                    this.Y.add(anonymousClass15);
                }
            }
        } else {
            this.Z = 3;
            nativeSetLocationStatus(3);
        }
    }

    protected void Location_StopService() {
        LocationManager locationManager = (LocationManager) this.h.getSystemService("location");
        for (LocationListener removeUpdates : this.Y) {
            locationManager.removeUpdates(removeUpdates);
        }
        this.Y.clear();
        this.X = null;
        this.Z = 0;
        nativeSetLocationStatus(0);
    }

    public void afterTextChanged(Editable editable) {
        reportSoftInputStr(editable.toString(), 0);
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void configurationChanged(Configuration configuration) {
        String str = "onConfigurationChanged";
        if (!K) {
            Log.d("Unity", str);
        }
        if (this.i instanceof SurfaceView) {
            ((SurfaceView) this.i).getHolder().setSizeFromLayout();
        }
        if (this.c && configuration.hardKeyboardHidden == 2) {
            ((InputMethodManager) this.h.getSystemService("input_method")).toggleSoftInput(0, 1);
        }
    }

    protected String getAndroidID() {
        return Build.ID;
    }

    protected String getAndroidVersion() {
        return VERSION.RELEASE;
    }

    protected int getCPUCount() {
        return Runtime.getRuntime().availableProcessors();
    }

    protected String getCPUType() {
        return M.b();
    }

    protected String getCacheDir() {
        String externalStorageState = Environment.getExternalStorageState();
        if (this.h.checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0 && "mounted".equals(externalStorageState)) {
            File file = new File(Environment.getExternalStorageDirectory().getPath() + "/Android/data/" + this.h.getPackageName() + "/cache/");
            if (file.exists() || file.mkdirs()) {
                return file.getPath();
            }
        }
        return this.h.getCacheDir().getPath();
    }

    protected String getDeviceUniqueIdentifier() {
        String str;
        try {
            if (this.B == null) {
                this.B = ((TelephonyManager) this.h.getSystemService("phone")).getDeviceId();
            }
            str = this.B;
            if (!(str == null || str.length() == 0)) {
                return str;
            }
        } catch (Exception e) {
            str = "android.permission.READ_PHONE_STATE not available?";
            if (!K) {
                Log.d("Unity", str);
            }
        }
        return Secure.getString(this.h.getContentResolver(), "android_id");
    }

    protected String getFilesDir() {
        String externalStorageState = Environment.getExternalStorageState();
        if (this.h.checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0 && "mounted".equals(externalStorageState)) {
            File file = new File(Environment.getExternalStorageDirectory().getPath() + "/Android/data/" + this.h.getPackageName() + "/files");
            if (file.exists() || file.mkdirs()) {
                return file.getPath();
            }
        }
        return this.h.getFilesDir().getPath();
    }

    protected String getHardwareModel() {
        if (this.C == null) {
            this.C = Build.MANUFACTURER + " " + Build.MODEL;
        }
        return this.C;
    }

    protected int getInternetReachability() {
        try {
            if (this.D == null) {
                this.D = ((ConnectivityManager) this.h.getSystemService("connectivity")).getActiveNetworkInfo();
            }
            NetworkInfo networkInfo = this.D;
            return networkInfo == null ? 0 : !networkInfo.isConnected() ? 0 : networkInfo.getType() + 1;
        } catch (Exception e) {
            String str = "android.permission.ACCESS_NETWORK_STATE not available?";
            if (!K) {
                Log.d("Unity", str);
            }
            return 0;
        }
    }

    protected int getOrientation() {
        return !(this.h instanceof Activity) ? 1 : ((Activity) this.h).getRequestedOrientation();
    }

    public Bundle getSettings() {
        return this.J;
    }

    protected String getSystemLanguage() {
        if (this.E == null) {
            this.E = Locale.getDefault().getISO3Language();
        }
        return this.E;
    }

    protected int getTotalMemory() {
        return M.c();
    }

    protected long getUptimeMillis() {
        return SystemClock.uptimeMillis();
    }

    public View getView() {
        return this;
    }

    protected boolean hasWakeLock() {
        return this.j.isHeld();
    }

    protected void hideSoftInput() {
        a(new Runnable(this) {
            private /* synthetic */ UnityPlayer a;

            {
                this.a = r1;
            }

            public final void run() {
                if (this.a.c) {
                    ((InputMethodManager) this.a.h.getSystemService("input_method")).toggleSoftInput(1, 0);
                    this.a.c = false;
                } else if (this.a.a != null) {
                    this.a.a.dismiss();
                    this.a.a = null;
                }
            }
        });
    }

    public void init(final int i, final boolean z) {
        CharSequence charSequence = "OK";
        String str;
        AlertDialog create;
        if (!o) {
            str = "OK";
            create = new Builder(this.h).setTitle("Failure to initialize!").setPositiveButton(charSequence, new OnClickListener(this) {
                private /* synthetic */ UnityPlayer a;

                {
                    this.a = r1;
                }

                public final void onClick(DialogInterface dialogInterface, int i) {
                    this.a.c();
                }
            }).setMessage("Your hardware does not support this application, sorry!").create();
            create.setCancelable(false);
            create.show();
        } else if (p) {
            SharedPreferences sharedPreferences = this.g;
            String str2 = "OK";
            AlertDialog create2 = new Builder(this.h).setTitle("Warning!").setPositiveButton(charSequence, new AnonymousClass10(sharedPreferences)).setMessage("Application was unable to load optimized libraries (possibly because your hardware does not fully support this application). It will run in 'compatible mode', with audio disabled").create();
            if (!sharedPreferences.getBoolean("warningSoftFloat", false)) {
                create2.show();
            }
            a(i, z);
        } else if (!Build.MANUFACTURER.equalsIgnoreCase("samsung") || VERSION.SDK_INT >= 8 || (M.a() & 4) == 0) {
            a(i, z);
        } else {
            o = false;
            str = "OK";
            create = new Builder(this.h).setTitle("Old Android OS detected!").setPositiveButton(charSequence, new OnClickListener(this) {
                private /* synthetic */ UnityPlayer c;

                public final void onClick(DialogInterface dialogInterface, int i) {
                    UnityPlayer.o = true;
                    this.c.a(i, z);
                }
            }).setMessage("This application requires at least Android OS version 2.2 on Samsung devices. Your device seems to be running an older OS version.\nPlease contact your carrier or the hardware vendor and ask them how to install a more recent version. It is a simple process that your provider's customer service can help you with.").create();
            create.setCancelable(false);
            create.show();
        }
    }

    public void init(c cVar, int i) {
        this.i = cVar;
        init(i, false);
    }

    protected boolean loadLibrary(String str) {
        String str2;
        String str3;
        String str4 = "Unity";
        try {
            System.loadLibrary(str);
            return true;
        } catch (UnsatisfiedLinkError e) {
            str2 = "Unable to find " + str;
            if (!K) {
                str3 = "Unity";
                Log.d(str4, str2);
            }
            return false;
        } catch (Exception e2) {
            str2 = "Unknown error " + e2;
            if (!K) {
                str3 = "Unity";
                Log.d(str4, str2);
            }
            return false;
        }
    }

    protected native void nativeSetLocation(float f, float f2, float f3, float f4, double d);

    protected native void nativeSetLocationStatus(int i);

    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    public void onDrawFrame(GL10 gl10) {
        String nativeGetLicenseKey;
        String str = "Unity";
        if (!this.I && this.F == null) {
            nativeGetLicenseKey = nativeGetLicenseKey();
            if (nativeGetLicenseKey != null) {
                h kVar;
                if (nativeGetLicensePolicy() == 0) {
                    kVar = new k(this.h, new a(new byte[]{(byte) 53, (byte) 36, (byte) 41, (byte) 64, (byte) 113, (byte) 61, (byte) 43, (byte) 42, (byte) 85, (byte) 46, (byte) 106, (byte) 113, (byte) 122, (byte) 108, (byte) 33, (byte) 50, (byte) 88, (byte) 99, (byte) 103, (byte) 78}, this.h.getPackageName(), nativeGetLicenseDeviceId()));
                } else {
                    kVar = new l();
                }
                try {
                    this.F = new d(this.h, kVar, nativeGetLicenseKey);
                    this.F.a(new e(this) {
                        private /* synthetic */ UnityPlayer a;

                        {
                            this.a = r1;
                        }

                        public final void a() {
                            this.a.G = true;
                            this.a.H = true;
                        }

                        public final void a(e.a aVar) {
                            UnityPlayer.a("Error while determining license validity : " + aVar);
                        }

                        public final void b() {
                            this.a.G = false;
                            this.a.H = true;
                        }
                    });
                } catch (Exception e) {
                    this.I = true;
                    nativeGetLicenseKey = "LVL key invalid";
                    if (!K) {
                        String str2 = "Unity";
                        Log.d(str, nativeGetLicenseKey);
                    }
                }
            }
        }
        if (nativeRender()) {
            this.A++;
            if (!this.q) {
                if (this.r) {
                    this.r = false;
                    return;
                }
                nativeGetLicenseKey = "initUnity";
                if (!K) {
                    str2 = "Unity";
                    Log.d(str, nativeGetLicenseKey);
                }
                nativeGetLicenseKey = "glGetString (GL10.GL_VERSION)='" + gl10.glGetString(7938) + "'";
                if (!K) {
                    str2 = "Unity";
                    Log.d(str, nativeGetLicenseKey);
                }
                nativeFile(this.h.getPackageCodePath());
                unityAndroidInit("assets/bin/", this.h.getApplicationInfo().dataDir + "/lib");
                this.q = true;
                nativeResize(this.s, this.t);
                nativeResume();
                return;
            }
            return;
        }
        this.z = true;
        nativeGetLicenseKey = "time to quit..";
        if (!K) {
            str2 = "Unity";
            Log.d(str, nativeGetLicenseKey);
        }
        c();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return a(i, keyEvent, true);
    }

    public boolean onKeyPreIme(int i, KeyEvent keyEvent) {
        if (!this.c || i != 4) {
            return super.onKeyPreIme(i, keyEvent);
        }
        if (keyEvent.getMetaState() == 2) {
            return a(i, keyEvent, keyEvent.getAction() == 0);
        }
        if (keyEvent.getAction() == 0) {
            reportSoftInputStr(this.e.toString(), 1);
        }
        return true;
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        return a(i, keyEvent, false);
    }

    protected boolean onNativeKey(long j, long j2, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, boolean z) {
        KeyEvent keyEvent = new KeyEvent(j, j2, i, i2, i3, i4, i5, i6, i7);
        if (z) {
            return onKeyPreIme(i2, keyEvent);
        }
        return a(i2, keyEvent, i == 0);
    }

    protected boolean onNativeTouchEvent(int i, int i2, int i3, float f, float f2, long j, int i4) {
        if (!this.q) {
            return true;
        }
        int i5 = i2 & 255;
        int[] iArr = new int[]{0, 3, 1, 4, 4, 0, 3, -1};
        if (i != ((65280 & i2) >> 8)) {
            i5 = 2;
        }
        final float f3 = f * this.w;
        final float f4 = f2 * this.x;
        final int i6 = iArr[i5];
        final int i7 = i3;
        final long j2 = j;
        final int i8 = i4;
        this.i.queueEvent(new Runnable(this) {
            private /* synthetic */ UnityPlayer g;

            public final void run() {
                this.g.nativeTouch(i7, f3, f4, i6, j2, i8);
            }
        });
        return true;
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        if (this.q) {
            switch (sensorEvent.sensor.getType()) {
                case 1:
                    float f;
                    int i;
                    sensorEvent.values.clone();
                    int orientation = this.l.getDefaultDisplay().getOrientation();
                    int i2 = (orientation - 1) & 3;
                    float f2 = (float) N[(i2 * 4) + 0];
                    float f3 = (float) N[(i2 * 4) + 1];
                    int i3 = N[(i2 * 4) + 2];
                    i2 = N[(i2 * 4) + 3];
                    this.O = (f2 * -0.10197162f) * sensorEvent.values[i3];
                    this.P = sensorEvent.values[i2] * (f3 * -0.10197162f);
                    this.Q = sensorEvent.values[2] * -0.10197162f;
                    this.R = sensorEvent.timestamp;
                    this.i.queueEvent(this.S);
                    float f4 = sensorEvent.values[0];
                    f2 = sensorEvent.values[1];
                    f3 = sensorEvent.values[2];
                    float sqrt = 1.0f / ((float) Math.sqrt((double) (((f4 * f4) + (f2 * f2)) + (f3 * f3))));
                    f4 *= sqrt;
                    f2 *= sqrt;
                    f3 *= sqrt;
                    i3 = getOrientation();
                    i3 = i3 == 1 ? 0 : i3 == 0 ? 1 : i3 == 9 ? 2 : i3 == 8 ? 3 : 0;
                    orientation = (orientation - i3) & 3;
                    if (orientation == 1 || orientation == 3) {
                        f = f4;
                        f4 = f2;
                    } else {
                        f = f2;
                    }
                    if (-1.0f < f) {
                        i = 1;
                        sqrt = f;
                    } else {
                        sqrt = -1.0f;
                        i = 0;
                    }
                    if (sqrt < (-f)) {
                        f2 = -f;
                        orientation = 2;
                    } else {
                        orientation = i;
                        f2 = sqrt;
                    }
                    if (f2 < f4) {
                        orientation = 3;
                        f2 = f4;
                    }
                    if (f2 < (-f4)) {
                        f4 = -f4;
                        orientation = 4;
                    } else {
                        f4 = f2;
                    }
                    if (f4 < f3) {
                        orientation = 5;
                        f4 = f3;
                    }
                    if (f4 < (-f3)) {
                        f4 = -f3;
                        orientation = 6;
                    }
                    if (((double) f4) < 0.5d * Math.sqrt(3.0d)) {
                        orientation = 0;
                    }
                    this.T = orientation;
                    this.i.queueEvent(this.U);
                    return;
                case 2:
                    sensorEvent.values.clone();
                    return;
                default:
                    return;
            }
        }
    }

    public void onSurfaceChanged(GL10 gl10, int i, int i2) {
        String str = "x";
        String str2 = "Unity";
        String str3 = "x";
        String str4 = "onSurfaceChanged " + i + str + i2;
        if (!K) {
            str3 = "Unity";
            Log.d(str2, str4);
        }
        if ((this.i instanceof SurfaceView) && !((this.u == 0 && this.v == 0) || (this.u == i && this.v == i2))) {
            setScreenSize(this.u, this.v);
        }
        if (this.i instanceof View) {
            int width = ((View) this.i).getWidth();
            int height = ((View) this.i).getHeight();
            String str5 = "x";
            String str6 = "view is " + width + str + height;
            if (!K) {
                str5 = "Unity";
                Log.d(str2, str6);
            }
            this.w = ((float) i) / ((float) width);
            this.x = ((float) i2) / ((float) height);
        }
        this.s = i;
        this.t = i2;
        nativeResize(i, i2);
    }

    public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        String str = "onSurfaceCreated";
        if (!K) {
            Log.d("Unity", str);
        }
        nativeRecreateGfxState();
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.q) {
            return true;
        }
        int action = motionEvent.getAction() & 255;
        int action2 = (motionEvent.getAction() & 65280) >> 8;
        int[] iArr = new int[]{0, 3, 1, 4, 4, 0, 3, -1};
        int pointerCount = motionEvent.getPointerCount();
        int i = 0;
        while (i < pointerCount) {
            final int pointerId;
            final float historicalX;
            final float historicalY;
            final int i2;
            final long eventTime;
            int historySize = motionEvent.getHistorySize();
            for (int i3 = 0; i3 < historySize; i3++) {
                pointerId = motionEvent.getPointerId(i);
                historicalX = motionEvent.getHistoricalX(i, i3);
                historicalY = motionEvent.getHistoricalY(i, i3);
                i2 = iArr[2];
                eventTime = motionEvent.getEventTime();
                this.i.queueEvent(new Runnable(this) {
                    private /* synthetic */ UnityPlayer f;

                    public final void run() {
                        this.f.nativeTouch(pointerId, historicalX, historicalY, i2, eventTime, 4098);
                    }
                });
            }
            int i4 = i == action2 ? action : 2;
            pointerId = motionEvent.getPointerId(i);
            historicalX = this.w * motionEvent.getX(i);
            historicalY = this.x * motionEvent.getY(i);
            i2 = iArr[i4];
            eventTime = motionEvent.getEventTime();
            this.i.queueEvent(new Runnable(this) {
                private /* synthetic */ UnityPlayer f;

                public final void run() {
                    this.f.nativeTouch(pointerId, historicalX, historicalY, i2, eventTime, 4098);
                }
            });
            i++;
        }
        return true;
    }

    protected void openURL(String str) {
        Intent intent = new Intent("android.intent.action.VIEW");
        Uri parse = Uri.parse(str);
        intent.setData(parse);
        if (parse.isRelative()) {
            intent.setDataAndType(Uri.fromFile(new File(str)), MimeTypeMap.getSingleton().getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(str)));
        }
        this.h.startActivity(intent);
    }

    public void pause() {
        if (!this.L) {
            String str = "onPause";
            if (!K) {
                Log.d("Unity", str);
            }
            if (o) {
                this.L = true;
                if (this.m != null) {
                    this.m.b();
                    this.m = null;
                }
                if (this.q) {
                    if (this.z) {
                        this.i.queueEvent(new Runnable(this) {
                            private /* synthetic */ UnityPlayer a;

                            {
                                this.a = r1;
                            }

                            public final void run() {
                                this.a.nativeDone();
                            }
                        });
                    } else {
                        this.i.queueEvent(new Runnable(this) {
                            private /* synthetic */ UnityPlayer a;

                            {
                                this.a = r1;
                            }

                            public final void run() {
                                this.a.nativePause();
                            }
                        });
                    }
                }
                this.i.onPause();
                setWakeLock(false);
                this.k.unregisterListener(this);
                if (this.Z == 1 || this.Z == 2) {
                    this.aa = true;
                    Location_StopService();
                }
            }
        }
    }

    public void quit() {
        String str = "onDestroy";
        if (!K) {
            Log.d("Unity", str);
        }
        if (o) {
            removeAllViews();
            this.i.onDestroy();
        }
        if (this.F != null) {
            this.F.a();
        }
        this.F = null;
        Process.killProcess(Process.myPid());
    }

    public void reportSoftInputStr(final String str, final int i) {
        if (i == 1) {
            hideSoftInput();
        }
        this.i.queueEvent(new Runnable(this) {
            private /* synthetic */ UnityPlayer c;

            public final void run() {
                if (str != null) {
                    this.c.nativeSetInputString(str);
                }
                if (i == 1) {
                    this.c.nativeSoftInputClosed();
                }
            }
        });
    }

    public void resume() {
        if (this.L) {
            String str = "onResume";
            if (!K) {
                Log.d("Unity", str);
            }
            if (o) {
                this.L = false;
                if (!p) {
                    this.m = new FMODAudioDevice();
                    this.m.a();
                }
                this.i.onResume();
                this.k.registerListener(this, this.k.getDefaultSensor(1), 1);
                this.k.registerListener(this, this.k.getDefaultSensor(2), 1);
                if (this.q) {
                    this.i.queueEvent(new Runnable(this) {
                        private /* synthetic */ UnityPlayer a;

                        {
                            this.a = r1;
                        }

                        public final void run() {
                            this.a.nativeResume();
                        }
                    });
                }
                if (this.aa) {
                    Location_StartService();
                }
                this.B = null;
                this.C = null;
                this.D = null;
                this.E = null;
            }
        }
    }

    protected void setHideInputField(boolean z) {
        this.b = z;
    }

    protected void setOrientation(int i) {
        if (this.h instanceof Activity) {
            Activity activity = (Activity) this.h;
            if (i == activity.getRequestedOrientation()) {
                return;
            }
            if (VERSION.SDK_INT >= 9 || !(i == 9 || i == 8)) {
                activity.setRequestedOrientation(i);
            }
        }
    }

    protected void setScreenSize(int i, int i2) {
        String str = "x";
        String str2 = "Unity";
        if (this.i instanceof SurfaceView) {
            SurfaceView surfaceView = (SurfaceView) this.i;
            Rect surfaceFrame = surfaceView.getHolder().getSurfaceFrame();
            String str3 = "x";
            StringBuilder append = new StringBuilder().append("setScreenSize ").append(i).append(" x ").append(i2).append(" (").append(surfaceFrame.width()).append(str).append(surfaceFrame.height()).append(" / ").append(surfaceView.getWidth());
            String str4 = "x";
            String stringBuilder = append.append(str).append(surfaceView.getHeight()).append(")").toString();
            if (!K) {
                str4 = "Unity";
                Log.d(str2, stringBuilder);
            }
            boolean z = (surfaceView.getWidth() == i && surfaceView.getHeight() == i2) || (i == -1 && i2 == -1);
            if (z) {
                this.u = 0;
                this.v = 0;
            } else {
                this.u = i;
                this.v = i2;
            }
            a(new AnonymousClass9(z, surfaceView, i, i2));
            return;
        }
        String str5 = "setScreenSize: Unable to retrieve surface holder";
        if (!K) {
            stringBuilder = "Unity";
            Log.d(str2, str5);
        }
    }

    protected void setWakeLock(boolean z) {
        if (z != hasWakeLock()) {
            if (z) {
                this.j.acquire();
            } else if (!z) {
                this.j.release();
            }
        }
    }

    protected boolean showBuildSetup() {
        return this.G;
    }

    protected boolean showRuntimeSetup() {
        return this.H;
    }

    protected void showSoftInput(String str, int i, boolean z, boolean z2, boolean z3, boolean z4, String str2) {
        final UnityPlayer unityPlayer = this;
        final String str3 = str;
        final boolean z5 = z2;
        final int i2 = i;
        final boolean z6 = z;
        final boolean z7 = z3;
        final boolean z8 = z4;
        final String str4 = str2;
        a(new Runnable(this) {
            private /* synthetic */ UnityPlayer i;

            public final void run() {
                if (this.i.b) {
                    ((InputMethodManager) unityPlayer.h.getSystemService("input_method")).toggleSoftInput(0, 1);
                    this.i.e.setLength(0);
                    this.i.e.append(str3);
                    this.i.d = z5;
                    UnityPlayer.a("mHiddenInputIsMultiLine = " + this.i.d);
                    this.i.c = true;
                    return;
                }
                this.i.a = new b(this.i.h, unityPlayer, str3, i2, z6, z5, z7, str4, this.i.b);
                this.i.a.show();
            }
        });
    }

    protected void showVideoPlayer(String str, int i, int i2, int i3, boolean z) {
        String str2 = "screenOrientation";
        final Intent intent = new Intent(this.h, VideoPlayer.class);
        intent.putExtra("fileName", str);
        intent.putExtra("backgroundColor", i);
        intent.putExtra("controlMode", i2);
        intent.putExtra("scalingMode", i3);
        intent.putExtra("isURL", z);
        if (this.h instanceof Activity) {
            String str3 = "screenOrientation";
            intent.putExtra(str2, ((Activity) this.h).getRequestedOrientation());
        } else {
            String str4 = "screenOrientation";
            intent.putExtra(str2, 1);
        }
        intent.putExtra("wakeLock", hasWakeLock());
        intent.addFlags(65536);
        a(new Runnable(this) {
            private /* synthetic */ UnityPlayer b;

            public final void run() {
                this.b.h.startActivity(intent);
            }
        });
    }

    protected void vibrate(int i) {
        if (this.n == null) {
            this.n = (Vibrator) this.h.getSystemService("vibrator");
        }
        if (i == 0) {
            try {
                this.n.cancel();
                return;
            } catch (Exception e) {
                r0 = "android.permission.VIBRATE not available?";
                if (!K) {
                    String str;
                    Log.d("Unity", str);
                    return;
                }
                return;
            }
        }
        this.n.vibrate((long) i);
    }
}
