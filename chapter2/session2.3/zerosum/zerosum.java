/*
ID: imdoing1
LANG: JAVA
TASK: zerosum
 */
import java.io.*;
import java.util.*;

class zerosum {
	static int n;
	static char[] operator = new char[10]; /* 1-9 */
	static PrintWriter out;
	public static void main (String args[]) {
		Scanner scan = null;        
        try {
            scan = new Scanner(new BufferedReader(new FileReader("zerosum.in")));
        } catch (Exception e) {}
        out = null;
        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter("zerosum.out")));
        } catch (Exception ee) {};
        n = scan.nextInt();
        dfs(1);
        out.close();
	}

	public static void dfs (int index) {
		if (index == n) {
			if (check()) {
				for (int i = 1; i < n; i ++) {
					out.print(i);
					out.print(operator[i]);
				}
				out.println(n);
			}
		}
		else {
			operator[index] = ' ';
			dfs(index + 1);

			operator[index] = '+';
			dfs(index + 1);

			operator[index] = '-';			
			dfs(index + 1);
		}
	}

	public static boolean check () {
		int result = 0;
		char prev_op = '+';
		int curr = 1;
		for (int i = 1; i < n; i ++) {
			if (operator[i] == ' ') {
				curr = 10 * curr + i + 1;
			}
			if (operator[i] == '+') {
				if (prev_op == '+') {
					result += curr;
				}
				if (prev_op == '-') {
					result -= curr;
				}
				prev_op = operator[i];
				curr = i + 1;
			}
			if (operator[i] == '-') {
				if (prev_op == '+') {
					result += curr;
				}
				if (prev_op == '-') {
					result -= curr;
				}
				prev_op = operator[i];
				curr = i + 1;
			}
			//System.out.println(curr);
		}
		if (prev_op == '+') 
			result += curr;
		if (prev_op == '-')
			result -= curr;

		if (result == 0)
			return true;
		else 
			return false;
	}
}