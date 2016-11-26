package com.unity3d.player;
 
import android.os.Build;
import android.util.Log;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.HashMap;
import java.util.Map;
 
final class a {
    private int a = 0;
    private String b;
    private int c = 0;
 
    a() {
        Map a = a("/proc/cpuinfo");
        String str = (String) a.get("CPU architecture");
        String str2 = (String) a.get("Features");
        if (str != null) {
            int length = str.length();
            int i = 0;
            while (i < length && Character.isDigit(str.charAt(i))) {
                i++;
            }
            str = str.substring(0, i);
            if (Integer.decode(str).intValue() >= 7 && !Build.CPU_ABI.equalsIgnoreCase("armeabi")) {
                this.a |= 4;
            }
            if (Integer.decode(str).intValue() >= 6) {
                this.a |= 2;
            }
            if (Integer.decode(str).intValue() >= 5) {
                this.a |= 1;
            }
        }
        if (str2 != null) {
            if (str2.contains("vfpv3")) {
                this.a |= 8;
            }
            if (str2.contains("neon")) {
                this.a |= 16;
            }
            if (str2.contains("vfp")) {
                this.a |= 32;
            }
        }
        this.b = (String) a.get("Processor");
        this.c = b((String) a("/proc/meminfo").get("MemTotal"));
    }
 
    private static Map a(String str) {
        Map hashMap = new HashMap();
        try {
            LineNumberReader lineNumberReader = new LineNumberReader(new FileReader(str), 8192);
            for (String readLine = lineNumberReader.readLine(); readLine != null; readLine = lineNumberReader.readLine()) {
                int indexOf = readLine.indexOf(58);
                if (indexOf >= 0) {
                    hashMap.put(readLine.substring(0, indexOf).trim(), readLine.substring(indexOf + 1).trim());
                }
            }
        } catch (FileNotFoundException e) {
            Log.e("FileNotFoundException", e.toString());
        } catch (IOException e2) {
            Log.e("IOException", e2.toString());
        }
        return hashMap;
    }
 
    private static int b(String str) {
        int length = str.length();
        int i = 0;
        while (i < length && Character.isDigit(str.charAt(i))) {
            i++;
        }
        return Integer.decode(str.substring(0, i)).intValue();
    }
 
    public final int a() {
        return this.a;
    }
 
    public final String b() {
        return this.b;
    }
 
    public final int c() {
        return this.c / 1024;
    }
}
