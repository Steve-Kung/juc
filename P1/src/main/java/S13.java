/*
一般来说，子线程是访问不到父线程的ThreadLocal变量，因为本质是两个不同的线程
如果想访问，借助InheritableThreadLocal类
 */
public class S13 {
//    public static ThreadLocal<String> threadLocal = new ThreadLocal<String>();
    public static ThreadLocal<String> threadLocal = new InheritableThreadLocal<String>();

    public static void main(String[] args) {
        // 父线程设置
        threadLocal.set("hello");
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // 子线程访问
                System.out.println(Thread.currentThread().getName()+"**"+ threadLocal.get());
            }
        });
        thread.start();
        System.out.println(Thread.currentThread().getName()+ "**" + threadLocal.get());
    }
}
