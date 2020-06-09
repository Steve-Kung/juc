/*
当一个线程调用共享对象的wait方法被阻塞挂起之后，
如果其他线程中断了该线程，则该线程会抛出java.lang.InterruptedException异常并返回
 */
public class S4 {
    static Object object = new Object();

    public static void main(String[] args) throws InterruptedException {
        // 创建线程
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("-----begin-----");
                    // 阻塞当前线程
                    synchronized (object){
                        object.wait();
                    }
                    System.out.println("-----end-----");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        threadA.start();
        Thread.sleep(1000);
        System.out.println("开始打断线程");
        threadA.interrupt();
        System.out.println("已经打断线程");
    }
}
