package bsh.org.objectweb.asm;

/* loaded from: classes.dex */
public class CodeWriter implements CodeVisitor {
    static final boolean CHECK = false;
    private static final int[] SIZE;
    private static Edge pool;
    private int access;
    private Label blockStack;
    private int catchCount;
    private ByteVector catchTable;
    private ByteVector code = new ByteVector();
    private final boolean computeMaxs;
    private Label currentBlock;
    private ClassWriter cw;
    private Item desc;
    private int exceptionCount;
    private int[] exceptions;
    private Edge head;
    private ByteVector lineNumber;
    private int lineNumberCount;
    private ByteVector localVar;
    private int localVarCount;
    private int maxLocals;
    private int maxStack;
    private int maxStackSize;
    private Item name;
    CodeWriter next;
    private boolean resize;
    private int stackSize;
    private Edge tail;

    static {
        int[] iArr = new int[202];
        for (int i2 = 0; i2 < 202; i2++) {
            iArr[i2] = "EFFFFFFFFGGFFFGGFFFEEFGFGFEEEEEEEEEEEEEEEEEEEEDEDEDDDDDCDCDEEEEEEEEEEEEEEEEEEEEBABABBBBDCFFFGGGEDCDCDCDCDCDCDCDCDCDCEEEEDDDDDDDCDCDCEFEFDDEEFFDEDEEEBDDBBDDDDDDCCCCCCCCEFEDDDCDCDEEEEEEEEEEFEEEEEEDDEEDDEE".charAt(i2) - 'E';
        }
        SIZE = iArr;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public CodeWriter(ClassWriter classWriter, boolean z) {
        if (classWriter.firstMethod == null) {
            classWriter.firstMethod = this;
        } else {
            classWriter.lastMethod.next = this;
        }
        classWriter.lastMethod = this;
        this.cw = classWriter;
        this.computeMaxs = z;
        if (z) {
            Label label = new Label();
            this.currentBlock = label;
            label.pushed = true;
            this.blockStack = label;
        }
    }

    private void addSuccessor(int i2, Label label) {
        Edge edge;
        synchronized (SIZE) {
            edge = pool;
            if (edge == null) {
                edge = new Edge();
            } else {
                pool = edge.poolNext;
            }
        }
        if (this.tail == null) {
            this.tail = edge;
        }
        edge.poolNext = this.head;
        this.head = edge;
        edge.stackSize = i2;
        edge.successor = label;
        Label label2 = this.currentBlock;
        edge.next = label2.successors;
        label2.successors = edge;
    }

    private static int getArgumentsAndReturnSizes(String str) {
        int i2;
        char charAt;
        int i3 = 1;
        int i4 = 1;
        int i5 = 1;
        while (true) {
            i2 = i4 + 1;
            char charAt2 = str.charAt(i4);
            if (charAt2 == ')') {
                break;
            }
            if (charAt2 == 'L') {
                while (true) {
                    i4 = i2 + 1;
                    if (str.charAt(i2) == ';') {
                        break;
                    }
                    i2 = i4;
                }
                i5++;
            } else if (charAt2 == '[') {
                i4 = i2;
                while (true) {
                    charAt = str.charAt(i4);
                    if (charAt != '[') {
                        break;
                    }
                    i4++;
                }
                if (charAt == 'D' || charAt == 'J') {
                    i5--;
                }
            } else {
                i5 = (charAt2 == 'D' || charAt2 == 'J') ? i5 + 2 : i5 + 1;
                i4 = i2;
            }
        }
        char charAt3 = str.charAt(i2);
        int i6 = i5 << 2;
        if (charAt3 == 'V') {
            i3 = 0;
        } else if (charAt3 == 'D' || charAt3 == 'J') {
            i3 = 2;
        }
        return i6 | i3;
    }

    static int getNewOffset(int[] iArr, int[] iArr2, int i2, int i3) {
        int i4 = i3 - i2;
        for (int i5 = 0; i5 < iArr.length; i5++) {
            if (i2 < iArr[i5] && iArr[i5] <= i3) {
                i4 += iArr2[i5];
            } else if (i3 < iArr[i5] && iArr[i5] <= i2) {
                i4 -= iArr2[i5];
            }
        }
        return i4;
    }

    static int readInt(byte[] bArr, int i2) {
        return (bArr[i2 + 3] & 255) | ((bArr[i2] & 255) << 24) | ((bArr[i2 + 1] & 255) << 16) | ((bArr[i2 + 2] & 255) << 8);
    }

    static short readShort(byte[] bArr, int i2) {
        return (short) ((bArr[i2 + 1] & 255) | ((bArr[i2] & 255) << 8));
    }

    static int readUnsignedShort(byte[] bArr, int i2) {
        return (bArr[i2 + 1] & 255) | ((bArr[i2] & 255) << 8);
    }

    static void writeShort(byte[] bArr, int i2, int i3) {
        bArr[i2] = (byte) (i3 >>> 8);
        bArr[i2 + 1] = (byte) i3;
    }

    protected byte[] getCode() {
        return this.code.data;
    }

    protected int getCodeSize() {
        return this.code.length;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int getSize() {
        int i2;
        if (this.resize) {
            resizeInstructions(new int[0], new int[0], 0);
        }
        if (this.code.length > 0) {
            this.cw.newUTF8("Code");
            i2 = this.code.length + 18 + (this.catchCount * 8) + 8;
            ByteVector byteVector = this.localVar;
            if (byteVector != null) {
                i2 += byteVector.length + 8;
            }
            ByteVector byteVector2 = this.lineNumber;
            if (byteVector2 != null) {
                i2 += byteVector2.length + 8;
            }
        } else {
            i2 = 8;
        }
        if (this.exceptionCount > 0) {
            this.cw.newUTF8("Exceptions");
            i2 += (this.exceptionCount * 2) + 8;
        }
        if ((this.access & 65536) != 0) {
            this.cw.newUTF8("Synthetic");
            i2 += 6;
        }
        if ((this.access & 131072) == 0) {
            return i2;
        }
        this.cw.newUTF8("Deprecated");
        return i2 + 6;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void init(int i2, String str, String str2, String[] strArr) {
        this.access = i2;
        this.name = this.cw.newUTF8(str);
        this.desc = this.cw.newUTF8(str2);
        if (strArr != null && strArr.length > 0) {
            int length = strArr.length;
            this.exceptionCount = length;
            this.exceptions = new int[length];
            for (int i3 = 0; i3 < this.exceptionCount; i3++) {
                this.exceptions[i3] = this.cw.newClass(strArr[i3]).index;
            }
        }
        if (this.computeMaxs) {
            int argumentsAndReturnSizes = getArgumentsAndReturnSizes(str2) >> 2;
            if ((i2 & 8) != 0) {
                argumentsAndReturnSizes--;
            }
            if (argumentsAndReturnSizes > this.maxLocals) {
                this.maxLocals = argumentsAndReturnSizes;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void put(ByteVector byteVector) {
        byteVector.put2(this.access).put2(this.name.index).put2(this.desc.index);
        int i2 = this.code.length > 0 ? 1 : 0;
        if (this.exceptionCount > 0) {
            i2++;
        }
        int i3 = this.access;
        if ((i3 & 65536) != 0) {
            i2++;
        }
        if ((i3 & 131072) != 0) {
            i2++;
        }
        byteVector.put2(i2);
        int i4 = this.code.length;
        if (i4 > 0) {
            int i5 = i4 + 12 + (this.catchCount * 8);
            ByteVector byteVector2 = this.localVar;
            if (byteVector2 != null) {
                i5 += byteVector2.length + 8;
            }
            ByteVector byteVector3 = this.lineNumber;
            if (byteVector3 != null) {
                i5 += byteVector3.length + 8;
            }
            byteVector.put2(this.cw.newUTF8("Code").index).put4(i5);
            byteVector.put2(this.maxStack).put2(this.maxLocals);
            ByteVector put4 = byteVector.put4(this.code.length);
            ByteVector byteVector4 = this.code;
            put4.putByteArray(byteVector4.data, 0, byteVector4.length);
            byteVector.put2(this.catchCount);
            if (this.catchCount > 0) {
                ByteVector byteVector5 = this.catchTable;
                byteVector.putByteArray(byteVector5.data, 0, byteVector5.length);
            }
            int i6 = this.localVar == null ? 0 : 1;
            if (this.lineNumber != null) {
                i6++;
            }
            byteVector.put2(i6);
            if (this.localVar != null) {
                byteVector.put2(this.cw.newUTF8("LocalVariableTable").index);
                byteVector.put4(this.localVar.length + 2).put2(this.localVarCount);
                ByteVector byteVector6 = this.localVar;
                byteVector.putByteArray(byteVector6.data, 0, byteVector6.length);
            }
            if (this.lineNumber != null) {
                byteVector.put2(this.cw.newUTF8("LineNumberTable").index);
                byteVector.put4(this.lineNumber.length + 2).put2(this.lineNumberCount);
                ByteVector byteVector7 = this.lineNumber;
                byteVector.putByteArray(byteVector7.data, 0, byteVector7.length);
            }
        }
        if (this.exceptionCount > 0) {
            byteVector.put2(this.cw.newUTF8("Exceptions").index).put4((this.exceptionCount * 2) + 2);
            byteVector.put2(this.exceptionCount);
            for (int i7 = 0; i7 < this.exceptionCount; i7++) {
                byteVector.put2(this.exceptions[i7]);
            }
        }
        if ((this.access & 65536) != 0) {
            byteVector.put2(this.cw.newUTF8("Synthetic").index).put4(0);
        }
        if ((this.access & 131072) != 0) {
            byteVector.put2(this.cw.newUTF8("Deprecated").index).put4(0);
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:8:0x024d. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:92:0x00cc. Please report as an issue. */
    protected int[] resizeInstructions(int[] iArr, int[] iArr2, int i2) {
        char c2;
        int readShort;
        int readShort2;
        int i3;
        int i4;
        byte[] bArr = this.code.data;
        int[] iArr3 = new int[i2];
        int[] iArr4 = new int[i2];
        int i5 = 0;
        System.arraycopy(iArr, 0, iArr3, 0, i2);
        System.arraycopy(iArr2, 0, iArr4, 0, i2);
        boolean[] zArr = new boolean[this.code.length];
        int i6 = 3;
        do {
            if (i6 == 3) {
                i6 = 2;
            }
            int i7 = 0;
            while (true) {
                c2 = 132;
                if (i7 < bArr.length) {
                    int i8 = bArr[i7] & 255;
                    switch (ClassWriter.TYPE[i8]) {
                        case 0:
                        case 4:
                            i7++;
                            i3 = 0;
                            break;
                        case 1:
                        case 3:
                        case 10:
                            i7 += 2;
                            i3 = 0;
                            break;
                        case 2:
                        case 5:
                        case 6:
                        case 11:
                        case 12:
                            i7 += 3;
                            i3 = 0;
                            break;
                        case 7:
                        case 9:
                            i7 += 5;
                            i3 = 0;
                            break;
                        case 8:
                            if (i8 > 201) {
                                i8 = i8 < 218 ? i8 - 49 : i8 - 20;
                                readShort2 = readUnsignedShort(bArr, i7 + 1);
                            } else {
                                readShort2 = readShort(bArr, i7 + 1);
                            }
                            int newOffset = getNewOffset(iArr3, iArr4, i7, readShort2 + i7);
                            if ((newOffset < -32768 || newOffset > 32767) && !zArr[i7]) {
                                int i9 = (i8 == 167 || i8 == 168) ? 2 : 5;
                                zArr[i7] = true;
                                i3 = i9;
                            } else {
                                i3 = 0;
                            }
                            i7 += 3;
                            break;
                        case 13:
                            if (i6 == 1) {
                                i3 = -(getNewOffset(iArr3, iArr4, 0, i7) & 3);
                            } else if (zArr[i7]) {
                                i3 = 0;
                            } else {
                                i3 = i7 & 3;
                                zArr[i7] = true;
                            }
                            int i10 = (i7 + 4) - (i7 & 3);
                            i7 = i10 + (((readInt(bArr, i10 + 8) - readInt(bArr, i10 + 4)) + 1) * 4) + 12;
                            break;
                        case 14:
                            if (i6 == 1) {
                                i4 = -(getNewOffset(iArr3, iArr4, 0, i7) & 3);
                            } else if (zArr[i7]) {
                                i4 = 0;
                            } else {
                                i4 = i7 & 3;
                                zArr[i7] = true;
                            }
                            int i11 = (i7 + 4) - (i7 & 3);
                            i7 = i11 + (readInt(bArr, i11 + 4) * 8) + 8;
                            i3 = i4;
                            break;
                        case 16:
                            if ((bArr[i7 + 1] & 255) == 132) {
                                i7 += 6;
                                i3 = 0;
                                break;
                            }
                        case 15:
                        default:
                            i7 += 4;
                            i3 = 0;
                            break;
                    }
                    if (i3 != 0) {
                        int[] iArr5 = new int[iArr3.length + 1];
                        int[] iArr6 = new int[iArr4.length + 1];
                        System.arraycopy(iArr3, 0, iArr5, 0, iArr3.length);
                        System.arraycopy(iArr4, 0, iArr6, 0, iArr4.length);
                        iArr5[iArr3.length] = i7;
                        iArr6[iArr4.length] = i3;
                        iArr3 = iArr5;
                        iArr4 = iArr6;
                        i5 = 0;
                        if (i3 > 0) {
                            i6 = 3;
                        }
                    } else {
                        i5 = 0;
                    }
                } else if (i6 < 3) {
                    i6--;
                }
            }
        } while (i6 != 0);
        ByteVector byteVector = new ByteVector(this.code.length);
        int i12 = 0;
        while (i12 < this.code.length) {
            int length = iArr3.length - 1;
            while (length >= 0) {
                if (iArr3[length] == i12 && length < i2) {
                    if (iArr2[length] > 0) {
                        byteVector.putByteArray(null, 0, iArr2[length]);
                    } else {
                        byteVector.length += iArr2[length];
                    }
                    iArr[length] = byteVector.length;
                }
                length--;
                i5 = 0;
                c2 = 132;
            }
            int i13 = bArr[i12] & 255;
            switch (ClassWriter.TYPE[i13]) {
                case 0:
                case 4:
                    byteVector.put1(i13);
                    i12++;
                    i5 = 0;
                    c2 = 132;
                    break;
                case 1:
                case 3:
                case 10:
                    byteVector.putByteArray(bArr, i12, 2);
                    i12 += 2;
                    i5 = 0;
                    c2 = 132;
                    break;
                case 2:
                case 5:
                case 6:
                case 11:
                case 12:
                    byteVector.putByteArray(bArr, i12, 3);
                    i12 += 3;
                    i5 = 0;
                    c2 = 132;
                    break;
                case 7:
                    byteVector.putByteArray(bArr, i12, 5);
                    i12 += 5;
                    i5 = 0;
                    c2 = 132;
                    break;
                case 8:
                    if (i13 > 201) {
                        i13 = i13 < 218 ? i13 - 49 : i13 - 20;
                        readShort = readUnsignedShort(bArr, i12 + 1);
                    } else {
                        readShort = readShort(bArr, i12 + 1);
                    }
                    int newOffset2 = getNewOffset(iArr3, iArr4, i12, readShort + i12);
                    if (newOffset2 < -32768 || newOffset2 > 32767) {
                        if (i13 == 167) {
                            byteVector.put1(200);
                        } else if (i13 == 168) {
                            byteVector.put1(201);
                        } else {
                            byteVector.put1(i13 <= 166 ? ((i13 + 1) ^ 1) - 1 : i13 ^ 1);
                            byteVector.put2(8);
                            byteVector.put1(200);
                            newOffset2 -= 3;
                        }
                        byteVector.put4(newOffset2);
                    } else {
                        byteVector.put1(i13);
                        byteVector.put2(newOffset2);
                    }
                    i12 += 3;
                    i5 = 0;
                    c2 = 132;
                    break;
                case 9:
                    int newOffset3 = getNewOffset(iArr3, iArr4, i12, readInt(bArr, i12 + 1) + i12);
                    byteVector.put1(i13);
                    byteVector.put4(newOffset3);
                    i12 += 5;
                    c2 = 132;
                    break;
                case 13:
                    int i14 = (i12 + 4) - (i12 & 3);
                    byteVector.put1(Constants.TABLESWITCH);
                    while (byteVector.length % 4 != 0) {
                        byteVector.put1(i5);
                    }
                    int readInt = readInt(bArr, i14) + i12;
                    int i15 = i14 + 4;
                    byteVector.put4(getNewOffset(iArr3, iArr4, i12, readInt));
                    int readInt2 = readInt(bArr, i15);
                    int i16 = i15 + 4;
                    byteVector.put4(readInt2);
                    int i17 = i16 + 4;
                    byteVector.put4(readInt(bArr, i17 - 4));
                    for (int readInt3 = (readInt(bArr, i16) - readInt2) + 1; readInt3 > 0; readInt3--) {
                        int readInt4 = readInt(bArr, i17) + i12;
                        i17 += 4;
                        byteVector.put4(getNewOffset(iArr3, iArr4, i12, readInt4));
                    }
                    i12 = i17;
                    c2 = 132;
                    break;
                case 14:
                    int i18 = (i12 + 4) - (i12 & 3);
                    byteVector.put1(Constants.LOOKUPSWITCH);
                    while (byteVector.length % 4 != 0) {
                        byteVector.put1(i5);
                        c2 = 132;
                    }
                    int readInt5 = readInt(bArr, i18) + i12;
                    int i19 = i18 + 4;
                    byteVector.put4(getNewOffset(iArr3, iArr4, i12, readInt5));
                    int readInt6 = readInt(bArr, i19);
                    int i20 = i19 + 4;
                    byteVector.put4(readInt6);
                    while (readInt6 > 0) {
                        byteVector.put4(readInt(bArr, i20));
                        int i21 = i20 + 4;
                        int readInt7 = readInt(bArr, i21) + i12;
                        i20 = i21 + 4;
                        byteVector.put4(getNewOffset(iArr3, iArr4, i12, readInt7));
                        readInt6--;
                        c2 = 132;
                    }
                    i12 = i20;
                    break;
                case 15:
                default:
                    byteVector.putByteArray(bArr, i12, 4);
                    i12 += 4;
                    i5 = 0;
                    c2 = 132;
                    break;
                case 16:
                    if ((bArr[i12 + 1] & 255) == c2) {
                        byteVector.putByteArray(bArr, i12, 6);
                        i12 += 6;
                        break;
                    } else {
                        byteVector.putByteArray(bArr, i12, 4);
                        i12 += 4;
                        break;
                    }
            }
        }
        ByteVector byteVector2 = this.catchTable;
        if (byteVector2 != null) {
            byte[] bArr2 = byteVector2.data;
            for (int i22 = 0; i22 < this.catchTable.length; i22 += 8) {
                writeShort(bArr2, i22, getNewOffset(iArr3, iArr4, i5, readUnsignedShort(bArr2, i22)));
                int i23 = i22 + 2;
                writeShort(bArr2, i23, getNewOffset(iArr3, iArr4, i5, readUnsignedShort(bArr2, i23)));
                int i24 = i22 + 4;
                writeShort(bArr2, i24, getNewOffset(iArr3, iArr4, i5, readUnsignedShort(bArr2, i24)));
            }
        }
        ByteVector byteVector3 = this.localVar;
        if (byteVector3 != null) {
            byte[] bArr3 = byteVector3.data;
            for (int i25 = 0; i25 < this.localVar.length; i25 += 10) {
                int readUnsignedShort = readUnsignedShort(bArr3, i25);
                int newOffset4 = getNewOffset(iArr3, iArr4, i5, readUnsignedShort);
                writeShort(bArr3, i25, newOffset4);
                writeShort(bArr3, i25, getNewOffset(iArr3, iArr4, i5, readUnsignedShort + readUnsignedShort(bArr3, i25 + 2)) - newOffset4);
            }
        }
        ByteVector byteVector4 = this.lineNumber;
        if (byteVector4 != null) {
            byte[] bArr4 = byteVector4.data;
            for (int i26 = 0; i26 < this.lineNumber.length; i26 += 4) {
                writeShort(bArr4, i26, getNewOffset(iArr3, iArr4, i5, readUnsignedShort(bArr4, i26)));
            }
        }
        this.code = byteVector;
        return iArr;
    }

    @Override // bsh.org.objectweb.asm.CodeVisitor
    public void visitFieldInsn(int i2, String str, String str2, String str3) {
        int i3;
        int i4;
        if (this.computeMaxs) {
            char charAt = str3.charAt(0);
            int i5 = -2;
            switch (i2) {
                case Constants.GETSTATIC /* 178 */:
                    i3 = this.stackSize + ((charAt == 'D' || charAt == 'J') ? 2 : 1);
                    break;
                case Constants.PUTSTATIC /* 179 */:
                    i4 = this.stackSize;
                    if (charAt != 'D' && charAt != 'J') {
                        i5 = -1;
                    }
                    i3 = i5 + i4;
                    break;
                case Constants.GETFIELD /* 180 */:
                    i3 = this.stackSize + ((charAt == 'D' || charAt == 'J') ? 1 : 0);
                    break;
                default:
                    i4 = this.stackSize;
                    if (charAt == 'D' || charAt == 'J') {
                        i5 = -3;
                    }
                    i3 = i5 + i4;
                    break;
            }
            if (i3 > this.maxStackSize) {
                this.maxStackSize = i3;
            }
            this.stackSize = i3;
        }
        this.code.put12(i2, this.cw.newField(str, str2, str3).index);
    }

    @Override // bsh.org.objectweb.asm.CodeVisitor
    public void visitIincInsn(int i2, int i3) {
        int i4;
        if (this.computeMaxs && (i4 = i2 + 1) > this.maxLocals) {
            this.maxLocals = i4;
        }
        if (i2 > 255 || i3 > 127 || i3 < -128) {
            this.code.put1(196).put12(132, i2).put2(i3);
        } else {
            this.code.put1(132).put11(i2, i3);
        }
    }

    @Override // bsh.org.objectweb.asm.CodeVisitor
    public void visitInsn(int i2) {
        Label label;
        if (this.computeMaxs) {
            int i3 = this.stackSize + SIZE[i2];
            if (i3 > this.maxStackSize) {
                this.maxStackSize = i3;
            }
            this.stackSize = i3;
            if (((i2 >= 172 && i2 <= 177) || i2 == 191) && (label = this.currentBlock) != null) {
                label.maxStackSize = this.maxStackSize;
                this.currentBlock = null;
            }
        }
        this.code.put1(i2);
    }

    @Override // bsh.org.objectweb.asm.CodeVisitor
    public void visitIntInsn(int i2, int i3) {
        if (this.computeMaxs && i2 != 188) {
            int i4 = this.stackSize + 1;
            if (i4 > this.maxStackSize) {
                this.maxStackSize = i4;
            }
            this.stackSize = i4;
        }
        if (i2 == 17) {
            this.code.put12(i2, i3);
        } else {
            this.code.put11(i2, i3);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0031, code lost:
    
        if (r6.currentBlock != null) goto L14;
     */
    @Override // bsh.org.objectweb.asm.CodeVisitor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void visitJumpInsn(int r7, bsh.org.objectweb.asm.Label r8) {
        /*
            r6 = this;
            boolean r0 = r6.computeMaxs
            r1 = 168(0xa8, float:2.35E-43)
            r2 = 167(0xa7, float:2.34E-43)
            r3 = 1
            if (r0 == 0) goto L36
            if (r7 != r2) goto L1c
            bsh.org.objectweb.asm.Label r0 = r6.currentBlock
            if (r0 == 0) goto L36
            int r4 = r6.maxStackSize
            r0.maxStackSize = r4
            int r0 = r6.stackSize
            r6.addSuccessor(r0, r8)
            r0 = 0
            r6.currentBlock = r0
            goto L36
        L1c:
            if (r7 != r1) goto L26
            bsh.org.objectweb.asm.Label r0 = r6.currentBlock
            if (r0 == 0) goto L36
            int r0 = r6.stackSize
            int r0 = r0 + r3
            goto L33
        L26:
            int r0 = r6.stackSize
            int[] r4 = bsh.org.objectweb.asm.CodeWriter.SIZE
            r4 = r4[r7]
            int r0 = r0 + r4
            r6.stackSize = r0
            bsh.org.objectweb.asm.Label r4 = r6.currentBlock
            if (r4 == 0) goto L36
        L33:
            r6.addSuccessor(r0, r8)
        L36:
            boolean r0 = r8.resolved
            if (r0 == 0) goto L76
            int r0 = r8.position
            bsh.org.objectweb.asm.ByteVector r4 = r6.code
            int r5 = r4.length
            int r0 = r0 - r5
            r5 = -32768(0xffffffffffff8000, float:NaN)
            if (r0 >= r5) goto L76
            r0 = 200(0xc8, float:2.8E-43)
            if (r7 != r2) goto L4d
            r4.put1(r0)
            goto L6d
        L4d:
            if (r7 != r1) goto L55
            r7 = 201(0xc9, float:2.82E-43)
            r4.put1(r7)
            goto L6d
        L55:
            r1 = 166(0xa6, float:2.33E-43)
            if (r7 > r1) goto L5d
            int r7 = r7 + r3
            r7 = r7 ^ r3
            int r7 = r7 - r3
            goto L5e
        L5d:
            r7 = r7 ^ r3
        L5e:
            r4.put1(r7)
            bsh.org.objectweb.asm.ByteVector r7 = r6.code
            r1 = 8
            r7.put2(r1)
            bsh.org.objectweb.asm.ByteVector r7 = r6.code
            r7.put1(r0)
        L6d:
            bsh.org.objectweb.asm.ByteVector r7 = r6.code
            int r0 = r7.length
            int r0 = r0 - r3
            r8.put(r6, r7, r0, r3)
            goto L84
        L76:
            bsh.org.objectweb.asm.ByteVector r0 = r6.code
            r0.put1(r7)
            bsh.org.objectweb.asm.ByteVector r7 = r6.code
            int r0 = r7.length
            int r0 = r0 - r3
            r1 = 0
            r8.put(r6, r7, r0, r1)
        L84:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: bsh.org.objectweb.asm.CodeWriter.visitJumpInsn(int, bsh.org.objectweb.asm.Label):void");
    }

    @Override // bsh.org.objectweb.asm.CodeVisitor
    public void visitLabel(Label label) {
        if (this.computeMaxs) {
            Label label2 = this.currentBlock;
            if (label2 != null) {
                label2.maxStackSize = this.maxStackSize;
                addSuccessor(this.stackSize, label);
            }
            this.currentBlock = label;
            this.stackSize = 0;
            this.maxStackSize = 0;
        }
        boolean z = this.resize;
        ByteVector byteVector = this.code;
        this.resize = label.resolve(this, byteVector.length, byteVector.data) | z;
    }

    @Override // bsh.org.objectweb.asm.CodeVisitor
    public void visitLdcInsn(Object obj) {
        ByteVector byteVector;
        int i2;
        Item newCst = this.cw.newCst(obj);
        if (this.computeMaxs) {
            int i3 = newCst.type;
            int i4 = (i3 == 5 || i3 == 6) ? this.stackSize + 2 : this.stackSize + 1;
            if (i4 > this.maxStackSize) {
                this.maxStackSize = i4;
            }
            this.stackSize = i4;
        }
        short s = newCst.index;
        int i5 = newCst.type;
        if (i5 == 5 || i5 == 6) {
            byteVector = this.code;
            i2 = 20;
        } else if (s < 256) {
            this.code.put11(18, s);
            return;
        } else {
            byteVector = this.code;
            i2 = 19;
        }
        byteVector.put12(i2, s);
    }

    @Override // bsh.org.objectweb.asm.CodeVisitor
    public void visitLineNumber(int i2, Label label) {
        if (this.lineNumber == null) {
            this.cw.newUTF8("LineNumberTable");
            this.lineNumber = new ByteVector();
        }
        this.lineNumberCount++;
        this.lineNumber.put2(label.position);
        this.lineNumber.put2(i2);
    }

    @Override // bsh.org.objectweb.asm.CodeVisitor
    public void visitLocalVariable(String str, String str2, Label label, Label label2, int i2) {
        if (this.localVar == null) {
            this.cw.newUTF8("LocalVariableTable");
            this.localVar = new ByteVector();
        }
        this.localVarCount++;
        this.localVar.put2(label.position);
        this.localVar.put2(label2.position - label.position);
        this.localVar.put2(this.cw.newUTF8(str).index);
        this.localVar.put2(this.cw.newUTF8(str2).index);
        this.localVar.put2(i2);
    }

    @Override // bsh.org.objectweb.asm.CodeVisitor
    public void visitLookupSwitchInsn(Label label, int[] iArr, Label[] labelArr) {
        ByteVector byteVector;
        if (this.computeMaxs) {
            int i2 = this.stackSize - 1;
            this.stackSize = i2;
            Label label2 = this.currentBlock;
            if (label2 != null) {
                label2.maxStackSize = this.maxStackSize;
                addSuccessor(i2, label);
                for (Label label3 : labelArr) {
                    addSuccessor(this.stackSize, label3);
                }
                this.currentBlock = null;
            }
        }
        ByteVector byteVector2 = this.code;
        int i3 = byteVector2.length;
        byteVector2.put1(Constants.LOOKUPSWITCH);
        while (true) {
            byteVector = this.code;
            if (byteVector.length % 4 == 0) {
                break;
            } else {
                byteVector.put1(0);
            }
        }
        label.put(this, byteVector, i3, true);
        this.code.put4(labelArr.length);
        for (int i4 = 0; i4 < labelArr.length; i4++) {
            this.code.put4(iArr[i4]);
            labelArr[i4].put(this, this.code, i3, true);
        }
    }

    @Override // bsh.org.objectweb.asm.CodeVisitor
    public void visitMaxs(int i2, int i3) {
        if (!this.computeMaxs) {
            this.maxStack = i2;
            this.maxLocals = i3;
            return;
        }
        int i4 = 0;
        Label label = this.blockStack;
        while (label != null) {
            Label label2 = label.next;
            int i5 = label.beginStackSize;
            int i6 = label.maxStackSize + i5;
            if (i6 <= i4) {
                i6 = i4;
            }
            label = label2;
            for (Edge edge = label.successors; edge != null; edge = edge.next) {
                Label label3 = edge.successor;
                if (!label3.pushed) {
                    label3.beginStackSize = edge.stackSize + i5;
                    label3.pushed = true;
                    label3.next = label;
                    label = label3;
                }
            }
            i4 = i6;
        }
        this.maxStack = i4;
        synchronized (SIZE) {
            Edge edge2 = this.tail;
            if (edge2 != null) {
                edge2.poolNext = pool;
                pool = this.head;
            }
        }
    }

    @Override // bsh.org.objectweb.asm.CodeVisitor
    public void visitMethodInsn(int i2, String str, String str2, String str3) {
        ClassWriter classWriter = this.cw;
        Item newItfMethod = i2 == 185 ? classWriter.newItfMethod(str, str2, str3) : classWriter.newMethod(str, str2, str3);
        int i3 = newItfMethod.intVal;
        if (this.computeMaxs) {
            if (i3 == 0) {
                i3 = getArgumentsAndReturnSizes(str3);
                newItfMethod.intVal = i3;
            }
            int i4 = i2 == 184 ? (this.stackSize - (i3 >> 2)) + (i3 & 3) + 1 : (this.stackSize - (i3 >> 2)) + (i3 & 3);
            if (i4 > this.maxStackSize) {
                this.maxStackSize = i4;
            }
            this.stackSize = i4;
        }
        if (i2 != 185) {
            this.code.put12(i2, newItfMethod.index);
            return;
        }
        if (!this.computeMaxs && i3 == 0) {
            i3 = getArgumentsAndReturnSizes(str3);
            newItfMethod.intVal = i3;
        }
        this.code.put12(Constants.INVOKEINTERFACE, newItfMethod.index).put11(i3 >> 2, 0);
    }

    @Override // bsh.org.objectweb.asm.CodeVisitor
    public void visitMultiANewArrayInsn(String str, int i2) {
        if (this.computeMaxs) {
            this.stackSize += 1 - i2;
        }
        this.code.put12(Constants.MULTIANEWARRAY, this.cw.newClass(str).index).put1(i2);
    }

    @Override // bsh.org.objectweb.asm.CodeVisitor
    public void visitTableSwitchInsn(int i2, int i3, Label label, Label[] labelArr) {
        ByteVector byteVector;
        if (this.computeMaxs) {
            int i4 = this.stackSize - 1;
            this.stackSize = i4;
            Label label2 = this.currentBlock;
            if (label2 != null) {
                label2.maxStackSize = this.maxStackSize;
                addSuccessor(i4, label);
                for (Label label3 : labelArr) {
                    addSuccessor(this.stackSize, label3);
                }
                this.currentBlock = null;
            }
        }
        ByteVector byteVector2 = this.code;
        int i5 = byteVector2.length;
        byteVector2.put1(Constants.TABLESWITCH);
        while (true) {
            byteVector = this.code;
            if (byteVector.length % 4 == 0) {
                break;
            } else {
                byteVector.put1(0);
            }
        }
        label.put(this, byteVector, i5, true);
        this.code.put4(i2).put4(i3);
        for (Label label4 : labelArr) {
            label4.put(this, this.code, i5, true);
        }
    }

    @Override // bsh.org.objectweb.asm.CodeVisitor
    public void visitTryCatchBlock(Label label, Label label2, Label label3, String str) {
        if (this.computeMaxs && !label3.pushed) {
            label3.beginStackSize = 1;
            label3.pushed = true;
            label3.next = this.blockStack;
            this.blockStack = label3;
        }
        this.catchCount++;
        if (this.catchTable == null) {
            this.catchTable = new ByteVector();
        }
        this.catchTable.put2(label.position);
        this.catchTable.put2(label2.position);
        this.catchTable.put2(label3.position);
        this.catchTable.put2(str != null ? this.cw.newClass(str).index : (short) 0);
    }

    @Override // bsh.org.objectweb.asm.CodeVisitor
    public void visitTypeInsn(int i2, String str) {
        if (this.computeMaxs && i2 == 187) {
            int i3 = this.stackSize + 1;
            if (i3 > this.maxStackSize) {
                this.maxStackSize = i3;
            }
            this.stackSize = i3;
        }
        this.code.put12(i2, this.cw.newClass(str).index);
    }

    @Override // bsh.org.objectweb.asm.CodeVisitor
    public void visitVarInsn(int i2, int i3) {
        if (this.computeMaxs) {
            if (i2 == 169) {
                Label label = this.currentBlock;
                if (label != null) {
                    label.maxStackSize = this.maxStackSize;
                    this.currentBlock = null;
                }
            } else {
                int i4 = this.stackSize + SIZE[i2];
                if (i4 > this.maxStackSize) {
                    this.maxStackSize = i4;
                }
                this.stackSize = i4;
            }
            int i5 = (i2 == 22 || i2 == 24 || i2 == 55 || i2 == 57) ? i3 + 2 : i3 + 1;
            if (i5 > this.maxLocals) {
                this.maxLocals = i5;
            }
        }
        if (i3 < 4 && i2 != 169) {
            this.code.put1((i2 < 54 ? ((i2 - 21) << 2) + 26 : ((i2 - 54) << 2) + 59) + i3);
        } else if (i3 >= 256) {
            this.code.put1(196).put12(i2, i3);
        } else {
            this.code.put11(i2, i3);
        }
    }
}
