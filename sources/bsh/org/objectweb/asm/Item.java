package bsh.org.objectweb.asm;

/* loaded from: classes.dex */
final class Item {
    double doubleVal;
    float floatVal;
    int hashCode;
    short index;
    int intVal;
    long longVal;
    Item next;
    String strVal1;
    String strVal2;
    String strVal3;
    int type;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Item() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Item(short s, Item item) {
        this.index = s;
        this.type = item.type;
        this.intVal = item.intVal;
        this.longVal = item.longVal;
        this.floatVal = item.floatVal;
        this.doubleVal = item.doubleVal;
        this.strVal1 = item.strVal1;
        this.strVal2 = item.strVal2;
        this.strVal3 = item.strVal3;
        this.hashCode = item.hashCode;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isEqualTo(Item item) {
        int i2 = item.type;
        int i3 = this.type;
        if (i2 != i3) {
            return false;
        }
        if (i3 != 1) {
            if (i3 == 12) {
                return item.strVal1.equals(this.strVal1) && item.strVal2.equals(this.strVal2);
            }
            switch (i3) {
                case 3:
                    return item.intVal == this.intVal;
                case 4:
                    return item.floatVal == this.floatVal;
                case 5:
                    return item.longVal == this.longVal;
                case 6:
                    return item.doubleVal == this.doubleVal;
                case 7:
                case 8:
                    break;
                default:
                    return item.strVal1.equals(this.strVal1) && item.strVal2.equals(this.strVal2) && item.strVal3.equals(this.strVal3);
            }
        }
        return item.strVal1.equals(this.strVal1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void set(double d2) {
        this.type = 6;
        this.doubleVal = d2;
        this.hashCode = 6 + ((int) d2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void set(float f) {
        this.type = 4;
        this.floatVal = f;
        this.hashCode = 4 + ((int) f);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void set(int i2) {
        this.type = 3;
        this.intVal = i2;
        this.hashCode = 3 + i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void set(int i2, String str, String str2, String str3) {
        int hashCode;
        int hashCode2;
        int hashCode3;
        this.type = i2;
        this.strVal1 = str;
        this.strVal2 = str2;
        this.strVal3 = str3;
        if (i2 != 1) {
            if (i2 == 12) {
                hashCode2 = str.hashCode();
                hashCode3 = str2.hashCode();
            } else if (i2 != 7 && i2 != 8) {
                hashCode2 = str.hashCode() * str2.hashCode();
                hashCode3 = str3.hashCode();
            }
            hashCode = hashCode2 * hashCode3;
            this.hashCode = i2 + hashCode;
        }
        hashCode = str.hashCode();
        this.hashCode = i2 + hashCode;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void set(long j) {
        this.type = 5;
        this.longVal = j;
        this.hashCode = 5 + ((int) j);
    }
}
