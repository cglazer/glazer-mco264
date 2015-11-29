package recursion;

import java.util.Arrays;
import java.util.Random;

public class BinarySearch {
	private int[] numbers;

	public BinarySearch(int size) {
		numbers = new int[size];
		Random gen = new Random();

		for (int i = 0; i < size; i++) {
			numbers[i] = gen.nextInt(10);
		}
		Arrays.sort(numbers);
	}

	public String toString() {
		StringBuffer info = new StringBuffer();
		for (int i = 0; i < numbers.length; i++) {
			info.append(numbers[i]);
			info.append(" ");
		}
		return info.toString();
	}

	public int search(int value) {
		return BinSearch(value, 0, numbers.length - 1);
	}

	private int BinSearch(int value, int start, int end) {
		if (end < start) {// no list //anchor
			return -1;
		}
		int mid = (start + end) / 2;
		if (numbers[mid] == value) {
			return mid;
		} else if (numbers[mid] > value) {
			return BinSearch(value, start, mid - 1);
		} else {
			return BinSearch(value, mid + 1, end);
		}
	}

	public static void main(String[] args) {
		BinarySearch bin = new BinarySearch(4);
		int index = bin.search(4);
		System.out.println(index > 0 ? index : "not found");
	}
}
