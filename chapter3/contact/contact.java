/*
ID: imdoing1
LANG: JAVA
PROG: contact
*/
import java.util.*;
import java.io.*;
import java.lang.Math;

class contact {
	//static Scanner scan = null;
	static BufferedReader br;
	static PrintWriter out = null;
	static int a;
	static int b;
	static int n;
	static String sequence;
	static int[] tree = new int[8193]; /* summation of 2^a, a from 0 to 12 */
	static int[] power_two = new int[]{1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192};
	static ArrayList<Integer> frequency = new ArrayList<Integer>();
	public static void main (String args[]) {
		Arrays.fill(tree, 0);
		read();
		//treeLeafFill();
		output();
	}

	public static void read () {
		try {
			br = new BufferedReader(new FileReader("contact.in"));
            //scan = new Scanner(new BufferedReader(new FileReader("contact.in")));
	        StringTokenizer st = new StringTokenizer(br.readLine());
	        a = Integer.parseInt(st.nextToken());
	        b = Integer.parseInt(st.nextToken());
	        n = Integer.parseInt(st.nextToken());
	        String line;
	        line = br.readLine();
	        treeLeafFill(line);     
	        while (true) {
	        	String curr_line = br.readLine();
	        	if (curr_line == null) {
	        		// tails 
	        		if (line.length() >= 12)
	       				treeLeafFill(line.substring(line.length()-11));
	        		break;
	        	}
	        	else {
	        		line = line.substring(line.length()-11) + curr_line;
	        		treeLeafFill(line);
	        	} 
	        }
	        for (int index = 4093; index >= 0; index --) {
				tree[index] += tree[left(index)] + tree[right(index)];
			}
        } catch (Exception e) {}
	}

	public static void treeLeafFill (String line) {
		sequence = line;
		int length = sequence.length();
		int start = 0;
		int end = Math.min(start+12, length);
		int depth = end-1;  // depth start from 0
		int index = getFirstPatternIndex(end);
		tree[index] ++;
		for (start = 1; start < length; start ++) {
			end = Math.min(start+12, length);
			index = cutRoot(index, depth, sequence.charAt(start-1));
			if (end - start == 12) {
				index = addLeaf(index, sequence.charAt(end-1));
			}
			else if (line.length() < 12) {
				depth --;
			}
			else {
				break;
			}
			tree[index] ++;
		}
	}

	public static int getFirstPatternIndex (int len) {
		int index;
		if (sequence.charAt(0) == '0')
			index = 0;
		else
			index = 1;
		for (int i = 1; i < len; i ++) {
			if (sequence.charAt(i) == '0')
				index = left(index);
			else
				index = right(index);
		}
		return index;
	}
/*0
  /\
  0 1*/
	public static int left (int index) {
		return 2*index+2;
		//return (index+1) << 1;
	}

	public static int right (int index) {
		return 2*index+3;
		//return (index+1) << 1 + 1;
	}

	/*001(4) -> 01(2)
	  4 - power[1] = 2 */
	/*100(11) -> 00(1)
	  11 - power[] = 1 */
	public static int cutRoot (int index, int depth, char root) {
		if (root == '1')
			depth ++;
		return index - power_two[depth];
	}

	public static int addLeaf (int index, char leaf) {
		if (leaf == '0')
			return left(index);
		else 
			return right(index);
	}

	public static void output () {
		int cnt = 1;
		try {
            out = new PrintWriter(new BufferedWriter(new FileWriter("contact.out")));
        } catch (Exception ee) {}
        int[] tmp = new int[8193];
        int aa = (int)Math.pow(2, a) - 2;
        int bb = (int)Math.pow(2, b+1) - 3;
        for (int i = bb; i >= aa; i --) {
        	tmp[i] = tree[i];
        }
        quicksort(tmp, aa, bb);
        frequency.add(tmp[bb]);
        for (int i = bb-1; i >= aa; i --) {
        	if (i > 0 && tmp[i] != tmp[i-1]) {
        		frequency.add(tmp[i]);
        		cnt ++;
        		if (cnt >= n)
        			break;
        	}
        }
        for (int j = 0; j < frequency.size(); j ++) {
        	out.println(frequency.get(j));
        	ArrayList<String> result = new ArrayList<String>();
        	for (int k = bb; k >= aa; k --) {
        		if (tree[k] == frequency.get(j)) {
        			result.add(flip(fillSubsequence(getDepth(k), k)));
        		}
        	}
        	if (result.size() > 1) {
        		String str = "";
        	    int max_length = 0;
				for (int i = 0; i < result.size(); i ++) {
					if (max_length < result.get(i).length())
						max_length = result.get(i).length();
				}
				for (int len = 1; len <= max_length; len ++) {
					ArrayList<String> temp = new ArrayList<String>();
					for (int p = 0; p < result.size(); p ++) {
						if (result.get(p).length() == len)
							temp.add(result.get(p));
					}
					Collections.sort(temp);
					for (int k = 0; k < temp.size(); k ++) {
						str += temp.get(k);
						if ((k + 1) % 6 == 0 && k < temp.size() - 1)
							str += "\n";
						else
							str += " ";
					}
				}
				out.println(str.substring(0, str.length()-1));
			}
			else if (result.size() == 1){
				out.println(result.get(0));
			}
        }
        out.close();
	}

	public static String flip (String str) {
		String ss = "";
		for (int i = str.length()-1; i >= 0; i --) {
			ss += str.charAt(i);
		}	
		return ss;
	}

	public static int getDepth (int index) {
		int depth = 0;
		if (index == 0 || index == 1) // 2
			depth = 0;
		if (index >= 2 && index <= 5) // 4 
			depth = 1;
		if (index >= 6 && index <= 13) // 8
			depth = 2;
		if (index >= 14 && index <= 29) // 16
			depth = 3;
		if (index >= 30 && index <= 61) // 32 
			depth = 4;
		if (index >= 62 && index <= 125) // 64
			depth = 5;
		if (index >= 126 && index <= 253) // 128
			depth = 6;
		if (index >= 254 && index <= 509) //256 
			depth = 7;
		if (index >= 510 && index <= 1021) // 512
			depth = 8;
		if (index >= 1024 && index <= 2045) // 1024  
			depth = 9;
		if (index >= 2046 && index <= 4093) // 2048	
			depth = 10;
		if (index >= 4094 && index <= 8189) // 4096
		 	depth = 11;
		return depth;
	}

	/* start from single 0 */
	public static String fillSubsequence (int depth, int i) {
		int curr_depth = depth+1;
		String sub = "";
		int curr_level = (int)Math.pow(2, curr_depth);
		int upper_total = 0;
		for (int k = curr_depth-1; k >= 0; k --) {
			upper_total += (int)Math.pow(2, k+1);
		}
		int order = i - upper_total;  // start from 0
		int upper = order;
		while (curr_depth > 0) {
			if (upper%2 == 0) {
				sub += "0";
			}
			else {
				sub += "1";
			}
			upper = (upper-1)/2;
			curr_depth --;
		}
		//System.out.println("DEPTH: " + depth + " INDEX: " + i + " SEQUENCE: " + sub + " FREQUENCY: " + tree[i]);
		return sub;
	}

	public static void quicksort (int[] arr, int start, int end) {
		int mid = arr[start];
		int left = start;
		int right = end;
		int temp;
		while (left <= right) {
			while (arr[left] < mid)
				left ++;
			while (arr[right] > mid)
				right --;
			if (left <= right) {
				temp = arr[right];
				arr[right] = arr[left];
				arr[left] = temp;
				left ++;
				right --;
			}
		}
		if (start < right) 
			quicksort(arr, start, right);
		if (left < end) 
			quicksort(arr, left, end);
	}
}