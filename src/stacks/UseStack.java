package stacks;

import java.util.Scanner;

public class UseStack {

	public static void main(String[] args) {
		StackArray<String> citiesVisited = new StackArray<String>();
		StackLinkedList<String> linkl = new StackLinkedList();
		String aCity;
		Scanner console = new Scanner(System.in);
		try {
			System.out
					.println("Enter next city on your route from origin to destination "
							+ "Enter ctrl+z to end input");

			while (console.hasNext()) {
				linkl.push(console.next());
				citiesVisited.push(console.next());
				System.out
						.println("Enter next city on your route from origin to destination");

			}
		} catch (FullStackException e) {
			System.out.println("can't store any more data, stack is full");
		}

		// now start backtracking.....
		System.out.println("To travel back from " + citiesVisited.peek()
				+ " follow this route");
		while (!citiesVisited.isEmpty()) {
			System.out.println(" -> " + citiesVisited.peek());
			citiesVisited.pop();
		}
		System.out.println("To travel back from " + linkl.peek()
				+ " follow this route");
		while (!linkl.isEmpty()) {
			System.out.println(" -> " + linkl.peek());
			linkl.pop();
		}
		System.out.println("reached origin");
		console.close();
	}

}
