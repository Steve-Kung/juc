import org.junit.Test;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.Queue;

public class S2 {
    int MAX_SIZE = 10;
    Queue<Integer> queue = new LinkedList<Integer>();

    class Producter extends Thread{
        @Override
        public void run() {
            while (true){
                // 生产者线程
                synchronized(queue){
                    // 消费队列满，则等待队列空闲
                    while (queue.size() == MAX_SIZE){
                        try {
                            // 挂起当前线程，并释放通过同步块获取的queue上的锁
                            // 让消费者线程可以获得该锁，然后获取队列里面的元素
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
//            队列空闲则生成元素， 并通知消费者线程
                    queue.add(1);
                    System.out.println(this.getName() + "生产");
                    queue.notifyAll();
                }
            }

        }
    }

    class Consumer extends Thread{

        @Override
        public void run() {
            while (true){
                // 消费者线程
                synchronized (queue){
                    while (queue.size() == 0){
                        try {
                            // 挂起当前线程，并释放通过同步块获取的queue上的锁
                            // 让生产者线程可以获得该锁，然后往队列里面添加元素
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    // 消费元素，并通知唤醒生产者线程
                    queue.poll();
                    System.out.println(this.getName() + "消费");
                    queue.notifyAll();
                }
            }

        }
    }

    @Test
    public void test1(){
        Producter producter = new Producter();
        Consumer consumer = new Consumer();
        producter.start();
        consumer.start();

    }
}
