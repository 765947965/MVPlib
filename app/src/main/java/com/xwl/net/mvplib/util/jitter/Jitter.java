package com.xwl.net.mvplib.util.jitter;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;

/**
 * <br> ClassName:   Jitter
 * <br> Description: 抖动事件处理
 * <br>
 * <br> Author:      谢文良
 * <br> Date:        2017/9/19 11:40
 */

public class Jitter {
    private static Map<Object, Jitter> mEvents = new HashMap<>();
    private IEventCallback mJitterCallback;
    private Consumer<Object> mObserver;
    private DisposableObserver<Object> mDisposableObserver;

    /**
     * <br> Description: 绑定事件源(在事件源发生期间，绑定key必须相同)
     * <br> Author:      谢文良
     * <br> Date:        2017/9/19 12:31
     *
     * @param key   key
     * @param delay 时间
     * @param unit  时间单位
     * @return Jitter
     */
    public static Jitter bind(Object key, long delay, TimeUnit unit) {
        if (!mEvents.containsKey(key)) {
            mEvents.put(key, new Jitter(delay, unit));
        }
        return mEvents.get(key);
    }

    /**
     * <br> Description: 绑定事件源(在事件源发生期间，绑定key必须相同)
     * <br> Author:      谢文良
     * <br> Date:        2017/9/19 12:31
     *
     * @param key key
     * @return Jitter
     */
    public static Jitter bind(Object key) {
        return bind(key, 300, TimeUnit.MILLISECONDS);
    }

    /**
     * <br> Description: 构造函数
     * <br> Author:      谢文良
     * <br> Date:        2017/9/19 12:33
     *
     * @param delay delay
     * @param unit  unit
     */
    private Jitter(long delay, TimeUnit unit) {
        mDisposableObserver = new DisposableObserver<Object>() {
            @Override
            public void onNext(@NonNull Object o) {
                if (mObserver != null) {
                    try {
                        mObserver.accept(o);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }

        };
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(@NonNull final ObservableEmitter<Object> e) throws Exception {
                mJitterCallback = new IEventCallback() {
                    @Override
                    public void sendEvent(Object o) {
                        e.onNext(o);
                    }
                };
            }
        }).throttleFirst(delay, unit).observeOn(AndroidSchedulers.mainThread()).subscribe(mDisposableObserver);
    }

    /**
     * <br> Description: 产生的事件
     * <br> Author:      谢文良
     * <br> Date:        2017/9/19 12:33
     *
     * @param o 事件
     */
    public void bufferObservable(Object o) {
        mJitterCallback.sendEvent(o);
    }

    /**
     * <br> Description: 接收(一定时间内)抖动事件的最后一次事件
     * <br> Author:      谢文良
     * <br> Date:        2017/9/19 12:33
     *
     * @param mObserver Consumer
     * @return Jitter
     */
    public Jitter subscribe(Consumer<Object> mObserver) {
        this.mObserver = mObserver;
        return this;
    }

    /**
     * <br> ClassName:   Jitter
     * <br> Description: 事件发送监听器
     * <br>
     * <br> Author:      谢文良
     * <br> Date:        2017/9/19 12:35
     */
    private interface IEventCallback {
        /**
         * <br> Description: 发送事件
         * <br> Author:      谢文良
         * <br> Date:        2017/9/19 12:34
         *
         * @param o T
         */
        void sendEvent(Object o);
    }

    /**
     * <br> Description: 解绑
     * <br> Author:      谢文良
     * <br> Date:        2017/9/19 17:10
     */
    public static void unBind() {
        for (Map.Entry<Object, Jitter> item : mEvents.entrySet()) {
            item.getValue().mDisposableObserver.dispose();
        }
        mEvents.clear();
    }
}
