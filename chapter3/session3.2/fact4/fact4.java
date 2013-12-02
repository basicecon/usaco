/*
ID: imdoing1
LANG: JAVA
PROG: fact4
*/
import java.util.*;
import java.io.*;

class fact4 {
	public static void main(String args[]) {
		Scanner scan = null;
		PrintWriter out = null;
		try {
			scan = new Scanner(new BufferedReader(new FileReader("fact4.in")));
			out = new PrintWriter(new BufferedWriter(new FileWriter("fact4.out")));
		} catch(Exception e) {}
		int n = scan.nextInt();
		int twos = 0;
		int fives = 0;
		int rem = 1;

		for (int i = 1; i <= n; i ++) {
			int tmp = i;
			while (tmp % 2 == 0) {
				tmp /= 2;
				twos ++;
			}
			while (tmp % 5 == 0) {
				tmp /= 5;
				fives ++;
			}
			rem = (rem * tmp) % 10;
		}
		if (twos == fives) {
			out.println(rem);
		}
		else if (twos > fives) {
			for (int i = 1; i <= twos - fives; i ++) {
				rem = (rem * 2) % 10;
			}
			out.println(rem);	
		}
		else {
			for (int i = 1; i <= fives - twos; i ++) {
				rem = (rem * 5) % 10;
			}
			out.println(rem);
		}
		out.close();
	}
}