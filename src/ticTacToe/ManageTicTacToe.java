package ticTacToe;

import java.util.Scanner;

public class ManageTicTacToe {
	public static void main(String args[]) {
		System.out.println("Thank you for choosing to play Tic-tac-toe!");
		Scanner input = new Scanner(System.in);
		String playAgain = null;
		int row;
		int column;
		do {
			TicTacToe board = new TicTacToe();
			int player = 0;
			boolean set = false;
			System.out.println(board.showBoard());
			do {
				if (player % 2 == 0) {
					do {
						System.out
								.println("Enter a row for player X (0, 1, or 2)");
						row = input.nextInt();
					} while (row < 0 || row > 2);
					do {
						System.out
								.println("Enter a column for player X (0, 1, or 2)");
						column = input.nextInt();
					} while (column < 0 || column > 2);
					try {
						set = board.addMark('X', row, column);
						System.out.println(board.showBoard());
						player++;
					} catch (InvalidInputException e) {
						System.out.println("Please enter 0, 1, or 2.");
					} catch (AlreadyFilledException e) {
						System.out
								.println("Sorry. This space was already filled. Please try again.");
					}
				} else {
					do {
						System.out
								.println("Enter a row for player O (0, 1, or 2)");
						row = input.nextInt();
					} while (row < 0 || row > 2);
					do {
						System.out
								.println("Enter a column for player O (0, 1, or 2)");
						column = input.nextInt();
					} while (column < 0 || column > 2);
					try {
						set = board.addMark('O', row, column);
						System.out.println(board.showBoard());
						player++;
					} catch (InvalidInputException e) {
						System.out.println("Please enter 0, 1, or 2.");
					} catch (AlreadyFilledException e) {
						System.out
								.println("Sorry. This space was already filled. Please try again.");
					}
				}
			} while (!set && player < 9);
			if (set) {
				if (player % 2 == 0) {
					System.out.println("Congratulations! Player O won!!");
				} else {
					System.out.println("Congratulations! Player X won!!");
				}
			} else {
				System.out.println("Game over.");
			}
			System.out.println("Would you like to play again?");
			playAgain = input.next();
		} while (playAgain.toUpperCase().charAt(0) == 'Y');

		System.out.println("Thank you for playing!");
		input.close();
	}

}
