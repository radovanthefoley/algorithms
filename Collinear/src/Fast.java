/**
 * @author Radovan Masaryk
 * 
 *         Given a set of N distinct points in the plane, draw every (maximal)
 *         line segment that connects a subset of 4 or more of the points by
 *         using of mergesort algorithm.
 * 
 */

import java.util.Arrays;

public class Fast {

    /**
     * Given a set of N distinct points in the plane, draw every (maximal) line
     * segment that connects a subset of 4 or more of the points.
     * 
     * @param args
     *            input file
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
        Point[] originalPoints = new Point[N];

        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            Point p = new Point(x, y);
            points[i] = p;
            originalPoints[i] = p;
            points[i].draw();
        }

        StdDraw.setPenColor(StdDraw.GREEN);

        for (int index = 0; index < N; index++) {
            Arrays.sort(points, originalPoints[index].SLOPE_ORDER);
            int i = 1;

            while (i < N) {
                int numberOfSameInRow = 1;
                while (i < N
                        && points[i - 1].slopeTo(originalPoints[index]) == points[i]
                                .slopeTo(originalPoints[index])) {
                    numberOfSameInRow++;
                    i++;
                }
                if (i <= N && numberOfSameInRow >= 3) {
                    int lo = i - numberOfSameInRow;
                    int hi = i - 1;
                    // natural order for the right output
                    Arrays.sort(points, lo, hi + 1);
                    // use only ordered sets in order to avoid
                    // duplicates and correctly drawn lines
                    if (originalPoints[index].compareTo(points[lo]) < 0) {
                        // output
                        System.out.print(originalPoints[index]);
                        for (int j = lo; j <= hi; j++)
                            System.out.print(" -> " + points[j]);
                        System.out.println();
                        // draw line
                        originalPoints[index].drawTo(points[hi]);
                    }
                }

                i++;
            }
        }

        // display to screen all at once
        StdDraw.show(0);
    }
}
