/**
 * @author Radovan Masaryk
 * 
 *         <i>Percolation statistics</i>. This class executes series of
 *         percolation runs and provides statistical methods to describe
 *         achieved results such as sample mean, sample standard deviation and
 *         95% confidence interval.
 */

public class PercolationStats {

    // holds result of every single run
    private double[] arrThresholds;

    // number of runs
    private final int T;

    /**
     * Creates PercolationStats object to find N*N 2D grid percolation system
     * threshold probability. Statistical data are gathered after T rounds of
     * execution.
     * 
     * @param N
     *            the size of N*N 2D grid
     * @param T
     *            the number of runs
     */
    public PercolationStats(int N, int T) {
        if (N <= 0)
            throw new IllegalArgumentException("illegal argument N <= 0");
        if (T <= 0)
            throw new IllegalArgumentException("illegal argument T <= 0");

        arrThresholds = new double[T];
        this.T = T;

        for (int i = 0; i < T; i++) {
            arrThresholds[i] = (double) executeOneRun(N) / (N * N);
        }
    }

    /**
     * What is the sample mean threshold probability?
     * 
     * @return sample mean threshold probability
     */
    public double mean() {
        return StdStats.mean(arrThresholds);
    }

    /**
     * What is the sample standard deviation of threshold probability?
     * 
     * @return sample standard deviation of threshold probability
     */
    public double stddev() {
        return StdStats.stddev(arrThresholds);
    }

    /**
     * What is the lower bound of the 95% confidence interval of threshold
     * probability?
     * 
     * @return lower bound of the 95% confidence interval of threshold
     *         probability
     */
    public double confidenceLo() {
        return mean() - (1.96 * stddev() / Math.sqrt(T));
    }

    /**
     * What is the higher bound of the 95% confidence interval of threshold
     * probability?
     * 
     * @return higher bound of the 95% confidence interval of threshold
     *         probability
     */
    public double confidenceHi() {
        return mean() + (1.96 * stddev() / Math.sqrt(T));
    }

    // return the threshold for particular run (the lowest number of opened
    // sites sufficient for system to percolate)
    private int executeOneRun(int N) {
        if (N <= 0)
            throw new IndexOutOfBoundsException("illegal argument N <= 0");

        int i, j;
        int threshold = 0;

        Percolation p = new Percolation(N);
        while (!p.percolates()) {
            do {
                i = StdRandom.uniform(N) + 1;
                j = StdRandom.uniform(N) + 1;
            } while (p.isOpen(i, j));

            p.open(i, j);

            threshold++;
        }

        return threshold;
    }

    /**
     * Obtain statistical data.
     * 
     * @param args
     *            input parameters, 1st - size of the system, 2nd - number of
     *            rounds
     */
    public static void main(String[] args) {
        int N, T;
        if (args.length == 2) {
            N = Integer.parseInt(args[0]);
            T = Integer.parseInt(args[1]);
        } else {
            throw new IllegalArgumentException(
                    "missing or illegal arguments, method takes N and T");
        }

        Stopwatch sw = new Stopwatch();

        PercolationStats ps = new PercolationStats(N, T);
        System.out.println("elapsed time: " + sw.elapsedTime());
        System.out.println("mean\t\t\t= " + ps.mean());
        System.out.println("stddev\t\t\t= " + ps.stddev());
        System.out.println("95% confidence interval\t= " + ps.confidenceLo()
                + ", " + ps.confidenceHi());
    }
}
