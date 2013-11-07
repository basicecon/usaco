/*
ID: imdoing1
LANG: JAVA
PROG: fracdec
*/
import java.util.*;
import java.io.*;

class fracdec {
	static Scanner scan;
    static PrintWriter out;
    static int numerator;
    static int denominator;
    static ArrayList<Integer> digits = new ArrayList<Integer>();
    static HashMap<Integer, Integer> remainder = new HashMap<Integer, Integer>();
    static List<Integer> keys = new ArrayList<Integer>();
    static String str = new String();
	public static void main (String args[]) {
		scan = null;        
        try {
            scan = new Scanner(new BufferedReader(new FileReader("fracdec.in")));
        } catch (Exception e) {}
        out = null;
        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter("fracdec.out")));
        } catch (Exception ee) {};
        numerator = scan.nextInt();
        denominator = scan.nextInt();
        longDivision();
        print();
	}

	public static void longDivision () {
		int index = 1; // start hash from 1
		digits.add(numerator/denominator);
		numerator = numerator%denominator;
		// continue when zhengchu or repeat 
		while (numerator != 0 && !remainder.containsKey(numerator)) {
			remainder.put(numerator, index);
			digits.add(10*numerator/denominator);
			numerator = 10*numerator%denominator;
			index ++;
			keys.add(numerator);
		}
	}

	public static void print () {
		int cnt = 0;
		str = "";
		str += digits.get(0);
		str += ".";
		cnt = str.length();	
		out.print(str);	
		if (numerator == 0) { // zhengchu
			if (digits.size() > 1) {  // xiao shu part
				for (int i = 1; i < digits.size(); i ++) {
					//str += digits.get(i);
					if (cnt%76 == 0) {
						out.println();
					}
					out.print(digits.get(i));
					cnt ++;
				}
			}
			else {
				//str += "0";
				out.print("0");
			}
		}
		else {
			/*
			for (int i = 0; i < keys.size(); i ++) {
				System.out.println(keys.get(i));
			}*/
			if (remainder.containsKey(numerator)) {
				// get(Object key) returns the mapping
				int start_point = remainder.get(numerator);
				for (int i = 1; i < start_point; i ++) {
					if (cnt%76 == 0) {
						out.println();
						//str += "\n";
					}
					//str += digits.get(i);
					out.print(digits.get(i));
					cnt ++;
				}
				out.print("(");
				cnt ++;
				//str += "(";
				for (int i = start_point; i <= remainder.size(); i ++) {
					if (cnt%76 == 0) {
						//str += "\n";
						out.println();
					}
					//str += digits.get(i);
					out.print(digits.get(i));
					cnt ++;
				}
				//str += ")";
				out.print(")");
				cnt ++;
			}
		}
		//out.println(str);
		out.println();
		out.close();
	}
}