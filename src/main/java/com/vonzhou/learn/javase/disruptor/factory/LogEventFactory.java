package com.vonzhou.learn.javase.disruptor.factory;

import com.lmax.disruptor.EventFactory;
import com.vonzhou.learn.javase.disruptor.event.LogEvent;

/**
 * @author vonzhou
 * @version 2018/9/21
 */
public class LogEventFactory implements EventFactory<LogEvent> {
    @Override
    public LogEvent newInstance() {
        return new LogEvent();
    }
}
