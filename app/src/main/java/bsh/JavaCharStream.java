package bsh;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/* loaded from: classes.dex */
public class JavaCharStream {
    public static final boolean staticFlag = false;
    int available;
    protected int[] bufcolumn;
    protected char[] buffer;
    protected int[] bufline;
    public int bufpos;
    int bufsize;
    protected int column;
    protected int inBuf;
    protected Reader inputStream;
    protected int line;
    protected int maxNextCharInd;
    protected char[] nextCharBuf;
    protected int nextCharInd;
    protected boolean prevCharIsCR;
    protected boolean prevCharIsLF;
    int tokenBegin;

    public JavaCharStream(InputStream inputStream) {
        this(inputStream, 1, 1, 4096);
    }

    public JavaCharStream(InputStream inputStream, int i2, int i3) {
        this(inputStream, i2, i3, 4096);
    }

    public JavaCharStream(InputStream inputStream, int i2, int i3, int i4) {
        this(new InputStreamReader(inputStream), i2, i3, 4096);
    }

    public JavaCharStream(Reader reader) {
        this(reader, 1, 1, 4096);
    }

    public JavaCharStream(Reader reader, int i2, int i3) {
        this(reader, i2, i3, 4096);
    }

    public JavaCharStream(Reader reader, int i2, int i3, int i4) {
        this.bufpos = -1;
        this.column = 0;
        this.line = 1;
        this.prevCharIsCR = false;
        this.prevCharIsLF = false;
        this.maxNextCharInd = 0;
        this.nextCharInd = -1;
        this.inBuf = 0;
        this.inputStream = reader;
        this.line = i2;
        this.column = i3 - 1;
        this.bufsize = i4;
        this.available = i4;
        this.buffer = new char[i4];
        this.bufline = new int[i4];
        this.bufcolumn = new int[i4];
        this.nextCharBuf = new char[4096];
    }

    static final int hexval(char c2) {
        switch (c2) {
            case '0':
                return 0;
            case '1':
                return 1;
            case '2':
                return 2;
            case '3':
                return 3;
            case '4':
                return 4;
            case '5':
                return 5;
            case '6':
                return 6;
            case '7':
                return 7;
            case '8':
                return 8;
            case '9':
                return 9;
            default:
                switch (c2) {
                    case 'A':
                        return 10;
                    case 'B':
                        return 11;
                    case 'C':
                        return 12;
                    case 'D':
                        return 13;
                    case 'E':
                        return 14;
                    case 'F':
                        return 15;
                    default:
                        switch (c2) {
                            case 'a':
                                return 10;
                            case 'b':
                                return 11;
                            case 'c':
                                return 12;
                            case 'd':
                                return 13;
                            case 'e':
                                return 14;
                            case 'f':
                                return 15;
                            default:
                                throw new IOException();
                        }
                }
        }
    }

    protected void AdjustBuffSize() {
        int i2 = this.available;
        int i3 = this.bufsize;
        if (i2 == i3) {
            int i4 = this.tokenBegin;
            if (i4 <= 2048) {
                ExpandBuff(false);
                return;
            } else {
                this.bufpos = 0;
                this.available = i4;
                return;
            }
        }
        int i5 = this.tokenBegin;
        if (i2 > i5) {
            this.available = i3;
        } else if (i5 - i2 < 2048) {
            ExpandBuff(true);
        } else {
            this.available = i5;
        }
    }

    public char BeginToken() {
        int i2 = this.inBuf;
        if (i2 <= 0) {
            this.tokenBegin = 0;
            this.bufpos = -1;
            return readChar();
        }
        this.inBuf = i2 - 1;
        int i3 = this.bufpos + 1;
        this.bufpos = i3;
        if (i3 == this.bufsize) {
            this.bufpos = 0;
        }
        int i4 = this.bufpos;
        this.tokenBegin = i4;
        return this.buffer[i4];
    }

    public void Done() {
        this.nextCharBuf = null;
        this.buffer = null;
        this.bufline = null;
        this.bufcolumn = null;
    }

    protected void ExpandBuff(boolean z) {
        int i2 = this.bufsize;
        char[] cArr = new char[i2 + 2048];
        int[] iArr = new int[i2 + 2048];
        int[] iArr2 = new int[i2 + 2048];
        try {
            if (z) {
                char[] cArr2 = this.buffer;
                int i3 = this.tokenBegin;
                System.arraycopy(cArr2, i3, cArr, 0, i2 - i3);
                System.arraycopy(this.buffer, 0, cArr, this.bufsize - this.tokenBegin, this.bufpos);
                this.buffer = cArr;
                int[] iArr3 = this.bufline;
                int i4 = this.tokenBegin;
                System.arraycopy(iArr3, i4, iArr, 0, this.bufsize - i4);
                System.arraycopy(this.bufline, 0, iArr, this.bufsize - this.tokenBegin, this.bufpos);
                this.bufline = iArr;
                int[] iArr4 = this.bufcolumn;
                int i5 = this.tokenBegin;
                System.arraycopy(iArr4, i5, iArr2, 0, this.bufsize - i5);
                System.arraycopy(this.bufcolumn, 0, iArr2, this.bufsize - this.tokenBegin, this.bufpos);
                this.bufcolumn = iArr2;
                this.bufpos += this.bufsize - this.tokenBegin;
            } else {
                char[] cArr3 = this.buffer;
                int i6 = this.tokenBegin;
                System.arraycopy(cArr3, i6, cArr, 0, i2 - i6);
                this.buffer = cArr;
                int[] iArr5 = this.bufline;
                int i7 = this.tokenBegin;
                System.arraycopy(iArr5, i7, iArr, 0, this.bufsize - i7);
                this.bufline = iArr;
                int[] iArr6 = this.bufcolumn;
                int i8 = this.tokenBegin;
                System.arraycopy(iArr6, i8, iArr2, 0, this.bufsize - i8);
                this.bufcolumn = iArr2;
                this.bufpos -= this.tokenBegin;
            }
            int i9 = this.bufsize + 2048;
            this.bufsize = i9;
            this.available = i9;
            this.tokenBegin = 0;
        } catch (Throwable th) {
            throw new Error(th.getMessage());
        }
    }

    protected void FillBuff() {
        if (this.maxNextCharInd == 4096) {
            this.nextCharInd = 0;
            this.maxNextCharInd = 0;
        }
        try {
            Reader reader = this.inputStream;
            char[] cArr = this.nextCharBuf;
            int i2 = this.maxNextCharInd;
            int read = reader.read(cArr, i2, 4096 - i2);
            if (read != -1) {
                this.maxNextCharInd += read;
            } else {
                this.inputStream.close();
                throw new IOException();
            }
        } catch (IOException e) {
            int i3 = this.bufpos;
            if (i3 != 0) {
                this.bufpos = i3 - 1;
                backup(0);
            } else {
                this.bufline[i3] = this.line;
                this.bufcolumn[i3] = this.column;
            }
            throw e;
        }
    }

    public String GetImage() {
        if (this.bufpos >= this.tokenBegin) {
            char[] cArr = this.buffer;
            int i2 = this.tokenBegin;
            return new String(cArr, i2, (this.bufpos - i2) + 1);
        }
        StringBuffer stringBuffer = new StringBuffer();
        char[] cArr2 = this.buffer;
        int i3 = this.tokenBegin;
        stringBuffer.append(new String(cArr2, i3, this.bufsize - i3));
        stringBuffer.append(new String(this.buffer, 0, this.bufpos + 1));
        return stringBuffer.toString();
    }

    public char[] GetSuffix(int i2) {
        char[] cArr = new char[i2];
        int i3 = this.bufpos;
        if (i3 + 1 >= i2) {
            System.arraycopy(this.buffer, (i3 - i2) + 1, cArr, 0, i2);
        } else {
            System.arraycopy(this.buffer, this.bufsize - ((i2 - i3) - 1), cArr, 0, (i2 - i3) - 1);
            System.arraycopy(this.buffer, 0, cArr, (i2 - r2) - 1, this.bufpos + 1);
        }
        return cArr;
    }

    public void ReInit(InputStream inputStream) {
        ReInit(inputStream, 1, 1, 4096);
    }

    public void ReInit(InputStream inputStream, int i2, int i3) {
        ReInit(inputStream, i2, i3, 4096);
    }

    public void ReInit(InputStream inputStream, int i2, int i3, int i4) {
        ReInit(new InputStreamReader(inputStream), i2, i3, 4096);
    }

    public void ReInit(Reader reader) {
        ReInit(reader, 1, 1, 4096);
    }

    public void ReInit(Reader reader, int i2, int i3) {
        ReInit(reader, i2, i3, 4096);
    }

    public void ReInit(Reader reader, int i2, int i3, int i4) {
        this.inputStream = reader;
        this.line = i2;
        this.column = i3 - 1;
        char[] cArr = this.buffer;
        if (cArr == null || i4 != cArr.length) {
            this.bufsize = i4;
            this.available = i4;
            this.buffer = new char[i4];
            this.bufline = new int[i4];
            this.bufcolumn = new int[i4];
            this.nextCharBuf = new char[4096];
        }
        this.prevCharIsCR = false;
        this.prevCharIsLF = false;
        this.maxNextCharInd = 0;
        this.inBuf = 0;
        this.tokenBegin = 0;
        this.bufpos = -1;
        this.nextCharInd = -1;
    }

    protected char ReadByte() {
        int i2 = this.nextCharInd + 1;
        this.nextCharInd = i2;
        if (i2 >= this.maxNextCharInd) {
            FillBuff();
        }
        return this.nextCharBuf[this.nextCharInd];
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void UpdateLineColumn(char r5) {
        /*
            r4 = this;
            int r0 = r4.column
            r1 = 1
            int r0 = r0 + r1
            r4.column = r0
            boolean r0 = r4.prevCharIsLF
            r2 = 10
            r3 = 0
            if (r0 == 0) goto L17
            r4.prevCharIsLF = r3
        Lf:
            int r0 = r4.line
            r4.column = r1
            int r0 = r0 + r1
            r4.line = r0
            goto L21
        L17:
            boolean r0 = r4.prevCharIsCR
            if (r0 == 0) goto L21
            r4.prevCharIsCR = r3
            if (r5 != r2) goto Lf
            r4.prevCharIsLF = r1
        L21:
            r0 = 9
            if (r5 == r0) goto L32
            if (r5 == r2) goto L2f
            r0 = 13
            if (r5 == r0) goto L2c
            goto L3e
        L2c:
            r4.prevCharIsCR = r1
            goto L3e
        L2f:
            r4.prevCharIsLF = r1
            goto L3e
        L32:
            int r5 = r4.column
            int r5 = r5 - r1
            r4.column = r5
            r0 = r5 & 7
            int r0 = 8 - r0
            int r5 = r5 + r0
            r4.column = r5
        L3e:
            int[] r5 = r4.bufline
            int r0 = r4.bufpos
            int r1 = r4.line
            r5[r0] = r1
            int[] r5 = r4.bufcolumn
            int r1 = r4.column
            r5[r0] = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: bsh.JavaCharStream.UpdateLineColumn(char):void");
    }

    public void adjustBeginLineColumn(int i2, int i3) {
        int i4;
        int i5 = this.tokenBegin;
        int i6 = this.bufpos;
        if (i6 >= i5) {
            i4 = (i6 - i5) + this.inBuf + 1;
        } else {
            i4 = this.inBuf + (this.bufsize - i5) + i6 + 1;
        }
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        while (true) {
            if (i7 >= i4) {
                break;
            }
            int[] iArr = this.bufline;
            int i10 = this.bufsize;
            int i11 = i5 % i10;
            i5++;
            int i12 = i5 % i10;
            if (iArr[i11] != iArr[i12]) {
                i9 = i11;
                break;
            }
            iArr[i11] = i2;
            int[] iArr2 = this.bufcolumn;
            int i13 = (iArr2[i12] + i8) - iArr2[i11];
            iArr2[i11] = i8 + i3;
            i7++;
            i8 = i13;
            i9 = i11;
        }
        if (i7 < i4) {
            int i14 = i2 + 1;
            this.bufline[i9] = i2;
            this.bufcolumn[i9] = i3 + i8;
            while (true) {
                int i15 = i7 + 1;
                if (i7 >= i4) {
                    break;
                }
                int[] iArr3 = this.bufline;
                int i16 = this.bufsize;
                i9 = i5 % i16;
                i5++;
                if (iArr3[i9] != iArr3[i5 % i16]) {
                    iArr3[i9] = i14;
                    i14++;
                } else {
                    iArr3[i9] = i14;
                }
                i7 = i15;
            }
        }
        this.line = this.bufline[i9];
        this.column = this.bufcolumn[i9];
    }

    public void backup(int i2) {
        this.inBuf += i2;
        int i3 = this.bufpos - i2;
        this.bufpos = i3;
        if (i3 < 0) {
            this.bufpos = i3 + this.bufsize;
        }
    }

    public int getBeginColumn() {
        return this.bufcolumn[this.tokenBegin];
    }

    public int getBeginLine() {
        return this.bufline[this.tokenBegin];
    }

    public int getColumn() {
        return this.bufcolumn[this.bufpos];
    }

    public int getEndColumn() {
        return this.bufcolumn[this.bufpos];
    }

    public int getEndLine() {
        return this.bufline[this.bufpos];
    }

    public int getLine() {
        return this.bufline[this.bufpos];
    }

    public char readChar() {
        char ReadByte;
        char ReadByte2;
        int i2 = this.inBuf;
        if (i2 > 0) {
            this.inBuf = i2 - 1;
            int i3 = this.bufpos + 1;
            this.bufpos = i3;
            if (i3 == this.bufsize) {
                this.bufpos = 0;
            }
            return this.buffer[this.bufpos];
        }
        int i4 = this.bufpos + 1;
        this.bufpos = i4;
        if (i4 == this.available) {
            AdjustBuffSize();
        }
        char[] cArr = this.buffer;
        int i5 = this.bufpos;
        char ReadByte3 = ReadByte();
        cArr[i5] = ReadByte3;
        UpdateLineColumn(ReadByte3);
        if (ReadByte3 != '\\') {
            return ReadByte3;
        }
        int i6 = 1;
        while (true) {
            int i7 = this.bufpos + 1;
            this.bufpos = i7;
            if (i7 == this.available) {
                AdjustBuffSize();
            }
            try {
                char[] cArr2 = this.buffer;
                int i8 = this.bufpos;
                ReadByte = ReadByte();
                cArr2[i8] = ReadByte;
                if (ReadByte != '\\') {
                    break;
                }
                UpdateLineColumn(ReadByte);
                i6++;
            } catch (IOException unused) {
                if (i6 > 1) {
                    backup(i6);
                }
                return '\\';
            }
        }
        UpdateLineColumn(ReadByte);
        if (ReadByte != 'u' || (i6 & 1) != 1) {
            backup(i6);
            return '\\';
        }
        int i9 = this.bufpos - 1;
        this.bufpos = i9;
        if (i9 < 0) {
            this.bufpos = this.bufsize - 1;
        }
        while (true) {
            try {
                ReadByte2 = ReadByte();
                if (ReadByte2 != 'u') {
                    break;
                }
                this.column++;
            } catch (IOException unused2) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Invalid escape character at line ");
                stringBuffer.append(this.line);
                stringBuffer.append(" column ");
                stringBuffer.append(this.column);
                stringBuffer.append(".");
                throw new Error(stringBuffer.toString());
            }
        }
        char[] cArr3 = this.buffer;
        int i10 = this.bufpos;
        char hexval = (char) ((hexval(ReadByte2) << 12) | (hexval(ReadByte()) << 8) | (hexval(ReadByte()) << 4) | hexval(ReadByte()));
        cArr3[i10] = hexval;
        this.column += 4;
        if (i6 == 1) {
            return hexval;
        }
        backup(i6 - 1);
        return '\\';
    }
}
