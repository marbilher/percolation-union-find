/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    public Cell[][] sites;
    private final WeightedQuickUnionUF unionFind;
    private final WeightedQuickUnionUF unionFindFull;
    private final int top;
    private final int bottom;
    private final int dimension;
    private int openSites;

    private class Cell {

        public Cell() {

        }
    }


    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException("N must be > 0");

        sites = new Cell[n][n];
        this.dimension = n;
        this.unionFind = new WeightedQuickUnionUF(n * n + 2);
        this.unionFindFull = new WeightedQuickUnionUF(n * n + 1);
        this.top = n * n;
        this.bottom = n * n + 1;
        this.openSites = 0;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validateSite(row, col);
        int flattenedSite = flattenMatrix(row, col) - 1;

        if (isOpen(row, col)) {
            return;
        }

        sites[row - 1][col - 1] = new Cell();
        this.openSites++;
        if (percolates()) {
            StdOut.printf("%nThe System percolates %n");
        }
        else {
            StdOut.printf("%nDoes not percolate %n");
        }

        //merge imaginary sites
        if (row == 1) {
            unionFind.union(this.top, flattenedSite);
            unionFindFull.union(this.top, flattenedSite);
        }

        if (row == this.dimension)
            unionFind.union(this.top, flattenedSite);

        //merge components
        if (isValidSite(row + 1, col) && isOpen(row + 1, col)) {
            unionFind.union(flattenedSite, flattenMatrix(row + 1, col) - 1);
            unionFindFull.union(flattenedSite, flattenMatrix(row + 1, col) - 1);
        }
        if (isValidSite(row - 1, col) && isOpen(row - 1, col)) {
            unionFind.union(flattenedSite, flattenMatrix(row - 1, col) - 1);
            unionFindFull.union(flattenedSite, flattenMatrix(row - 1, col) - 1);
        }
        if (isValidSite(row, col + 1) && isOpen(row, col + 1)) {
            unionFind.union(flattenedSite, flattenMatrix(row, col + 1) - 1);
            unionFindFull.union(flattenedSite, flattenMatrix(row, col + 1) - 1);
        }
        if (isValidSite(row, col - 1) && isOpen(row, col - 1)) {
            unionFind.union(flattenedSite, flattenMatrix(row, col - 1) - 1);
            unionFindFull.union(flattenedSite, flattenMatrix(row, col - 1) - 1);
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (isValidSite(row, col)) {
            return sites[row - 1][col - 1] != null;
        }
        else {
            return false;
        }
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {

        // row = row - 1;
        // col = col - 1;
        validateSite(row, col);
        return unionFindFull.connected(this.top, flattenMatrix(row, col) - 1);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        // int count = 0;
        // for (int i = 0; i < sites.length; i++) {
        //     for (int j = 0; j < sites.length; j++) {
        //         if (sites[i][j] != null) count++;
        //     }
        // }
        // return count;
        return this.openSites;
    }

    private int flattenMatrix(int row, int col) {
        return this.dimension * (row - 1) + col;
    }

    public boolean isValidSite(int row, int col) {
        row = row - 1;
        col = col - 1;
        return (row < this.dimension && col < this.dimension && row >= 0 && col >= 0);
    }

    private void validateSite(int row, int col) {
        if (!isValidSite(row, col)) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
    }


    // does the system percolate?
    public boolean percolates() {
        return unionFind.connected(this.top, this.bottom);
    }

    public static void main(String[] args) {

        // Percolation test = new Percolation(10);
        // test.open(0, 1);
        // System.out.println("foo:" + test.sites[0][0] + test.numberOfOpenSites());


    }

}
