package bsh;

import java.io.Serializable;

/* loaded from: classes.dex */
public class Token implements Serializable {
    public int beginColumn;
    public int beginLine;
    public int endColumn;
    public int endLine;
    public String image;
    public int kind;
    public Token next;
    public Token specialToken;

    public static final Token newToken(int i2) {
        return new Token();
    }

    public String toString() {
        return this.image;
    }
}
