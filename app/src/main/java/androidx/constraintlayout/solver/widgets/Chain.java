package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.LinearSystem;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class Chain {
    public static final boolean USE_CHAIN_OPTIMIZATION = false;

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0031, code lost:
    
        if (r8 == 2) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0044, code lost:
    
        r5 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:317:0x0042, code lost:
    
        r5 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:325:0x0040, code lost:
    
        if (r8 == 2) goto L25;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0258 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:136:0x04ca A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:143:0x04de  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x04e7  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x04ee  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x04fe  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x0504 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:162:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:163:0x04ea  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x04e1  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x02af A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:190:0x0396  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x0397 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:202:0x031e  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x0345  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x034c  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x035f  */
    /* JADX WARN: Removed duplicated region for block: B:215:0x036a  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x0379  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x037c  */
    /* JADX WARN: Removed duplicated region for block: B:220:0x0375  */
    /* JADX WARN: Removed duplicated region for block: B:221:0x0330  */
    /* JADX WARN: Removed duplicated region for block: B:239:0x039f A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:247:0x03b2  */
    /* JADX WARN: Removed duplicated region for block: B:267:0x0412  */
    /* JADX WARN: Removed duplicated region for block: B:270:0x041b  */
    /* JADX WARN: Removed duplicated region for block: B:273:0x0427  */
    /* JADX WARN: Removed duplicated region for block: B:287:0x042a  */
    /* JADX WARN: Removed duplicated region for block: B:300:0x0482  */
    /* JADX WARN: Removed duplicated region for block: B:308:0x04b7  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0197  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x01b4  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x01d1  */
    /* JADX WARN: Type inference failed for: r2v55, types: [androidx.constraintlayout.solver.widgets.ConstraintWidget] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [androidx.constraintlayout.solver.widgets.ConstraintWidget] */
    /* JADX WARN: Type inference failed for: r7v32 */
    /* JADX WARN: Type inference failed for: r7v33 */
    /* JADX WARN: Type inference failed for: r7v34 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static void a(androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer r37, androidx.constraintlayout.solver.LinearSystem r38, int r39, int r40, androidx.constraintlayout.solver.widgets.ChainHead r41) {
        /*
            Method dump skipped, instructions count: 1321
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.widgets.Chain.a(androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer, androidx.constraintlayout.solver.LinearSystem, int, int, androidx.constraintlayout.solver.widgets.ChainHead):void");
    }

    public static void applyChainConstraints(ConstraintWidgetContainer constraintWidgetContainer, LinearSystem linearSystem, ArrayList<ConstraintWidget> arrayList, int i2) {
        ChainHead[] chainHeadArr;
        int i3;
        int i4;
        if (i2 == 0) {
            i3 = constraintWidgetContainer.mHorizontalChainsSize;
            chainHeadArr = constraintWidgetContainer.k0;
            i4 = 0;
        } else {
            int i5 = constraintWidgetContainer.mVerticalChainsSize;
            chainHeadArr = constraintWidgetContainer.j0;
            i3 = i5;
            i4 = 2;
        }
        for (int i6 = 0; i6 < i3; i6++) {
            ChainHead chainHead = chainHeadArr[i6];
            chainHead.define();
            if (arrayList == null || (arrayList != null && arrayList.contains(chainHead.a))) {
                a(constraintWidgetContainer, linearSystem, i2, i4, chainHead);
            }
        }
    }
}
