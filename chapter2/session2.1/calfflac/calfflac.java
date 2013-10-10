/*
ID: imdoing1
LANG: JAVA
PROG: calfflac
*/
import java.util.*;
import java.io.*;

class calfflac {
	static String text;
	static String text_upperCase;
	static String max;
	static int maxLen;
	static int maxPos = -1;
	public static void main (String args[]) {
		Scanner scan = null;
		text = "";
		try {
            scan = new Scanner(new BufferedReader(new FileReader("calfflac.in")));
        } catch (Exception e) {}
        while (scan.hasNext())
	        text += scan.nextLine() + "\n";
	    text_upperCase = text.toUpperCase();
	    max = "";
	    maxLen = 0;
	    //String max = isPalindrome();
	    //System.out.println(text);
	    //System.out.println(max);
	    PrintWriter out = null;
       	try {
       		out = new PrintWriter(new BufferedWriter(new FileWriter("calfflac.out")));
      	} catch (Exception ee) {};
      	/*for (int i = 0; i < text.length(); i ++) {
      		isPalindrome(i);
      	}*/
      	split();
      	out.println(count(max));
       	out.println(max);
       	out.close();
	}

	public static void isPalindrome (int i) {
		String tmp = "";
		//int maxLen = 1;
//for 		
		int j = 0;
		int k = 0;	
		int lenPal = 1;
		if (isAlpha(text_upperCase.charAt(i))) {
			j = i - 1;
			k = i + 1;
			while (j >= 0 && k < text_upperCase.length()) {
				if (isAlpha(text.charAt(j)) && isAlpha(text.charAt(k))) {
					if (text_upperCase.charAt(j) == text_upperCase.charAt(k)) {
						tmp = text.substring(j, k+1); 
						j --;
						k ++;
						lenPal += 2;
						continue;
					}
					else
						break;
				}
				if (!isAlpha(text_upperCase.charAt(k))) 
					k ++;
				if (!isAlpha(text_upperCase.charAt(j))) 
					j --;
			}
			if ((lenPal > maxLen) || (lenPal == maxLen && i < maxPos)) {
				max = tmp;
				maxLen = lenPal;
				maxPos = i;
			}

			int right_i = i + 1;
			// find i'
			while (right_i < text.length() && !isAlpha(text.charAt(right_i))) {
					right_i ++;
			}

			// check if ch[i] == ch[i']. if no continue;
			if (right_i < text.length() && 
				(text_upperCase.charAt(i) == text_upperCase.charAt(right_i))) {
				j = i - 1;
				k = right_i + 1;
				lenPal = 2;
				tmp = "";
			} 
			else 
				return;

			// if yes then expand
			while (j >= 0 && k < text_upperCase.length()) {
				if (isAlpha(text.charAt(j)) && isAlpha(text.charAt(k))) {
					if (text_upperCase.charAt(j) == text_upperCase.charAt(k)) {
						tmp = text.substring(j, k+1); 
						j --;
						k ++;
						lenPal += 2;
						continue;
					}
					else
						break;
				}
				if (!isAlpha(text_upperCase.charAt(k))) 
					k ++;
				if (!isAlpha(text_upperCase.charAt(j))) 
					j --;
			}

			if ((lenPal > maxLen) || (lenPal == maxLen && i < maxPos)) {
				max = tmp;
				maxLen = lenPal;
				maxPos = i;
			}
		}
	}

	public static void split () {
		for (int i = 0; i < text.length(); i++) {
			isPalindrome(i);
			if (maxLen == 1921) break;
		}

		/*
		int mid = text.length()/2;
		isPalindrome(mid);
		int left = mid-1;
		int right = mid+1;
		while (true) {
			boolean flag = false;
			//do something on the left
			if (left >= 0 && left >= maxLen/2) {
				isPalindrome(left);
				left --;
				flag = true;
			}
			//do something on the right 
			if (right < text.length() && text.length() - right >= maxLen/2) {
				isPalindrome(right);
				right ++;
				flag = true;
			}
			if (!flag) {
				break;
			}
		}*/
	}

	public static String clear_nonAlpha (String str) {
		String new_str = "";
		int start = 0;
		int end = str.length() - 1;
		while (!isAlpha(str.charAt(start))) {
			start ++;
			if (start == str.length())
				return ""; 
		}
		while (!isAlpha(str.charAt(end))) {
			end --;
		}
		new_str = str.substring(start, end+1);
		return new_str;
	}

	public static boolean isAlpha (char ch) {
		if ((ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z'))
			return true;
		else
			return false;
	}

	public static int count (String str) {
		int count = 0;
		for (int i = 0; i < str.length(); i ++) {
			if (isAlpha(str.charAt(i)))
				count ++;
		}
		return count;
	}
}