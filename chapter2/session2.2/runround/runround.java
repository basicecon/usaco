/*
ID: imdoing1
LANG: JAVA
PROG: runround
*/
import java.util.*;
import java.io.*;

class runround {
	public static void main(String args[]) {
		Scanner scan = null;
		PrintWriter out = null;	
		try {
			scan = new Scanner(new BufferedReader(new FileReader("runround.in")));
		} catch (Exception e) {}
	   	try {
	   		out = new PrintWriter(new BufferedWriter(new FileWriter("runround.out")));
	  	} catch (Exception ee) {};
	  	int start = scan.nextInt();
	  	int i = start + 1;
	  	while(true) {
	  		if(check(i)) {
	  			out.println(i);
	  			break;
	  		}
	  		i ++;
	  	}
	  	out.close();
	}

	public static boolean check(int number) {
		int length = String.valueOf(number).length();
	  	String str = Integer.toString(number);
	  	char[] charArr = str.toCharArray();
	  	int[] intArr = new int[length];
	  	int[] occur = new int[10]; // 1-9
	  	for (int i = 0; i < length; i ++) {
	  		intArr[i] = charArr[i] - '0';
	  		occur[intArr[i]] ++;
	  		if (intArr[i] == 0)
	  			return false;
	  		if (occur[intArr[i]] > 1)
	  			return false;
	  	}
	  	boolean[] arr = new boolean[length];
	  	Arrays.fill(arr, false);
	  	int pos = 0;
	  	int count = 0;
	  	while(count < length) {
	  		int tmp_num = intArr[pos];
	  		int next = tmp_num%length+pos;
	  		next = next%length;
	  		if (arr[next])
	  			return false;
	  		arr[next] = true;
	  		pos = next;
	  		count ++;
	  	}
	  	return true;
	}
}