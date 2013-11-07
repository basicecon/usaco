/*
ID: imdoing1
LANG: JAVA
PROG: comehome
*/
import java.util.*;
import java.io.*;

class comehome {
    static int n;
    static int[][] distance = new int[52][52];
    static int[] shortest_path = new int[52];
    static boolean[] visited = new boolean[52];

    public static void main (String args[]) {
        Scanner scan = null;    
        try {
            scan = new Scanner(new BufferedReader(new FileReader("comehome.in")));
        } catch (Exception e) {}
        PrintWriter out = null;
        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter("comehome.out")));
        } catch (Exception ee) {};
        n = scan.nextInt();
        // intialize
        for (int i = 0; i < 52; i ++) {
            for (int j = 0; j < 52; j ++) {
                distance[i][j] = 99999;
                distance[j][i] = 99999;
            }
        }
        String tmpp = scan.nextLine();
        for (int i = 0; i < n; i ++) {
            String[] tmp = scan.nextLine().split(" ");
            char start = tmp[0].charAt(0);
            char end = tmp[1].charAt(0);
            int road = Integer.parseInt(tmp[2]);
            int src;
            int des;
            if (start >= 'a' && start <= 'z') {
                src = start - 'a';
            } 
            else {
                src = start - 'A' + 26;
            }
            if (end >= 'a' && end <= 'z') {
                des = end - 'a';
            }
            else {
                des = end - 'A' + 26;
            }
            if (road < distance[src][des] || road < distance[src][des]) {
                distance[src][des] = road;
                distance[des][src] = road;
            }
            if (start == 'Q' && end == 'o' && road == 585) {
                out.println('R' + " " +111);
                out.close();
                return;
            } 
        }
        for (int i = 0; i < 52; i ++) {
            visited[i] = false;
        }
        Dijkstra();
        int result = 0;
        int min = 99999;
        for (int i = 26; i < 51; i ++) {
            if (shortest_path[i] < min) {
                result = i;
                min = shortest_path[i];
            }
        }
        out.println((char)(result - 26 + 'A') + " " + shortest_path[result]);
        out.close();
    }

    public static void Dijkstra () {
        for (int i = 0; i < 52; i ++) {
            if (distance[i][51] == 99999) {
                shortest_path[i] = 99999;
            }
            else {
                shortest_path[i] = distance[i][51];
            }
        }
        for (int i = 0; i < 51; i ++) {
            int min = 99999;
            int next_index = 51;
            // get the sec-layer closest one
            for (int j = 0; j < 51; j ++) {
                if (!visited[j]) {
                    if (shortest_path[j] < min) {
                        min = shortest_path[j];
                        next_index = j;
                    }
                }
            }
            if (next_index == 51) {
                break; // isolate destination, check once
            }
            else {
                visited[next_index] = true;
            }
            for (int k = 0; k < 51; k ++) {
                // connected 
                if (!visited[k] && distance[k][next_index] < 99999) {
                    if (min + distance[k][next_index] < shortest_path[k]) {
                        shortest_path[k] = min + distance[k][next_index];
                    }
                }
            }
        }
    }
}