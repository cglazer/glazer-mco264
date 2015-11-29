package decimalToBinary;

import java.util.Scanner;

public class ManageDecimalToBinary {
	public static void main(String args[]) {
		DecimalToBinary binary = new DecimalToBinary();
		Scanner input = new Scanner(System.in);
		String convertAgain=null;
		do{
		System.out.println("What number would you like to convert to binary?");
		int number = input.nextInt();
		System.out.println(binary.toBinary(number));
		System.out.println("Would you like to convert another number?");
		convertAgain= input.next();
		input.nextLine();
		}while(convertAgain.toUpperCase().charAt(0) == 'Y');
		input.close();
	}
}
