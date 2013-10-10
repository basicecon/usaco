/*
ID: imdoing1
LANG: JAVA
PROG: ariprog
*/
import java.util.*;
import java.io.*;

class ariprog {
	static int n;
	static int m;
	static ArrayList<array> result = new ArrayList<array>();
	static int[] isDS;

	static class array {
		int para[] = new int[2];
		public array(int a, int b) {
			para[0] = a;
			para[1] = b;
		}
	}	

	public static void main(String args[]) {
		isDS = new int[130000];
		for (int k = 0; k < 130000; k ++)
			//havent check yet
			isDS[k] = -1;
		boolean flag = false;
		Scanner scan = null;	
		try {
			scan = new Scanner(new BufferedReader(new FileReader("ariprog.in")));
		} catch (Exception e) {}
		PrintWriter out = null;
       	try {
       		out = new PrintWriter(new BufferedWriter(new FileWriter("ariprog.out")));
      	} catch (Exception ee) {};
		
		n = scan.nextInt();
		m = scan.nextInt();
		for (int b = 1; b <= m*m; b ++) {
			for (int a = 0; a <= m*m; a ++) {
			 	if (a+(n-1)*b > 2*m*m)
			 		break;
				if (sec_check(a, b)) {
					flag = true;
					out.println(a + " " + b);
				}
			}
		}
		if (!flag)
			out.println("NONE");
		out.close();
	}

	public static boolean check(int number) {
		if (isDS[number] == 1)
			return true;
		else if (isDS[number] == 0)
			return false;
		else {
			for (int p = 0; p <= m; p ++) {
				if (p*p > number)
					break;
				int q = (int)Math.sqrt(number - p*p);
				if (number == p*p + q*q && q <= m) {
					//System.out.println("number: " + number + " " + p + " " + q);
					isDS[number] = 1;
					return true;
				}
			}
			isDS[number] = 0;
			return false;
		}
	}	

	public static boolean sec_check(int a, int b) {
		for (int i = 0; i < n; i ++) {
			int tmp = a+i*b;
			if (!check(tmp))
				return false;
		}
		return true;
	}

}