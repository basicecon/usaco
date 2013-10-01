/*
ID: imdoing1
LANG: JAVA
PROG: milk2
*/

import java.util.*;
import java.lang.*;
import java.io.*;

class milk2 {
    private static int[][] arr = new int[5001][2];
	private static int n;
    public static void main(String args[]) {
		Scanner scan = null;
        try {
            scan = new Scanner(new BufferedReader(new FileReader("milk2.in")));
        } catch (Exception e) {}
        PrintWriter out = null;
        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter("milk2.out")));
        } catch (Exception ee) {};

        n = scan.nextInt();

        for (int i = 0; i < n; i ++) {
            arr[i][0] = scan.nextInt();
            arr[i][1] = scan.nextInt();
        }
        while (true) {
            if (!isExistInclusion())
                break;
        }
        for (int i = 0; i < n; i ++) {
            System.out.println(arr[i][0] + " " + arr[i][1]);
        }
        sort();
        int max = 0;
        int space = 0;
        for (int i = 0; i < n; i ++) {
            if ((arr[i][1] - arr[i][0]) > max)
                max =  arr[i][1] - arr[i][0];
            if ((arr[i+1][0] - arr[i][1]) > space && i+1 < n)
                space = arr[i+1][0] - arr[i][1];
        }
        out.println(max + " " + space);
        out.close();
    }

    private static void sort() {
        for (int i = 0; i < n; i ++) {
            for (int j = i+1; j < n; j ++) {
                if (arr[i][0] > arr[j][0]) {
                    int temp = arr[j][0];
                    arr[j][0] = arr[i][0];
                    arr[i][0] = temp; 
                    temp = arr[j][1];
                    arr[j][1] = arr[i][1];                
                    arr[i][1] = temp;
                }
            }
        }
    }

    private static boolean isExistInclusion() {
        for (int i = 0; i < n; i ++) {
            for (int j = i+1; j < n; j ++) {
                if (arr[j][0] >= arr[i][0] && arr[j][1] <= arr[i][1]) {
                    deleteItem(j);
                    return true;
                }
                if (arr[j][0] <= arr[i][0] && arr[j][1] >= arr[i][1]) {
                    deleteItem(i);
                    return true;   
                }
                if (arr[i][0] <= arr[j][0] && 
                    arr[i][1] >= arr[j][0] &&
                    arr[i][1] < arr[j][1]) {
                    mergeItem(i, j);
                    return true;
                }
                if (arr[j][0] <= arr[i][0] && 
                    arr[j][1] >= arr[i][0] &&
                    arr[j][1] < arr[i][1]) {
                    mergeItem(j, i);
                    return true;
                }
            }
        }
        return false;
    }

    private static void deleteItem(int index) {
        for (int i = index; i < n; i ++) {
            arr[i][0] = arr[i+1][0];
            arr[i][1] = arr[i+1][1];
        }
        n --;
    }

    private static void mergeItem(int p, int q) {
        arr[p][1] = arr[q][1];
        deleteItem(q);
    }

}