package generic;

public class GenericClass {

	public <T extends Comparable<T>> T max(T val1, T val2) {
		int result = val1.compareTo(val2);
		if (result > 0) {
			return val1;
		} else if (result < 0) {
			return val2;
		} else {
			return null;
		}
	}

	public static void main(String[] args) {
		String name1 = "Hello";
		String name2 = "GoodBye";
		GenericClass gen = new GenericClass();
		System.out.println(gen.max(name1, name2));

		Integer value1 = 15;
		Integer value2 = 25;
		System.out.println(gen.max(value1, value2));

		Book firstBook = new Book("Curious George", 13.99);
		Book secondBook = new Book("Amelia Bedilia", 6.00);

		System.out.println(gen.max(firstBook, secondBook));

	}
}
