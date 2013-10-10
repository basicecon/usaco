/*
ID: imdoing1
LANG: JAVA
PROG: namenum
*/
import java.util.*;
import java.io.*;

class namenum {
	public static void main (String args[]) {
		Map<String, ArrayList<String>> m = new HashMap<String, ArrayList<String>>();  
		Scanner scan = null;
        try {
            scan = new Scanner(new BufferedReader(new FileReader("dict.txt")));
        } catch (Exception e) {}
        String name_key = "";
        ArrayList<String> nameList = new ArrayList<String>();
        while (scan.hasNext()) {
        	String name = scan.next();
        	name_key = convertToKey(name);
        	nameList = m.get(name_key);
        	if (nameList == null) 
        		nameList = new ArrayList<String>();
/*        	else{
        		System.out.println("Key: " + name_key);
	        	for (String str : nameList) {
	        		System.out.print(str + " ");
	        	}
	        	System.out.println(name + "\n");
        	} */
        	nameList.add(name);
        	m.put(name_key, nameList);
        }
        //System.out.println(m.size() + "distinct key");
        try {
            scan = new Scanner(new BufferedReader(new FileReader("namenum.in")));
        } catch (Exception e) {}
        String brand = scan.next();
        nameList = m.get(brand);
        PrintWriter out = null;
        try {
	            out = new PrintWriter(new BufferedWriter(new FileWriter("namenum.out")));
	        } catch (Exception ee) {};
	    if (nameList != null) {
        	Collections.sort(nameList);
        	for (String str : nameList) {
	        	out.println(str);
	        }
        } else {
        	out.println("NONE");
        }

        out.close();
	}

	public static String convertToKey(String str) {
		int n = str.length();
		String key = "";
		for (int i = 0; i < n; i ++) {
			char ch = str.charAt(i);
			char num = letterToNum(ch);
			key += num;
		}
		return key;
	}

	private static char letterToNum(char x) {
		switch (x) {
			case 'A': case 'B': case 'C':
				return '2';
			case 'D': case 'E': case 'F':
				return '3';
			case 'G': case 'H': case 'I':
				return '4';
			case 'J': case 'K': case 'L':
				return '5';
			case 'M': case 'N': case 'O':
				return '6';
			case 'P': case 'R': case 'S':
				return '7';
			case 'T': case 'U': case 'V':
				return '8';
			case 'W': case 'X': case 'Y':
				return '9';
			default:
				return 'X';
		}
	}
}