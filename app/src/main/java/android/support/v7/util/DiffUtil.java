package android.support.v7.util;

import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* loaded from: lib/Mus.dex */
public class DiffUtil {
    private static final Comparator<Snake> SNAKE_COMPARATOR = new Comparator<Snake>() { // from class: android.support.v7.util.DiffUtil.1
        @Override // java.util.Comparator
        public int compare(Snake snake, Snake snake2) {
            int i2 = snake.x - snake2.x;
            return i2 == 0 ? snake.y - snake2.y : i2;
        }
    };

    private DiffUtil() {
    }

    public static DiffResult calculateDiff(Callback callback) {
        return calculateDiff(callback, true);
    }

    public static DiffResult calculateDiff(Callback callback, boolean z) {
        Range range;
        int oldListSize = callback.getOldListSize();
        int newListSize = callback.getNewListSize();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new Range(0, oldListSize, 0, newListSize));
        int abs = oldListSize + newListSize + Math.abs(oldListSize - newListSize);
        int[] iArr = new int[abs * 2];
        int[] iArr2 = new int[abs * 2];
        ArrayList arrayList3 = new ArrayList();
        while (!arrayList2.isEmpty()) {
            Range range2 = (Range) arrayList2.remove(arrayList2.size() - 1);
            Snake diffPartial = diffPartial(callback, range2.oldListStart, range2.oldListEnd, range2.newListStart, range2.newListEnd, iArr, iArr2, abs);
            if (diffPartial != null) {
                if (diffPartial.size > 0) {
                    arrayList.add(diffPartial);
                }
                diffPartial.x += range2.oldListStart;
                diffPartial.y += range2.newListStart;
                if (arrayList3.isEmpty()) {
                    range = r15;
                    Range range3 = new Range();
                } else {
                    range = (Range) arrayList3.remove(arrayList3.size() - 1);
                }
                Range range4 = range;
                range4.oldListStart = range2.oldListStart;
                range4.newListStart = range2.newListStart;
                if (diffPartial.reverse) {
                    range4.oldListEnd = diffPartial.x;
                    range4.newListEnd = diffPartial.y;
                } else if (diffPartial.removal) {
                    range4.oldListEnd = diffPartial.x - 1;
                    range4.newListEnd = diffPartial.y;
                } else {
                    range4.oldListEnd = diffPartial.x;
                    range4.newListEnd = diffPartial.y - 1;
                }
                arrayList2.add(range4);
                if (diffPartial.reverse) {
                    if (diffPartial.removal) {
                        range2.oldListStart = diffPartial.x + diffPartial.size + 1;
                        range2.newListStart = diffPartial.y + diffPartial.size;
                    } else {
                        range2.oldListStart = diffPartial.x + diffPartial.size;
                        range2.newListStart = diffPartial.y + diffPartial.size + 1;
                    }
                } else {
                    range2.oldListStart = diffPartial.x + diffPartial.size;
                    range2.newListStart = diffPartial.y + diffPartial.size;
                }
                arrayList2.add(range2);
            } else {
                arrayList3.add(range2);
            }
        }
        Collections.sort(arrayList, SNAKE_COMPARATOR);
        return new DiffResult(callback, arrayList, iArr, iArr2, z);
    }

    private static Snake diffPartial(Callback callback, int i2, int i3, int i4, int i5, int[] iArr, int[] iArr2, int i6) {
        int i7;
        boolean z;
        int i8;
        boolean z2;
        int i9 = i3 - i2;
        int i10 = i5 - i4;
        if (i3 - i2 < 1 || i5 - i4 < 1) {
            return null;
        }
        int i11 = i9 - i10;
        int i12 = ((i9 + i10) + 1) / 2;
        Arrays.fill(iArr, (i6 - i12) - 1, i6 + i12 + 1, 0);
        Arrays.fill(iArr2, ((i6 - i12) - 1) + i11, i6 + i12 + 1 + i11, i9);
        boolean z3 = i11 % 2 != 0;
        for (int i13 = 0; i13 <= i12; i13++) {
            for (int i14 = -i13; i14 <= i13; i14 += 2) {
                if (i14 == (-i13) || (i14 != i13 && iArr[(i6 + i14) - 1] < iArr[i6 + i14 + 1])) {
                    i8 = iArr[i6 + i14 + 1];
                    z2 = false;
                } else {
                    i8 = iArr[(i6 + i14) - 1] + 1;
                    z2 = true;
                }
                for (int i15 = i8 - i14; i8 < i9 && i15 < i10 && callback.areItemsTheSame(i2 + i8, i4 + i15); i15++) {
                    i8++;
                }
                iArr[i6 + i14] = i8;
                if (z3 && i14 >= (i11 - i13) + 1 && i14 <= (i11 + i13) - 1 && iArr[i6 + i14] >= iArr2[i6 + i14]) {
                    Snake snake = new Snake();
                    snake.x = iArr2[i6 + i14];
                    snake.y = snake.x - i14;
                    snake.size = iArr[i6 + i14] - iArr2[i6 + i14];
                    snake.removal = z2;
                    snake.reverse = false;
                    return snake;
                }
            }
            for (int i16 = -i13; i16 <= i13; i16 += 2) {
                int i17 = i16 + i11;
                if (i17 == i13 + i11 || (i17 != (-i13) + i11 && iArr2[(i6 + i17) - 1] < iArr2[i6 + i17 + 1])) {
                    i7 = iArr2[(i6 + i17) - 1];
                    z = false;
                } else {
                    i7 = iArr2[(i6 + i17) + 1] - 1;
                    z = true;
                }
                for (int i18 = i7 - i17; i7 > 0 && i18 > 0 && callback.areItemsTheSame((i2 + i7) - 1, (i4 + i18) - 1); i18--) {
                    i7--;
                }
                iArr2[i6 + i17] = i7;
                if (!z3 && i16 + i11 >= (-i13) && i16 + i11 <= i13 && iArr[i6 + i17] >= iArr2[i6 + i17]) {
                    Snake snake2 = new Snake();
                    snake2.x = iArr2[i6 + i17];
                    snake2.y = snake2.x - i17;
                    snake2.size = iArr[i6 + i17] - iArr2[i6 + i17];
                    snake2.removal = z;
                    snake2.reverse = true;
                    return snake2;
                }
            }
        }
        throw new IllegalStateException("DiffUtil hit an unexpected case while trying to calculate the optimal path. Please make sure your data is not changing during the diff calculation.");
    }

    /* loaded from: lib/Mus.dex */
    public static abstract class Callback {
        public abstract boolean areContentsTheSame(int i2, int i3);

        public abstract boolean areItemsTheSame(int i2, int i3);

        public abstract int getNewListSize();

        public abstract int getOldListSize();

        @Nullable
        public Object getChangePayload(int i2, int i3) {
            return null;
        }
    }

    /* loaded from: lib/Mus.dex */
    public static abstract class ItemCallback<T> {
        public abstract boolean areContentsTheSame(T t, T t2);

        public abstract boolean areItemsTheSame(T t, T t2);

        public Object getChangePayload(T t, T t2) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: lib/Mus.dex */
    public static class Snake {
        boolean removal;
        boolean reverse;
        int size;
        int x;
        int y;

        Snake() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: lib/Mus.dex */
    public static class Range {
        int newListEnd;
        int newListStart;
        int oldListEnd;
        int oldListStart;

        public Range() {
        }

        public Range(int i2, int i3, int i4, int i5) {
            this.oldListStart = i2;
            this.oldListEnd = i3;
            this.newListStart = i4;
            this.newListEnd = i5;
        }
    }

    /* loaded from: lib/Mus.dex */
    public static class DiffResult {
        private static final int FLAG_CHANGED = 2;
        private static final int FLAG_IGNORE = 16;
        private static final int FLAG_MASK = 31;
        private static final int FLAG_MOVED_CHANGED = 4;
        private static final int FLAG_MOVED_NOT_CHANGED = 8;
        private static final int FLAG_NOT_CHANGED = 1;
        private static final int FLAG_OFFSET = 5;
        private final Callback mCallback;
        private final boolean mDetectMoves;
        private final int[] mNewItemStatuses;
        private final int mNewListSize;
        private final int[] mOldItemStatuses;
        private final int mOldListSize;
        private final List<Snake> mSnakes;

        DiffResult(Callback callback, List<Snake> list, int[] iArr, int[] iArr2, boolean z) {
            this.mSnakes = list;
            this.mOldItemStatuses = iArr;
            this.mNewItemStatuses = iArr2;
            Arrays.fill(this.mOldItemStatuses, 0);
            Arrays.fill(this.mNewItemStatuses, 0);
            this.mCallback = callback;
            this.mOldListSize = callback.getOldListSize();
            this.mNewListSize = callback.getNewListSize();
            this.mDetectMoves = z;
            addRootSnake();
            findMatchingItems();
        }

        private void addRootSnake() {
            Snake snake = this.mSnakes.isEmpty() ? null : this.mSnakes.get(0);
            if (snake == null || snake.x != 0 || snake.y != 0) {
                Snake snake2 = new Snake();
                snake2.x = 0;
                snake2.y = 0;
                snake2.removal = false;
                snake2.size = 0;
                snake2.reverse = false;
                this.mSnakes.add(0, snake2);
            }
        }

        private void findMatchingItems() {
            int i2 = this.mOldListSize;
            int i3 = this.mNewListSize;
            for (int size = this.mSnakes.size() - 1; size >= 0; size--) {
                Snake snake = this.mSnakes.get(size);
                int i4 = snake.x + snake.size;
                int i5 = snake.y + snake.size;
                if (this.mDetectMoves) {
                    while (i2 > i4) {
                        findAddition(i2, i3, size);
                        i2--;
                    }
                    while (i3 > i5) {
                        findRemoval(i2, i3, size);
                        i3--;
                    }
                }
                for (int i6 = 0; i6 < snake.size; i6++) {
                    int i7 = snake.x + i6;
                    int i8 = snake.y + i6;
                    int i9 = this.mCallback.areContentsTheSame(i7, i8) ? 1 : 2;
                    this.mOldItemStatuses[i7] = (i8 << 5) | i9;
                    this.mNewItemStatuses[i8] = (i7 << 5) | i9;
                }
                i2 = snake.x;
                i3 = snake.y;
            }
        }

        private void findAddition(int i2, int i3, int i4) {
            if (this.mOldItemStatuses[i2 - 1] == 0) {
                findMatchingItem(i2, i3, i4, false);
            }
        }

        private void findRemoval(int i2, int i3, int i4) {
            if (this.mNewItemStatuses[i3 - 1] == 0) {
                findMatchingItem(i2, i3, i4, true);
            }
        }

        private boolean findMatchingItem(int i2, int i3, int i4, boolean z) {
            int i5;
            int i6;
            int i7;
            if (z) {
                i5 = i3 - 1;
                i6 = i2;
                i7 = i3 - 1;
            } else {
                i5 = i2 - 1;
                i6 = i2 - 1;
                i7 = i3;
            }
            for (int i8 = i4; i8 >= 0; i8--) {
                Snake snake = this.mSnakes.get(i8);
                int i9 = snake.x + snake.size;
                int i10 = snake.y + snake.size;
                if (z) {
                    for (int i11 = i6 - 1; i11 >= i9; i11--) {
                        if (this.mCallback.areItemsTheSame(i11, i5)) {
                            int i12 = this.mCallback.areContentsTheSame(i11, i5) ? 8 : 4;
                            this.mNewItemStatuses[i5] = (i11 << 5) | 16;
                            this.mOldItemStatuses[i11] = (i5 << 5) | i12;
                            return true;
                        }
                    }
                } else {
                    for (int i13 = i7 - 1; i13 >= i10; i13--) {
                        if (this.mCallback.areItemsTheSame(i5, i13)) {
                            int i14 = this.mCallback.areContentsTheSame(i5, i13) ? 8 : 4;
                            this.mOldItemStatuses[i2 - 1] = (i13 << 5) | 16;
                            this.mNewItemStatuses[i13] = ((i2 - 1) << 5) | i14;
                            return true;
                        }
                    }
                }
                i6 = snake.x;
                i7 = snake.y;
            }
            return false;
        }

        public void dispatchUpdatesTo(RecyclerView.Adapter adapter) {
            dispatchUpdatesTo(new AdapterListUpdateCallback(adapter));
        }

        public void dispatchUpdatesTo(ListUpdateCallback listUpdateCallback) {
            BatchingListUpdateCallback batchingListUpdateCallback;
            if (listUpdateCallback instanceof BatchingListUpdateCallback) {
                batchingListUpdateCallback = (BatchingListUpdateCallback) listUpdateCallback;
            } else {
                batchingListUpdateCallback = new BatchingListUpdateCallback(listUpdateCallback);
            }
            ArrayList arrayList = new ArrayList();
            int i2 = this.mOldListSize;
            int i3 = this.mNewListSize;
            for (int size = this.mSnakes.size() - 1; size >= 0; size--) {
                Snake snake = this.mSnakes.get(size);
                int i4 = snake.size;
                int i5 = snake.x + i4;
                int i6 = snake.y + i4;
                if (i5 < i2) {
                    dispatchRemovals(arrayList, batchingListUpdateCallback, i5, i2 - i5, i5);
                }
                if (i6 < i3) {
                    dispatchAdditions(arrayList, batchingListUpdateCallback, i5, i3 - i6, i6);
                }
                for (int i7 = i4 - 1; i7 >= 0; i7--) {
                    if ((this.mOldItemStatuses[snake.x + i7] & 31) == 2) {
                        batchingListUpdateCallback.onChanged(snake.x + i7, 1, this.mCallback.getChangePayload(snake.x + i7, snake.y + i7));
                    }
                }
                i2 = snake.x;
                i3 = snake.y;
            }
            batchingListUpdateCallback.dispatchLastEvent();
        }

        private static PostponedUpdate removePostponedUpdate(List<PostponedUpdate> list, int i2, boolean z) {
            for (int size = list.size() - 1; size >= 0; size--) {
                PostponedUpdate postponedUpdate = list.get(size);
                if (postponedUpdate.posInOwnerList == i2 && postponedUpdate.removal == z) {
                    list.remove(size);
                    for (int i3 = size; i3 < list.size(); i3++) {
                        list.get(i3).currentPos += z ? 1 : -1;
                    }
                    return postponedUpdate;
                }
            }
            return null;
        }

        private void dispatchAdditions(List<PostponedUpdate> list, ListUpdateCallback listUpdateCallback, int i2, int i3, int i4) {
            if (!this.mDetectMoves) {
                listUpdateCallback.onInserted(i2, i3);
                return;
            }
            for (int i5 = i3 - 1; i5 >= 0; i5--) {
                int i6 = this.mNewItemStatuses[i4 + i5] & 31;
                switch (i6) {
                    case 0:
                        listUpdateCallback.onInserted(i2, 1);
                        Iterator<PostponedUpdate> it = list.iterator();
                        while (it.hasNext()) {
                            it.next().currentPos++;
                        }
                        break;
                    case 4:
                    case 8:
                        int i7 = this.mNewItemStatuses[i4 + i5] >> 5;
                        listUpdateCallback.onMoved(removePostponedUpdate(list, i7, true).currentPos, i2);
                        if (i6 == 4) {
                            listUpdateCallback.onChanged(i2, 1, this.mCallback.getChangePayload(i7, i4 + i5));
                            break;
                        } else {
                            break;
                        }
                    case 16:
                        list.add(new PostponedUpdate(i4 + i5, i2, false));
                        break;
                    default:
                        throw new IllegalStateException("unknown flag for pos " + (i4 + i5) + " " + Long.toBinaryString(i6));
                }
            }
        }

        private void dispatchRemovals(List<PostponedUpdate> list, ListUpdateCallback listUpdateCallback, int i2, int i3, int i4) {
            if (!this.mDetectMoves) {
                listUpdateCallback.onRemoved(i2, i3);
                return;
            }
            for (int i5 = i3 - 1; i5 >= 0; i5--) {
                int i6 = this.mOldItemStatuses[i4 + i5] & 31;
                switch (i6) {
                    case 0:
                        listUpdateCallback.onRemoved(i2 + i5, 1);
                        for (PostponedUpdate postponedUpdate : list) {
                            postponedUpdate.currentPos--;
                        }
                        break;
                    case 4:
                    case 8:
                        int i7 = this.mOldItemStatuses[i4 + i5] >> 5;
                        PostponedUpdate removePostponedUpdate = removePostponedUpdate(list, i7, false);
                        listUpdateCallback.onMoved(i2 + i5, removePostponedUpdate.currentPos - 1);
                        if (i6 == 4) {
                            listUpdateCallback.onChanged(removePostponedUpdate.currentPos - 1, 1, this.mCallback.getChangePayload(i4 + i5, i7));
                            break;
                        } else {
                            break;
                        }
                    case 16:
                        list.add(new PostponedUpdate(i4 + i5, i2 + i5, true));
                        break;
                    default:
                        throw new IllegalStateException("unknown flag for pos " + (i4 + i5) + " " + Long.toBinaryString(i6));
                }
            }
        }

        @VisibleForTesting
        List<Snake> getSnakes() {
            return this.mSnakes;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: lib/Mus.dex */
    public static class PostponedUpdate {
        int currentPos;
        int posInOwnerList;
        boolean removal;

        public PostponedUpdate(int i2, int i3, boolean z) {
            this.posInOwnerList = i2;
            this.currentPos = i3;
            this.removal = z;
        }
    }
}
