/*
ID: imdoing1
LANG: JAVA
PROG: checker
*/
import java.util.*;
import java.io.*;

class checker {
	static int n;
	static int[] used_col;
	static int[] up_diag;
	static int[] down_diag;
	static int count;
	static int[] rows;
	static PrintWriter out;
	public static void main(String args[]) {
		count = 0;
		Scanner scan = null;	
		try {
			scan = new Scanner(new BufferedReader(new FileReader("checker.in")));
		} catch (Exception e) {}
       	try {
       		out = new PrintWriter(new BufferedWriter(new FileWriter("checker.out")));
      	} catch (Exception ee) {};
      	n = scan.nextInt();
      	used_col = new int[n];
      	up_diag = new int[2*n];
      	down_diag = new int[2*n];
      	rows = new int[n]; 
     	for (int i = 0; i < n; i ++)
			used_col[i] = 0;
		for (int i = 0; i < 2*n; i ++) {
			up_diag[i] = 0;
			down_diag[i] = 0;
		}
      	if (n <= 6) {
      		dfs(0);
      		//return;
      	}
      	// >= 7 levels
      	else {
      		for (int col = 0; col < n/2; col ++) {
      			rows[0] = col;
      			mark(0, col);
      			dfs(1);
      			unmark(0, col);
      		}
      		count *= 2;
      		if (n%2 != 0) {  // insert middle column
      			rows[0] = n/2;
      			mark(0, n/2);
      			dfs(1);
      			unmark(0, n/2);
      		}
      	}
      	out.println(count);
      	out.close();
	}

	public static void dfs(int row) {
		if (row == n) {
			count ++;
			if (count <= 3) {
				String tmp = "";
				for (int col_index : rows) {
					tmp += col_index+1 + " ";
				}
				out.println(tmp.substring(0, tmp.length()-1));
			}
		}
		else {
			for (int col = 0; col < n; col ++) {
				if (used_col[col] == 0 && up_diag[row+col] == 0 && down_diag[col-row+n] == 0) {
					rows[row] = col;
					mark(row, col);
					dfs(row+1);
					unmark(row, col);
				}
			}
		}
	}	

	public static void mark(int row, int col) {
		used_col[col] ++;
		up_diag[row+col] ++;
		down_diag[col-row+n] ++;
	}

	public static void unmark(int row, int col) {
		used_col[col] --;
		up_diag[row+col] --;
		down_diag[col-row+n] --;
	}
}