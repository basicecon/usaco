/*
ID: imdoing1
LANG: JAVA
PROG: cowtour
*/
import java.util.*;
import java.io.*;
import java.lang.Math;

class cowtour {
    static double factor = 1e7;
    static Scanner scan;
    static PrintWriter out;
    static int n;
    static coordinate[] point;
    static class coordinate {
        int x;
        int y;
        public coordinate (int xx, int yy) {
            x = xx;
            y = yy;
        }
    }
    static double[][] distance;
    static double[][] distance_out; 
    static double[] radius;
    static double maxRadius;
    static double maxDiameter;
    static int[][] connection; // adjacency matrix
    public static void main (String args[]) {
        read();
        calculateDistance();
        updateShortestPath();
        calculateRadius();
        calculateDiameter();
        double result = Math.max(maxRadius, maxDiameter);
        String res = String.format("%.6f", result);
        out.println(res);
        out.close();
    }

    public static void read () {
        scan = null;        
        try {
            scan = new Scanner(new BufferedReader(new FileReader("cowtour.in")));
        } catch (Exception e) {}
        out = null;
        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter("cowtour.out")));
        } catch (Exception ee) {};
        n = scan.nextInt();
        point = new coordinate[n];
        for (int i = 0; i < n; i ++) {
            point[i] = new coordinate(scan.nextInt(), scan.nextInt());
        }
        connection = new int[n][n];
        for (int i = 0; i < n; i ++) {
            String tmp = scan.next();
            for (int j = 0; j < n; j ++) {
                connection[i][j] = tmp.charAt(j) - '0';
            }
        }
    }

    public static void calculateDistance () {
        distance = new double[n][n];
        distance_out = new double[n][n];
        for (int i = 0; i < n; i ++) {
            for (int j = i+1; j < n; j ++) {
                if (connection[i][j] == 1 || i == j) {
                    int sum = (point[i].x - point[j].x)*(point[i].x - point[j].x) +
                              (point[i].y - point[j].y)*(point[i].y - point[j].y);   
                    distance[i][j] = Math.round(Math.sqrt(sum)*factor)/factor;
                    distance[j][i] = distance[i][j];
                }
                else {
                    distance[i][j] = 99999;
                    distance[j][i] = 99999;
                    int sum = (point[i].x - point[j].x)*(point[i].x - point[j].x) +
                              (point[i].y - point[j].y)*(point[i].y - point[j].y);   
                    distance_out[i][j] = Math.round(Math.sqrt(sum)*factor)/factor;
                    distance_out[j][i] = distance_out[i][j];

                }
            }
        }
    }

    public static void updateShortestPath () {
        for (int k = 0; k < n; k ++) {
            for (int i = 0; i < n; i ++) {    
                for (int j = 0; j < n; j ++) {    
                    if (distance[i][k] + distance[k][j] < distance[i][j]) {
                        distance[i][j] = distance[i][k] + distance[k][j];
                    }
                }
            }
        }
    }

    public static void calculateRadius () {
        radius = new double[n];
        for (int i = 0; i < n; i ++) {
            for (int j = 0; j < n; j ++) {
                if (distance[i][j] != 99999) {
                    if (distance[i][j] > radius[i]) {
                        radius[i] = distance[i][j];
                    }
                }
            }
        }
        double max = 0;
        for (int i = 0; i < n; i ++) {
            if (radius[i] > max) {
                max = radius[i];
            }
        }
        maxRadius = max;
    }

    // main part
    public static void calculateDiameter () {
        double max = 100000;
        for (int i = 0; i < n; i ++) {
            for (int j = 0; j < n; j ++) {
                if (distance[i][j] == 99999) {
                    if (radius[i] + radius[j] + distance_out[i][j] < max) {
                        max = radius[i] + radius[j] + distance_out[i][j];
                    }
                }
            }
        }
        maxDiameter = max;
    }
}