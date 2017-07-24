package com.vonzhou.learn.javase.guava.event;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

/**
 * @version 2017/7/24.
 */
public class FooListener {

    @Subscribe
    public void doAction(FooEvent event) {
        System.out.println("处理事件：" + event.getMsg());
    }

    @Subscribe
    public void doAction2(FooEvent event) {
        System.out.println("处理2事件：" + event.getMsg());
    }

    public static void main(String[] args) {
        EventBus eventBus = new EventBus("test");
        FooListener fooListener = new FooListener();

        eventBus.register(fooListener);

        eventBus.post(new FooEvent("event1"));

    }
}
