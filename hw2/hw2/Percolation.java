package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private int N;
    private WeightedQuickUnionUF uf;
    private int nOfOpenSites;
    private boolean[][] grid;
    private final int top;
    private final int down;

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        if (N <= 0) {
            throw new java.lang.IllegalArgumentException("N must be positive");
        }
        this.N = N;
        top = N * N;
        down = top + 1;
        nOfOpenSites = 0;

        uf = new WeightedQuickUnionUF(N * N + 2);// N * N :top , N * N + 1 down

        grid = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = false;
            }
        }
    }

    private int xy2Index(int row, int col) {
        checkRange(row, col);
        return row * N + col;
    }

    private void checkRange(int row, int col) {
        if (row < 0 || row >= N) {
            throw new java.lang.IndexOutOfBoundsException("row out of range");
        }
        if (col < 0 || col >= N) {
            throw new java.lang.IndexOutOfBoundsException("col out of range");
        }
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        checkRange(row, col);
        if (!isOpen(row, col)) {
            grid[row][col] = true;
            nOfOpenSites += 1;
            if (N == 1) {
                uf.union(top, xy2Index(row, col));
                uf.union(down, xy2Index(row, col));
                return;
            }
            if (row == 0) {
                connectAround(row, col);
                uf.union(top, xy2Index(row, col));
                return;
            }
            if (row == N - 1) {
                connectAround(row, col);
                uf.union(down, xy2Index(row, col));
                return;
            }
            connectAround(row, col);
        }
    }

    private void connectAround(int row, int col) {
        boolean opened;
        try {
            opened = isOpen(row - 1, col);
            if (opened) {
                uf.union(xy2Index(row, col), xy2Index(row - 1, col));
            }
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            opened = isOpen(row + 1, col);
            if (opened) {
                uf.union(xy2Index(row, col), xy2Index(row + 1, col));
            }
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            opened = isOpen(row, col - 1);
            if (opened) {
                uf.union(xy2Index(row, col), xy2Index(row, col - 1));
            }
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            opened = isOpen(row, col + 1);
            if (opened) {
                uf.union(xy2Index(row, col), xy2Index(row, col + 1));
            }
        } catch (IndexOutOfBoundsException e) {
        }
    }


    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        checkRange(row, col);
        return grid[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        checkRange(row, col);
        int top = N * N;
        return uf.connected(xy2Index(row, col), top);
    }

    // number of open sites
    public int numberOfOpenSites() {
        return nOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.connected(top, down);
    }

    public static void main(String[] args) {

    }
}
