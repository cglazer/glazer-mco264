package fileNameRecursion;

import java.io.File;
import java.util.Scanner;

import medicines.NotFoundException;

public class FileRecursion {

	public static void main(String[] args) throws NotFoundException {
		Scanner input = new Scanner(System.in);
		System.out
				.println("Enter your folder name and address with no spaces. For example 'C:\\Users\\Chaya Glazer\\Documents'.");
		String fileName = input.nextLine();
		File f = new File(fileName);
		if (!f.exists()) {
			try {
				throw new NotFoundException();
			} catch (NotFoundException e) {
				System.out.println("Sorry. Your file was not found.");
			}
		}
		FindFiles find = new FindFiles();
		StringBuilder builder = new StringBuilder();
		System.out.println(find.recurFile(f, 0, builder));
		// "C:\\Users\\Chaya Glazer\\Documents\\Testing Folder"
		input.close();
	}

}