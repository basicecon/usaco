import java.util.*;

public class QuickSort_partition {
	static int arr[];
	public static void partition (int index, int n) {
		int new_arr[] = new int[n];
		int j = 0;
		for (int i = 0; i < n; i ++) {
			if (arr[i] < arr[index]) {
				new_arr[j] = arr[i];
				j ++;
			}
		} 
		new_arr[j] = arr[index];
		j ++;
		for (int i = 0; i < n; i ++) {
			if (arr[i] > arr[index]) {
				new_arr[j] = arr[i];
				j ++;
			}
		}

		for (int i = 0; i < j; i ++) 
			System.out.print(new_arr[i] + " ");
		System.out.println();
	}
	public static void main (String args[]) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		arr = new int[n];
		for (int i = 0; i < n; i ++) {
			arr[i] = scan.nextInt();
		}
		partition(0, n);
	}
}