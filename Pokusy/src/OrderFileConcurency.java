import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class OrderFileConcurency {
    static int threads = 0;
    static final ArrayList<Float> numbers = new ArrayList<Float>(100);

    synchronized static void addNumber(float value) {
        numbers.add(value);
    }

    public static void main(String... args) {
        sortUsingArrayList();

    }

    private static void sortUsingArrayList() {
        ExecutorService es = Executors.newFixedThreadPool(1000);
        CompletionService<Object> cs = new ExecutorCompletionService<Object>(es);
        BufferedReader br = null;
        BufferedWriter bw = null;

        try {
            br = new BufferedReader(new FileReader(
                    "/Users/radovan/Desktop/input.txt"));

            while (br.ready()) {

                final String line = br.readLine();
                final Transformer.CallBack callBack = new Transformer.CallBack() {

                    @Override
                    public void callBack(float value) {
                        addNumber(value);

                    }
                };

                Runnable runnable = new Runnable() {

                    @Override
                    public void run() {
                        Transformer.transform(new Float(line), callBack);
                    }
                };

                cs.submit(runnable, null);

                threads++;
                // System.out.println(threads);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < threads; i++) {
            try {
                cs.take();
                System.out.println(i);
                
            } catch (InterruptedException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
        
        es.shutdown();
        
        System.out.println("sort started");

        Collections.sort(numbers);
        
        System.out.println("sort finished");
        
        Iterator<Float> iter = numbers.iterator();

        try {
            bw = new BufferedWriter(new FileWriter(
                    "/Users/radovan/Desktop/output.txt"));
            while (iter.hasNext()) {
                bw.write(iter.next().toString());
                bw.newLine();
            }

            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null)
                    bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        System.out.println("end");
    }
}
