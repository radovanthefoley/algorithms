
public class KdTree {
    private Node root;
    private int size;

    private static class Node {
        private Point2D p; // the point
        private RectHV rect; // the axis-aligned rectangle corresponding to this
                             // node
        private Node lb; // the left/bottom subtree
        private Node rt; // the right/top subtree

        public Node(Point2D p, RectHV rect) {
            this.p = p;
            this.rect = rect;
        }
    }

    public boolean isEmpty() {
        return size == 0;
    } // is the set empty?

    public int size() {
        return size;
    } // number of points in the set

    public void insert(Point2D p) {
        if (!contains(p))
            root = insert(root, p, true);
    }

    private Node insert(Node node, Point2D p, boolean vertical) {
        size++;
        if (node == null)
            return new Node(p, null); // null !!!!!! has to be RectHV
        double cmp;
        if (vertical)
            cmp = p.x() - node.p.x();
        else
            cmp = p.y() - node.p.y();
        vertical = !vertical;
        if (cmp < 0)
            node.lb = insert(node.lb, p, vertical);
        else if (cmp > 0)
            node.rt = insert(node.rt, p, vertical);
        else
            node.p = p;
        return node;
    }

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

        for (Point2D candidate : set) {
            if (nearest == null) {
                nearest = candidate;
                distance = nearest.distanceSquaredTo(p);
            } else {
                double tmpDistance = candidate.distanceSquaredTo(p);
                if (tmpDistance < distance) {
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
