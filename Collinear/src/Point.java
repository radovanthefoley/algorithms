/*************************************************************************
 * Name: Radovan Masaryk Email: rmasaryk@gmail.com
 * 
 * Compilation: javac Point.java Execution: Dependencies: StdDraw.java
 * 
 * Description: An immutable data type for points in the plane.
 * 
 *************************************************************************/

import java.util.Comparator;

public class Point implements Comparable<Point> {

    /**
     * compare points by slope
     */
    public final Comparator<Point> SLOPE_ORDER = new Comparator<Point>() {

        @Override
        public int compare(Point o1, Point o2) {
            // TODO Auto-generated method stub
            return Double.valueOf(slopeTo(o1)).compareTo(slopeTo(o2));
        }
    };

    private final int x; // x coordinate
    private final int y; // y coordinate

    /**
     * create the point (x, y)
     * 
     * @param x
     *            coordinate
     * @param y
     *            coordinate
     */
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    /**
     * plot this point to standard drawing
     */
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    /**
     * draw line between this point and that point to standard drawing
     * 
     * @param that
     *            other point used to comparison
     */
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    /**
     * slope between this point and that point
     * 
     * @param that
     *            other point used to calculation of slope
     * @return double slope
     */
    public double slopeTo(Point that) { // the same point
        boolean ypsilons = (that.y == this.y);
        boolean xses = (that.x == this.x);

        if (ypsilons && xses)
            return Double.NEGATIVE_INFINITY;
        if (ypsilons) // horizontal slope
            return 0.0; // (Array.sort distinguishes -0.0 and 0.0)
        if (xses) // vertical slope
            return Double.POSITIVE_INFINITY;

        return Double.valueOf(that.y - this.y) / (that.x - this.x);
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public int compareTo(Point that) {
        /* YOUR CODE HERE */
        if (this.y < that.y)
            return -1;
        if (this.y > that.y)
            return +1;
        if (this.x < that.x)
            return -1;
        if (this.x > that.x)
            return +1;
        return 0;
    }

    // return string representation of this point
    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    /**
     * unit tests
     * 
     * @param args
     */
    public static void main(String[] args) {
        /* YOUR CODE HERE */
        Point point = new Point(0, 0);
        Point theSame = new Point(0, 0);
        Point horizont = new Point(-1, 0);
        Point vertical = new Point(0, 1);

        System.out.println(point.slopeTo(theSame));
        System.out.println(point.slopeTo(horizont));
        System.out.println(point.slopeTo(vertical));
        System.out.println("---------------------");
        System.out.println(point.compareTo(theSame));
        System.out.println(point.compareTo(horizont));
        System.out.println(horizont.compareTo(point));
        System.out.println(point.compareTo(vertical));
        System.out.println(vertical.compareTo(point));
    }
}
