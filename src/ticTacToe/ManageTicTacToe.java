package ticTacToe;

import java.util.Scanner;

public class ManageTicTacToe {
	public static void main(String args[]) {
		System.out.println("Thank you for choosing to play Tic-tac-toe!");
		Scanner input = new Scanner(System.in);
		String playAgain = null;

		do {
			TicTacToe board = new TicTacToe();
			int player = 0;
			boolean set = false;
			System.out.println(board.showBoard());
			do {
				if (player % 2 == 0) {
					System.out.println("Enter a row for player X (0, 1, or 2)");
					int row = input.nextInt();
					System.out
							.println("Enter a column for player X (0, 1, or 2)");
					int column = input.nextInt();
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
					System.out.println("Enter a row for player O (0, 1, or 2)");
					int row = input.nextInt();
					System.out
							.println("Enter a column for player O (0, 1, or 2)");
					int column = input.nextInt();

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
				System.out.println("Congratulations! You won!!");
			}
			System.out.println("Would you like to play again?");
			playAgain = input.next();
		} while (playAgain.toUpperCase().charAt(0) == 'Y');

		System.out.println("Thank you for playing!");
		input.close();
	}

}
