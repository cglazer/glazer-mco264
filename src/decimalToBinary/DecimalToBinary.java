package decimalToBinary;

import java.util.Iterator;
import java.util.Stack;

public class DecimalToBinary {
	private Stack<Integer> stack;

	public DecimalToBinary() {
		this.stack = new Stack<Integer>();
	}

	public String toBinary(int number) throws NullPointerException {
		while (number > 0) {
			stack.push(number % 2);
			number = (int) number / 2;
		}
		StringBuilder binaryNumber = new StringBuilder();
		Iterator<Integer> iter = stack.iterator();
		while (iter.hasNext()) {
			binaryNumber.append(stack.pop());
		}

		return binaryNumber.toString();
	}
}
