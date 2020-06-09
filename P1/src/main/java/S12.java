/*
ThreadLocal 线程本地变量
创建一个ThreadLocal变量后， 每个线程都会复制一个变量到自己的本地内存互不影响

ThreadLocal的原理
ThreadLocal有点类似于一个工具类，
通过set方法把value值放入到调用线程的threadLocals成员变量中
get也相应从对应地方取出来
 */
public class S12 {
    private static ThreadLocal<String> localVariable = new ThreadLocal<String>();

    static void print(String str){
        System.out.println(str + "****" + localVariable.get());
        localVariable.remove();
    }

    public static void main(String[] args) {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                localVariable.set("1111");
                print("thread-1");
                System.out.println(localVariable.get());
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                localVariable.set("2222");
                print("thread-2");
                System.out.println(localVariable.get());
            }
        });

        thread1.start();
        thread2.start();
    }
}
