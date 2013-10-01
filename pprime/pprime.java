/*
ID: imdoing1
LANG: JAVA
PROG: pprime
*/
import java.util.*;
import java.io.*;

class pprime {
	//0, 1, 2, 3, 4, 5, 6, 7, 8, 9
	//00,11,22,33,44,55,66,77,88,99
	static String[] start;
	static ArrayList<String> pp = new ArrayList<String>();
	static int[] numbers;
	static int a;
	static int b;

	static boolean[] primes = new boolean[3000000]; 
	static int[] pure_primes;
	//set up the primesieve
	public static void fillSieve() {
	    Arrays.fill(primes,true);        // assume all integers are prime.
	    primes[0]=primes[1]=false;       // we know 0 and 1 are not prime.
	    int num = 0;
	    for (int i=2;i<primes.length;i++) {
	        //if the number is prime, 
	        //then go through all its multiples and make their values false.
	        if(primes[i]) {
	            for (int j=2;i*j<primes.length;j++) {
	                primes[i*j]=false;
	            }
	            num ++;
	        }
	    }
	    pure_primes = new int[num];
	    int j = 0;
	    for (int i=2;i<primes.length;i++) {
	        if(primes[i]) {
	        	pure_primes[j] = i;
	        	j ++;  
	        }
	    }
	}
	
	public static void main(String args[]) {
		fillSieve();
		start = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
				"00","11","22","33","44","55","66","77","88","99"};	
		Scanner scan = null;	
		try {
			scan = new Scanner(new BufferedReader(new FileReader("pprime.in")));
		} catch (Exception e) {}
		PrintWriter out = null;
       	try {
       		out = new PrintWriter(new BufferedWriter(new FileWriter("pprime.out")));
      	} catch (Exception ee) {};
      	a = scan.nextInt();
      	b = scan.nextInt();
		for(int i = 0; i < 20; i ++) {
			findPals(start[i]);
		}
		sort();
		for(int i = 0; i < pp.size(); i ++) {
			out.println(numbers[i]);
		}
		out.close();
	}

	public static void findPals(String str) { //dfs
/*		if(isInRange(str)) {
			if(isPrime(str))
				store(str);
		}
		else
			return;
*/	
		if(isNumber(str))
			if(isInRange(str))
				if(isPrime(str))
					store(str);

		for (int i = 0; i < 10; i ++) {
			String tmp = i + str + i;
			String bstr = Integer.toString((int)b);
			if (tmp.length() <= bstr.length() && tmp.length() <= 7)
				findPals(tmp);
		}
	}

	public static boolean isPrime(String str) {
		int number = Integer.parseInt(str);
		if (number < primes.length)
			return primes[number];
		if(Integer.parseInt(str) == 1)
			return false;
		for(int i = 0; pure_primes[i]*pure_primes[i] <= number; i++) {
			if(number%pure_primes[i] == 0)
				return false;
		}
		return true;
	}

	public static boolean isNumber(String str) {
		if(str.substring(0,1) == "0") //leading 0
			return false;
		else
			return true; 
	}

	public static void store(String str) {
		pp.add(str);
	}

	public static boolean isInRange(String str) {
		int number = Integer.parseInt(str);
		if(number >= a && number <= b)
			return true;
		else
			return false;
	}

	public static void sort() {
		numbers = new int[pp.size()];
		for (int i = 0; i < pp.size(); i ++) {
			String tmp = pp.get(i);
			numbers[i] = Integer.parseInt(tmp);
		}
		quicksort(0, pp.size()-1);
	}

	public static void quicksort(int start, int end) {
		int left = start;
		int right = end;
		//int mid = left;
		int mid = numbers[start];
		while(left <= right) {
			while(numbers[left] < mid)
				left ++;
			while(numbers[right] > mid)
				right --;
			if(left <= right) {
				int tmp = numbers[left];
				numbers[left] = numbers[right];
				numbers[right] = tmp;
				left ++;
				right --;
			}
		}
		if(start < right)
			quicksort(start, right);
		if(left < end)
			quicksort(left, end);
	}
}