/*
ID: imdoing1
LANG: JAVA
PROG: concom
*/
import java.util.*;
import java.io.*;

class concom {
    static int n;
    static int comp_num = 0;
    static int[][] percentage = new int[105][105];
    static ArrayList<int[]> list = new ArrayList<int[]>();
    public static void main (String args[]) {
        Scanner scan = null;        
        try {
            scan = new Scanner(new BufferedReader(new FileReader("concom.in")));
        } catch (Exception e) {}
        PrintWriter out = null;
        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter("concom.out")));
        } catch (Exception ee) {};
        n = scan.nextInt();
        for (int[] j : percentage)
            Arrays.fill(j, 0);
        for (int i = 1; i <= n; i ++) {
            int big = scan.nextInt();
            int small = scan.nextInt();
            if (big > comp_num)
                comp_num = big;
            if (small > comp_num)
                comp_num = small;
            percentage[big][small] = scan.nextInt();
            //System.out.println(percentage[big[i]][small[i]]);
        }
        fill();
        for (int p = 1; p <= 100; p ++) {
            for (int q = 1; q <= 100; q ++) {
                //System.out.print(percentage[p][q]);
                if (percentage[p][q] > 50) {
                    int tmp[] = new int[2];
                    tmp[0] = p;
                    tmp[1] = q;
                    if (q != p)
                        list.add(tmp);
                }
                //System.out.println();
            }
        }
        for (int k = 0; k < list.size(); k ++) {
            out.println(list.get(k)[0] + " " + list.get(k)[1]);
        }
        out.close();
    }

    public static void fill () {
        System.out.println(comp_num);
        for (int i = 1; i <= comp_num; i ++) {  // check all bigs
            boolean[] sig = new boolean[comp_num+1];
            Arrays.fill(sig, true);
            while (true) {
                boolean flag = false;
                for (int j = 1; j <= comp_num; j ++) {                                               
                    if (sig[j] && percentage[i][j] > 50) {                        
                        flag = true;
                        update(i, j);
                        sig[j] = false;
                    }  
                }
                if (!flag)
                    break;
            }            
        }
    }

    public static void update (int x, int y) {
        for (int i = 1; i <= comp_num; i ++) {
            percentage[x][i] += percentage[y][i];
            if (percentage[x][i] > 100) 
                percentage[x][i] = 100;
        }
    }
}
