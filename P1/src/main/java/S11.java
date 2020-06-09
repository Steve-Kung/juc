/*
设置守护线程
当前进程中如果不含有用户线程，只有守护线程的话，JVM也会立即关闭
 */
public class S11 {
    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (;;){

                }
            }
        });
        // 设置为守护线程
        thread.setDaemon(true);
        thread.start();
        System.out.println("1");
    }
}
