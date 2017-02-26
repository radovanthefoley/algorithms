public class PointSET {

    private SET<Point2D> set;

    public PointSET() {
        set = new SET<Point2D>();
    } // construct an empty set of points

    public boolean isEmpty() {
        return set.isEmpty();
    } // is the set empty?

    public int size() {
        return set.size();
    } // number of points in the set

    public void insert(Point2D p) {
        if (!contains(p)) {
            set.add(p);
        }
    } // add the point p to the set (if it is not already in the set)

    public boolean contains(Point2D p) {
        return set.contains(p);
    } // does the set contain the point p?

    public void draw() {
        for (Point2D p : set) {
            StdDraw.point(p.x(), p.y());
        }
    } // draw all of the points to standard draw

    public Iterable<Point2D> range(RectHV rect) {
        SET<Point2D> range = new SET<Point2D>();

        for (Point2D p : set) {
            if (rect.contains(p))
                range.add(p);
        }

        return range;
    } // all points in the set that are inside the rectangle

    public Point2D nearest(Point2D p) {
        Point2D nearest = null;
        double distance = -1.0;
        
        for(Point2D candidate : set){
            if(nearest == null){
                nearest = candidate;
                distance = nearest.distanceSquaredTo(p);
            } else {
                double tmpDistance = candidate.distanceSquaredTo(p);
                if(tmpDistance < distance){
                    nearest = candidate;
                    distance = tmpDistance;
                }
            }
        }
        
        return nearest;
    } // a nearest neighbor in the set to p; null if set is empty

    public static void main(String[] args) {
        PointSET set = new PointSET();
        Point2D p1 = new Point2D(0.0, 0.0);
        Point2D p2 = new Point2D(0.15, 0.73);
        Point2D p3 = new Point2D(0.5, 0.2);
        Point2D p4 = new Point2D(0.2, 0.43);
        set.insert(p1);
        set.insert(p2);
        set.insert(p3);
        set.insert(p4);
        // StdDraw.setXscale(0, 1);
        // StdDraw.setYscale(0, 1);
        StdDraw.setPenRadius(0.01);
        set.draw();
    }
}
