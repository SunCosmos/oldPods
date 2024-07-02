package android.support.v7.widget;

import android.support.v4.util.Pools;
import android.support.v7.widget.OpReorderer;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: lib/Mus.dex */
public class AdapterHelper implements OpReorderer.Callback {
    private static final boolean DEBUG = false;
    static final int POSITION_TYPE_INVISIBLE = 0;
    static final int POSITION_TYPE_NEW_OR_LAID_OUT = 1;
    private static final String TAG = "AHT";
    final Callback mCallback;
    final boolean mDisableRecycler;
    private int mExistingUpdateTypes;
    Runnable mOnItemProcessedCallback;
    final OpReorderer mOpReorderer;
    final ArrayList<UpdateOp> mPendingUpdates;
    final ArrayList<UpdateOp> mPostponedList;
    private Pools.Pool<UpdateOp> mUpdateOpPool;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: lib/Mus.dex */
    public interface Callback {
        RecyclerView.ViewHolder findViewHolder(int i2);

        void markViewHoldersUpdated(int i2, int i3, Object obj);

        void offsetPositionsForAdd(int i2, int i3);

        void offsetPositionsForMove(int i2, int i3);

        void offsetPositionsForRemovingInvisible(int i2, int i3);

        void offsetPositionsForRemovingLaidOutOrNewView(int i2, int i3);

        void onDispatchFirstPass(UpdateOp updateOp);

        void onDispatchSecondPass(UpdateOp updateOp);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AdapterHelper(Callback callback) {
        this(callback, false);
    }

    AdapterHelper(Callback callback, boolean z) {
        this.mUpdateOpPool = new Pools.SimplePool(30);
        this.mPendingUpdates = new ArrayList<>();
        this.mPostponedList = new ArrayList<>();
        this.mExistingUpdateTypes = 0;
        this.mCallback = callback;
        this.mDisableRecycler = z;
        this.mOpReorderer = new OpReorderer(this);
    }

    AdapterHelper addUpdateOp(UpdateOp... updateOpArr) {
        Collections.addAll(this.mPendingUpdates, updateOpArr);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void reset() {
        recycleUpdateOpsAndClearList(this.mPendingUpdates);
        recycleUpdateOpsAndClearList(this.mPostponedList);
        this.mExistingUpdateTypes = 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Failed to find 'out' block for switch in B:5:0x0026. Please report as an issue. */
    public void preProcess() {
        this.mOpReorderer.reorderOps(this.mPendingUpdates);
        int size = this.mPendingUpdates.size();
        for (int i2 = 0; i2 < size; i2++) {
            UpdateOp updateOp = this.mPendingUpdates.get(i2);
            switch (updateOp.cmd) {
                case 1:
                    applyAdd(updateOp);
                    break;
                case 2:
                    applyRemove(updateOp);
                    break;
                case 4:
                    applyUpdate(updateOp);
                    break;
                case 8:
                    applyMove(updateOp);
                    break;
            }
            if (this.mOnItemProcessedCallback != null) {
                this.mOnItemProcessedCallback.run();
            }
        }
        this.mPendingUpdates.clear();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void consumePostponedUpdates() {
        int size = this.mPostponedList.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.mCallback.onDispatchSecondPass(this.mPostponedList.get(i2));
        }
        recycleUpdateOpsAndClearList(this.mPostponedList);
        this.mExistingUpdateTypes = 0;
    }

    private void applyMove(UpdateOp updateOp) {
        postponeAndUpdateViewHolders(updateOp);
    }

    private void applyRemove(UpdateOp updateOp) {
        UpdateOp updateOp2 = updateOp;
        int i2 = updateOp2.positionStart;
        int i3 = 0;
        int i4 = updateOp2.positionStart + updateOp2.itemCount;
        boolean z = -1;
        int i5 = updateOp2.positionStart;
        while (i5 < i4) {
            boolean z2 = false;
            if (this.mCallback.findViewHolder(i5) != null || canFindInPreLayout(i5)) {
                if (!z) {
                    dispatchAndUpdateViewHolders(obtainUpdateOp(2, i2, i3, null));
                    z2 = true;
                }
                z = true;
            } else {
                if (z) {
                    postponeAndUpdateViewHolders(obtainUpdateOp(2, i2, i3, null));
                    z2 = true;
                }
                z = false;
            }
            if (z2) {
                i5 -= i3;
                i4 -= i3;
                i3 = 1;
            } else {
                i3++;
            }
            i5++;
        }
        if (i3 != updateOp2.itemCount) {
            recycleUpdateOp(updateOp2);
            updateOp2 = obtainUpdateOp(2, i2, i3, null);
        }
        if (!z) {
            dispatchAndUpdateViewHolders(updateOp2);
        } else {
            postponeAndUpdateViewHolders(updateOp2);
        }
    }

    private void applyUpdate(UpdateOp updateOp) {
        boolean z;
        UpdateOp updateOp2 = updateOp;
        int i2 = updateOp2.positionStart;
        int i3 = 0;
        int i4 = updateOp2.positionStart + updateOp2.itemCount;
        boolean z2 = -1;
        for (int i5 = updateOp2.positionStart; i5 < i4; i5++) {
            if (this.mCallback.findViewHolder(i5) != null || canFindInPreLayout(i5)) {
                if (!z2) {
                    dispatchAndUpdateViewHolders(obtainUpdateOp(4, i2, i3, updateOp2.payload));
                    i3 = 0;
                    i2 = i5;
                }
                z = true;
            } else {
                if (z2) {
                    postponeAndUpdateViewHolders(obtainUpdateOp(4, i2, i3, updateOp2.payload));
                    i3 = 0;
                    i2 = i5;
                }
                z = false;
            }
            z2 = z;
            i3++;
        }
        if (i3 != updateOp2.itemCount) {
            Object obj = updateOp2.payload;
            recycleUpdateOp(updateOp2);
            updateOp2 = obtainUpdateOp(4, i2, i3, obj);
        }
        if (!z2) {
            dispatchAndUpdateViewHolders(updateOp2);
        } else {
            postponeAndUpdateViewHolders(updateOp2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00a1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void dispatchAndUpdateViewHolders(android.support.v7.widget.AdapterHelper.UpdateOp r18) {
        /*
            Method dump skipped, instructions count: 258
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.AdapterHelper.dispatchAndUpdateViewHolders(android.support.v7.widget.AdapterHelper$UpdateOp):void");
    }

    void dispatchFirstPassAndUpdateViewHolders(UpdateOp updateOp, int i2) {
        this.mCallback.onDispatchFirstPass(updateOp);
        switch (updateOp.cmd) {
            case 2:
                this.mCallback.offsetPositionsForRemovingInvisible(i2, updateOp.itemCount);
                return;
            case 3:
            default:
                throw new IllegalArgumentException("only remove and update ops can be dispatched in first pass");
            case 4:
                this.mCallback.markViewHoldersUpdated(i2, updateOp.itemCount, updateOp.payload);
                return;
        }
    }

    private int updatePositionWithPostponed(int i2, int i3) {
        int i4;
        int i5;
        int i6 = i2;
        for (int size = this.mPostponedList.size() - 1; size >= 0; size--) {
            UpdateOp updateOp = this.mPostponedList.get(size);
            if (updateOp.cmd == 8) {
                if (updateOp.positionStart < updateOp.itemCount) {
                    i4 = updateOp.positionStart;
                    i5 = updateOp.itemCount;
                } else {
                    i4 = updateOp.itemCount;
                    i5 = updateOp.positionStart;
                }
                if (i6 >= i4 && i6 <= i5) {
                    if (i4 == updateOp.positionStart) {
                        if (i3 == 1) {
                            updateOp.itemCount++;
                        } else if (i3 == 2) {
                            updateOp.itemCount--;
                        }
                        i6++;
                    } else {
                        if (i3 == 1) {
                            updateOp.positionStart++;
                        } else if (i3 == 2) {
                            updateOp.positionStart--;
                        }
                        i6--;
                    }
                } else if (i6 < updateOp.positionStart) {
                    if (i3 == 1) {
                        updateOp.positionStart++;
                        updateOp.itemCount++;
                    } else if (i3 == 2) {
                        updateOp.positionStart--;
                        updateOp.itemCount--;
                    }
                }
            } else if (updateOp.positionStart <= i6) {
                if (updateOp.cmd == 1) {
                    i6 -= updateOp.itemCount;
                } else if (updateOp.cmd == 2) {
                    i6 += updateOp.itemCount;
                }
            } else if (i3 == 1) {
                updateOp.positionStart++;
            } else if (i3 == 2) {
                updateOp.positionStart--;
            }
        }
        for (int size2 = this.mPostponedList.size() - 1; size2 >= 0; size2--) {
            UpdateOp updateOp2 = this.mPostponedList.get(size2);
            if (updateOp2.cmd == 8) {
                if (updateOp2.itemCount == updateOp2.positionStart || updateOp2.itemCount < 0) {
                    this.mPostponedList.remove(size2);
                    recycleUpdateOp(updateOp2);
                }
            } else if (updateOp2.itemCount <= 0) {
                this.mPostponedList.remove(size2);
                recycleUpdateOp(updateOp2);
            }
        }
        return i6;
    }

    private boolean canFindInPreLayout(int i2) {
        int size = this.mPostponedList.size();
        for (int i3 = 0; i3 < size; i3++) {
            UpdateOp updateOp = this.mPostponedList.get(i3);
            if (updateOp.cmd == 8) {
                if (findPositionOffset(updateOp.itemCount, i3 + 1) == i2) {
                    return true;
                }
            } else if (updateOp.cmd == 1) {
                int i4 = updateOp.positionStart + updateOp.itemCount;
                for (int i5 = updateOp.positionStart; i5 < i4; i5++) {
                    if (findPositionOffset(i5, i3 + 1) == i2) {
                        return true;
                    }
                }
            } else {
                continue;
            }
        }
        return false;
    }

    private void applyAdd(UpdateOp updateOp) {
        postponeAndUpdateViewHolders(updateOp);
    }

    private void postponeAndUpdateViewHolders(UpdateOp updateOp) {
        this.mPostponedList.add(updateOp);
        switch (updateOp.cmd) {
            case 1:
                this.mCallback.offsetPositionsForAdd(updateOp.positionStart, updateOp.itemCount);
                return;
            case 2:
                this.mCallback.offsetPositionsForRemovingLaidOutOrNewView(updateOp.positionStart, updateOp.itemCount);
                return;
            case 3:
            case 5:
            case 6:
            case 7:
            default:
                throw new IllegalArgumentException("Unknown update op type for " + updateOp);
            case 4:
                this.mCallback.markViewHoldersUpdated(updateOp.positionStart, updateOp.itemCount, updateOp.payload);
                return;
            case 8:
                this.mCallback.offsetPositionsForMove(updateOp.positionStart, updateOp.itemCount);
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean hasPendingUpdates() {
        return this.mPendingUpdates.size() > 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean hasAnyUpdateTypes(int i2) {
        return (this.mExistingUpdateTypes & i2) != 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int findPositionOffset(int i2) {
        return findPositionOffset(i2, 0);
    }

    int findPositionOffset(int i2, int i3) {
        int i4 = i2;
        int size = this.mPostponedList.size();
        for (int i5 = i3; i5 < size; i5++) {
            UpdateOp updateOp = this.mPostponedList.get(i5);
            if (updateOp.cmd == 8) {
                if (updateOp.positionStart == i4) {
                    i4 = updateOp.itemCount;
                } else {
                    if (updateOp.positionStart < i4) {
                        i4--;
                    }
                    if (updateOp.itemCount <= i4) {
                        i4++;
                    }
                }
            } else if (updateOp.positionStart > i4) {
                continue;
            } else if (updateOp.cmd == 2) {
                if (i4 < updateOp.positionStart + updateOp.itemCount) {
                    return -1;
                }
                i4 -= updateOp.itemCount;
            } else if (updateOp.cmd == 1) {
                i4 += updateOp.itemCount;
            }
        }
        return i4;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean onItemRangeChanged(int i2, int i3, Object obj) {
        if (i3 >= 1) {
            this.mPendingUpdates.add(obtainUpdateOp(4, i2, i3, obj));
            this.mExistingUpdateTypes |= 4;
            return this.mPendingUpdates.size() == 1;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean onItemRangeInserted(int i2, int i3) {
        if (i3 >= 1) {
            this.mPendingUpdates.add(obtainUpdateOp(1, i2, i3, null));
            this.mExistingUpdateTypes |= 1;
            return this.mPendingUpdates.size() == 1;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean onItemRangeRemoved(int i2, int i3) {
        if (i3 >= 1) {
            this.mPendingUpdates.add(obtainUpdateOp(2, i2, i3, null));
            this.mExistingUpdateTypes |= 2;
            return this.mPendingUpdates.size() == 1;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean onItemRangeMoved(int i2, int i3, int i4) {
        if (i2 == i3) {
            return false;
        }
        if (i4 == 1) {
            this.mPendingUpdates.add(obtainUpdateOp(8, i2, i3, null));
            this.mExistingUpdateTypes |= 8;
            return this.mPendingUpdates.size() == 1;
        }
        throw new IllegalArgumentException("Moving more than 1 item is not supported yet");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Failed to find 'out' block for switch in B:5:0x0021. Please report as an issue. */
    public void consumeUpdatesInOnePass() {
        consumePostponedUpdates();
        int size = this.mPendingUpdates.size();
        for (int i2 = 0; i2 < size; i2++) {
            UpdateOp updateOp = this.mPendingUpdates.get(i2);
            switch (updateOp.cmd) {
                case 1:
                    this.mCallback.onDispatchSecondPass(updateOp);
                    this.mCallback.offsetPositionsForAdd(updateOp.positionStart, updateOp.itemCount);
                    break;
                case 2:
                    this.mCallback.onDispatchSecondPass(updateOp);
                    this.mCallback.offsetPositionsForRemovingInvisible(updateOp.positionStart, updateOp.itemCount);
                    break;
                case 4:
                    this.mCallback.onDispatchSecondPass(updateOp);
                    this.mCallback.markViewHoldersUpdated(updateOp.positionStart, updateOp.itemCount, updateOp.payload);
                    break;
                case 8:
                    this.mCallback.onDispatchSecondPass(updateOp);
                    this.mCallback.offsetPositionsForMove(updateOp.positionStart, updateOp.itemCount);
                    break;
            }
            if (this.mOnItemProcessedCallback != null) {
                this.mOnItemProcessedCallback.run();
            }
        }
        recycleUpdateOpsAndClearList(this.mPendingUpdates);
        this.mExistingUpdateTypes = 0;
    }

    public int applyPendingUpdatesToPosition(int i2) {
        int i3 = i2;
        int size = this.mPendingUpdates.size();
        for (int i4 = 0; i4 < size; i4++) {
            UpdateOp updateOp = this.mPendingUpdates.get(i4);
            switch (updateOp.cmd) {
                case 1:
                    if (updateOp.positionStart <= i3) {
                        i3 += updateOp.itemCount;
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if (updateOp.positionStart <= i3) {
                        if (updateOp.positionStart + updateOp.itemCount > i3) {
                            return -1;
                        }
                        i3 -= updateOp.itemCount;
                        break;
                    } else {
                        continue;
                    }
                case 8:
                    if (updateOp.positionStart == i3) {
                        i3 = updateOp.itemCount;
                        break;
                    } else {
                        if (updateOp.positionStart < i3) {
                            i3--;
                        }
                        if (updateOp.itemCount <= i3) {
                            i3++;
                            break;
                        } else {
                            break;
                        }
                    }
            }
        }
        return i3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean hasUpdates() {
        return (this.mPostponedList.isEmpty() || this.mPendingUpdates.isEmpty()) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: lib/Mus.dex */
    public static class UpdateOp {
        static final int ADD = 1;
        static final int MOVE = 8;
        static final int POOL_SIZE = 30;
        static final int REMOVE = 2;
        static final int UPDATE = 4;
        int cmd;
        int itemCount;
        Object payload;
        int positionStart;

        UpdateOp(int i2, int i3, int i4, Object obj) {
            this.cmd = i2;
            this.positionStart = i3;
            this.itemCount = i4;
            this.payload = obj;
        }

        String cmdToString() {
            switch (this.cmd) {
                case 1:
                    return "add";
                case 2:
                    return "rm";
                case 3:
                case 5:
                case 6:
                case 7:
                default:
                    return "??";
                case 4:
                    return "up";
                case 8:
                    return "mv";
            }
        }

        public String toString() {
            return Integer.toHexString(System.identityHashCode(this)) + "[" + cmdToString() + ",s:" + this.positionStart + "c:" + this.itemCount + ",p:" + this.payload + "]";
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            UpdateOp updateOp = (UpdateOp) obj;
            if (this.cmd != updateOp.cmd) {
                return false;
            }
            if (this.cmd == 8 && Math.abs(this.itemCount - this.positionStart) == 1 && this.itemCount == updateOp.positionStart && this.positionStart == updateOp.itemCount) {
                return true;
            }
            if (this.itemCount == updateOp.itemCount && this.positionStart == updateOp.positionStart) {
                if (this.payload != null) {
                    if (!this.payload.equals(updateOp.payload)) {
                        return false;
                    }
                } else if (updateOp.payload != null) {
                    return false;
                }
                return true;
            }
            return false;
        }

        public int hashCode() {
            return (31 * ((31 * this.cmd) + this.positionStart)) + this.itemCount;
        }
    }

    @Override // android.support.v7.widget.OpReorderer.Callback
    public UpdateOp obtainUpdateOp(int i2, int i3, int i4, Object obj) {
        UpdateOp acquire = this.mUpdateOpPool.acquire();
        if (acquire == null) {
            acquire = new UpdateOp(i2, i3, i4, obj);
        } else {
            acquire.cmd = i2;
            acquire.positionStart = i3;
            acquire.itemCount = i4;
            acquire.payload = obj;
        }
        return acquire;
    }

    @Override // android.support.v7.widget.OpReorderer.Callback
    public void recycleUpdateOp(UpdateOp updateOp) {
        if (!this.mDisableRecycler) {
            updateOp.payload = null;
            this.mUpdateOpPool.release(updateOp);
        }
    }

    void recycleUpdateOpsAndClearList(List<UpdateOp> list) {
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            recycleUpdateOp(list.get(i2));
        }
        list.clear();
    }
}
