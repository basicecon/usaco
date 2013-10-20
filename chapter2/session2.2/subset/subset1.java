/*
ID: imdoing1
LANG: JAVA
PROG: subset
*/
import java.util.*;
import java.io.*;

class subset1 {
	static int n;
	static int half_sum;
	static int tmp_sum;
	static int count;
	public static void main(String args[]) {
		PrintWriter out = null;
		Scanner scan = null;	
		try {
			scan = new Scanner(new BufferedReader(new FileReader("subset.in")));
		} catch (Exception e) {}
	   	try {
	   		out = new PrintWriter(new BufferedWriter(new FileWriter("subset.out")));
	  	} catch (Exception ee) {};
	  	n = scan.nextInt();
	  	int sum = 0;
	  	tmp_sum = 0;
	  	count = 0;
	  	for (int i = 1; i <= n; i ++) 
	  		sum += i;
	  	if (sum % 2 == 0) {
	  		half_sum = sum / 2;
	  		dfs(1);
	  		out.println(count/2);
	  	}
	  	else
	  		out.println(0);
	  	out.close();
	}

	public static void dfs(int number) {
		if (tmp_sum == half_sum) {
			count ++;
		}
		else {	
			for (int i = number; i <= n; i ++) {
				tmp_sum += i;
				if (tmp_sum <= half_sum)
					dfs(i+1);
				tmp_sum -= i;
			}
		}
	}
}