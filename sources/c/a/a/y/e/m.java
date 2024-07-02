package c.a.a.y.e;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class m extends c {
    @Override // c.a.a.y.e.c
    int c(char c2, StringBuilder sb) {
        int i2;
        int i3;
        char c3;
        if (c2 == ' ') {
            c3 = 3;
        } else {
            if (c2 >= '0' && c2 <= '9') {
                i3 = (c2 - '0') + 4;
            } else {
                if (c2 < 'a' || c2 > 'z') {
                    if (c2 < 0 || c2 > 31) {
                        char c4 = '!';
                        if (c2 < '!' || c2 > '/') {
                            if (c2 >= ':' && c2 <= '@') {
                                sb.append((char) 1);
                                i2 = (c2 - ':') + 15;
                            } else if (c2 < '[' || c2 > '_') {
                                c4 = '`';
                                if (c2 == '`') {
                                    sb.append((char) 2);
                                } else if (c2 >= 'A' && c2 <= 'Z') {
                                    sb.append((char) 2);
                                    i2 = (c2 - 'A') + 1;
                                } else {
                                    if (c2 < '{' || c2 > 127) {
                                        if (c2 >= 128) {
                                            sb.append("\u0001\u001e");
                                            return c((char) (c2 - 128), sb) + 2;
                                        }
                                        j.e(c2);
                                        throw null;
                                    }
                                    sb.append((char) 2);
                                    i2 = (c2 - '{') + 27;
                                }
                            } else {
                                sb.append((char) 1);
                                i2 = (c2 - '[') + 22;
                            }
                            c2 = (char) i2;
                        } else {
                            sb.append((char) 1);
                        }
                        i2 = c2 - c4;
                        c2 = (char) i2;
                    } else {
                        sb.append((char) 0);
                    }
                    sb.append(c2);
                    return 2;
                }
                i3 = (c2 - 'a') + 14;
            }
            c3 = (char) i3;
        }
        sb.append(c3);
        return 1;
    }

    @Override // c.a.a.y.e.c
    public int e() {
        return 2;
    }
}
