/*
当线程调用共享对象的wait方法时，当前线程只会释放当前共享对象的锁，
当前线程持有的其他共享独享的监视器锁并不会释放

wait方法释放锁 阻塞自己
 */
public class S3 {
    // 创建资源
    private static volatile Object resourceA = new Object();
    private static volatile Object resourceB = new Object();

    public static void main(String[] args) throws InterruptedException {
        // 创建线程
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 获取resourceA共享资源的监视器锁
                    synchronized (resourceA){
                        System.out.println("threadA get resourceA lock");
                        // 获取resourceB共享资源的监视器锁
                        synchronized (resourceB){
                            System.out.println("threadA get resourceB lock");
                            // 线程A阻塞，并释放获取到的resourceA lock
                            System.out.println("threadA release resourceA lock");
                            resourceA.wait();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // 创建线程
        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 休眠1s
                    Thread.sleep(1000);

                    // 获取resourceA共享资源的监视器锁
                    synchronized (resourceA){
                        System.out.println("threadB get resourceA lock");
                        System.out.println("threadB try get resourceB lock");
                        // 获取resourceB共享资源的监视器锁
                        synchronized (resourceB){
                            System.out.println("threadB get resourceB lock");
                            // 线程B阻塞，并释放获取到的resourceA lock
                            System.out.println("threadA release resourceA lock");
                            resourceA.wait();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // 启动线程
        threadA.start();
        threadB.start();

        // 等待两个线程的结束
        threadA.join();
        threadB.join();

        System.out.println("main over");

    }
}
