package cn.hugo.android.scanner;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.util.Log;
import com.oldpods.app.R;
import java.io.IOException;

/* loaded from: classes.dex */
final class b implements MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener {
    private static final String e = b.class.getSimpleName();
    private final Activity a;
    private MediaPlayer b = null;

    /* renamed from: c, reason: collision with root package name */
    private boolean f1418c;

    /* renamed from: d, reason: collision with root package name */
    private boolean f1419d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(Activity activity) {
        this.a = activity;
        e();
    }

    private MediaPlayer a(Context context) {
        MediaPlayer mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(3);
        mediaPlayer.setOnCompletionListener(this);
        mediaPlayer.setOnErrorListener(this);
        AssetFileDescriptor openRawResourceFd = context.getResources().openRawResourceFd(R.raw.beep);
        try {
            mediaPlayer.setDataSource(openRawResourceFd.getFileDescriptor(), openRawResourceFd.getStartOffset(), openRawResourceFd.getLength());
            openRawResourceFd.close();
            mediaPlayer.setVolume(0.1f, 0.1f);
            mediaPlayer.prepare();
            return mediaPlayer;
        } catch (IOException e2) {
            Log.w(e, e2);
            return null;
        }
    }

    private static boolean d(SharedPreferences sharedPreferences, Context context) {
        boolean z = sharedPreferences.getBoolean("preferences_play_beep", true);
        if (!z || ((AudioManager) context.getSystemService("audio")).getRingerMode() == 2) {
            return z;
        }
        return false;
    }

    public synchronized void b() {
        MediaPlayer mediaPlayer = this.b;
        if (mediaPlayer != null) {
            mediaPlayer.release();
            this.b = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void c() {
        MediaPlayer mediaPlayer;
        if (this.f1418c && (mediaPlayer = this.b) != null) {
            mediaPlayer.start();
        }
        if (this.f1419d) {
            ((Vibrator) this.a.getSystemService("vibrator")).vibrate(200L);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void e() {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.a);
        this.f1418c = d(defaultSharedPreferences, this.a);
        this.f1419d = defaultSharedPreferences.getBoolean("preferences_vibrate", false);
        if (this.f1418c && this.b == null) {
            this.a.setVolumeControlStream(3);
            this.b = a(this.a);
        }
    }

    @Override // android.media.MediaPlayer.OnCompletionListener
    public void onCompletion(MediaPlayer mediaPlayer) {
        mediaPlayer.seekTo(0);
    }

    @Override // android.media.MediaPlayer.OnErrorListener
    public synchronized boolean onError(MediaPlayer mediaPlayer, int i2, int i3) {
        if (i2 == 100) {
            this.a.finish();
        } else {
            mediaPlayer.release();
            this.b = null;
            e();
        }
        return true;
    }
}
