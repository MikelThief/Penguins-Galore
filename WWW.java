package com.unity3d.player;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

class WWW extends Thread {
    private int a;
    private String b;
    private byte[] c;
    private Map d;

    WWW(int i, String str, byte[] bArr, Map map) {
        this.a = i;
        this.b = str;
        this.c = bArr;
        this.d = map;
        start();
    }

    private static native void doneCallback(int i);

    private static native void errorCallback(int i, String str);

    private static native boolean headerCallback(int i, String str);

    private static native void progressCallback(int i, float f, float f2, double d);

    private static native boolean readCallback(int i, byte[] bArr, int i2);

    protected boolean headerCallback(String str, String str2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(": ");
        stringBuilder.append(str2);
        stringBuilder.append("\n\r");
        return headerCallback(this.a, stringBuilder.toString());
    }

    protected boolean headerCallback(Map map) {
        if (map == null || map.size() == 0) {
            return false;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Entry entry : map.entrySet()) {
            for (String str : (List) entry.getValue()) {
                stringBuilder.append((String) entry.getKey());
                stringBuilder.append(": ");
                stringBuilder.append(str);
                stringBuilder.append("\n\r");
            }
        }
        return headerCallback(this.a, stringBuilder.toString());
    }

    protected void progressCallback(int i, int i2, int i3, int i4, long j, long j2) {
        float f;
        float f2;
        double d;
        if (i4 > 0) {
            float f3 = ((float) i3) / ((float) i4);
            double max = (1000.0d * ((double) i3)) / Math.max((double) (j - j2), 0.1d);
            double max2 = ((double) Math.max(i4 - i3, 0)) / max;
            if (Double.isInfinite(max2) || Double.isNaN(max2)) {
                max2 = 0.0d;
            }
            double d2 = max2;
            f = f3;
            f2 = 1.0f;
            d = d2;
        } else if (i2 > 0) {
            f = 0.0f;
            f2 = (float) (i / i2);
            d = 0.0d;
        } else {
            return;
        }
        progressCallback(this.a, f2, f, d);
    }

    protected boolean readCallback(byte[] bArr, int i) {
        return readCallback(this.a, bArr, i);
    }

    public void run() {
        try {
            int i;
            URL url = new URL(this.b);
            URLConnection openConnection = url.openConnection();
            if (this.d != null) {
                for (Entry entry : this.d.entrySet()) {
                    openConnection.addRequestProperty((String) entry.getKey(), (String) entry.getValue());
                }
            }
            if (this.c != null) {
                openConnection.setDoOutput(true);
                try {
                    OutputStream outputStream = openConnection.getOutputStream();
                    i = 0;
                    while (i < this.c.length) {
                        int min = Math.min(1428, this.c.length - i);
                        outputStream.write(this.c, i, min);
                        min += i;
                        progressCallback(min, this.c.length, 0, 0, 0, 0);
                        i = min;
                    }
                } catch (IOException e) {
                    errorCallback(this.a, e.toString());
                    e.printStackTrace();
                    return;
                }
            }
            Map headerFields = openConnection.getHeaderFields();
            boolean headerCallback = headerCallback(headerFields);
            if ((headerFields == null || !headerFields.containsKey("content-length")) && openConnection.getContentLength() != -1) {
                headerCallback = headerCallback || headerCallback("content-length", String.valueOf(openConnection.getContentLength()));
            }
            boolean z = ((headerFields == null || !headerFields.containsKey("content-type")) && openConnection.getContentType() != null) ? headerCallback || headerCallback("content-type", openConnection.getContentType()) : headerCallback;
            if (z) {
                errorCallback(this.a, this.b + " aborted");
                return;
            }
            int contentLength = openConnection.getContentLength() > 0 ? openConnection.getContentLength() : 0;
            i = (url.getProtocol().equalsIgnoreCase("file") || url.getProtocol().equalsIgnoreCase("jar")) ? contentLength == 0 ? 32768 : Math.min(contentLength, 32768) : 1428;
            try {
                long currentTimeMillis = System.currentTimeMillis();
                byte[] bArr = new byte[i];
                InputStream inputStream = openConnection.getInputStream();
                int i2 = 0;
                for (i = 0; i != -1; i = inputStream.read(bArr)) {
                    if (readCallback(bArr, i)) {
                        errorCallback(this.a, this.b + " aborted");
                        return;
                    }
                    i2 += i;
                    progressCallback(0, 0, i2, contentLength, System.currentTimeMillis(), currentTimeMillis);
                }
                if (contentLength == 0 || contentLength == i2) {
                    progressCallback(0, 0, i2, i2, 0, 0);
                    doneCallback(this.a);
                    return;
                }
                errorCallback(this.a, this.b + " short read (" + i2 + "/" + contentLength + ")");
            } catch (IOException e2) {
                errorCallback(this.a, e2.toString());
                e2.printStackTrace();
            } catch (RuntimeException e3) {
                errorCallback(this.a, e3.toString());
                e3.printStackTrace();
            }
        } catch (MalformedURLException e4) {
            errorCallback(this.a, e4.toString());
            e4.printStackTrace();
        } catch (IOException e22) {
            errorCallback(this.a, e22.toString());
            e22.printStackTrace();
        }
    }
}
