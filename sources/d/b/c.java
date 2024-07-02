package d.b;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

/* loaded from: classes.dex */
public class c extends b<a> {
    private Reader e;
    private int f = 1;
    private int g = 0;

    public c(Reader reader) {
        if (reader == null) {
            throw new IllegalArgumentException("input must not be null");
        }
        this.e = new BufferedReader(reader);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // d.b.b
    /* renamed from: i, reason: merged with bridge method [inline-methods] */
    public a d() {
        return new a((char) 0, this.f, this.g);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // d.b.b
    /* renamed from: j, reason: merged with bridge method [inline-methods] */
    public a e() {
        try {
            int read = this.e.read();
            if (read == -1) {
                return null;
            }
            if (read == 10) {
                this.f++;
                this.g = 0;
            }
            this.g++;
            return new a((char) read, this.f, this.g);
        } catch (IOException e) {
            this.f2041c.add(d.a(new a((char) 0, this.f, this.g), e.getMessage()));
            return null;
        }
    }

    public String toString() {
        StringBuilder sb;
        a f;
        if (this.a.size() == 0) {
            sb = new StringBuilder();
            sb.append(this.f);
            sb.append(":");
            sb.append(this.g);
            sb.append(": Buffer empty");
        } else {
            if (this.a.size() < 2) {
                sb = new StringBuilder();
                sb.append(this.f);
                sb.append(":");
                sb.append(this.g);
                sb.append(": ");
                f = c();
            } else {
                sb = new StringBuilder();
                sb.append(this.f);
                sb.append(":");
                sb.append(this.g);
                sb.append(": ");
                sb.append(c());
                sb.append(", ");
                f = f();
            }
            sb.append(f);
        }
        return sb.toString();
    }
}
