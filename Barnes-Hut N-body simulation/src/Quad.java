public class Quad {

    private double xmid, ymid, length;

    public Quad(double xmid, double ymid, double length) {
        // create a new quadrant centered at (xmid, ymid) of the given side
        // length.
        this.xmid = xmid;
        this.ymid = ymid;
        this.length = length;
    }

    public boolean contains(double x, double y) {
        // Return true if (x, y) is in the quadrant, and false otherwise.
        if (x >= xmid - length / 2 && x < xmid + length / 2
                && y >= ymid - length / 2 && y < ymid + length / 2)
            return true;
        return false;
    }

    public double length() {
        // Returns the length of a side of the quadrant.
        return length;
    }

    public Quad NW() {
        // return a new Quad representing a sub-quadrant of the invoking
        // quadrant.
        return new Quad(xmid - length / 4, ymid + length / 4, length / 2);
    }

    public Quad NE() {
        // return a new Quad representing a sub-quadrant of the invoking
        // quadrant.
        return new Quad(xmid + length / 4, ymid + length / 4, length / 2);
    }

    public Quad SW() {
        // return a new Quad representing a sub-quadrant of the invoking
        // quadrant.
        return new Quad(xmid - length / 4, ymid - length / 4, length / 2);
    }

    public Quad SE() {
        // return a new Quad representing a sub-quadrant of the invoking
        // quadrant.
        return new Quad(xmid + length / 4, ymid - length / 4, length / 2);
    }

    @Override
    public String toString() {
        return "Quad [xmid=" + xmid + ", ymid=" + ymid + ", length=" + length
                + "]";
    }

    public void draw() {
        // draw the quad using StdDraw.
        StdDraw.rectangle(xmid, ymid, length / 2, length / 2);
    }

    private void draw(double x, double y) {
        // testing
        if (contains(x, y)) {
            StdDraw.filledRectangle(xmid, ymid, length / 2, length / 2);
        } else {
            StdDraw.rectangle(xmid, ymid, length / 2, length / 2);
        }
    }

    public static void main(String[] args) {
        // testing
        StdDraw.setScale(-6, 6);

        Quad q = new Quad(0, 0, 10);
        q.draw();

        Quad nwq = q.NW();
        nwq.draw();
        Quad neq = q.NE();
        neq.draw();
        Quad swq = q.SW();
        swq.draw();
        Quad seq = q.SE();
        seq.draw();

        Quad senwq = seq.NW();
        senwq.draw();
        Quad seneq = seq.NE();
        seneq.draw();
        Quad seswq = seq.SW();
        seswq.draw();
        Quad seseq = seq.SE();
        seseq.draw();

        while (true) {
            if (StdDraw.mousePressed()) {
                double x = StdDraw.mouseX();
                double y = StdDraw.mouseY();
                nwq.draw(x, y);
                neq.draw(x, y);
                swq.draw(x, y);
                senwq.draw(x, y);
                seneq.draw(x, y);
                seswq.draw(x, y);
                seseq.draw(x, y);
            }
        }
    }
}
