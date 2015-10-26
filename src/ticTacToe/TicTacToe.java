package ticTacToe;

public class TicTacToe {
	private Character[][] board;

	public TicTacToe() {
		board = new Character[3][3];
	}

	public boolean addMark(Character iD, Integer row, Integer column)
			throws InvalidInputException, AlreadyFilledException {
		if (iD == null || row == null || column == null) {
			throw new NullPointerException();
		}
		if (row < 0 || row > 3 || column < 0 || column > 3) {
			throw new InvalidInputException();
		}
		if (!(iD == 'X' || iD == 'O')) {
			throw new InvalidInputException();
		}
		if (board[row][column] != null) {
			throw new AlreadyFilledException();
		}
		board[row][column] = iD;
		return checkSet();
	}

	public String showBoard() {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < board.length; i++) {
			buffer.append("\n-------------\n");
			buffer.append("| ");
			for (int y = 0; y < board[i].length; y++) {
				
				if (this.board[i][y] == null) {
					buffer.append(" ");
				} else {
					buffer.append(this.board[i][y]);
				}
				buffer.append(" | ");
			}

		}
		buffer.append("\n-------------");
		return buffer.toString();
	}

	public boolean checkSet() {
		boolean set = false;
		for (int i = 0; i < board.length; i++) {
			// check for a horizontal row
			if (board[i][0] != null && board[i][0] == board[i][1]
					&& board[i][0] == board[i][2]) {
				set = true;
				break;
			}
			// check for a vertical row
			if (board[0][i] != null && board[0][i] == board[1][i]
					&& board[0][i] == board[2][i]) {
				set = true;
				break;
			}
			// check for diagnal rows
			if (board[0][0] != null && board[0][0] == board[1][1]
					&& board[0][0] == board[2][2]) {
				set = true;
			}
			if (board[0][2] != null && board[0][2] == board[1][1]
					&& board[0][2] == board[2][0]) {
				set = true;
			}
		}
		return set;
	}

}
