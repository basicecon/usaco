/*
ID: imdoing1
LANG: JAVA
PROG: money
*/
import java.util.*;
import java.io.*;

class money {
    public static int n;
    public static int amount;
    public static int[] cc;
    public static long[][] bb = new long[25][10001];  // ????
    public static void main (String args[]) {
        Scanner scan = null;        
        try {
            scan = new Scanner(new BufferedReader(new FileReader("money.in")));
        } catch (Exception e) {}
        PrintWriter out = null;
        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter("money.out")));
        } catch (Exception ee) {};
        n = scan.nextInt();
        amount = scan.nextInt();
        cc = new int[n];
        for (int i = 0; i < n; i ++)
            cc[i] = scan.nextInt();
        for (long[] j : bb)
            Arrays.fill(j, -1);
        out.println(dp(n - 1, amount));
        out.close();  
    }
    // top down 
    public static long dp (int cc_cnt, int mon_cnt) {
        if (mon_cnt == 0)
            return 1;
        if (mon_cnt < 0)
            return 0;
        /*if (cc_cnt == 0)
            return 1;*/
        if (cc_cnt < 0)
            return 0;
        if (bb[cc_cnt][mon_cnt] >= 0)
            return bb[cc_cnt][mon_cnt];
        bb[cc_cnt][mon_cnt] = 0;
        for (int i = 0; i <= cc_cnt; i ++) {
            bb[cc_cnt][mon_cnt] += dp(i, mon_cnt-cc[i]);
            /*
            int tmp = i - 1;
            int tmpp = mon_cnt - i;
            System.out.println("dp" + "(" + tmp + ", " + tmpp + ") " + "= " + dp(i-1, mon_cnt-i));
            */
        }/*
        for (int k = 0; k < cc_cnt; k ++) {
            for (int j = 0; j < mon_cnt; j ++) {
                System.out.print(bb[k][j] + " ");
            }
            System.out.println();
        } */
        return bb[cc_cnt][mon_cnt];
    }
}