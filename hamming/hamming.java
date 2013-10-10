/*
ID: imdoing1
LANG: JAVA
PROG: hamming
*/
import java.util.*;
import java.io.*;

class hamming {
	static int[] result;
	static int n;
	static int b;
	static int d;
	static int end;
	static int cnt = 0;
	public static void main(String args[]) {
		result = new int[255];
		Scanner scan = null;	
		try {
			scan = new Scanner(new BufferedReader(new FileReader("hamming.in")));
		} catch (Exception e) {}
      	n = scan.nextInt();
      	b = scan.nextInt();
      	d = scan.nextInt();
      	// start from 0
      	end = 1 << b;
      	find();
      	print();
	}

	public static boolean fullFill(int dec) {
		for (int i = 0; i <= cnt; i ++) {
			int num = 0;
			int xor = result[i] ^ dec;
			String tmp = Integer.toBinaryString(xor);
			//System.out.println(tmp + "======");
			for (int j = 0; j < tmp.length(); j ++) {
				if (tmp.charAt(j) == '1')
					num ++;
			}
			if (num < d)
				return false;
		}
		return true;
	}

	public static void find() {
		result[cnt] = 0;
		for (int i = 0; i < end && cnt < n; i ++) {
			if (fullFill(i))
				result[++cnt] = i;
		}
	}	

	public static void print() {
		PrintWriter out = null;
       	try {
       		out = new PrintWriter(new BufferedWriter(new FileWriter("hamming.out")));
      	} catch (Exception ee) {};
		int i = 0;
		//System.out.println(cnt);
		String str = "";
		while (i < n) {
			if (i%10 < 9) 
				str += result[i] + " ";
			if (i%10 == 9)
				str += result[i] + "\n";
			i ++;
		}
		out.println(str.substring(0, str.length()-1));
		out.close();
	}
}