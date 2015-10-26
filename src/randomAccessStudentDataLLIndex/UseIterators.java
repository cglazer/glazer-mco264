package randomAccessStudentDataLLIndex;

//tester class
public class UseIterators {
	public static void main(String[] args) {
		LinkedList<String> names = new LinkedList<String>();
		names.add("Libby");
		names.add("Rena");
		names.add("Ahuva");

		// this is the internal iterator
		names.iter.reset();
		while (names.iter.hasNext()) {
			System.out.println(names.iter.next());
		}

	}
}
