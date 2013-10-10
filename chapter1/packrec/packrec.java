/*
ID: imdoing1
LANG: JAVA
PROG: packrec
*/
import java.util.*;
import java.io.*;

class packrec {
	static int min;
	static int index;
	static ArrayList<Rectangle> min_rec = new ArrayList<Rectangle>();
	
	static class Rectangle {
		int side[] = new int[2];
		public Rectangle () {
			side[0] = 0;
			side[1] = 0;
		}
	}


	public static void main (String args[]) {
		PrintWriter out = null;
       	try {
       		out = new PrintWriter(new BufferedWriter(new FileWriter("packrec.out")));
      	} catch (Exception ee) {};
		Rectangle r[] = read();
		model(r);
		ArrayList<Rectangle> arr = switchIt(min_rec);
		insertionSort(arr);
		out.println(min);
		for (int i = 0; i < min_rec.size(); i ++) {
			Rectangle rr = min_rec.get(i);
			out.println(rr.side[0] + " " + rr.side[1]);
		}
		out.close();
	}

	public static void update (int width, int height) {
		Rectangle curr = new Rectangle();
		curr.side[0] = width;
		curr.side[1] = height;
		int tmp = width * height;
		if (tmp > min)
			return;
		if (tmp == min) {
			for (int i = 0; i < min_rec.size(); i ++) {
				if (width == min_rec.get(i).side[0] || height == min_rec.get(i).side[0])
					return; 
			}
			min_rec.add(curr);
		}
		if (tmp < min){
			index = 0;
			min_rec.clear();
			min_rec.add(curr);
			min = tmp;
		}
		/*for (int m = 0; m < min_rec.size(); m ++)
			System.out.println("updated arraylist: " + min_rec.get(m).side[0] + " " + min_rec.get(m).side[1]);
		*/
	}	
	/*
	public static void sort (ArrayList<Rectangle> rec) { //dono whatkind of sort same result
		for (int i = 0; i < rec.size(); i ++) {
			Rectangle left_rec = rec.get(i);
			int left_area = left_rec.side[0] * left_rec.side[1];
			for (int j = rec.size()-1;j >= 0; j --) {
				Rectangle right_rec = rec.get(j);
				int right_area = right_rec.side[0] * right_rec.side[1];
				if (left_area > right_area) {
					Rectangle tmp = left_rec;
					left_rec = right_rec;
					right_rec = left_rec;
				}
			}
		}
	}
	*/

	public static ArrayList<Rectangle> switchIt (ArrayList<Rectangle> arraylist) {
		for (int i = 0; i < arraylist.size(); i ++) {
			Rectangle tmp = arraylist.get(i);
			if (tmp.side[0] > tmp.side[1]) {
				int in = tmp.side[0];
				tmp.side[0] = tmp.side[1];
				tmp.side[1] = in;
			}
		}
		return arraylist;
	}

	//array list sorting = ^ =
	public static void insertionSort (ArrayList<Rectangle> arraylist) {
		for (int i = 1; i < arraylist.size(); i ++) {
			Rectangle curr = arraylist.get(i);
			int j = i - 1;
			while (j >= 0 && arraylist.get(j).side[0] > curr.side[0]) {
				arraylist.set(j+1, arraylist.get(j));
				j --;
			}
			arraylist.set(j+1, curr);	
		}
	}

	public static Rectangle[] read () {
		Rectangle r[] = new Rectangle[4];
		Scanner scan = null;	
		try {
            scan = new Scanner(new BufferedReader(new FileReader("packrec.in")));
        } catch (Exception e) {}
        int i = 0;
        while (scan.hasNext() && i < 4) {
        	r[i] = new Rectangle();
        	int tmp1 = scan.nextInt();
        	int tmp2 = scan.nextInt();
        	if (tmp1 >= tmp2) {
        		r[i].side[0] = tmp2;
        		r[i].side[1] = tmp1;
        	}
        	else {
        		r[i].side[0] = tmp1;
        		r[i].side[1] = tmp2;	
        	}
        	i ++;
        }
        return r;
	}

	public static int max (int a, int b) {
		return (a > b ? a : b);
	}
	public static int maxx (int a, int b, int c) {
		int d = max(a, b);
		return (c > d ? c : d);
	}
	public static int maxxx (int a, int b, int c, int d) {
		int e = maxx(a, b, c);
		return (d > e ? d : e);
	}
	
	public static void model (Rectangle[] r){
		min = ~0x80000000;
		for (int i = 0; i < 4; i ++) {
			for (int j = 0; j < 4; j ++) {
				if (i == j) 
					continue;
				for (int k = 0; k < 4; k ++) {
					if (k == i || k == j) 
						continue;
					int l = 6 - i - j - k;
					for (int q = 0; q < 16; q ++) {	
						int[] w = new int[4];
						int[] h = new int[4];
						int wmin, hmin;
						w[0] = r[i].side[bitwise_I(q)];
						h[0] = r[i].side[1 - bitwise_I(q)]; 
						w[1] = r[j].side[bitwise_J(q)];
						h[1] = r[j].side[1 - bitwise_J(q)];
						w[2] = r[k].side[bitwise_K(q)];
						h[2] = r[k].side[1 - bitwise_K(q)];
						w[3] = r[l].side[bitwise_L(q)];
						h[3] = r[l].side[1 - bitwise_L(q)];
						//first model
						wmin = w[0] + w[1] + w[2] + w[3];
						hmin = maxxx(h[0], h[1], h[2], h[3]);   
						update(wmin, hmin);
						//second
						wmin = max(w[0]+w[1]+w[2], w[3]);
						hmin = maxx(h[0], h[1], h[2]) + h[3];
						update(wmin, hmin);
						//third model
						wmin = max(w[0]+w[1], w[3]) + w[2];
						hmin = max(max(h[0], h[1])+h[3], h[2]);
						update(wmin, hmin);
						//fourth model
						wmin = max(w[0]+w[1]+w[2], w[0]+w[2]+w[3]);
						hmin = maxx(h[0], h[1]+h[3], h[2]);
						update(wmin, hmin);
						//fifth model
						wmin = max(w[0]+w[1]+w[2], w[1]+w[2]+w[3]);
						hmin = maxx(h[0]+h[3], h[1], h[2]);
						update(wmin, hmin);
						//sixth model
						if (h[2] == h[3])
							wmin = max(w[0]+w[1], w[2]+w[3]);
						else if (h[2] < h[3] && h[0]+h[2] > h[3])
							wmin = maxx(w[0]+w[1], w[0]+w[3], w[2]+w[3]);
						else if (h[2] > h[3] && h[2] < h[1]+h[3])
							wmin = maxx(w[0]+w[1], w[1]+w[2], w[2]+w[3]);
						hmin = max(h[0]+h[2], h[1]+h[3]);  
						update(wmin, hmin);
					}
				} 
			}
		}
	}

	public static int bitwise_I (int q) {
		return (q & 0x08) >> 3;
	}
	public static int bitwise_J (int q) {
		return (q & 0x04) >> 2;
	}
	public static int bitwise_K (int q) {
		return (q & 0x02) >> 1;
	}
	public static int bitwise_L (int q) {
		return (q & 0x01);
	}
}
