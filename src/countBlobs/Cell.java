package countBlobs;

public class Cell {
	private boolean visited;
	private boolean hasBlob;
	private int row;
	private int column;

	public Cell(int row, int column) {
		this.visited = false;
		this.row = row;
		this.column = column;
		this.hasBlob = false;
	}

	public int getRow() {
		return this.row;
	}

	public int getColumn() {
		return this.column;
	}

	public void setVisited() {
		this.visited = true;
	}

	public boolean isVisited() {
		return this.visited;
	}

	public void setBlobTrue() {
		this.hasBlob = true;
	}

	public boolean hasBlob() {
		return this.hasBlob;
	}
}
