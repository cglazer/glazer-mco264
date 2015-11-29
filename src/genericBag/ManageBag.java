package genericBag;

import medicines.NotFoundException;
import generic.Book;

public class ManageBag {
	public static void main(String[] args) {
		Bag<Book> myBag = new Bag<Book>(5);
		
		System.out.println("Bag Empty: " + myBag.isEmpty());
		System.out.println("Bag Full: " + myBag.isFull());
		
		Book one = new Book("Curious George", 3.99);
		Book two = new Book("Dr. Seuss", 5.99);
		Book three= new Book("Amelia Bedilia", 10.95);
		try {
			myBag.dropInto(two);
			System.out.println("\nAdded " + two.getTitle());
			myBag.dropInto(one);
			System.out.println("Added " + one.getTitle());
			myBag.dropInto(three);
			System.out.println("Added " + three.getTitle());
		} catch (AlreadyFullException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("\nBag Empty: " + myBag.isEmpty());
		System.out.println("Bag Full: " + myBag.isFull());
		
		try {
			myBag.dropInto(two);
			System.out.println("\nAdded " +two.getTitle());
			myBag.dropInto(three);
			System.out.println("Added " + three.getTitle());
		} catch (AlreadyFullException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println("\nBag Empty: " + myBag.isEmpty());
		System.out.println("Bag Full: " + myBag.isFull());
		
		
		try {
			myBag.dropInto(one);
			System.out.println("\nAdded " +one.getTitle());
		} catch (AlreadyFullException e) {
			// TODO Auto-generated catch block
			System.out.println("\nTried to add a book");
			System.out.println("Sorry. There's no more room to add books.");
		}
		
		try {
			myBag.takeOut(two);
			System.out.println("\nRemoved " +two.getTitle());
		} catch (NotFoundException e2) {
			// TODO Auto-generated catch block
			System.out.println("Sorry. This book is not in your bag.");
		}
		
		
		try {
			System.out.println("Took out " + myBag.takeOut().getTitle());
		} catch (EmptyBagException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			myBag.dropInto(one);
			System.out.println("Added " +one.getTitle());
			myBag.dropInto(one);
			System.out.println("Added " + one.getTitle());
		} catch (AlreadyFullException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Book four = new Book("Good Night", 2.50);
		
		try {
			myBag.takeOut(four);
			System.out.println("Took out " + four.getTitle());
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("\nTried to remove " +four.getTitle());
			System.out.println("Sorry. This book is not in your bag.");
		}
		
		}
	}

