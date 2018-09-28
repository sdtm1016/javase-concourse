package com.vonzhou.learn.javase.disruptor;

import java.util.concurrent.*;

import com.lmax.disruptor.WorkHandler;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.vonzhou.learn.javase.disruptor.consumer.LogEventConsumer;
import com.vonzhou.learn.javase.disruptor.event.LogEvent;
import com.vonzhou.learn.javase.disruptor.factory.LogEventFactory;
import com.vonzhou.learn.javase.disruptor.producer.LogEventProducer;

/**
 * @author vonzhou
 * @version 2018/9/21
 */
public class MultiConsumer2_1 {
    public static final int WORKER_SIZE = 4;

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        // 环形数组的容量，必须要是2的次幂
        int bufferSize = 1024;

        // 构造 Disruptor
        Disruptor<LogEvent> disruptor = new Disruptor<>(new LogEventFactory(), bufferSize, new ThreadFactory() {
            private int counter = 0;
            private String prefix = "DisruptorWorker";

            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r, prefix + "-" + counter++);
                t.setDaemon(true);
                return t;
            }
        }, ProducerType.SINGLE,
                        new YieldingWaitStrategy());

        // 设置消费者
        WorkHandler<LogEvent>[] consumers = new LogEventConsumer[WORKER_SIZE];
        for (int i = 0; i < consumers.length; i++) {
            consumers[i] = new LogEventConsumer();
        }
        disruptor.handleEventsWithWorkerPool(consumers);

        // 启动 Disruptor
        disruptor.start();

        // 生产者要使用 Disruptor 的环形数组
        LogEventProducer producer = new LogEventProducer(disruptor.getRingBuffer());

        // 模拟消息发送
        for (int i = 0; i < 5000; i++) {
            producer.onData(String.format("M%s", i));
        }
        System.out.println(String.format("== Total cost %s seconds ==", (System.currentTimeMillis() - start) / 1000));

    }
}
