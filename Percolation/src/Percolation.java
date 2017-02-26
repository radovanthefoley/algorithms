/**
 * @author Radovan Masaryk
 * 
 *         <i>Percolation model</i>. This class models 2D percolation problem
 *         using WeightedQuickUnionUF algorithm as effective way to determine
 *         percolation probability.
 */

public class Percolation {

    // holds 2D structure, true for opened sites
    private boolean[][] grid;

    // size
    private final int N;

    // WeightedQuickUnionUF objects hold connection algorithm
    private WeightedQuickUnionUF wquTopBottom; // used for percolation test
    private WeightedQuickUnionUF wquTopOnly; // used for isFull test, avoiding
                                             // backwash

    /**
     * Create Percolation object for N*N 2D grid system
     * 
     * @param N
     *            the size of 2D grid
     */
    public Percolation(int N) {
        this.N = N;

        wquTopBottom = new WeightedQuickUnionUF(N * N + 2); // +2 for virtual
                                                            // sites

        wquTopOnly = new WeightedQuickUnionUF(N * N + 1); // +1 for virtual top

        grid = new boolean[N + 1][N + 1]; // extra row and column for more
                                          // readable code, all false (not
                                          // opened by default)
    }

    /**
     * Open site with i and j coordinates
     * 
     * @param i
     *            row coordinate
     * @param j
     *            column coordinate
     */
    public void open(int i, int j) {
        checkIndexes(i, j);

        if (grid[i][j])
            return;

        grid[i][j] = true;

        // connection with surrounding sites (only if exist)
        if (j - 1 > 0 && isOpen(i, j - 1)) {
            wquTopBottom.union(xyTo1D(i, j), xyTo1D(i, j - 1));
            wquTopOnly.union(xyTo1D(i, j), xyTo1D(i, j - 1));
        }
        if (j + 1 <= N && isOpen(i, j + 1)) {
            wquTopBottom.union(xyTo1D(i, j), xyTo1D(i, j + 1));
            wquTopOnly.union(xyTo1D(i, j), xyTo1D(i, j + 1));
        }
        if (i - 1 > 0 && isOpen(i - 1, j)) {
            wquTopBottom.union(xyTo1D(i, j), xyTo1D(i - 1, j));
            wquTopOnly.union(xyTo1D(i, j), xyTo1D(i - 1, j));
        }
        if (i + 1 <= N && isOpen(i + 1, j)) {
            wquTopBottom.union(xyTo1D(i, j), xyTo1D(i + 1, j));
            wquTopOnly.union(xyTo1D(i, j), xyTo1D(i + 1, j));
        }
        // connection with virtual sites if first or last row
        if (i == 1) {
            wquTopBottom.union(xyTo1D(i, j), N * N);
            wquTopOnly.union(xyTo1D(i, j), N * N);
        }
        if (i == N) {
            wquTopBottom.union(xyTo1D(i, j), N * N + 1);
        }
    }

    /**
     * Is the site with i and j coordinates open?
     * 
     * @param i
     *            row coordinate
     * @param j
     *            column coordinate
     * @return true or false
     */
    public boolean isOpen(int i, int j) {
        checkIndexes(i, j);

        return grid[i][j];
    }

    /**
     * Is the open site with i and j coordinates connected to an open site in
     * the top row via a chain of neighboring (left, right, up, down) open
     * sites?
     * 
     * @param i
     *            row coordinate
     * @param j
     *            column coordinate
     * @return true or false
     */
    public boolean isFull(int i, int j) {
        checkIndexes(i, j);

        // site is full if it is connected with upper virtual site
        return wquTopOnly.connected(xyTo1D(i, j), N * N);
    }

    /**
     * Does the system percolates? In other words: Is there any open site in the
     * bottom row connected to an open site in the top row via a chain of
     * neighboring (left, right, up, down) open sites?
     * 
     * @return true or false
     */
    public boolean percolates() {
        // system percolates if virtual sites are connected
        return wquTopBottom.connected(N * N, N * N + 1);
    }

    // check if indexes are not out of bounds
    private void checkIndexes(int i, int j) {
        if (i <= 0 || i > N)
            throw new IndexOutOfBoundsException("row index i out of bounds");
        if (j <= 0 || j > N)
            throw new IndexOutOfBoundsException("row index j out of bounds");
    }

    // mapping of 2D grid into 1D array
    private int xyTo1D(int i, int j) {
        return N * i - j;
    }

    // unit tests
    public static void main(String[] args) {
        final int N = 3;
        Percolation p = new Percolation(N);

        // test of xyTo1D
        System.out.println("Mapping, should return interval "
                + "of integers from 0 (included) to " + (N * N - 1)
                + " (included):");
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                System.out.print(p.xyTo1D(i, j) + "\t");
            }
            System.out.println();
        }

        // test isOpen(), open(), isFull(), percolates()
        System.out.println("isOpen(1, 1) should return false: "
                + p.isOpen(1, 1));
        p.open(1, 1);
        System.out.println("executed: open(1, 1);");
        System.out
                .println("isOpen(1, 1) should return true: " + p.isOpen(1, 1));
        p.open(1, 2);
        System.out.println("executed: open(1, 2);");
        p.open(2, 2);
        System.out.println("executed: open(2, 2);");
        System.out.println("isFull(2,2) should return true: " + p.isFull(2, 2));
        p.open(3, 3);
        System.out.println("executed: open(3, 3);");
        System.out.println("percolates() should return false: "
                + p.percolates());
        p.open(2, 3);
        System.out.println("executed: open(2, 3);");
        System.out
                .println("percolates() should return true: " + p.percolates());
    }
}
