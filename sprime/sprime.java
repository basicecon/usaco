/*
ID: imdoing1
LANG: JAVA
PROG: sprime
*/
import java.util.*;
import java.io.*;

class sprime {
	static ArrayList<Integer> allPrime = new ArrayList<Integer>();
	static int n;
	static int[] head;
	static int[] next;
	static int[] pure_prime;
	static int count;
	static boolean[] is_prime = new boolean[2000000];
	public static void main(String args[]) {
		head = new int[]{2, 3, 5, 7};
		next = new int[]{1, 3, 7, 9};
		fillPrime();
		/*for(int i = 0; i < 4; i ++) {
			dfs(head[i]);
		}*/
		//search();
		ArrayList<Integer> initial = new ArrayList<Integer>();
		initial.add(2);
		initial.add(3);
		initial.add(5);
		initial.add(7);
		int level = startPt();
		int j = 1;
		ArrayList<Integer> tmp = initial;
		while (j < level) {
			tmp = getLongerPrime(tmp);
			j ++;
		}

		PrintWriter out = null;
       	try {
       		out = new PrintWriter(new BufferedWriter(new FileWriter("sprime.out")));
      	} catch (Exception ee) {};

		for(int i = 0; i < tmp.size(); i ++)
			out.println(tmp.get(i));
		out.close();
	}

	//left boundary
	public static int startPt() {
		Scanner scan = null;	
		try {
			scan = new Scanner(new BufferedReader(new FileReader("sprime.in")));
		} catch (Exception e) {}
		n = scan.nextInt();
		return n;
	}

	public static ArrayList<Integer> getLongerPrime(ArrayList<Integer> short_prime) {
		ArrayList<Integer> long_prime = new ArrayList<Integer>();
		for (int x : short_prime) {
			for (int i = 0; i < 4; i ++) {
				int tmp = x * 10 + next[i];
				if (isPrime(tmp)) {
					long_prime.add(tmp);
				}
			}
		}
		return long_prime;
	}

/*
	public static void dfs(int number) {
		//check if in range, not return
		int start = startPt();
		if(number >= start*10)
			return;
		//check if prime, yes->store
		if(isSuperPrime(number))
			if(String.valueOf(number).length() == n)
				allPrime.add(number);
		//dfs
		for(int i = 0; i < 4; i ++) {
			int tmp = number*10 + next[i];
			dfs(tmp);
	t	}
	}
*/

	public static void search() {
		int start = startPt();
		for (int i = 0; i < 4; i ++) {
			for (int j = head[i]*start; j < (head[i]+1)*start; j ++) {
				if (isSuperPrime(j))
					allPrime.add(j);
			}
		}
	}

	public static boolean isPrime(int number) {
		/*
		if(number%2 == 0 || number%3 == 0)
			return false;
		for(int i = 5; i*i <= number; i+=2) {
			if(number%i == 0)
				return false;
		} 
		return true;
		*/
		if (number < is_prime.length)
			return is_prime[number];
		
		for(int i = 0; pure_prime[i]*pure_prime[i] <= number; i++) {
			if(number%pure_prime[i] == 0)
				return false;
		}
		return true;
	}

	public static boolean isSuperPrime(int number) {
		int len = String.valueOf(number).length();
		for(int i = 1; i < number/10; i *= 10) {
			int tmp = number/i;
			if(!isPrime(tmp))
				return false;
		}
		return true;
	}

	public static void fillPrime() {
		for (int i = 0; i < 1000000; i ++) {
			is_prime[i] = true;
		}
		is_prime[0] = false;
		is_prime[1] = false;
		count = 0;
		for (int i = 2; i < is_prime.length; i ++) {
			if(is_prime[i]) {
				for (int j = 2; i*j < is_prime.length; j ++) {
					is_prime[i*j] = false;
				}	
				count ++;
			}
		}
		pure_prime = new int[count];
		int cnt = 0;
		for (int i = 2; i < is_prime.length; i ++) {
			if (is_prime[i]) {
				pure_prime[cnt] = i;
				cnt ++;
			}
		}

	}
}