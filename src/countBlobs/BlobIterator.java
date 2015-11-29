package countBlobs;

public class BlobIterator {
	private Cell[][] grid;
	private int rows;
	private int columns;
	private int rowCounter;
	private int columnCounter;
	

		public BlobIterator(Cell grid[][]){
		this.grid = grid;
		this.rows = grid.length;
		this.columns = this.grid[this.rowCounter].length;
		this.rowCounter = 0;
		this.columnCounter = 0;
		
	}

	public boolean hasNext() {
		if (this.rowCounter == rows - 1 && this.columnCounter == columns - 1) {
			return false;
		}
		return true;
	}

	public Cell next() {
		if (columnCounter < columns - 1) {
			columnCounter++;
		} else {
			rowCounter++;
			this.columnCounter = 0;
		}
		return grid[this.rowCounter][this.columnCounter];
	}

	public Cell above() {
		if (this.rowCounter > 0) {
			return grid[this.rowCounter - 1][this.columnCounter];
		}

		return null;

	}

	public Cell below() {
		if (this.rowCounter < rows - 1) {
			return grid[this.rowCounter + 1][this.columnCounter];
		}
		return null;

	}

	public Cell toRight() {
		if (columnCounter < columnCounter - 1) {
			return grid[this.rowCounter][this.columnCounter + 1];
		}
		return null;
	}

	public Cell toLeft() {
		if (this.columnCounter > 0) {
			return grid[this.rowCounter][this.columnCounter - 1];
		}
		return null;
	}
public void setCounter(int row, int column){
	if(row<rows){
		this.rowCounter= row;
	}if(column<columns){
		this.columnCounter=column;
	}
}
	public void reset() {
		this.rowCounter = 0;
		this.columnCounter = 0;
	}
}
