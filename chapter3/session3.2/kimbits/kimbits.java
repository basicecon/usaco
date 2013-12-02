/*
ID: imdoing1
LANG: JAVA
PROG: kimbits
*/
import java.util.*;
import java.io.*;

class kimbits {
	static Scanner scan = null;
	static PrintWriter out = null;
	static int nn;
	static int ll;
	static long ii;
	static long[][] mark;

	public static void main(String args[]) {
		try {
			scan = new Scanner(new BufferedReader(new FileReader("kimbits.in")));
			out = new PrintWriter(new BufferedWriter(new FileWriter("kimbits.out")));
		} catch(Exception e) {}
		nn = scan.nextInt();
		ll = scan.nextInt();
		ii = scan.nextLong();
		mark = new long[nn+1][ll+1];
		dp();
		output(nn, ll, ii);
		out.println();
		out.close();
	}

	public static void dp() {
		// initialize, not sure include 0 or not
		for (int i = 1; i <= nn; i ++) {
			mark[i][0] = 1;
		}
		for (int i = 0; i <= ll; i ++) {
			mark[0][i] = 1;
		}
		for (int i = 1; i <= nn; i ++) {
			for (int j = 1; j <= ll; j ++) {
				if (i < j) {
					mark[i][j] = mark[i][i];
				}
			}
		}
		// dp
		for (int i = 1; i <= nn; i ++) {
			for (int j = 1; j <= ll; j ++) {
				if (i >= j) {
					mark[i][j] = mark[i-1][j] + mark[i-1][j-1];
				}
				else {
					mark[i][j] = mark[i][i];
				}
				//System.out.println("mark[" + i + "][" + j + "] = " + mark[i][j] + " ");
			}
			//System.out.println();
		}
	}

	public static void output(int i, int j, long k) {
		if (j > i)
			j = i;

		if (k == mark[i][j]) {
			for (int a = 1; a <= j; a ++) {
				out.print("1");
			}
			for (int a = 1; a <= i-j; a ++) {
				out.print("0");
			}
		}
		else {
			//System.out.println("k = " + k + " mark[i-1][j] = " + mark[i-1][j]);
			if (k <= mark[i-1][j]) {
				out.print("0");
				output(i-1, j, k);
			}
			else {
				out.print("1");
				output(i-1, j-1, k-mark[i-1][j]);
			}
		}
	}
}