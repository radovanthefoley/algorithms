
public class HelloWorld {

    /**
     * @param args
     */
    public static void main(String[] args) {
     // TODO Auto-generated method stub
        int sps = 44100;
        int hz = 440;
        double duration = 10.0;
        int N = (int) (sps * duration); // total number of samples
        double[] a = new double[N + 1];
        for (int i = 0; i <= N; i++)
            a[i] = Math.sin(2 * Math.PI * i * hz / sps);
        StdAudio.play(a);
    }

}
