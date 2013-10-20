/*
ID: imdoing1
LANG: JAVA
PROG: preface
*/
import java.util.*;
import java.io.*;

class preface {
	static int roman_I = 0;
	static int roman_V = 0;
	static int roman_X = 0;
	static int roman_L = 0;
	static int roman_C = 0;
	static int roman_D = 0;
	static int roman_M = 0;
	public static void main(String args[]) {
		Scanner scan = null;	
		try {
			scan = new Scanner(new BufferedReader(new FileReader("preface.in")));
		} catch (Exception e) {}
		PrintWriter out = null;
       	try {
       		out = new PrintWriter(new BufferedWriter(new FileWriter("preface.out")));
      	} catch (Exception ee) {};
      	int n = scan.nextInt();
      	for (int i = 1; i <= n; i ++) {
      		count(i);
      	}
      	if (roman_I > 0)
      		out.println("I " + roman_I);
      	if (roman_V > 0)
      		out.println("V " + roman_V);
      	if (roman_X > 0)
      		out.println("X " + roman_X);
      	if (roman_L > 0)
      		out.println("L " + roman_L);
      	if (roman_C > 0)
      		out.println("C " + roman_C);
      	if (roman_D > 0)
      	 	out.println("D " + roman_D);
      	if (roman_M > 0)
      		out.println("M " + roman_M);
      	out.close();
	}

	public static void count(int number) {		
/*
1: I       X        C        M
2: II      XX       CC       MM
3: III     XXX      CCC      MMM 
4: IV      XL       CD 
5: V       L        D
6: VI      LX       DC
7: VII     LXX      DCC
8: VIII    LXXX     DCCC
9: IX      XC       CM 
*/
		int tmp1 = number % 10;
		switch(tmp1) {
			case 1: roman_I += 1; break;
			case 2: roman_I += 2; break;
			case 3: roman_I += 3; break;
			case 4: roman_I += 1; roman_V += 1; break;
			case 5: roman_V += 1; break;
			case 6: roman_V += 1; roman_I += 1; break;
			case 7: roman_V += 1; roman_I += 2; break; 
			case 8: roman_V += 1; roman_I += 3; break;
			case 9: roman_I += 1; roman_X += 1; break;
		}
		int tmp2 = number / 10 % 10;
		switch(tmp2) {
			case 1: roman_X += 1; break;
			case 2: roman_X += 2; break;
			case 3: roman_X += 3; break;
			case 4: roman_X += 1; roman_L += 1; break;
			case 5: roman_L += 1; break;
			case 6: roman_L += 1; roman_X += 1; break;
			case 7: roman_L += 1; roman_X += 2; break; 
			case 8: roman_L += 1; roman_X += 3; break;
			case 9: roman_X += 1; roman_C += 1; break;
		}
		int tmp3 = number / 100 % 10;
		switch(tmp3) {
			case 1: roman_C += 1; break;
			case 2: roman_C += 2; break;
			case 3: roman_C += 3; break;
			case 4: roman_C += 1; roman_D += 1; break;
			case 5: roman_D += 1; break;
			case 6: roman_D += 1; roman_C += 1; break;
			case 7: roman_D += 1; roman_C += 2; break; 
			case 8: roman_D += 1; roman_C += 3; break;
			case 9: roman_C += 1; roman_M += 1; break;
		}
		int tmp4 = number / 1000;
		switch(tmp4) {
			case 1: roman_M += 1; break;
			case 2: roman_M += 2; break;
			case 3: roman_M += 3; break;
		}
	}
}