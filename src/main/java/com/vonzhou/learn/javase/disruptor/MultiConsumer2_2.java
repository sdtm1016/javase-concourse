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
public class MultiConsumer2_2 {
    public static final int WORKER_SIZE = 4;

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        // Fixed Thread Pool
        ExecutorService executor = new ThreadPoolExecutor(WORKER_SIZE, WORKER_SIZE, 0L, TimeUnit.MILLISECONDS,
                        new ArrayBlockingQueue<Runnable>(1024), new ThreadFactory() {
                            private int counter = 0;
                            private String prefix = "DisruptorWorker";

                            @Override
                            public Thread newThread(Runnable r) {
                                return new Thread(r, prefix + "-" + counter++);
                            }
                        }, new RejectedExecutionHandler() {

                            @Override
                            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                                try {
                                    executor.getQueue().put(r);
                                } catch (InterruptedException e) {
                                    System.out.println("Disrutptor worker 拒绝策略执行异常！" + e);
                                }
                            }
                        });

        // 模拟消息发送
        for (int i = 0; i < 5000; i++) {
            executor.submit(new Task(String.format("M%s", i)));
        }
        System.out.println(String.format("== Total cost %s seconds ==", (System.currentTimeMillis() - start) / 1000));

    }

    public static class Task implements Runnable {
        private String msg;

        public Task(String msg) {
            this.msg = msg;
        }

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + " | Event : " + msg);
                Thread.sleep(20);
            } catch (Exception e) {

            }
        }
    }
}