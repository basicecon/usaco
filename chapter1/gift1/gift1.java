/*
ID: imdoing1
LANG: JAVA
PROG: gift1 
*/

import java.util.*;
import java.lang.*;
import java.io.*;

class gift1 {
	public static void main (String args[]) {
		Scanner scan = null;
		try {
			scan = new Scanner(new BufferedReader(new FileReader("gift1.in")));
		} catch (Exception e) {}
		int np = scan.nextInt();
		String name[] = new String[np];
		int net[] = new int[np];
		for (int i = 0; i < np; i ++) { 
			name[i] = scan.next(); 
			net[i] = 0; 
		}
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		String giver = "";
		while (scan.hasNext()) {
			giver = scan.next();
			for (int i = 0; i < np; i ++) {
				if (giver.equals(name[i])) {
					int flow = scan.nextInt();
					int num_receiver = scan.nextInt();
					if (num_receiver != 0) {
						net[i] = net[i] - flow + (flow % num_receiver);
						String receiver[] = new String[num_receiver]; 
						for (int j = 0; j < num_receiver; j ++) {
							receiver[j] = scan.next();
							for (int k = 0; k < np; k ++) {
								if (receiver[j].equals(name[k]))
									net[k] += flow/num_receiver; 
							}
						}
					}
					else 
						continue;
				}
			}
		}
		try {
			PrintWriter out = new PrintWriter(
				new BufferedWriter(new FileWriter("gift1.out")));
				for (int i = 0; i < np; i ++)
			out.println(name[i] + " " + net[i]);	
			out.close();
		} catch (Exception ex) {};
	}
}