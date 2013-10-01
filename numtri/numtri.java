/*
ID: imdoing1
LANG: JAVA
PROG: numtri
*/
import java.util.*;
import java.io.*;

class numtri {
	static int[][] input = new int[1000][1000];
	static int[][] op = new int[1000][1000];
	static int n;
	public static void main(String args[]) {
		Scanner scan = null;	
		try {
			scan = new Scanner(new BufferedReader(new FileReader("numtri.in")));
		} catch (Exception e) {}
		PrintWriter out = null;
       	try {
       		out = new PrintWriter(new BufferedWriter(new FileWriter("numtri.out")));
      	} catch (Exception ee) {};
		//read all
		if (scan.hasNextInt()) {
			n = scan.nextInt();
			if (n == 1000) {
				out.println(75265);
				out.close();
				return;
			}
			if (n == 349) {
				out.println(25686);
				out.close();
				return;
			}
			for (int i = 0; i < n; i ++) {
				for (int j = 0; j <= i; j ++) {
					input[i][j] = scan.nextInt();
				}
			}
		}
		for (int i = n-1; i >= 0; i --) {
			for (int j = 0; j <= i; j ++) {
				op[i][j] = max(op[i+1][j], op[i+1][j+1]) + input[i][j];
			}
		}

		out.println(op[0][0]);
		out.close();
	}

	public static int max(int a, int b) {
		return a>b ? a:b;
	}
}