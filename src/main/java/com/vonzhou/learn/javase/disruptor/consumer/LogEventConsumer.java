package com.vonzhou.learn.javase.disruptor.consumer;

import com.lmax.disruptor.EventHandler;
import com.vonzhou.learn.javase.disruptor.event.LogEvent;

/**
 * @author vonzhou
 * @version 2018/9/21
 */
public class LogEventConsumer implements EventHandler<LogEvent> {

    @Override
    public void onEvent(LogEvent event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println(Thread.currentThread().getName() + " | Event : " + event);
    }
}
