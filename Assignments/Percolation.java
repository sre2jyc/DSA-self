import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Percolation {

    private boolean[][] opened;
    private int top = 0;
    private int bottom;
    private int size;
    private WeightedQuickUnionUF qf;
    private int Opensites = 0 ;

    /**
     * Creates N-by-N grid, with all sites blocked.
     */
    public Percolation(int N) {
        size = N;
        bottom = size * size + 1;
        qf = new WeightedQuickUnionUF(size * size + 2);
        opened = new boolean[size][size];
    }

    /**
     * Opens site (row i, column j) if it is not already.
     */
    public void open(int i, int j) {
        if(!opened[i-1][j-1]){
            Opensites ++ ;
        opened[i - 1][j - 1] = true;
        if (i == 1) {
            qf.union(getQFIndex(i, j), top);
        }
        if (i == size) {
            qf.union(getQFIndex(i, j), bottom);
        }

        if (j > 1 && isOpen(i, j - 1)) {
            qf.union(getQFIndex(i, j), getQFIndex(i, j - 1));
        }
        if (j < size && isOpen(i, j + 1)) {
            qf.union(getQFIndex(i, j), getQFIndex(i, j + 1));
        }
        if (i > 1 && isOpen(i - 1, j)) {
            qf.union(getQFIndex(i, j), getQFIndex(i - 1, j));
        }
        if (i < size && isOpen(i + 1, j)) {
            qf.union(getQFIndex(i, j), getQFIndex(i + 1, j));
        }
    }
    }

    /**
     * Is site (row i, column j) open?
     */
    public boolean isOpen(int i, int j) {
        return opened[i - 1][j - 1];
    }

    /**
     * Is site (row i, column j) full?
     */
    public boolean isFull(int i, int j) {
        if (0 < i && i <= size && 0 < j && j <= size) {
            if(qf.find(top) == qf.find(getQFIndex(i, j))){
                return true ;
            }
            else{
                return false ;
            }
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Does the system percolate?
     */
    public boolean percolates() {
        if(qf.find(top) == qf.find(bottom)){
            return true ;
        }
        else{
            return false ;
        }
    }

    // returns the number of open sites
    public int numberOfOpenSites(){
        return Opensites ;
    }

    private int getQFIndex(int i, int j) {
        return size * (i - 1) + j;
    }
}
