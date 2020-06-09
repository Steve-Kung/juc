/*
线程中断 void interrupt() 中断线程
  boolean  isInterrupted() 获取当前线程的中断标志位
  boolean interrupted() 获取当前调用线程的中断标志位

 */
public class S9 {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (;;){

                }
            }
        });
        thread1.start();
        thread1.interrupt();
        System.out.println(thread1.isInterrupted());
        System.out.println(thread1.interrupted()); // 调用线程是主线程
        System.out.println(Thread.interrupted()); // 调用线程也是主线程
        System.out.println(thread1.isInterrupted());
    }

}
