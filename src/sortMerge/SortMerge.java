package sortMerge;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class SortMerge {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("How many numbers would you like to generate?");
		int numbers = input.nextInt();
		while (numbers < 20) {
			System.out
					.println("Error! Please choose at least 20 numbers. How many numbers would you like to generate?");
			numbers = input.nextInt();
		}
		input.nextLine();
		String filename;
		System.out
				.println("Enter the file name where you'd like to store the generated values: ");
		filename = input.nextLine();
		System.out
				.println("Where would you like to store the sorted values? Please enter a file name.");
		String sortedfilename = input.nextLine();
		while (sortedfilename.equalsIgnoreCase(filename)) {
			System.out
					.println("Please choose a different file name. Where would you like to store the sorted values?");
			sortedfilename = input.nextLine();
		}
		try {
			new SortMerge(numbers, sortedfilename, filename);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		input.close();
	}

	public SortMerge(int numbers, String sortedfilename, String filename)
			throws IOException {
		PrintWriter outputFile = new PrintWriter(filename);
		// generate random numbers
		Random random = new Random();
		for (int i = 0; i < numbers; i++) {
			int num = random.nextInt(900) + 1;
			outputFile.print(num + " ");
		}
		// close the file with all numbers unsorted
		outputFile.close();
		// see how many files the numbers should be split to. At most 10 numbers
		// per file
		int numFiles = (int) numbers / 10;
		if (numbers % 10 > 0) {
			numFiles++;
		}
		// create an array of files to hold the sorted sections
		File[] files = new File[numFiles];
		for (int i = 0; i < files.length; i++) {
			files[i] = new File("Sorted" + (i + 1) + ".txt");
		}

		Scanner read = new Scanner(new File(filename));
		int filenum = 0;
		PrintWriter newfile = new PrintWriter(files[filenum]);
		// keep count of what value were up to
		int count = 0;
		ArrayList<Integer> temporaryArray = new ArrayList<Integer>();
		while (read.hasNext()) {
			if (count == 10) {
				// sort an array of values
				sort(temporaryArray, 0, temporaryArray.size() - 1);
				// the first value in each file should be the size
				newfile.println(count);
				for (int i = 0; i < temporaryArray.size(); i++) {
					newfile.println(temporaryArray.get(i));
				}
				// close each printWriter as its done
				newfile.close();
				filenum++;
				newfile = new PrintWriter(files[filenum]);
				count = 0;
				// clear the arrayList
				temporaryArray = new ArrayList<Integer>();
			}
			temporaryArray.add(read.nextInt());
			count++;
		}
		sort(temporaryArray, 0, temporaryArray.size() - 1);
		newfile.println(count);
		for (int i = 0; i < temporaryArray.size(); i++) {
			newfile.println(temporaryArray.get(i));
		}
		newfile.close();
		read.close();
		PrintWriter write = new PrintWriter(sortedfilename);
		File temp = new File("temp.txt");
		PrintWriter tempwrite = new PrintWriter(temp);
		tempwrite.close();
		for (int y = 0; y < numFiles; y++) {
			temp = merge(temp, files[y]);
		}
		Scanner readTemp = new Scanner(temp);
		readTemp.next();// garbage-size
		while (readTemp.hasNext()) {
			write.print(readTemp.nextInt() + " ");
		}
		write.close();
		readTemp.close();
	}

	public void sort(ArrayList<Integer> items, int first, int last) {
		int pos;
		if (first < last) {
			// list still has elements in it
			pos = split(items, first, last);
			// sort the left sublist
			sort(items, first, pos - 1);
			// sort the right sublist
			sort(items, pos + 1, last);
		}
	}

	public int split(ArrayList<Integer> items, int first, int last) {
		int pos;
		int left = first;
		int right = last;
		// choose an arbitrary pivot value
		Integer pivot = items.get(left);

		Integer temp;

		while (left < right) {
			// items remain in the list
			// search from right to find elements that are <= pivot and should
			// therefore be swapped over to the set on the left
			while (items.get(right).compareTo(pivot) > 0) {
				right--; // it is in correct place, continue search
			}
			// search from left for element that > pivot
			while (left < right && items.get(left).compareTo(pivot) <= 0)
				left++; // nothing to do, bec. values is in correct position so
						// far
			if (left < right) {
				// found two values that should be swapped, swap the references
				temp = items.get(left);

				items.set(left, items.get(right));
				items.set(right, temp);

			}
		}
		// now place the pivot in between the two lists
		pos = right;
		items.set(first, items.get(pos));
		items.set(first, items.get(pos));
		items.set(pos, pivot);
		return pos;

	}

	private File merge(File file1, File file2) throws IOException {
		int size1 = -1, size2 = -1;
		int count1 = 0, count2 = 0;
		int index1 = 0, index2 = 0;
		@SuppressWarnings("resource")
		Scanner readone = new Scanner(file1);
		if (readone.hasNext()) {
			size1 = readone.nextInt();
			if (readone.hasNext()) {
				index1 = readone.nextInt();
				count1++;
			}
		}
		PrintWriter tempwrite = new PrintWriter("f.txt");
		Scanner read2 = new Scanner(file2);
		if (read2.hasNext()) {
			size2 = read2.nextInt();
			if (read2.hasNext()) {
				index2 = read2.nextInt();
				count2++;
			}
		}
		int size;
		if (size1 == -1) {
			size = size2;
		} else {
			size = size1 + size2;
		}

		tempwrite.println(size);
		while (count1 <= size1 && count2 <= size2) {
			if (index1 <= index2) {
				tempwrite.println(index1);
				if (count1 < size1) {
					index1 = readone.nextInt();
				}
				count1++;
			} else {
				tempwrite.println(index2);
				if (count2 < size2) {
					index2 = read2.nextInt();
				}
				count2++;
			}
		}
		if (count1 <= size1) {
			while (count1 <= size1) {
				tempwrite.println(index1);
				if (count1 < size1) {
					index1 = readone.nextInt();
				}
				count1++;
			}
		} else {
			while (count2 <= size2) {
				tempwrite.println(index2);
				if (count2 < size2) {
					index2 = read2.nextInt();
				}
				count2++;
			}
		}
		read2.close();
		tempwrite.flush();
		tempwrite.close();
		File f = new File("f.txt");
		return f;
	}
}
