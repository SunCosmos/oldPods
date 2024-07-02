package android.support.v7.util;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.util.ThreadUtil;
import android.support.v7.util.TileList;
import android.util.Log;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: lib/Mus.dex */
class MessageThreadUtil<T> implements ThreadUtil<T> {

    /* renamed from: android.support.v7.util.MessageThreadUtil$1, reason: invalid class name */
    /* loaded from: lib/Mus.dex */
    class AnonymousClass1 implements ThreadUtil.MainThreadCallback<T> {
        static final int ADD_TILE = 2;
        static final int REMOVE_TILE = 3;
        static final int UPDATE_ITEM_COUNT = 1;
        final /* synthetic */ ThreadUtil.MainThreadCallback val$callback;
        final MessageQueue mQueue = new MessageQueue();
        private final Handler mMainThreadHandler = new Handler(Looper.getMainLooper());
        private Runnable mMainThreadRunnable = new Runnable() { // from class: android.support.v7.util.MessageThreadUtil.1.1
            @Override // java.lang.Runnable
            public void run() {
                SyncQueueItem next = AnonymousClass1.this.mQueue.next();
                while (true) {
                    SyncQueueItem syncQueueItem = next;
                    if (syncQueueItem != null) {
                        switch (syncQueueItem.what) {
                            case 1:
                                AnonymousClass1.this.val$callback.updateItemCount(syncQueueItem.arg1, syncQueueItem.arg2);
                                break;
                            case 2:
                                AnonymousClass1.this.val$callback.addTile(syncQueueItem.arg1, (TileList.Tile) syncQueueItem.data);
                                break;
                            case 3:
                                AnonymousClass1.this.val$callback.removeTile(syncQueueItem.arg1, syncQueueItem.arg2);
                                break;
                            default:
                                Log.e("ThreadUtil", "Unsupported message, what=" + syncQueueItem.what);
                                break;
                        }
                        next = AnonymousClass1.this.mQueue.next();
                    } else {
                        return;
                    }
                }
            }
        };

        AnonymousClass1(ThreadUtil.MainThreadCallback mainThreadCallback) {
            this.val$callback = mainThreadCallback;
        }

        @Override // android.support.v7.util.ThreadUtil.MainThreadCallback
        public void updateItemCount(int i2, int i3) {
            sendMessage(SyncQueueItem.obtainMessage(1, i2, i3));
        }

        @Override // android.support.v7.util.ThreadUtil.MainThreadCallback
        public void addTile(int i2, TileList.Tile<T> tile) {
            sendMessage(SyncQueueItem.obtainMessage(2, i2, tile));
        }

        @Override // android.support.v7.util.ThreadUtil.MainThreadCallback
        public void removeTile(int i2, int i3) {
            sendMessage(SyncQueueItem.obtainMessage(3, i2, i3));
        }

        private void sendMessage(SyncQueueItem syncQueueItem) {
            this.mQueue.sendMessage(syncQueueItem);
            this.mMainThreadHandler.post(this.mMainThreadRunnable);
        }
    }

    @Override // android.support.v7.util.ThreadUtil
    public ThreadUtil.MainThreadCallback<T> getMainThreadProxy(ThreadUtil.MainThreadCallback<T> mainThreadCallback) {
        return new AnonymousClass1(mainThreadCallback);
    }

    /* renamed from: android.support.v7.util.MessageThreadUtil$2, reason: invalid class name */
    /* loaded from: lib/Mus.dex */
    class AnonymousClass2 implements ThreadUtil.BackgroundCallback<T> {
        static final int LOAD_TILE = 3;
        static final int RECYCLE_TILE = 4;
        static final int REFRESH = 1;
        static final int UPDATE_RANGE = 2;
        final /* synthetic */ ThreadUtil.BackgroundCallback val$callback;
        final MessageQueue mQueue = new MessageQueue();
        private final Executor mExecutor = AsyncTask.THREAD_POOL_EXECUTOR;
        AtomicBoolean mBackgroundRunning = new AtomicBoolean(false);
        private Runnable mBackgroundRunnable = new Runnable() { // from class: android.support.v7.util.MessageThreadUtil.2.1
            @Override // java.lang.Runnable
            public void run() {
                while (true) {
                    SyncQueueItem next = AnonymousClass2.this.mQueue.next();
                    if (next != null) {
                        switch (next.what) {
                            case 1:
                                AnonymousClass2.this.mQueue.removeMessages(1);
                                AnonymousClass2.this.val$callback.refresh(next.arg1);
                                break;
                            case 2:
                                AnonymousClass2.this.mQueue.removeMessages(2);
                                AnonymousClass2.this.mQueue.removeMessages(3);
                                AnonymousClass2.this.val$callback.updateRange(next.arg1, next.arg2, next.arg3, next.arg4, next.arg5);
                                break;
                            case 3:
                                AnonymousClass2.this.val$callback.loadTile(next.arg1, next.arg2);
                                break;
                            case 4:
                                AnonymousClass2.this.val$callback.recycleTile((TileList.Tile) next.data);
                                break;
                            default:
                                Log.e("ThreadUtil", "Unsupported message, what=" + next.what);
                                break;
                        }
                    } else {
                        AnonymousClass2.this.mBackgroundRunning.set(false);
                        return;
                    }
                }
            }
        };

        AnonymousClass2(ThreadUtil.BackgroundCallback backgroundCallback) {
            this.val$callback = backgroundCallback;
        }

        @Override // android.support.v7.util.ThreadUtil.BackgroundCallback
        public void refresh(int i2) {
            sendMessageAtFrontOfQueue(SyncQueueItem.obtainMessage(1, i2, (Object) null));
        }

        @Override // android.support.v7.util.ThreadUtil.BackgroundCallback
        public void updateRange(int i2, int i3, int i4, int i5, int i6) {
            sendMessageAtFrontOfQueue(SyncQueueItem.obtainMessage(2, i2, i3, i4, i5, i6, null));
        }

        @Override // android.support.v7.util.ThreadUtil.BackgroundCallback
        public void loadTile(int i2, int i3) {
            sendMessage(SyncQueueItem.obtainMessage(3, i2, i3));
        }

        @Override // android.support.v7.util.ThreadUtil.BackgroundCallback
        public void recycleTile(TileList.Tile<T> tile) {
            sendMessage(SyncQueueItem.obtainMessage(4, 0, tile));
        }

        private void sendMessage(SyncQueueItem syncQueueItem) {
            this.mQueue.sendMessage(syncQueueItem);
            maybeExecuteBackgroundRunnable();
        }

        private void sendMessageAtFrontOfQueue(SyncQueueItem syncQueueItem) {
            this.mQueue.sendMessageAtFrontOfQueue(syncQueueItem);
            maybeExecuteBackgroundRunnable();
        }

        private void maybeExecuteBackgroundRunnable() {
            if (this.mBackgroundRunning.compareAndSet(false, true)) {
                this.mExecutor.execute(this.mBackgroundRunnable);
            }
        }
    }

    @Override // android.support.v7.util.ThreadUtil
    public ThreadUtil.BackgroundCallback<T> getBackgroundProxy(ThreadUtil.BackgroundCallback<T> backgroundCallback) {
        return new AnonymousClass2(backgroundCallback);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: lib/Mus.dex */
    public static class SyncQueueItem {
        private static SyncQueueItem sPool;
        private static final Object sPoolLock = new Object();
        public int arg1;
        public int arg2;
        public int arg3;
        public int arg4;
        public int arg5;
        public Object data;
        private SyncQueueItem next;
        public int what;

        SyncQueueItem() {
        }

        void recycle() {
            this.next = null;
            this.arg5 = 0;
            this.arg4 = 0;
            this.arg3 = 0;
            this.arg2 = 0;
            this.arg1 = 0;
            this.what = 0;
            this.data = null;
            synchronized (sPoolLock) {
                if (sPool != null) {
                    this.next = sPool;
                }
                sPool = this;
            }
        }

        static SyncQueueItem obtainMessage(int i2, int i3, int i4, int i5, int i6, int i7, Object obj) {
            SyncQueueItem syncQueueItem;
            SyncQueueItem syncQueueItem2;
            synchronized (sPoolLock) {
                if (sPool == null) {
                    syncQueueItem = new SyncQueueItem();
                } else {
                    syncQueueItem = sPool;
                    sPool = sPool.next;
                    syncQueueItem.next = null;
                }
                syncQueueItem.what = i2;
                syncQueueItem.arg1 = i3;
                syncQueueItem.arg2 = i4;
                syncQueueItem.arg3 = i5;
                syncQueueItem.arg4 = i6;
                syncQueueItem.arg5 = i7;
                syncQueueItem.data = obj;
                syncQueueItem2 = syncQueueItem;
            }
            return syncQueueItem2;
        }

        static SyncQueueItem obtainMessage(int i2, int i3, int i4) {
            return obtainMessage(i2, i3, i4, 0, 0, 0, null);
        }

        static SyncQueueItem obtainMessage(int i2, int i3, Object obj) {
            return obtainMessage(i2, i3, 0, 0, 0, 0, obj);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: lib/Mus.dex */
    public static class MessageQueue {
        private SyncQueueItem mRoot;

        MessageQueue() {
        }

        synchronized SyncQueueItem next() {
            SyncQueueItem syncQueueItem;
            if (this.mRoot == null) {
                syncQueueItem = null;
            } else {
                SyncQueueItem syncQueueItem2 = this.mRoot;
                this.mRoot = this.mRoot.next;
                syncQueueItem = syncQueueItem2;
            }
            return syncQueueItem;
        }

        synchronized void sendMessageAtFrontOfQueue(SyncQueueItem syncQueueItem) {
            syncQueueItem.next = this.mRoot;
            this.mRoot = syncQueueItem;
        }

        synchronized void sendMessage(SyncQueueItem syncQueueItem) {
            if (this.mRoot == null) {
                this.mRoot = syncQueueItem;
            } else {
                SyncQueueItem syncQueueItem2 = this.mRoot;
                while (syncQueueItem2.next != null) {
                    syncQueueItem2 = syncQueueItem2.next;
                }
                syncQueueItem2.next = syncQueueItem;
            }
        }

        synchronized void removeMessages(int i2) {
            while (this.mRoot != null && this.mRoot.what == i2) {
                SyncQueueItem syncQueueItem = this.mRoot;
                this.mRoot = this.mRoot.next;
                syncQueueItem.recycle();
            }
            if (this.mRoot != null) {
                SyncQueueItem syncQueueItem2 = this.mRoot;
                SyncQueueItem syncQueueItem3 = syncQueueItem2.next;
                while (true) {
                    SyncQueueItem syncQueueItem4 = syncQueueItem3;
                    if (syncQueueItem4 == null) {
                        break;
                    }
                    SyncQueueItem syncQueueItem5 = syncQueueItem4.next;
                    if (syncQueueItem4.what == i2) {
                        syncQueueItem2.next = syncQueueItem5;
                        syncQueueItem4.recycle();
                    } else {
                        syncQueueItem2 = syncQueueItem4;
                    }
                    syncQueueItem3 = syncQueueItem5;
                }
            }
        }
    }
}
