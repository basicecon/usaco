/*
ID: imdoing1
LANG: JAVA
PROG: milk
*/
import java.util.*;
import java.io.*;

class milk {
	static sellers individual[];
	static int price_arr[];
	static int amount_arr[];
	static int n;
	public static class sellers {
		int amount;
		int price;	
		public sellers (int milk_price, int milk_amount) {
			price = milk_price;
			amount = milk_amount;
		}
	}

	public static void main (String args[]) {
		Scanner scan = null;
        try {
            scan = new Scanner(new BufferedReader(new FileReader("milk.in")));
        } catch (Exception e) {}
        PrintWriter out = null;
        try {
        	out = new PrintWriter(new BufferedWriter(new FileWriter("milk.out")));
        } catch (Exception ee) {};
        int total_milk = scan.nextInt();
        n = scan.nextInt();
        if (total_milk == 0 || n == 0) {
        	out.println(0);
        	out.close();
        	return;
        }
        individual = new sellers[n];
        amount_arr = new int[n]; 
        price_arr = new int[n];
		for (int i = 0; i < n; i ++) {
			int tmp_price = scan.nextInt();
			int tmp_amount = scan.nextInt();
			//System.out.println("====" + tmp_price + " " + tmp_amount + "====");		
			individual[i] = new sellers(tmp_price, tmp_amount);
			price_arr[i] = individual[i].price;
			amount_arr[i] = individual[i].amount;
		} 

		/*
		for (int i = 0; i < n; i ++) {
			System.out.println(individual[i].price + " " + individual[i].amount);
		}*/
		quicksort(0, n-1);
		/*
		for (int i = 0; i < n; i ++) {
			System.out.println(price_arr[i] + " " + amount_arr[i]);
		}*/
		int total = 0;
		int money = 0;
		//System.out.println(total_milk);
		for (int i = 0; i < n; i ++) {
			total += amount_arr[i];
			money += price_arr[i] * amount_arr[i];
			//System.out.println(total + " " + money);
			if (total > total_milk) {
				//System.out.println("=====");
				money -= (total - total_milk) * price_arr[i];
				//System.out.println(money);
				break;
			}
		}
		//System.out.println(money);
        out.println(money);
        out.close();
	}

	public static void quicksort (int start, int end) {
		int left = start;
		int right = end;
		int tmp; //seller
		int mid = price_arr[start];
		while (left <= right) {
			while (price_arr[left] < mid)	
				left ++;
			while (price_arr[right] > mid)
				right --;
			if (left <= right) {
				tmp = price_arr[left];
				price_arr[left] = price_arr[right];
				price_arr[right] = tmp;
				tmp = amount_arr[left];
				amount_arr[left] = amount_arr[right];
				amount_arr[right] = tmp;

				left ++;
				right --;
			}
		}
		if (start < right)
			quicksort(start, right);
		if (left < end)
			quicksort(left, end);
	}
}