package lesson.leader;

import java.util.Comparator;

public class Sort {
	//冒泡排序
	public  static <T> void bubbleSort(T[] arr,
			int row ,int high,Comparator<T> c){
		for (int i = row; i <= high; i++) {
			for (int j = 1; j <= high-i; j++) {
				if (c.compare(arr[j], arr[j-1])==-1) {
					T tmp = arr[j];
					arr[j] = arr[j-1];
					arr[j-1] = tmp;
				}
			}
		}
	}
	//快速排序
	public static <T> void quickSort(T arr[],
			int left,int right,Comparator<T> c){
		if (left < right) {
			int pa = partition(arr, left, right,c);
			quickSort(arr, left, pa - 1,c);
			quickSort(arr, pa + 1, right,c);
		}
	}
	
	private static <T> int partition(T arr[],int left,int right,Comparator<T> c){
		T pivot = arr[left]; //使用 arr[left] 作为枢轴
		while (left < right) {
			while (left < right && c.compare(arr[right], pivot) != -1) 
				right--;
			arr[left] = arr[right];
			while (left < right && c.compare(arr[left],pivot) != 1)
				left++;
			arr[right] = arr[left];
		} 
		arr[left] = pivot;
		return left;
	}
	
}
