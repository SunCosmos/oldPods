package bsh;

import java.io.FilterReader;
import java.io.InputStreamReader;
import java.io.Reader;

/* loaded from: classes.dex */
class CommandLineReader extends FilterReader {
    static final int lastCharNL = 1;
    static final int normal = 0;
    static final int sentSemi = 2;
    int state;

    public CommandLineReader(Reader reader) {
        super(reader);
        this.state = 1;
    }

    public static void main(String[] strArr) {
        while (true) {
            System.out.println(new CommandLineReader(new InputStreamReader(System.in)).read());
        }
    }

    @Override // java.io.FilterReader, java.io.Reader
    public int read() {
        int read;
        if (this.state == 2) {
            this.state = 1;
            return 10;
        }
        do {
            read = ((FilterReader) this).in.read();
        } while (read == 13);
        if (read != 10) {
            this.state = 0;
            return read;
        }
        if (this.state == 1) {
            this.state = 2;
            return 59;
        }
        this.state = 1;
        return read;
    }

    @Override // java.io.FilterReader, java.io.Reader
    public int read(char[] cArr, int i2, int i3) {
        int read = read();
        if (read == -1) {
            return -1;
        }
        cArr[i2] = (char) read;
        return 1;
    }
}
