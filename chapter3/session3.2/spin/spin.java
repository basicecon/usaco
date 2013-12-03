/*
ID: imdoing1
LANG: JAVA
PROG: spin
*/
import java.util.*;
import java.io.*;

class spin {
	static int MAX = 102;
	static int wheels = 0;
	static int[] speed = new int[MAX];
	static int[] wedges = new int[MAX];
	static int[][] start = new int[MAX][MAX];
	static int[][] extent = new int[MAX][MAX];
	static int[][] end = new int[MAX][MAX];
	static PrintWriter out;

	public static void main(String args[]) {
		Scanner scan = null;
		out = null;
		try {
			scan = new Scanner(new BufferedReader(new FileReader("spin.in")));
			out = new PrintWriter(new BufferedWriter(new FileWriter("spin.out")));
		} catch(Exception e) {}

		while (scan.hasNext()) {
			speed[wheels] = scan.nextInt();
			wedges[wheels] = scan.nextInt();
			for (int i = 0; i < wedges[wheels]; i ++) {
				start[wheels][i] = scan.nextInt();
				extent[wheels][i] = scan.nextInt();
				end[wheels][i] = start[wheels][i] + extent[wheels][i];
				if (end[wheels][i] >= 360)
					end[wheels][i] -= 360;
			}
			wheels ++;
		}
		update();
	}

	public static boolean check() {
		boolean flag;
		int cnt = 0;

		for (int i = 0; i < 360; i ++) {
			cnt = 0;
			for (int j = 0; j < wheels; j ++) {
				flag = false;
				for (int k = 0; k < wedges[j]; k ++) {
					//System.out.println("i = " + i + " start = " + start[j][k] + " end = " + end[j][k]);
					if ((i >= start[j][k] && i <= end[j][k] && start[j][k] <= end[j][k]) 
						|| ((i >= start[j][k] || i <= end[j][k]) && end[j][k] < start[j][k])) {
						flag = true;
						break;
					}
				}
				if (flag)
					cnt ++;
			}

			if (cnt == wheels)
				return true;
		}
		return false;
	}

	public static void update() {
		for (int i = 0; i < 360; i ++) {
			if (check()) {
				output(i);
			}
			for (int j = 0; j < wheels; j ++) {
				for (int k = 0; k < wedges[j]; k ++) {
					start[j][k] += speed[j];
					if (start[j][k] >= 360)
						start[j][k] -= 360;
					end[j][k] += speed[j];
					if (end[j][k] >= 360)
						end[j][k] -= 360;
				}
			}
		}
		output(-1);
	}

	public static void output(int angle) {
		if (angle == -1) {
			out.println("none");
		}
		else {
			out.println(angle);
		}
		out.close();
	}

}