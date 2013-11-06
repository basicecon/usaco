/*
ID: imdoing1
LANG: JAVA
PROG: maze1
*/
import java.util.*;
import java.io.*;

class maze1 {
    static int width;
    static int height;
    static cell[][] map;
    static boolean[][] check;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    static class cell {
        boolean up;
        boolean down;
        boolean left;
        boolean right;
        int dist;
        public cell () {
            up = false;
            down = false;
            left = false;
            right = false;
            dist = 99999;
        }
    }
    static coordinate[] data;
    static class coordinate {
        int x;
        int y;
        public coordinate (int row, int col) {
            x = row;
            y = col;
        }
    }
    public static void main (String args[]) {
        Scanner scan = null;        
        try {
            scan = new Scanner(new BufferedReader(new FileReader("maze1.in")));
        } catch (Exception e) {}
        PrintWriter out = null;
        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter("maze1.out")));
        } catch (Exception ee) {};
        width = scan.nextInt();
        height = scan.nextInt();
        scan.nextLine();
        map = new cell[height+2][width+2];
        check = new boolean[height+2][width+2];
        for (int i = 0; i < height + 2; i ++) {
            for (int j = 0; j < width + 2; j ++) { 
                map[i][j] = new cell();
                check[i][j] = true;
            }
        }
        data = new coordinate[5000];
        for (int i = 0; i < 2*height+1; i ++) {
            String line = scan.nextLine();
            char wall[] = line.toCharArray();
            if (i%2 == 0) {
                for (int j = 1; j < 2*width+1; j += 2) {
                    if (wall[j] == ' ') {
                        map[(i+1)/2][(j+1)/2].down = true;
                        if ((i+1)/2+1 <= height + 1)
                            map[(i+1)/2+1][(j+1)/2].up = true;
                    }
                }
            }
            else {
                for (int j = 0; j < 2*width+1; j += 2) {
                    if (wall[j] == ' ') {
                        map[(i+1)/2][j/2].right = true;
                        if (j/2+1 <= width + 1)
                            map[(i+1)/2][j/2+1].left = true;
                    }
                }
            }
        }
        for (int i = 0; i < height + 2; i ++) {
            for (int j = 0; j < width + 2; j ++) {
                if (i == 0 || i == height+1 || j == 0 || j == width+1) {
                    bfs(i, j);          
                }
            }
        }
        /*
        for (int i= 0; i< height+2; i++ ){
            for (int j= 0; j< width+2; j++ ){
                System.out.print(map[i][j].left + " ");
            }
            System.out.println();
        }
        */
        int max = 0;
        for (int i = 0; i < height + 2; i ++) {
            for (int j = 0; j < width + 2; j ++) {
                if (map[i][j].dist > max) {
                    max = map[i][j].dist;
                }
            }
        }
        out.println(max);
        out.close();
    }

    public static void bfs (int row, int col) {
        int head = 0;
        int tail = 0;
        data[head] = new coordinate(row, col);
        map[row][col].dist = 0;
        for (int i = 0; i < height + 2; i ++) {
            for (int j = 0; j < width + 2; j ++) {
                check[i][j] = true;
            }
        }
        while (head <= tail) {
            for (int i = 0; i < 4; i ++) {
                row = data[head].x + dx[i];
                col = data[head].y + dy[i];
                if (i == 0)
                    if (!map[data[head].x][data[head].y].right)
                        continue;
                if (i == 1)
                    if (!map[data[head].x][data[head].y].down)
                        continue;
                if (i == 2)
                    if (!map[data[head].x][data[head].y].left)
                        continue;
                if (i == 3)
                    if (!map[data[head].x][data[head].y].up)
                        continue;
                if (row >= 0 && row < height+2 && col >= 0 && col < width+2 &&
                    check[row][col] && map[row][col].dist > map[data[head].x][data[head].y].dist + 1) {
                    map[row][col].dist = map[data[head].x][data[head].y].dist + 1;
                    tail ++;
                    data[tail] = new coordinate(row, col);
                    check[row][col] = false;
                }
            }
            head ++;
        }
    }
}