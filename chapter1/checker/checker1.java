/*
ID: imdoing1
LANG: JAVA
PROG: checker
*/
import java.util.*;
import java.io.*;

class checker1 {
	static int n;
	static ArrayList<int[]> solution = new ArrayList<int[]>();
	static int[] first_three;
	static int[] pos;
	static int[] pos_reverse;
	public static void main(String args[]) {
		Scanner scan = null;	
		try {
			scan = new Scanner(new BufferedReader(new FileReader("checker.in")));
		} catch (Exception e) {}
		PrintWriter out = null;
       	try {
       		out = new PrintWriter(new BufferedWriter(new FileWriter("checker.out")));
      	} catch (Exception ee) {};
		n = scan.nextInt();
		pos = new int[n];
		pos_reverse = new int[n];
		first_three = new int[3];
		for (int i = 0; i < n; i ++) {
			pos[i] = 0;
			pos_reverse[i] = 0;
		}
		placeQueen(0);
		sort(0, solution.size()-1);
		for (int i = 0; i < 3; i ++) {
			//System.out.println(Arrays.toString(solution.get(i)));
			for (int j = 0; j < n; j ++) {
				if (j < n-1)
					out.print(solution.get(i)[j] + " ");	
				if (j == n-1)
					out.print(solution.get(i)[j]);
			}
			out.println();
		}
		out.println(solution.size());
		out.close();
	}

	public static void placeQueen(int col_index) { //dfs
		if (col_index == n) {
			solution.add(pos_reverse.clone());
			return;
		}
		for (int row_index = 0; row_index < n; row_index ++) {
			if (canPlaceQueen(row_index, col_index)) {
				//System.out.println(Arrays.toString(pos));
				//System.out.println(row_index + " " + col_index);
				pos[col_index] = row_index;
				pos_reverse[row_index] = col_index + 1;
				placeQueen(col_index+1);
				pos[col_index] = -1;
				pos_reverse[row_index] = -1;
			}
		}
	}

	public static boolean canPlaceQueen(int row_index, int col_index) {
		for (int i = 0; i < col_index; i ++) {
			if (row_index == pos[i]) {
				return false;
			}
			if (row_index-pos[i] != 0) {
				if (((double)(col_index-i)/(row_index-pos[i]) > 0.99999 &&
					(double)(col_index-i)/(row_index-pos[i]) < 1.00001) || 
					((double)(col_index-i)/(row_index-pos[i]) + 1 > -0.0001 &&
					(double)(col_index-i)/(row_index-pos[i]) + 1 < 0.0001)) {
					return false;
				} 
			}
		}
		return true;
	}

	public static int compare(int[] arr1, int[] arr2) {
		int i = 0;
		while (i < n && arr1[i] == arr2[i]) {
			i ++;
		}
		if (i < n) {
			if (arr1[i] < arr2[i]) 
				return 1;
			else 
				return -1;
		}
		else 
			return 0;
	}

	public static void sort(int start, int end) {
		int left = start;
		int right = end;
		int[] mid = solution.get(start).clone();
		while (left <= right) {
			while (compare(solution.get(left), mid) == 1)
				left ++;
			while (compare(solution.get(right), mid) == -1)
				right --;
			if (left <= right) {
				Collections.swap(solution, left, right);
				left ++;
				right --;
			}
		}
		if (start < right)
			sort(start, right);
		if (left < end)
			sort(left, end);
	}
}