/*
ID: imdoing1
LANG: JAVA
PROG: inflate
*/
import java.util.*;
import java.io.*;
import java.lang.Math;

class inflate {
	static int m;
	static int n;
	static int[] time;
	static int[] score;
	static int[] result;
	public static void main (String args[]) {
		Scanner scan = null;    
        try {
            scan = new Scanner(new BufferedReader(new FileReader("inflate.in")));
        } catch (Exception e) {}
        PrintWriter out = null;
        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter("inflate.out")));
        } catch (Exception ee) {}
        m = scan.nextInt();
        n = scan.nextInt();
        score = new int[n];
        time = new int[n];
        for (int i = 0; i < n; i ++) {
        	score[i] = scan.nextInt();
        	time[i] = scan.nextInt();
        }
        result = new int[m+1];
        result[0] = 0;
        dp();
        out.println(result[m]);
        out.close();
	}

	public static void dp () {
		/*
		for (int i = 0; i <= m; i ++) {
			for (int j = 0; j <= n; j ++) {
				if (i >= time[j]) {
					result[i] = Math.max(result[i], result[i-time[j]] + score[j]);
				}
			}
		} */
		for (int i = 0; i < n; i ++) {
			for (int j = 0; j <= m; j ++) {	
				if (j >= time[i]) {
					//System.out.println(j + " " + result[j]);
					result[j] = Math.max(result[j], result[j-time[i]] + score[i]);
				}
			}
		}
	}
}