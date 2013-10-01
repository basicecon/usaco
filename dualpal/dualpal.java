/*
ID: imdoing1
LANG: JAVA
PROG: dualpal
*/
import java.util.*;
import java.io.*;

class dualpal {
	public static void main (String args[]) {
		Scanner scan = null;
        try {
            scan = new Scanner(new BufferedReader(new FileReader("dualpal.in")));
        } catch (Exception e) {}
        int total_lines = scan.nextInt();
        int start = scan.nextInt();
        ArrayList<Integer> result = new ArrayList<Integer>();
        int number = start + 1;
        for (int lines = 1; lines <= total_lines; number ++) {
			int count = 0;
			for (int base = 2; base <= 10; base ++) {
				String tmp = Integer.toString(number, base).toUpperCase();
				//System.out.println(lines + "=====================" + tmp + " " + number);	
				if (isPalindrome(tmp)) 
					count ++;
			}
			if (count >= 2) {
				result.add(number);
				lines ++;
			}
        }
        String op = "";
		for (int i = 0; i < result.size(); i ++) {
			op += result.get(i) + "\n";
			//System.out.println(op);
		}
        PrintWriter out = null;
        try {
        	out = new PrintWriter(new BufferedWriter(new FileWriter("dualpal.out")));
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