/*
ID: imdoing1
LANG: JAVA
PROG: crypt1
*/
import java.util.*;
import java.io.*;

class crypt1 {
	static int digit[];
	static int n;
	public static void main (String args[]) {
		int count = 0;
		Scanner scan = null;	
		try {
            scan = new Scanner(new BufferedReader(new FileReader("crypt1.in")));
        } catch (Exception e) {}
        n = scan.nextInt();
        digit = new int[n];
        for (int i = 0; i < n; i ++)
        	digit[i] = scan.nextInt();
        count = primeCrypt();
        PrintWriter out = null;
       	try {
       		out = new PrintWriter(new BufferedWriter(new FileWriter("crypt1.out")));
      	} catch (Exception ee) {};
       	out.println(count);
       	out.close();
	}
	public static boolean rightCombo (int subject) {
		int count = 0;
		if (subject >= 111 && subject <= 999) {
			//three digits
			while (subject > 0) {
				int tmp = subject % 10;
				subject /= 10;
				for (int i = 0; i < n; i ++) {
					if (tmp == digit[i]) {
						count ++;
					}
				}
			}
			if (count == 3)
				return true;
			else
				return false; 
		}
		else if (subject >= 11 && subject <= 99) {
			//two digits
			while (subject > 0) {
				int tmp = subject % 10;
				subject /= 10;
				for (int i = 0; i < n; i ++) {
					if (tmp == digit[i]) {
						count ++;
					}
				}
			}
			if (count == 2) 
				return true;
			else 
				return false;
		}	
		else 
			return false;
	}

	public static boolean rightCombo_add (int subject) {
		int count = 0;
		if (subject >= 1111 && subject <= 9999) {
			while (subject > 0) {
				int tmp = subject % 10;
				subject /= 10;
				for (int i = 0; i < n; i ++) {
					if (tmp == digit[i]) {
						count ++;
					}
				}
			}
			if (count == 4)
				return true;
			else
				return false;
		}
		else 
			return false;
	} 

	public static int primeCrypt () {
		int count = 0;
		int first_par = 0;
		int second_par = 0;
		int addition = 0;
		for (int i = 111; i <= 999; i ++) {
			for (int j = 11; j <= 99; j ++) {
				if (rightCombo(i) && rightCombo(j)) {
					first_par = i * (j % 10);
					second_par = i * (j / 10);
					if (rightCombo(first_par) && rightCombo(second_par)) {
						addition = i * j;
						if (rightCombo_add(addition)) 
							count ++;
					}
				}
			}
		}
		return count;
	}
}