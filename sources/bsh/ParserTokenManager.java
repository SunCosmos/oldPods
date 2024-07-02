package bsh;

import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v7.widget.ActivityChooserView;
import bsh.org.objectweb.asm.Constants;
import java.io.IOException;
import java.io.PrintStream;

/* loaded from: classes.dex */
public class ParserTokenManager implements ParserConstants {
    protected char curChar;
    int curLexState;
    public PrintStream debugStream;
    int defaultLexState;
    protected JavaCharStream input_stream;
    int jjmatchedKind;
    int jjmatchedPos;
    int jjnewStateCnt;
    int jjround;
    private final int[] jjrounds;
    private final int[] jjstateSet;
    static final long[] jjbitVec0 = {0, 0, -1, -1};
    static final long[] jjbitVec1 = {-2, -1, -1, -1};
    static final long[] jjbitVec3 = {2301339413881290750L, -16384, 4294967295L, 432345564227567616L};
    static final long[] jjbitVec4 = {0, 0, 0, -36028797027352577L};
    static final long[] jjbitVec5 = {0, -1, -1, -1};
    static final long[] jjbitVec6 = {-1, -1, 65535, 0};
    static final long[] jjbitVec7 = {-1, -1, 0, 0};
    static final long[] jjbitVec8 = {70368744177663L, 0, 0, 0};
    static final int[] jjnextStates = {37, 38, 43, 44, 47, 48, 15, 56, 61, 73, 26, 27, 29, 17, 19, 52, 54, 9, 57, 58, 60, 2, 3, 5, 11, 12, 15, 26, 27, 31, 29, 39, 40, 15, 47, 48, 15, 63, 64, 66, 69, 70, 72, 13, 14, 20, 21, 23, 28, 30, 32, 41, 42, 45, 46, 49, 50};
    public static final String[] jjstrLiteralImages = {"", null, null, null, null, null, null, null, null, null, "abstract", "boolean", "break", "class", "byte", "case", "catch", "char", "const", "continue", "default", "do", "double", "else", "enum", "extends", "false", "final", "finally", "float", "for", "goto", "if", "implements", "import", "instanceof", "int", "interface", "long", "native", "new", "null", "package", "private", "protected", "public", "return", "short", "static", "strictfp", "switch", "synchronized", "transient", "throw", "throws", "true", "try", "void", "volatile", "while", null, null, null, null, null, null, null, null, null, null, null, null, "(", ")", "{", "}", "[", "]", ";", ",", ".", "=", ">", "@gt", "<", "@lt", "!", "~", "?", ":", "==", "<=", "@lteq", ">=", "@gteq", "!=", "||", "@or", "&&", "@and", "++", "--", "+", "-", "*", "/", "&", "@bitwise_and", "|", "@bitwise_or", "^", "%", "<<", "@left_shift", ">>", "@right_shift", ">>>", "@right_unsigned_shift", "+=", "-=", "*=", "/=", "&=", "@and_assign", "|=", "@or_assign", "^=", "%=", "<<=", "@left_shift_assign", ">>=", "@right_shift_assign", ">>>=", "@right_unsigned_shift_assign"};
    public static final String[] lexStateNames = {"DEFAULT"};
    static final long[] jjtoToken = {2305843009213692929L, -195, 63};
    static final long[] jjtoSkip = {1022, 0, 0};
    static final long[] jjtoSpecial = {896, 0, 0};

    public ParserTokenManager(JavaCharStream javaCharStream) {
        this.debugStream = System.out;
        this.jjrounds = new int[74];
        this.jjstateSet = new int[Constants.LCMP];
        this.curLexState = 0;
        this.defaultLexState = 0;
        this.input_stream = javaCharStream;
    }

    public ParserTokenManager(JavaCharStream javaCharStream, int i2) {
        this(javaCharStream);
        SwitchTo(i2);
    }

    private final void ReInitRounds() {
        this.jjround = -2147483647;
        int i2 = 74;
        while (true) {
            int i3 = i2 - 1;
            if (i2 <= 0) {
                return;
            }
            this.jjrounds[i3] = Integer.MIN_VALUE;
            i2 = i3;
        }
    }

    private final void jjAddStates(int i2, int i3) {
        while (true) {
            int[] iArr = this.jjstateSet;
            int i4 = this.jjnewStateCnt;
            this.jjnewStateCnt = i4 + 1;
            iArr[i4] = jjnextStates[i2];
            int i5 = i2 + 1;
            if (i2 == i3) {
                return;
            } else {
                i2 = i5;
            }
        }
    }

    private static final boolean jjCanMove_0(int i2, int i3, int i4, long j, long j2) {
        return i2 == 0 && (jjbitVec0[i4] & j2) != 0;
    }

    private static final boolean jjCanMove_1(int i2, int i3, int i4, long j, long j2) {
        return i2 != 0 ? (jjbitVec1[i3] & j) != 0 : (jjbitVec0[i4] & j2) != 0;
    }

    private static final boolean jjCanMove_2(int i2, int i3, int i4, long j, long j2) {
        return i2 != 0 ? i2 != 51 ? i2 != 61 ? i2 != 48 ? i2 != 49 ? (jjbitVec3[i3] & j) != 0 : (jjbitVec6[i4] & j2) != 0 : (jjbitVec5[i4] & j2) != 0 : (jjbitVec8[i4] & j2) != 0 : (jjbitVec7[i4] & j2) != 0 : (jjbitVec4[i4] & j2) != 0;
    }

    private final void jjCheckNAdd(int i2) {
        int[] iArr = this.jjrounds;
        int i3 = iArr[i2];
        int i4 = this.jjround;
        if (i3 != i4) {
            int[] iArr2 = this.jjstateSet;
            int i5 = this.jjnewStateCnt;
            this.jjnewStateCnt = i5 + 1;
            iArr2[i5] = i2;
            iArr[i2] = i4;
        }
    }

    private final void jjCheckNAddStates(int i2) {
        int[] iArr = jjnextStates;
        jjCheckNAdd(iArr[i2]);
        jjCheckNAdd(iArr[i2 + 1]);
    }

    private final void jjCheckNAddStates(int i2, int i3) {
        while (true) {
            jjCheckNAdd(jjnextStates[i2]);
            int i4 = i2 + 1;
            if (i2 == i3) {
                return;
            } else {
                i2 = i4;
            }
        }
    }

    private final void jjCheckNAddTwoStates(int i2, int i3) {
        jjCheckNAdd(i2);
        jjCheckNAdd(i3);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:167:0x0261, code lost:
    
        if (r4 > 69) goto L162;
     */
    /* JADX WARN: Code restructure failed: missing block: B:168:0x0263, code lost:
    
        r4 = 69;
     */
    /* JADX WARN: Code restructure failed: missing block: B:171:0x0270, code lost:
    
        if (r4 > 69) goto L162;
     */
    /* JADX WARN: Code restructure failed: missing block: B:179:0x0299, code lost:
    
        if ((r21 & 71776119061217280L) != 0) goto L188;
     */
    /* JADX WARN: Code restructure failed: missing block: B:180:0x02c4, code lost:
    
        r1 = 12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:181:0x02dd, code lost:
    
        jjCheckNAddStates(10, r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:191:0x02c2, code lost:
    
        if ((r21 & 566935683072L) != 0) goto L188;
     */
    /* JADX WARN: Code restructure failed: missing block: B:193:0x02d2, code lost:
    
        if ((r21 & (-17179878401L)) != 0) goto L194;
     */
    /* JADX WARN: Code restructure failed: missing block: B:195:0x02db, code lost:
    
        if (r35.curChar == '\"') goto L194;
     */
    /* JADX WARN: Code restructure failed: missing block: B:203:0x0303, code lost:
    
        if ((r21 & 71776119061217280L) != 0) goto L217;
     */
    /* JADX WARN: Code restructure failed: missing block: B:204:0x033d, code lost:
    
        r1 = 18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:209:0x031d, code lost:
    
        if ((r21 & 566935683072L) != 0) goto L168;
     */
    /* JADX WARN: Code restructure failed: missing block: B:216:0x033b, code lost:
    
        if ((r21 & (-549755823105L)) != 0) goto L217;
     */
    /* JADX WARN: Code restructure failed: missing block: B:298:0x0474, code lost:
    
        if (r4 > 8) goto L313;
     */
    /* JADX WARN: Code restructure failed: missing block: B:299:0x0484, code lost:
    
        r4 = 8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:302:0x0482, code lost:
    
        if (r4 > 8) goto L313;
     */
    /* JADX WARN: Code restructure failed: missing block: B:304:0x048e, code lost:
    
        if ((r21 & (-9217)) != 0) goto L319;
     */
    /* JADX WARN: Code restructure failed: missing block: B:305:0x0498, code lost:
    
        jjCheckNAddStates(21, 23);
     */
    /* JADX WARN: Code restructure failed: missing block: B:307:0x0496, code lost:
    
        if (r35.curChar == '!') goto L319;
     */
    /* JADX WARN: Code restructure failed: missing block: B:350:0x0563, code lost:
    
        if ((r21 & 5700160604602368L) != 0) goto L373;
     */
    /* JADX WARN: Code restructure failed: missing block: B:351:0x057a, code lost:
    
        jjCheckNAddStates(10, 12);
     */
    /* JADX WARN: Code restructure failed: missing block: B:356:0x0578, code lost:
    
        if ((r21 & (-268435457)) != 0) goto L373;
     */
    /* JADX WARN: Code restructure failed: missing block: B:358:0x058a, code lost:
    
        if ((r21 & 5700160604602368L) != 0) goto L382;
     */
    /* JADX WARN: Code restructure failed: missing block: B:359:0x05a3, code lost:
    
        r1 = 18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0073, code lost:
    
        if ((r21 & (-4398046511105L)) != 0) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:364:0x05a1, code lost:
    
        if ((r21 & (-268435457)) != 0) goto L382;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0081, code lost:
    
        jjCheckNAddTwoStates(71, 69);
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x007f, code lost:
    
        if ((r21 & (-145135534866433L)) != 0) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0099, code lost:
    
        if ((r21 & (-4398046511105L)) != 0) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00a0, code lost:
    
        jjCheckNAddTwoStates(68, 69);
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x009e, code lost:
    
        if (r35.curChar == '*') goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x00b9, code lost:
    
        if ((r21 & (-4398046511105L)) != 0) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x00c7, code lost:
    
        jjCheckNAddTwoStates(65, r15);
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x00c5, code lost:
    
        if ((r21 & (-145135534866433L)) != 0) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x00ea, code lost:
    
        if (r35.curChar == '*') goto L52;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x00ec, code lost:
    
        jjCheckNAdd(62);
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x0107, code lost:
    
        if (r4 > 7) goto L63;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x0114, code lost:
    
        r4 = 7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x0112, code lost:
    
        if (r4 > 7) goto L63;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x0148, code lost:
    
        if (r35.curChar == '*') goto L52;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:320:0x04d3. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:9:0x004a. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:23:0x07af  */
    /* JADX WARN: Removed duplicated region for block: B:257:0x0434  */
    /* JADX WARN: Removed duplicated region for block: B:262:0x0448  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x07c4 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x07c3 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:425:0x07d1 A[LOOP:3: B:396:0x061d->B:425:0x07d1, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:426:0x07aa A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final int jjMoveNfa_0(int r36, int r37) {
        /*
            Method dump skipped, instructions count: 2256
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: bsh.ParserTokenManager.jjMoveNfa_0(int, int):int");
    }

    private final int jjMoveStringLiteralDfa0_0() {
        char c2 = this.curChar;
        if (c2 == '\t') {
            return jjStartNfaWithStates_0(0, 2, 0);
        }
        if (c2 == '\n') {
            return jjStartNfaWithStates_0(0, 5, 0);
        }
        if (c2 == '\f') {
            return jjStartNfaWithStates_0(0, 4, 0);
        }
        if (c2 == '\r') {
            return jjStartNfaWithStates_0(0, 3, 0);
        }
        if (c2 == ' ') {
            return jjStartNfaWithStates_0(0, 1, 0);
        }
        if (c2 == '!') {
            this.jjmatchedKind = 86;
            return jjMoveStringLiteralDfa1_0(0L, 2147483648L, 0L);
        }
        if (c2 == '%') {
            this.jjmatchedKind = 111;
            return jjMoveStringLiteralDfa1_0(0L, Long.MIN_VALUE, 0L);
        }
        if (c2 == '&') {
            this.jjmatchedKind = 106;
            return jjMoveStringLiteralDfa1_0(0L, 288230393331580928L, 0L);
        }
        if (c2 == ']') {
            return jjStopAtPos(0, 77);
        }
        if (c2 == '^') {
            this.jjmatchedKind = 110;
            return jjMoveStringLiteralDfa1_0(0L, 4611686018427387904L, 0L);
        }
        if (c2 == 'v') {
            return jjMoveStringLiteralDfa1_0(432345564227567616L, 0L, 0L);
        }
        if (c2 == 'w') {
            return jjMoveStringLiteralDfa1_0(576460752303423488L, 0L, 0L);
        }
        switch (c2) {
            case '(':
                return jjStopAtPos(0, 72);
            case ')':
                return jjStopAtPos(0, 73);
            case '*':
                this.jjmatchedKind = 104;
                return jjMoveStringLiteralDfa1_0(0L, 72057594037927936L, 0L);
            case '+':
                this.jjmatchedKind = 102;
                return jjMoveStringLiteralDfa1_0(0L, 18014467228958720L, 0L);
            case ',':
                return jjStopAtPos(0, 79);
            case '-':
                this.jjmatchedKind = 103;
                return jjMoveStringLiteralDfa1_0(0L, 36028934457917440L, 0L);
            case '.':
                return jjStartNfaWithStates_0(0, 80, 11);
            case '/':
                this.jjmatchedKind = 105;
                return jjMoveStringLiteralDfa1_0(0L, 144115188075855872L, 0L);
            case '[':
                return jjStopAtPos(0, 76);
            case 'i':
                return jjMoveStringLiteralDfa1_0(270582939648L, 0L, 0L);
            case 'l':
                return jjMoveStringLiteralDfa1_0(274877906944L, 0L, 0L);
            case 'n':
                return jjMoveStringLiteralDfa1_0(3848290697216L, 0L, 0L);
            case 'p':
                return jjMoveStringLiteralDfa1_0(65970697666560L, 0L, 0L);
            case '{':
                return jjStopAtPos(0, 74);
            case '|':
                this.jjmatchedKind = 108;
                return jjMoveStringLiteralDfa1_0(0L, 1152921508901814272L, 0L);
            case '}':
                return jjStopAtPos(0, 75);
            case '~':
                return jjStopAtPos(0, 87);
            default:
                switch (c2) {
                    case ':':
                        return jjStopAtPos(0, 89);
                    case ';':
                        return jjStopAtPos(0, 78);
                    case '<':
                        this.jjmatchedKind = 84;
                        return jjMoveStringLiteralDfa1_0(0L, 281475110928384L, 1L);
                    case '=':
                        this.jjmatchedKind = 81;
                        return jjMoveStringLiteralDfa1_0(0L, 67108864L, 0L);
                    case '>':
                        this.jjmatchedKind = 82;
                        return jjMoveStringLiteralDfa1_0(0L, 5629500071084032L, 20L);
                    case '?':
                        return jjStopAtPos(0, 88);
                    case '@':
                        return jjMoveStringLiteralDfa1_0(0L, 2894169735298547712L, 42L);
                    default:
                        switch (c2) {
                            case 'a':
                                return jjMoveStringLiteralDfa1_0(PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID, 0L, 0L);
                            case 'b':
                                return jjMoveStringLiteralDfa1_0(22528L, 0L, 0L);
                            case 'c':
                                return jjMoveStringLiteralDfa1_0(1024000L, 0L, 0L);
                            case 'd':
                                return jjMoveStringLiteralDfa1_0(7340032L, 0L, 0L);
                            case 'e':
                                return jjMoveStringLiteralDfa1_0(58720256L, 0L, 0L);
                            case 'f':
                                return jjMoveStringLiteralDfa1_0(2080374784L, 0L, 0L);
                            case 'g':
                                return jjMoveStringLiteralDfa1_0(2147483648L, 0L, 0L);
                            default:
                                switch (c2) {
                                    case 'r':
                                        return jjMoveStringLiteralDfa1_0(70368744177664L, 0L, 0L);
                                    case 's':
                                        return jjMoveStringLiteralDfa1_0(4362862139015168L, 0L, 0L);
                                    case 't':
                                        return jjMoveStringLiteralDfa1_0(139611588448485376L, 0L, 0L);
                                    default:
                                        return jjMoveNfa_0(6, 0);
                                }
                        }
                }
        }
    }

    private final int jjMoveStringLiteralDfa10_0(long j, long j2, long j3, long j4, long j5, long j6) {
        long j7 = j2 & j;
        long j8 = j4 & j3;
        long j9 = j6 & j5;
        if ((j7 | j8 | j9) == 0) {
            return jjStartNfa_0(8, j, j3, j5);
        }
        try {
            char readChar = this.input_stream.readChar();
            this.curChar = readChar;
            if (readChar == 'e') {
                return jjMoveStringLiteralDfa11_0(j7, 2251799813685248L, j8, 0L, j9, 0L);
            }
            if (readChar == 'f') {
                return jjMoveStringLiteralDfa11_0(j7, 0L, j8, 2251799813685248L, j9, 8L);
            }
            if (readChar == 'i') {
                return jjMoveStringLiteralDfa11_0(j7, 0L, j8, 9007199254740992L, j9, 32L);
            }
            if (readChar == 'n') {
                return (576460752303423488L & j8) != 0 ? jjStopAtPos(10, 123) : jjMoveStringLiteralDfa11_0(j7, 0L, j8, 8796093022208L, j9, 0L);
            }
            if (readChar != 'r') {
                if (readChar == 't') {
                    if ((562949953421312L & j8) != 0) {
                        this.jjmatchedKind = 113;
                        this.jjmatchedPos = 10;
                    }
                    return jjMoveStringLiteralDfa11_0(j7, 0L, j8, 0L, j9, 2L);
                }
            } else if ((35184372088832L & j8) != 0) {
                return jjStopAtPos(10, 109);
            }
            return jjStartNfa_0(9, j7, j8, j9);
        } catch (IOException unused) {
            jjStopStringLiteralDfa_0(9, j7, j8, j9);
            return 10;
        }
    }

    private final int jjMoveStringLiteralDfa11_0(long j, long j2, long j3, long j4, long j5, long j6) {
        long j7 = j2 & j;
        long j8 = j4 & j3;
        long j9 = j6 & j5;
        if ((j7 | j8 | j9) == 0) {
            return jjStartNfa_0(9, j, j3, j5);
        }
        try {
            char readChar = this.input_stream.readChar();
            this.curChar = readChar;
            if (readChar == '_') {
                return jjMoveStringLiteralDfa12_0(j7, 0L, j8, 0L, j9, 2L);
            }
            if (readChar != 'd') {
                if (readChar == 'g') {
                    return jjMoveStringLiteralDfa12_0(j7, 0L, j8, 9007199254740992L, j9, 32L);
                }
                if (readChar == 't') {
                    if ((j8 & 2251799813685248L) != 0) {
                        this.jjmatchedKind = 115;
                        this.jjmatchedPos = 11;
                    }
                    return jjMoveStringLiteralDfa12_0(j7, 0L, j8, 0L, j9, 8L);
                }
            } else {
                if ((j7 & 2251799813685248L) != 0) {
                    return jjStartNfaWithStates_0(11, 51, 35);
                }
                if ((8796093022208L & j8) != 0) {
                    return jjStopAtPos(11, 107);
                }
            }
            return jjStartNfa_0(10, j7, j8, j9);
        } catch (IOException unused) {
            jjStopStringLiteralDfa_0(10, j7, j8, j9);
            return 11;
        }
    }

    private final int jjMoveStringLiteralDfa12_0(long j, long j2, long j3, long j4, long j5, long j6) {
        long j7 = j4 & j3;
        long j8 = j6 & j5;
        if (((j2 & j) | j7 | j8) == 0) {
            return jjStartNfa_0(10, j, j3, j5);
        }
        try {
            char readChar = this.input_stream.readChar();
            this.curChar = readChar;
            return readChar != '_' ? readChar != 'a' ? readChar != 'n' ? jjStartNfa_0(11, 0L, j7, j8) : jjMoveStringLiteralDfa13_0(j7, 9007199254740992L, j8, 32L) : jjMoveStringLiteralDfa13_0(j7, 0L, j8, 2L) : jjMoveStringLiteralDfa13_0(j7, 0L, j8, 8L);
        } catch (IOException unused) {
            jjStopStringLiteralDfa_0(11, 0L, j7, j8);
            return 12;
        }
    }

    private final int jjMoveStringLiteralDfa13_0(long j, long j2, long j3, long j4) {
        long j5 = j2 & j;
        long j6 = j4 & j3;
        if ((j5 | j6) == 0) {
            return jjStartNfa_0(11, 0L, j, j3);
        }
        try {
            char readChar = this.input_stream.readChar();
            this.curChar = readChar;
            return readChar != 'a' ? readChar != 'e' ? readChar != 's' ? jjStartNfa_0(12, 0L, j5, j6) : jjMoveStringLiteralDfa14_0(j5, 0L, j6, 2L) : jjMoveStringLiteralDfa14_0(j5, 9007199254740992L, j6, 32L) : jjMoveStringLiteralDfa14_0(j5, 0L, j6, 8L);
        } catch (IOException unused) {
            jjStopStringLiteralDfa_0(12, 0L, j5, j6);
            return 13;
        }
    }

    private final int jjMoveStringLiteralDfa14_0(long j, long j2, long j3, long j4) {
        long j5 = j2 & j;
        long j6 = j4 & j3;
        if ((j5 | j6) == 0) {
            return jjStartNfa_0(12, 0L, j, j3);
        }
        try {
            char readChar = this.input_stream.readChar();
            this.curChar = readChar;
            return readChar != 'd' ? readChar != 's' ? jjStartNfa_0(13, 0L, j5, j6) : jjMoveStringLiteralDfa15_0(j5, 0L, j6, 10L) : jjMoveStringLiteralDfa15_0(j5, 9007199254740992L, j6, 32L);
        } catch (IOException unused) {
            jjStopStringLiteralDfa_0(13, 0L, j5, j6);
            return 14;
        }
    }

    private final int jjMoveStringLiteralDfa15_0(long j, long j2, long j3, long j4) {
        long j5 = j2 & j;
        long j6 = j4 & j3;
        if ((j5 | j6) == 0) {
            return jjStartNfa_0(13, 0L, j, j3);
        }
        try {
            char readChar = this.input_stream.readChar();
            this.curChar = readChar;
            return readChar != '_' ? readChar != 'i' ? readChar != 's' ? jjStartNfa_0(14, 0L, j5, j6) : jjMoveStringLiteralDfa16_0(j5, 0L, j6, 8L) : jjMoveStringLiteralDfa16_0(j5, 0L, j6, 2L) : jjMoveStringLiteralDfa16_0(j5, 9007199254740992L, j6, 32L);
        } catch (IOException unused) {
            jjStopStringLiteralDfa_0(14, 0L, j5, j6);
            return 15;
        }
    }

    private final int jjMoveStringLiteralDfa16_0(long j, long j2, long j3, long j4) {
        long j5 = j2 & j;
        long j6 = j4 & j3;
        if ((j5 | j6) == 0) {
            return jjStartNfa_0(14, 0L, j, j3);
        }
        try {
            char readChar = this.input_stream.readChar();
            this.curChar = readChar;
            return readChar != 'g' ? readChar != 'i' ? readChar != 's' ? jjStartNfa_0(15, 0L, j5, j6) : jjMoveStringLiteralDfa17_0(j5, 9007199254740992L, j6, 32L) : jjMoveStringLiteralDfa17_0(j5, 0L, j6, 8L) : jjMoveStringLiteralDfa17_0(j5, 0L, j6, 2L);
        } catch (IOException unused) {
            jjStopStringLiteralDfa_0(15, 0L, j5, j6);
            return 16;
        }
    }

    private final int jjMoveStringLiteralDfa17_0(long j, long j2, long j3, long j4) {
        long j5 = j2 & j;
        long j6 = j4 & j3;
        if ((j5 | j6) == 0) {
            return jjStartNfa_0(15, 0L, j, j3);
        }
        try {
            char readChar = this.input_stream.readChar();
            this.curChar = readChar;
            return readChar != 'g' ? readChar != 'h' ? (readChar == 'n' && (2 & j6) != 0) ? jjStopAtPos(17, 129) : jjStartNfa_0(16, 0L, j5, j6) : jjMoveStringLiteralDfa18_0(j5, 9007199254740992L, j6, 32L) : jjMoveStringLiteralDfa18_0(j5, 0L, j6, 8L);
        } catch (IOException unused) {
            jjStopStringLiteralDfa_0(16, 0L, j5, j6);
            return 17;
        }
    }

    private final int jjMoveStringLiteralDfa18_0(long j, long j2, long j3, long j4) {
        long j5 = j2 & j;
        long j6 = j4 & j3;
        if ((j5 | j6) == 0) {
            return jjStartNfa_0(16, 0L, j, j3);
        }
        try {
            char readChar = this.input_stream.readChar();
            this.curChar = readChar;
            return readChar != 'i' ? (readChar == 'n' && (8 & j6) != 0) ? jjStopAtPos(18, 131) : jjStartNfa_0(17, 0L, j5, j6) : jjMoveStringLiteralDfa19_0(j5, 9007199254740992L, j6, 32L);
        } catch (IOException unused) {
            jjStopStringLiteralDfa_0(17, 0L, j5, j6);
            return 18;
        }
    }

    private final int jjMoveStringLiteralDfa19_0(long j, long j2, long j3, long j4) {
        long j5 = j2 & j;
        long j6 = j4 & j3;
        if ((j5 | j6) == 0) {
            return jjStartNfa_0(17, 0L, j, j3);
        }
        try {
            char readChar = this.input_stream.readChar();
            this.curChar = readChar;
            return readChar != 'f' ? jjStartNfa_0(18, 0L, j5, j6) : jjMoveStringLiteralDfa20_0(j5, 9007199254740992L, j6, 32L);
        } catch (IOException unused) {
            jjStopStringLiteralDfa_0(18, 0L, j5, j6);
            return 19;
        }
    }

    private final int jjMoveStringLiteralDfa1_0(long j, long j2, long j3) {
        try {
            char readChar = this.input_stream.readChar();
            this.curChar = readChar;
            if (readChar != '&') {
                if (readChar != '+') {
                    if (readChar != '-') {
                        if (readChar == 'r') {
                            return jjMoveStringLiteralDfa2_0(j, 112616378963333120L, j2, 11258999068426240L, j3, 40L);
                        }
                        if (readChar != '|') {
                            if (readChar == 'a') {
                                return jjMoveStringLiteralDfa2_0(j, 4947869532160L, j2, 576460786663161856L, j3, 0L);
                            }
                            if (readChar == 'b') {
                                return jjMoveStringLiteralDfa2_0(j, PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID, j2, 43980465111040L, j3, 0L);
                            }
                            if (readChar == 't') {
                                return jjMoveStringLiteralDfa2_0(j, 844424930131968L, j2, 0L, j3, 0L);
                            }
                            if (readChar == 'u') {
                                return jjMoveStringLiteralDfa2_0(j, 37383395344384L, j2, 0L, j3, 0L);
                            }
                            switch (readChar) {
                                case '<':
                                    if ((j2 & 281474976710656L) != 0) {
                                        this.jjmatchedKind = 112;
                                        this.jjmatchedPos = 1;
                                    }
                                    return jjMoveStringLiteralDfa2_0(j, 0L, j2, 0L, j3, 1L);
                                case '=':
                                    if ((j2 & 67108864) != 0) {
                                        return jjStopAtPos(1, 90);
                                    }
                                    if ((j2 & 134217728) != 0) {
                                        return jjStopAtPos(1, 91);
                                    }
                                    if ((j2 & 536870912) != 0) {
                                        return jjStopAtPos(1, 93);
                                    }
                                    if ((j2 & 2147483648L) != 0) {
                                        return jjStopAtPos(1, 95);
                                    }
                                    if ((j2 & 18014398509481984L) != 0) {
                                        return jjStopAtPos(1, 118);
                                    }
                                    if ((j2 & 36028797018963968L) != 0) {
                                        return jjStopAtPos(1, 119);
                                    }
                                    if ((j2 & 72057594037927936L) != 0) {
                                        return jjStopAtPos(1, 120);
                                    }
                                    if ((j2 & 144115188075855872L) != 0) {
                                        return jjStopAtPos(1, 121);
                                    }
                                    if ((j2 & 288230376151711744L) != 0) {
                                        return jjStopAtPos(1, 122);
                                    }
                                    if ((j2 & 1152921504606846976L) != 0) {
                                        return jjStopAtPos(1, 124);
                                    }
                                    if ((j2 & 4611686018427387904L) != 0) {
                                        return jjStopAtPos(1, 126);
                                    }
                                    if ((j2 & Long.MIN_VALUE) != 0) {
                                        return jjStopAtPos(1, 127);
                                    }
                                    break;
                                case '>':
                                    if ((j2 & 1125899906842624L) != 0) {
                                        this.jjmatchedKind = 114;
                                        this.jjmatchedPos = 1;
                                    }
                                    return jjMoveStringLiteralDfa2_0(j, 0L, j2, 4503599627370496L, j3, 20L);
                                default:
                                    switch (readChar) {
                                        case 'e':
                                            return jjMoveStringLiteralDfa2_0(j, 71468256854016L, j2, 0L, j3, 0L);
                                        case 'f':
                                            if ((j & 4294967296L) != 0) {
                                                return jjStartNfaWithStates_0(1, 32, 35);
                                            }
                                            break;
                                        case 'g':
                                            return jjMoveStringLiteralDfa2_0(j, 0L, j2, 1074266112L, j3, 0L);
                                        case 'h':
                                            return jjMoveStringLiteralDfa2_0(j, 603623087556132864L, j2, 0L, j3, 0L);
                                        case 'i':
                                            return jjMoveStringLiteralDfa2_0(j, 402653184L, j2, 0L, j3, 0L);
                                        default:
                                            switch (readChar) {
                                                case 'l':
                                                    return jjMoveStringLiteralDfa2_0(j, 545267712L, j2, 562950223953920L, j3, 2L);
                                                case 'm':
                                                    return jjMoveStringLiteralDfa2_0(j, 25769803776L, j2, 0L, j3, 0L);
                                                case 'n':
                                                    return jjMoveStringLiteralDfa2_0(j, 240534945792L, j2, 0L, j3, 0L);
                                                case 'o':
                                                    if ((PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE & j) != 0) {
                                                        this.jjmatchedKind = 21;
                                                        this.jjmatchedPos = 1;
                                                    }
                                                    return jjMoveStringLiteralDfa2_0(j, 432345842331682816L, j2, 2305843017803628544L, j3, 0L);
                                                default:
                                                    switch (readChar) {
                                                        case 'w':
                                                            return jjMoveStringLiteralDfa2_0(j, 1125899906842624L, j2, 0L, j3, 0L);
                                                        case 'x':
                                                            return jjMoveStringLiteralDfa2_0(j, 33554432L, j2, 0L, j3, 0L);
                                                        case 'y':
                                                            return jjMoveStringLiteralDfa2_0(j, 2251799813701632L, j2, 0L, j3, 0L);
                                                    }
                                            }
                                    }
                            }
                        } else if ((j2 & 4294967296L) != 0) {
                            return jjStopAtPos(1, 96);
                        }
                    } else if ((j2 & 137438953472L) != 0) {
                        return jjStopAtPos(1, 101);
                    }
                } else if ((j2 & 68719476736L) != 0) {
                    return jjStopAtPos(1, 100);
                }
            } else if ((j2 & 17179869184L) != 0) {
                return jjStopAtPos(1, 98);
            }
            return jjStartNfa_0(0, j, j2, j3);
        } catch (IOException unused) {
            jjStopStringLiteralDfa_0(0, j, j2, j3);
            return 1;
        }
    }

    private final int jjMoveStringLiteralDfa20_0(long j, long j2, long j3, long j4) {
        long j5 = j2 & j;
        long j6 = j4 & j3;
        if ((j5 | j6) == 0) {
            return jjStartNfa_0(18, 0L, j, j3);
        }
        try {
            char readChar = this.input_stream.readChar();
            this.curChar = readChar;
            if (readChar != 't') {
                return jjStartNfa_0(19, 0L, j5, j6);
            }
            if ((9007199254740992L & j5) != 0) {
                this.jjmatchedKind = 117;
                this.jjmatchedPos = 20;
            }
            return jjMoveStringLiteralDfa21_0(j5, 0L, j6, 32L);
        } catch (IOException unused) {
            jjStopStringLiteralDfa_0(19, 0L, j5, j6);
            return 20;
        }
    }

    private final int jjMoveStringLiteralDfa21_0(long j, long j2, long j3, long j4) {
        long j5 = j4 & j3;
        if (((j2 & j) | j5) == 0) {
            return jjStartNfa_0(19, 0L, j, j3);
        }
        try {
            char readChar = this.input_stream.readChar();
            this.curChar = readChar;
            return readChar != '_' ? jjStartNfa_0(20, 0L, 0L, j5) : jjMoveStringLiteralDfa22_0(j5, 32L);
        } catch (IOException unused) {
            jjStopStringLiteralDfa_0(20, 0L, 0L, j5);
            return 21;
        }
    }

    private final int jjMoveStringLiteralDfa22_0(long j, long j2) {
        long j3 = j2 & j;
        if (j3 == 0) {
            return jjStartNfa_0(20, 0L, 0L, j);
        }
        try {
            char readChar = this.input_stream.readChar();
            this.curChar = readChar;
            return readChar != 'a' ? jjStartNfa_0(21, 0L, 0L, j3) : jjMoveStringLiteralDfa23_0(j3, 32L);
        } catch (IOException unused) {
            jjStopStringLiteralDfa_0(21, 0L, 0L, j3);
            return 22;
        }
    }

    private final int jjMoveStringLiteralDfa23_0(long j, long j2) {
        long j3 = j2 & j;
        if (j3 == 0) {
            return jjStartNfa_0(21, 0L, 0L, j);
        }
        try {
            char readChar = this.input_stream.readChar();
            this.curChar = readChar;
            return readChar != 's' ? jjStartNfa_0(22, 0L, 0L, j3) : jjMoveStringLiteralDfa24_0(j3, 32L);
        } catch (IOException unused) {
            jjStopStringLiteralDfa_0(22, 0L, 0L, j3);
            return 23;
        }
    }

    private final int jjMoveStringLiteralDfa24_0(long j, long j2) {
        long j3 = j2 & j;
        if (j3 == 0) {
            return jjStartNfa_0(22, 0L, 0L, j);
        }
        try {
            char readChar = this.input_stream.readChar();
            this.curChar = readChar;
            return readChar != 's' ? jjStartNfa_0(23, 0L, 0L, j3) : jjMoveStringLiteralDfa25_0(j3, 32L);
        } catch (IOException unused) {
            jjStopStringLiteralDfa_0(23, 0L, 0L, j3);
            return 24;
        }
    }

    private final int jjMoveStringLiteralDfa25_0(long j, long j2) {
        long j3 = j2 & j;
        if (j3 == 0) {
            return jjStartNfa_0(23, 0L, 0L, j);
        }
        try {
            char readChar = this.input_stream.readChar();
            this.curChar = readChar;
            return readChar != 'i' ? jjStartNfa_0(24, 0L, 0L, j3) : jjMoveStringLiteralDfa26_0(j3, 32L);
        } catch (IOException unused) {
            jjStopStringLiteralDfa_0(24, 0L, 0L, j3);
            return 25;
        }
    }

    private final int jjMoveStringLiteralDfa26_0(long j, long j2) {
        long j3 = j2 & j;
        if (j3 == 0) {
            return jjStartNfa_0(24, 0L, 0L, j);
        }
        try {
            char readChar = this.input_stream.readChar();
            this.curChar = readChar;
            return readChar != 'g' ? jjStartNfa_0(25, 0L, 0L, j3) : jjMoveStringLiteralDfa27_0(j3, 32L);
        } catch (IOException unused) {
            jjStopStringLiteralDfa_0(25, 0L, 0L, j3);
            return 26;
        }
    }

    private final int jjMoveStringLiteralDfa27_0(long j, long j2) {
        long j3 = j2 & j;
        if (j3 == 0) {
            return jjStartNfa_0(25, 0L, 0L, j);
        }
        try {
            char readChar = this.input_stream.readChar();
            this.curChar = readChar;
            return (readChar == 'n' && (32 & j3) != 0) ? jjStopAtPos(27, 133) : jjStartNfa_0(26, 0L, 0L, j3);
        } catch (IOException unused) {
            jjStopStringLiteralDfa_0(26, 0L, 0L, j3);
            return 27;
        }
    }

    private final int jjMoveStringLiteralDfa2_0(long j, long j2, long j3, long j4, long j5, long j6) {
        int i2;
        long j7 = j2 & j;
        long j8 = j4 & j3;
        long j9 = j6 & j5;
        if ((j7 | j8 | j9) == 0) {
            return jjStartNfa_0(0, j, j3, j5);
        }
        try {
            char readChar = this.input_stream.readChar();
            this.curChar = readChar;
            if (readChar != '=') {
                if (readChar == '>') {
                    if ((4503599627370496L & j8) != 0) {
                        this.jjmatchedKind = 116;
                        this.jjmatchedPos = 2;
                    }
                    return jjMoveStringLiteralDfa3_0(j7, 0L, j8, 0L, j9, 16L);
                }
                if (readChar == 'e') {
                    return jjMoveStringLiteralDfa3_0(j7, PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM, j8, 562949953421312L, j9, 2L);
                }
                if (readChar == 'f') {
                    return jjMoveStringLiteralDfa3_0(j7, PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED, j8, 0L, j9, 0L);
                }
                if (readChar == 'i') {
                    return jjMoveStringLiteralDfa3_0(j7, 721710636379144192L, j8, 11302979533537280L, j9, 40L);
                }
                if (readChar == 'l') {
                    return jjMoveStringLiteralDfa3_0(j7, 288232575242076160L, j8, 0L, j9, 0L);
                }
                if (readChar != 'w') {
                    if (readChar != 'y') {
                        switch (readChar) {
                            case 'a':
                                return jjMoveStringLiteralDfa3_0(j7, 4785074604220416L, j8, 0L, j9, 0L);
                            case 'b':
                                return jjMoveStringLiteralDfa3_0(j7, 35184372088832L, j8, 0L, j9, 0L);
                            case 'c':
                                return jjMoveStringLiteralDfa3_0(j7, 4398046511104L, j8, 0L, j9, 0L);
                            default:
                                switch (readChar) {
                                    case 'n':
                                        return jjMoveStringLiteralDfa3_0(j7, 2252075095031808L, j8, 576460786663161856L, j9, 0L);
                                    case 'o':
                                        return jjMoveStringLiteralDfa3_0(j7, 158330211272704L, j8, 0L, j9, 0L);
                                    case 'p':
                                        return jjMoveStringLiteralDfa3_0(j7, 25769803776L, j8, 0L, j9, 0L);
                                    default:
                                        switch (readChar) {
                                            case 'r':
                                                if ((1073741824 & j7) != 0) {
                                                    return jjStartNfaWithStates_0(2, 30, 35);
                                                }
                                                if ((8589934592L & j8) != 0) {
                                                    this.jjmatchedKind = 97;
                                                    this.jjmatchedPos = 2;
                                                }
                                                return jjMoveStringLiteralDfa3_0(j7, 27584547717644288L, j8, 2305843009213693952L, j9, 0L);
                                            case 's':
                                                return jjMoveStringLiteralDfa3_0(j7, 34368160768L, j8, 0L, j9, 0L);
                                            case 't':
                                                if ((68719476736L & j7) != 0) {
                                                    i2 = 36;
                                                } else {
                                                    if ((PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED & j8) == 0) {
                                                        if ((PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE & j8) != 0) {
                                                            i2 = 85;
                                                        }
                                                        return jjMoveStringLiteralDfa3_0(j7, 71058120065024L, j8, 1342177280L, j9, 0L);
                                                    }
                                                    i2 = 83;
                                                }
                                                this.jjmatchedKind = i2;
                                                this.jjmatchedPos = 2;
                                                return jjMoveStringLiteralDfa3_0(j7, 71058120065024L, j8, 1342177280L, j9, 0L);
                                            case 'u':
                                                return jjMoveStringLiteralDfa3_0(j7, 36028797039935488L, j8, 0L, j9, 0L);
                                        }
                                }
                        }
                    }
                    if ((72057594037927936L & j7) != 0) {
                        return jjStartNfaWithStates_0(2, 56, 35);
                    }
                } else if ((1099511627776L & j7) != 0) {
                    return jjStartNfaWithStates_0(2, 40, 35);
                }
            } else {
                if ((1 & j9) != 0) {
                    return jjStopAtPos(2, 128);
                }
                if ((4 & j9) != 0) {
                    return jjStopAtPos(2, 130);
                }
            }
            return jjStartNfa_0(1, j7, j8, j9);
        } catch (IOException unused) {
            jjStopStringLiteralDfa_0(1, j7, j8, j9);
            return 2;
        }
    }

    private final int jjMoveStringLiteralDfa3_0(long j, long j2, long j3, long j4, long j5, long j6) {
        long j7 = j2 & j;
        long j8 = j4 & j3;
        long j9 = j6 & j5;
        if ((j7 | j8 | j9) == 0) {
            return jjStartNfa_0(1, j, j3, j5);
        }
        try {
            char readChar = this.input_stream.readChar();
            this.curChar = readChar;
            if (readChar != '=') {
                if (readChar == '_') {
                    return jjMoveStringLiteralDfa4_0(j7, 0L, j8, 2305843009213693952L, j9, 0L);
                }
                if (readChar == 'i') {
                    return jjMoveStringLiteralDfa4_0(j7, 563499709235200L, j8, 0L, j9, 0L);
                }
                switch (readChar) {
                    case 'a':
                        return jjMoveStringLiteralDfa4_0(j7, 288230377092288512L, j8, 0L, j9, 0L);
                    case 'b':
                        return jjMoveStringLiteralDfa4_0(j7, 4194304L, j8, 0L, j9, 0L);
                    case 'c':
                        return jjMoveStringLiteralDfa4_0(j7, 2251799813750784L, j8, 0L, j9, 0L);
                    case 'd':
                        if ((144115188075855872L & j7) != 0) {
                            return jjStartNfaWithStates_0(3, 57, 35);
                        }
                        if ((34359738368L & j8) != 0) {
                            this.jjmatchedKind = 99;
                            this.jjmatchedPos = 3;
                        }
                        return jjMoveStringLiteralDfa4_0(j7, 0L, j8, 576460752303423488L, j9, 0L);
                    case 'e':
                        return (PlaybackStateCompat.ACTION_PREPARE & j7) != 0 ? jjStartNfaWithStates_0(3, 14, 35) : (PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID & j7) != 0 ? jjStartNfaWithStates_0(3, 15, 35) : (8388608 & j7) != 0 ? jjStartNfaWithStates_0(3, 23, 35) : (36028797018963968L & j7) != 0 ? jjStartNfaWithStates_0(3, 55, 35) : jjMoveStringLiteralDfa4_0(j7, 137472507904L, j8, 1342177280L, j9, 0L);
                    case 'f':
                        return jjMoveStringLiteralDfa4_0(j7, 0L, j8, 562949953421312L, j9, 2L);
                    case 'g':
                        return (274877906944L & j7) != 0 ? jjStartNfaWithStates_0(3, 38, 35) : jjMoveStringLiteralDfa4_0(j7, 0L, j8, 11258999068426240L, j9, 40L);
                    default:
                        switch (readChar) {
                            case 'k':
                                return jjMoveStringLiteralDfa4_0(j7, 4398046511104L, j8, 0L, j9, 0L);
                            case 'l':
                                return (2199023255552L & j7) != 0 ? jjStartNfaWithStates_0(3, 41, 35) : jjMoveStringLiteralDfa4_0(j7, 576495945265448960L, j8, 0L, j9, 0L);
                            case 'm':
                                if ((16777216 & j7) != 0) {
                                    return jjStartNfaWithStates_0(3, 24, 35);
                                }
                                break;
                            case 'n':
                                return jjMoveStringLiteralDfa4_0(j7, 4503599627370496L, j8, 0L, j9, 0L);
                            case 'o':
                                return (2147483648L & j7) != 0 ? jjStartNfaWithStates_0(3, 31, 35) : jjMoveStringLiteralDfa4_0(j7, 27021614944092160L, j8, 0L, j9, 0L);
                            default:
                                switch (readChar) {
                                    case 'r':
                                        return (PlaybackStateCompat.ACTION_PREPARE_FROM_URI & j7) != 0 ? jjStartNfaWithStates_0(3, 17, 35) : jjMoveStringLiteralDfa4_0(j7, 140737488355328L, j8, 0L, j9, 0L);
                                    case 's':
                                        return jjMoveStringLiteralDfa4_0(j7, 67379200L, j8, 0L, j9, 0L);
                                    case 't':
                                        return jjMoveStringLiteralDfa4_0(j7, 1425001429861376L, j8, 43980465111040L, j9, 0L);
                                    case 'u':
                                        return jjMoveStringLiteralDfa4_0(j7, 70368744177664L, j8, 0L, j9, 0L);
                                    case 'v':
                                        return jjMoveStringLiteralDfa4_0(j7, 8796093022208L, j8, 0L, j9, 0L);
                                }
                        }
                }
            } else if ((16 & j9) != 0) {
                return jjStopAtPos(3, 132);
            }
            return jjStartNfa_0(2, j7, j8, j9);
        } catch (IOException unused) {
            jjStopStringLiteralDfa_0(2, j7, j8, j9);
            return 3;
        }
    }

    private final int jjMoveStringLiteralDfa4_0(long j, long j2, long j3, long j4, long j5, long j6) {
        long j7 = j2 & j;
        long j8 = j4 & j3;
        long j9 = j6 & j5;
        if ((j7 | j8 | j9) == 0) {
            return jjStartNfa_0(2, j, j3, j5);
        }
        try {
            char readChar = this.input_stream.readChar();
            this.curChar = readChar;
            switch (readChar) {
                case '_':
                    return jjMoveStringLiteralDfa5_0(j7, 0L, j8, 576460752303423488L, j9, 0L);
                case 'a':
                    return jjMoveStringLiteralDfa5_0(j7, 13228499271680L, j8, 2305843009213693952L, j9, 0L);
                case 'c':
                    return jjMoveStringLiteralDfa5_0(j7, 1688849860263936L, j8, 0L, j9, 0L);
                case 'e':
                    return (67108864 & j7) != 0 ? jjStartNfaWithStates_0(4, 26, 35) : (576460752303423488L & j7) != 0 ? jjStartNfaWithStates_0(4, 59, 35) : jjMoveStringLiteralDfa5_0(j7, 17600775981056L, j8, 0L, j9, 0L);
                case 'h':
                    return (PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH & j7) != 0 ? jjStartNfaWithStates_0(4, 16, 35) : jjMoveStringLiteralDfa5_0(j7, 2251799813685248L, j8, 11258999068426240L, j9, 40L);
                case 'i':
                    return jjMoveStringLiteralDfa5_0(j7, 316659349323776L, j8, 0L, j9, 0L);
                case 'k':
                    if ((PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM & j7) != 0) {
                        return jjStartNfaWithStates_0(4, 12, 35);
                    }
                    break;
                case 'l':
                    if ((134217728 & j7) != 0) {
                        this.jjmatchedKind = 27;
                        this.jjmatchedPos = 4;
                    }
                    return jjMoveStringLiteralDfa5_0(j7, 272629760L, j8, 0L, j9, 0L);
                case 'n':
                    return jjMoveStringLiteralDfa5_0(j7, 33554432L, j8, 0L, j9, 0L);
                case 'q':
                    if ((268435456 & j8) != 0) {
                        return jjStopAtPos(4, 92);
                    }
                    if ((1073741824 & j8) != 0) {
                        return jjStopAtPos(4, 94);
                    }
                    break;
                case 'r':
                    return jjMoveStringLiteralDfa5_0(j7, 70523363001344L, j8, 0L, j9, 0L);
                case 's':
                    return (PlaybackStateCompat.ACTION_PLAY_FROM_URI & j7) != 0 ? jjStartNfaWithStates_0(4, 13, 35) : jjMoveStringLiteralDfa5_0(j7, 4503599627370496L, j8, 0L, j9, 0L);
                case 't':
                    return (PlaybackStateCompat.ACTION_SET_REPEAT_MODE & j7) != 0 ? jjStartNfaWithStates_0(4, 18, 35) : (536870912 & j7) != 0 ? jjStartNfaWithStates_0(4, 29, 35) : (140737488355328L & j7) != 0 ? jjStartNfaWithStates_0(4, 47, 35) : jjMoveStringLiteralDfa5_0(j7, 288230376151711744L, j8, 562949953421312L, j9, 2L);
                case 'u':
                    return jjMoveStringLiteralDfa5_0(j7, PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED, j8, 0L, j9, 0L);
                case 'v':
                    return jjMoveStringLiteralDfa5_0(j7, 549755813888L, j8, 0L, j9, 0L);
                case 'w':
                    if ((9007199254740992L & j7) != 0) {
                        this.jjmatchedKind = 53;
                        this.jjmatchedPos = 4;
                    }
                    return jjMoveStringLiteralDfa5_0(j7, 18014398509481984L, j8, 43980465111040L, j9, 0L);
            }
            return jjStartNfa_0(3, j7, j8, j9);
        } catch (IOException unused) {
            jjStopStringLiteralDfa_0(3, j7, j8, j9);
            return 4;
        }
    }

    private final int jjMoveStringLiteralDfa5_0(long j, long j2, long j3, long j4, long j5, long j6) {
        long j7 = j2 & j;
        long j8 = j4 & j3;
        long j9 = j6 & j5;
        if ((j7 | j8 | j9) == 0) {
            return jjStartNfa_0(3, j, j3, j5);
        }
        try {
            char readChar = this.input_stream.readChar();
            this.curChar = readChar;
            switch (readChar) {
                case '_':
                    return jjMoveStringLiteralDfa6_0(j7, 0L, j8, 562949953421312L, j9, 2L);
                case 'a':
                    return jjMoveStringLiteralDfa6_0(j7, 3072L, j8, 576460752303423488L, j9, 0L);
                case 'c':
                    return (35184372088832L & j7) != 0 ? jjStartNfaWithStates_0(5, 45, 35) : (281474976710656L & j7) != 0 ? jjStartNfaWithStates_0(5, 48, 35) : jjMoveStringLiteralDfa6_0(j7, 17592186044416L, j8, 0L, j9, 0L);
                case 'd':
                    return jjMoveStringLiteralDfa6_0(j7, 33554432L, j8, 0L, j9, 0L);
                case 'e':
                    if ((4194304 & j7) != 0) {
                        return jjStartNfaWithStates_0(5, 22, 35);
                    }
                    if ((549755813888L & j7) != 0) {
                        return jjStartNfaWithStates_0(5, 39, 35);
                    }
                    break;
                case 'f':
                    return jjMoveStringLiteralDfa6_0(j7, 137438953472L, j8, 0L, j9, 0L);
                case 'g':
                    return jjMoveStringLiteralDfa6_0(j7, 4398046511104L, j8, 0L, j9, 0L);
                case 'h':
                    if ((1125899906842624L & j7) != 0) {
                        return jjStartNfaWithStates_0(5, 50, 35);
                    }
                    break;
                case 'i':
                    return jjMoveStringLiteralDfa6_0(j7, 292733975779082240L, j8, 43980465111040L, j9, 0L);
                case 'l':
                    return jjMoveStringLiteralDfa6_0(j7, 269484032L, j8, 0L, j9, 0L);
                case 'm':
                    return jjMoveStringLiteralDfa6_0(j7, 8589934592L, j8, 0L, j9, 0L);
                case 'n':
                    return (70368744177664L & j7) != 0 ? jjStartNfaWithStates_0(5, 46, 35) : jjMoveStringLiteralDfa6_0(j7, 34360262656L, j8, 0L, j9, 0L);
                case 'r':
                    return jjMoveStringLiteralDfa6_0(j7, 2251799813685248L, j8, 0L, j9, 0L);
                case 's':
                    return (18014398509481984L & j7) != 0 ? jjStartNfaWithStates_0(5, 54, 35) : jjMoveStringLiteralDfa6_0(j7, 0L, j8, 2305843009213693952L, j9, 0L);
                case 't':
                    return (17179869184L & j7) != 0 ? jjStartNfaWithStates_0(5, 34, 35) : jjMoveStringLiteralDfa6_0(j7, 571746046443520L, j8, 11258999068426240L, j9, 40L);
            }
            return jjStartNfa_0(4, j7, j8, j9);
        } catch (IOException unused) {
            jjStopStringLiteralDfa_0(4, j7, j8, j9);
            return 5;
        }
    }

    private final int jjMoveStringLiteralDfa6_0(long j, long j2, long j3, long j4, long j5, long j6) {
        long j7 = j2 & j;
        long j8 = j4 & j3;
        long j9 = j6 & j5;
        if ((j7 | j8 | j9) == 0) {
            return jjStartNfa_0(4, j, j3, j5);
        }
        try {
            char readChar = this.input_stream.readChar();
            this.curChar = readChar;
            if (readChar == '_') {
                return jjMoveStringLiteralDfa7_0(j7, 0L, j8, 11258999068426240L, j9, 40L);
            }
            if (readChar == 'a') {
                return jjMoveStringLiteralDfa7_0(j7, 137438953472L, j8, 0L, j9, 0L);
            }
            if (readChar == 'c') {
                return jjMoveStringLiteralDfa7_0(j7, 34359739392L, j8, 0L, j9, 0L);
            }
            if (readChar == 'l') {
                return jjMoveStringLiteralDfa7_0(j7, 288230376151711744L, j8, 0L, j9, 0L);
            }
            if (readChar != 'y') {
                if (readChar == 'e') {
                    return (4398046511104L & j7) != 0 ? jjStartNfaWithStates_0(6, 42, 35) : (8796093022208L & j7) != 0 ? jjStartNfaWithStates_0(6, 43, 35) : jjMoveStringLiteralDfa7_0(j7, 4503608217305088L, j8, 0L, j9, 0L);
                }
                if (readChar == 'f') {
                    return jjMoveStringLiteralDfa7_0(j7, 562949953421312L, j8, 0L, j9, 0L);
                }
                if (readChar != 'n') {
                    if (readChar == 'o') {
                        return jjMoveStringLiteralDfa7_0(j7, 2251799813685248L, j8, 0L, j9, 0L);
                    }
                    switch (readChar) {
                        case 's':
                            return (33554432 & j7) != 0 ? jjStartNfaWithStates_0(6, 25, 35) : jjMoveStringLiteralDfa7_0(j7, 0L, j8, 2882910691935649792L, j9, 2L);
                        case 't':
                            return (PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED & j7) != 0 ? jjStartNfaWithStates_0(6, 20, 35) : jjMoveStringLiteralDfa7_0(j7, 17592186044416L, j8, 0L, j9, 0L);
                        case 'u':
                            return jjMoveStringLiteralDfa7_0(j7, PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED, j8, 0L, j9, 0L);
                    }
                }
                if ((PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH & j7) != 0) {
                    return jjStartNfaWithStates_0(6, 11, 35);
                }
            } else if ((268435456 & j7) != 0) {
                return jjStartNfaWithStates_0(6, 28, 35);
            }
            return jjStartNfa_0(5, j7, j8, j9);
        } catch (IOException unused) {
            jjStopStringLiteralDfa_0(5, j7, j8, j9);
            return 6;
        }
    }

    private final int jjMoveStringLiteralDfa7_0(long j, long j2, long j3, long j4, long j5, long j6) {
        long j7 = j2 & j;
        long j8 = j4 & j3;
        long j9 = j6 & j5;
        if ((j7 | j8 | j9) == 0) {
            return jjStartNfa_0(5, j, j3, j5);
        }
        try {
            char readChar = this.input_stream.readChar();
            this.curChar = readChar;
            if (readChar == 'c') {
                return jjMoveStringLiteralDfa8_0(j7, 137438953472L, j8, 0L, j9, 0L);
            }
            if (readChar == 'e') {
                return (PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED & j7) != 0 ? jjStartNfaWithStates_0(7, 19, 35) : (288230376151711744L & j7) != 0 ? jjStartNfaWithStates_0(7, 58, 35) : jjMoveStringLiteralDfa8_0(j7, 17626545782784L, j8, 43980465111040L, j9, 0L);
            }
            if (readChar == 'n') {
                return jjMoveStringLiteralDfa8_0(j7, 6755408030990336L, j8, 0L, j9, 0L);
            }
            if (readChar != 'p') {
                if (readChar == 'h') {
                    return jjMoveStringLiteralDfa8_0(j7, 0L, j8, 562949953421312L, j9, 2L);
                }
                if (readChar == 'i') {
                    return jjMoveStringLiteralDfa8_0(j7, 0L, j8, 2305843009213693952L, j9, 0L);
                }
                switch (readChar) {
                    case 's':
                        return jjMoveStringLiteralDfa8_0(j7, 0L, j8, 578712552117108736L, j9, 8L);
                    case 't':
                        if ((PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID & j7) != 0) {
                            return jjStartNfaWithStates_0(7, 10, 35);
                        }
                        break;
                    case 'u':
                        return jjMoveStringLiteralDfa8_0(j7, 0L, j8, 9007199254740992L, j9, 32L);
                }
            } else if ((562949953421312L & j7) != 0) {
                return jjStartNfaWithStates_0(7, 49, 35);
            }
            return jjStartNfa_0(6, j7, j8, j9);
        } catch (IOException unused) {
            jjStopStringLiteralDfa_0(6, j7, j8, j9);
            return 7;
        }
    }

    private final int jjMoveStringLiteralDfa8_0(long j, long j2, long j3, long j4, long j5, long j6) {
        long j7 = j2 & j;
        long j8 = j4 & j3;
        long j9 = j6 & j5;
        if ((j7 | j8 | j9) == 0) {
            return jjStartNfa_0(6, j, j3, j5);
        }
        try {
            char readChar = this.input_stream.readChar();
            this.curChar = readChar;
            if (readChar == '_') {
                return jjMoveStringLiteralDfa9_0(j7, 0L, j8, 43980465111040L, j9, 0L);
            }
            if (readChar == 't') {
                return (4503599627370496L & j7) != 0 ? jjStartNfaWithStates_0(8, 52, 35) : jjMoveStringLiteralDfa9_0(j7, 8589934592L, j8, 0L, j9, 0L);
            }
            if (readChar != 'd') {
                if (readChar != 'e') {
                    if (readChar == 'n') {
                        return jjMoveStringLiteralDfa9_0(j7, 0L, j8, 9007199254740992L, j9, 32L);
                    }
                    if (readChar == 'o') {
                        return jjMoveStringLiteralDfa9_0(j7, 34359738368L, j8, 0L, j9, 0L);
                    }
                    switch (readChar) {
                        case 'g':
                            return jjMoveStringLiteralDfa9_0(j7, 0L, j8, 2305843009213693952L, j9, 0L);
                        case 'h':
                            return jjMoveStringLiteralDfa9_0(j7, 0L, j8, 2251799813685248L, j9, 8L);
                        case 'i':
                            return jjMoveStringLiteralDfa9_0(j7, 2251799813685248L, j8, 577023702256844800L, j9, 2L);
                    }
                }
                if ((137438953472L & j7) != 0) {
                    return jjStartNfaWithStates_0(8, 37, 35);
                }
            } else if ((17592186044416L & j7) != 0) {
                return jjStartNfaWithStates_0(8, 44, 35);
            }
            return jjStartNfa_0(7, j7, j8, j9);
        } catch (IOException unused) {
            jjStopStringLiteralDfa_0(7, j7, j8, j9);
            return 8;
        }
    }

    private final int jjMoveStringLiteralDfa9_0(long j, long j2, long j3, long j4, long j5, long j6) {
        long j7 = j2 & j;
        long j8 = j4 & j3;
        long j9 = j6 & j5;
        if ((j7 | j8 | j9) == 0) {
            return jjStartNfa_0(7, j, j3, j5);
        }
        try {
            char readChar = this.input_stream.readChar();
            this.curChar = readChar;
            if (readChar == 'a') {
                return jjMoveStringLiteralDfa10_0(j7, 0L, j8, 8796093022208L, j9, 0L);
            }
            if (readChar == 'i') {
                return jjMoveStringLiteralDfa10_0(j7, 0L, j8, 2251799813685248L, j9, 8L);
            }
            if (readChar == 's') {
                return (8589934592L & j7) != 0 ? jjStartNfaWithStates_0(9, 33, 35) : jjMoveStringLiteralDfa10_0(j7, 0L, j8, 9007199254740992L, j9, 32L);
            }
            if (readChar == 'z') {
                return jjMoveStringLiteralDfa10_0(j7, 2251799813685248L, j8, 0L, j9, 0L);
            }
            if (readChar == 'f') {
                return (34359738368L & j7) != 0 ? jjStartNfaWithStates_0(9, 35, 35) : jjMoveStringLiteralDfa10_0(j7, 0L, j8, 562949953421312L, j9, 2L);
            }
            if (readChar == 'g') {
                return jjMoveStringLiteralDfa10_0(j7, 0L, j8, 576460752303423488L, j9, 0L);
            }
            if (readChar != 'n') {
                if (readChar == 'o') {
                    return jjMoveStringLiteralDfa10_0(j7, 0L, j8, 35184372088832L, j9, 0L);
                }
            } else if ((2305843009213693952L & j8) != 0) {
                return jjStopAtPos(9, 125);
            }
            return jjStartNfa_0(8, j7, j8, j9);
        } catch (IOException unused) {
            jjStopStringLiteralDfa_0(8, j7, j8, j9);
            return 9;
        }
    }

    private final int jjStartNfaWithStates_0(int i2, int i3, int i4) {
        this.jjmatchedKind = i3;
        this.jjmatchedPos = i2;
        try {
            this.curChar = this.input_stream.readChar();
            return jjMoveNfa_0(i4, i2 + 1);
        } catch (IOException unused) {
            return i2 + 1;
        }
    }

    private final int jjStartNfa_0(int i2, long j, long j2, long j3) {
        return jjMoveNfa_0(jjStopStringLiteralDfa_0(i2, j, j2, j3), i2 + 1);
    }

    private final int jjStopAtPos(int i2, int i3) {
        this.jjmatchedKind = i3;
        this.jjmatchedPos = i2;
        return i2 + 1;
    }

    private final int jjStopStringLiteralDfa_0(int i2, long j, long j2, long j3) {
        int i3;
        switch (i2) {
            case 0:
                if ((144117387099111424L & j2) != 0) {
                    return 56;
                }
                if ((62 & j) != 0) {
                    return 0;
                }
                if ((j2 & PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH) != 0) {
                    return 11;
                }
                if ((j & 1152921504606845952L) == 0) {
                    return -1;
                }
                this.jjmatchedKind = 69;
                return 35;
            case 1:
                if ((4301258752L & j) != 0) {
                    return 35;
                }
                if ((j & 1152921500305587200L) == 0) {
                    return -1;
                }
                if (this.jjmatchedPos != 1) {
                    this.jjmatchedKind = 69;
                    this.jjmatchedPos = 1;
                }
                return 35;
            case 2:
                if ((1080862599528053760L & j) == 0) {
                    return (j & 72058900781727744L) != 0 ? 35 : -1;
                }
                if (this.jjmatchedPos != 2) {
                    this.jjmatchedKind = 69;
                    this.jjmatchedPos = 2;
                }
                return 35;
            case 3:
                if ((900716275798195200L & j) == 0) {
                    return (j & 180146461168812032L) != 0 ? 35 : -1;
                }
                if (this.jjmatchedPos != 3) {
                    this.jjmatchedKind = 69;
                    this.jjmatchedPos = 3;
                }
                return 35;
            case 4:
                if ((603623088562974720L & j) != 0) {
                    return 35;
                }
                if ((j & 297093187235220480L) == 0) {
                    return -1;
                }
                if (this.jjmatchedPos != 4) {
                    this.jjmatchedKind = 69;
                    this.jjmatchedPos = 4;
                }
                return 35;
            case 5:
                if ((295579692563958784L & j) == 0) {
                    return (j & 19527893449179136L) != 0 ? 35 : -1;
                }
                this.jjmatchedKind = 69;
                i3 = 5;
                break;
            case 6:
                if ((295566498121384960L & j) == 0) {
                    return (j & 13194442573824L) != 0 ? 35 : -1;
                }
                this.jjmatchedKind = 69;
                i3 = 6;
                break;
            case 7:
                if ((288793326105658368L & j) != 0) {
                    return 35;
                }
                if ((j & 6773172015726592L) == 0) {
                    return -1;
                }
                this.jjmatchedKind = 69;
                i3 = 7;
                break;
            case 8:
                if ((2251842763358208L & j) == 0) {
                    return (j & 4521329252368384L) != 0 ? 35 : -1;
                }
                this.jjmatchedKind = 69;
                i3 = 8;
                break;
            case 9:
                if ((j & 2251799813685248L) == 0) {
                    return (j & 42949672960L) != 0 ? 35 : -1;
                }
                this.jjmatchedKind = 69;
                i3 = 9;
                break;
            case 10:
                if ((j & 2251799813685248L) == 0) {
                    return -1;
                }
                if (this.jjmatchedPos != 10) {
                    this.jjmatchedKind = 69;
                    this.jjmatchedPos = 10;
                }
                return 35;
            case 11:
                return (j & 2251799813685248L) != 0 ? 35 : -1;
            default:
                return -1;
        }
        this.jjmatchedPos = i3;
        return 35;
    }

    public void ReInit(JavaCharStream javaCharStream) {
        this.jjnewStateCnt = 0;
        this.jjmatchedPos = 0;
        this.curLexState = this.defaultLexState;
        this.input_stream = javaCharStream;
        ReInitRounds();
    }

    public void ReInit(JavaCharStream javaCharStream, int i2) {
        ReInit(javaCharStream);
        SwitchTo(i2);
    }

    public void SwitchTo(int i2) {
        if (i2 < 1 && i2 >= 0) {
            this.curLexState = i2;
            return;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Error: Ignoring invalid lexical state : ");
        stringBuffer.append(i2);
        stringBuffer.append(". State unchanged.");
        throw new TokenMgrError(stringBuffer.toString(), 2);
    }

    public Token getNextToken() {
        int i2;
        int i3;
        boolean z;
        String str;
        String str2 = null;
        Token token = null;
        while (true) {
            int i4 = 0;
            try {
                this.curChar = this.input_stream.BeginToken();
                this.jjmatchedKind = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
                this.jjmatchedPos = 0;
                int jjMoveStringLiteralDfa0_0 = jjMoveStringLiteralDfa0_0();
                if (this.jjmatchedKind == Integer.MAX_VALUE) {
                    int endLine = this.input_stream.getEndLine();
                    int endColumn = this.input_stream.getEndColumn();
                    try {
                        this.input_stream.readChar();
                        this.input_stream.backup(1);
                        i2 = endLine;
                        i3 = endColumn;
                        z = false;
                    } catch (IOException unused) {
                        str2 = jjMoveStringLiteralDfa0_0 <= 1 ? "" : this.input_stream.GetImage();
                        char c2 = this.curChar;
                        if (c2 == '\n' || c2 == '\r') {
                            endLine++;
                        } else {
                            i4 = endColumn + 1;
                        }
                        i2 = endLine;
                        i3 = i4;
                        z = true;
                    }
                    if (z) {
                        str = str2;
                    } else {
                        this.input_stream.backup(1);
                        str = jjMoveStringLiteralDfa0_0 > 1 ? this.input_stream.GetImage() : "";
                    }
                    throw new TokenMgrError(z, this.curLexState, i2, i3, str, this.curChar, 0);
                }
                int i5 = this.jjmatchedPos;
                if (i5 + 1 < jjMoveStringLiteralDfa0_0) {
                    this.input_stream.backup((jjMoveStringLiteralDfa0_0 - i5) - 1);
                }
                long[] jArr = jjtoToken;
                int i6 = this.jjmatchedKind;
                if ((jArr[i6 >> 6] & (1 << (i6 & 63))) != 0) {
                    break;
                }
                if (((1 << (i6 & 63)) & jjtoSpecial[i6 >> 6]) != 0) {
                    Token jjFillToken = jjFillToken();
                    if (token != null) {
                        jjFillToken.specialToken = token;
                        token.next = jjFillToken;
                    }
                    token = jjFillToken;
                }
            } catch (IOException unused2) {
                this.jjmatchedKind = 0;
            }
        }
        Token jjFillToken2 = jjFillToken();
        jjFillToken2.specialToken = token;
        return jjFillToken2;
    }

    protected Token jjFillToken() {
        Token newToken = Token.newToken(this.jjmatchedKind);
        int i2 = this.jjmatchedKind;
        newToken.kind = i2;
        String str = jjstrLiteralImages[i2];
        if (str == null) {
            str = this.input_stream.GetImage();
        }
        newToken.image = str;
        newToken.beginLine = this.input_stream.getBeginLine();
        newToken.beginColumn = this.input_stream.getBeginColumn();
        newToken.endLine = this.input_stream.getEndLine();
        newToken.endColumn = this.input_stream.getEndColumn();
        return newToken;
    }

    public void setDebugStream(PrintStream printStream) {
        this.debugStream = printStream;
    }
}
