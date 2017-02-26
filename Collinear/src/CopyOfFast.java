import java.util.Arrays;

public class CopyOfFast {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // rescale coordinates and turn on animation mode
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);

        // read in the input
        String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();

        Point[] points = new Point[N];

        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
            StdDraw.setPenRadius(0.005);
            points[i].draw();
        }

        StdDraw.setPenColor(StdDraw.GREEN);
        StdDraw.setPenRadius();

        for (int index = 0; index < N; index++) {
            Arrays.sort(points); // natural order first (to go through all
                                 // points after they are shuffled in next
                                 // steps)
            Arrays.sort(points, points[index].SLOPE_ORDER); // after this
                                                            // points[index]
                                                            // becomes points[0]
            int i = 1;

            while (i < N) {
                int numberOfSameInRow = 0;

                double previousSlope = points[i - 1].slopeTo(points[0]);
                double actualSlope = points[i].slopeTo(points[0]);

                while (i < N
                        && points[i - 1].slopeTo(points[0]) == points[i]
                                .slopeTo(points[0])) {
                    numberOfSameInRow++;
                    i++;
                }
                if (i <= N && numberOfSameInRow >= 2) {
                    int lo = i - 1 - numberOfSameInRow;
                    int hi = i - 1;
                    // natural order not needed, mergesort is stable
                    // Arrays.sort(points, lo, hi + 1);
                    // in order to print subarray only once
                    if (points[0].compareTo(points[lo]) < 0) {
                        // output
                        System.out.print(points[0]);
                        for (int j = lo; j <= hi; j++)
                            System.out.print(" -> " + points[j]);
                        System.out.println();
                        // draw line
                        points[0].drawTo(points[hi]);
                    }
                }

                i++;
            }
        }

        // display to screen all at once
        StdDraw.show(0);
    }
}
