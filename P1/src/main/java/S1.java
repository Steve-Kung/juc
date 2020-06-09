import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/*
 三种创建线程的方法
 1. 实现Runnable接口的run方法
 2. 继承Thread类并重写run方法
 3. 使用FutureTask方式
  */
public class S1 {
    //  方式 1
    public static class MyThread extends Thread{
        @Override
        public void run() {
            System.out.println("继承方式 重写run方法");
            System.out.println(this.getName());
        }
    }
    //  方式 2
    public static class RunableTask implements Runnable{
        @Override
        public void run() {
            System.out.println("实现接口方式 重写run方法");
            System.out.println(Thread.currentThread().getName());
        }
    }

    //  方式 3
    public static class CallerTask implements Callable<String>{

        @Override
        public String call() throws Exception {
            return "futureTask 重写call方法 并返回结果";
        }
    }

    @Test
    public void test1(){
        // 创建线程
        MyThread thread = new MyThread();
        thread.start();
    }

    @Test
    public void test2(){
        RunableTask task = new RunableTask();
        new Thread(task).start();
        new Thread(task).start();
    }

    @Test
    public void test3(){
        // 创建异步任务
        FutureTask<String> futureTask = new FutureTask<String>(new CallerTask());
        // 启动线程
        new Thread(futureTask).start();

        try {
            // 等待任务完成可以返回结果
            String result = futureTask.get();
            System.out.println(result);

        } catch (Exception e){
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {



    }
}
