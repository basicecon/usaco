import java.util.*;
import java.io.*;
import java.lang.Math;

class contact_hash {
	static int a;
	static int b;
	static int n;
	static String sequence;
	static Dictionary pattern_hash = new Hashtable();
	static ArrayList pattern_list = new ArrayList<pattern>();
	public static void main (String args[]) {
		read();
		fillHash();
		output();
	}

	public static void read () {
		try {
			BufferedReader br = new BufferedReader(new FileReader("contact.in"));
			StringTokenizer st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			n = Integer.parseInt(st.nextToken());

			StringBuffer sb = new StringBuffer();
			String line = br.readLine();
			while (line != null) {
				sb.append(line);
				line = br.readLine();
			}
			sequence = sb.toString();
		} catch (Exception e) {}
	}

	public static void fillHash () {
		int length = sequence.length();
		for (int start = 0; start < length; start ++) {
			for (int len = a; len <= b; len ++) {
				if (start + len <= length) {
					String sub = sequence.substring(start, start+len);
					pattern pat = (pattern)pattern_hash.get(sub);
					if (pat == null) {
						pat = new pattern(sub);
						pat.frequency = 1;
						pattern_hash.put(sub, pat); // put(key, value)
						pattern_list.add(pat);
					}
					else {
						pat.frequency ++;
					}
				}
			}
		}
		Collections.sort(pattern_list);
	}

	public static void output () {
		PrintWriter out = null;
		try {
            out = new PrintWriter(new BufferedWriter(new FileWriter("contact.out")));
        } catch (Exception ee) {}
		Iterator it = pattern_list.iterator();
		pattern pat = (pattern)it.next();
		int frequency = -1;
		int cnt = 0;
		while (n > 0 && pat != null) {
			frequency = pat.frequency;
			out.println(frequency);
			n --;
			out.print(pat.sequence);
			cnt = 1;
			if (it.hasNext())
				pat = (pattern)it.next();
			else
				pat = null;
			while (pat != null && pat.frequency == frequency) {
				if (cnt % 6 == 0) {
					out.println();
					out.print(pat.sequence);
				}
				else {
					out.print(" " + pat.sequence);
				}
				cnt ++;
				pat = (pattern)it.next();
			}
			out.println();
		}
		out.close();
	}

	static class pattern implements Comparable {
		String sequence;
		int frequency;
		public pattern (String sequence) {
			this.sequence = sequence;
			this.frequency = 0;
		}

		public int compareTo (Object obj) {
			pattern pat = (pattern)obj;
			if (this.frequency > pat.frequency)
				return -1;
			if (this.frequency < pat.frequency)
				return 1;
			if (this.sequence.length() < this.sequence.length())
				return -1;
			if (this.sequence.length() > this.sequence.length())
				return 1;
			return this.sequence.compareTo(pat.sequence);
		}
	}
}