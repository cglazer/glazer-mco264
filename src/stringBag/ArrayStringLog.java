package stringBag;

import java.util.Random;

public class ArrayStringLog implements StringLogInterface {
	protected String name;
	protected String[] log;
	protected int lastIndex = -1;

	public ArrayStringLog(String name, int maxSize) throws InvalidDataException {
		if (maxSize < 1) {
			throw new InvalidDataException();
		}
		this.log = new String[maxSize];
		this.name = name;
	}

	public ArrayStringLog(String name) {
		this.log = new String[100];
		this.name = name;
	}

	public void insert(String element) throws InvalidDataException {
		if (isFull()) {
			throw new InvalidDataException();
		}
		this.lastIndex++;
		this.log[lastIndex] = element;
	}

	public boolean isFull() {
		if (this.lastIndex == (this.log.length - 1)) {
			return true;
		} else {
			return false;
		}
	}

	public int size() {
		return (this.lastIndex + 1);
	}

	public boolean isEmpty() {
		if (size() == 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean contains(String element) {
		int location = 0;
		while (location <= this.lastIndex) {
			if (element.equalsIgnoreCase(this.log[location])) {
				return true;
			} else {
				location++;
			}
		}
		return false;
	}

	public String remove() {
		Random anyNum = new Random();
		int remove = anyNum.nextInt(size());
		String removeName = log[remove];
		for (int i = remove; i < this.lastIndex; i++) {
			log[i] = log[i + 1];
		}
		this.lastIndex--;

		return removeName;
	}

	public void clear() {
		for (int i = 0; i <= this.lastIndex; i++) {
			this.log[i] = null;
		}
		this.lastIndex = -1;
	}

	public String getName() {
		return this.name;
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("Log: ");
		buffer.append(this.name);
		buffer.append("\n\n");
		for (int i = 0; i <= lastIndex; i++) {
			buffer.append(i + 1);
			buffer.append(". ");
			buffer.append(log[i]);
			buffer.append("\n");
		}
		return buffer.toString();
	}
}
