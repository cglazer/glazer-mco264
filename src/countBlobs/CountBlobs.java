package countBlobs;

import java.util.Scanner;

public class CountBlobs {
	public static void main(String[] args) {
		Grid grid = null;
		Scanner input = new Scanner(System.in);
		int percentage;
		final int rows = 10;
		final int columns = 40;
		do {
			System.out
					.println("What percentage of the grid would you like to have blobs(1-99)");
			percentage = input.nextInt();
		} while (percentage < 1 || percentage > 99);
		input.close();
		try {
			grid = new Grid(rows, columns, percentage);
		} catch (InvalidGridException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(grid.toString());
		int count = grid.blobCount();
		if (count == 1) {
			System.out.println("The grid has " + count + " Blob!");
		} else {
			System.out.println("The grid has " + count + " Blobs!");
		}
	}

}