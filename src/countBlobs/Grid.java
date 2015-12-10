package countBlobs;

import java.util.Random;
import java.util.Stack;

public class Grid {
	private int rows;
	private int cols;
	private Cell[][] grid;

	public Grid(int rows, int cols, int percentage) throws InvalidGridException {
		if (rows <= 0 || cols <= 0 || percentage <= 0 || percentage >= 100) {
			throw new InvalidGridException();
		}
		this.rows = rows;
		this.cols = cols;
		this.grid = new Cell[this.rows][this.cols];
		int randInt;
		Random rand = new Random();
		for (int i = 0; i < this.rows; i++) {
			for (int j = 0; j < this.cols; j++) {
				randInt = rand.nextInt(100);
				if (randInt < percentage) {
					grid[i][j] = new Cell(i, j);
					grid[i][j].setBlobTrue();
				} else {
					grid[i][j] = new Cell(i, j);
				}
			}
		}
	}

	public int blobCount() {
		int count = 0;
		// iterator to go through the grid in sequential order
		BlobIterator iter = new BlobIterator(this.grid);
		// stack to hold cells with a blob in it
		Stack<Cell> stack = new Stack<Cell>();
		// create an iterator that can jump to different cells that are
		// called from the stack
		BlobIterator stackIter = new BlobIterator(this.grid);
		Cell current = grid[0][0];
		do {
			// only go further if the cell wasn't checked yet and it has a blob
			if (!current.isVisited() && current.hasBlob()) {
				current.setVisited();
				count++;
				stack.add(current);
				Cell below = null;
				Cell toRight = null;
				Cell above = null;
				Cell toLeft = null;
				while (!stack.empty()) {
					Cell check = stack.pop();
					// set the iterator to the top of the stack and check all
					// sides of each cell in the stack and add each side cell if
					// and only if that cell has a blob and it wasn't visited
					// yet
					stackIter.setCounter(check.getRow(), check.getColumn());
					above = stackIter.above();
					below = stackIter.below();
					toLeft = stackIter.toLeft();
					toRight = stackIter.toRight();
					if (above != null && !above.isVisited()) {
						if (above.hasBlob()) {
							stack.add(above);
						}
						above.setVisited();
					}
					if (below != null && !below.isVisited()) {
						if (below.hasBlob()) {
							stack.add(below);
						}
						below.setVisited();
					}
					if (toLeft != null && !toLeft.isVisited()) {
						if (toLeft.hasBlob()) {
							stack.add(toLeft);
						}
						toLeft.setVisited();
					}
					if (toRight != null && !toRight.isVisited()) {
						if (toRight.hasBlob()) {
							stack.add(toRight);
						}
						toRight.setVisited();
					}

				}
			}
			// not necessary to check the last box in the grid- if it would be a
			// blob, it would be already checked
			current = iter.next();
		} while (iter.hasNext());
		// return the number of blobs in the grid
		return count;
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (grid[i][j].hasBlob()) {
					buffer.append("X");
				} else {
					buffer.append("-");
				}
			}
			buffer.append("\n");

		}
		return buffer.toString();

	}
}
