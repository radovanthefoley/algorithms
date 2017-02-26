
public class Transformer {
    public interface CallBack {
        void callBack(float value);
    }
    
    public static void transform(float value, CallBack callBack) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        callBack.callBack(value + 100);
    }
}
