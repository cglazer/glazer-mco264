package countBlobs;

public class Cell {
	private boolean visited;
	private boolean hasBlob;
	int row;
	int column;

	public Cell(int row, int column) {
		visited = false;
	this.row=row;
	this.column=column;
	}
public int getRow(){
	return this.row;
}public int getColumn(){
	return this.column;
}
	public void setVisited() {
		visited = true;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setBlobTrue() {
		hasBlob = true;
	}

	public boolean hasBlob() {
		return hasBlob;
	}

	public void setBlobFalse() {
		hasBlob=false;
		
	}

}
