package genericBag;

import java.util.LinkedList;
import java.util.Random;

import medicines.NotFoundException;

public class Bag<T extends Comparable<T>> implements BagInterface<T> {
	private LinkedList<T> values;
	private int size;
	private int currentSize;

	public Bag(int size) {
		this.values = new LinkedList<T>();
		this.size = size;
		this.currentSize = 0;
	}

	@Override
	public void dropInto(T item) throws AlreadyFullException {
		if (!isFull()) {
			Random anyNum = new Random();
			int index = anyNum.nextInt(this.currentSize + 1);
			this.values.add(index, item);
			this.values.add(item);
			this.currentSize++;
		} else {
			throw new AlreadyFullException();
		}
	}

	@Override
	public T takeOut() throws EmptyBagException {
		if (!isEmpty()) {
			Random anyNum = new Random();
			int index = anyNum.nextInt(this.currentSize + 1);
			T item = this.values.remove(index);
			this.currentSize--;
			return item;
		} else {
			throw new EmptyBagException();
		}
	}

	@Override
	public T takeOut(T item) throws NotFoundException {
		if (values.contains(item)) {
			this.values.remove(item);
			this.currentSize--;
			return item;
		} else {
			throw new NotFoundException();
		}
	}

	@Override
	public boolean isEmpty() {
		if (this.currentSize == 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isFull() {
		if (this.currentSize >= this.size) {
			return true;
		}
		return false;
	}
}
