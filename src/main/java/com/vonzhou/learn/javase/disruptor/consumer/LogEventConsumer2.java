package com.vonzhou.learn.javase.disruptor.consumer;

import com.lmax.disruptor.WorkHandler;
import com.vonzhou.learn.javase.disruptor.MultiConsumer2_1;
import com.vonzhou.learn.javase.disruptor.event.LogEvent;

/**
 * @author vonzhou
 * @version 2018/9/21
 */
public class LogEventConsumer2 implements  WorkHandler<LogEvent> {

    private void process(LogEvent event) throws Exception {
        System.out.println(Thread.currentThread().getName() + " | Event : " + event);
//        Thread.sleep(20);
        MultiConsumer2_1.countdown.countDown();
    }

    @Override
    public void onEvent(LogEvent event) throws Exception {
        process(event);
    }
}
