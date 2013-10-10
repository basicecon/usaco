/*
ID: imdoing1
LANG: JAVA
PROG: barn1
*/
import java.util.*;
import java.io.*;

class barn1 {
	static int gate[];
	static int dist[];
	public static void main (String args[]) {
		Scanner scan = null;
        try {
            scan = new Scanner(new BufferedReader(new FileReader("barn1.in")));
        } catch (Exception e) {}
        PrintWriter out = null;
        try {
        	out = new PrintWriter(new BufferedWriter(new FileWriter("barn1.out")));
        } catch (Exception ee) {};
        int board = scan.nextInt();
        int tt_gate = scan.nextInt();
        int cow_gate = scan.nextInt();
        if (board > cow_gate) {
        	out.println(cow_gate);
        	out.close();
        	return;
        }
        gate = new int[cow_gate];
        for (int i = 0; i < cow_gate; i ++) {
        	int tmp = scan.nextInt();
        	gate[i] = tmp;
        }
        quicksort_gates(0, cow_gate-1);
        dist = new int[cow_gate - 1]; 
        for (int i = 1; i < cow_gate; i ++) {
        	int tmp = gate[i] - gate[i-1];
        	dist[i-1] = tmp; 
        }
        quicksort(0, cow_gate - 2);
        int space = 0;
        int count = 0;
        for (int i = cow_gate-2; i >= 0; i --) {
        	if (count < board-1) {
        		space += dist[i];
        		count ++;
        	}
        }
        int least = gate[cow_gate-1] - gate[0] - space + board;
        out.println(least);
        out.close();
	}

	public static void quicksort (int start, int end) {
		int left = start;
		int right = end;
		int mid = dist[start];
		while (left < right) {
			while (dist[left] < mid)
				left ++;
			while (dist[right] > mid)
				right --;
			if (left <= right) {
				int tmp = dist[left];
				dist[left] = dist[right];
				dist[right] = tmp;
				left ++;
				right --;
			}
		}
		if (start < right)
			quicksort(start, right);
		if (left < end)
			quicksort(left, end);
	}
	public static void quicksort_gates (int start, int end) {
		int left = start;
		int right = end;
		int mid = gate[start];
		while (left < right) {
			while (gate[left] < mid)
				left ++;
			while (gate[right] > mid)
				right --;
			if (left <= right) {
				int tmp = gate[left];
				gate[left] = gate[right];
				gate[right] = tmp;
				left ++;
				right --;
			}
		}
		if (start < right)
			quicksort_gates(start, right);
		if (left < end)
			quicksort_gates(left, end);
	}
}