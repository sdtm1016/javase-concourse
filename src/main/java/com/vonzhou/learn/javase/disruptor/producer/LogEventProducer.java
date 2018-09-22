package com.vonzhou.learn.javase.disruptor.producer;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;
import com.vonzhou.learn.javase.disruptor.event.LogEvent;


/**
 * @author vonzhou
 * @version 2018/9/21
 */
public class LogEventProducer {
    private final RingBuffer<LogEvent> ringBuffer;

    public LogEventProducer(RingBuffer<LogEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    private static final EventTranslatorOneArg<LogEvent, String> TRANSLATOR = new EventTranslatorOneArg<LogEvent, String>() {
        public void translateTo(LogEvent event, long sequence, String bb) {
            event.setMsg(bb);
        }
    };


    public void onData(String msg){
        ringBuffer.publishEvent(TRANSLATOR, msg);
    }
}
