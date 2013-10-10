/*
ID: imdoing1
LANG: JAVA
PROG: frac1
*/
import java.util.*;
import java.io.*;

class frac1 {
	static ArrayList<Node> fractions = new ArrayList<Node>();
	static int n = 0;
	public static class Node {
		int numerator;
		int denominator;
		float value;
		public Node(int a, int b) {
			numerator = a;
			denominator = b;
			value = (float)a/(float)b;
		}
	}
	public static void main(String args[]) {
		Scanner scan = null;	
		try {
			scan = new Scanner(new BufferedReader(new FileReader("frac1.in")));
		} catch (Exception e) {}
		PrintWriter out = null;
       	try {
       		out = new PrintWriter(new BufferedWriter(new FileWriter("frac1.out")));
      	} catch (Exception ee) {};
      	n = scan.nextInt();
      	allFractions();
      	/*
      	for (int i = 0; i < fractions.size()-1; i ++) {
       		System.out.println(fractions.get(i).numerator + "/" + fractions.get(i).denominator);
       	}*/

      	quickSort(0, fractions.size()-1);
      	//Arrays.sort(fractions);
       	for (int i = 0; i < fractions.size(); i ++) {
       		out.println(fractions.get(i).numerator + "/" + fractions.get(i).denominator);
       	}
       	out.close();
	}

	public static void allFractions() {
		fractions.add(new Node(0, 1));
		fractions.add(new Node(1, 1));
		for (int i = 2; i <= n; i ++) {
			fractions.add(new Node(1, i));
			for (int j = 2; j < i; j ++) {
				if (isPrime(i))
					fractions.add(new Node(j, i));
				else {
					if (isPrime(j)) {
						if (i%j != 0)   // 3/6
							fractions.add(new Node(j, i));
					}
					else {
						if (!isExistDivisor(j, i))
							fractions.add(new Node(j, i));
					}
				}
			}
		}
	}

	public static boolean isPrime(int a) {
		for (int i = 2; i <= Math.sqrt(a); i ++) {
			if (a%i == 0)
				return false;
		}
		return true;
	}

	public static boolean isExistDivisor(int a, int b) {
		for (int i = 2; i <= a/2; i ++) {
			if (a%i == 0 && b%i == 0)
				return true;
		}
		return false;
	}

	public static void quickSort(int start, int end) {
		int left = start;
		int right = end;
		float mid = fractions.get(start).value;
		while (left <= right) {
			while (fractions.get(left).value < mid)
				left ++;
			while (fractions.get(right).value > mid)
				right --;
			if (left <= right) {
				Node tmp = new Node(1, 1);
				tmp.numerator = fractions.get(left).numerator;
				tmp.denominator = fractions.get(left).denominator;
				tmp.value = fractions.get(left).value;
				fractions.get(left).numerator = fractions.get(right).numerator;
				fractions.get(left).denominator = fractions.get(right).denominator;
				fractions.get(left).value = fractions.get(right).value;
				fractions.get(right).numerator = tmp.numerator;
				fractions.get(right).denominator = tmp.denominator;
				fractions.get(right).value = tmp.value;
				left ++;
				right --;
			}
		}
		if (right > start)
			quickSort(start, right);
		if (end > left)
			quickSort(left, end);
	}


}