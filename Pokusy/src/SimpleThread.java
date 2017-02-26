
public class SimpleThread extends Thread {

    public static final int NUMBER_OF_THREADS = 2;
    private final String TAG;

    public SimpleThread(String aTAG) {
        TAG = aTAG;
    }

    @Override
    public void run() {
        while (true) {
            //synchronized (this) {
                try {
                    //wait();
                    System.out.println(TAG);
                    //Thread.yield();
                    if((int) (java.lang.Math.random()*100) == 1) {
                        break;
                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            //}
        }
    }

    public static void main(String[] args) throws InterruptedException {
//        CounterThread cthreads[] = new CounterThread[NUMBER_OF_THREADS];
//        for (int i = 0; i < NUMBER_OF_THREADS; i++) {
//            cthreads[i] = new CounterThread("t_" + i);
//            cthreads[i].start();
//        }
//        
//        while (true) {
//            for (int i = 0; i < NUMBER_OF_THREADS; i++) {
//                synchronized (cthreads[i]) {
//                    cthreads[i].notify();
//                    Thread.sleep(50);
//                }
//
//            }
//        }
        SimpleThread a = new SimpleThread("a");
        SimpleThread b = new SimpleThread("b");
        
        a.start();
        a.join();
        b.start();
    }

}
