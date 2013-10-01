/*
ID: imdoing1
LANG: JAVA
PROG: palsquare
*/
import java.util.*;
import java.io.*;

class palsquare {
	public static void main (String args[]) {
		Scanner scan = null;
        try {
            scan = new Scanner(new BufferedReader(new FileReader("palsquare.in")));
        } catch (Exception e) {}
        int base = scan.nextInt();
        int square = 0;
        ArrayList<String> alPalindrome = new ArrayList<String>();
        ArrayList<String> sqrt = new ArrayList<String>();

		for (int i = 1; i <= 300; i ++) {
			square = i * i;
			String tmp = Integer.toString(i, base).toUpperCase();
			String tmp_square = Integer.toString(square, base).toUpperCase();
			if (isPalindrome(tmp_square)) {
				sqrt.add(tmp);
				alPalindrome.add(tmp_square);
			}
		}
		String op = "";
		for (int i = 0; i < sqrt.size(); i ++) {
			op += sqrt.get(i) + " " + alPalindrome.get(i) + "\n";
			//System.out.println(op);
		}
		PrintWriter out = null;
        try {
        	out = new PrintWriter(new BufferedWriter(new FileWriter("palsquare.out")));
        } catch (Exception ee) {};
        out.print(op);
        out.close();
	}

	public static boolean isPalindrome (String fw_str) {
		String bw_str = swap(fw_str);
		return fw_str.equals(bw_str);
	}

	public static String swap (String str) {
		int n = str.length();
		int left = 0;
		int right = n-1;
		char digit[] = new char[n];
		for (int i = 0; i < n; i ++) 
			digit[i] = str.charAt(i);
		while (left < right) {
			char temp = digit[left];
			digit[left] = digit[right];
			digit[right] = temp;
			left ++;
			right --;
		}
		String bw = "";
		for (int i = 0; i < n; i ++) 
			bw += digit[i];
		return bw;
	}
}