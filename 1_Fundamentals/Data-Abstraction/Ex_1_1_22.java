package z_1;

import java.io.File;
import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;


public class Ex_1_1_22 {

    private Ex_1_1_22() { }

    public static int indexOf(int[] a, int key) {
        int lo = 0;
        int hi = a.length - 1;
        int indent = 0;
        while (lo <= hi) {
            // Key is in a[lo..hi] or not present.
        	StdOut.printf("%s%d%s%d\n",repeat(4*indent++, ' '), lo,"-", hi);
        	
            int mid = lo + (hi - lo) / 2;
            if      (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else return mid;
        }
        return -1;
    }

    private static String repeat(int n, char c)
    {
        String s = "";
        for (int i = 0; i < n; i++)
            s += c;
        return s;
    }
    
    @Deprecated
    public static int rank(int key, int[] a) {
        return indexOf(a, key);
    }

    public static void main(String[] args) {

        // read the integers from a file
    	 String pathname ="D:\\JAVA_WORK\\suanfa4\\src\\z_1\\largeW.txt"; 
         File filename = new File(pathname); 
        In in = new In(filename);
        int[] whitelist = in.readAllInts();

        // sort the array
        Arrays.sort(whitelist);

        // read integer key from standard input; print if not in whitelist
        while (!StdIn.isEmpty()) {
            int key = StdIn.readInt();
            if (Ex_1_1_22.indexOf(whitelist, key) == -1)
                StdOut.println(key);
        }
    }
}
