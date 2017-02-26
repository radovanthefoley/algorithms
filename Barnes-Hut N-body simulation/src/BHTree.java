import java.awt.Color;

public class BHTree {
    public static final double THETA = 0.5;

    private Body body; // body or aggregate body stored in this node
    private Quad quad; // square region that the tree represents
    private BHTree NW; // tree representing northwest quadrant
    private BHTree NE; // tree representing northeast quadrant
    private BHTree SW; // tree representing southwest quadrant
    private BHTree SE; // tree representing southeast quadrant

    public BHTree(Quad q) {
        // create a Barnes-Hut tree with no bodies, representing the given
        // quadrant.
        this.quad = q;
    }

    public void insert(Body b) {
        // add the body b to the invoking Barnes-Hut tree.
        if (body == null) {
            this.body = b;
            return;
        }

        if (isExternal()) {
            NW = new BHTree(quad.NW());
            NE = new BHTree(quad.NE());
            SW = new BHTree(quad.SW());
            SE = new BHTree(quad.SE());

            if (body.in(NW.quad))
                NW.insert(body);
            else if (body.in(NE.quad))
                NE.insert(body);
            else if (body.in(SW.quad))
                SW.insert(body);
            else
                SE.insert(body);
        }

        body = body.plus(b);
        if (b.in(NW.quad))
            NW.insert(b);
        else if (b.in(NE.quad))
            NE.insert(b);
        else if (b.in(SW.quad))
            SW.insert(b);
        else
            SE.insert(b);
    }

    public void updateForce(Body b) {
        // approximate the net force acting on body b from all the bodies in the
        // invoking Barnes-Hut tree, and update b's force accordingly.
        if (body == null || body == b)
            return;

        if (isExternal()) {
            b.addForce(body);
            return;
        }

        if (quad.length() / body.distanceTo(b) > THETA) {
            NW.updateForce(b);
            NE.updateForce(b);
            SW.updateForce(b);
            SE.updateForce(b);
        } else {
            b.addForce(body);
            return;
        }
    }

    @Override
    public String toString() {
        if (isExternal())
            return " " + body + "\n";
        else
            return "*" + body + "\n" + NW + NE + SW + SE;
    }

    public void draw() {
        // draw the Barnes-Hut tree using StdDraw.
        if (isExternal()) {
            quad.draw();
            return;
        }

        NW.draw();
        NE.draw();
        SW.draw();
        SE.draw();
    }

    public boolean isExternal() {
        if (NW != null || NE != null || SW != null || SE != null)
            return false;

        return true;
    }

    public static void main(String[] args) {
        // draws initial BHTree

        In in = new In(args[0]); // input file
        int N = in.readInt(); // number of particles
        double radius = in.readDouble(); // radius of universe

        // turn on animation mode and rescale coordinate system
        StdDraw.show(0);
        StdDraw.setXscale(-radius, +radius);
        StdDraw.setYscale(-radius, +radius);

        BHTree tree = new BHTree(new Quad(0, 0, 2 * radius));

        // read in and initialize bodies
        for (int i = 0; i < N; i++) {
            double px = in.readDouble();
            double py = in.readDouble();
            double vx = in.readDouble();
            double vy = in.readDouble();
            double mass = in.readDouble();
            int red = in.readInt();
            int green = in.readInt();
            int blue = in.readInt();
            Color color = new Color(red, green, blue);
            tree.insert(new Body(px, py, vx, vy, mass, color));
        }

        tree.draw();
        
        System.out.println(tree.toString());

        StdDraw.show(0);
    }
}
