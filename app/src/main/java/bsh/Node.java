package bsh;

import java.io.Serializable;

/* loaded from: classes.dex */
interface Node extends Serializable {
    void jjtAddChild(Node node, int i2);

    void jjtClose();

    Node jjtGetChild(int i2);

    int jjtGetNumChildren();

    Node jjtGetParent();

    void jjtOpen();

    void jjtSetParent(Node node);
}
