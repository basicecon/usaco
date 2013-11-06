/*
ID: imdoing1
LANG: JAVA
PROG: ttwo
*/
import java.util.*;
import java.io.*;

class ttwo {
    static int[][] map = new int[10][10];
    static class cowboy {
        int row;
        int col;
        int dir;
        public cowboy () {
            row = 0;
            col = 0;
            dir = 0;
        }
    }
    static cowboy cow = new cowboy();
    static cowboy boy = new cowboy();
    static int count = 0;
    public static void main (String args[]) {
        Scanner scan = null;        
        try {
            scan = new Scanner(new BufferedReader(new FileReader("ttwo.in")));
        } catch (Exception e) {}
        PrintWriter out = null;
        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter("ttwo.out")));
        } catch (Exception ee) {};
        for (int i = 0; i < 10; i ++) {
            String tmp = scan.next();
            char[] token = tmp.toCharArray();
            for (int j = 0; j < 10; j ++) {
                switch (token[j]) {
                    case 'F':
                        boy.row = i;
                        boy.col = j;
                        boy.dir = 0;
                        map[i][j] = 0;
                        break;
                    case 'C':
                        cow.row = i;
                        cow.col = j;
                        cow.dir = 0;
                        map[i][j] = 0;
                        break;
                    case '.':
                        map[i][j] = 0;
                        break;
                    case '*':
                        map[i][j] = 1;
                        break;
                }
            }
        }
        /*
        for (int p = 0; p < 10; p ++) {
            for (int q = 0; q < 10; q ++) {
                System.out.print(map[p][q] + " ");
            }
            System.out.println();
        }*/
        while(true) {
            move(boy);
            move(cow);
            count ++;
            if (meet(boy, cow)) {
                out.println(count);
                break;
            }
            if (count >= 160000) {
                out.println(0);
                break;
            }
        }
        out.close();
    }
    public static void move (cowboy sb) {
        cowboy temp = new cowboy();
        temp.row = sb.row;
        temp.col = sb.col;
        temp.dir = sb.dir;
        switch (temp.dir) {
            case 0: // N
                temp.row --;
                break;
            case 1: // E
                temp.col ++;
                break;
            case 2: // S
                temp.row ++;
                break;
            case 3: // W
                temp.col --;
                break;
        }
        if (isLegal(temp)) {
            sb.row = temp.row;
            sb.col = temp.col;
            sb.dir = temp.dir;
        }
        else {
            sb.dir = (sb.dir + 1) % 4;
        }
    }

    public static boolean isLegal (cowboy temp) {
        if (temp.row >= 10 || temp.col >= 10 || temp.row < 0 || temp.col < 0 || map[temp.row][temp.col] == 1) {
            return false;
        }
        return true;
    }

    public static boolean meet (cowboy c, cowboy b) {
        if (c.row == b.row && c.col == b.col) {
            return true;
        }
        return false;
    }
}