/*
ID: imdoing1
LANG: JAVA
PROG: milk3
*/
import java.util.*;
import java.io.*;
import java.lang.Math.*;

class milk3{
	static boolean checked[][] = new boolean[21][21];
	static boolean possible[] = new boolean[21];
	static int a;
	static int b;
	static int c;

	public static void pour(int x, int y, int z) {
		if (checked[x][y])
			return;
		checked[x][y] = true;
		if (x == 0)
			possible[z] = true;
		// x ==> y
		if (x > 0 && y < b)
			pour(Math.max(0, x+y-b), Math.min(x+y, b), z); 
		// x ==> z
		if (x > 0 && z < c)
			pour(Math.max(0, x+z-c), y, Math.min(x+z, c));
		// y ==> x
		if (y > 0 && x < a) 
			pour(Math.min(x+y, a), Math.max(0, x+y-a), z);
		// y ==> z
		if (y > 0 && z < c) 
			pour(a, Math.max(0, z+y-c), Math.min(y+z, c));
		// z ==> x
		if (z > 0 && x < a)
			pour(Math.min(x+z, a), y, Math.max(0, z+x-a));
		// z ==> y
		if (z > 0 && y < b)
			pour(x, Math.min(z+y, b), Math.max(0, y+z-b));
	}

	public static void main(String args[]) {
		Scanner scan = null;	
		try {
            scan = new Scanner(new BufferedReader(new FileReader("milk3.in")));
        } catch (Exception e) {}
        PrintWriter out = null;
       	try {
       		out = new PrintWriter(new BufferedWriter(new FileWriter("milk3.out")));
      	} catch (Exception ee) {};
        a = scan.nextInt();
        b = scan.nextInt();
        c = scan.nextInt();
        pour(0, 0, c);
        for (int i = 0; i < c; i ++) {
        	if (possible[i])
        		out.print(i + " ");
        }
        out.println(c);
        out.close();
	}
}