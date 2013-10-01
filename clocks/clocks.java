/*
ID: imdoing1
LANG: JAVA
PROG: clocks
*/
import java.util.*;
import java.io.*;

class clocks {
	static int[] clock;
	static int[][] move;
	static int[] min_path;
	static int path_step = Integer.MAX_VALUE;

	public static void main(String args[]) {
		read();
		int[] move_path = new int[9];
		dfs(0, move_path);
		out();
	}

	public static void read() {
		Scanner scan = null;	
		try {
            scan = new Scanner(new BufferedReader(new FileReader("clocks.in")));
        } catch (Exception e) {}
        clock = new int[9];
        while (scan.hasNext()) {
        	for (int i = 0; i < 9; i ++) 
        		clock[i] = scan.nextInt();	
        }
        move = new int[][]{{'A', 'B', 'D', 'E'}, {'A', 'B', 'C'}, 
        {'B', 'C', 'E', 'F'}, {'A', 'D', 'G'}, 
        {'B', 'D', 'E', 'F', 'H'}, {'C', 'F', 'I'}, 
        {'D', 'E', 'G', 'H'}, {'G', 'H', 'I'},
        {'E', 'F', 'H', 'I'}};
        for (int i = 0; i < move.length; i ++)
        	for (int j = 0; j < move[i].length; j ++)
        		move[i][j] -= 'A';
	}

	public static void out() {
		PrintWriter out = null;
       	try {
       		out = new PrintWriter(new BufferedWriter(new FileWriter("clocks.out")));
      	} catch (Exception ee) {};
      	String result = new String();
      	for (int i = 0; i < min_path.length; i ++) {
      		int times = min_path[i];
      		for (int j = 0; j < times; j ++) {
      			result += i+1 + " ";
      		}
      	}
      	result = result.substring(0, result.length()-1);
      	out.println(result);
      	out.close();
	}

	public static void dfs(int index, int[] move_path) {
		if (index == move_path.length) {
			if (isTwelve()) {
				int step = 0;
				for (int cnt : move_path)
					step += cnt;
				if (step < path_step) {
					path_step = step;
					min_path = move_path.clone();
					//?
				}
			}
		}
		else {
			for (int rotation = 3; rotation >= 0; rotation --) {
				for (int n = 0; n < rotation; n ++)
					moveClocks(move[index]);
				move_path[index] = rotation;
				dfs(index + 1, move_path);
				for (int n = 0; n < rotation; n ++)
					moveClocksBack(move[index]); 
			}
		}
	}

	public static void moveClocks(int[] target) {
		for (int i : target)
			clock[i] = clockWise(clock[i]);
	}

	public static void moveClocksBack(int[] target) {
		for (int i : target)
			clock[i] = antiClockWise(clock[i]);	
	}

	public static int clockWise(int time) {
		return (time+3 == 15) ? 3 : time+3;
	}

	public static int antiClockWise(int time) {
		return (time-3 == 0) ? 12 : time-3;
	}

	public static boolean isTwelve() {
		for (int i : clock) {
			if (i != 12)
				return false;
		}
		return true;
	}

}