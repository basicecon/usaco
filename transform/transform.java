/*
ID: imdoing1
LANG: JAVA
PROG: transform
*/

import java.util.*;
import java.lang.*;
import java.io.*;

class transform {
	public static void main(String args[]) {
		Scanner scan = null;
        try {
            scan = new Scanner(new BufferedReader(new FileReader("transform.in")));
        } catch (Exception e) {}
        PrintWriter out = null;
        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter("transform.out")));
        } catch (Exception ee) {};
        int n = scan.nextInt();
		char[][] pattern = new char[n][n];
		for (int i = 0; i < n; i ++) {
			String temp = scan.next();
			for (int j = 0; j < n; j ++)
				pattern[i][j] = temp.charAt(j);
		}
		char[][] new_pattern = new char[n][n];
		for (int i = 0; i < n; i ++) {
			String temp = scan.next();
			for (int j = 0; j < n; j ++) 
				new_pattern[i][j] = temp.charAt(j);
		}
		/*    
		for (int i = 0; i < n; i ++) {
			for (int j = 0; j < n; j ++)
				System.out.print(new_pattern[i][j] + " ");
			System.out.println();
		}*/

		if (isEqual(new_pattern, rotate(pattern)))
			out.println(1);
		else if (isEqual(new_pattern, rotate(rotate(pattern))))
			out.println(2);
		else if (isEqual(new_pattern, rotate(rotate(rotate(pattern)))))
			out.println(3);
		else if (isEqual(new_pattern, reflect(pattern)))
			out.println(4);
		else if (isEqual(new_pattern, rotate(reflect(pattern))))
			out.println(5);
		else if (isEqual(new_pattern, rotate(rotate(reflect(pattern)))))
			out.println(5);
		else if (isEqual(new_pattern, rotate(rotate(rotate(reflect(pattern))))))
			out.println(5);
		else if (isEqual(new_pattern, pattern))
			out.println(6);
		else 
			out.println(7);
		/*
		char[][] test = reflect(pattern);
		for (int i = 0; i < n; i ++) {
			for (int j = 0; j < n; j ++)
				System.out.print(test[i][j] + " ");
			System.out.println();
		}
		System.out.println(isEqual(new_pattern, rotate(pattern)));
		*/
		out.close();

	}

	private static char[][] rotate(char[][] square) {
		int n = square.length;
		char[][] new_square = new char[n][n];
		for (int i = 0; i < n; i ++) {
			for (int j = 0; j < n; j ++) {
				new_square[i][j] = square[j][n-1-i];
			}
		}
		return new_square;
	}

	private static char[][] reflect(char[][] square) {
		int n = square.length;
		char[][] new_square = new char[n][n];
		for (int i = 0; i < n; i ++) {
			int start = 0;
			int end = n - 1;
			while (start <= end) {
				new_square[i][start] = square[i][end];
				new_square[i][end] = square[i][start];
				start ++;
				end --;
			}
		}
		return new_square;
	}

	private static boolean isEqual(char[][] square, char[][] new_square) {
		int n = square.length;
		for (int i = 0; i < n; i ++) {
			for (int j = 0; j < n; j ++) {
				if (square[i][j] != new_square[i][j]) {
					return false;
				}
			}
		}
		return true;
	}
}