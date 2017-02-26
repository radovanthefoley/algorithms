import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class OrderFile {
    static int threads = 0;
    static final ArrayList<Float> numbers = new ArrayList<Float>(100);
    
    synchronized static void addNumber(float value){
        numbers.add(value);
    }
    
    public static void main(String... args) {
        sortUsingArrayList();

    }

    private static void sortUsingArrayList() {
        BufferedReader br = null;
        BufferedWriter bw = null;

        try {
            br = new BufferedReader(new FileReader(
                    "/Users/radovan/Desktop/input.txt"));
            
            while (br.ready()) {
                
                final String line = br.readLine();
                //if(line == null) break;
                final Transformer.CallBack callBack = new Transformer.CallBack() {
                    
                    @Override
                    public void callBack(float value) {
                        addNumber(value);
                        
                    }
                };
                
//                Runnable runnable = new Runnable() {
//                    
//                    @Override
//                    public void run() {
//                        Transformer.transform(new Float(line), callBack);
//                    }
//                };
//                
//                new Thread(runnable).start();
                
                new Thread() {
                    @Override
                    public void run() {
                          Transformer.transform(new Float(line), callBack);
                    }  
                }.start();
                
                threads++;
                //System.out.println(threads);
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

        while(numbers.size() < threads) {
            System.out.println(numbers.size());
            System.out.println(threads);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
        
        Collections.sort(numbers);
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
    }
}
