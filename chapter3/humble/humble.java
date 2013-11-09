/*
ID: imdoing1
LANG: JAVA
PROG: humble
*/
import java.util.*;
import java.io.*;
import java.lang.Math;

class humble {
	static int k;
	static int n;
	static int[] primes;
	static int[] index; // keep track of the index of humbles for each primes
	static long[] humbles;
	public static void main (String args[]) {
		Scanner scan = null;    
        try {
            scan = new Scanner(new BufferedReader(new FileReader("humble.in")));
        } catch (Exception e) {}
        PrintWriter out = null;
        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter("humble.out")));
        } catch (Exception ee) {}
        k = scan.nextInt();
        n = scan.nextInt();
        primes = new int[k];
        index = new int[k];
        for (int i = 0; i < k; i ++) {
        	primes[i] = scan.nextInt();
        	index[i] = 0; 
        }
        humbles = new long[n+1];
        humbles[0] = 1;
        dp(); 
        out.println(humbles[n]);
        out.close();
	}	

	public static void dp () {
		for (int i = 1; i <= n; i ++) {  // every ith humble
			humbles[i] = 99999999999L;
			for (int j = 0; j < k; j ++) {  // every primes
				while (humbles[index[j]] * primes[j] <= humbles[i-1]) {
					index[j] ++;
				}
				humbles[i] = Math.min(humbles[i], humbles[index[j]] * primes[j]);
			}
		}
	}
}