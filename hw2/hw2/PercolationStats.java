package hw2;

import edu.princeton.cs.introcs.StdRandom;
import org.junit.Test;

public class PercolationStats {
    private double meanOfThresholds;
    private double stdOfThresholds;
    //private double low;
    //private double high;
    private int T;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        this.T = T;
        double[] thresholds = new double[T];
        for (int i = 0; i < thresholds.length; i++) {
            thresholds[i] = PercolationStat(N, pf);
        }
        meanOfThresholds = calMean(thresholds);
        stdOfThresholds = calStddev(thresholds);
    }

    // sample mean of percolation threshold
    public double mean() {
        return meanOfThresholds;
    }

    private double calMean(double[] thresholds) {
        double sum = 0;
        for (double threshold : thresholds) {
            sum += threshold;
        }
        return sum / thresholds.length;
    }

    // sample standard deviation of percolation threshold , should calculate mean previously
    public double stddev() {
        return stdOfThresholds;
    }

    private double calStddev(double[] thresholds) {
        double sum = 0;
        double temp;
        for (double threshold : thresholds) {
            temp = (threshold - meanOfThresholds);
            sum += temp * temp;
        }
        return Math.sqrt(sum / (thresholds.length - 1));
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return meanOfThresholds - 1.96 * stdOfThresholds / Math.sqrt(T);
    }


    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return meanOfThresholds + 1.96 * stdOfThresholds / Math.sqrt(T);
    }

    // perform 1 independent experiments on an N-by-N grid, return threshold
    private double PercolationStat(int N, PercolationFactory pf) {
        Percolation p = pf.make(N);
        int row;
        int col;
        while (!p.percolates()) {
            row = StdRandom.uniform(N);
            col = StdRandom.uniform(N);
            p.open(row, col);
        }
        return p.numberOfOpenSites() / ((double) N * N);
    }

}
