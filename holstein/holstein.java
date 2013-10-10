/*
ID: imdoing1
LANG: JAVA
PROG: holstein
*/
import java.util.*;
import java.io.*;

class holstein {
	static LinkedList<int[]> curr_queue = new LinkedList<int[]>();
	static LinkedList<int[]> result_queue = new LinkedList<int[]>();
	static int vita;
	static int[] vitamins;
	static int feed;
	static int[][] scoop;
	public static void main(String args[]) {
		Scanner scan = null;	
		try {
			scan = new Scanner(new BufferedReader(new FileReader("holstein.in")));
		} catch (Exception e) {}
		vita = scan.nextInt();
		vitamins = new int[vita+1];  // corresponds to curr
		for (int i = 0; i < vita; i ++)
			vitamins[i] = scan.nextInt();
		feed = scan.nextInt();
		scoop = new int[feed][vita];
		for (int i = 0; i < feed; i ++)
			for (int j = 0; j < vita; j ++)
				scoop[i][j] = scan.nextInt();
		bfs();
		PrintWriter out = null;
       	try {
       		out = new PrintWriter(new BufferedWriter(new FileWriter("holstein.out")));
      	} catch (Exception ee) {};
      	int[] opt = result_queue.poll();
      	String rs = "" + opt[feed];
      	for (int k = 0; k < opt[feed]; k ++) {
      		int tmp = opt[k]+1;
      		rs += " " + tmp;
      	}
      	out.println(rs);
      	out.close();
	}

	public static void bfs() {
		curr_queue.add(vitamins);
		result_queue.add(new int[feed+1]);
		while (!curr_queue.isEmpty()) {
			int[] state = curr_queue.poll();    // return & remove head
			if (fullFill(state))
				return;
			int[] trace = result_queue.poll(); 
 			int next_scoop = state[vita];  // initially as 0
 			int cnt = trace[feed];
 			for (int i = next_scoop; i < feed; i ++) {
 				int[] curr = state.clone();
 				int[] tmp = trace.clone();
 				update(curr, scoop[i]);
 				curr[vita] = i+1;
 				curr_queue.add(curr);
 				tmp[cnt] = i;
 				tmp[feed] ++;
 				result_queue.add(tmp);
 			}
		}
	}

	public static void update(int[] a, int[] b) {
		for (int i = 0; i < b.length; i ++) {
			a[i] -= b[i];
		}
	}

	public static boolean fullFill(int[] a) {
		for (int i = 0; i < a.length-1; i ++) {
			if (a[i] > 0)
				return false;
		}
		return true;
	}
}