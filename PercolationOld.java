/******************************************************************************
 *  Name:    Greg Umali
 *  NetID:   gumali
 *  Precept: P05
 * 
 *  Description: Simulates a grid of N x N squares and offers functionality
 *  to open sites and test whether the system percolates (top-to-bottom path)
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    
    private int iSize;
    private WeightedQuickUnionUF oFlow;
    private WeightedQuickUnionUF oFlowCheck;
    private int[][] iaIsOpen;
    private int iVTop;
    private int iVBottom;
    private int iOpenSites;
    
    // create n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException();
        
        iSize = n;
        
        // two extra for virtual top and bottom
        oFlow = new WeightedQuickUnionUF(iSize * iSize + 2);
        // one extra for just virtual top
        oFlowCheck = new WeightedQuickUnionUF(iSize * iSize + 1);
            
        iaIsOpen = new int[iSize][iSize];
        
        // arbitrarily set to index n^2
        iVTop = iSize * iSize;
        // arbitrarily set to index n^2+1
        iVBottom = iSize * iSize + 1;
        
        iOpenSites = 0;
        
        
    }
        
    private int to1D(int row, int col) {
        return (row * iSize + col);
    }
    
    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        
        if (row < 0 || row >= iSize) throw new IndexOutOfBoundsException();
        if (col < 0 || col >= iSize) throw new IndexOutOfBoundsException();
        
        // if not already open, open the site
        if (iaIsOpen[row][col] == 0) {
            iaIsOpen[row][col] = 1;
            iOpenSites++;
        }
        
        // if not on left edge, check if adjacent left is open; link if so
        if (col != 0) {
            if (iaIsOpen[row][col-1] == 1) {
                oFlow.union(to1D(row, col), to1D(row, col-1));
                oFlowCheck.union(to1D(row, col), to1D(row, col-1));
            }
        }
        
        // if not on right edge, check if adjacent right is open; link if so
        if (col != iSize-1) {
            if (iaIsOpen[row][col+1] == 1) {
                oFlow.union(to1D(row, col), to1D(row, col+1));
                oFlowCheck.union(to1D(row, col), to1D(row, col+1));
            }
        }
        
        // if not on top edge, check if adjacent top is open; link if so
        if (row != 0) {
            if (iaIsOpen[row-1][col] == 1) {
                oFlow.union(to1D(row, col), to1D(row-1, col));
                oFlowCheck.union(to1D(row, col), to1D(row-1, col));
            }
        }
        // if on top edge, link to virtual top
        else {
            oFlow.union(to1D(row, col), iVTop);
            oFlowCheck.union(to1D(row, col), iVTop);
        }
        
        // if not on bottom edge, check if adjacent bottom is open; link if so
        if (row != iSize-1) {
            if (iaIsOpen[row+1][col] == 1) {
                oFlow.union(to1D(row, col), to1D(row+1, col));
                oFlowCheck.union(to1D(row, col), to1D(row+1, col));
            }
        }
        // if on bottom edge, link to virtual bottom (ONLY for oFlow)
        else {
            oFlow.union(to1D(row, col), iVBottom);
        }
    }
    
    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        return (iaIsOpen[row][col] == 1);
    }
    
    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        
        if (row < 0 || row >= iSize) throw new IndexOutOfBoundsException();
        if (col < 0 || col >= iSize) throw new IndexOutOfBoundsException();
        
        return (oFlow.connected(to1D(row, col), iVTop) &&
                oFlowCheck.connected(to1D(row, col), iVTop));
    }
    
    // number of open sites
    public int numberOfOpenSites() {
        return iOpenSites;
    }
    
    // does the system percolate?
    public boolean percolates() {
        return oFlow.connected(iVTop, iVBottom);
    }
    
    // unit testing
    public static void main(String[] args) {
        
        Percolation oPercolator = new Percolation(5);
        
        System.out.println("Corner cases");
        
        oPercolator.open(0, 0);
        oPercolator.open(0, 4);
        oPercolator.open(4, 0);
        oPercolator.open(4, 4);
        
        if (oPercolator.isOpen(0, 1)) System.out.println("(0, 1) is open");
        else System.out.println("(0, 1) is not open");
        
        if (oPercolator.isOpen(0, 0)) System.out.println("(0, 0) is open");
        else System.out.println("(0, 0) is not open");
        
        if (oPercolator.isFull(0, 1)) System.out.println("(0, 1) is full");
        else System.out.println("(0, 1) is not full");
        
        if (oPercolator.isFull(0, 0)) System.out.println("(0, 0) is full");
        else System.out.println("(0, 0) is not full");
        
        
        
        System.out.println("Statement testing");
        
        System.out.println("Open sites: " + oPercolator.numberOfOpenSites());
        
        System.out.println("Opening two sites...");
        oPercolator.open(0, 1);
        oPercolator.open(1, 1);
        
        System.out.println("Open sites: " + oPercolator.numberOfOpenSites());
        
        if (oPercolator.percolates()) System.out.println("Grid percolates");
        else System.out.println("Grid does not percolate");
        
        System.out.println("Opening four more sites...");
        oPercolator.open(1, 2);
        oPercolator.open(2, 2);
        oPercolator.open(3, 2);
        oPercolator.open(4, 2);
        
        System.out.println("Open sites: " + oPercolator.numberOfOpenSites());
        
        if (oPercolator.percolates()) System.out.println("Grid percolates");
        else System.out.println("Grid does not percolate");
    }
}