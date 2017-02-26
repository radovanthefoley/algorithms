/**
 * @author Radovan Masaryk
 * 
 *         Given a set of N distinct points in the plane, draw every (maximal)
 *         line segment that connects a subset of 4 points by using brute-force
 *         algorithm.
 * 
 */

public class Brute {

    /**
     * Given a set of N distinct points in the plane, draw every (maximal) line
     * segment that connects a subset of 4 points by using brute-force
     * algorithm.ts.
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

        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
            points[i].draw();
        }

        StdDraw.setPenColor(StdDraw.RED);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == j)
                    continue;
                for (int k = 0; k < N; k++) {
                    if (i == k || j == k)
                        continue;
                    for (int l = 0; l < N; l++) {
                        if (i == l || j == l || k == l)
                            continue;
                        if ((points[i].slopeTo(points[j]) == points[i]
                                .slopeTo(points[k]))
                                && ((points[i].slopeTo(points[j]) == points[i]
                                        .slopeTo(points[l]))))
                            // use only ordered sets in order to avoid
                            // duplicates and correctly drawn lines
                            if (points[i].compareTo(points[j]) < 1
                                    && points[j].compareTo(points[k]) < 1
                                    && points[k].compareTo(points[l]) < 1) {
                                System.out.println(points[i] + " -> "
                                        + points[j] + " -> " + points[k]
                                        + " -> " + points[l]);
                                points[i].drawTo(points[l]);
                            }
                    }
                }
            }
        }
        
        // display to screen all at once
        StdDraw.show(0);
    }
}
