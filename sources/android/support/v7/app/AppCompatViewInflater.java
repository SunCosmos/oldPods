package android.support.v7.app;

import android.R;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.support.v4.view.ViewCompat;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatCheckedTextView;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatMultiAutoCompleteTextView;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.AppCompatSeekBar;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.TintContextWrapper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.InflateException;
import android.view.View;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/* loaded from: lib/Mus.dex */
public class AppCompatViewInflater {
    private static final String LOG_TAG = "AppCompatViewInflater";
    private final Object[] mConstructorArgs = new Object[2];
    private static final Class<?>[] sConstructorSignature = {Context.class, AttributeSet.class};
    private static final int[] sOnClickAttrs = {R.attr.onClick};
    private static final String[] sClassPrefixList = {"android.widget.", "android.view.", "android.webkit."};
    private static final Map<String, Constructor<? extends View>> sConstructorMap = new ArrayMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    public final View createView(View view, String str, @NonNull Context context, @NonNull AttributeSet attributeSet, boolean z, boolean z2, boolean z3, boolean z4) {
        View createSeekBar;
        Context context2 = context;
        if (z && view != null) {
            context2 = view.getContext();
        }
        if (z2 || z3) {
            context2 = themifyContext(context2, attributeSet, z2, z3);
        }
        if (z4) {
            context2 = TintContextWrapper.wrap(context2);
        }
        boolean z5 = -1;
        switch (str.hashCode()) {
            case -1946472170:
                if (str.equals("RatingBar")) {
                    z5 = 11;
                    break;
                }
                break;
            case -1455429095:
                if (str.equals("CheckedTextView")) {
                    z5 = 8;
                    break;
                }
                break;
            case -1346021293:
                if (str.equals("MultiAutoCompleteTextView")) {
                    z5 = 10;
                    break;
                }
                break;
            case -938935918:
                if (str.equals("TextView")) {
                    z5 = false;
                    break;
                }
                break;
            case -937446323:
                if (str.equals("ImageButton")) {
                    z5 = 5;
                    break;
                }
                break;
            case -658531749:
                if (str.equals("SeekBar")) {
                    z5 = 12;
                    break;
                }
                break;
            case -339785223:
                if (str.equals("Spinner")) {
                    z5 = 4;
                    break;
                }
                break;
            case 776382189:
                if (str.equals("RadioButton")) {
                    z5 = 7;
                    break;
                }
                break;
            case 1125864064:
                if (str.equals("ImageView")) {
                    z5 = true;
                    break;
                }
                break;
            case 1413872058:
                if (str.equals("AutoCompleteTextView")) {
                    z5 = 9;
                    break;
                }
                break;
            case 1601505219:
                if (str.equals("CheckBox")) {
                    z5 = 6;
                    break;
                }
                break;
            case 1666676343:
                if (str.equals("EditText")) {
                    z5 = 3;
                    break;
                }
                break;
            case 2001146706:
                if (str.equals("Button")) {
                    z5 = 2;
                    break;
                }
                break;
        }
        switch (z5) {
            case false:
                createSeekBar = createTextView(context2, attributeSet);
                verifyNotNull(createSeekBar, str);
                break;
            case true:
                createSeekBar = createImageView(context2, attributeSet);
                verifyNotNull(createSeekBar, str);
                break;
            case true:
                createSeekBar = createButton(context2, attributeSet);
                verifyNotNull(createSeekBar, str);
                break;
            case true:
                createSeekBar = createEditText(context2, attributeSet);
                verifyNotNull(createSeekBar, str);
                break;
            case true:
                createSeekBar = createSpinner(context2, attributeSet);
                verifyNotNull(createSeekBar, str);
                break;
            case true:
                createSeekBar = createImageButton(context2, attributeSet);
                verifyNotNull(createSeekBar, str);
                break;
            case true:
                createSeekBar = createCheckBox(context2, attributeSet);
                verifyNotNull(createSeekBar, str);
                break;
            case true:
                createSeekBar = createRadioButton(context2, attributeSet);
                verifyNotNull(createSeekBar, str);
                break;
            case true:
                createSeekBar = createCheckedTextView(context2, attributeSet);
                verifyNotNull(createSeekBar, str);
                break;
            case true:
                createSeekBar = createAutoCompleteTextView(context2, attributeSet);
                verifyNotNull(createSeekBar, str);
                break;
            case true:
                createSeekBar = createMultiAutoCompleteTextView(context2, attributeSet);
                verifyNotNull(createSeekBar, str);
                break;
            case true:
                createSeekBar = createRatingBar(context2, attributeSet);
                verifyNotNull(createSeekBar, str);
                break;
            case true:
                createSeekBar = createSeekBar(context2, attributeSet);
                verifyNotNull(createSeekBar, str);
                break;
            default:
                createSeekBar = createView(context2, str, attributeSet);
                break;
        }
        if (createSeekBar == null && context2 != context2) {
            createSeekBar = createViewFromTag(context2, str, attributeSet);
        }
        if (createSeekBar != null) {
            checkOnClickListener(createSeekBar, attributeSet);
        }
        return createSeekBar;
    }

    @NonNull
    protected AppCompatTextView createTextView(Context context, AttributeSet attributeSet) {
        return new AppCompatTextView(context, attributeSet);
    }

    @NonNull
    protected AppCompatImageView createImageView(Context context, AttributeSet attributeSet) {
        return new AppCompatImageView(context, attributeSet);
    }

    @NonNull
    protected AppCompatButton createButton(Context context, AttributeSet attributeSet) {
        return new AppCompatButton(context, attributeSet);
    }

    @NonNull
    protected AppCompatEditText createEditText(Context context, AttributeSet attributeSet) {
        return new AppCompatEditText(context, attributeSet);
    }

    @NonNull
    protected AppCompatSpinner createSpinner(Context context, AttributeSet attributeSet) {
        return new AppCompatSpinner(context, attributeSet);
    }

    @NonNull
    protected AppCompatImageButton createImageButton(Context context, AttributeSet attributeSet) {
        return new AppCompatImageButton(context, attributeSet);
    }

    @NonNull
    protected AppCompatCheckBox createCheckBox(Context context, AttributeSet attributeSet) {
        return new AppCompatCheckBox(context, attributeSet);
    }

    @NonNull
    protected AppCompatRadioButton createRadioButton(Context context, AttributeSet attributeSet) {
        return new AppCompatRadioButton(context, attributeSet);
    }

    @NonNull
    protected AppCompatCheckedTextView createCheckedTextView(Context context, AttributeSet attributeSet) {
        return new AppCompatCheckedTextView(context, attributeSet);
    }

    @NonNull
    protected AppCompatAutoCompleteTextView createAutoCompleteTextView(Context context, AttributeSet attributeSet) {
        return new AppCompatAutoCompleteTextView(context, attributeSet);
    }

    @NonNull
    protected AppCompatMultiAutoCompleteTextView createMultiAutoCompleteTextView(Context context, AttributeSet attributeSet) {
        return new AppCompatMultiAutoCompleteTextView(context, attributeSet);
    }

    @NonNull
    protected AppCompatRatingBar createRatingBar(Context context, AttributeSet attributeSet) {
        return new AppCompatRatingBar(context, attributeSet);
    }

    @NonNull
    protected AppCompatSeekBar createSeekBar(Context context, AttributeSet attributeSet) {
        return new AppCompatSeekBar(context, attributeSet);
    }

    private void verifyNotNull(View view, String str) {
        if (view == null) {
            throw new IllegalStateException(getClass().getName() + " asked to inflate view for <" + str + ">, but returned null");
        }
    }

    @Nullable
    protected View createView(Context context, String str, AttributeSet attributeSet) {
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [android.support.v7.app.AppCompatViewInflater] */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5, types: [android.view.View] */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v7 */
    private View createViewFromTag(Context context, String str, AttributeSet attributeSet) {
        AppCompatViewInflater appCompatViewInflater = this;
        String str2 = str;
        if (str2.equals("view")) {
            str2 = attributeSet.getAttributeValue(null, "class");
        }
        try {
            appCompatViewInflater.mConstructorArgs[0] = context;
            appCompatViewInflater.mConstructorArgs[1] = attributeSet;
            if (-1 == str2.indexOf(46)) {
                int i2 = 0;
                while (true) {
                    if (i2 >= sClassPrefixList.length) {
                        appCompatViewInflater.mConstructorArgs[0] = null;
                        appCompatViewInflater.mConstructorArgs[1] = null;
                        appCompatViewInflater = 0;
                        break;
                    }
                    View createViewByPrefix = appCompatViewInflater.createViewByPrefix(context, str2, sClassPrefixList[i2]);
                    if (createViewByPrefix != null) {
                        appCompatViewInflater.mConstructorArgs[0] = null;
                        appCompatViewInflater.mConstructorArgs[1] = null;
                        appCompatViewInflater = createViewByPrefix;
                        break;
                    }
                    i2++;
                }
            } else {
                View createViewByPrefix2 = appCompatViewInflater.createViewByPrefix(context, str2, null);
                appCompatViewInflater.mConstructorArgs[0] = null;
                appCompatViewInflater.mConstructorArgs[1] = null;
                appCompatViewInflater = createViewByPrefix2;
            }
            return appCompatViewInflater;
        } catch (Exception e) {
            appCompatViewInflater.mConstructorArgs[0] = null;
            appCompatViewInflater.mConstructorArgs[1] = null;
            return null;
        } catch (Throwable th) {
            appCompatViewInflater.mConstructorArgs[0] = null;
            appCompatViewInflater.mConstructorArgs[1] = null;
            throw th;
        }
    }

    private void checkOnClickListener(View view, AttributeSet attributeSet) {
        Context context = view.getContext();
        if (context instanceof ContextWrapper) {
            if (Build.VERSION.SDK_INT < 15 || ViewCompat.hasOnClickListeners(view)) {
                TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, sOnClickAttrs);
                String string = obtainStyledAttributes.getString(0);
                if (string != null) {
                    view.setOnClickListener(new DeclaredOnClickListener(view, string));
                }
                obtainStyledAttributes.recycle();
            }
        }
    }

    private View createViewByPrefix(Context context, String str, String str2) throws ClassNotFoundException, InflateException {
        Constructor<? extends View> constructor = sConstructorMap.get(str);
        if (constructor == null) {
            try {
                constructor = context.getClassLoader().loadClass(str2 != null ? str2 + str : str).asSubclass(View.class).getConstructor(sConstructorSignature);
                sConstructorMap.put(str, constructor);
            } catch (Exception e) {
                return null;
            }
        }
        constructor.setAccessible(true);
        return constructor.newInstance(this.mConstructorArgs);
    }

    private static Context themifyContext(Context context, AttributeSet attributeSet, boolean z, boolean z2) {
        Context context2 = context;
        TypedArray obtainStyledAttributes = context2.obtainStyledAttributes(attributeSet, android.support.v7.appcompat.R.styleable.View, 0, 0);
        int i2 = 0;
        if (z) {
            i2 = obtainStyledAttributes.getResourceId(android.support.v7.appcompat.R.styleable.View_android_theme, 0);
        }
        if (z2 && i2 == 0) {
            i2 = obtainStyledAttributes.getResourceId(android.support.v7.appcompat.R.styleable.View_theme, 0);
            if (i2 != 0) {
                Log.i(LOG_TAG, "app:theme is now deprecated. Please move to using android:theme instead.");
            }
        }
        obtainStyledAttributes.recycle();
        if (i2 != 0 && (!(context2 instanceof ContextThemeWrapper) || ((ContextThemeWrapper) context2).getThemeResId() != i2)) {
            context2 = new ContextThemeWrapper(context2, i2);
        }
        return context2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: lib/Mus.dex */
    public static class DeclaredOnClickListener implements View.OnClickListener {
        private final View mHostView;
        private final String mMethodName;
        private Context mResolvedContext;
        private Method mResolvedMethod;

        public DeclaredOnClickListener(@NonNull View view, @NonNull String str) {
            this.mHostView = view;
            this.mMethodName = str;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(@NonNull View view) {
            if (this.mResolvedMethod == null) {
                resolveMethod(this.mHostView.getContext(), this.mMethodName);
            }
            try {
                this.mResolvedMethod.invoke(this.mResolvedContext, view);
            } catch (IllegalAccessException e) {
                throw new IllegalStateException("Could not execute non-public method for android:onClick", e);
            } catch (InvocationTargetException e2) {
                throw new IllegalStateException("Could not execute method for android:onClick", e2);
            }
        }

        @NonNull
        private void resolveMethod(@Nullable Context context, @NonNull String str) {
            Method method;
            Context context2 = context;
            while (context2 != null) {
                try {
                    if (!context2.isRestricted() && (method = context2.getClass().getMethod(this.mMethodName, View.class)) != null) {
                        this.mResolvedMethod = method;
                        this.mResolvedContext = context2;
                        return;
                    }
                } catch (NoSuchMethodException e) {
                }
                if (context2 instanceof ContextWrapper) {
                    context2 = ((ContextWrapper) context2).getBaseContext();
                } else {
                    context2 = null;
                }
            }
            int id = this.mHostView.getId();
            throw new IllegalStateException("Could not find method " + this.mMethodName + "(View) in a parent or ancestor Context for android:onClick attribute defined on view " + this.mHostView.getClass() + (id == -1 ? "" : " with id '" + this.mHostView.getContext().getResources().getResourceEntryName(id) + "'"));
        }
    }
}
