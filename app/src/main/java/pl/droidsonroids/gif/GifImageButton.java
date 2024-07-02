package pl.droidsonroids.gif;

import android.content.Context;
import android.net.Uri;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.widget.ImageButton;
import pl.droidsonroids.gif.f;

/* loaded from: classes.dex */
public class GifImageButton extends ImageButton {
    private boolean a;

    public GifImageButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(f.c(this, attributeSet, 0, 0));
    }

    public GifImageButton(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        a(f.c(this, attributeSet, i2, 0));
    }

    private void a(f.a aVar) {
        this.a = aVar.a;
        int i2 = aVar.f2073c;
        if (i2 > 0) {
            super.setImageResource(i2);
        }
        int i3 = aVar.f2074d;
        if (i3 > 0) {
            super.setBackgroundResource(i3);
        }
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof GifViewSavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        GifViewSavedState gifViewSavedState = (GifViewSavedState) parcelable;
        super.onRestoreInstanceState(gifViewSavedState.getSuperState());
        gifViewSavedState.a(getDrawable(), 0);
        gifViewSavedState.a(getBackground(), 1);
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        return new GifViewSavedState(super.onSaveInstanceState(), this.a ? getDrawable() : null, this.a ? getBackground() : null);
    }

    @Override // android.view.View
    public void setBackgroundResource(int i2) {
        if (f.e(this, false, i2)) {
            return;
        }
        super.setBackgroundResource(i2);
    }

    public void setFreezesAnimation(boolean z) {
        this.a = z;
    }

    @Override // android.widget.ImageView
    public void setImageResource(int i2) {
        if (f.e(this, true, i2)) {
            return;
        }
        super.setImageResource(i2);
    }

    @Override // android.widget.ImageView
    public void setImageURI(Uri uri) {
        if (f.d(this, uri)) {
            return;
        }
        super.setImageURI(uri);
    }
}
