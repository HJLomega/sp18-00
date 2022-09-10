package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private int N;
    //private WeightedQuickUnionUF uf;
    private int nOfOpenSites;
    private int[][] grid;

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        if (N <= 0) {
            throw new java.lang.IllegalArgumentException("N must be positive");
        }
        this.N = N;
        nOfOpenSites = 0;
        //uf = new WeightedQuickUnionUF(N * N);
        grid = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = 0;
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
            grid[row][col] = 1;
            nOfOpenSites += 1;
            newToBeFull(row, col);
        }
    }


    /* check whether a new opened site should be full, if so set that site full */
    private void newToBeFull(int row, int col) {
        if (row == 0) {
            setFull(row, col, row - 1, col);
            return;
        }
        boolean toBeFull;
        try {
            toBeFull = isFull(row - 1, col);
            if (toBeFull) {
                setFull(row, col, row - 1, col);
                return;
            }
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            toBeFull = isFull(row + 1, col);
            if (toBeFull) {
                setFull(row, col, row + 1, col);
                return;
            }
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            toBeFull = isFull(row, col - 1);
            if (toBeFull) {
                setFull(row, col, row, col - 1);
                return;
            }
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            toBeFull = isFull(row, col + 1);
            if (toBeFull) {
                setFull(row, col, row, col + 1);
                return;
            }
        } catch (IndexOutOfBoundsException e) {
        }

    }

    /* check whether a full site should FlowAround, if so set the corresponded site full */
    private void fullFlowAround(int row, int col) {
        boolean toBeFull;
        try {
            toBeFull = isOpen(row - 1, col);
            if (toBeFull) {
                setFull(row - 1, col, row, col);
            }
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            toBeFull = isOpen(row + 1, col);
            if (toBeFull) {
                setFull(row + 1, col, row, col);
            }
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            toBeFull = isOpen(row, col - 1);
            if (toBeFull) {
                setFull(row, col - 1, row, col);
            }
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            toBeFull = isOpen(row, col + 1);
            if (toBeFull) {
                setFull(row, col + 1, row, col);
            }
        } catch (IndexOutOfBoundsException e) {
        }
    }

    /* connect source and the input site,
        check whether the site below  should be open
     */
    private void setFull(int row, int col, int sourceRow, int sourceCol) {
        if (isFull(row, col)) {
            return;
        }
        grid[row][col] = 2;
        //uf.union(xy2Index(row, col), xy2Index(sourceRow, sourceCol));
        fullFlowAround(row, col);
    }


    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        checkRange(row, col);
        return grid[row][col] > 0; //open, full
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        checkRange(row, col);
        return grid[row][col] == 2;
    }

    // number of open sites
    public int numberOfOpenSites() {
        return nOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        for (int j = 0; j < N; j++) {
            if (isFull(N - 1, j)) {
                return true;
            }
        }
        return false;
    }
}
