/*
ID: imdoing1
LANG: JAVA
TASK: nocows
 */
import java.io.*;
import java.util.*;

class nocows {
	static int nn;
	static int kk;
	public static void main (String args[]) {
		Scanner scan = null;        
        try {
            scan = new Scanner(new BufferedReader(new FileReader("nocows.in")));
        } catch (Exception e) {}
        PrintWriter out = null;
        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter("nocows.out")));
        } catch (Exception ee) {};
        nn = scan.nextInt();
        kk = scan.nextInt();
        int result = dp(nn, kk);
        out.println(result);
        out.close();
 	}

	// bottom-up dp
	public static int dp (int n, int k) {
		int[][] table = new int[n+1][k+1];
		table[1][1] = 1;
		/*
		for (int i = 0; i <= k; i ++) {
			table[0][i] = 1; 
		}*/
		for (int i = 3; i <= n; i += 2) {
			for (int j = 2; j <= k; j++) {
				int sum = 0;

				for (int left_num = 1; left_num < i - 1; left_num += 2) {
					for (int height = 1; height < j - 1; height ++) {
						sum += 2 * table[left_num][j - 1] * table[i - left_num - 1][height] % 9901;
					}
					sum += table[left_num][j - 1] * table[i - left_num - 1][j - 1] % 9901;
				}

				table[i][j] = sum % 9901;
			}
		}
		/*
		for (int i = 1; i <= n; i ++) {
			for (int j = 1; j <= k; j ++) {
				System.out.print(table[i][j] + " ");
			} 
			System.out.println();
		}
		*/
		return table[n][k];
	}
}