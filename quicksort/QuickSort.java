import java.util.*;

public class QuickSort {
	static int arr[];
	public static void quicksort (int start, int end) {
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
			quicksort(start, right);
		if (left < end) 
			quicksort(left, end);
	}
	public static void main (String args[]) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		arr = new int[n];
		for (int i = 0; i < n; i ++) {
			arr[i] = scan.nextInt();
		}
		quicksort(0,n-1);
		for (int i = 0; i < n; i ++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
}