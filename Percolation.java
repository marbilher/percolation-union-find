/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

public class Percolation {

    public Cell[][] sites;

    private class Cell {
        public boolean full;

        public Cell() {

        }
    }


    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        sites = new Cell[n][n];
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        sites[row][col] = new Cell();
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (sites[row][col] != null) {
            return true;
        }
        else {
            return false;
        }
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (sites[row][col].full) {
            return true;
        }
        else {
            return false;
        }
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        int count = 0;
        for (int i = 0; i < sites.length; i++) {
            for (int j = 0; j < sites.length; j++) {
                if (sites[i][j] != null) count++;
            }
        }
        return count;
    }

    // does the system percolate?
    public boolean percolates() {
        return true;
    }

    public static void main(String[] args) {

        Percolation test = new Percolation(10);
        test.open(0, 1);
        System.out.println("foo:" + test.sites[0][0] + test.numberOfOpenSites());


    }

}
