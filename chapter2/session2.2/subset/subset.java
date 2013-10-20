/*
ID: imdoing1
LANG: JAVA
PROG: subset
*/
import java.util.*;
import java.io.*;

class subset {
	static int n;
	static int half_sum;
	static long count;
	static long[][] bb = new long[40][391]; // 39 * 390(half_sum)
	public static void main(String args[]) {
		for (long[] i : bb)
			Arrays.fill(i, -1);
		PrintWriter out = null;
		Scanner scan = null;	
		try {
			scan = new Scanner(new BufferedReader(new FileReader("subset.in")));
		} catch (Exception e) {}
	   	try {
	   		out = new PrintWriter(new BufferedWriter(new FileWriter("subset.out")));
	  	} catch (Exception ee) {};
	  	n = scan.nextInt();
	  	int sum = 0;
	  	for (int i = 1; i <= n; i ++) 
	  		sum += i;
	  	if (sum % 2 == 0) {
	  		half_sum = sum / 2;
	  		count = dp(n, half_sum);
	  		out.println(count/2);
	  	}
	  	else
	  		out.println(0);
	  	out.close();
	}

	public static long dp(int x, int y) {
		// base case
		if (y == 0)
			return 1;
		if (y < 0)
			return 0;
		if (x == 0)
			return 0;
		if (bb[x][y] >= 0)
			return bb[x][y];
		// real stuff
		bb[x][y] = 0;
		for (int i = 1; i <= x; i ++) {
			bb[x][y] += dp(i-1, y-i);
		}
		return bb[x][y];
	}
}