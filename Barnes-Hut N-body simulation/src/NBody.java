import java.awt.Color;

public class NBody {
    public static final int STEPS_PER_ANIMATION = 100;
    public static final double dt = 0.001;

    public static void main(String[] args) {
        In in = new In(args[0]); // input file
        int N = in.readInt(); // number of particles
        double radius = in.readDouble(); // radius of universe

        // turn on animation mode and rescale coordinate system
        StdDraw.show(0);
        StdDraw.setXscale(-radius, +radius);
        StdDraw.setYscale(-radius, +radius);

        BHTree tree;

        // read in and initialize bodies
        Body[] bodies = new Body[N]; // array of N bodies
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
            // color = Color.WHITE;
            bodies[i] = new Body(px, py, vx, vy, mass, color);
        }

        // tree.draw();

        StdDraw.show(0);
        
        int step = 0;

        // simulate the universe
        for (double t = 0.0; true; t = t + dt) {
            if(StdDraw.mousePressed()){
                System.out.println("X: " + StdDraw.mouseX() + "\tY: " + StdDraw.mouseY());
            }
            // populate BHTree in each step
            tree = new BHTree(new Quad(0, 0, 2 * radius));
            double rx, ry;
            for (int i = 0; i < N; i++) {
                rx = bodies[i].getRx();
                ry = bodies[i].getRy();
                // insert only if within Universe
                if (rx * rx <= radius * radius && ry * ry <= radius * radius)
                    tree.insert(bodies[i]);
            }

            // update the forces and positions
            for (int i = 0; i < N; i++) {
                bodies[i].resetForce();
                tree.updateForce(bodies[i]);
            }

            // update the bodies
            for (int i = 0; i < N; i++) {
                bodies[i].update(dt);
            }

            // draw the bodies
            StdDraw.clear(StdDraw.BLACK);
            for (int i = 0; i < N; i++) {
                bodies[i].draw();
            }
            
            step++;
            
            if (step == STEPS_PER_ANIMATION) {
                StdDraw.show(0);
                step = 0;
            }        
        }
    }
}
