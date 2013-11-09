/*
ID: imdoing1
LANG: JAVA
PROG: agrinet
*/
import java.util.*;
import java.io.*;

class agrinet {
	static int n;
	static int[][] dist;
	static int result = 0;
	static ArrayList<Integer> in_tree = new ArrayList<Integer>();
	static ArrayList<Integer> out_tree = new ArrayList<Integer>();
	public static void main (String args[]) {
		Scanner scan = null;    
        try {
            scan = new Scanner(new BufferedReader(new FileReader("agrinet.in")));
        } catch (Exception e) {}
        PrintWriter out = null;
        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter("agrinet.out")));
        } catch (Exception ee) {};
        n = scan.nextInt();
        dist = new int[n][n];
        for (int i = 0; i < n; i ++) {
        	for (int j = 0; j < n; j ++) {
        		dist[i][j] = 0;
        		dist[j][i] = 0;
        	}
        }
        for (int i = 0; i < n; i ++) {
        	out_tree.add(i);
        	for (int j = 0;j < n; j ++) {	
        		dist[i][j] = scan.nextInt();
        		dist[j][i] = dist[i][j];
        	}
        }
        in_tree.add(0);
        // remove object
        out_tree.remove(new Integer(0));
        prim();
        out.println(result);
        out.close();
	}

	public static void prim () {
		for (int i = 1; i < n; i ++) {
			int next_node = -1;
			int min = 999999;
			for (int j = 0; j < in_tree.size(); j ++) {
				for (int k = 0; k < out_tree.size(); k ++) {
					if (dist[in_tree.get(j)][out_tree.get(k)] != 0) {
						if (dist[in_tree.get(j)][out_tree.get(k)] < min) {
							min = dist[in_tree.get(j)][out_tree.get(k)];
							next_node = out_tree.get(k);						
						}
					}
				}
			}
			in_tree.add(next_node);
			out_tree.remove(new Integer(next_node));
			result += min;
		}
	}
}