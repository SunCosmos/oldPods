package com.iapp.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.iapp.app.run.mian;
import com.iapp.app.x5.WebView;
import com.oldpods.app.R;
import java.io.File;

/* loaded from: classes.dex */
public class logoActivity extends AppCompatActivity {
    private boolean a = true;
    private AlertDialog b = null;

    /* renamed from: c */
    private EditText f1942c;

    /* loaded from: classes.dex */
    public class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            String d2 = c.b.a.a.p.d(logoActivity.this.f1942c.getText().toString() + "mmpfbf");
            if (!com.iapp.app.b.h2(logoActivity.this, d2)) {
                Toast.makeText(logoActivity.this, "答案错误", 0).show();
                return;
            }
            String str = logoActivity.this.getFilesDir().getPath() + "/config/userencryption.xml";
            c.b.a.a.d.b(str, false);
            c.b.a.a.d.k(str, "<s>" + d2 + "</s>");
            logoActivity.this.h();
        }
    }

    /* loaded from: classes.dex */
    public class b implements DialogInterface.OnClickListener {
        b() {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            logoActivity.this.finish();
        }
    }

    /* loaded from: classes.dex */
    public class c implements View.OnClickListener {
        c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (logoActivity.this.b != null) {
                logoActivity.this.b.dismiss();
                logoActivity.this.b = null;
            }
            logoActivity.this.j();
        }
    }

    private String f(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            CharSequence applicationLabel = packageManager.getApplicationLabel(packageManager.getPackageInfo(context.getPackageName(), 0).applicationInfo);
            return applicationLabel != null ? applicationLabel.toString() : "请等等";
        } catch (Exception e) {
            e.printStackTrace();
            return "请等等";
        }
    }

    public void h() {
        WebView.b(getApplicationContext());
        Intent flags = new Intent(this, (Class<?>) mian.class).setFlags(67108864);
        Bundle bundle = new Bundle();
        bundle.putString("OpenFilexmlui", "mian.iyu");
        flags.putExtras(bundle);
        startActivity(flags);
        finish();
    }

    private void i(Activity activity) {
        if (Build.VERSION.SDK_INT < 23) {
            k();
            return;
        }
        boolean z = false;
        try {
            z = c.b.a.a.k.d(activity, activity.getPackageManager().getPackageInfo(activity.getPackageName(), 4096).requestedPermissions);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (z) {
            return;
        }
        k();
    }

    public void j() {
        String f = c.b.a.a.d.f(this, "userencryption.xml");
        if (f == null || !f.contains("<ok>true</ok>")) {
            new File(getFilesDir().getPath() + "/config/userencryption.xml").delete();
            h();
            return;
        }
        String af = e.af(this);
        if (af != null) {
            if (com.iapp.app.b.h2(this, af)) {
                h();
                return;
            }
            new File(getFilesDir().getPath() + "/config/userencryption.xml").delete();
        }
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
        linearLayout.setOrientation(1);
        linearLayout.setPadding(20, 0, 20, 0);
        EditText editText = new EditText(this);
        this.f1942c = editText;
        editText.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
        this.f1942c.setHint("请输入答案");
        this.f1942c.setHighlightColor(Color.parseColor("#bbbbbb"));
        Button button = new Button(this);
        button.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
        button.setText("马上使用");
        button.setOnClickListener(new a());
        linearLayout.addView(this.f1942c);
        linearLayout.addView(button);
        Bitmap b2 = c.b.a.a.i.b(this, "res/" + c.b.a.a.p.h(f, "img"));
        if (b2 != null) {
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
            imageView.setImageBitmap(b2);
            linearLayout.addView(imageView);
        }
        ScrollView scrollView = new ScrollView(this);
        scrollView.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
        scrollView.addView(linearLayout);
        this.b = new AlertDialog.Builder(this).setIcon(getResources().getDrawable(R.mipmap.img_iapp)).setTitle(f(this)).setMessage(c.b.a.a.p.h(f, "text")).setView(scrollView).setNegativeButton("再见", new b()).show();
        LinearLayout linearLayout2 = new LinearLayout(this);
        linearLayout2.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        linearLayout2.setOrientation(1);
        linearLayout2.setGravity(16);
        Button button2 = new Button(this);
        button2.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
        button2.setText("开始使用");
        button2.setOnClickListener(new c());
        linearLayout2.addView(button2);
        setContentView(linearLayout2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x0019, code lost:
    
        c.b.a.a.t.o = 0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void k() {
        /*
            Method dump skipped, instructions count: 260
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.iapp.app.logoActivity.k():void");
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        i(this);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i2, @NonNull String[] strArr, @NonNull int[] iArr) {
        boolean z;
        if (i2 == 60542 && strArr != null) {
            int length = iArr.length;
            if (length == strArr.length) {
                z = true;
                for (int i3 = 0; i3 < length; i3++) {
                    if (iArr[i3] == -1) {
                        String[] strArr2 = c.b.a.a.l.a;
                        int length2 = strArr2.length;
                        int i4 = 0;
                        while (true) {
                            if (i4 >= length2) {
                                break;
                            }
                            if (strArr2[i4].equals(strArr[i3])) {
                                z = false;
                                break;
                            }
                            i4++;
                        }
                        if (!z) {
                            break;
                        }
                    }
                }
            } else {
                z = true;
            }
            if (!z) {
                Toast.makeText(this, "部分功能权限授权失败", 1).show();
            }
        }
        if (this.a) {
            k();
            this.a = false;
        }
        super.onRequestPermissionsResult(i2, strArr, iArr);
    }
}
