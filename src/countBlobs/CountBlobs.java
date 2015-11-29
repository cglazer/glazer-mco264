package countBlobs;

public class CountBlobs {
	public static void main(String[] args) {
		Grid grid=null;
		try {
			grid = new Grid(10, 10, 33);
		} catch (InvalidGridException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(grid.toString());
		System.out.println(grid.blobCount());
	}

}