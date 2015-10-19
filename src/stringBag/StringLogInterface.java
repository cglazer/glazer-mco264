package stringBag;

public interface StringLogInterface {
	void insert(String emelemt) throws InvalidDataException;

	boolean isFull();

	int size();

	boolean contains(String element);

	void clear();

	String getName();

	String toString();

	boolean isEmpty();

	String remove();
}
