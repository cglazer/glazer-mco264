package fileNameRecursion;

import java.io.File;
import java.util.Scanner;

public class FileRecursion {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out
				.println("Enter your folder name and address with no spaces. For example 'C:\\Users\\Chaya Glazer\\Documents'.");
		String fileName = input.nextLine();
		File f = new File(fileName);
		if (!f.exists()) {
			do {
				System.out
						.println("Sorry, your file was not found. Please reenter your folder name and address with no spaces. For example 'C:\\Users\\Chaya Glazer\\Documents'.");
				fileName = input.nextLine();
				f = new File(fileName);
			} while (!f.exists());
		}
		FindFiles find = new FindFiles();
		StringBuilder builder = new StringBuilder();
		// pass in the file, 0 so that it can be incremented and the format can
		// be layed out accordingly with tabs for inner files and folders and a
		// string builder to build as it recursively goes through the files and
		// folders.
		System.out.println(find.recurFile(f, 0, builder));
		input.close();
	}

}