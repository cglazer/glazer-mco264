package doubleLinkedList;

import randomAccessExceptions.NotFoundException;

public class LinkedListMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello");
		
		DoubleLinkedList<Integer> list = new DoubleLinkedList<Integer>();
		list.add(2);
		list.add(3);
		list.add(1);
		list.add(4);
		ReverseIterator<?> iterator = list.externalIterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	try {
		System.out.println();
		list.remove(3);
		System.out.println(" 3 was removed\n");
	} catch (NotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	iterator = list.externalIterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		
		try {
			System.out.println();
			list.remove(4);
			System.out.println(" 4 was removed\n");
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		iterator = list.externalIterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		System.out.println("done");
		
	}

}
