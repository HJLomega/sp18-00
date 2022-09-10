package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private int N;
    private WeightedQuickUnionUF uf;
    private int [][] grid;
    /* 0 : block
       1 : open
       2 : full */
    private int nOfOpenSites;
    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N){
        if (N <= 0){
            throw new java.lang.IllegalArgumentException("N must be positive");
        }
        this.N = N;
        nOfOpenSites = 0;
        uf = new WeightedQuickUnionUF(N*N);
        grid = new int[N][N];
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                grid[i][j] = 0;
            }
        }
    }

    private int xy2Index(int row, int col){
        checkRange(row, col);
        return row * N + col;
    }

    private void checkRange(int row, int col){
        if (row < 0 || row >= N){
            throw new java.lang.IllegalArgumentException("row out of range");
        }
        if (col < 0 || col >= N){
            throw new java.lang.IllegalArgumentException("col out of range");
        }
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col){
        checkRange(row, col);
        if (!isOpen(row, col)) {
            grid[row][col] = 1;
            nOfOpenSites += 1;
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
        checkRange(row, col);
        return grid[row][col] > 0; //open, full
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col){
        checkRange(row, col);
        return grid[row][col] == 2;
    }

    // number of open sites
    public int numberOfOpenSites(){
        return nOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates(){
        return false;
    }
}
