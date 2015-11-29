package genericBag;

import medicines.NotFoundException;

public interface BagInterface<T extends Comparable<T>> {
	public void dropInto(T item) throws AlreadyFullException;

	public T takeOut() throws EmptyBagException;

	public T takeOut(T item) throws NotFoundException;

	public boolean isEmpty();

	public boolean isFull();
}
