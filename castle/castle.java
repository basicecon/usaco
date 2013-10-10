/*
ID: imdoing1
LANG: JAVA
PROG: castle
*/
import java.util.*;
import java.io.*;

class castle {
	static int[][] map = new int[50][50];   // orginal data
	static int[][] father = new int[50][50];    // flood fill with numbers
	static int[] room_size = new int[2500];
	static int room_num = 0;
	static int[] bit = {1, 2, 4, 8};
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	static data[] cell = new data[2500];
	static int max_size = 0;
	static int largest = 0;
	static int m;
	static int n;
	static int max_i = -1;
	static int max_j = -1;
	static int max_di = -1;
	static PrintWriter out;
	static wall[] possible = new wall[5000];   // every cell two directions N & E 
	public static class data {
		int x;
		int y;
		public data(int a, int b) {
 			x = a;
 			y = b;
		}
	}
	public static class wall {
		int row;
		int col;
		char direction;
		public wall(int x, int y, char a) {
			row = x;
			col = y;
			direction = a;
		}
	}
	public static void main(String args[]) {
		Scanner scan = null;	
		try {
			scan = new Scanner(new BufferedReader(new FileReader("castle.in")));
		} catch (Exception e) {}
		out = null;
       	try {
       		out = new PrintWriter(new BufferedWriter(new FileWriter("castle.out")));
      	} catch (Exception ee) {};
      	m = scan.nextInt();
      	n = scan.nextInt();
      	for (int i = 0; i < n; i ++) {
      		for (int j = 0; j < m; j ++) {
 				map[i][j] = scan.nextInt();
 				father[i][j] = -1;
      		}
      	} 
      	
      	for (int i = 0; i < n; i ++) {
      		for (int j = 0; j < m; j ++) {
      			if (father[i][j] == -1) {
      				bfs(i, j);
      				if (room_size[room_num-1] > largest)
      					largest = room_size[room_num-1];
      			}
      		}
      	}
      	out.println(room_num);
      	out.println(largest);
      	cut();
		out.close();
	}

	public static void bfs(int x, int y) {
  		int head = 0; 
  		int tail = 0;
  		cell[head] = new data(x, y);  // tmp variable
  		room_size[room_num] ++;
  		father[x][y] = room_num;
 		while (head <= tail) {
 			for (int i = 0; i < 4; i ++) {
 				x = cell[head].x + dx[i];
 				y = cell[head].y + dy[i];
 				if (x >= 0 && x < n && y >= 0 && y < m && father[x][y] == -1 && (map[x][y] & bit[i]) == 0) {
 					father[x][y] = room_num;
 					room_size[room_num] ++;
 					tail ++;
 					cell[tail] = new data(x, y); 
 				}
 			}
 			head ++;
 		}
 		room_num ++;
	}

	public static void cut() {
		int dir = 0;
		/*
		for (int i = 0; i < n; i ++) {
			for (int j=0; j < m; j++) {
				System.out.print(father[i][j] + " ");
			}
			System.out.println();
		}
		*/
		for (int j = 0; j < m; j ++) {
			for (int i = n - 1; i >= 0; i --) {
				data tmp = new data(i, j);
				for (int k = 3; k >= 0; k --) {
					if (k == 1 || k == 2) continue;
					int x = tmp.x + dx[k];
					int y = tmp.y + dy[k];
					if (x >= 0 && x < n && y >= 0 && y < m && father[x][y] != father[i][j]) {
						if (room_size[father[x][y]] + room_size[father[i][j]] > max_size) {
							max_size = room_size[father[x][y]] + room_size[father[i][j]];
							max_i = i + 1;
							max_j = j + 1;
							max_di = k;
						}
					}
				}
			}
		}
		out.println(max_size);
		out.println(max_i + " " + max_j + " " + direction(max_di));
		/*
		int s = 0;
		for (int j = 0; j < m; j ++) {
			for (int i = n-1; i >= 0; i --) {
				for (int k = 0; k < 4; k ++) {
					if (k == 1 || k == 2) continue;
					int x = i + dx[k];
					int y = j + dy[k];
					possible[s] = new wall(i+1, j+1, direction(k)); 
					//System.out.println(i + " " + j + "  ===> " + x + " " + y);
					//System.out.println(k);
					//System.out.println(possible[0].row + " " + possible[0].col + " " + possible[0].direction);
					//System.out.println(room_size[father[x][y]] + room_size[father[i][j]]);
					//System.out.println("laaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
					if (x >= 0 && x < n && y >= 0 && y < m && father[x][y] != father[i][j]) {
						if (room_size[father[x][y]] + room_size[father[i][j]] == max_size) {
							possible[s] = new wall(i+1, j+1, direction(k)); 
							System.out.println(i + " " + j + "  ===> " + x + " " + y);
							System.out.println(possible[s].row + " " + possible[s].col + " " + possible[s].direction);
							System.out.println();
							s ++;
						}
					}
				}
			}
		}
		*/
	}

	public static char direction(int t) {
		if (t == 0)
			return 'E';
		if (t == 3)
			return 'N';
		else 
			return 0;
	}
}