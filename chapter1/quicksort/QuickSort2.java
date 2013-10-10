import java.util.*;

public class QuickSort2 {
	static int arr[];
	public static void partition (int start, int end) {
		int new_arr[] = new int[arr.length];
		int index = start;
		for (int i = start; i <= end; i ++) {
			if (arr[i] < arr[start]) 
				new_arr[index++] = arr[i];
		}
		int mid = index;
		new_arr[index++] = arr[start];
		for (int i = start; i <= end; i ++) {
			if (arr[i] > arr[start]) 
				new_arr[index++] = arr[i];
		}
		for (int i = start; i <= end; i ++) 
			arr[i] = new_arr[i];
		if (mid-1 > start)
			partition(start, mid-1);
		if (mid+1 < end)
			partition(mid+1, end);
		for (int i = start; i <= end; i ++) 
			System.out.print(arr[i] + " ");
		System.out.println();
	}
	public static void main (String args[]) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		arr = new int[n];
		for (int i = 0; i < n; i ++) {
			arr[i] = scan.nextInt();
		}
		partition(0, n-1);
	}
}