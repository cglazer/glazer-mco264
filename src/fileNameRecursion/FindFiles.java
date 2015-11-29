package fileNameRecursion;

import java.io.File;

public class FindFiles {

	public String recurFile(File filename, int number, StringBuilder builder) {
		for (File file : filename.listFiles()) {
			if (file.isDirectory()) {
				for (int i = 0; i < number; i++) {
					builder.append("\t");
				}
				builder.append(String.format("%-20s Size: %8d%n", file
						.getName().toUpperCase(), file.length()));
				recurFile(file, number + 1, builder);
			} else {
				for (int i = 0; i < number; i++) {
					builder.append("\t");
				}
				builder.append(String.format("%-20s Size: %8d%n",
						file.getName(), file.length()));
			}
		}
		return builder.toString();
	}
}