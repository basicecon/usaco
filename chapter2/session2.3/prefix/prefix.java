/*
ID: imdoing1
LANG: JAVA
PROG: prefix
*/
import java.util.*;
import java.io.*;

class prefix {
	static ArrayList<String> subseq = new ArrayList<String>();
	static String seq;
	static int[] bb = new int[200001];
	public static void main(String args[]) {
        
		PrintWriter out = null;
        int y = 200049;
        Scanner scan = null;        
        try {
            scan = new Scanner(new BufferedReader(new FileReader("prefix.in")));
        } catch (Exception e) {}
        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter("prefix.out")));
        } catch (Exception ee) {};

        String tmp;
        int i = 0;
        tmp = scan.next();
        while (!tmp.equals(".")) {
        	subseq.add(tmp);
        	tmp = scan.next();
        }
        seq = scan.next();
        while (scan.hasNext()) {
        	seq += scan.next();            
        }
        Arrays.fill(bb, -1);

        out.println(dp(0));
        out.close();
	}

	public static int dp(int index) {
		// bi
		if (bb[index] >= 0)
			return bb[index];
		if (index >= seq.length())
			return 0;

		bb[index] = 0;

		for (String j : subseq) {
			int cur, cur_length;
			//System.out.println(seq.substring(index) + " " + j);
			if (seq.substring(index).startsWith(j)) {
				cur = j.length();
				cur_length = cur + dp(index + cur);
				if (cur_length > bb[index])
					bb[index] = cur_length;
			}
		}
		return bb[index];
	}
}