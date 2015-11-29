package countBlobs;

import java.util.Random;
import java.util.Stack;

public class Grid {
	protected int rows;
	protected int cols;

	protected Cell[][] grid;

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
					grid[i][j].setBlobFalse();
				}
			}
		}
	}

	public int blobCount() {
		// returns the number of blobs in the grid
		int count = 0;
		// count blobs
		BlobIterator iter = new BlobIterator(this.grid);
		iter.reset();
		Stack<Cell> stack = new Stack<Cell>();
		Cell current=grid[0][0];
		do{
			if (!current.isVisited() && current.hasBlob()) {
				current.setVisited();
				Cell above = iter.above();
				Cell below = iter.below();
				Cell toLeft = iter.toLeft();
				Cell toRight = iter.toRight();
				if (above != null && above.hasBlob()) {
					stack.add(current);
					count++;
					System.out.println(current.getRow() + ", " + current.getColumn());
				} else if (below != null && below.hasBlob()) {
					stack.add(current);
					count++;
					System.out.println(current.getRow() + ", " + current.getColumn());
				} else if (toLeft != null && toLeft.hasBlob()) {
					stack.add(current);
					count++;
					System.out.println(current.getRow() + ", " + current.getColumn());
				} else if (toRight != null && toRight.hasBlob()) {
					stack.add(current);
					count++;
					System.out.println(current.getRow() + ", " + current.getColumn());
				}
				BlobIterator stackIter = new BlobIterator(this.grid);
				while (!stack.empty()) {
					Cell check = stack.pop();
					stackIter.setCounter(check.row, check.column);
					above = stackIter.above();
					below = stackIter.below();
					toLeft = stackIter.toLeft();
					toRight = stackIter.toRight();
					if (above != null && !above.isVisited()) {
						if (above.hasBlob()) {
							stack.add(above);
						}
						grid[above.getRow()][above.getColumn()].setVisited();
						above.setVisited();
					}
					if (below != null && !below.isVisited()) {
						if (below.hasBlob()) {
							stack.add(below);
						}
					grid[below.getRow()][below.getColumn()].setVisited();
					below.setVisited();
					}
					if (toLeft != null && !toLeft.isVisited()) {
						if (toLeft.hasBlob()) {
							stack.add(toLeft);
						}
						grid[toLeft.getRow()][toLeft.getColumn()].setVisited();
						toLeft.setVisited();
					}
					if (toRight != null && !toRight.isVisited()) {
						if (toRight.hasBlob()) {
							stack.add(toRight);
						}
						grid[toRight.getRow()][toRight.getColumn()].setVisited();
						toRight.setVisited();
					}

				}
			}
			current=iter.next();
		}while(iter.hasNext());

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
