package recursion;

public class Sum {
	int sum = 0;

	public int Sum(int[] numbers) {
		// /assume array is filled to capacity
		for (int i = 0; i < numbers.length; i++) {
			sum += numbers[i];
		}
		return sum;
	}

	public int RecurSum(int[] numbers) {
		return RecurSum(numbers, 0);
	}

	private int RecurSum(int[] numbers, int iterator) {
		if (numbers.length ==0) {
			return 0;
		} else if (iterator==numbers.length-1) {
			return numbers[iterator];
		} else {
			return numbers[iterator] + RecurSum(numbers, iterator+1);
		}
	}

	public static void main(String[] args) {
		int[] numbers = { 4, 5, 8 };
		Sum sum = new Sum();
		System.out.println(sum.Sum(numbers));
		System.out.println(sum.RecurSum(numbers));
	}
}
