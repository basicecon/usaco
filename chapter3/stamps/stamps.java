/*
ID: imdoing1
LANG: JAVA
PROG: stamps
*/
import java.util.*;
import java.io.*;
import java.lang.Math;

class stamps {
	static int k;
	static int n;
	static int[] value;
	static int[] minStamp = new int[6999999];
	public static void main (String args[]) {
		Scanner scan = null;    
        try {
            scan = new Scanner(new BufferedReader(new FileReader("stamps.in")));
        } catch (Exception e) {}
        PrintWriter out = null;
        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter("stamps.out")));
        } catch (Exception ee) {}
        k = scan.nextInt();
        n = scan.nextInt();
        value = new int[n];
        int i = 0;
        while (scan.hasNext()) {
        	value[i] = scan.nextInt();
        	i ++;
        }
        Arrays.fill(minStamp, 99999);
        minStamp[0] = 0;
        for (int p = 0; p < n; p ++)
        	minStamp[value[p]] = 1;
        int result = dp();
        out.println(result - 1);
        out.close();
	}

	public static int dp () {
		int i = 1;
		while (true) {
			for (int j = 0; j < n; j ++) {
				if (i >= value[j])
					minStamp[i] = (int)Math.min(minStamp[i], minStamp[i-value[j]] + 1);
			}
			//System.out.println(i + " needs " + minStamp[i] + " stamps");
			if (minStamp[i] > k) {
				break;
			}
			i ++;
		}
		return i;
	}
}