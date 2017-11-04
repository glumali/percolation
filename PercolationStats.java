/******************************************************************************
 *  Name:    Greg Umali
 *  NetID:   gumali
 *  Precept: P05
 * 
 *  Description: Performs a Monte Carlo situation to estimate the percolation
 *  threshold p* for a percolating system.
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {
    
    private static int iSize;
    private static double[] daThresholds;
    private static int iNumTrials;
    
    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials) {
        
        // count variable for the for loop
        int i;
        // stores the working Percolation trial
        Percolation oPercolator;
        
        // used to choose a random space to open in the Percolation simulation
        int row;
        int col; 
        
        // tracks how many currently open sites there are in the grid
        int iOpenSites;
        
        if (n <= 0) throw new IllegalArgumentException();
        
        iSize = n;
        iNumTrials = trials;
        
        daThresholds = new double[iNumTrials];
        
        for (i = 0; i < iNumTrials; i++) {
            iOpenSites = 0;
            
            oPercolator = new Percolation(iSize);
            
            while (!oPercolator.percolates()) {
                row = StdRandom.uniform(iSize);
                col = StdRandom.uniform(iSize);
                
                if (!oPercolator.isOpen(row, col)) {
                    oPercolator.open(row, col);
                    iOpenSites++;
                }
            }
            daThresholds[i] = (double) iOpenSites / (iSize*iSize);
        }
    }
        
    // sample mean of percolation threshold
    public double mean() {
        
        return StdStats.mean(daThresholds);
    }
    
    // sample standard deviation of percolation threshold
    public double stddev() {
        
        return StdStats.stddev(daThresholds);
    }
    
    // low  endpoint of 95% confidence interval
    public double confidenceLow() {
        return (mean() - 1.96*stddev() / Math.sqrt(iNumTrials));
    }
    
    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return (mean() + 1.96*stddev() / Math.sqrt(iNumTrials));
    }
    
    // unit testing
    public static void main(String[] args) {
        
        int iGridSize;
        int iTrials;
        // test object
        PercolationStats oPercStats;
        // used to time during timing analysis
        Stopwatch oTimer;
        
        // unit testing
        
        System.out.println("Unit testing");
        
        iGridSize = 200;
        iTrials = 100;
        oPercStats = new PercolationStats(iGridSize, iTrials);
        
        System.out.format("mean()                  = %f\n", 
                          oPercStats.mean());
        System.out.format("stddev()                = %f\n", 
                          oPercStats.stddev());
        System.out.format("confidenceLow()         = %f\n", 
                          oPercStats.confidenceLow());
        System.out.format("confidenceHigh()        = %f\n", 
                          oPercStats.confidenceHigh());
        
        System.out.println();
        
        iGridSize = 2;
        iTrials = 100000;
        oPercStats = new PercolationStats(iGridSize, iTrials);
        
        System.out.format("mean()                  = %f\n", 
                          oPercStats.mean());
        System.out.format("stddev()                = %f\n", 
                          oPercStats.stddev());
        System.out.format("confidenceLow()         = %f\n", 
                          oPercStats.confidenceLow());
        System.out.format("confidenceHigh()        = %f\n", 
                          oPercStats.confidenceHigh());
        
        System.out.println("\nTiming analysis, T constant");
        
        // n = 50, T = 50
        iGridSize = 50;
        iTrials = 50;
        System.out.printf("n = %8d T = %8d\n", iGridSize, iTrials);
        oTimer = new Stopwatch();
        oPercStats = new PercolationStats(iGridSize, iTrials);
        System.out.println("Time: " + oTimer.elapsedTime() + "\n");
        
        // n = 100, T = 50
        iGridSize = 100;
        iTrials = 50;
        System.out.printf("n = %8d T = %8d\n", iGridSize, iTrials);
        oTimer = new Stopwatch();
        oPercStats = new PercolationStats(iGridSize, iTrials);
        System.out.println("Time: " + oTimer.elapsedTime() + "\n");
        
        // n = 200, T = 50
        iGridSize = 200;
        iTrials = 50;
        System.out.printf("n = %8d T = %8d\n", iGridSize, iTrials);
        oTimer = new Stopwatch();
        oPercStats = new PercolationStats(iGridSize, iTrials);
        System.out.println("Time: " + oTimer.elapsedTime() + "\n");
        
        // n = 400, T = 50
        iGridSize = 400;
        iTrials = 50;
        System.out.printf("n = %8d T = %8d\n", iGridSize, iTrials);
        oTimer = new Stopwatch();
        oPercStats = new PercolationStats(iGridSize, iTrials);
        System.out.println("Time: " + oTimer.elapsedTime() + "\n");
        
        // n = 800, T = 50
        iGridSize = 800;
        iTrials = 50;
        System.out.printf("n = %8d T = %8d\n", iGridSize, iTrials);
        oTimer = new Stopwatch();
        oPercStats = new PercolationStats(iGridSize, iTrials);
        System.out.println("Time: " + oTimer.elapsedTime() + "\n");
        
        // n = 1600, T = 50
        iGridSize = 1600;
        iTrials = 50;
        System.out.printf("n = %8d T = %8d\n", iGridSize, iTrials);
        oTimer = new Stopwatch();
        oPercStats = new PercolationStats(iGridSize, iTrials);
        System.out.println("Time: " + oTimer.elapsedTime() + "\n");
        
        
        System.out.println("\nTiming analysis, N constant");
        
        // n = 200, T = 50
        iGridSize = 200;
        iTrials = 50;
        System.out.printf("n = %8d T = %8d\n", iGridSize, iTrials);
        oTimer = new Stopwatch();
        oPercStats = new PercolationStats(iGridSize, iTrials);
        System.out.println("Time: " + oTimer.elapsedTime() + "\n");
        
        // n = 200, T = 100
        iGridSize = 200;
        iTrials = 100;
        System.out.printf("n = %8d T = %8d\n", iGridSize, iTrials);
        oTimer = new Stopwatch();
        oPercStats = new PercolationStats(iGridSize, iTrials);
        System.out.println("Time: " + oTimer.elapsedTime() + "\n");
        
        // n = 200, T = 200
        iGridSize = 200;
        iTrials = 200;
        System.out.printf("n = %8d T = %8d\n", iGridSize, iTrials);
        oTimer = new Stopwatch();
        oPercStats = new PercolationStats(iGridSize, iTrials);
        System.out.println("Time: " + oTimer.elapsedTime() + "\n");
        
        // n = 200, T = 400
        iGridSize = 200;
        iTrials = 400;
        System.out.printf("n = %8d T = %8d\n", iGridSize, iTrials);
        oTimer = new Stopwatch();
        oPercStats = new PercolationStats(iGridSize, iTrials);
        System.out.println("Time: " + oTimer.elapsedTime() + "\n");
        
        // n = 200, T = 800
        iGridSize = 200;
        iTrials = 800;
        System.out.printf("n = %8d T = %8d\n", iGridSize, iTrials);
        oTimer = new Stopwatch();
        oPercStats = new PercolationStats(iGridSize, iTrials);
        System.out.println("Time: " + oTimer.elapsedTime() + "\n");
        
        // n = 200, T = 1600
        iGridSize = 200;
        iTrials = 1600;
        System.out.printf("n = %8d T = %8d\n", iGridSize, iTrials);
        oTimer = new Stopwatch();
        oPercStats = new PercolationStats(iGridSize, iTrials);
        System.out.println("Time: " + oTimer.elapsedTime() + "\n");
    }
}