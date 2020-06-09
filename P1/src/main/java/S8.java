/**
 * 让出cpu执行权的Thread.yield()方法
 */
public class S8 implements Runnable {
    S8(){
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 5;i++){
            if ((i % 5) == 0){
                System.out.println("-1-");
                // 当前线程让出cpu执行权， 放弃剩下时间片， 进行下一轮调度
//                Thread.yield();
            }
        }
        System.out.println("-2-");
    }

    public static void main(String[] args) {
        new S8();
        new S8();
        new S8();
    }
}
