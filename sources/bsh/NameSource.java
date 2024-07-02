package bsh;

/* loaded from: classes.dex */
public interface NameSource {

    /* loaded from: classes.dex */
    public interface Listener {
        void nameSourceChanged(NameSource nameSource);
    }

    void addNameSourceListener(Listener listener);

    String[] getAllNames();
}
