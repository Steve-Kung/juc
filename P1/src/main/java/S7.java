import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Thread.sleep方法 线程在睡眠时不会释放对应锁
 */
public class S7 {
    // 创建一个独占锁
    private static final Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                // 获取独占锁
                lock.lock();
                try {
                    System.out.println("-1-");
                    Thread.sleep(1000);
                    System.out.println("-2-");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // 释放锁
                    lock.unlock();
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                // 获取独占锁
                lock.lock();

                try {
                    System.out.println("-3-");
                    Thread.sleep(1000);
                    System.out.println("-4-");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }

            }
        });

        thread1.start();
        thread2.start();
    }
}
