package android.support.v7.util;

/* loaded from: lib/Mus.dex */
public class BatchingListUpdateCallback implements ListUpdateCallback {
    private static final int TYPE_ADD = 1;
    private static final int TYPE_CHANGE = 3;
    private static final int TYPE_NONE = 0;
    private static final int TYPE_REMOVE = 2;
    final ListUpdateCallback mWrapped;
    int mLastEventType = 0;
    int mLastEventPosition = -1;
    int mLastEventCount = -1;
    Object mLastEventPayload = null;

    public BatchingListUpdateCallback(ListUpdateCallback listUpdateCallback) {
        this.mWrapped = listUpdateCallback;
    }

    public void dispatchLastEvent() {
        if (this.mLastEventType != 0) {
            switch (this.mLastEventType) {
                case 1:
                    this.mWrapped.onInserted(this.mLastEventPosition, this.mLastEventCount);
                    break;
                case 2:
                    this.mWrapped.onRemoved(this.mLastEventPosition, this.mLastEventCount);
                    break;
                case 3:
                    this.mWrapped.onChanged(this.mLastEventPosition, this.mLastEventCount, this.mLastEventPayload);
                    break;
            }
            this.mLastEventPayload = null;
            this.mLastEventType = 0;
        }
    }

    @Override // android.support.v7.util.ListUpdateCallback
    public void onInserted(int i2, int i3) {
        if (this.mLastEventType == 1 && i2 >= this.mLastEventPosition && i2 <= this.mLastEventPosition + this.mLastEventCount) {
            this.mLastEventCount += i3;
            this.mLastEventPosition = Math.min(i2, this.mLastEventPosition);
        } else {
            dispatchLastEvent();
            this.mLastEventPosition = i2;
            this.mLastEventCount = i3;
            this.mLastEventType = 1;
        }
    }

    @Override // android.support.v7.util.ListUpdateCallback
    public void onRemoved(int i2, int i3) {
        if (this.mLastEventType == 2 && this.mLastEventPosition >= i2 && this.mLastEventPosition <= i2 + i3) {
            this.mLastEventCount += i3;
            this.mLastEventPosition = i2;
        } else {
            dispatchLastEvent();
            this.mLastEventPosition = i2;
            this.mLastEventCount = i3;
            this.mLastEventType = 2;
        }
    }

    @Override // android.support.v7.util.ListUpdateCallback
    public void onMoved(int i2, int i3) {
        dispatchLastEvent();
        this.mWrapped.onMoved(i2, i3);
    }

    @Override // android.support.v7.util.ListUpdateCallback
    public void onChanged(int i2, int i3, Object obj) {
        if (this.mLastEventType == 3 && i2 <= this.mLastEventPosition + this.mLastEventCount && i2 + i3 >= this.mLastEventPosition && this.mLastEventPayload == obj) {
            int i4 = this.mLastEventPosition + this.mLastEventCount;
            this.mLastEventPosition = Math.min(i2, this.mLastEventPosition);
            this.mLastEventCount = Math.max(i4, i2 + i3) - this.mLastEventPosition;
        } else {
            dispatchLastEvent();
            this.mLastEventPosition = i2;
            this.mLastEventCount = i3;
            this.mLastEventPayload = obj;
            this.mLastEventType = 3;
        }
    }
}
