/*
thread1.join()方法 等待当前线程执行完毕
 */
public class S6 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("-1-");
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("-2-");
            }
        });

        thread1.start();
        thread2.start();

        System.out.println("-3-");

        thread1.join();
        thread2.join();

        System.out.println("-4-");

    }
}
