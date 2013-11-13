/*
ID: imdoing1
LANG: JAVA
PROG: rect1
*/
import java.util.*;
import java.io.*;
import java.lang.Math;

class rect1 {
	static int n; // num of colors
	static int[] l, r, t, b, c;
	static int[] color_area = new int[2501];  // every color's area
	static Scanner scan;
	static PrintWriter out;

	public static void main (String args[]) {
		scan = null;    
        try {
            scan = new Scanner(new BufferedReader(new FileReader("rect1.in")));
        } catch (Exception e) {}
        out = null;
        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter("rect1.out")));
        } catch (Exception ee) {};
		read();
		run();
		output();
	}
	public static void read () {
        int right = scan.nextInt();
        int top = scan.nextInt();
        n = scan.nextInt();

        l = new int[n+1];
        r = new int[n+1];
        t = new int[n+1];
        b = new int[n+1];
        c = new int[n+1];

        t[0] = top;
        r[0] = right;

        for (int i = 0; i < n; i ++) {
        	l[i+1] = scan.nextInt(); 
        	b[i+1] = scan.nextInt();
        	r[i+1] = scan.nextInt();
        	t[i+1] = scan.nextInt();
        	c[i+1] = scan.nextInt();
        }
	}

	public static void run () {
		RectangleList list = new RectangleList(new Rectangle(l[0], r[0], t[0], b[0], 1));
		for (int i = n; i > 0; i --) {
			/*System.out.println(l[i] + " " + b[i] + " " + r[i] + " " + t[i] + " " + "WITH");*/
			Rectangle layer = new Rectangle(l[i], r[i], t[i], b[i], c[i]); /*c[i]*/
			ArrayList overlaps = list.overlapRec(layer);
			
			/*System.out.println("how many overlaps: " + overlaps.size());*/
			Iterator r = overlaps.iterator();
			while (r.hasNext()) {
				/*System.out.println("every overlap: ");*/
			//for (Rectangle r : overlaps) {
				Rectangle overlap = (Rectangle)r.next();
				/*System.out.println("area: " + layer.overlapArea(overlap));*/
				color_area[layer.color] += layer.overlapArea(overlap);
				Rectangle[] rect = layer.cut(overlap);
				// layer&overlap_overlapArea + rect[all] = overlap 
				int rect_area = 0;
				if (rect != null) {
					for (int m = 0; m < rect.length; m ++) {
						rect_area += rect[m].area();
					}
				}
				if (layer.overlapArea(overlap) + rect_area != overlap.area()) {
					System.out.println(layer.overlapArea(overlap));
					System.out.println(rect_area);
					System.out.println(overlap.area());
					System.out.println("OVERLAP AREA: " + overlap.left + " " + overlap.bottom + " " + overlap.right + " " + overlap.top);
					System.out.println("LAYER AREA: " + layer.left + " " + layer.bottom + " " + layer.right + " " + layer.top);
				}
				/*System.out.println("after cutted, how many rect: " + rect.length);*/
				if (rect != null) {
					for (int j = 0; j < rect.length; j ++) {
						list.insertRec(rect[j]);
					}
				}
				list.removeRec(overlap);
			}
			/*System.out.println("how many rects remaining on the list: " + list_size);*/
		}
		color_area[1] = r[0]*t[0];
		for (int i = 2; i <= 2500; i ++) {
			color_area[1] -= color_area[i];
		}
	}

	public static void output () {
        for (int i = 1; i <= 2500; i ++) {
        	if (color_area[i] > 0) {
        		out.println(i + " " + color_area[i]);
        	}
        }
        out.close();
	}

	static class Rectangle {
		// four borders
		int left; 
		int right;
		int top;
		int bottom;
		int color;
		public Rectangle (int left, int right, int top, int bottom, int color) {
			this.left = left;
			this.right = right;
			this.top = top;
			this.bottom = bottom;
			this.color = color;
		}
		public int area () {
			int a = (this.right - this.left) * (this.top - this.bottom);
			return a;
		}
		// decompose base
		public Rectangle[] cut (Rectangle base) {
			Rectangle[] rectList = null;
			int color = base.color;
			int num;
			if ((base.right <= this.right) && (base.right >= this.left)) {
				if ((base.left <= this.right) && (base.left >= this.left)) {
					if ((base.top <= this.top) && (base.top >= this.bottom)) {
						if ((base.bottom <= this.top) && (base.bottom >= this.bottom)) {
						}
						else {  // bottom out
							num = 1;
							Rectangle[] rect = new Rectangle[num];
							int[] l = new int[num];
							int[] r = new int[num];
							int[] t = new int[num];
							int[] b = new int[num];

							l[0] = base.left;
							r[0] = base.right;
							t[0] = this.bottom;
							b[0] = base.bottom;

							for (int i = 0; i < num; i ++)
								rect[i] = new Rectangle(l[i], r[i], t[i], b[i], color);
							rectList = rect;
						}
					}
					else { // top out
						if ((base.bottom <= this.top) && (base.bottom >= this.bottom)) {
							num = 1;
							Rectangle[] rect = new Rectangle[num];
							int[] l = new int[num];
							int[] r = new int[num];
							int[] t = new int[num];
							int[] b = new int[num];

							l[0] = base.left;
							r[0] = base.right;
							t[0] = base.top;
							b[0] = this.top;

							for (int i = 0; i < num; i ++)
								rect[i] = new Rectangle(l[i], r[i], t[i], b[i], color);
							rectList = rect;
						}
						else {  // top, bottom out
							num = 2;
							Rectangle[] rect = new Rectangle[num];
							int[] l = new int[num];
							int[] r = new int[num];
							int[] t = new int[num];
							int[] b = new int[num];

							l[0] = base.left;
							r[0] = base.right;
							t[0] = base.top;
							b[0] = this.top;

							l[1] = base.left;
							r[1] = base.right;
							t[1] = this.bottom;
							b[1] = base.bottom;							

							for (int i = 0; i < num; i ++)
								rect[i] = new Rectangle(l[i], r[i], t[i], b[i], color);
							rectList = rect;
						}
					}
				}
				else {  // left out
					if ((base.top <= this.top) && (base.top >= this.bottom)) {
						if ((base.bottom <= this.top) && (base.bottom >= this.bottom)) { // only left out
							num = 1;
							Rectangle[] rect = new Rectangle[num];
							int[] l = new int[num];
							int[] r = new int[num];
							int[] t = new int[num];
							int[] b = new int[num];

							l[0] = base.left;
							r[0] = this.left;
							t[0] = base.top;
							b[0] = base.bottom;

							for (int i = 0; i < num; i ++)
								rect[i] = new Rectangle(l[i], r[i], t[i], b[i], color);
							rectList = rect;
						}
 						else { // left, bottom out
 							num = 2;
							Rectangle[] rect = new Rectangle[num];
							int[] l = new int[num];
							int[] r = new int[num];
							int[] t = new int[num];
							int[] b = new int[num];

							l[0] = base.left;
							r[0] = this.left;
							t[0] = base.top;
							b[0] = base.bottom;

							l[1] = this.left;
							r[1] = base.right;
							t[1] = this.bottom;
							b[1] = base.bottom;							

							for (int i = 0; i < num; i ++)
								rect[i] = new Rectangle(l[i], r[i], t[i], b[i], color);
							rectList = rect;
						}
					}
					else { // left, top out
						if ((base.bottom <= this.top) && (base.bottom >= this.bottom)) {
							num = 2;
							Rectangle[] rect = new Rectangle[num];
							int[] l = new int[num];
							int[] r = new int[num];
							int[] t = new int[num];
							int[] b = new int[num];

							l[0] = base.left;
							r[0] = this.left;
							t[0] = base.top;
							b[0] = base.bottom;

							l[1] = this.left;
							r[1] = base.right;
							t[1] = base.top;
							b[1] = this.top;							

							for (int i = 0; i < num; i ++)
								rect[i] = new Rectangle(l[i], r[i], t[i], b[i], color);
							rectList = rect;
						}
						else { // left, top, bottom out
							num = 3;
							Rectangle[] rect = new Rectangle[num];
							int[] l = new int[num];
							int[] r = new int[num];
							int[] t = new int[num];
							int[] b = new int[num];

							l[0] = base.left;
							r[0] = this.left;
							t[0] = base.top;
							b[0] = base.bottom;

							l[1] = this.left;
							r[1] = base.right;
							t[1] = base.top;
							b[1] = this.top;
							
							l[2] = this.left;
							r[2] = base.right;
							t[2] = this.bottom;
							b[2] = base.bottom;							

							for (int i = 0; i < num; i ++)
								rect[i] = new Rectangle(l[i], r[i], t[i], b[i], color);	
							rectList = rect;
						}
					}
				}
			}
			else {  // right out
				if ((base.left <= this.right) && (base.left >= this.left)) {  // left in
					if ((base.top <= this.top) && (base.top >= this.bottom)) {  // top in
						if ((base.bottom <= this.top) && (base.bottom >= this.bottom)) { // bottom in
							num = 1;
							Rectangle[] rect = new Rectangle[num];
							int[] l = new int[num];
							int[] r = new int[num];
							int[] t = new int[num];
							int[] b = new int[num];

							l[0] = this.right;
							r[0] = base.right;
							t[0] = base.top;
							b[0] = base.bottom;

							for (int i = 0; i < num; i ++)
								rect[i] = new Rectangle(l[i], r[i], t[i], b[i], color);
							rectList = rect;
						}
						else {  // right, bottom out
							num = 2;
							Rectangle[] rect = new Rectangle[num];
							int[] l = new int[num];
							int[] r = new int[num];
							int[] t = new int[num];
							int[] b = new int[num];

							l[0] = base.left;
							r[0] = this.right;
							t[0] = this.bottom;
							b[0] = base.bottom;

							l[1] = this.right;
							r[1] = base.right;
							t[1] = base.top;
							b[1] = base.bottom;							

							for (int i = 0; i < num; i ++)
								rect[i] = new Rectangle(l[i], r[i], t[i], b[i], color);
							rectList = rect;
						}
					}
					else {  // right, top out
						if ((base.bottom <= this.top) && (base.bottom >= this.bottom)) { // bottom in
							num = 2;
							Rectangle[] rect = new Rectangle[num];
							int[] l = new int[num];
							int[] r = new int[num];
							int[] t = new int[num];
							int[] b = new int[num];

							l[0] = base.left;
							r[0] = this.right;
							t[0] = base.top;
							b[0] = this.top;

							l[1] = this.right;
							r[1] = base.right;
							t[1] = base.top;
							b[1] = base.bottom;							

							for (int i = 0; i < num; i ++)
								rect[i] = new Rectangle(l[i], r[i], t[i], b[i], color);
							rectList = rect;
						}
						else {  // right, top, bottom out
							num = 3;
							Rectangle[] rect = new Rectangle[num];
							int[] l = new int[num];
							int[] r = new int[num];
							int[] t = new int[num];
							int[] b = new int[num];

							l[0] = this.right;
							r[0] = base.right;
							t[0] = base.top;
							b[0] = base.bottom;

							l[1] = base.left;
							r[1] = this.right;
							t[1] = base.top;
							b[1] = this.top;
							
							l[2] = base.left;
							r[2] = this.right;
							t[2] = this.bottom;
							b[2] = base.bottom;							

							for (int i = 0; i < num; i ++)
								rect[i] = new Rectangle(l[i], r[i], t[i], b[i], color);	
							rectList = rect;
						}
					}
				}
				else {  // right, left out
					if ((base.top <= this.top) && (base.top >= this.bottom)) {  // top in
						if ((base.bottom <= this.top) && (base.bottom >= this.bottom)) { // bottom in
							num = 2;
							Rectangle[] rect = new Rectangle[num];
							int[] l = new int[num];
							int[] r = new int[num];
							int[] t = new int[num];
							int[] b = new int[num];

							l[0] = base.left;
							r[0] = this.left;
							t[0] = base.top;
							b[0] = base.bottom;

							l[1] = this.right;
							r[1] = base.right;
							t[1] = base.top;
							b[1] = base.bottom;							

							for (int i = 0; i < num; i ++)
								rect[i] = new Rectangle(l[i], r[i], t[i], b[i], color);
							rectList = rect;
						}
						else {  // right, left, bottom out
							num = 3;
							Rectangle[] rect = new Rectangle[num];
							int[] l = new int[num];
							int[] r = new int[num];
							int[] t = new int[num];
							int[] b = new int[num];

							l[0] = this.left;
							r[0] = this.right;
							t[0] = this.bottom;
							b[0] = base.bottom;

							l[1] = base.left;
							r[1] = this.left;
							t[1] = base.top;
							b[1] = base.bottom;
							
							l[2] = this.right;
							r[2] = base.right;
							t[2] = base.top;
							b[2] = base.bottom;							

							for (int i = 0; i < num; i ++)
								rect[i] = new Rectangle(l[i], r[i], t[i], b[i], color);	
							rectList = rect;
						}
					}
					else { // right, left, top out
						if ((base.bottom <= this.top) && (base.bottom >= this.bottom)) { // bottom in
							num = 3;
							Rectangle[] rect = new Rectangle[num];
							int[] l = new int[num];
							int[] r = new int[num];
							int[] t = new int[num];
							int[] b = new int[num];

							l[0] = this.left;
							r[0] = this.right;
							t[0] = base.top;
							b[0] = this.top;

							l[1] = base.left;
							r[1] = this.left;
							t[1] = base.top;
							b[1] = base.bottom;
							
							l[2] = this.right;
							r[2] = base.right;
							t[2] = base.top;
							b[2] = base.bottom;							

							for (int i = 0; i < num; i ++)
								rect[i] = new Rectangle(l[i], r[i], t[i], b[i], color);		
							rectList = rect;
						}
						else {  // right. left, top, bottom out
							num = 4;
							Rectangle[] rect = new Rectangle[num];
							int[] l = new int[num];
							int[] r = new int[num];
							int[] t = new int[num];
							int[] b = new int[num];

							l[0] = this.left;
							r[0] = this.right;
							t[0] = base.top;
							b[0] = this.top;

							l[1] = this.left;
							r[1] = this.right;
							t[1] = this.bottom;
							b[1] = base.bottom;
							
							l[2] = base.left;
							r[2] = this.left;
							t[2] = base.top;
							b[2] = base.bottom;

							l[3] = this.right;
							r[3] = base.right;
							t[3] = base.top;
							b[3] = base.bottom;							

							for (int i = 0; i < num; i ++)
								rect[i] = new Rectangle(l[i], r[i], t[i], b[i], color);	
							rectList = rect;
						}
					}
				}
			}
			//rectList = rect;
			return rectList;
		}
	
		public boolean isOverlap (Rectangle base) {
			//  ---		20	
			// |   |
			//  ---
			//  ---
			// |   |
			//  ---     0
			/*
			System.out.println("COMPARISON: ");
			System.out.print(base.left + " " + base.bottom + " " + base.right + " " + base.top + " ");
			*/
			int l = Math.max(this.left, base.left);
			int r = Math.min(this.right, base.right);
			if (l >= r) {
				//System.out.println("FALSE");
				return false;
			}
			int t = Math.min(this.top, base.top); 
			int b = Math.max(this.bottom, base.bottom);
			if (b >= t) {
				//System.out.println("FALSE");
				return false;
			}
			/*
			System.out.println("TRUE");
			System.out.println("l = " + l + " = " + "MAX(" + " " + this.left + ", " + base.left + " )");
			System.out.println("r = " + r + " = " + "MIN(" + " " + this.right + ", " + base.right + " )");
			System.out.println("b = " + b + " = " + "MAX(" + " " + this.bottom + ", " + base.bottom + " )");
			System.out.println("t = " + t + " = " + "MIN(" + " " + this.top + ", " + base.top + " )");
			*/
			return true;
		}

		public int overlapArea (Rectangle base) {
			int l = Math.max(this.left, base.left);
			int r = Math.min(this.right, base.right);
			if (l >= r)
				return 0;
			int t = Math.min(this.top, base.top); 
			int b = Math.max(this.bottom, base.bottom);
			if (b >= t)
				return 0;
			return (r-l)*(t-b);	
		}
	}; // end of Rectangle class
	static class RectangleList {
		ArrayList recList = new ArrayList<Rectangle>();
		public RectangleList (Rectangle base) {
			recList.add(base);
		}
		public void insertRec (Rectangle base) {
			recList.add(base);
		}
		public void removeRec (Rectangle base) {
			recList.remove(base);
		}
		public ArrayList overlapRec (Rectangle base) {
			ArrayList overLapList = new ArrayList<Rectangle>();
			Iterator it = recList.iterator();
			while (it.hasNext()) {
				Rectangle r = (Rectangle)it.next(); 
			//for (int r = 0; r < this.recList.size(); r ++) {
				if (base.isOverlap(r)) {
					//System.out.println("is overlap with: " + r.left + " " + r.bottom + " " + r.right + " " + r.top);
				//if (base.isOverlap((Rectangle)this.recList.get(r))) {
					overLapList.add(r);
					//overLapList.add(this.recList.get(r));
				}
			}
			return overLapList;
		}
	};
}