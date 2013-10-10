/*
ID: imdoing1
LANG: JAVA
PROG: sort3
*/
import java.util.*;
import java.io.*;

class sort3 {
	static int cnt = 0;
	public static void main(String args[]) {
		Scanner scan = null;	
		try {
			scan = new Scanner(new BufferedReader(new FileReader("sort3.in")));
		} catch (Exception e) {}
		PrintWriter out = null;
       	try {
       		out = new PrintWriter(new BufferedWriter(new FileWriter("sort3.out")));
      	} catch (Exception ee) {};
      	int n = scan.nextInt();
      	int[] seq = new int[n];
      	int[] fre = new int[4];   // java start at 0
      	for (int i = 0; i < n; i ++) {
      		seq[i] = scan.nextInt();
   			fre[seq[i]] ++;
      	}
      	// zone 1: 0 ==> fre(1)-1
      	// zone 2: fre(1) ==> fre(1)+fre(2)-1
      	// zone 3: fre(1)+fre(2) ==> n

      	// clear 1s in zone 2
      	for (int i = fre[1]; i < fre[1]+fre[2]; i ++) {
      		if (seq[i] == 1) {
      			if (findNextIndex(seq, 2) < fre[1])
      				swap(seq, i, findNextIndex(seq, 2));
      			else
      				swap(seq, i, findNextIndex(seq, 3));
      		}
      	}
      	// clear 1s in zone 3
      	for (int i = fre[1]+fre[2]; i < n; i ++) {
      		if (seq[i] == 1) {
      			if (findNextIndex(seq, 3) < fre[1])
      				swap(seq, i, findNextIndex(seq, 3));
      			else
      				swap(seq, i, findNextIndex(seq, 2));
      		}	
      	}
      	// swap 2s and 3s 
      	// in zone 2 and zone 3
      	for (int i = fre[1]+fre[2]; i < n; i ++) {
      		if (seq[i] == 2) {
      			swap(seq, i, findNextIndex(seq, 3));
      		}
      	}
      	out.println(cnt);
      	/*
      	for (int i = 0; i < n; i ++) {
      		System.out.print(seq[i] + " ");
      	}
      	System.out.println();
      	*/
      	out.close();
	}

	public static void swap(int[] s, int i, int j) {
		cnt ++;
		int tmp = s[i];
		s[i] = s[j];
		s[j] = tmp;
	}

	public static int findNextIndex(int[] a, int next) {
		for (int i = 0; i < a.length; i ++) {
			if (a[i] == next)
				return i; 
		}
		return 0;
	}
}