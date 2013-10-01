/*
ID: imdoing1
LANG: JAVA
PROG: beads
*/

import java.util.*;
import java.lang.*;
import java.io.*;

class beads {
    public static void main (String args[]) {
        Scanner scan = null;
        try {
            scan = new Scanner(new BufferedReader(new FileReader("beads.in")));
        } catch (Exception e) {}
        PrintWriter out = null;
        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter("beads.out")));
        } catch (Exception ee) {};

        int n = scan.nextInt();
        String bead = scan.next();
        String beadString = bead + bead;
        int indexNotWhite = notWhiteIndex(0, beadString);
        if (indexNotWhite == -1) {
            out.println(bead.length());
            out.close();
            return;
        }
        int max = 0;
        
        do {
            char left = beadString.charAt(indexNotWhite);
            char right = (left == 'r' ? 'b' : 'r');
            int left_count = leftCount(left, indexNotWhite, beadString);
            int right_count = rightCount(right, indexNotWhite+1, beadString);
            if (left_count + right_count > max)
                max = left_count + right_count;
        }
        while ((indexNotWhite = notWhiteIndex(indexNotWhite+1, beadString)) != -1);


        if (max > n)
            out.println(n);
        else 
            out.println(max);
        out.close();
    }
    public static int notWhiteIndex (int start, String beads) {
        for (int i = start; i < beads.length(); i ++) {
            if (beads.charAt(i) != 'w')
                return i;
        }
        return -1;
    }
    public static int leftCount (char color, int start, String beads) {
        int count = 0;
        for (int i = start; i >= 0; i --) {
            if (beads.charAt(i) == color || beads.charAt(i) == 'w')
                count ++;
            else 
                return count; 
        }
        return count;
    }
    public static int rightCount (char color, int start, String beads) {
        int count = 0;
        for (int i = start; i < beads.length(); i ++) {
            if (beads.charAt(i) == color || beads.charAt(i) == 'w')
                count ++;
            else
                return count;
        }
        return count;
    }


}