package com.vonzhou.learn.javase.guava.event;

/**
 * @version 2017/7/24.
 */
public class FooEvent {
    private String msg;

    public FooEvent(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
