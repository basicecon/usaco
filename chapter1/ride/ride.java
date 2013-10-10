/*
ID: imdoing1
LANG: JAVA
PROG: ride 
*/

import java.util.*;
import java.lang.*;
import java.io.*;

class ride {
	public static void main (String args[]) {
		Scanner scan = null;
		try {
			scan = new Scanner(new BufferedReader(new FileReader("ride.in")));
		} catch (Exception e) {}
		String comet = scan.nextLine();
		String group = scan.nextLine();
		int comet_final = 1;
		int group_final = 1;
		char comet_char[] = new char[comet.length()];
		int comet_int[] = new int[comet.length()];
		char group_char[] = new char[group.length()];
		int group_int[] = new int[group.length()];
		for (int i = 0; i < comet.length(); i ++) {
			comet_char[i] = comet.charAt(i);
			comet_int[i] = comet_char[i] - 64;
			comet_final *= comet_int[i];
		} 
		for (int i = 0; i < group.length(); i ++) {
			group_char[i] = group.charAt(i);
			group_int[i] = group_char[i] - 64;
			group_final *= group_int[i];
		}
		try {
			PrintWriter out = new PrintWriter(
				new BufferedWriter(new FileWriter("ride.out")));
			if (comet_final % 47 == group_final % 47) 
				out.println("GO");
			else
				out.println("STAY");
			out.close();
		} catch (Exception ex) {
		
		}; 
		
	}	
}
