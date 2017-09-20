package com.xwl.net.mvplib.util.jitter;


import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;

/**
 * <br> ClassName:   Jitter
 * <br> Description: 抖动事件处理
 * <br>
 * <br> Author:      谢文良
 * <br> Date:        2017/9/19 11:40
 */

public class Jitter extends Observable<Object> {
    private static Map<Object, Map<Object, Jitter>> tags = new HashMap<>();
    private Observer<? super Object> mObserver;

    /**
     * <br> Description: 绑定事件源(在事件源发生期间，绑定key必须相同)
     * <br> Author:      谢文良
     * <br> Date:        2017/9/19 12:31
     *
     * @param tag tag
     * @param key key
     * @return Jitter
     */
    public static Jitter bind(Object tag, Object key) {
        if (!tags.containsKey(tag)) {
            tags.put(tag, new HashMap<Object, Jitter>());
        }
        Map<Object, Jitter> mEvents = tags.get(tag);
        if (!mEvents.containsKey(key)) {
            mEvents.put(key, new Jitter());
        }
        return mEvents.get(key);
    }

    /**
     * <br> Description: 构造函数
     * <br> Author:      谢文良
     * <br> Date:        2017/9/19 12:33
     */
    private Jitter() {

    }

    /**
     * <br> Description: 产生的事件
     * <br> Author:      谢文良
     * <br> Date:        2017/9/19 12:33
     *
     * @param o 事件
     */
    public void bufferEvent(Object o) {
        mObserver.onNext(o);
    }


    @Override
    protected void subscribeActual(final Observer<? super Object> observer) {
        mObserver = observer;
    }

    /**
     * <br> Description: 解绑
     * <br> Author:      谢文良
     * <br> Date:        2017/9/19 17:10
     *
     * @param tag tag
     */
    public static void unBind(Object tag) {
        if (tags.containsKey(tag)) {
            Map<Object, Jitter> mEvents = tags.get(tag);
            for (Map.Entry<Object, Jitter> item : mEvents.entrySet()) {
                item.getValue().mObserver.onComplete();
            }
            mEvents.clear();
            tags.remove(tag);
        }
    }
}
