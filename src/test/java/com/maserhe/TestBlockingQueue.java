package com.maserhe;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 描述:
 * 测试阻塞队列
 *
 * @author Maserhe
 * @create 2021-04-10 21:33
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestBlockingQueue {

    private static final Logger logger = LoggerFactory.getLogger(TestBlockingQueue.class);

    @Test
    public void test() {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);

        new Thread(new Producer(queue), "producer").start();
        new Thread(new Consumer(queue), "consumer1").start();
        new Thread(new Consumer(queue), "consumer2").start();

    }
}
class Producer implements Runnable{

    BlockingQueue<Integer> queue;

    public Producer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i ++ ) {
            try {
                queue.put(i);
                System.out.println(Thread.currentThread().getName() + " 生产了" + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Consumer implements Runnable{

    BlockingQueue<Integer> queue;

    public Consumer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Integer remove = queue.take();
                System.out.println(Thread.currentThread().getName() + " 消费了" + remove);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

