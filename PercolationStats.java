/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private int numTrials;
    private double[] trialResults;


    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) throw new IllegalArgumentException("n and trials must be >= 1");

        int dimension = n;
        numTrials = trials;
        trialResults = new double[numTrials];

        for (int trial = 0; trial < numTrials; trial++) {
            Percolation percolation = new Percolation(dimension);
            while (!percolation.percolates()) {
                int row = StdRandom.uniform(1, dimension + 1);
                int col = StdRandom.uniform(1, dimension + 1);
                percolation.open(row, col);
            }
            int openSites = percolation.numberOfOpenSites();
            double result = (double) openSites / (dimension * dimension);
            trialResults[trial] = result;
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(trialResults);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(trialResults);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - ((1.96 * stddev()) / Math.sqrt(numTrials));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + ((1.96 * stddev()) / Math.sqrt(numTrials));
    }

    // test client (see below)
    public static void main(String[] args) {
        int dimension = 10;
        int trialCount = 10;
        if (args.length >= 2) {
            dimension = Integer.parseInt(args[0]);
            trialCount = Integer.parseInt(args[1]);
        }
        PercolationStats ps = new PercolationStats(dimension, trialCount);

        String confidence = ps.confidenceLo() + " / " + ps.confidenceHi();
        StdOut.println("mean = " + ps.mean());
        StdOut.println("stddev = " + ps.stddev());
        StdOut.println("95% confidence interval = " + confidence);
    }
    // Thanks oak2278, I referenced your work several times when stuck
}
