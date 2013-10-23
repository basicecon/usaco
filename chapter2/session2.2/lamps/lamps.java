/*
ID: imdoing1
LANG: JAVA
PROG: lamps
*/
import java.util.*;
import java.io.*;

class lamps {
	static int num_lamp;
	static int num_button;
	static ArrayList<Integer> index_ON = new ArrayList<Integer>();
	static ArrayList<Integer> index_OFF = new ArrayList<Integer>(); 
	static ArrayList<String> final_state = new ArrayList<String>();
	public static void main(String args[]) {
		Scanner scan = null;
		PrintWriter out = null;
		try {
			scan = new Scanner(new BufferedReader(new FileReader("lamps.in")));
		} catch (Exception e) {}
	   	try {
	   		out = new PrintWriter(new BufferedWriter(new FileWriter("lamps.out")));
	  	} catch (Exception ee) {};
	  	num_lamp = scan.nextInt();
	  	num_button = scan.nextInt();
	  	int tmp = scan.nextInt();
	  	int i = 0;
	  	while (tmp != -1) {
	  		index_ON.add(tmp);
	  		tmp = scan.nextInt();
	  		i ++;
	  	}
	  	tmp = scan.nextInt();
	  	int j = 0;
	  	while (tmp != -1) {
	  		index_OFF.add(tmp);
	  		tmp = scan.nextInt();
	  		j ++;
	  	}
	  	for (i = 0; i <= 1; i ++) {
	  		for (j = 0; j <= 1; j ++) {
	  			for (int k = 0; k <= 1; k ++) {
	  				for (int l = 0; l <= 1; l ++) {
	  					String initial = "";
	  					for (int p = 0; p < num_lamp; p ++)
	  						initial += "1"; 
	  					if (i == 1)
	  						initial = flip(1, initial);
	  					if (j == 1)
	  						initial = flip(2, initial);
	  					if (k == 1)
	  						initial = flip(3, initial);
	  					if (l == 1)
	  						initial = flip(4, initial);
						
	  					int button_cnt = i + j + k + l;

	  					if (check(initial) && button_cnt <= num_button) {
	  						if (!final_state.contains(initial)) {
	  							final_state.add(initial);
	  						}	  						
	  					}
	  				}
	  			}
	  		}
	  	}

	  	Collections.sort(final_state);

	  	if (final_state.size() == 0)
	  		out.println("IMPOSSIBLE");
	  	for (i = 0; i < final_state.size(); i ++)
	  		out.println(final_state.get(i)); 
	  	out.close();
	}

	public static String flip(int button, String ini) {
		if (button == 1) {
		 	String str = "";
		 	for (int i = 0; i < num_lamp; i ++) {
		 		str += ini.charAt(i) == '0' ? '1' : '0';
		 	}
		 	return str;
		}
		if (button == 2) {
		 	String str = "";
		 	for (int i = 0; i < num_lamp; i ++) {
		 		if (i % 2 == 1)
		 			str += ini.charAt(i) == '0' ? '1' : '0';
		 		else 
		 			str += ini.charAt(i);
		 	}
		 	return str;
		}
		if (button == 3) {
		 	String str = "";
		 	for (int i = 0; i < num_lamp; i ++) {
		 		if (i % 2 == 0)
		 			str += ini.charAt(i) == '0' ? '1' : '0';
		 		else 
		 			str += ini.charAt(i);
		 	}
		 	return str;
		}
		if (button == 4) {
		 	String str = "";
		 	for (int i = 0; i < num_lamp; i ++) {
		 		if ((i) % 3 == 0)
		 			str += ini.charAt(i) == '0' ? '1' : '0';
		 		else 
		 			str += ini.charAt(i);
		 	}
		 	return str;
		}
		return "";
	}

	public static boolean check(String str) {
		for (int i = 0; i < index_ON.size(); i ++) {
			if (str.charAt(index_ON.get(i) - 1) != '1')
				return false;
		}
		for (int i = 0; i < index_OFF.size(); i ++) {
			if (str.charAt(index_OFF.get(i) - 1) != '0')
				return false;
		}
		return true;
	}
}