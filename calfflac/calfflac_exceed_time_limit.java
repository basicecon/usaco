/*
ID: imdoing1
LANG: JAVA
PROG: calfflac
*/
import java.util.*;
import java.io.*;
class calfflac {
	public static void main (String args[]) {
		String text = "";
		String new_text = ""; 
		Scanner scan = null;
		String max = "";
		String maxx = "";
        try {
            scan = new Scanner(new BufferedReader(new FileReader("calfflac.in")));
        } catch (Exception e) {}
        while (scan.hasNext()) {
	        text += scan.nextLine();
	        new_text = toAlpha(text);
	    }
	        //System.out.println(new_text);
	    if (findMaxPal(new_text).length() > max.length()) {
	    	max = findMaxPal(new_text);
	       	//System.out.println("max: " + max);
	       	maxx = passageExistPal(text, max);
	       	//System.out.println("maxx: " + maxx);
	       	PrintWriter out = null;
       		try {
       			out = new PrintWriter(new BufferedWriter(new FileWriter("calfflac.out")));
       		} catch (Exception ee) {};
       		out.println(count(maxx));
       		out.println(maxx);
       		out.close();
       	}
	}

	public static int count (String str) {
		int count = 0;
		for (int i = 0; i < str.length(); i ++) {
			if (isAlpha(str.charAt(i))) {
				count ++;
			}
		}
		return count;
	}

	public static String passageExistPal (String str, String maxx) {
		int left = 0;
		int right = 0;
		for (int i = 0; i < str.length(); i ++) {
			for (int j = str.length()-1; j > i; j --) {
				if (isAlpha(str.charAt(i))) {
					if (Character.toUpperCase(str.charAt(i)) == Character.toUpperCase(str.charAt(j))) {
					//System.out.println(i + ": " + str.charAt(i) + "==" + j + ": " + str.charAt(j));
					String tmp = toAlpha(str.substring(i, j+1));
					//System.out.println(tmp.toUpperCase() + " " + maxx + "\n"); 
						if (maxx.equals(tmp.toUpperCase())) {
							//System.out.println("------");
							left = i;
							right = j;
							return str.substring(left, right+1);
						}
					}
				}
			}
		}
		return str.substring(left, right+1);
	}

	public static boolean isAlpha (char txt) {
		if ((txt >= 'A' && txt <= 'Z') || (txt >= 'a' && txt <= 'z')) {
			return true;
		}
		return false;
	}

	public static boolean isPalindrome (String str, int start, int end) {
		int left = start;
		int right = end;
		str = str.toUpperCase();
		while (left <= right) {
			if (str.charAt(left) == str.charAt(right)) {
				left ++;
				right --;
			}
			else 
				return false;
		}
		return true;
	}

	public static String findMaxPal (String str) {
		int n = str.length();
		str = str.toUpperCase();
		String max = "";
		for (int i = 0; i < n; i ++) {
			for (int j = n-1; j > i; j --) {
				if (str.charAt(i) == str.charAt(j)) {
					if (isPalindrome(str, i, j)) {
						//System.out.println(str.substring(i, j+1));
						if (str.substring(i, j).length() > max.length()) {
							//System.out.println(str.substring(i, j+1));
							max = str.substring(i, j+1);
						}
						break;
					}
				}
			}
		}
		return max;
	}

	public static String toAlpha (String str){
		int count = 0;
		char ascii[] = new char[str.length()];
		String convert = "";
		for (int i = 0; i < str.length(); i ++) {
			ascii[i] = str.charAt(i); 
			if (isAlpha(ascii[i])) {
				count ++;
				convert += ascii[i];
			}
		}
		return convert;
	}
}