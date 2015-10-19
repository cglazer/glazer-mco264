package stringBag;

import java.util.Scanner;

public class UseStringLog {
	public static void main(String args[]) {
		StringLogInterface names = null;
		try {
			names = new ArrayStringLog("Names", 5);
			System.out.println("A string bag was created!\n");
		} catch (InvalidDataException e1) {
			System.out.println("Invalid size.");
			System.exit(1);
		}
		try {
			names.insert("Jeannine");
			System.out.println("Jeannine was added");
		} catch (InvalidDataException e) {
			// TODO Auto-generated catch block
			System.out.println("Insufficient space.");
		}
		try {
			names.insert("Dora");
			System.out.println("Dora was added");
		} catch (InvalidDataException e) {
			// TODO Auto-generated catch block
			System.out.println("Insufficient space.");
		}
		try {
			names.insert("Dora");
			System.out.println("Dora was added");
		} catch (InvalidDataException e) {
			// TODO Auto-generated catch block
			System.out.println("Insufficient space.");
		}
		try {
			names.insert("Jo-Anne");
			System.out.println("Jo-Anne was added");
		} catch (InvalidDataException e) {
			// TODO Auto-generated catch block
			System.out.println("Insufficient space.");
		}

		System.out.println("\nSize= " + names.size());
		System.out.println(names);
		System.out.println("Contains 'Dora': " + names.contains("Dora"));
		System.out.println("String Bag Full: " + names.isFull());
		while (!names.isEmpty()) {
			System.out.println(names.remove() + " is being removed.");
		}
		System.out.println("Size= " + names.size());

		try {
			names.insert("Haily");
			System.out.println("Haily was added");
		} catch (InvalidDataException e) {
			// TODO Auto-generated catch block
			System.out.println("Insufficient space.");
		}
		System.out.println("Size= " + names.size());
		names.clear();
		System.out.println("Bag was cleared");

		System.out.println(names);
		System.out.println("Contains 'Dora': " + names.contains("Dora"));
		Scanner inputText = new Scanner(System.in);
		String n;
		int choice = menu();
		while (choice >= 1 && choice <= 8) {
			switch (choice) {
			case 1:
				System.out.println(names.getName());
				break;
			case 2:
				System.out.println(names.size());
				break;
			case 3:
				System.out.println("What name would you like to add?");
				n = inputText.next();
				try {
					names.insert(n);
					System.out.println(n + " was added");
				} catch (InvalidDataException e) {
					// TODO Auto-generated catch block
					System.out.println("Insufficient space.");
				}
				break;
			case 4:
				System.out.println("What name would you like to check?");
				n = inputText.next();
				System.out.println(names.contains(n));
				break;
			case 5:
				System.out.println(names.isFull());
				break;
			case 6:
				System.out.println(names.remove() + " was removed.");
				break;
			case 7:
				names.clear();
				break;
			case 8:
				System.out.println(names.toString());
				break;
			}
			choice = menu();
		}
		inputText.close();
	}

	public static int menu() {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		int choice;
		do {
			System.out.println("1. Get log name." + "\n2. Get log size."
					+ "\n3. Add a name to the bag."
					+ "\n4. Check if the log contains a specific name."
					+ "\n5. Check if the bag is full."
					+ "\n6. Remove a random name from the bag."
					+ "\n7. Clear the bag."
					+ "\n8. Print all contents of the bag." + "\n0. Exit");
			choice = input.nextInt();
		} while (choice < 0 || choice > 8);
		return choice;
	}

}
