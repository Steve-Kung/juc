/**
 * notify() 唤醒相同锁的线程
 * notifyAll()
 */
public class S5 {
    private static volatile Object resourceA = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread threadA  = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resourceA){
                    try {
                        System.out.println("-----1------");
                        resourceA.wait();
                        System.out.println("------2-----");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
         Thread threadB = new Thread(new Runnable() {
             @Override
             public void run() {
                 synchronized (resourceA){
                     try {
                         System.out.println("-----3------");
                         resourceA.wait();
                         System.out.println("-----4------");
                     } catch (InterruptedException e) {
                         e.printStackTrace();
                     }
                 }
             }
         });

         Thread threadC = new Thread(new Runnable() {
             @Override
             public void run() {
                 synchronized (resourceA){
                     // 随机唤醒一个 缺少resourceA锁的线程
//                     resourceA.notify();
                     resourceA.notifyAll();
                 }

             }
         });


         threadA.start();
         threadB.start();
         Thread.sleep(1000);

         threadC.start();

         // 等待线程结束
        threadA.join();
        threadB.join();
        threadC.join();

        System.out.println("main -- over");
    }
}
