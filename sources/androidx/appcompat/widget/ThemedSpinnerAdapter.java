package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.widget.SpinnerAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.view.ContextThemeWrapper;

/* loaded from: classes.dex */
public interface ThemedSpinnerAdapter extends SpinnerAdapter {

    /* loaded from: classes.dex */
    public static final class Helper {
        private final Context a;
        private final LayoutInflater b;

        /* renamed from: c, reason: collision with root package name */
        private LayoutInflater f198c;

        public Helper(@NonNull Context context) {
            this.a = context;
            this.b = LayoutInflater.from(context);
        }

        @NonNull
        public LayoutInflater getDropDownViewInflater() {
            LayoutInflater layoutInflater = this.f198c;
            return layoutInflater != null ? layoutInflater : this.b;
        }

        @Nullable
        public Resources.Theme getDropDownViewTheme() {
            LayoutInflater layoutInflater = this.f198c;
            if (layoutInflater == null) {
                return null;
            }
            return layoutInflater.getContext().getTheme();
        }

        public void setDropDownViewTheme(@Nullable Resources.Theme theme) {
            this.f198c = theme == null ? null : theme == this.a.getTheme() ? this.b : LayoutInflater.from(new ContextThemeWrapper(this.a, theme));
        }
    }

    @Nullable
    Resources.Theme getDropDownViewTheme();

    void setDropDownViewTheme(@Nullable Resources.Theme theme);
}
