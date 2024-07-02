package bsh;

import android.support.v7.widget.ActivityChooserView;
import java.io.FileReader;
import java.io.InputStream;
import java.io.Reader;

/* loaded from: classes.dex */
public class Parser implements ParserTreeConstants, ParserConstants {
    JavaCharStream jj_input_stream;
    private int jj_la;
    private Token jj_lastpos;
    public Token jj_nt;
    private Token jj_scanpos;
    private boolean jj_semLA;
    public ParserTokenManager token_source;
    protected JJTParserState jjtree = new JJTParserState();
    boolean retainComments = false;
    public boolean lookingAhead = false;
    private final LookaheadSuccess jj_ls = new LookaheadSuccess(null);
    public Token token = new Token();
    private int jj_ntk = -1;

    /* renamed from: bsh.Parser$1, reason: invalid class name */
    /* loaded from: classes.dex */
    class AnonymousClass1 {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class LookaheadSuccess extends Error {
        private LookaheadSuccess() {
        }

        /* synthetic */ LookaheadSuccess(AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    public Parser(ParserTokenManager parserTokenManager) {
        this.token_source = parserTokenManager;
    }

    public Parser(InputStream inputStream) {
        this.jj_input_stream = new JavaCharStream(inputStream, 1, 1);
        this.token_source = new ParserTokenManager(this.jj_input_stream);
    }

    public Parser(Reader reader) {
        this.jj_input_stream = new JavaCharStream(reader, 1, 1);
        this.token_source = new ParserTokenManager(this.jj_input_stream);
    }

    private final boolean jj_2_1(int i2) {
        this.jj_la = i2;
        Token token = this.token;
        this.jj_scanpos = token;
        this.jj_lastpos = token;
        try {
            return true ^ jj_3_1();
        } catch (LookaheadSuccess unused) {
            return true;
        }
    }

    private final boolean jj_2_10(int i2) {
        this.jj_la = i2;
        Token token = this.token;
        this.jj_scanpos = token;
        this.jj_lastpos = token;
        try {
            return true ^ jj_3_10();
        } catch (LookaheadSuccess unused) {
            return true;
        }
    }

    private final boolean jj_2_11(int i2) {
        this.jj_la = i2;
        Token token = this.token;
        this.jj_scanpos = token;
        this.jj_lastpos = token;
        try {
            return true ^ jj_3_11();
        } catch (LookaheadSuccess unused) {
            return true;
        }
    }

    private final boolean jj_2_12(int i2) {
        this.jj_la = i2;
        Token token = this.token;
        this.jj_scanpos = token;
        this.jj_lastpos = token;
        try {
            return true ^ jj_3_12();
        } catch (LookaheadSuccess unused) {
            return true;
        }
    }

    private final boolean jj_2_13(int i2) {
        this.jj_la = i2;
        Token token = this.token;
        this.jj_scanpos = token;
        this.jj_lastpos = token;
        try {
            return true ^ jj_3_13();
        } catch (LookaheadSuccess unused) {
            return true;
        }
    }

    private final boolean jj_2_14(int i2) {
        this.jj_la = i2;
        Token token = this.token;
        this.jj_scanpos = token;
        this.jj_lastpos = token;
        try {
            return true ^ jj_3_14();
        } catch (LookaheadSuccess unused) {
            return true;
        }
    }

    private final boolean jj_2_15(int i2) {
        this.jj_la = i2;
        Token token = this.token;
        this.jj_scanpos = token;
        this.jj_lastpos = token;
        try {
            return true ^ jj_3_15();
        } catch (LookaheadSuccess unused) {
            return true;
        }
    }

    private final boolean jj_2_16(int i2) {
        this.jj_la = i2;
        Token token = this.token;
        this.jj_scanpos = token;
        this.jj_lastpos = token;
        try {
            return true ^ jj_3_16();
        } catch (LookaheadSuccess unused) {
            return true;
        }
    }

    private final boolean jj_2_17(int i2) {
        this.jj_la = i2;
        Token token = this.token;
        this.jj_scanpos = token;
        this.jj_lastpos = token;
        try {
            return true ^ jj_3_17();
        } catch (LookaheadSuccess unused) {
            return true;
        }
    }

    private final boolean jj_2_18(int i2) {
        this.jj_la = i2;
        Token token = this.token;
        this.jj_scanpos = token;
        this.jj_lastpos = token;
        try {
            return true ^ jj_3_18();
        } catch (LookaheadSuccess unused) {
            return true;
        }
    }

    private final boolean jj_2_19(int i2) {
        this.jj_la = i2;
        Token token = this.token;
        this.jj_scanpos = token;
        this.jj_lastpos = token;
        try {
            return true ^ jj_3_19();
        } catch (LookaheadSuccess unused) {
            return true;
        }
    }

    private final boolean jj_2_2(int i2) {
        this.jj_la = i2;
        Token token = this.token;
        this.jj_scanpos = token;
        this.jj_lastpos = token;
        try {
            return true ^ jj_3_2();
        } catch (LookaheadSuccess unused) {
            return true;
        }
    }

    private final boolean jj_2_20(int i2) {
        this.jj_la = i2;
        Token token = this.token;
        this.jj_scanpos = token;
        this.jj_lastpos = token;
        try {
            return true ^ jj_3_20();
        } catch (LookaheadSuccess unused) {
            return true;
        }
    }

    private final boolean jj_2_21(int i2) {
        this.jj_la = i2;
        Token token = this.token;
        this.jj_scanpos = token;
        this.jj_lastpos = token;
        try {
            return true ^ jj_3_21();
        } catch (LookaheadSuccess unused) {
            return true;
        }
    }

    private final boolean jj_2_22(int i2) {
        this.jj_la = i2;
        Token token = this.token;
        this.jj_scanpos = token;
        this.jj_lastpos = token;
        try {
            return true ^ jj_3_22();
        } catch (LookaheadSuccess unused) {
            return true;
        }
    }

    private final boolean jj_2_23(int i2) {
        this.jj_la = i2;
        Token token = this.token;
        this.jj_scanpos = token;
        this.jj_lastpos = token;
        try {
            return true ^ jj_3_23();
        } catch (LookaheadSuccess unused) {
            return true;
        }
    }

    private final boolean jj_2_24(int i2) {
        this.jj_la = i2;
        Token token = this.token;
        this.jj_scanpos = token;
        this.jj_lastpos = token;
        try {
            return true ^ jj_3_24();
        } catch (LookaheadSuccess unused) {
            return true;
        }
    }

    private final boolean jj_2_25(int i2) {
        this.jj_la = i2;
        Token token = this.token;
        this.jj_scanpos = token;
        this.jj_lastpos = token;
        try {
            return true ^ jj_3_25();
        } catch (LookaheadSuccess unused) {
            return true;
        }
    }

    private final boolean jj_2_26(int i2) {
        this.jj_la = i2;
        Token token = this.token;
        this.jj_scanpos = token;
        this.jj_lastpos = token;
        try {
            return true ^ jj_3_26();
        } catch (LookaheadSuccess unused) {
            return true;
        }
    }

    private final boolean jj_2_27(int i2) {
        this.jj_la = i2;
        Token token = this.token;
        this.jj_scanpos = token;
        this.jj_lastpos = token;
        try {
            return true ^ jj_3_27();
        } catch (LookaheadSuccess unused) {
            return true;
        }
    }

    private final boolean jj_2_28(int i2) {
        this.jj_la = i2;
        Token token = this.token;
        this.jj_scanpos = token;
        this.jj_lastpos = token;
        try {
            return true ^ jj_3_28();
        } catch (LookaheadSuccess unused) {
            return true;
        }
    }

    private final boolean jj_2_29(int i2) {
        this.jj_la = i2;
        Token token = this.token;
        this.jj_scanpos = token;
        this.jj_lastpos = token;
        try {
            return true ^ jj_3_29();
        } catch (LookaheadSuccess unused) {
            return true;
        }
    }

    private final boolean jj_2_3(int i2) {
        this.jj_la = i2;
        Token token = this.token;
        this.jj_scanpos = token;
        this.jj_lastpos = token;
        try {
            return true ^ jj_3_3();
        } catch (LookaheadSuccess unused) {
            return true;
        }
    }

    private final boolean jj_2_30(int i2) {
        this.jj_la = i2;
        Token token = this.token;
        this.jj_scanpos = token;
        this.jj_lastpos = token;
        try {
            return true ^ jj_3_30();
        } catch (LookaheadSuccess unused) {
            return true;
        }
    }

    private final boolean jj_2_31(int i2) {
        this.jj_la = i2;
        Token token = this.token;
        this.jj_scanpos = token;
        this.jj_lastpos = token;
        try {
            return true ^ jj_3_31();
        } catch (LookaheadSuccess unused) {
            return true;
        }
    }

    private final boolean jj_2_4(int i2) {
        this.jj_la = i2;
        Token token = this.token;
        this.jj_scanpos = token;
        this.jj_lastpos = token;
        try {
            return true ^ jj_3_4();
        } catch (LookaheadSuccess unused) {
            return true;
        }
    }

    private final boolean jj_2_5(int i2) {
        this.jj_la = i2;
        Token token = this.token;
        this.jj_scanpos = token;
        this.jj_lastpos = token;
        try {
            return true ^ jj_3_5();
        } catch (LookaheadSuccess unused) {
            return true;
        }
    }

    private final boolean jj_2_6(int i2) {
        this.jj_la = i2;
        Token token = this.token;
        this.jj_scanpos = token;
        this.jj_lastpos = token;
        try {
            return true ^ jj_3_6();
        } catch (LookaheadSuccess unused) {
            return true;
        }
    }

    private final boolean jj_2_7(int i2) {
        this.jj_la = i2;
        Token token = this.token;
        this.jj_scanpos = token;
        this.jj_lastpos = token;
        try {
            return true ^ jj_3_7();
        } catch (LookaheadSuccess unused) {
            return true;
        }
    }

    private final boolean jj_2_8(int i2) {
        this.jj_la = i2;
        Token token = this.token;
        this.jj_scanpos = token;
        this.jj_lastpos = token;
        try {
            return true ^ jj_3_8();
        } catch (LookaheadSuccess unused) {
            return true;
        }
    }

    private final boolean jj_2_9(int i2) {
        this.jj_la = i2;
        Token token = this.token;
        this.jj_scanpos = token;
        this.jj_lastpos = token;
        try {
            return true ^ jj_3_9();
        } catch (LookaheadSuccess unused) {
            return true;
        }
    }

    private final boolean jj_3R_100() {
        return jj_3R_130();
    }

    private final boolean jj_3R_101() {
        return jj_3R_37();
    }

    private final boolean jj_3R_102() {
        return jj_3R_32();
    }

    private final boolean jj_3R_103() {
        return jj_3R_29();
    }

    private final boolean jj_3R_104() {
        Token token = this.jj_scanpos;
        if (!jj_3_16()) {
            return false;
        }
        this.jj_scanpos = token;
        if (!jj_3R_131()) {
            return false;
        }
        this.jj_scanpos = token;
        if (!jj_3R_132()) {
            return false;
        }
        this.jj_scanpos = token;
        return jj_3R_133();
    }

    private final boolean jj_3R_105() {
        return jj_3R_129();
    }

    private final boolean jj_3R_106() {
        return jj_3R_134();
    }

    private final boolean jj_3R_107() {
        return jj_3R_33() || jj_3R_34() || jj_3R_39();
    }

    private final boolean jj_3R_108() {
        if (jj_3R_135()) {
            return true;
        }
        Token token = this.jj_scanpos;
        if (!jj_3R_156()) {
            return false;
        }
        this.jj_scanpos = token;
        return false;
    }

    private final boolean jj_3R_109() {
        Token token = this.jj_scanpos;
        if (!jj_3_5()) {
            return false;
        }
        this.jj_scanpos = token;
        return jj_3R_136();
    }

    private final boolean jj_3R_110() {
        return jj_scan_token(79) || jj_3R_109();
    }

    private final boolean jj_3R_111() {
        return jj_scan_token(79) || jj_3R_29();
    }

    private final boolean jj_3R_112() {
        return jj_3R_39();
    }

    private final boolean jj_3R_113() {
        Token token;
        if (jj_scan_token(50) || jj_scan_token(72) || jj_3R_39() || jj_scan_token(73) || jj_scan_token(74)) {
            return true;
        }
        do {
            token = this.jj_scanpos;
        } while (!jj_3R_183());
        this.jj_scanpos = token;
        return jj_scan_token(75);
    }

    private final boolean jj_3R_114() {
        if (jj_scan_token(32) || jj_scan_token(72) || jj_3R_39() || jj_scan_token(73) || jj_3R_45()) {
            return true;
        }
        Token token = this.jj_scanpos;
        if (!jj_3R_184()) {
            return false;
        }
        this.jj_scanpos = token;
        return false;
    }

    private final boolean jj_3R_115() {
        return jj_scan_token(59) || jj_scan_token(72) || jj_3R_39() || jj_scan_token(73) || jj_3R_45();
    }

    private final boolean jj_3R_116() {
        return jj_scan_token(21) || jj_3R_45() || jj_scan_token(59) || jj_scan_token(72) || jj_3R_39() || jj_scan_token(73) || jj_scan_token(78);
    }

    private final boolean jj_3R_117() {
        if (jj_scan_token(30) || jj_scan_token(72)) {
            return true;
        }
        Token token = this.jj_scanpos;
        if (jj_3R_185()) {
            this.jj_scanpos = token;
        }
        if (jj_scan_token(78)) {
            return true;
        }
        Token token2 = this.jj_scanpos;
        if (jj_3R_186()) {
            this.jj_scanpos = token2;
        }
        if (jj_scan_token(78)) {
            return true;
        }
        Token token3 = this.jj_scanpos;
        if (jj_3R_187()) {
            this.jj_scanpos = token3;
        }
        return jj_scan_token(73) || jj_3R_45();
    }

    private final boolean jj_3R_118() {
        Token token = this.jj_scanpos;
        if (!jj_3_30()) {
            return false;
        }
        this.jj_scanpos = token;
        return jj_3R_137();
    }

    private final boolean jj_3R_119() {
        if (jj_scan_token(12)) {
            return true;
        }
        Token token = this.jj_scanpos;
        if (jj_scan_token(69)) {
            this.jj_scanpos = token;
        }
        return jj_scan_token(78);
    }

    private final boolean jj_3R_120() {
        if (jj_scan_token(19)) {
            return true;
        }
        Token token = this.jj_scanpos;
        if (jj_scan_token(69)) {
            this.jj_scanpos = token;
        }
        return jj_scan_token(78);
    }

    private final boolean jj_3R_121() {
        if (jj_scan_token(46)) {
            return true;
        }
        Token token = this.jj_scanpos;
        if (jj_3R_188()) {
            this.jj_scanpos = token;
        }
        return jj_scan_token(78);
    }

    private final boolean jj_3R_122() {
        return jj_scan_token(51) || jj_scan_token(72) || jj_3R_39() || jj_scan_token(73) || jj_3R_38();
    }

    private final boolean jj_3R_123() {
        return jj_scan_token(53) || jj_3R_39() || jj_scan_token(78);
    }

    private final boolean jj_3R_124() {
        Token token;
        if (jj_scan_token(56) || jj_3R_38()) {
            return true;
        }
        do {
            token = this.jj_scanpos;
        } while (!jj_3R_189());
        this.jj_scanpos = token;
        if (!jj_3R_190()) {
            return false;
        }
        this.jj_scanpos = token;
        return false;
    }

    private final boolean jj_3R_125() {
        return jj_scan_token(37);
    }

    private final boolean jj_3R_126() {
        return jj_scan_token(69);
    }

    private final boolean jj_3R_127() {
        return jj_3R_42() || jj_scan_token(69);
    }

    private final boolean jj_3R_128() {
        return jj_scan_token(34) || jj_scan_token(104) || jj_scan_token(78);
    }

    private final boolean jj_3R_129() {
        Token token = this.jj_scanpos;
        if (!jj_3R_138()) {
            return false;
        }
        this.jj_scanpos = token;
        if (!jj_3R_139()) {
            return false;
        }
        this.jj_scanpos = token;
        if (!jj_3R_140()) {
            return false;
        }
        this.jj_scanpos = token;
        if (!jj_3R_141()) {
            return false;
        }
        this.jj_scanpos = token;
        if (!jj_3R_142()) {
            return false;
        }
        this.jj_scanpos = token;
        if (!jj_3R_143()) {
            return false;
        }
        this.jj_scanpos = token;
        return jj_3R_144();
    }

    private final boolean jj_3R_130() {
        Token token = this.jj_scanpos;
        if (!jj_3_18()) {
            return false;
        }
        this.jj_scanpos = token;
        return jj_3R_145();
    }

    private final boolean jj_3R_131() {
        return jj_scan_token(76) || jj_3R_39() || jj_scan_token(77);
    }

    private final boolean jj_3R_132() {
        if (jj_scan_token(80) || jj_scan_token(69)) {
            return true;
        }
        Token token = this.jj_scanpos;
        if (!jj_3R_146()) {
            return false;
        }
        this.jj_scanpos = token;
        return false;
    }

    private final boolean jj_3R_133() {
        return jj_scan_token(74) || jj_3R_39() || jj_scan_token(75);
    }

    private final boolean jj_3R_134() {
        Token token;
        if (jj_3R_39()) {
            return true;
        }
        do {
            token = this.jj_scanpos;
        } while (!jj_3R_147());
        this.jj_scanpos = token;
        return false;
    }

    private final boolean jj_3R_135() {
        Token token;
        if (jj_3R_148()) {
            return true;
        }
        do {
            token = this.jj_scanpos;
        } while (!jj_3R_159());
        this.jj_scanpos = token;
        return false;
    }

    private final boolean jj_3R_136() {
        return jj_scan_token(69);
    }

    private final boolean jj_3R_137() {
        return jj_scan_token(30) || jj_scan_token(72) || jj_3R_32() || jj_scan_token(69) || jj_scan_token(89) || jj_3R_39() || jj_scan_token(73) || jj_3R_45();
    }

    private final boolean jj_3R_138() {
        return jj_scan_token(60);
    }

    private final boolean jj_3R_139() {
        return jj_scan_token(64);
    }

    private final boolean jj_3R_140() {
        return jj_scan_token(66);
    }

    private final boolean jj_3R_141() {
        return jj_scan_token(67);
    }

    private final boolean jj_3R_142() {
        return jj_3R_149();
    }

    private final boolean jj_3R_143() {
        return jj_scan_token(41);
    }

    private final boolean jj_3R_144() {
        return jj_scan_token(57);
    }

    private final boolean jj_3R_145() {
        if (jj_scan_token(40) || jj_3R_29()) {
            return true;
        }
        Token token = this.jj_scanpos;
        if (!jj_3R_151()) {
            return false;
        }
        this.jj_scanpos = token;
        return jj_3R_152();
    }

    private final boolean jj_3R_146() {
        return jj_3R_69();
    }

    private final boolean jj_3R_147() {
        return jj_scan_token(79) || jj_3R_39();
    }

    private final boolean jj_3R_148() {
        Token token;
        if (jj_3R_153()) {
            return true;
        }
        do {
            token = this.jj_scanpos;
        } while (!jj_3R_162());
        this.jj_scanpos = token;
        return false;
    }

    private final boolean jj_3R_149() {
        Token token = this.jj_scanpos;
        if (!jj_3R_154()) {
            return false;
        }
        this.jj_scanpos = token;
        return jj_3R_155();
    }

    private final boolean jj_3R_150() {
        Token token = this.jj_scanpos;
        if (!jj_3_21()) {
            return false;
        }
        this.jj_scanpos = token;
        return jj_3R_157();
    }

    private final boolean jj_3R_151() {
        return jj_3R_150();
    }

    private final boolean jj_3R_152() {
        if (jj_3R_69()) {
            return true;
        }
        Token token = this.jj_scanpos;
        if (!jj_3_17()) {
            return false;
        }
        this.jj_scanpos = token;
        return false;
    }

    private final boolean jj_3R_153() {
        Token token;
        if (jj_3R_158()) {
            return true;
        }
        do {
            token = this.jj_scanpos;
        } while (!jj_3R_165());
        this.jj_scanpos = token;
        return false;
    }

    private final boolean jj_3R_154() {
        return jj_scan_token(55);
    }

    private final boolean jj_3R_155() {
        return jj_scan_token(26);
    }

    private final boolean jj_3R_156() {
        return jj_scan_token(88) || jj_3R_39() || jj_scan_token(89) || jj_3R_108();
    }

    private final boolean jj_3R_157() {
        Token token;
        if (jj_3R_160()) {
            return true;
        }
        do {
            token = this.jj_scanpos;
        } while (!jj_3R_160());
        this.jj_scanpos = token;
        return jj_3R_97();
    }

    private final boolean jj_3R_158() {
        Token token;
        if (jj_3R_161()) {
            return true;
        }
        do {
            token = this.jj_scanpos;
        } while (!jj_3R_167());
        this.jj_scanpos = token;
        return false;
    }

    private final boolean jj_3R_159() {
        Token token = this.jj_scanpos;
        if (jj_scan_token(96)) {
            this.jj_scanpos = token;
            if (jj_scan_token(97)) {
                return true;
            }
        }
        return jj_3R_148();
    }

    private final boolean jj_3R_160() {
        return jj_scan_token(76) || jj_scan_token(77);
    }

    private final boolean jj_3R_161() {
        Token token;
        if (jj_3R_164()) {
            return true;
        }
        do {
            token = this.jj_scanpos;
        } while (!jj_3R_169());
        this.jj_scanpos = token;
        return false;
    }

    private final boolean jj_3R_162() {
        Token token = this.jj_scanpos;
        if (jj_scan_token(98)) {
            this.jj_scanpos = token;
            if (jj_scan_token(99)) {
                return true;
            }
        }
        return jj_3R_153();
    }

    private final boolean jj_3R_163() {
        Token token;
        if (jj_3R_31()) {
            return true;
        }
        do {
            token = this.jj_scanpos;
        } while (!jj_3_4());
        this.jj_scanpos = token;
        return false;
    }

    private final boolean jj_3R_164() {
        Token token;
        if (jj_3R_166()) {
            return true;
        }
        do {
            token = this.jj_scanpos;
        } while (!jj_3R_171());
        this.jj_scanpos = token;
        return false;
    }

    private final boolean jj_3R_165() {
        Token token = this.jj_scanpos;
        if (jj_scan_token(108)) {
            this.jj_scanpos = token;
            if (jj_scan_token(109)) {
                return true;
            }
        }
        return jj_3R_158();
    }

    private final boolean jj_3R_166() {
        if (jj_3R_168()) {
            return true;
        }
        Token token = this.jj_scanpos;
        if (!jj_3R_179()) {
            return false;
        }
        this.jj_scanpos = token;
        return false;
    }

    private final boolean jj_3R_167() {
        return jj_scan_token(110) || jj_3R_161();
    }

    private final boolean jj_3R_168() {
        Token token;
        if (jj_3R_170()) {
            return true;
        }
        do {
            token = this.jj_scanpos;
        } while (!jj_3R_182());
        this.jj_scanpos = token;
        return false;
    }

    private final boolean jj_3R_169() {
        Token token = this.jj_scanpos;
        if (jj_scan_token(106)) {
            this.jj_scanpos = token;
            if (jj_scan_token(107)) {
                return true;
            }
        }
        return jj_3R_164();
    }

    private final boolean jj_3R_170() {
        Token token;
        if (jj_3R_178()) {
            return true;
        }
        do {
            token = this.jj_scanpos;
        } while (!jj_3R_192());
        this.jj_scanpos = token;
        return false;
    }

    private final boolean jj_3R_171() {
        Token token = this.jj_scanpos;
        if (jj_scan_token(90)) {
            this.jj_scanpos = token;
            if (jj_scan_token(95)) {
                return true;
            }
        }
        return jj_3R_166();
    }

    private final boolean jj_3R_172() {
        return jj_scan_token(25) || jj_3R_29();
    }

    private final boolean jj_3R_173() {
        return jj_scan_token(33) || jj_3R_76();
    }

    private final boolean jj_3R_174() {
        return jj_scan_token(54) || jj_3R_76();
    }

    private final boolean jj_3R_175() {
        return jj_3R_38();
    }

    private final boolean jj_3R_176() {
        if (jj_scan_token(69)) {
            return true;
        }
        Token token = this.jj_scanpos;
        if (!jj_3R_180()) {
            return false;
        }
        this.jj_scanpos = token;
        return false;
    }

    private final boolean jj_3R_177() {
        return jj_scan_token(79) || jj_3R_176();
    }

    private final boolean jj_3R_178() {
        Token token;
        if (jj_3R_181()) {
            return true;
        }
        do {
            token = this.jj_scanpos;
        } while (!jj_3R_200());
        this.jj_scanpos = token;
        return false;
    }

    private final boolean jj_3R_179() {
        return jj_scan_token(35) || jj_3R_32();
    }

    private final boolean jj_3R_180() {
        return jj_scan_token(81) || jj_3R_31();
    }

    private final boolean jj_3R_181() {
        Token token;
        if (jj_3R_191()) {
            return true;
        }
        do {
            token = this.jj_scanpos;
        } while (!jj_3R_209());
        this.jj_scanpos = token;
        return false;
    }

    private final boolean jj_3R_182() {
        Token token = this.jj_scanpos;
        if (jj_scan_token(84)) {
            this.jj_scanpos = token;
            if (jj_scan_token(85)) {
                this.jj_scanpos = token;
                if (jj_scan_token(82)) {
                    this.jj_scanpos = token;
                    if (jj_scan_token(83)) {
                        this.jj_scanpos = token;
                        if (jj_scan_token(91)) {
                            this.jj_scanpos = token;
                            if (jj_scan_token(92)) {
                                this.jj_scanpos = token;
                                if (jj_scan_token(93)) {
                                    this.jj_scanpos = token;
                                    if (jj_scan_token(94)) {
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return jj_3R_170();
    }

    private final boolean jj_3R_183() {
        Token token;
        if (jj_3R_193()) {
            return true;
        }
        do {
            token = this.jj_scanpos;
        } while (!jj_3_29());
        this.jj_scanpos = token;
        return false;
    }

    private final boolean jj_3R_184() {
        return jj_scan_token(23) || jj_3R_45();
    }

    private final boolean jj_3R_185() {
        return jj_3R_194();
    }

    private final boolean jj_3R_186() {
        return jj_3R_39();
    }

    private final boolean jj_3R_187() {
        return jj_3R_195();
    }

    private final boolean jj_3R_188() {
        return jj_3R_39();
    }

    private final boolean jj_3R_189() {
        return jj_scan_token(16) || jj_scan_token(72) || jj_3R_109() || jj_scan_token(73) || jj_3R_38();
    }

    private final boolean jj_3R_190() {
        return jj_scan_token(28) || jj_3R_38();
    }

    private final boolean jj_3R_191() {
        Token token = this.jj_scanpos;
        if (!jj_3R_196()) {
            return false;
        }
        this.jj_scanpos = token;
        if (!jj_3R_197()) {
            return false;
        }
        this.jj_scanpos = token;
        if (!jj_3R_198()) {
            return false;
        }
        this.jj_scanpos = token;
        return jj_3R_199();
    }

    private final boolean jj_3R_192() {
        Token token = this.jj_scanpos;
        if (jj_scan_token(112)) {
            this.jj_scanpos = token;
            if (jj_scan_token(113)) {
                this.jj_scanpos = token;
                if (jj_scan_token(114)) {
                    this.jj_scanpos = token;
                    if (jj_scan_token(115)) {
                        this.jj_scanpos = token;
                        if (jj_scan_token(116)) {
                            this.jj_scanpos = token;
                            if (jj_scan_token(117)) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return jj_3R_178();
    }

    private final boolean jj_3R_193() {
        Token token = this.jj_scanpos;
        if (!jj_3R_201()) {
            return false;
        }
        this.jj_scanpos = token;
        return jj_3R_202();
    }

    private final boolean jj_3R_194() {
        Token token = this.jj_scanpos;
        if (!jj_3R_203()) {
            return false;
        }
        this.jj_scanpos = token;
        return jj_3R_204();
    }

    private final boolean jj_3R_195() {
        return jj_3R_205();
    }

    private final boolean jj_3R_196() {
        Token token = this.jj_scanpos;
        if (jj_scan_token(102)) {
            this.jj_scanpos = token;
            if (jj_scan_token(103)) {
                return true;
            }
        }
        return jj_3R_191();
    }

    private final boolean jj_3R_197() {
        return jj_3R_206();
    }

    private final boolean jj_3R_198() {
        return jj_3R_207();
    }

    private final boolean jj_3R_199() {
        return jj_3R_208();
    }

    private final boolean jj_3R_200() {
        Token token = this.jj_scanpos;
        if (jj_scan_token(102)) {
            this.jj_scanpos = token;
            if (jj_scan_token(103)) {
                return true;
            }
        }
        return jj_3R_181();
    }

    private final boolean jj_3R_201() {
        return jj_scan_token(15) || jj_3R_39() || jj_scan_token(89);
    }

    private final boolean jj_3R_202() {
        return jj_scan_token(20) || jj_scan_token(89);
    }

    private final boolean jj_3R_203() {
        return jj_3R_93();
    }

    private final boolean jj_3R_204() {
        return jj_3R_205();
    }

    private final boolean jj_3R_205() {
        Token token;
        if (jj_3R_112()) {
            return true;
        }
        do {
            token = this.jj_scanpos;
        } while (!jj_3R_210());
        this.jj_scanpos = token;
        return false;
    }

    private final boolean jj_3R_206() {
        return jj_scan_token(100) || jj_3R_33();
    }

    private final boolean jj_3R_207() {
        return jj_scan_token(101) || jj_3R_33();
    }

    private final boolean jj_3R_208() {
        Token token = this.jj_scanpos;
        if (!jj_3R_211()) {
            return false;
        }
        this.jj_scanpos = token;
        if (!jj_3R_212()) {
            return false;
        }
        this.jj_scanpos = token;
        return jj_3R_213();
    }

    private final boolean jj_3R_209() {
        Token token = this.jj_scanpos;
        if (jj_scan_token(104)) {
            this.jj_scanpos = token;
            if (jj_scan_token(105)) {
                this.jj_scanpos = token;
                if (jj_scan_token(111)) {
                    return true;
                }
            }
        }
        return jj_3R_191();
    }

    private final boolean jj_3R_210() {
        return jj_scan_token(79) || jj_3R_112();
    }

    private final boolean jj_3R_211() {
        Token token = this.jj_scanpos;
        if (jj_scan_token(87)) {
            this.jj_scanpos = token;
            if (jj_scan_token(86)) {
                return true;
            }
        }
        return jj_3R_191();
    }

    private final boolean jj_3R_212() {
        return jj_3R_214();
    }

    private final boolean jj_3R_213() {
        return jj_3R_215();
    }

    private final boolean jj_3R_214() {
        Token token = this.jj_scanpos;
        if (!jj_3R_216()) {
            return false;
        }
        this.jj_scanpos = token;
        return jj_3R_217();
    }

    private final boolean jj_3R_215() {
        Token token = this.jj_scanpos;
        if (!jj_3R_218()) {
            return false;
        }
        this.jj_scanpos = token;
        return jj_3R_219();
    }

    private final boolean jj_3R_216() {
        return jj_scan_token(72) || jj_3R_32() || jj_scan_token(73) || jj_3R_191();
    }

    private final boolean jj_3R_217() {
        return jj_scan_token(72) || jj_3R_32() || jj_scan_token(73) || jj_3R_208();
    }

    private final boolean jj_3R_218() {
        if (jj_3R_33()) {
            return true;
        }
        Token token = this.jj_scanpos;
        if (!jj_scan_token(100)) {
            return false;
        }
        this.jj_scanpos = token;
        return jj_scan_token(101);
    }

    private final boolean jj_3R_219() {
        return jj_3R_33();
    }

    private final boolean jj_3R_28() {
        Token token = this.jj_scanpos;
        if (!jj_3R_46()) {
            return false;
        }
        this.jj_scanpos = token;
        if (!jj_3R_47()) {
            return false;
        }
        this.jj_scanpos = token;
        if (!jj_3R_48()) {
            return false;
        }
        this.jj_scanpos = token;
        if (!jj_3R_49()) {
            return false;
        }
        this.jj_scanpos = token;
        if (!jj_3_28()) {
            return false;
        }
        this.jj_scanpos = token;
        if (!jj_3R_50()) {
            return false;
        }
        this.jj_scanpos = token;
        if (!jj_3R_51()) {
            return false;
        }
        this.jj_scanpos = token;
        return jj_3R_52();
    }

    private final boolean jj_3R_29() {
        Token token;
        if (jj_scan_token(69)) {
            return true;
        }
        do {
            token = this.jj_scanpos;
        } while (!jj_3_7());
        this.jj_scanpos = token;
        return false;
    }

    private final boolean jj_3R_30() {
        return jj_scan_token(80) || jj_scan_token(104);
    }

    private final boolean jj_3R_31() {
        Token token = this.jj_scanpos;
        if (!jj_3R_53()) {
            return false;
        }
        this.jj_scanpos = token;
        return jj_3R_54();
    }

    private final boolean jj_3R_32() {
        Token token;
        Token token2 = this.jj_scanpos;
        if (jj_3R_55()) {
            this.jj_scanpos = token2;
            if (jj_3R_56()) {
                return true;
            }
        }
        do {
            token = this.jj_scanpos;
        } while (!jj_3_6());
        this.jj_scanpos = token;
        return false;
    }

    private final boolean jj_3R_33() {
        Token token;
        if (jj_3R_57()) {
            return true;
        }
        do {
            token = this.jj_scanpos;
        } while (!jj_3R_58());
        this.jj_scanpos = token;
        return false;
    }

    private final boolean jj_3R_34() {
        Token token = this.jj_scanpos;
        if (!jj_scan_token(81)) {
            return false;
        }
        this.jj_scanpos = token;
        if (!jj_scan_token(120)) {
            return false;
        }
        this.jj_scanpos = token;
        if (!jj_scan_token(121)) {
            return false;
        }
        this.jj_scanpos = token;
        if (!jj_scan_token(127)) {
            return false;
        }
        this.jj_scanpos = token;
        if (!jj_scan_token(118)) {
            return false;
        }
        this.jj_scanpos = token;
        if (!jj_scan_token(119)) {
            return false;
        }
        this.jj_scanpos = token;
        if (!jj_scan_token(122)) {
            return false;
        }
        this.jj_scanpos = token;
        if (!jj_scan_token(126)) {
            return false;
        }
        this.jj_scanpos = token;
        if (!jj_scan_token(124)) {
            return false;
        }
        this.jj_scanpos = token;
        if (!jj_scan_token(128)) {
            return false;
        }
        this.jj_scanpos = token;
        if (!jj_scan_token(129)) {
            return false;
        }
        this.jj_scanpos = token;
        if (!jj_scan_token(130)) {
            return false;
        }
        this.jj_scanpos = token;
        if (!jj_scan_token(131)) {
            return false;
        }
        this.jj_scanpos = token;
        if (!jj_scan_token(132)) {
            return false;
        }
        this.jj_scanpos = token;
        return jj_scan_token(133);
    }

    private final boolean jj_3R_35() {
        Token token = this.jj_scanpos;
        if (!jj_3_10()) {
            return false;
        }
        this.jj_scanpos = token;
        if (!jj_3R_59()) {
            return false;
        }
        this.jj_scanpos = token;
        return jj_3R_60();
    }

    private final boolean jj_3R_36() {
        Token token = this.jj_scanpos;
        if (!jj_3R_61()) {
            return false;
        }
        this.jj_scanpos = token;
        if (!jj_3R_62()) {
            return false;
        }
        this.jj_scanpos = token;
        if (!jj_3R_63()) {
            return false;
        }
        this.jj_scanpos = token;
        if (!jj_3R_64()) {
            return false;
        }
        this.jj_scanpos = token;
        if (!jj_3R_65()) {
            return false;
        }
        this.jj_scanpos = token;
        if (!jj_3R_66()) {
            return false;
        }
        this.jj_scanpos = token;
        if (!jj_3R_67()) {
            return false;
        }
        this.jj_scanpos = token;
        return jj_3R_68();
    }

    private final boolean jj_3R_37() {
        return jj_3R_29() || jj_3R_69();
    }

    private final boolean jj_3R_38() {
        Token token;
        if (jj_scan_token(74)) {
            return true;
        }
        do {
            token = this.jj_scanpos;
        } while (!jj_3_23());
        this.jj_scanpos = token;
        return jj_scan_token(75);
    }

    private final boolean jj_3R_39() {
        Token token = this.jj_scanpos;
        if (!jj_3R_70()) {
            return false;
        }
        this.jj_scanpos = token;
        return jj_3R_71();
    }

    private final boolean jj_3R_40() {
        return jj_scan_token(69) || jj_scan_token(89) || jj_3R_45();
    }

    private final boolean jj_3R_41() {
        Token token;
        do {
            token = this.jj_scanpos;
        } while (!jj_3R_72());
        this.jj_scanpos = token;
        return false;
    }

    private final boolean jj_3R_42() {
        Token token = this.jj_scanpos;
        if (!jj_3R_73()) {
            return false;
        }
        this.jj_scanpos = token;
        return jj_3R_74();
    }

    private final boolean jj_3R_43() {
        if (jj_scan_token(72)) {
            return true;
        }
        Token token = this.jj_scanpos;
        if (jj_3R_75()) {
            this.jj_scanpos = token;
        }
        return jj_scan_token(73);
    }

    private final boolean jj_3R_44() {
        return jj_scan_token(54) || jj_3R_76();
    }

    private final boolean jj_3R_45() {
        Token token = this.jj_scanpos;
        if (jj_3_22()) {
            this.jj_scanpos = token;
            if (jj_3R_77()) {
                this.jj_scanpos = token;
                if (jj_scan_token(78)) {
                    this.jj_scanpos = token;
                    if (jj_3R_78()) {
                        this.jj_scanpos = token;
                        if (jj_3R_79()) {
                            this.jj_scanpos = token;
                            if (jj_3R_80()) {
                                this.jj_scanpos = token;
                                if (jj_3R_81()) {
                                    this.jj_scanpos = token;
                                    if (jj_3R_82()) {
                                        this.jj_scanpos = token;
                                        this.lookingAhead = true;
                                        boolean isRegularForStatement = isRegularForStatement();
                                        this.jj_semLA = isRegularForStatement;
                                        this.lookingAhead = false;
                                        if (!isRegularForStatement || jj_3R_83()) {
                                            this.jj_scanpos = token;
                                            if (jj_3R_84()) {
                                                this.jj_scanpos = token;
                                                if (jj_3R_85()) {
                                                    this.jj_scanpos = token;
                                                    if (jj_3R_86()) {
                                                        this.jj_scanpos = token;
                                                        if (jj_3R_87()) {
                                                            this.jj_scanpos = token;
                                                            if (jj_3R_88()) {
                                                                this.jj_scanpos = token;
                                                                if (jj_3R_89()) {
                                                                    this.jj_scanpos = token;
                                                                    if (jj_3R_90()) {
                                                                        return true;
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private final boolean jj_3R_46() {
        return jj_3R_91();
    }

    private final boolean jj_3R_47() {
        return jj_3R_92();
    }

    private final boolean jj_3R_48() {
        return jj_3R_92();
    }

    private final boolean jj_3R_49() {
        return jj_3R_93() || jj_scan_token(78);
    }

    private final boolean jj_3R_50() {
        return jj_3R_94();
    }

    private final boolean jj_3R_51() {
        return jj_3R_95();
    }

    private final boolean jj_3R_52() {
        return jj_3R_96();
    }

    private final boolean jj_3R_53() {
        return jj_3R_97();
    }

    private final boolean jj_3R_54() {
        return jj_3R_39();
    }

    private final boolean jj_3R_55() {
        return jj_3R_36();
    }

    private final boolean jj_3R_56() {
        return jj_3R_29();
    }

    private final boolean jj_3R_57() {
        Token token = this.jj_scanpos;
        if (!jj_3R_98()) {
            return false;
        }
        this.jj_scanpos = token;
        if (!jj_3R_99()) {
            return false;
        }
        this.jj_scanpos = token;
        if (!jj_3R_100()) {
            return false;
        }
        this.jj_scanpos = token;
        if (!jj_3R_101()) {
            return false;
        }
        this.jj_scanpos = token;
        if (!jj_3R_102()) {
            return false;
        }
        this.jj_scanpos = token;
        return jj_3R_103();
    }

    private final boolean jj_3R_58() {
        return jj_3R_104();
    }

    private final boolean jj_3R_59() {
        return jj_scan_token(72) || jj_3R_29() || jj_scan_token(76) || jj_scan_token(77);
    }

    private final boolean jj_3R_60() {
        if (jj_scan_token(72) || jj_3R_29() || jj_scan_token(73)) {
            return true;
        }
        Token token = this.jj_scanpos;
        if (!jj_scan_token(87)) {
            return false;
        }
        this.jj_scanpos = token;
        if (!jj_scan_token(86)) {
            return false;
        }
        this.jj_scanpos = token;
        if (!jj_scan_token(72)) {
            return false;
        }
        this.jj_scanpos = token;
        if (!jj_scan_token(69)) {
            return false;
        }
        this.jj_scanpos = token;
        if (!jj_scan_token(40)) {
            return false;
        }
        this.jj_scanpos = token;
        return jj_3R_105();
    }

    private final boolean jj_3R_61() {
        return jj_scan_token(11);
    }

    private final boolean jj_3R_62() {
        return jj_scan_token(17);
    }

    private final boolean jj_3R_63() {
        return jj_scan_token(14);
    }

    private final boolean jj_3R_64() {
        return jj_scan_token(47);
    }

    private final boolean jj_3R_65() {
        return jj_scan_token(36);
    }

    private final boolean jj_3R_66() {
        return jj_scan_token(38);
    }

    private final boolean jj_3R_67() {
        return jj_scan_token(29);
    }

    private final boolean jj_3R_68() {
        return jj_scan_token(22);
    }

    private final boolean jj_3R_69() {
        if (jj_scan_token(72)) {
            return true;
        }
        Token token = this.jj_scanpos;
        if (jj_3R_106()) {
            this.jj_scanpos = token;
        }
        return jj_scan_token(73);
    }

    private final boolean jj_3R_70() {
        return jj_3R_107();
    }

    private final boolean jj_3R_71() {
        return jj_3R_108();
    }

    private final boolean jj_3R_72() {
        Token token = this.jj_scanpos;
        if (!jj_scan_token(43)) {
            return false;
        }
        this.jj_scanpos = token;
        if (!jj_scan_token(44)) {
            return false;
        }
        this.jj_scanpos = token;
        if (!jj_scan_token(45)) {
            return false;
        }
        this.jj_scanpos = token;
        if (!jj_scan_token(51)) {
            return false;
        }
        this.jj_scanpos = token;
        if (!jj_scan_token(27)) {
            return false;
        }
        this.jj_scanpos = token;
        if (!jj_scan_token(39)) {
            return false;
        }
        this.jj_scanpos = token;
        if (!jj_scan_token(52)) {
            return false;
        }
        this.jj_scanpos = token;
        if (!jj_scan_token(58)) {
            return false;
        }
        this.jj_scanpos = token;
        if (!jj_scan_token(10)) {
            return false;
        }
        this.jj_scanpos = token;
        if (!jj_scan_token(48)) {
            return false;
        }
        this.jj_scanpos = token;
        return jj_scan_token(49);
    }

    private final boolean jj_3R_73() {
        return jj_scan_token(57);
    }

    private final boolean jj_3R_74() {
        return jj_3R_32();
    }

    private final boolean jj_3R_75() {
        Token token;
        if (jj_3R_109()) {
            return true;
        }
        do {
            token = this.jj_scanpos;
        } while (!jj_3R_110());
        this.jj_scanpos = token;
        return false;
    }

    private final boolean jj_3R_76() {
        Token token;
        if (jj_3R_29()) {
            return true;
        }
        do {
            token = this.jj_scanpos;
        } while (!jj_3R_111());
        this.jj_scanpos = token;
        return false;
    }

    private final boolean jj_3R_77() {
        return jj_3R_38();
    }

    private final boolean jj_3R_78() {
        return jj_3R_112() || jj_scan_token(78);
    }

    private final boolean jj_3R_79() {
        return jj_3R_113();
    }

    private final boolean jj_3R_80() {
        return jj_3R_114();
    }

    private final boolean jj_3R_81() {
        return jj_3R_115();
    }

    private final boolean jj_3R_82() {
        return jj_3R_116();
    }

    private final boolean jj_3R_83() {
        return jj_3R_117();
    }

    private final boolean jj_3R_84() {
        return jj_3R_118();
    }

    private final boolean jj_3R_85() {
        return jj_3R_119();
    }

    private final boolean jj_3R_86() {
        return jj_3R_120();
    }

    private final boolean jj_3R_87() {
        return jj_3R_121();
    }

    private final boolean jj_3R_88() {
        return jj_3R_122();
    }

    private final boolean jj_3R_89() {
        return jj_3R_123();
    }

    private final boolean jj_3R_90() {
        return jj_3R_124();
    }

    private final boolean jj_3R_91() {
        if (jj_3R_41()) {
            return true;
        }
        Token token = this.jj_scanpos;
        if (jj_scan_token(13)) {
            this.jj_scanpos = token;
            if (jj_3R_125()) {
                return true;
            }
        }
        if (jj_scan_token(69)) {
            return true;
        }
        Token token2 = this.jj_scanpos;
        if (jj_3R_172()) {
            this.jj_scanpos = token2;
        }
        Token token3 = this.jj_scanpos;
        if (jj_3R_173()) {
            this.jj_scanpos = token3;
        }
        return jj_3R_38();
    }

    private final boolean jj_3R_92() {
        if (jj_3R_41()) {
            return true;
        }
        Token token = this.jj_scanpos;
        if (jj_3R_126()) {
            this.jj_scanpos = token;
            if (jj_3R_127()) {
                return true;
            }
        }
        if (jj_3R_43()) {
            return true;
        }
        Token token2 = this.jj_scanpos;
        if (jj_3R_174()) {
            this.jj_scanpos = token2;
        }
        Token token3 = this.jj_scanpos;
        if (!jj_3R_175()) {
            return false;
        }
        this.jj_scanpos = token3;
        return jj_scan_token(78);
    }

    private final boolean jj_3R_93() {
        Token token;
        if (jj_3R_41() || jj_3R_32() || jj_3R_176()) {
            return true;
        }
        do {
            token = this.jj_scanpos;
        } while (!jj_3R_177());
        this.jj_scanpos = token;
        return false;
    }

    private final boolean jj_3R_94() {
        Token token = this.jj_scanpos;
        if (!jj_3_3()) {
            return false;
        }
        this.jj_scanpos = token;
        return jj_3R_128();
    }

    private final boolean jj_3R_95() {
        return jj_scan_token(42) || jj_3R_29();
    }

    private final boolean jj_3R_96() {
        return jj_scan_token(68);
    }

    private final boolean jj_3R_97() {
        if (jj_scan_token(74)) {
            return true;
        }
        Token token = this.jj_scanpos;
        if (jj_3R_163()) {
            this.jj_scanpos = token;
        }
        Token token2 = this.jj_scanpos;
        if (jj_scan_token(79)) {
            this.jj_scanpos = token2;
        }
        return jj_scan_token(75);
    }

    private final boolean jj_3R_98() {
        return jj_3R_129();
    }

    private final boolean jj_3R_99() {
        return jj_scan_token(72) || jj_3R_39() || jj_scan_token(73);
    }

    private final boolean jj_3_1() {
        return jj_3R_28();
    }

    private final boolean jj_3_10() {
        return jj_scan_token(72) || jj_3R_36();
    }

    private final boolean jj_3_11() {
        return jj_scan_token(72) || jj_3R_29() || jj_scan_token(76);
    }

    private final boolean jj_3_12() {
        if (jj_3R_33()) {
            return true;
        }
        Token token = this.jj_scanpos;
        if (!jj_scan_token(100)) {
            return false;
        }
        this.jj_scanpos = token;
        return jj_scan_token(101);
    }

    private final boolean jj_3_13() {
        return jj_scan_token(72) || jj_3R_36();
    }

    private final boolean jj_3_14() {
        return jj_3R_37();
    }

    private final boolean jj_3_15() {
        return jj_3R_32() || jj_scan_token(80) || jj_scan_token(13);
    }

    private final boolean jj_3_16() {
        return jj_scan_token(80) || jj_scan_token(13);
    }

    private final boolean jj_3_17() {
        return jj_3R_38();
    }

    private final boolean jj_3_18() {
        return jj_scan_token(40) || jj_3R_36() || jj_3R_150();
    }

    private final boolean jj_3_19() {
        return jj_scan_token(76) || jj_3R_39() || jj_scan_token(77);
    }

    private final boolean jj_3_2() {
        return jj_scan_token(69) || jj_scan_token(72);
    }

    private final boolean jj_3_20() {
        return jj_scan_token(76) || jj_scan_token(77);
    }

    private final boolean jj_3_21() {
        Token token;
        Token token2;
        if (jj_3_19()) {
            return true;
        }
        do {
            token = this.jj_scanpos;
        } while (!jj_3_19());
        this.jj_scanpos = token;
        do {
            token2 = this.jj_scanpos;
        } while (!jj_3_20());
        this.jj_scanpos = token2;
        return false;
    }

    private final boolean jj_3_22() {
        return jj_3R_40();
    }

    private final boolean jj_3_23() {
        return jj_3R_28();
    }

    private final boolean jj_3_24() {
        if (jj_3R_41()) {
            return true;
        }
        Token token = this.jj_scanpos;
        if (!jj_scan_token(13)) {
            return false;
        }
        this.jj_scanpos = token;
        return jj_scan_token(37);
    }

    private final boolean jj_3_25() {
        return jj_3R_41() || jj_3R_42() || jj_scan_token(69) || jj_scan_token(72);
    }

    private final boolean jj_3_26() {
        if (jj_3R_41() || jj_scan_token(69) || jj_3R_43()) {
            return true;
        }
        Token token = this.jj_scanpos;
        if (jj_3R_44()) {
            this.jj_scanpos = token;
        }
        return jj_scan_token(74);
    }

    private final boolean jj_3_27() {
        return jj_3R_41() || jj_3R_32() || jj_scan_token(69);
    }

    private final boolean jj_3_28() {
        return jj_3R_45();
    }

    private final boolean jj_3_29() {
        return jj_3R_28();
    }

    private final boolean jj_3_3() {
        Token token = this.jj_scanpos;
        if (jj_scan_token(48)) {
            this.jj_scanpos = token;
        }
        if (jj_scan_token(34) || jj_3R_29()) {
            return true;
        }
        Token token2 = this.jj_scanpos;
        if (jj_3R_30()) {
            this.jj_scanpos = token2;
        }
        return jj_scan_token(78);
    }

    private final boolean jj_3_30() {
        return jj_scan_token(30) || jj_scan_token(72) || jj_scan_token(69) || jj_scan_token(89) || jj_3R_39() || jj_scan_token(73) || jj_3R_45();
    }

    private final boolean jj_3_31() {
        return jj_3R_41() || jj_3R_32() || jj_scan_token(69);
    }

    private final boolean jj_3_4() {
        return jj_scan_token(79) || jj_3R_31();
    }

    private final boolean jj_3_5() {
        return jj_3R_32() || jj_scan_token(69);
    }

    private final boolean jj_3_6() {
        return jj_scan_token(76) || jj_scan_token(77);
    }

    private final boolean jj_3_7() {
        return jj_scan_token(80) || jj_scan_token(69);
    }

    private final boolean jj_3_8() {
        return jj_3R_33() || jj_3R_34();
    }

    private final boolean jj_3_9() {
        return jj_3R_35();
    }

    private final Token jj_consume_token(int i2) {
        Token token = this.token;
        Token token2 = token.next;
        if (token2 == null) {
            token2 = this.token_source.getNextToken();
            token.next = token2;
        }
        this.token = token2;
        this.jj_ntk = -1;
        Token token3 = this.token;
        if (token3.kind == i2) {
            return token3;
        }
        this.token = token;
        throw generateParseException();
    }

    private final int jj_ntk() {
        Token token = this.token;
        Token token2 = token.next;
        this.jj_nt = token2;
        if (token2 == null) {
            token2 = this.token_source.getNextToken();
            token.next = token2;
        }
        int i2 = token2.kind;
        this.jj_ntk = i2;
        return i2;
    }

    private final boolean jj_scan_token(int i2) {
        Token token = this.jj_scanpos;
        if (token == this.jj_lastpos) {
            this.jj_la--;
            Token token2 = token.next;
            if (token2 == null) {
                token2 = this.token_source.getNextToken();
                token.next = token2;
            }
            this.jj_scanpos = token2;
            this.jj_lastpos = token2;
        } else {
            this.jj_scanpos = token.next;
        }
        Token token3 = this.jj_scanpos;
        if (token3.kind != i2) {
            return true;
        }
        if (this.jj_la == 0 && token3 == this.jj_lastpos) {
            throw this.jj_ls;
        }
        return false;
    }

    public static void main(String[] strArr) {
        boolean z;
        int i2 = 0;
        if (strArr[0].equals("-p")) {
            i2 = 1;
            z = true;
        } else {
            z = false;
        }
        while (i2 < strArr.length) {
            Parser parser = new Parser(new FileReader(strArr[i2]));
            parser.setRetainComments(true);
            while (!parser.Line()) {
                if (z) {
                    System.out.println(parser.popNode());
                }
            }
            i2++;
        }
    }

    public final void AdditiveExpression() {
        Token jj_consume_token;
        MultiplicativeExpression();
        while (true) {
            int i2 = this.jj_ntk;
            if (i2 == -1) {
                i2 = jj_ntk();
            }
            if (i2 != 102 && i2 != 103) {
                return;
            }
            int i3 = this.jj_ntk;
            if (i3 == -1) {
                i3 = jj_ntk();
            }
            if (i3 == 102) {
                jj_consume_token = jj_consume_token(102);
            } else {
                if (i3 != 103) {
                    jj_consume_token(-1);
                    throw new ParseException();
                }
                jj_consume_token = jj_consume_token(103);
            }
            MultiplicativeExpression();
            BSHBinaryExpression bSHBinaryExpression = new BSHBinaryExpression(15);
            boolean z = true;
            this.jjtree.openNodeScope(bSHBinaryExpression);
            jjtreeOpenNodeScope(bSHBinaryExpression);
            try {
                this.jjtree.closeNodeScope(bSHBinaryExpression, 2);
                z = false;
                jjtreeCloseNodeScope(bSHBinaryExpression);
                bSHBinaryExpression.kind = jj_consume_token.kind;
            } catch (Throwable th) {
                if (z) {
                    this.jjtree.closeNodeScope(bSHBinaryExpression, 2);
                    jjtreeCloseNodeScope(bSHBinaryExpression);
                }
                throw th;
            }
        }
    }

    public final void AllocationExpression() {
        boolean z;
        BSHAllocationExpression bSHAllocationExpression = new BSHAllocationExpression(23);
        this.jjtree.openNodeScope(bSHAllocationExpression);
        jjtreeOpenNodeScope(bSHAllocationExpression);
        try {
            if (jj_2_18(2)) {
                jj_consume_token(40);
                PrimitiveType();
            } else {
                int i2 = this.jj_ntk;
                if (i2 == -1) {
                    i2 = jj_ntk();
                }
                if (i2 != 40) {
                    jj_consume_token(-1);
                    throw new ParseException();
                }
                jj_consume_token(40);
                AmbiguousName();
                int i3 = this.jj_ntk;
                if (i3 == -1) {
                    i3 = jj_ntk();
                }
                if (i3 == 72) {
                    Arguments();
                    if (jj_2_17(2)) {
                        Block();
                    }
                    this.jjtree.closeNodeScope((Node) bSHAllocationExpression, true);
                    jjtreeCloseNodeScope(bSHAllocationExpression);
                }
                if (i3 != 76) {
                    jj_consume_token(-1);
                    throw new ParseException();
                }
            }
            ArrayDimensions();
            this.jjtree.closeNodeScope((Node) bSHAllocationExpression, true);
            jjtreeCloseNodeScope(bSHAllocationExpression);
        } catch (Throwable th) {
            try {
                this.jjtree.clearNodeScope(bSHAllocationExpression);
                z = false;
            } catch (Throwable th2) {
                th = th2;
                z = true;
            }
            try {
                if (th instanceof RuntimeException) {
                    throw ((RuntimeException) th);
                }
                if (!(th instanceof ParseException)) {
                    throw ((Error) th);
                }
                throw ((ParseException) th);
            } catch (Throwable th3) {
                th = th3;
                if (z) {
                    this.jjtree.closeNodeScope((Node) bSHAllocationExpression, true);
                    jjtreeCloseNodeScope(bSHAllocationExpression);
                }
                throw th;
            }
        }
    }

    public final void AmbiguousName() {
        boolean z;
        StringBuffer stringBuffer;
        BSHAmbiguousName bSHAmbiguousName = new BSHAmbiguousName(12);
        this.jjtree.openNodeScope(bSHAmbiguousName);
        jjtreeOpenNodeScope(bSHAmbiguousName);
        try {
            stringBuffer = new StringBuffer(jj_consume_token(69).image);
            while (jj_2_7(2)) {
                jj_consume_token(80);
                Token jj_consume_token = jj_consume_token(69);
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append(".");
                stringBuffer2.append(jj_consume_token.image);
                stringBuffer.append(stringBuffer2.toString());
            }
            this.jjtree.closeNodeScope((Node) bSHAmbiguousName, true);
        } catch (Throwable th) {
            th = th;
            z = true;
        }
        try {
            jjtreeCloseNodeScope(bSHAmbiguousName);
            bSHAmbiguousName.text = stringBuffer.toString();
        } catch (Throwable th2) {
            th = th2;
            z = false;
            if (z) {
                this.jjtree.closeNodeScope((Node) bSHAmbiguousName, true);
                jjtreeCloseNodeScope(bSHAmbiguousName);
            }
            throw th;
        }
    }

    public final void AndExpression() {
        Token jj_consume_token;
        EqualityExpression();
        while (true) {
            int i2 = this.jj_ntk;
            if (i2 == -1) {
                i2 = jj_ntk();
            }
            if (i2 != 106 && i2 != 107) {
                return;
            }
            int i3 = this.jj_ntk;
            if (i3 == -1) {
                i3 = jj_ntk();
            }
            if (i3 == 106) {
                jj_consume_token = jj_consume_token(106);
            } else {
                if (i3 != 107) {
                    jj_consume_token(-1);
                    throw new ParseException();
                }
                jj_consume_token = jj_consume_token(107);
            }
            EqualityExpression();
            BSHBinaryExpression bSHBinaryExpression = new BSHBinaryExpression(15);
            boolean z = true;
            this.jjtree.openNodeScope(bSHBinaryExpression);
            jjtreeOpenNodeScope(bSHBinaryExpression);
            try {
                this.jjtree.closeNodeScope(bSHBinaryExpression, 2);
                z = false;
                jjtreeCloseNodeScope(bSHBinaryExpression);
                bSHBinaryExpression.kind = jj_consume_token.kind;
            } catch (Throwable th) {
                if (z) {
                    this.jjtree.closeNodeScope(bSHBinaryExpression, 2);
                    jjtreeCloseNodeScope(bSHBinaryExpression);
                }
                throw th;
            }
        }
    }

    public final void ArgumentList() {
        while (true) {
            Expression();
            int i2 = this.jj_ntk;
            if (i2 == -1) {
                i2 = jj_ntk();
            }
            if (i2 != 79) {
                return;
            } else {
                jj_consume_token(79);
            }
        }
    }

    public final void Arguments() {
        boolean z;
        BSHArguments bSHArguments = new BSHArguments(22);
        this.jjtree.openNodeScope(bSHArguments);
        jjtreeOpenNodeScope(bSHArguments);
        try {
            jj_consume_token(72);
            int i2 = this.jj_ntk;
            if (i2 == -1) {
                i2 = jj_ntk();
            }
            switch (i2) {
                case 11:
                case 14:
                case 17:
                case 22:
                case 26:
                case 29:
                case 36:
                case 38:
                case 40:
                case 41:
                case 47:
                case 55:
                case 57:
                case 60:
                case 64:
                case 66:
                case 67:
                case 69:
                case 72:
                case 86:
                case 87:
                case 100:
                case 101:
                case 102:
                case 103:
                    ArgumentList();
                    break;
            }
            jj_consume_token(73);
            this.jjtree.closeNodeScope((Node) bSHArguments, true);
            jjtreeCloseNodeScope(bSHArguments);
        } catch (Throwable th) {
            try {
                this.jjtree.clearNodeScope(bSHArguments);
                z = false;
            } catch (Throwable th2) {
                th = th2;
                z = true;
            }
            try {
                if (th instanceof RuntimeException) {
                    throw ((RuntimeException) th);
                }
                if (!(th instanceof ParseException)) {
                    throw ((Error) th);
                }
                throw ((ParseException) th);
            } catch (Throwable th3) {
                th = th3;
                if (z) {
                    this.jjtree.closeNodeScope((Node) bSHArguments, true);
                    jjtreeCloseNodeScope(bSHArguments);
                }
                throw th;
            }
        }
    }

    public final void ArrayDimensions() {
        boolean z;
        int i2;
        BSHArrayDimensions bSHArrayDimensions = new BSHArrayDimensions(24);
        this.jjtree.openNodeScope(bSHArrayDimensions);
        jjtreeOpenNodeScope(bSHArrayDimensions);
        try {
            if (jj_2_21(2)) {
                do {
                    jj_consume_token(76);
                    Expression();
                    jj_consume_token(77);
                    bSHArrayDimensions.addDefinedDimension();
                } while (jj_2_19(2));
                while (jj_2_20(2)) {
                    jj_consume_token(76);
                    jj_consume_token(77);
                    bSHArrayDimensions.addUndefinedDimension();
                }
            } else {
                int i3 = this.jj_ntk;
                if (i3 == -1) {
                    i3 = jj_ntk();
                }
                if (i3 != 76) {
                    jj_consume_token(-1);
                    throw new ParseException();
                }
                do {
                    jj_consume_token(76);
                    jj_consume_token(77);
                    bSHArrayDimensions.addUndefinedDimension();
                    i2 = this.jj_ntk;
                    if (i2 == -1) {
                        i2 = jj_ntk();
                    }
                } while (i2 == 76);
                ArrayInitializer();
            }
            this.jjtree.closeNodeScope((Node) bSHArrayDimensions, true);
            jjtreeCloseNodeScope(bSHArrayDimensions);
        } catch (Throwable th) {
            try {
                this.jjtree.clearNodeScope(bSHArrayDimensions);
                z = false;
            } catch (Throwable th2) {
                th = th2;
                z = true;
            }
            try {
                if (th instanceof RuntimeException) {
                    throw ((RuntimeException) th);
                }
                if (!(th instanceof ParseException)) {
                    throw ((Error) th);
                }
                throw ((ParseException) th);
            } catch (Throwable th3) {
                th = th3;
                if (z) {
                    this.jjtree.closeNodeScope((Node) bSHArrayDimensions, true);
                    jjtreeCloseNodeScope(bSHArrayDimensions);
                }
                throw th;
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public final void ArrayInitializer() {
        boolean z;
        BSHArrayInitializer bSHArrayInitializer = new BSHArrayInitializer(6);
        this.jjtree.openNodeScope(bSHArrayInitializer);
        jjtreeOpenNodeScope(bSHArrayInitializer);
        try {
            jj_consume_token(74);
            int i2 = this.jj_ntk;
            if (i2 == -1) {
                i2 = jj_ntk();
            }
            switch (i2) {
                case 11:
                case 14:
                case 17:
                case 22:
                case 26:
                case 29:
                case 36:
                case 38:
                case 40:
                case 41:
                case 47:
                case 55:
                case 57:
                case 60:
                case 64:
                case 66:
                case 67:
                case 69:
                case 72:
                case 74:
                case 86:
                case 87:
                case 100:
                case 101:
                case 102:
                case 103:
                    while (true) {
                        VariableInitializer();
                        if (!jj_2_4(2)) {
                            break;
                        } else {
                            jj_consume_token(79);
                        }
                    }
            }
            int i3 = this.jj_ntk;
            if (i3 == -1) {
                i3 = jj_ntk();
            }
            if (i3 == 79) {
                jj_consume_token(79);
            }
            jj_consume_token(75);
            this.jjtree.closeNodeScope((Node) bSHArrayInitializer, true);
            jjtreeCloseNodeScope(bSHArrayInitializer);
        } catch (Throwable th) {
            try {
                this.jjtree.clearNodeScope(bSHArrayInitializer);
                z = false;
            } catch (Throwable th2) {
                th = th2;
                z = true;
            }
            try {
                if (th instanceof RuntimeException) {
                    throw ((RuntimeException) th);
                }
                if (!(th instanceof ParseException)) {
                    throw ((Error) th);
                }
                throw ((ParseException) th);
            } catch (Throwable th3) {
                th = th3;
                if (z) {
                    this.jjtree.closeNodeScope((Node) bSHArrayInitializer, true);
                    jjtreeCloseNodeScope(bSHArrayInitializer);
                }
                throw th;
            }
        }
    }

    public final void Assignment() {
        boolean z;
        BSHAssignment bSHAssignment = new BSHAssignment(13);
        this.jjtree.openNodeScope(bSHAssignment);
        jjtreeOpenNodeScope(bSHAssignment);
        try {
            PrimaryExpression();
            bSHAssignment.operator = AssignmentOperator();
            Expression();
            this.jjtree.closeNodeScope((Node) bSHAssignment, true);
            jjtreeCloseNodeScope(bSHAssignment);
        } catch (Throwable th) {
            try {
                this.jjtree.clearNodeScope(bSHAssignment);
                z = false;
            } catch (Throwable th2) {
                th = th2;
                z = true;
            }
            try {
                if (th instanceof RuntimeException) {
                    throw ((RuntimeException) th);
                }
                if (!(th instanceof ParseException)) {
                    throw ((Error) th);
                }
                throw ((ParseException) th);
            } catch (Throwable th3) {
                th = th3;
                if (z) {
                    this.jjtree.closeNodeScope((Node) bSHAssignment, true);
                    jjtreeCloseNodeScope(bSHAssignment);
                }
                throw th;
            }
        }
    }

    public final int AssignmentOperator() {
        int i2;
        int i3 = this.jj_ntk;
        if (i3 == -1) {
            i3 = jj_ntk();
        }
        int i4 = 81;
        if (i3 != 81) {
            i4 = 124;
            if (i3 != 124) {
                switch (i3) {
                    case 118:
                        i2 = 118;
                        break;
                    case 119:
                        i2 = 119;
                        break;
                    case 120:
                        i2 = 120;
                        break;
                    case 121:
                        i2 = 121;
                        break;
                    case 122:
                        i2 = 122;
                        break;
                    default:
                        switch (i3) {
                            case 126:
                                i2 = 126;
                                break;
                            case 127:
                                i2 = 127;
                                break;
                            case 128:
                                i2 = 128;
                                break;
                            case 129:
                                i2 = 129;
                                break;
                            case 130:
                                i2 = 130;
                                break;
                            case 131:
                                i2 = 131;
                                break;
                            case 132:
                                i2 = 132;
                                break;
                            case 133:
                                i2 = 133;
                                break;
                            default:
                                jj_consume_token(-1);
                                throw new ParseException();
                        }
                }
                jj_consume_token(i2);
                return getToken(0).kind;
            }
        }
        jj_consume_token(i4);
        return getToken(0).kind;
    }

    public final void Block() {
        boolean z;
        BSHBlock bSHBlock = new BSHBlock(25);
        this.jjtree.openNodeScope(bSHBlock);
        jjtreeOpenNodeScope(bSHBlock);
        try {
            jj_consume_token(74);
            while (jj_2_23(1)) {
                BlockStatement();
            }
            jj_consume_token(75);
            this.jjtree.closeNodeScope((Node) bSHBlock, true);
            jjtreeCloseNodeScope(bSHBlock);
        } catch (Throwable th) {
            try {
                this.jjtree.clearNodeScope(bSHBlock);
                z = false;
            } catch (Throwable th2) {
                th = th2;
                z = true;
            }
            try {
                if (th instanceof RuntimeException) {
                    throw ((RuntimeException) th);
                }
                if (!(th instanceof ParseException)) {
                    throw ((Error) th);
                }
                throw ((ParseException) th);
            } catch (Throwable th3) {
                th = th3;
                if (z) {
                    this.jjtree.closeNodeScope((Node) bSHBlock, true);
                    jjtreeCloseNodeScope(bSHBlock);
                }
                throw th;
            }
        }
    }

    public final void BlockStatement() {
        if (jj_2_24(ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED)) {
            ClassDeclaration();
            return;
        }
        if (jj_2_25(ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED) || jj_2_26(ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED)) {
            MethodDeclaration();
            return;
        }
        if (jj_2_27(ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED)) {
            TypedVariableDeclaration();
            jj_consume_token(78);
            return;
        }
        if (jj_2_28(1)) {
            Statement();
            return;
        }
        int i2 = this.jj_ntk;
        if (i2 == -1) {
            i2 = jj_ntk();
        }
        if (i2 != 34) {
            if (i2 == 42) {
                PackageDeclaration();
                return;
            } else if (i2 != 48) {
                if (i2 == 68) {
                    FormalComment();
                    return;
                } else {
                    jj_consume_token(-1);
                    throw new ParseException();
                }
            }
        }
        ImportDeclaration();
    }

    public final boolean BooleanLiteral() {
        int i2 = this.jj_ntk;
        if (i2 == -1) {
            i2 = jj_ntk();
        }
        if (i2 == 26) {
            jj_consume_token(26);
            return false;
        }
        if (i2 == 55) {
            jj_consume_token(55);
            return true;
        }
        jj_consume_token(-1);
        throw new ParseException();
    }

    public final void BreakStatement() {
        boolean z;
        BSHReturnStatement bSHReturnStatement = new BSHReturnStatement(35);
        this.jjtree.openNodeScope(bSHReturnStatement);
        jjtreeOpenNodeScope(bSHReturnStatement);
        try {
            jj_consume_token(12);
            int i2 = this.jj_ntk;
            if (i2 == -1) {
                i2 = jj_ntk();
            }
            if (i2 == 69) {
                jj_consume_token(69);
            }
            jj_consume_token(78);
            this.jjtree.closeNodeScope((Node) bSHReturnStatement, true);
            z = false;
        } catch (Throwable th) {
            th = th;
            z = true;
        }
        try {
            jjtreeCloseNodeScope(bSHReturnStatement);
            bSHReturnStatement.kind = 12;
        } catch (Throwable th2) {
            th = th2;
            if (z) {
                this.jjtree.closeNodeScope((Node) bSHReturnStatement, true);
                jjtreeCloseNodeScope(bSHReturnStatement);
            }
            throw th;
        }
    }

    public final void CastExpression() {
        boolean z;
        BSHCastExpression bSHCastExpression = new BSHCastExpression(17);
        this.jjtree.openNodeScope(bSHCastExpression);
        jjtreeOpenNodeScope(bSHCastExpression);
        try {
            if (jj_2_13(ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED)) {
                jj_consume_token(72);
                Type();
                jj_consume_token(73);
                UnaryExpression();
            } else {
                int i2 = this.jj_ntk;
                if (i2 == -1) {
                    i2 = jj_ntk();
                }
                if (i2 != 72) {
                    jj_consume_token(-1);
                    throw new ParseException();
                }
                jj_consume_token(72);
                Type();
                jj_consume_token(73);
                UnaryExpressionNotPlusMinus();
            }
            this.jjtree.closeNodeScope((Node) bSHCastExpression, true);
            jjtreeCloseNodeScope(bSHCastExpression);
        } catch (Throwable th) {
            try {
                this.jjtree.clearNodeScope(bSHCastExpression);
                z = false;
            } catch (Throwable th2) {
                th = th2;
                z = true;
            }
            try {
                if (th instanceof RuntimeException) {
                    throw ((RuntimeException) th);
                }
                if (!(th instanceof ParseException)) {
                    throw ((Error) th);
                }
                throw ((ParseException) th);
            } catch (Throwable th3) {
                th = th3;
                if (z) {
                    this.jjtree.closeNodeScope((Node) bSHCastExpression, true);
                    jjtreeCloseNodeScope(bSHCastExpression);
                }
                throw th;
            }
        }
    }

    public final void CastLookahead() {
        int i2 = 72;
        if (jj_2_10(2)) {
            jj_consume_token(72);
            PrimitiveType();
            return;
        }
        if (jj_2_11(ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED)) {
            jj_consume_token(72);
            AmbiguousName();
            jj_consume_token(76);
            jj_consume_token(77);
            return;
        }
        int i3 = this.jj_ntk;
        if (i3 == -1) {
            i3 = jj_ntk();
        }
        if (i3 != 72) {
            jj_consume_token(-1);
            throw new ParseException();
        }
        jj_consume_token(72);
        AmbiguousName();
        jj_consume_token(73);
        int i4 = this.jj_ntk;
        if (i4 == -1) {
            i4 = jj_ntk();
        }
        if (i4 != 26 && i4 != 55 && i4 != 57 && i4 != 60 && i4 != 64) {
            if (i4 == 69) {
                jj_consume_token(69);
                return;
            }
            if (i4 != 72) {
                i2 = 40;
                if (i4 != 40) {
                    if (i4 != 41 && i4 != 66 && i4 != 67) {
                        i2 = 86;
                        if (i4 != 86) {
                            i2 = 87;
                            if (i4 != 87) {
                                jj_consume_token(-1);
                                throw new ParseException();
                            }
                        }
                    }
                }
            }
            jj_consume_token(i2);
            return;
        }
        Literal();
    }

    public final void ClassDeclaration() {
        boolean z;
        Modifiers Modifiers;
        Token jj_consume_token;
        BSHClassDeclaration bSHClassDeclaration = new BSHClassDeclaration(1);
        this.jjtree.openNodeScope(bSHClassDeclaration);
        jjtreeOpenNodeScope(bSHClassDeclaration);
        boolean z2 = false;
        try {
            Modifiers = Modifiers(0, false);
            int i2 = this.jj_ntk;
            if (i2 == -1) {
                i2 = jj_ntk();
            }
            if (i2 == 13) {
                jj_consume_token(13);
            } else {
                if (i2 != 37) {
                    jj_consume_token(-1);
                    throw new ParseException();
                }
                jj_consume_token(37);
                bSHClassDeclaration.isInterface = true;
            }
            jj_consume_token = jj_consume_token(69);
            int i3 = this.jj_ntk;
            if (i3 == -1) {
                i3 = jj_ntk();
            }
            if (i3 == 25) {
                jj_consume_token(25);
                AmbiguousName();
                bSHClassDeclaration.extend = true;
            }
            int i4 = this.jj_ntk;
            if (i4 == -1) {
                i4 = jj_ntk();
            }
            if (i4 == 33) {
                jj_consume_token(33);
                bSHClassDeclaration.numInterfaces = NameList();
            }
            Block();
            this.jjtree.closeNodeScope((Node) bSHClassDeclaration, true);
        } catch (Throwable th) {
            th = th;
            z = true;
        }
        try {
            jjtreeCloseNodeScope(bSHClassDeclaration);
            bSHClassDeclaration.modifiers = Modifiers;
            bSHClassDeclaration.name = jj_consume_token.image;
        } catch (Throwable th2) {
            th = th2;
            z = false;
            try {
                if (z) {
                    this.jjtree.clearNodeScope(bSHClassDeclaration);
                } else {
                    this.jjtree.popNode();
                    z2 = z;
                }
                try {
                    z = th instanceof RuntimeException;
                    if (z) {
                        throw ((RuntimeException) th);
                    }
                    if (!(th instanceof ParseException)) {
                        throw ((Error) th);
                    }
                    throw ((ParseException) th);
                } catch (Throwable th3) {
                    z = z2;
                    th = th3;
                    if (z) {
                        this.jjtree.closeNodeScope((Node) bSHClassDeclaration, true);
                        jjtreeCloseNodeScope(bSHClassDeclaration);
                    }
                    throw th;
                }
            } catch (Throwable th4) {
                th = th4;
            }
        }
    }

    public final void ConditionalAndExpression() {
        Token jj_consume_token;
        InclusiveOrExpression();
        while (true) {
            int i2 = this.jj_ntk;
            if (i2 == -1) {
                i2 = jj_ntk();
            }
            if (i2 != 98 && i2 != 99) {
                return;
            }
            int i3 = this.jj_ntk;
            if (i3 == -1) {
                i3 = jj_ntk();
            }
            if (i3 == 98) {
                jj_consume_token = jj_consume_token(98);
            } else {
                if (i3 != 99) {
                    jj_consume_token(-1);
                    throw new ParseException();
                }
                jj_consume_token = jj_consume_token(99);
            }
            InclusiveOrExpression();
            BSHBinaryExpression bSHBinaryExpression = new BSHBinaryExpression(15);
            boolean z = true;
            this.jjtree.openNodeScope(bSHBinaryExpression);
            jjtreeOpenNodeScope(bSHBinaryExpression);
            try {
                this.jjtree.closeNodeScope(bSHBinaryExpression, 2);
                z = false;
                jjtreeCloseNodeScope(bSHBinaryExpression);
                bSHBinaryExpression.kind = jj_consume_token.kind;
            } catch (Throwable th) {
                if (z) {
                    this.jjtree.closeNodeScope(bSHBinaryExpression, 2);
                    jjtreeCloseNodeScope(bSHBinaryExpression);
                }
                throw th;
            }
        }
    }

    public final void ConditionalExpression() {
        ConditionalOrExpression();
        int i2 = this.jj_ntk;
        if (i2 == -1) {
            i2 = jj_ntk();
        }
        if (i2 != 88) {
            return;
        }
        jj_consume_token(88);
        Expression();
        jj_consume_token(89);
        BSHTernaryExpression bSHTernaryExpression = new BSHTernaryExpression(14);
        this.jjtree.openNodeScope(bSHTernaryExpression);
        jjtreeOpenNodeScope(bSHTernaryExpression);
        try {
            ConditionalExpression();
            this.jjtree.closeNodeScope(bSHTernaryExpression, 3);
            jjtreeCloseNodeScope(bSHTernaryExpression);
        } catch (Throwable th) {
            try {
                this.jjtree.clearNodeScope(bSHTernaryExpression);
                if (th instanceof RuntimeException) {
                    throw ((RuntimeException) th);
                }
                if (!(th instanceof ParseException)) {
                    throw ((Error) th);
                }
                throw ((ParseException) th);
            } catch (Throwable th2) {
                if (1 != 0) {
                    this.jjtree.closeNodeScope(bSHTernaryExpression, 3);
                    jjtreeCloseNodeScope(bSHTernaryExpression);
                }
                throw th2;
            }
        }
    }

    public final void ConditionalOrExpression() {
        Token jj_consume_token;
        ConditionalAndExpression();
        while (true) {
            int i2 = this.jj_ntk;
            if (i2 == -1) {
                i2 = jj_ntk();
            }
            if (i2 != 96 && i2 != 97) {
                return;
            }
            int i3 = this.jj_ntk;
            if (i3 == -1) {
                i3 = jj_ntk();
            }
            if (i3 == 96) {
                jj_consume_token = jj_consume_token(96);
            } else {
                if (i3 != 97) {
                    jj_consume_token(-1);
                    throw new ParseException();
                }
                jj_consume_token = jj_consume_token(97);
            }
            ConditionalAndExpression();
            BSHBinaryExpression bSHBinaryExpression = new BSHBinaryExpression(15);
            boolean z = true;
            this.jjtree.openNodeScope(bSHBinaryExpression);
            jjtreeOpenNodeScope(bSHBinaryExpression);
            try {
                this.jjtree.closeNodeScope(bSHBinaryExpression, 2);
                z = false;
                jjtreeCloseNodeScope(bSHBinaryExpression);
                bSHBinaryExpression.kind = jj_consume_token.kind;
            } catch (Throwable th) {
                if (z) {
                    this.jjtree.closeNodeScope(bSHBinaryExpression, 2);
                    jjtreeCloseNodeScope(bSHBinaryExpression);
                }
                throw th;
            }
        }
    }

    public final void ContinueStatement() {
        boolean z;
        BSHReturnStatement bSHReturnStatement = new BSHReturnStatement(35);
        this.jjtree.openNodeScope(bSHReturnStatement);
        jjtreeOpenNodeScope(bSHReturnStatement);
        try {
            jj_consume_token(19);
            int i2 = this.jj_ntk;
            if (i2 == -1) {
                i2 = jj_ntk();
            }
            if (i2 == 69) {
                jj_consume_token(69);
            }
            jj_consume_token(78);
            this.jjtree.closeNodeScope((Node) bSHReturnStatement, true);
            z = false;
        } catch (Throwable th) {
            th = th;
            z = true;
        }
        try {
            jjtreeCloseNodeScope(bSHReturnStatement);
            bSHReturnStatement.kind = 19;
        } catch (Throwable th2) {
            th = th2;
            if (z) {
                this.jjtree.closeNodeScope((Node) bSHReturnStatement, true);
                jjtreeCloseNodeScope(bSHReturnStatement);
            }
            throw th;
        }
    }

    public final void DoStatement() {
        boolean z;
        BSHWhileStatement bSHWhileStatement = new BSHWhileStatement(30);
        this.jjtree.openNodeScope(bSHWhileStatement);
        jjtreeOpenNodeScope(bSHWhileStatement);
        boolean z2 = false;
        try {
            jj_consume_token(21);
            Statement();
            jj_consume_token(59);
            jj_consume_token(72);
            Expression();
            jj_consume_token(73);
            jj_consume_token(78);
            this.jjtree.closeNodeScope((Node) bSHWhileStatement, true);
        } catch (Throwable th) {
            th = th;
            z = true;
        }
        try {
            jjtreeCloseNodeScope(bSHWhileStatement);
            bSHWhileStatement.isDoStatement = true;
        } catch (Throwable th2) {
            th = th2;
            z = false;
            try {
                if (z) {
                    this.jjtree.clearNodeScope(bSHWhileStatement);
                } else {
                    this.jjtree.popNode();
                    z2 = z;
                }
                try {
                    z = th instanceof RuntimeException;
                    if (z) {
                        throw ((RuntimeException) th);
                    }
                    if (!(th instanceof ParseException)) {
                        throw ((Error) th);
                    }
                    throw ((ParseException) th);
                } catch (Throwable th3) {
                    th = th3;
                    z = z2;
                    if (z) {
                        this.jjtree.closeNodeScope((Node) bSHWhileStatement, true);
                        jjtreeCloseNodeScope(bSHWhileStatement);
                    }
                    throw th;
                }
            } catch (Throwable th4) {
                th = th4;
            }
        }
    }

    public final void EmptyStatement() {
        jj_consume_token(78);
    }

    public final void EnhancedForStatement() {
        boolean z;
        String str;
        BSHEnhancedForStatement bSHEnhancedForStatement = new BSHEnhancedForStatement(32);
        this.jjtree.openNodeScope(bSHEnhancedForStatement);
        jjtreeOpenNodeScope(bSHEnhancedForStatement);
        boolean z2 = false;
        try {
            try {
                if (jj_2_30(4)) {
                    jj_consume_token(30);
                    jj_consume_token(72);
                    Token jj_consume_token = jj_consume_token(69);
                    jj_consume_token(89);
                    Expression();
                    jj_consume_token(73);
                    Statement();
                    this.jjtree.closeNodeScope((Node) bSHEnhancedForStatement, true);
                    jjtreeCloseNodeScope(bSHEnhancedForStatement);
                    str = jj_consume_token.image;
                } else {
                    int i2 = this.jj_ntk;
                    if (i2 == -1) {
                        i2 = jj_ntk();
                    }
                    if (i2 != 30) {
                        jj_consume_token(-1);
                        throw new ParseException();
                    }
                    jj_consume_token(30);
                    jj_consume_token(72);
                    Type();
                    Token jj_consume_token2 = jj_consume_token(69);
                    jj_consume_token(89);
                    Expression();
                    jj_consume_token(73);
                    Statement();
                    this.jjtree.closeNodeScope((Node) bSHEnhancedForStatement, true);
                    jjtreeCloseNodeScope(bSHEnhancedForStatement);
                    str = jj_consume_token2.image;
                }
                bSHEnhancedForStatement.varName = str;
            } catch (Throwable th) {
                th = th;
                z = false;
                try {
                    if (z) {
                        this.jjtree.clearNodeScope(bSHEnhancedForStatement);
                    } else {
                        this.jjtree.popNode();
                        z2 = z;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
                try {
                    z = th instanceof RuntimeException;
                    if (z) {
                        throw ((RuntimeException) th);
                    }
                    if (!(th instanceof ParseException)) {
                        throw ((Error) th);
                    }
                    throw ((ParseException) th);
                } catch (Throwable th3) {
                    th = th3;
                    z = z2;
                    if (z) {
                        this.jjtree.closeNodeScope((Node) bSHEnhancedForStatement, true);
                        jjtreeCloseNodeScope(bSHEnhancedForStatement);
                    }
                    throw th;
                }
            }
        } catch (Throwable th4) {
            th = th4;
            z = true;
        }
    }

    public final void EqualityExpression() {
        Token jj_consume_token;
        InstanceOfExpression();
        while (true) {
            int i2 = this.jj_ntk;
            if (i2 == -1) {
                i2 = jj_ntk();
            }
            if (i2 != 90 && i2 != 95) {
                return;
            }
            int i3 = this.jj_ntk;
            if (i3 == -1) {
                i3 = jj_ntk();
            }
            if (i3 == 90) {
                jj_consume_token = jj_consume_token(90);
            } else {
                if (i3 != 95) {
                    jj_consume_token(-1);
                    throw new ParseException();
                }
                jj_consume_token = jj_consume_token(95);
            }
            InstanceOfExpression();
            BSHBinaryExpression bSHBinaryExpression = new BSHBinaryExpression(15);
            boolean z = true;
            this.jjtree.openNodeScope(bSHBinaryExpression);
            jjtreeOpenNodeScope(bSHBinaryExpression);
            try {
                this.jjtree.closeNodeScope(bSHBinaryExpression, 2);
                z = false;
                jjtreeCloseNodeScope(bSHBinaryExpression);
                bSHBinaryExpression.kind = jj_consume_token.kind;
            } catch (Throwable th) {
                if (z) {
                    this.jjtree.closeNodeScope(bSHBinaryExpression, 2);
                    jjtreeCloseNodeScope(bSHBinaryExpression);
                }
                throw th;
            }
        }
    }

    public final void ExclusiveOrExpression() {
        AndExpression();
        while (true) {
            int i2 = this.jj_ntk;
            if (i2 == -1) {
                i2 = jj_ntk();
            }
            if (i2 != 110) {
                return;
            }
            Token jj_consume_token = jj_consume_token(110);
            AndExpression();
            BSHBinaryExpression bSHBinaryExpression = new BSHBinaryExpression(15);
            boolean z = true;
            this.jjtree.openNodeScope(bSHBinaryExpression);
            jjtreeOpenNodeScope(bSHBinaryExpression);
            try {
                this.jjtree.closeNodeScope(bSHBinaryExpression, 2);
                z = false;
                jjtreeCloseNodeScope(bSHBinaryExpression);
                bSHBinaryExpression.kind = jj_consume_token.kind;
            } catch (Throwable th) {
                if (z) {
                    this.jjtree.closeNodeScope(bSHBinaryExpression, 2);
                    jjtreeCloseNodeScope(bSHBinaryExpression);
                }
                throw th;
            }
        }
    }

    public final void Expression() {
        if (jj_2_8(ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED)) {
            Assignment();
            return;
        }
        int i2 = this.jj_ntk;
        if (i2 == -1) {
            i2 = jj_ntk();
        }
        switch (i2) {
            case 11:
            case 14:
            case 17:
            case 22:
            case 26:
            case 29:
            case 36:
            case 38:
            case 40:
            case 41:
            case 47:
            case 55:
            case 57:
            case 60:
            case 64:
            case 66:
            case 67:
            case 69:
            case 72:
            case 86:
            case 87:
            case 100:
            case 101:
            case 102:
            case 103:
                ConditionalExpression();
                return;
            default:
                jj_consume_token(-1);
                throw new ParseException();
        }
    }

    public final void ForInit() {
        if (jj_2_31(ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED)) {
            TypedVariableDeclaration();
            return;
        }
        int i2 = this.jj_ntk;
        if (i2 == -1) {
            i2 = jj_ntk();
        }
        switch (i2) {
            case 11:
            case 14:
            case 17:
            case 22:
            case 26:
            case 29:
            case 36:
            case 38:
            case 40:
            case 41:
            case 47:
            case 55:
            case 57:
            case 60:
            case 64:
            case 66:
            case 67:
            case 69:
            case 72:
            case 86:
            case 87:
            case 100:
            case 101:
            case 102:
            case 103:
                StatementExpressionList();
                return;
            default:
                jj_consume_token(-1);
                throw new ParseException();
        }
    }

    public final void ForStatement() {
        boolean z;
        BSHForStatement bSHForStatement = new BSHForStatement(31);
        this.jjtree.openNodeScope(bSHForStatement);
        jjtreeOpenNodeScope(bSHForStatement);
        try {
            jj_consume_token(30);
            jj_consume_token(72);
            int i2 = this.jj_ntk;
            if (i2 == -1) {
                i2 = jj_ntk();
            }
            switch (i2) {
                case 10:
                case 11:
                case 14:
                case 17:
                case 22:
                case 26:
                case 27:
                case 29:
                case 36:
                case 38:
                case 39:
                case 40:
                case 41:
                case 43:
                case 44:
                case 45:
                case 47:
                case 48:
                case 49:
                case 51:
                case 52:
                case 55:
                case 57:
                case 58:
                case 60:
                case 64:
                case 66:
                case 67:
                case 69:
                case 72:
                case 86:
                case 87:
                case 100:
                case 101:
                case 102:
                case 103:
                    ForInit();
                    bSHForStatement.hasForInit = true;
                    break;
            }
            jj_consume_token(78);
            int i3 = this.jj_ntk;
            if (i3 == -1) {
                i3 = jj_ntk();
            }
            switch (i3) {
                case 11:
                case 14:
                case 17:
                case 22:
                case 26:
                case 29:
                case 36:
                case 38:
                case 40:
                case 41:
                case 47:
                case 55:
                case 57:
                case 60:
                case 64:
                case 66:
                case 67:
                case 69:
                case 72:
                case 86:
                case 87:
                case 100:
                case 101:
                case 102:
                case 103:
                    Expression();
                    bSHForStatement.hasExpression = true;
                    break;
            }
            jj_consume_token(78);
            int i4 = this.jj_ntk;
            if (i4 == -1) {
                i4 = jj_ntk();
            }
            switch (i4) {
                case 11:
                case 14:
                case 17:
                case 22:
                case 26:
                case 29:
                case 36:
                case 38:
                case 40:
                case 41:
                case 47:
                case 55:
                case 57:
                case 60:
                case 64:
                case 66:
                case 67:
                case 69:
                case 72:
                case 86:
                case 87:
                case 100:
                case 101:
                case 102:
                case 103:
                    ForUpdate();
                    bSHForStatement.hasForUpdate = true;
                    break;
            }
            jj_consume_token(73);
            Statement();
            this.jjtree.closeNodeScope((Node) bSHForStatement, true);
            jjtreeCloseNodeScope(bSHForStatement);
        } catch (Throwable th) {
            try {
                this.jjtree.clearNodeScope(bSHForStatement);
                z = false;
                try {
                    if (th instanceof RuntimeException) {
                        throw ((RuntimeException) th);
                    }
                    if (!(th instanceof ParseException)) {
                        throw ((Error) th);
                    }
                    throw ((ParseException) th);
                } catch (Throwable th2) {
                    th = th2;
                    if (z) {
                        this.jjtree.closeNodeScope((Node) bSHForStatement, true);
                        jjtreeCloseNodeScope(bSHForStatement);
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                z = true;
            }
        }
    }

    public final void ForUpdate() {
        StatementExpressionList();
    }

    public final void FormalComment() {
        BSHFormalComment bSHFormalComment = new BSHFormalComment(26);
        this.jjtree.openNodeScope(bSHFormalComment);
        jjtreeOpenNodeScope(bSHFormalComment);
        boolean z = true;
        try {
            Token jj_consume_token = jj_consume_token(68);
            this.jjtree.closeNodeScope(bSHFormalComment, this.retainComments);
            z = false;
            jjtreeCloseNodeScope(bSHFormalComment);
            bSHFormalComment.text = jj_consume_token.image;
        } catch (Throwable th) {
            if (z) {
                this.jjtree.closeNodeScope(bSHFormalComment, this.retainComments);
                jjtreeCloseNodeScope(bSHFormalComment);
            }
            throw th;
        }
    }

    public final void FormalParameter() {
        boolean z;
        String str;
        BSHFormalParameter bSHFormalParameter = new BSHFormalParameter(8);
        this.jjtree.openNodeScope(bSHFormalParameter);
        jjtreeOpenNodeScope(bSHFormalParameter);
        boolean z2 = false;
        try {
            try {
                if (jj_2_5(2)) {
                    Type();
                    Token jj_consume_token = jj_consume_token(69);
                    this.jjtree.closeNodeScope((Node) bSHFormalParameter, true);
                    jjtreeCloseNodeScope(bSHFormalParameter);
                    str = jj_consume_token.image;
                } else {
                    int i2 = this.jj_ntk;
                    if (i2 == -1) {
                        i2 = jj_ntk();
                    }
                    if (i2 != 69) {
                        jj_consume_token(-1);
                        throw new ParseException();
                    }
                    Token jj_consume_token2 = jj_consume_token(69);
                    this.jjtree.closeNodeScope((Node) bSHFormalParameter, true);
                    jjtreeCloseNodeScope(bSHFormalParameter);
                    str = jj_consume_token2.image;
                }
                bSHFormalParameter.name = str;
            } catch (Throwable th) {
                th = th;
                z = false;
                try {
                    if (z) {
                        this.jjtree.clearNodeScope(bSHFormalParameter);
                    } else {
                        this.jjtree.popNode();
                        z2 = z;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
                try {
                    z = th instanceof RuntimeException;
                    if (z) {
                        throw ((RuntimeException) th);
                    }
                    if (!(th instanceof ParseException)) {
                        throw ((Error) th);
                    }
                    throw ((ParseException) th);
                } catch (Throwable th3) {
                    th = th3;
                    z = z2;
                    if (z) {
                        this.jjtree.closeNodeScope((Node) bSHFormalParameter, true);
                        jjtreeCloseNodeScope(bSHFormalParameter);
                    }
                    throw th;
                }
            }
        } catch (Throwable th4) {
            th = th4;
            z = true;
        }
    }

    public final void FormalParameters() {
        boolean z;
        BSHFormalParameters bSHFormalParameters = new BSHFormalParameters(7);
        this.jjtree.openNodeScope(bSHFormalParameters);
        jjtreeOpenNodeScope(bSHFormalParameters);
        try {
            jj_consume_token(72);
            int i2 = this.jj_ntk;
            if (i2 == -1) {
                i2 = jj_ntk();
            }
            if (i2 == 11 || i2 == 14 || i2 == 17 || i2 == 22 || i2 == 29 || i2 == 36 || i2 == 38 || i2 == 47 || i2 == 69) {
                while (true) {
                    FormalParameter();
                    int i3 = this.jj_ntk;
                    if (i3 == -1) {
                        i3 = jj_ntk();
                    }
                    if (i3 != 79) {
                        break;
                    } else {
                        jj_consume_token(79);
                    }
                }
            }
            jj_consume_token(73);
            this.jjtree.closeNodeScope((Node) bSHFormalParameters, true);
            jjtreeCloseNodeScope(bSHFormalParameters);
        } catch (Throwable th) {
            try {
                this.jjtree.clearNodeScope(bSHFormalParameters);
                z = false;
            } catch (Throwable th2) {
                th = th2;
                z = true;
            }
            try {
                if (th instanceof RuntimeException) {
                    throw ((RuntimeException) th);
                }
                if (!(th instanceof ParseException)) {
                    throw ((Error) th);
                }
                throw ((ParseException) th);
            } catch (Throwable th3) {
                th = th3;
                if (z) {
                    this.jjtree.closeNodeScope((Node) bSHFormalParameters, true);
                    jjtreeCloseNodeScope(bSHFormalParameters);
                }
                throw th;
            }
        }
    }

    public final void IfStatement() {
        boolean z;
        BSHIfStatement bSHIfStatement = new BSHIfStatement(29);
        this.jjtree.openNodeScope(bSHIfStatement);
        jjtreeOpenNodeScope(bSHIfStatement);
        try {
            jj_consume_token(32);
            jj_consume_token(72);
            Expression();
            jj_consume_token(73);
            Statement();
            int i2 = this.jj_ntk;
            if (i2 == -1) {
                i2 = jj_ntk();
            }
            if (i2 == 23) {
                jj_consume_token(23);
                Statement();
            }
            this.jjtree.closeNodeScope((Node) bSHIfStatement, true);
            jjtreeCloseNodeScope(bSHIfStatement);
        } catch (Throwable th) {
            try {
                this.jjtree.clearNodeScope(bSHIfStatement);
                z = false;
            } catch (Throwable th2) {
                th = th2;
                z = true;
            }
            try {
                if (th instanceof RuntimeException) {
                    throw ((RuntimeException) th);
                }
                if (!(th instanceof ParseException)) {
                    throw ((Error) th);
                }
                throw ((ParseException) th);
            } catch (Throwable th3) {
                th = th3;
                if (z) {
                    this.jjtree.closeNodeScope((Node) bSHIfStatement, true);
                    jjtreeCloseNodeScope(bSHIfStatement);
                }
                throw th;
            }
        }
    }

    public final void ImportDeclaration() {
        boolean z;
        BSHImportDeclaration bSHImportDeclaration = new BSHImportDeclaration(4);
        this.jjtree.openNodeScope(bSHImportDeclaration);
        jjtreeOpenNodeScope(bSHImportDeclaration);
        boolean z2 = false;
        try {
            try {
                if (!jj_2_3(3)) {
                    int i2 = this.jj_ntk;
                    if (i2 == -1) {
                        i2 = jj_ntk();
                    }
                    if (i2 != 34) {
                        jj_consume_token(-1);
                        throw new ParseException();
                    }
                    jj_consume_token(34);
                    jj_consume_token(104);
                    jj_consume_token(78);
                    this.jjtree.closeNodeScope((Node) bSHImportDeclaration, true);
                    jjtreeCloseNodeScope(bSHImportDeclaration);
                    bSHImportDeclaration.superImport = true;
                    return;
                }
                int i3 = this.jj_ntk;
                if (i3 == -1) {
                    i3 = jj_ntk();
                }
                Token token = null;
                Token jj_consume_token = i3 != 48 ? null : jj_consume_token(48);
                jj_consume_token(34);
                AmbiguousName();
                int i4 = this.jj_ntk;
                if (i4 == -1) {
                    i4 = jj_ntk();
                }
                if (i4 == 80) {
                    token = jj_consume_token(80);
                    jj_consume_token(104);
                }
                jj_consume_token(78);
                this.jjtree.closeNodeScope((Node) bSHImportDeclaration, true);
                jjtreeCloseNodeScope(bSHImportDeclaration);
                if (jj_consume_token != null) {
                    bSHImportDeclaration.staticImport = true;
                }
                if (token != null) {
                    bSHImportDeclaration.importPackage = true;
                }
            } catch (Throwable th) {
                th = th;
                z = false;
                try {
                    if (z) {
                        this.jjtree.clearNodeScope(bSHImportDeclaration);
                    } else {
                        this.jjtree.popNode();
                        z2 = z;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
                try {
                    z = th instanceof RuntimeException;
                    if (z) {
                        throw ((RuntimeException) th);
                    }
                    if (!(th instanceof ParseException)) {
                        throw ((Error) th);
                    }
                    throw ((ParseException) th);
                } catch (Throwable th3) {
                    th = th3;
                    z = z2;
                    if (z) {
                        this.jjtree.closeNodeScope((Node) bSHImportDeclaration, true);
                        jjtreeCloseNodeScope(bSHImportDeclaration);
                    }
                    throw th;
                }
            }
        } catch (Throwable th4) {
            th = th4;
            z = true;
        }
    }

    public final void InclusiveOrExpression() {
        Token jj_consume_token;
        ExclusiveOrExpression();
        while (true) {
            int i2 = this.jj_ntk;
            if (i2 == -1) {
                i2 = jj_ntk();
            }
            if (i2 != 108 && i2 != 109) {
                return;
            }
            int i3 = this.jj_ntk;
            if (i3 == -1) {
                i3 = jj_ntk();
            }
            if (i3 == 108) {
                jj_consume_token = jj_consume_token(108);
            } else {
                if (i3 != 109) {
                    jj_consume_token(-1);
                    throw new ParseException();
                }
                jj_consume_token = jj_consume_token(109);
            }
            ExclusiveOrExpression();
            BSHBinaryExpression bSHBinaryExpression = new BSHBinaryExpression(15);
            boolean z = true;
            this.jjtree.openNodeScope(bSHBinaryExpression);
            jjtreeOpenNodeScope(bSHBinaryExpression);
            try {
                this.jjtree.closeNodeScope(bSHBinaryExpression, 2);
                z = false;
                jjtreeCloseNodeScope(bSHBinaryExpression);
                bSHBinaryExpression.kind = jj_consume_token.kind;
            } catch (Throwable th) {
                if (z) {
                    this.jjtree.closeNodeScope(bSHBinaryExpression, 2);
                    jjtreeCloseNodeScope(bSHBinaryExpression);
                }
                throw th;
            }
        }
    }

    public final void InstanceOfExpression() {
        RelationalExpression();
        int i2 = this.jj_ntk;
        if (i2 == -1) {
            i2 = jj_ntk();
        }
        if (i2 != 35) {
            return;
        }
        Token jj_consume_token = jj_consume_token(35);
        Type();
        BSHBinaryExpression bSHBinaryExpression = new BSHBinaryExpression(15);
        boolean z = true;
        this.jjtree.openNodeScope(bSHBinaryExpression);
        jjtreeOpenNodeScope(bSHBinaryExpression);
        try {
            this.jjtree.closeNodeScope(bSHBinaryExpression, 2);
            z = false;
            jjtreeCloseNodeScope(bSHBinaryExpression);
            bSHBinaryExpression.kind = jj_consume_token.kind;
        } catch (Throwable th) {
            if (z) {
                this.jjtree.closeNodeScope(bSHBinaryExpression, 2);
                jjtreeCloseNodeScope(bSHBinaryExpression);
            }
            throw th;
        }
    }

    public final void LabeledStatement() {
        jj_consume_token(69);
        jj_consume_token(89);
        Statement();
    }

    public final boolean Line() {
        int i2 = this.jj_ntk;
        if (i2 == -1) {
            i2 = jj_ntk();
        }
        if (i2 == 0) {
            jj_consume_token(0);
            Interpreter.debug("End of File!");
            return true;
        }
        if (jj_2_1(1)) {
            BlockStatement();
            return false;
        }
        jj_consume_token(-1);
        throw new ParseException();
    }

    public final void Literal() {
        boolean z;
        int i2;
        Primitive primitive;
        Primitive primitive2;
        BSHLiteral bSHLiteral = new BSHLiteral(21);
        this.jjtree.openNodeScope(bSHLiteral);
        jjtreeOpenNodeScope(bSHLiteral);
        boolean z2 = false;
        try {
            i2 = this.jj_ntk;
            if (i2 == -1) {
                i2 = jj_ntk();
            }
            try {
            } catch (Throwable th) {
                th = th;
                z = false;
            }
        } catch (Throwable th2) {
            th = th2;
            z = true;
        }
        if (i2 != 26) {
            if (i2 == 41) {
                NullLiteral();
                this.jjtree.closeNodeScope((Node) bSHLiteral, true);
                jjtreeCloseNodeScope(bSHLiteral);
                primitive2 = Primitive.NULL;
            } else if (i2 != 55) {
                if (i2 != 57) {
                    if (i2 == 60) {
                        Token jj_consume_token = jj_consume_token(60);
                        this.jjtree.closeNodeScope((Node) bSHLiteral, true);
                        jjtreeCloseNodeScope(bSHLiteral);
                        String str = jj_consume_token.image;
                        char charAt = str.charAt(str.length() - 1);
                        if (charAt == 'l' || charAt == 'L') {
                            primitive = new Primitive(new Long(str.substring(0, str.length() - 1)).longValue());
                            bSHLiteral.value = primitive;
                        }
                        try {
                            bSHLiteral.value = new Primitive(Integer.decode(str).intValue());
                            return;
                        } catch (NumberFormatException unused) {
                            StringBuffer stringBuffer = new StringBuffer();
                            stringBuffer.append("Error or number too big for integer type: ");
                            stringBuffer.append(str);
                            throw createParseException(stringBuffer.toString());
                        }
                    }
                    if (i2 == 64) {
                        Token jj_consume_token2 = jj_consume_token(64);
                        this.jjtree.closeNodeScope((Node) bSHLiteral, true);
                        jjtreeCloseNodeScope(bSHLiteral);
                        String str2 = jj_consume_token2.image;
                        char charAt2 = str2.charAt(str2.length() - 1);
                        if (charAt2 != 'f' && charAt2 != 'F') {
                            if (charAt2 == 'd' || charAt2 == 'D') {
                                str2 = str2.substring(0, str2.length() - 1);
                            }
                            primitive = new Primitive(new Double(str2).doubleValue());
                            bSHLiteral.value = primitive;
                        }
                        primitive = new Primitive(new Float(str2.substring(0, str2.length() - 1)).floatValue());
                        bSHLiteral.value = primitive;
                    }
                    if (i2 == 66) {
                        Token jj_consume_token3 = jj_consume_token(66);
                        this.jjtree.closeNodeScope((Node) bSHLiteral, true);
                        jjtreeCloseNodeScope(bSHLiteral);
                        try {
                            String str3 = jj_consume_token3.image;
                            bSHLiteral.charSetup(str3.substring(1, str3.length() - 1));
                            return;
                        } catch (Exception unused2) {
                            StringBuffer stringBuffer2 = new StringBuffer();
                            stringBuffer2.append("Error parsing character: ");
                            stringBuffer2.append(jj_consume_token3.image);
                            throw createParseException(stringBuffer2.toString());
                        }
                    }
                    if (i2 != 67) {
                        jj_consume_token(-1);
                        throw new ParseException();
                    }
                    Token jj_consume_token4 = jj_consume_token(67);
                    this.jjtree.closeNodeScope((Node) bSHLiteral, true);
                    jjtreeCloseNodeScope(bSHLiteral);
                    try {
                        String str4 = jj_consume_token4.image;
                        bSHLiteral.stringSetup(str4.substring(1, str4.length() - 1));
                        return;
                    } catch (Exception unused3) {
                        StringBuffer stringBuffer3 = new StringBuffer();
                        stringBuffer3.append("Error parsing string: ");
                        stringBuffer3.append(jj_consume_token4.image);
                        throw createParseException(stringBuffer3.toString());
                    }
                    th = th;
                    z = false;
                    try {
                        if (z) {
                            this.jjtree.clearNodeScope(bSHLiteral);
                        } else {
                            this.jjtree.popNode();
                            z2 = z;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                    }
                    try {
                        z = th instanceof RuntimeException;
                        if (z) {
                            throw ((RuntimeException) th);
                        }
                        if (!(th instanceof ParseException)) {
                            throw ((Error) th);
                        }
                        throw ((ParseException) th);
                    } catch (Throwable th4) {
                        z = z2;
                        th = th4;
                        if (z) {
                            this.jjtree.closeNodeScope((Node) bSHLiteral, true);
                            jjtreeCloseNodeScope(bSHLiteral);
                        }
                        throw th;
                    }
                }
                VoidLiteral();
                this.jjtree.closeNodeScope((Node) bSHLiteral, true);
                jjtreeCloseNodeScope(bSHLiteral);
                primitive2 = Primitive.VOID;
            }
            bSHLiteral.value = primitive2;
            return;
        }
        boolean BooleanLiteral = BooleanLiteral();
        this.jjtree.closeNodeScope((Node) bSHLiteral, true);
        jjtreeCloseNodeScope(bSHLiteral);
        primitive = new Primitive(BooleanLiteral);
        bSHLiteral.value = primitive;
    }

    public final void MethodDeclaration() {
        String str;
        BSHMethodDeclaration bSHMethodDeclaration = new BSHMethodDeclaration(2);
        this.jjtree.openNodeScope(bSHMethodDeclaration);
        jjtreeOpenNodeScope(bSHMethodDeclaration);
        boolean z = false;
        try {
            bSHMethodDeclaration.modifiers = Modifiers(1, false);
            if (jj_2_2(ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED)) {
                str = jj_consume_token(69).image;
            } else {
                int i2 = this.jj_ntk;
                if (i2 == -1) {
                    i2 = jj_ntk();
                }
                if (i2 != 11 && i2 != 14 && i2 != 17 && i2 != 22 && i2 != 29 && i2 != 36 && i2 != 38 && i2 != 47 && i2 != 57 && i2 != 69) {
                    jj_consume_token(-1);
                    throw new ParseException();
                }
                ReturnType();
                str = jj_consume_token(69).image;
            }
            bSHMethodDeclaration.name = str;
            FormalParameters();
            int i3 = this.jj_ntk;
            if (i3 == -1) {
                i3 = jj_ntk();
            }
            if (i3 == 54) {
                jj_consume_token(54);
                bSHMethodDeclaration.numThrows = NameList();
            }
            int i4 = this.jj_ntk;
            if (i4 == -1) {
                i4 = jj_ntk();
            }
            if (i4 == 74) {
                Block();
            } else {
                if (i4 != 78) {
                    jj_consume_token(-1);
                    throw new ParseException();
                }
                jj_consume_token(78);
            }
            this.jjtree.closeNodeScope((Node) bSHMethodDeclaration, true);
            jjtreeCloseNodeScope(bSHMethodDeclaration);
        } catch (Throwable th) {
            try {
                this.jjtree.clearNodeScope(bSHMethodDeclaration);
            } catch (Throwable th2) {
                th = th2;
                z = true;
            }
            try {
                if (th instanceof RuntimeException) {
                    throw ((RuntimeException) th);
                }
                if (!(th instanceof ParseException)) {
                    throw ((Error) th);
                }
                throw ((ParseException) th);
            } catch (Throwable th3) {
                th = th3;
                if (z) {
                    this.jjtree.closeNodeScope((Node) bSHMethodDeclaration, true);
                    jjtreeCloseNodeScope(bSHMethodDeclaration);
                }
                throw th;
            }
        }
    }

    public final void MethodInvocation() {
        boolean z;
        BSHMethodInvocation bSHMethodInvocation = new BSHMethodInvocation(19);
        this.jjtree.openNodeScope(bSHMethodInvocation);
        jjtreeOpenNodeScope(bSHMethodInvocation);
        try {
            AmbiguousName();
            Arguments();
            this.jjtree.closeNodeScope((Node) bSHMethodInvocation, true);
            jjtreeCloseNodeScope(bSHMethodInvocation);
        } catch (Throwable th) {
            try {
                this.jjtree.clearNodeScope(bSHMethodInvocation);
                z = false;
            } catch (Throwable th2) {
                th = th2;
                z = true;
            }
            try {
                if (th instanceof RuntimeException) {
                    throw ((RuntimeException) th);
                }
                if (!(th instanceof ParseException)) {
                    throw ((Error) th);
                }
                throw ((ParseException) th);
            } catch (Throwable th3) {
                th = th3;
                if (z) {
                    this.jjtree.closeNodeScope((Node) bSHMethodInvocation, true);
                    jjtreeCloseNodeScope(bSHMethodInvocation);
                }
                throw th;
            }
        }
    }

    public final Modifiers Modifiers(int i2, boolean z) {
        int i3;
        Modifiers modifiers = null;
        while (true) {
            int i4 = this.jj_ntk;
            if (i4 == -1) {
                i4 = jj_ntk();
            }
            if (i4 != 10 && i4 != 27 && i4 != 39 && i4 != 58 && i4 != 48 && i4 != 49 && i4 != 51 && i4 != 52) {
                switch (i4) {
                    case 43:
                    case 44:
                    case 45:
                        break;
                    default:
                        return modifiers;
                }
            }
            int i5 = this.jj_ntk;
            if (i5 == -1) {
                i5 = jj_ntk();
            }
            if (i5 == 10) {
                jj_consume_token(10);
            } else if (i5 == 27) {
                jj_consume_token(27);
            } else if (i5 == 39) {
                jj_consume_token(39);
            } else if (i5 == 58) {
                jj_consume_token(58);
            } else if (i5 == 48) {
                jj_consume_token(48);
            } else if (i5 == 49) {
                jj_consume_token(49);
            } else if (i5 == 51) {
                jj_consume_token(51);
            } else if (i5 != 52) {
                switch (i5) {
                    case 43:
                        i3 = 43;
                        break;
                    case 44:
                        i3 = 44;
                        break;
                    case 45:
                        i3 = 45;
                        break;
                    default:
                        jj_consume_token(-1);
                        throw new ParseException();
                }
                jj_consume_token(i3);
            } else {
                jj_consume_token(52);
            }
            if (!z) {
                if (modifiers == null) {
                    try {
                        modifiers = new Modifiers();
                    } catch (IllegalStateException e) {
                        throw createParseException(e.getMessage());
                    }
                }
                modifiers.addModifier(i2, getToken(0).image);
            }
        }
    }

    public final void MultiplicativeExpression() {
        Token jj_consume_token;
        UnaryExpression();
        while (true) {
            int i2 = this.jj_ntk;
            if (i2 == -1) {
                i2 = jj_ntk();
            }
            if (i2 != 104 && i2 != 105 && i2 != 111) {
                return;
            }
            int i3 = this.jj_ntk;
            if (i3 == -1) {
                i3 = jj_ntk();
            }
            if (i3 == 104) {
                jj_consume_token = jj_consume_token(104);
            } else if (i3 == 105) {
                jj_consume_token = jj_consume_token(105);
            } else {
                if (i3 != 111) {
                    jj_consume_token(-1);
                    throw new ParseException();
                }
                jj_consume_token = jj_consume_token(111);
            }
            UnaryExpression();
            BSHBinaryExpression bSHBinaryExpression = new BSHBinaryExpression(15);
            boolean z = true;
            this.jjtree.openNodeScope(bSHBinaryExpression);
            jjtreeOpenNodeScope(bSHBinaryExpression);
            try {
                this.jjtree.closeNodeScope(bSHBinaryExpression, 2);
                z = false;
                jjtreeCloseNodeScope(bSHBinaryExpression);
                bSHBinaryExpression.kind = jj_consume_token.kind;
            } catch (Throwable th) {
                if (z) {
                    this.jjtree.closeNodeScope(bSHBinaryExpression, 2);
                    jjtreeCloseNodeScope(bSHBinaryExpression);
                }
                throw th;
            }
        }
    }

    public final int NameList() {
        AmbiguousName();
        int i2 = 1;
        while (true) {
            int i3 = this.jj_ntk;
            if (i3 == -1) {
                i3 = jj_ntk();
            }
            if (i3 != 79) {
                return i2;
            }
            jj_consume_token(79);
            AmbiguousName();
            i2++;
        }
    }

    public final void NullLiteral() {
        jj_consume_token(41);
    }

    public final void PackageDeclaration() {
        boolean z;
        BSHPackageDeclaration bSHPackageDeclaration = new BSHPackageDeclaration(3);
        this.jjtree.openNodeScope(bSHPackageDeclaration);
        jjtreeOpenNodeScope(bSHPackageDeclaration);
        try {
            jj_consume_token(42);
            AmbiguousName();
            this.jjtree.closeNodeScope((Node) bSHPackageDeclaration, true);
            jjtreeCloseNodeScope(bSHPackageDeclaration);
        } catch (Throwable th) {
            try {
                this.jjtree.clearNodeScope(bSHPackageDeclaration);
                z = false;
            } catch (Throwable th2) {
                th = th2;
                z = true;
            }
            try {
                if (th instanceof RuntimeException) {
                    throw ((RuntimeException) th);
                }
                if (!(th instanceof ParseException)) {
                    throw ((Error) th);
                }
                throw ((ParseException) th);
            } catch (Throwable th3) {
                th = th3;
                if (z) {
                    this.jjtree.closeNodeScope((Node) bSHPackageDeclaration, true);
                    jjtreeCloseNodeScope(bSHPackageDeclaration);
                }
                throw th;
            }
        }
    }

    public final void PostfixExpression() {
        boolean z;
        if (!jj_2_12(ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED)) {
            int i2 = this.jj_ntk;
            if (i2 == -1) {
                i2 = jj_ntk();
            }
            switch (i2) {
                case 11:
                case 14:
                case 17:
                case 22:
                case 26:
                case 29:
                case 36:
                case 38:
                case 40:
                case 41:
                case 47:
                case 55:
                case 57:
                case 60:
                case 64:
                case 66:
                case 67:
                case 69:
                case 72:
                    PrimaryExpression();
                    return;
                default:
                    jj_consume_token(-1);
                    throw new ParseException();
            }
        }
        PrimaryExpression();
        int i3 = this.jj_ntk;
        if (i3 == -1) {
            i3 = jj_ntk();
        }
        int i4 = 100;
        if (i3 != 100) {
            i4 = 101;
            if (i3 != 101) {
                jj_consume_token(-1);
                throw new ParseException();
            }
        }
        Token jj_consume_token = jj_consume_token(i4);
        BSHUnaryExpression bSHUnaryExpression = new BSHUnaryExpression(16);
        this.jjtree.openNodeScope(bSHUnaryExpression);
        jjtreeOpenNodeScope(bSHUnaryExpression);
        try {
            this.jjtree.closeNodeScope(bSHUnaryExpression, 1);
            z = false;
        } catch (Throwable th) {
            th = th;
            z = true;
        }
        try {
            jjtreeCloseNodeScope(bSHUnaryExpression);
            bSHUnaryExpression.kind = jj_consume_token.kind;
            bSHUnaryExpression.postfix = true;
        } catch (Throwable th2) {
            th = th2;
            if (z) {
                this.jjtree.closeNodeScope(bSHUnaryExpression, 1);
                jjtreeCloseNodeScope(bSHUnaryExpression);
            }
            throw th;
        }
    }

    public final void PreDecrementExpression() {
        boolean z;
        Token jj_consume_token = jj_consume_token(101);
        PrimaryExpression();
        BSHUnaryExpression bSHUnaryExpression = new BSHUnaryExpression(16);
        this.jjtree.openNodeScope(bSHUnaryExpression);
        jjtreeOpenNodeScope(bSHUnaryExpression);
        try {
            this.jjtree.closeNodeScope(bSHUnaryExpression, 1);
            z = false;
        } catch (Throwable th) {
            th = th;
            z = true;
        }
        try {
            jjtreeCloseNodeScope(bSHUnaryExpression);
            bSHUnaryExpression.kind = jj_consume_token.kind;
        } catch (Throwable th2) {
            th = th2;
            if (z) {
                this.jjtree.closeNodeScope(bSHUnaryExpression, 1);
                jjtreeCloseNodeScope(bSHUnaryExpression);
            }
            throw th;
        }
    }

    public final void PreIncrementExpression() {
        boolean z;
        Token jj_consume_token = jj_consume_token(100);
        PrimaryExpression();
        BSHUnaryExpression bSHUnaryExpression = new BSHUnaryExpression(16);
        this.jjtree.openNodeScope(bSHUnaryExpression);
        jjtreeOpenNodeScope(bSHUnaryExpression);
        try {
            this.jjtree.closeNodeScope(bSHUnaryExpression, 1);
            z = false;
        } catch (Throwable th) {
            th = th;
            z = true;
        }
        try {
            jjtreeCloseNodeScope(bSHUnaryExpression);
            bSHUnaryExpression.kind = jj_consume_token.kind;
        } catch (Throwable th2) {
            th = th2;
            if (z) {
                this.jjtree.closeNodeScope(bSHUnaryExpression, 1);
                jjtreeCloseNodeScope(bSHUnaryExpression);
            }
            throw th;
        }
    }

    public final void PrimaryExpression() {
        boolean z;
        BSHPrimaryExpression bSHPrimaryExpression = new BSHPrimaryExpression(18);
        this.jjtree.openNodeScope(bSHPrimaryExpression);
        jjtreeOpenNodeScope(bSHPrimaryExpression);
        try {
            PrimaryPrefix();
            while (true) {
                int i2 = this.jj_ntk;
                if (i2 == -1) {
                    i2 = jj_ntk();
                }
                if (i2 != 74 && i2 != 76 && i2 != 80) {
                    this.jjtree.closeNodeScope((Node) bSHPrimaryExpression, true);
                    jjtreeCloseNodeScope(bSHPrimaryExpression);
                    return;
                }
                PrimarySuffix();
            }
        } catch (Throwable th) {
            try {
                this.jjtree.clearNodeScope(bSHPrimaryExpression);
                z = false;
            } catch (Throwable th2) {
                th = th2;
                z = true;
            }
            try {
                if (th instanceof RuntimeException) {
                    throw ((RuntimeException) th);
                }
                if (!(th instanceof ParseException)) {
                    throw ((Error) th);
                }
                throw ((ParseException) th);
            } catch (Throwable th3) {
                th = th3;
                if (z) {
                    this.jjtree.closeNodeScope((Node) bSHPrimaryExpression, true);
                    jjtreeCloseNodeScope(bSHPrimaryExpression);
                }
                throw th;
            }
        }
    }

    public final void PrimaryPrefix() {
        int i2 = this.jj_ntk;
        if (i2 == -1) {
            i2 = jj_ntk();
        }
        if (i2 != 26 && i2 != 55 && i2 != 57 && i2 != 60 && i2 != 64) {
            if (i2 == 72) {
                jj_consume_token(72);
                Expression();
                jj_consume_token(73);
                return;
            }
            if (i2 == 40) {
                AllocationExpression();
                return;
            }
            if (i2 != 41 && i2 != 66 && i2 != 67) {
                if (jj_2_14(ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED)) {
                    MethodInvocation();
                    return;
                }
                if (jj_2_15(ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED)) {
                    Type();
                    return;
                }
                int i3 = this.jj_ntk;
                if (i3 == -1) {
                    i3 = jj_ntk();
                }
                if (i3 == 69) {
                    AmbiguousName();
                    return;
                } else {
                    jj_consume_token(-1);
                    throw new ParseException();
                }
            }
        }
        Literal();
    }

    public final void PrimarySuffix() {
        boolean z;
        BSHPrimarySuffix bSHPrimarySuffix = new BSHPrimarySuffix(20);
        this.jjtree.openNodeScope(bSHPrimarySuffix);
        jjtreeOpenNodeScope(bSHPrimarySuffix);
        boolean z2 = false;
        try {
            try {
                if (jj_2_16(2)) {
                    jj_consume_token(80);
                    jj_consume_token(13);
                    this.jjtree.closeNodeScope((Node) bSHPrimarySuffix, true);
                    jjtreeCloseNodeScope(bSHPrimarySuffix);
                    bSHPrimarySuffix.operation = 0;
                } else {
                    int i2 = this.jj_ntk;
                    if (i2 == -1) {
                        i2 = jj_ntk();
                    }
                    if (i2 == 74) {
                        jj_consume_token(74);
                        Expression();
                        jj_consume_token(75);
                        this.jjtree.closeNodeScope((Node) bSHPrimarySuffix, true);
                        jjtreeCloseNodeScope(bSHPrimarySuffix);
                        bSHPrimarySuffix.operation = 3;
                    } else if (i2 == 76) {
                        jj_consume_token(76);
                        Expression();
                        jj_consume_token(77);
                        this.jjtree.closeNodeScope((Node) bSHPrimarySuffix, true);
                        jjtreeCloseNodeScope(bSHPrimarySuffix);
                        bSHPrimarySuffix.operation = 1;
                    } else {
                        if (i2 != 80) {
                            jj_consume_token(-1);
                            throw new ParseException();
                        }
                        jj_consume_token(80);
                        Token jj_consume_token = jj_consume_token(69);
                        int i3 = this.jj_ntk;
                        if (i3 == -1) {
                            i3 = jj_ntk();
                        }
                        if (i3 == 72) {
                            Arguments();
                        }
                        this.jjtree.closeNodeScope((Node) bSHPrimarySuffix, true);
                        jjtreeCloseNodeScope(bSHPrimarySuffix);
                        bSHPrimarySuffix.operation = 2;
                        bSHPrimarySuffix.field = jj_consume_token.image;
                    }
                }
            } catch (Throwable th) {
                th = th;
                z = false;
                try {
                    if (z) {
                        this.jjtree.clearNodeScope(bSHPrimarySuffix);
                    } else {
                        this.jjtree.popNode();
                        z2 = z;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
                try {
                    z = th instanceof RuntimeException;
                    if (z) {
                        throw ((RuntimeException) th);
                    }
                    if (!(th instanceof ParseException)) {
                        throw ((Error) th);
                    }
                    throw ((ParseException) th);
                } catch (Throwable th3) {
                    th = th3;
                    z = z2;
                    if (z) {
                        this.jjtree.closeNodeScope((Node) bSHPrimarySuffix, true);
                        jjtreeCloseNodeScope(bSHPrimarySuffix);
                    }
                    throw th;
                }
            }
        } catch (Throwable th4) {
            th = th4;
            z = true;
        }
    }

    public final void PrimitiveType() {
        int i2;
        Class cls;
        BSHPrimitiveType bSHPrimitiveType = new BSHPrimitiveType(11);
        this.jjtree.openNodeScope(bSHPrimitiveType);
        jjtreeOpenNodeScope(bSHPrimitiveType);
        boolean z = false;
        try {
            i2 = this.jj_ntk;
            if (i2 == -1) {
                i2 = jj_ntk();
            }
        } catch (Throwable th) {
            th = th;
            z = true;
        }
        try {
            if (i2 == 11) {
                jj_consume_token(11);
                this.jjtree.closeNodeScope((Node) bSHPrimitiveType, true);
                jjtreeCloseNodeScope(bSHPrimitiveType);
                cls = Boolean.TYPE;
            } else if (i2 == 14) {
                jj_consume_token(14);
                this.jjtree.closeNodeScope((Node) bSHPrimitiveType, true);
                jjtreeCloseNodeScope(bSHPrimitiveType);
                cls = Byte.TYPE;
            } else if (i2 == 17) {
                jj_consume_token(17);
                this.jjtree.closeNodeScope((Node) bSHPrimitiveType, true);
                jjtreeCloseNodeScope(bSHPrimitiveType);
                cls = Character.TYPE;
            } else if (i2 == 22) {
                jj_consume_token(22);
                this.jjtree.closeNodeScope((Node) bSHPrimitiveType, true);
                jjtreeCloseNodeScope(bSHPrimitiveType);
                cls = Double.TYPE;
            } else if (i2 == 29) {
                jj_consume_token(29);
                this.jjtree.closeNodeScope((Node) bSHPrimitiveType, true);
                jjtreeCloseNodeScope(bSHPrimitiveType);
                cls = Float.TYPE;
            } else if (i2 == 36) {
                jj_consume_token(36);
                this.jjtree.closeNodeScope((Node) bSHPrimitiveType, true);
                jjtreeCloseNodeScope(bSHPrimitiveType);
                cls = Integer.TYPE;
            } else if (i2 == 38) {
                jj_consume_token(38);
                this.jjtree.closeNodeScope((Node) bSHPrimitiveType, true);
                jjtreeCloseNodeScope(bSHPrimitiveType);
                cls = Long.TYPE;
            } else {
                if (i2 != 47) {
                    jj_consume_token(-1);
                    throw new ParseException();
                }
                jj_consume_token(47);
                this.jjtree.closeNodeScope((Node) bSHPrimitiveType, true);
                jjtreeCloseNodeScope(bSHPrimitiveType);
                cls = Short.TYPE;
            }
            bSHPrimitiveType.type = cls;
        } catch (Throwable th2) {
            th = th2;
            if (z) {
                this.jjtree.closeNodeScope((Node) bSHPrimitiveType, true);
                jjtreeCloseNodeScope(bSHPrimitiveType);
            }
            throw th;
        }
    }

    public void ReInit(ParserTokenManager parserTokenManager) {
        this.token_source = parserTokenManager;
        this.token = new Token();
        this.jj_ntk = -1;
        this.jjtree.reset();
    }

    public void ReInit(InputStream inputStream) {
        this.jj_input_stream.ReInit(inputStream, 1, 1);
        this.token_source.ReInit(this.jj_input_stream);
        this.token = new Token();
        this.jj_ntk = -1;
        this.jjtree.reset();
    }

    public void ReInit(Reader reader) {
        this.jj_input_stream.ReInit(reader, 1, 1);
        this.token_source.ReInit(this.jj_input_stream);
        this.token = new Token();
        this.jj_ntk = -1;
        this.jjtree.reset();
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:5:0x000c. Please report as an issue. */
    public final void RelationalExpression() {
        int i2;
        ShiftExpression();
        while (true) {
            int i3 = this.jj_ntk;
            if (i3 == -1) {
                i3 = jj_ntk();
            }
            switch (i3) {
                default:
                    switch (i3) {
                        case 91:
                        case 92:
                        case 93:
                        case 94:
                            break;
                        default:
                            return;
                    }
                case 82:
                case 83:
                case 84:
                case 85:
                    int i4 = this.jj_ntk;
                    if (i4 == -1) {
                        i4 = jj_ntk();
                    }
                    switch (i4) {
                        case 82:
                            i2 = 82;
                            break;
                        case 83:
                            i2 = 83;
                            break;
                        case 84:
                            i2 = 84;
                            break;
                        case 85:
                            i2 = 85;
                            break;
                        default:
                            switch (i4) {
                                case 91:
                                    i2 = 91;
                                    break;
                                case 92:
                                    i2 = 92;
                                    break;
                                case 93:
                                    i2 = 93;
                                    break;
                                case 94:
                                    i2 = 94;
                                    break;
                                default:
                                    jj_consume_token(-1);
                                    throw new ParseException();
                            }
                    }
                    Token jj_consume_token = jj_consume_token(i2);
                    ShiftExpression();
                    BSHBinaryExpression bSHBinaryExpression = new BSHBinaryExpression(15);
                    boolean z = true;
                    this.jjtree.openNodeScope(bSHBinaryExpression);
                    jjtreeOpenNodeScope(bSHBinaryExpression);
                    try {
                        this.jjtree.closeNodeScope(bSHBinaryExpression, 2);
                        z = false;
                        jjtreeCloseNodeScope(bSHBinaryExpression);
                        bSHBinaryExpression.kind = jj_consume_token.kind;
                    } catch (Throwable th) {
                        if (z) {
                            this.jjtree.closeNodeScope(bSHBinaryExpression, 2);
                            jjtreeCloseNodeScope(bSHBinaryExpression);
                        }
                        throw th;
                    }
            }
        }
    }

    public final void ReturnStatement() {
        boolean z;
        BSHReturnStatement bSHReturnStatement = new BSHReturnStatement(35);
        this.jjtree.openNodeScope(bSHReturnStatement);
        jjtreeOpenNodeScope(bSHReturnStatement);
        boolean z2 = false;
        try {
            jj_consume_token(46);
            int i2 = this.jj_ntk;
            if (i2 == -1) {
                i2 = jj_ntk();
            }
            switch (i2) {
                case 11:
                case 14:
                case 17:
                case 22:
                case 26:
                case 29:
                case 36:
                case 38:
                case 40:
                case 41:
                case 47:
                case 55:
                case 57:
                case 60:
                case 64:
                case 66:
                case 67:
                case 69:
                case 72:
                case 86:
                case 87:
                case 100:
                case 101:
                case 102:
                case 103:
                    Expression();
                    break;
            }
            jj_consume_token(78);
            this.jjtree.closeNodeScope((Node) bSHReturnStatement, true);
        } catch (Throwable th) {
            th = th;
            z = true;
        }
        try {
            jjtreeCloseNodeScope(bSHReturnStatement);
            bSHReturnStatement.kind = 46;
        } catch (Throwable th2) {
            th = th2;
            z = false;
            try {
                if (z) {
                    this.jjtree.clearNodeScope(bSHReturnStatement);
                } else {
                    this.jjtree.popNode();
                    z2 = z;
                }
            } catch (Throwable th3) {
                th = th3;
            }
            try {
                z = th instanceof RuntimeException;
                if (z) {
                    throw ((RuntimeException) th);
                }
                if (!(th instanceof ParseException)) {
                    throw ((Error) th);
                }
                throw ((ParseException) th);
            } catch (Throwable th4) {
                z = z2;
                th = th4;
                if (z) {
                    this.jjtree.closeNodeScope((Node) bSHReturnStatement, true);
                    jjtreeCloseNodeScope(bSHReturnStatement);
                }
                throw th;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:36:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void ReturnType() {
        /*
            r6 = this;
            bsh.BSHReturnType r0 = new bsh.BSHReturnType
            r1 = 10
            r0.<init>(r1)
            bsh.JJTParserState r1 = r6.jjtree
            r1.openNodeScope(r0)
            r6.jjtreeOpenNodeScope(r0)
            r1 = 0
            r2 = 1
            int r3 = r6.jj_ntk     // Catch: java.lang.Throwable -> L6c
            r4 = -1
            if (r3 != r4) goto L1a
            int r3 = r6.jj_ntk()     // Catch: java.lang.Throwable -> L6c
        L1a:
            r5 = 11
            if (r3 == r5) goto L5d
            r5 = 14
            if (r3 == r5) goto L5d
            r5 = 17
            if (r3 == r5) goto L5d
            r5 = 22
            if (r3 == r5) goto L5d
            r5 = 29
            if (r3 == r5) goto L5d
            r5 = 36
            if (r3 == r5) goto L5d
            r5 = 38
            if (r3 == r5) goto L5d
            r5 = 47
            if (r3 == r5) goto L5d
            r5 = 57
            if (r3 == r5) goto L4c
            r5 = 69
            if (r3 != r5) goto L43
            goto L5d
        L43:
            r6.jj_consume_token(r4)     // Catch: java.lang.Throwable -> L6c
            bsh.ParseException r3 = new bsh.ParseException     // Catch: java.lang.Throwable -> L6c
            r3.<init>()     // Catch: java.lang.Throwable -> L6c
            throw r3     // Catch: java.lang.Throwable -> L6c
        L4c:
            r6.jj_consume_token(r5)     // Catch: java.lang.Throwable -> L6c
            bsh.JJTParserState r3 = r6.jjtree     // Catch: java.lang.Throwable -> L6c
            r3.closeNodeScope(r0, r2)     // Catch: java.lang.Throwable -> L6c
            r6.jjtreeCloseNodeScope(r0)     // Catch: java.lang.Throwable -> L5a
            r0.isVoid = r2     // Catch: java.lang.Throwable -> L5a
            goto L61
        L5a:
            r3 = move-exception
            r4 = 0
            goto L6e
        L5d:
            r6.Type()     // Catch: java.lang.Throwable -> L6c
            r1 = 1
        L61:
            if (r1 == 0) goto L6b
            bsh.JJTParserState r1 = r6.jjtree
            r1.closeNodeScope(r0, r2)
            r6.jjtreeCloseNodeScope(r0)
        L6b:
            return
        L6c:
            r3 = move-exception
            r4 = 1
        L6e:
            if (r4 == 0) goto L76
            bsh.JJTParserState r5 = r6.jjtree     // Catch: java.lang.Throwable -> L91
            r5.clearNodeScope(r0)     // Catch: java.lang.Throwable -> L91
            goto L7c
        L76:
            bsh.JJTParserState r1 = r6.jjtree     // Catch: java.lang.Throwable -> L91
            r1.popNode()     // Catch: java.lang.Throwable -> L91
            r1 = r4
        L7c:
            boolean r4 = r3 instanceof java.lang.RuntimeException     // Catch: java.lang.Throwable -> L8d
            if (r4 != 0) goto L8a
            boolean r4 = r3 instanceof bsh.ParseException     // Catch: java.lang.Throwable -> L8d
            if (r4 == 0) goto L87
            bsh.ParseException r3 = (bsh.ParseException) r3     // Catch: java.lang.Throwable -> L8d
            throw r3     // Catch: java.lang.Throwable -> L8d
        L87:
            java.lang.Error r3 = (java.lang.Error) r3     // Catch: java.lang.Throwable -> L8d
            throw r3     // Catch: java.lang.Throwable -> L8d
        L8a:
            java.lang.RuntimeException r3 = (java.lang.RuntimeException) r3     // Catch: java.lang.Throwable -> L8d
            throw r3     // Catch: java.lang.Throwable -> L8d
        L8d:
            r3 = move-exception
            r4 = r1
            r1 = r3
            goto L92
        L91:
            r1 = move-exception
        L92:
            if (r4 == 0) goto L9c
            bsh.JJTParserState r3 = r6.jjtree
            r3.closeNodeScope(r0, r2)
            r6.jjtreeCloseNodeScope(r0)
        L9c:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: bsh.Parser.ReturnType():void");
    }

    public final void ShiftExpression() {
        int i2;
        AdditiveExpression();
        while (true) {
            int i3 = this.jj_ntk;
            if (i3 == -1) {
                i3 = jj_ntk();
            }
            switch (i3) {
                case 112:
                case 113:
                case 114:
                case 115:
                case 116:
                case 117:
                    int i4 = this.jj_ntk;
                    if (i4 == -1) {
                        i4 = jj_ntk();
                    }
                    switch (i4) {
                        case 112:
                            i2 = 112;
                            break;
                        case 113:
                            i2 = 113;
                            break;
                        case 114:
                            i2 = 114;
                            break;
                        case 115:
                            i2 = 115;
                            break;
                        case 116:
                            i2 = 116;
                            break;
                        case 117:
                            i2 = 117;
                            break;
                        default:
                            jj_consume_token(-1);
                            throw new ParseException();
                    }
                    Token jj_consume_token = jj_consume_token(i2);
                    AdditiveExpression();
                    BSHBinaryExpression bSHBinaryExpression = new BSHBinaryExpression(15);
                    boolean z = true;
                    this.jjtree.openNodeScope(bSHBinaryExpression);
                    jjtreeOpenNodeScope(bSHBinaryExpression);
                    try {
                        this.jjtree.closeNodeScope(bSHBinaryExpression, 2);
                        z = false;
                        jjtreeCloseNodeScope(bSHBinaryExpression);
                        bSHBinaryExpression.kind = jj_consume_token.kind;
                    } catch (Throwable th) {
                        if (z) {
                            this.jjtree.closeNodeScope(bSHBinaryExpression, 2);
                            jjtreeCloseNodeScope(bSHBinaryExpression);
                        }
                        throw th;
                    }
                default:
                    return;
            }
        }
    }

    public final void Statement() {
        if (jj_2_22(2)) {
            LabeledStatement();
            return;
        }
        int i2 = this.jj_ntk;
        if (i2 == -1) {
            i2 = jj_ntk();
        }
        switch (i2) {
            case 11:
            case 14:
            case 17:
            case 22:
            case 26:
            case 29:
            case 36:
            case 38:
            case 40:
            case 41:
            case 47:
            case 55:
            case 57:
            case 60:
            case 64:
            case 66:
            case 67:
            case 69:
            case 72:
            case 86:
            case 87:
            case 100:
            case 101:
            case 102:
            case 103:
                StatementExpression();
                jj_consume_token(78);
                return;
            case 21:
                DoStatement();
                return;
            case 32:
                IfStatement();
                return;
            case 50:
                SwitchStatement();
                return;
            case 59:
                WhileStatement();
                return;
            case 74:
                Block();
                return;
            case 78:
                EmptyStatement();
                return;
            default:
                if (isRegularForStatement()) {
                    ForStatement();
                    return;
                }
                int i3 = this.jj_ntk;
                if (i3 == -1) {
                    i3 = jj_ntk();
                }
                if (i3 == 12) {
                    BreakStatement();
                    return;
                }
                if (i3 == 19) {
                    ContinueStatement();
                    return;
                }
                if (i3 == 30) {
                    EnhancedForStatement();
                    return;
                }
                if (i3 == 46) {
                    ReturnStatement();
                    return;
                }
                if (i3 == 51) {
                    SynchronizedStatement();
                    return;
                }
                if (i3 == 53) {
                    ThrowStatement();
                    return;
                } else if (i3 == 56) {
                    TryStatement();
                    return;
                } else {
                    jj_consume_token(-1);
                    throw new ParseException();
                }
        }
    }

    public final void StatementExpression() {
        Expression();
    }

    public final void StatementExpressionList() {
        boolean z;
        BSHStatementExpressionList bSHStatementExpressionList = new BSHStatementExpressionList(34);
        this.jjtree.openNodeScope(bSHStatementExpressionList);
        jjtreeOpenNodeScope(bSHStatementExpressionList);
        try {
            StatementExpression();
            while (true) {
                int i2 = this.jj_ntk;
                if (i2 == -1) {
                    i2 = jj_ntk();
                }
                if (i2 != 79) {
                    this.jjtree.closeNodeScope((Node) bSHStatementExpressionList, true);
                    jjtreeCloseNodeScope(bSHStatementExpressionList);
                    return;
                } else {
                    jj_consume_token(79);
                    StatementExpression();
                }
            }
        } catch (Throwable th) {
            try {
                this.jjtree.clearNodeScope(bSHStatementExpressionList);
                z = false;
            } catch (Throwable th2) {
                th = th2;
                z = true;
            }
            try {
                if (th instanceof RuntimeException) {
                    throw ((RuntimeException) th);
                }
                if (!(th instanceof ParseException)) {
                    throw ((Error) th);
                }
                throw ((ParseException) th);
            } catch (Throwable th3) {
                th = th3;
                if (z) {
                    this.jjtree.closeNodeScope((Node) bSHStatementExpressionList, true);
                    jjtreeCloseNodeScope(bSHStatementExpressionList);
                }
                throw th;
            }
        }
    }

    public final void SwitchLabel() {
        boolean z;
        BSHSwitchLabel bSHSwitchLabel = new BSHSwitchLabel(28);
        this.jjtree.openNodeScope(bSHSwitchLabel);
        jjtreeOpenNodeScope(bSHSwitchLabel);
        boolean z2 = false;
        try {
            int i2 = this.jj_ntk;
            if (i2 == -1) {
                i2 = jj_ntk();
            }
            if (i2 == 15) {
                jj_consume_token(15);
                Expression();
                jj_consume_token(89);
                z2 = true;
            } else {
                if (i2 != 20) {
                    jj_consume_token(-1);
                    throw new ParseException();
                }
                jj_consume_token(20);
                jj_consume_token(89);
                this.jjtree.closeNodeScope((Node) bSHSwitchLabel, true);
                try {
                    jjtreeCloseNodeScope(bSHSwitchLabel);
                    bSHSwitchLabel.isDefault = true;
                } catch (Throwable th) {
                    th = th;
                    z = false;
                    try {
                        if (z) {
                            this.jjtree.clearNodeScope(bSHSwitchLabel);
                        } else {
                            this.jjtree.popNode();
                            z2 = z;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                    }
                    try {
                        z = th instanceof RuntimeException;
                        if (z) {
                            throw ((RuntimeException) th);
                        }
                        if (!(th instanceof ParseException)) {
                            throw ((Error) th);
                        }
                        throw ((ParseException) th);
                    } catch (Throwable th3) {
                        z = z2;
                        th = th3;
                        if (z) {
                            this.jjtree.closeNodeScope((Node) bSHSwitchLabel, true);
                            jjtreeCloseNodeScope(bSHSwitchLabel);
                        }
                        throw th;
                    }
                }
            }
            if (z2) {
                this.jjtree.closeNodeScope((Node) bSHSwitchLabel, true);
                jjtreeCloseNodeScope(bSHSwitchLabel);
            }
        } catch (Throwable th4) {
            th = th4;
            z = true;
        }
    }

    public final void SwitchStatement() {
        boolean z;
        BSHSwitchStatement bSHSwitchStatement = new BSHSwitchStatement(27);
        this.jjtree.openNodeScope(bSHSwitchStatement);
        jjtreeOpenNodeScope(bSHSwitchStatement);
        try {
            jj_consume_token(50);
            jj_consume_token(72);
            Expression();
            jj_consume_token(73);
            jj_consume_token(74);
            while (true) {
                int i2 = this.jj_ntk;
                if (i2 == -1) {
                    i2 = jj_ntk();
                }
                if (i2 != 15 && i2 != 20) {
                    jj_consume_token(75);
                    this.jjtree.closeNodeScope((Node) bSHSwitchStatement, true);
                    jjtreeCloseNodeScope(bSHSwitchStatement);
                    return;
                } else {
                    SwitchLabel();
                    while (jj_2_29(1)) {
                        BlockStatement();
                    }
                }
            }
        } catch (Throwable th) {
            try {
                this.jjtree.clearNodeScope(bSHSwitchStatement);
                z = false;
            } catch (Throwable th2) {
                th = th2;
                z = true;
            }
            try {
                if (th instanceof RuntimeException) {
                    throw ((RuntimeException) th);
                }
                if (!(th instanceof ParseException)) {
                    throw ((Error) th);
                }
                throw ((ParseException) th);
            } catch (Throwable th3) {
                th = th3;
                if (z) {
                    this.jjtree.closeNodeScope((Node) bSHSwitchStatement, true);
                    jjtreeCloseNodeScope(bSHSwitchStatement);
                }
                throw th;
            }
        }
    }

    public final void SynchronizedStatement() {
        boolean z;
        BSHBlock bSHBlock = new BSHBlock(25);
        this.jjtree.openNodeScope(bSHBlock);
        jjtreeOpenNodeScope(bSHBlock);
        boolean z2 = false;
        try {
            jj_consume_token(51);
            jj_consume_token(72);
            Expression();
            jj_consume_token(73);
            Block();
            this.jjtree.closeNodeScope((Node) bSHBlock, true);
        } catch (Throwable th) {
            th = th;
            z = true;
        }
        try {
            jjtreeCloseNodeScope(bSHBlock);
            bSHBlock.isSynchronized = true;
        } catch (Throwable th2) {
            th = th2;
            z = false;
            try {
                if (z) {
                    this.jjtree.clearNodeScope(bSHBlock);
                } else {
                    this.jjtree.popNode();
                    z2 = z;
                }
            } catch (Throwable th3) {
                th = th3;
            }
            try {
                z = th instanceof RuntimeException;
                if (z) {
                    throw ((RuntimeException) th);
                }
                if (!(th instanceof ParseException)) {
                    throw ((Error) th);
                }
                throw ((ParseException) th);
            } catch (Throwable th4) {
                th = th4;
                z = z2;
                if (z) {
                    this.jjtree.closeNodeScope((Node) bSHBlock, true);
                    jjtreeCloseNodeScope(bSHBlock);
                }
                throw th;
            }
        }
    }

    public final void ThrowStatement() {
        boolean z;
        BSHThrowStatement bSHThrowStatement = new BSHThrowStatement(36);
        this.jjtree.openNodeScope(bSHThrowStatement);
        jjtreeOpenNodeScope(bSHThrowStatement);
        try {
            jj_consume_token(53);
            Expression();
            jj_consume_token(78);
            this.jjtree.closeNodeScope((Node) bSHThrowStatement, true);
            jjtreeCloseNodeScope(bSHThrowStatement);
        } catch (Throwable th) {
            try {
                this.jjtree.clearNodeScope(bSHThrowStatement);
                z = false;
                try {
                    if (th instanceof RuntimeException) {
                        throw ((RuntimeException) th);
                    }
                    if (!(th instanceof ParseException)) {
                        throw ((Error) th);
                    }
                    throw ((ParseException) th);
                } catch (Throwable th2) {
                    th = th2;
                    if (z) {
                        this.jjtree.closeNodeScope((Node) bSHThrowStatement, true);
                        jjtreeCloseNodeScope(bSHThrowStatement);
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                z = true;
            }
        }
    }

    public final void TryStatement() {
        boolean z;
        BSHTryStatement bSHTryStatement = new BSHTryStatement(37);
        this.jjtree.openNodeScope(bSHTryStatement);
        jjtreeOpenNodeScope(bSHTryStatement);
        boolean z2 = false;
        try {
            jj_consume_token(56);
            Block();
            boolean z3 = false;
            while (true) {
                int i2 = this.jj_ntk;
                if (i2 == -1) {
                    i2 = jj_ntk();
                }
                if (i2 != 16) {
                    break;
                }
                jj_consume_token(16);
                jj_consume_token(72);
                FormalParameter();
                jj_consume_token(73);
                Block();
                z3 = true;
            }
            int i3 = this.jj_ntk;
            if (i3 == -1) {
                i3 = jj_ntk();
            }
            if (i3 == 28) {
                jj_consume_token(28);
                Block();
                z3 = true;
            }
            this.jjtree.closeNodeScope((Node) bSHTryStatement, true);
            try {
                jjtreeCloseNodeScope(bSHTryStatement);
                if (z3) {
                } else {
                    throw generateParseException();
                }
            } catch (Throwable th) {
                th = th;
                z = false;
                try {
                    if (z) {
                        this.jjtree.clearNodeScope(bSHTryStatement);
                    } else {
                        this.jjtree.popNode();
                        z2 = z;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
                try {
                    z = th instanceof RuntimeException;
                    if (z) {
                        throw ((RuntimeException) th);
                    }
                    if (!(th instanceof ParseException)) {
                        throw ((Error) th);
                    }
                    throw ((ParseException) th);
                } catch (Throwable th3) {
                    th = th3;
                    z = z2;
                    if (z) {
                        this.jjtree.closeNodeScope((Node) bSHTryStatement, true);
                        jjtreeCloseNodeScope(bSHTryStatement);
                    }
                    throw th;
                }
            }
        } catch (Throwable th4) {
            th = th4;
            z = true;
        }
    }

    public final void Type() {
        boolean z;
        BSHType bSHType = new BSHType(9);
        this.jjtree.openNodeScope(bSHType);
        jjtreeOpenNodeScope(bSHType);
        try {
            int i2 = this.jj_ntk;
            if (i2 == -1) {
                i2 = jj_ntk();
            }
            if (i2 == 11 || i2 == 14 || i2 == 17 || i2 == 22 || i2 == 29 || i2 == 36 || i2 == 38 || i2 == 47) {
                PrimitiveType();
            } else {
                if (i2 != 69) {
                    jj_consume_token(-1);
                    throw new ParseException();
                }
                AmbiguousName();
            }
            while (jj_2_6(2)) {
                jj_consume_token(76);
                jj_consume_token(77);
                bSHType.addArrayDimension();
            }
            this.jjtree.closeNodeScope((Node) bSHType, true);
            jjtreeCloseNodeScope(bSHType);
        } catch (Throwable th) {
            try {
                this.jjtree.clearNodeScope(bSHType);
                z = false;
            } catch (Throwable th2) {
                th = th2;
                z = true;
            }
            try {
                if (th instanceof RuntimeException) {
                    throw ((RuntimeException) th);
                }
                if (!(th instanceof ParseException)) {
                    throw ((Error) th);
                }
                throw ((ParseException) th);
            } catch (Throwable th3) {
                th = th3;
                if (z) {
                    this.jjtree.closeNodeScope((Node) bSHType, true);
                    jjtreeCloseNodeScope(bSHType);
                }
                throw th;
            }
        }
    }

    public final void TypedVariableDeclaration() {
        boolean z;
        BSHTypedVariableDeclaration bSHTypedVariableDeclaration = new BSHTypedVariableDeclaration(33);
        this.jjtree.openNodeScope(bSHTypedVariableDeclaration);
        jjtreeOpenNodeScope(bSHTypedVariableDeclaration);
        boolean z2 = false;
        try {
            Modifiers Modifiers = Modifiers(2, false);
            Type();
            while (true) {
                VariableDeclarator();
                int i2 = this.jj_ntk;
                if (i2 == -1) {
                    i2 = jj_ntk();
                }
                if (i2 != 79) {
                    this.jjtree.closeNodeScope((Node) bSHTypedVariableDeclaration, true);
                    try {
                        jjtreeCloseNodeScope(bSHTypedVariableDeclaration);
                        bSHTypedVariableDeclaration.modifiers = Modifiers;
                        return;
                    } catch (Throwable th) {
                        th = th;
                        z = false;
                        try {
                            if (z) {
                                this.jjtree.clearNodeScope(bSHTypedVariableDeclaration);
                            } else {
                                this.jjtree.popNode();
                                z2 = z;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                        }
                        try {
                            z = th instanceof RuntimeException;
                            if (z) {
                                throw ((RuntimeException) th);
                            }
                            if (!(th instanceof ParseException)) {
                                throw ((Error) th);
                            }
                            throw ((ParseException) th);
                        } catch (Throwable th3) {
                            th = th3;
                            z = z2;
                            if (z) {
                                this.jjtree.closeNodeScope((Node) bSHTypedVariableDeclaration, true);
                                jjtreeCloseNodeScope(bSHTypedVariableDeclaration);
                            }
                            throw th;
                        }
                    }
                }
                jj_consume_token(79);
            }
        } catch (Throwable th4) {
            th = th4;
            z = true;
        }
    }

    public final void UnaryExpression() {
        boolean z;
        int i2 = this.jj_ntk;
        if (i2 == -1) {
            i2 = jj_ntk();
        }
        switch (i2) {
            case 11:
            case 14:
            case 17:
            case 22:
            case 26:
            case 29:
            case 36:
            case 38:
            case 40:
            case 41:
            case 47:
            case 55:
            case 57:
            case 60:
            case 64:
            case 66:
            case 67:
            case 69:
            case 72:
            case 86:
            case 87:
                UnaryExpressionNotPlusMinus();
                return;
            case 100:
                PreIncrementExpression();
                return;
            case 101:
                PreDecrementExpression();
                return;
            case 102:
            case 103:
                int i3 = this.jj_ntk;
                if (i3 == -1) {
                    i3 = jj_ntk();
                }
                int i4 = 102;
                if (i3 != 102) {
                    i4 = 103;
                    if (i3 != 103) {
                        jj_consume_token(-1);
                        throw new ParseException();
                    }
                }
                Token jj_consume_token = jj_consume_token(i4);
                UnaryExpression();
                BSHUnaryExpression bSHUnaryExpression = new BSHUnaryExpression(16);
                this.jjtree.openNodeScope(bSHUnaryExpression);
                jjtreeOpenNodeScope(bSHUnaryExpression);
                try {
                    this.jjtree.closeNodeScope(bSHUnaryExpression, 1);
                    z = false;
                } catch (Throwable th) {
                    th = th;
                    z = true;
                }
                try {
                    jjtreeCloseNodeScope(bSHUnaryExpression);
                    bSHUnaryExpression.kind = jj_consume_token.kind;
                    return;
                } catch (Throwable th2) {
                    th = th2;
                    if (z) {
                        this.jjtree.closeNodeScope(bSHUnaryExpression, 1);
                        jjtreeCloseNodeScope(bSHUnaryExpression);
                    }
                    throw th;
                }
            default:
                jj_consume_token(-1);
                throw new ParseException();
        }
    }

    public final void UnaryExpressionNotPlusMinus() {
        Token jj_consume_token;
        boolean z;
        int i2 = this.jj_ntk;
        if (i2 == -1) {
            i2 = jj_ntk();
        }
        if (i2 != 86 && i2 != 87) {
            if (jj_2_9(ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED)) {
                CastExpression();
                return;
            }
            int i3 = this.jj_ntk;
            if (i3 == -1) {
                i3 = jj_ntk();
            }
            switch (i3) {
                case 11:
                case 14:
                case 17:
                case 22:
                case 26:
                case 29:
                case 36:
                case 38:
                case 40:
                case 41:
                case 47:
                case 55:
                case 57:
                case 60:
                case 64:
                case 66:
                case 67:
                case 69:
                case 72:
                    PostfixExpression();
                    return;
                default:
                    jj_consume_token(-1);
                    throw new ParseException();
            }
        }
        int i4 = this.jj_ntk;
        if (i4 == -1) {
            i4 = jj_ntk();
        }
        if (i4 == 86) {
            jj_consume_token = jj_consume_token(86);
        } else {
            if (i4 != 87) {
                jj_consume_token(-1);
                throw new ParseException();
            }
            jj_consume_token = jj_consume_token(87);
        }
        UnaryExpression();
        BSHUnaryExpression bSHUnaryExpression = new BSHUnaryExpression(16);
        this.jjtree.openNodeScope(bSHUnaryExpression);
        jjtreeOpenNodeScope(bSHUnaryExpression);
        try {
            this.jjtree.closeNodeScope(bSHUnaryExpression, 1);
            z = false;
        } catch (Throwable th) {
            th = th;
            z = true;
        }
        try {
            jjtreeCloseNodeScope(bSHUnaryExpression);
            bSHUnaryExpression.kind = jj_consume_token.kind;
        } catch (Throwable th2) {
            th = th2;
            if (z) {
                this.jjtree.closeNodeScope(bSHUnaryExpression, 1);
                jjtreeCloseNodeScope(bSHUnaryExpression);
            }
            throw th;
        }
    }

    public final void VariableDeclarator() {
        boolean z;
        Token jj_consume_token;
        BSHVariableDeclarator bSHVariableDeclarator = new BSHVariableDeclarator(5);
        this.jjtree.openNodeScope(bSHVariableDeclarator);
        jjtreeOpenNodeScope(bSHVariableDeclarator);
        boolean z2 = false;
        try {
            jj_consume_token = jj_consume_token(69);
            int i2 = this.jj_ntk;
            if (i2 == -1) {
                i2 = jj_ntk();
            }
            if (i2 == 81) {
                jj_consume_token(81);
                VariableInitializer();
            }
            this.jjtree.closeNodeScope((Node) bSHVariableDeclarator, true);
        } catch (Throwable th) {
            th = th;
            z = true;
        }
        try {
            jjtreeCloseNodeScope(bSHVariableDeclarator);
            bSHVariableDeclarator.name = jj_consume_token.image;
        } catch (Throwable th2) {
            th = th2;
            z = false;
            try {
                if (z) {
                    this.jjtree.clearNodeScope(bSHVariableDeclarator);
                } else {
                    this.jjtree.popNode();
                    z2 = z;
                }
            } catch (Throwable th3) {
                th = th3;
            }
            try {
                z = th instanceof RuntimeException;
                if (z) {
                    throw ((RuntimeException) th);
                }
                if (!(th instanceof ParseException)) {
                    throw ((Error) th);
                }
                throw ((ParseException) th);
            } catch (Throwable th4) {
                th = th4;
                z = z2;
                if (z) {
                    this.jjtree.closeNodeScope((Node) bSHVariableDeclarator, true);
                    jjtreeCloseNodeScope(bSHVariableDeclarator);
                }
                throw th;
            }
        }
    }

    public final void VariableInitializer() {
        int i2 = this.jj_ntk;
        if (i2 == -1) {
            i2 = jj_ntk();
        }
        switch (i2) {
            case 11:
            case 14:
            case 17:
            case 22:
            case 26:
            case 29:
            case 36:
            case 38:
            case 40:
            case 41:
            case 47:
            case 55:
            case 57:
            case 60:
            case 64:
            case 66:
            case 67:
            case 69:
            case 72:
            case 86:
            case 87:
            case 100:
            case 101:
            case 102:
            case 103:
                Expression();
                return;
            case 74:
                ArrayInitializer();
                return;
            default:
                jj_consume_token(-1);
                throw new ParseException();
        }
    }

    public final void VoidLiteral() {
        jj_consume_token(57);
    }

    public final void WhileStatement() {
        boolean z;
        BSHWhileStatement bSHWhileStatement = new BSHWhileStatement(30);
        this.jjtree.openNodeScope(bSHWhileStatement);
        jjtreeOpenNodeScope(bSHWhileStatement);
        try {
            jj_consume_token(59);
            jj_consume_token(72);
            Expression();
            jj_consume_token(73);
            Statement();
            this.jjtree.closeNodeScope((Node) bSHWhileStatement, true);
            jjtreeCloseNodeScope(bSHWhileStatement);
        } catch (Throwable th) {
            try {
                this.jjtree.clearNodeScope(bSHWhileStatement);
                z = false;
            } catch (Throwable th2) {
                th = th2;
                z = true;
            }
            try {
                if (th instanceof RuntimeException) {
                    throw ((RuntimeException) th);
                }
                if (!(th instanceof ParseException)) {
                    throw ((Error) th);
                }
                throw ((ParseException) th);
            } catch (Throwable th3) {
                th = th3;
                if (z) {
                    this.jjtree.closeNodeScope((Node) bSHWhileStatement, true);
                    jjtreeCloseNodeScope(bSHWhileStatement);
                }
                throw th;
            }
        }
    }

    ParseException createParseException(String str) {
        Token token = this.token;
        int i2 = token.beginLine;
        int i3 = token.beginColumn;
        if (token.kind == 0) {
            String str2 = ParserConstants.tokenImage[0];
        } else {
            String str3 = token.image;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Parse error at line ");
        stringBuffer.append(i2);
        stringBuffer.append(", column ");
        stringBuffer.append(i3);
        stringBuffer.append(" : ");
        stringBuffer.append(str);
        return new ParseException(stringBuffer.toString());
    }

    public final void disable_tracing() {
    }

    public final void enable_tracing() {
    }

    public ParseException generateParseException() {
        Token token = this.token.next;
        int i2 = token.beginLine;
        int i3 = token.beginColumn;
        String str = token.kind == 0 ? ParserConstants.tokenImage[0] : token.image;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Parse error at line ");
        stringBuffer.append(i2);
        stringBuffer.append(", column ");
        stringBuffer.append(i3);
        stringBuffer.append(".  Encountered: ");
        stringBuffer.append(str);
        return new ParseException(stringBuffer.toString());
    }

    public final Token getNextToken() {
        Token token = this.token;
        Token token2 = token.next;
        if (token2 == null) {
            token2 = this.token_source.getNextToken();
            token.next = token2;
        }
        this.token = token2;
        this.jj_ntk = -1;
        return this.token;
    }

    public final Token getToken(int i2) {
        Token token = this.lookingAhead ? this.jj_scanpos : this.token;
        for (int i3 = 0; i3 < i2; i3++) {
            Token token2 = token.next;
            if (token2 == null) {
                token2 = this.token_source.getNextToken();
                token.next = token2;
            }
            token = token2;
        }
        return token;
    }

    boolean isRegularForStatement() {
        if (getToken(1).kind != 30) {
            return false;
        }
        int i2 = 3;
        if (getToken(2).kind != 72) {
            return false;
        }
        while (true) {
            int i3 = i2 + 1;
            int i4 = getToken(i2).kind;
            if (i4 == 0) {
                return false;
            }
            if (i4 == 78) {
                return true;
            }
            if (i4 == 89) {
                return false;
            }
            i2 = i3;
        }
    }

    void jjtreeCloseNodeScope(Node node) {
        ((SimpleNode) node).lastToken = getToken(0);
    }

    void jjtreeOpenNodeScope(Node node) {
        ((SimpleNode) node).firstToken = getToken(1);
    }

    public SimpleNode popNode() {
        if (this.jjtree.nodeArity() > 0) {
            return (SimpleNode) this.jjtree.popNode();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void reInitInput(Reader reader) {
        ReInit(reader);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void reInitTokenInput(Reader reader) {
        JavaCharStream javaCharStream = this.jj_input_stream;
        javaCharStream.ReInit(reader, javaCharStream.getEndLine(), this.jj_input_stream.getEndColumn());
    }

    public void setRetainComments(boolean z) {
        this.retainComments = z;
    }
}
