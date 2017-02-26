import java.util.Random;

public class Triangel {
    private static final int RUNS = 1000000;

    private static boolean isTriangel(double x, double y) {
        if (x > y) {
            double tmp = x;
            x = y;
            y = tmp;
        }

        double a = x;
        double b = y - x;
        double c = 1 - a - b;

        assert a + b + c == 1d;

        if (a + b < c)
            return false;
        if (a + c < b)
            return false;
        if (c + b < a)
            return false;

        return true;
    }

    public static void main(String[] args) {
        int success = 0;
        Random r = new Random();
        for (int i = 0; i < RUNS; i++) {
            if (isTriangel(r.nextDouble(), r.nextDouble())) {
                success++;
            }
        }
        System.out.println("p = " + success*1.0/RUNS);
    }

}
