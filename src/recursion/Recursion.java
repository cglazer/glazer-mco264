package recursion;

public class Recursion {
	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			System.out.println("Recursion is fun!");
		}
		System.out.println("\nAnd now for recursion!\n");
		RecurMessage(5);
	}

	public static void RecurMessage(int num) {
		if (num == 0) {
			return;
		}
		System.out.println("Recursion is fun!");
		RecurMessage(num - 1);
	}
}
