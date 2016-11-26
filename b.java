package com.unity3d.player;
 
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.method.TextKeyListener;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.view.WindowManager.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
 
public final class b extends Dialog implements OnClickListener {
    private Context a = null;
    private UnityPlayer b = null;
 
    public b(Context context, UnityPlayer unityPlayer, String str, int i, boolean z, boolean z2, boolean z3, String str2, boolean z4) {
        super(context);
        this.a = context;
        this.b = unityPlayer;
        getWindow().setGravity(80);
        getWindow().requestFeature(1);
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        setContentView(createSoftInputView());
        getWindow().clearFlags(2);
        EditText editText = (EditText) findViewById(1057292289);
        Button button = (Button) findViewById(1057292290);
        editText.setImeOptions(6);
        editText.setText(str);
        editText.setHint(str2);
        int i2 = ((z ? 32768 : 0) | (z2 ? 131072 : 0)) | (z3 ? 128 : 0);
        switch (i) {
            case 0:
                i2 |= 1;
                break;
            case 1:
                i2 = (i2 | 1) | 16384;
                break;
            case 2:
                i2 = ((i2 | 2) | 8192) | 4096;
                break;
            case 3:
                i2 = (i2 | 1) | 16;
                break;
            case 4:
                i2 |= 2;
                break;
            case 5:
                i2 |= 3;
                break;
            case 6:
                i2 = (i2 | 1) | 96;
                break;
            case 7:
                i2 = (i2 | 1) | 32;
                break;
        }
        editText.setInputType(i2);
        editText.addTextChangedListener(this.b);
        i2 = editText.getInputType();
        editText.setKeyListener(TextKeyListener.getInstance());
        editText.setRawInputType(i2);
        editText.setClickable(true);
        if (!z2) {
            editText.selectAll();
        }
        if (z4) {
            editText.setImeOptions(268435456);
            LayoutParams attributes = getWindow().getAttributes();
            attributes.y -= 1000;
        }
        button.setOnClickListener(this);
        editText.setOnFocusChangeListener(new OnFocusChangeListener(this) {
            private /* synthetic */ b a;
 
            {
                this.a = r1;
            }
 
            public final void onFocusChange(View view, boolean z) {
                if (z) {
                    this.a.getWindow().setSoftInputMode(5);
                }
            }
        });
    }
 
    private String a() {
        EditText editText = (EditText) findViewById(1057292289);
        return editText == null ? null : editText.getText().toString().trim();
    }
 
    protected final View createSoftInputView() {
        View relativeLayout = new RelativeLayout(this.a);
        relativeLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        View anonymousClass2 = new EditText(this, this.a) {
            private /* synthetic */ b a;
 
            public final boolean onKeyPreIme(int i, KeyEvent keyEvent) {
                if (i == 4) {
                    this.a.b.reportSoftInputStr(this.a.a(), 1);
                }
                return i == 84 ? true : super.onKeyPreIme(i, keyEvent);
            }
 
            public final void onWindowFocusChanged(boolean z) {
                super.onWindowFocusChanged(z);
                if (z) {
                    ((InputMethodManager) this.a.a.getSystemService("input_method")).showSoftInput(this, 0);
                }
            }
        };
        ViewGroup.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(15);
        layoutParams.addRule(0, 1057292290);
        anonymousClass2.setLayoutParams(layoutParams);
        anonymousClass2.setId(1057292289);
        relativeLayout.addView(anonymousClass2);
        anonymousClass2 = new Button(this.a);
        anonymousClass2.setText(this.a.getResources().getIdentifier("ok", "string", "android"));
        layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(15);
        layoutParams.addRule(11);
        anonymousClass2.setLayoutParams(layoutParams);
        anonymousClass2.setId(1057292290);
        relativeLayout.addView(anonymousClass2);
        ((EditText) relativeLayout.findViewById(1057292289)).setOnEditorActionListener(new OnEditorActionListener(this) {
            private /* synthetic */ b a;
 
            {
                this.a = r1;
            }
 
            public final boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == 6) {
                    this.a.b.reportSoftInputStr(this.a.a(), 1);
                }
                return false;
            }
        });
        return relativeLayout;
    }
 
    public final void onBackPressed() {
        this.b.reportSoftInputStr(a(), 1);
    }
 
    public final void onClick(View view) {
        this.b.reportSoftInputStr(a(), 1);
    }
}
