import java.util.Random;
import java.util.Scanner;

public class TicTacJoe2 {

	public static void main(String[] args) {

		String[] fields = {"1","2","3","4","5","6","7","8","9"};
		String start_player = "X";
		System.out.println("===============================");
		System.out.println(" TicTacJoe - 2 - Version 0.001");
		System.out.println("===============================");
		System.out.println(" ");
		printGameField(fields);
		makeMove(start_player,fields);
	}

	public static void printGameField(String[] fields) {
		System.out.println("");
		for (int i=0; i< 3;i++) {
			System.out.print(fields[i]+" ");
		}
		System.out.println();

		for (int i=3; i< 6;i++) {
			System.out.print(fields[i]+" ");
		}
		System.out.println();
		for (int i=6; i< 9;i++) {
			System.out.print(fields[i]+" ");
		}
		System.out.println("");
	}

	//with string return
	//with error message when field occupied
	public static String [] makeMove(String player, String[] fields) {
		System.out.print("Player: " +player+", ");
		int enter_number;		
		Scanner in = new Scanner(System.in);		
		System.out.print("Enter Number (1-9):");		
		enter_number = scanInt();
		if (fields[enter_number-1]==player || fields[enter_number-1]==otherPlayer(player)){
			System.out.println("");
			System.out.println(" + + + Already occupied. Try again. + + +");
			printGameField(fields);
			return makeMove(player,fields);
		}
		else {fields[enter_number-1]=player;
		printGameField(fields);
		if (boardFull(player, fields)==true) {
			System.out.println("Board Full   ---  GAME OVER!");
			return gameOver();
		}
		if (win(fields)==true) {
			System.out.println("Player "+player+" wins!");
			return gameOver();
		}
		}
		return makeMove(otherPlayer(player),fields);		
	}	

	public static String otherPlayer(String player) {
		if (player == "X") {
			return "O";
		}		
		if (player == "O") {
			return "X";
		}
		String problem_player = "Sth weng wrong";
		return problem_player;
	}	

	//returns true when board full and false if not full
	public static boolean boardFull(String player, String[] fields) {
		for (int i=0;i<fields.length;i++) {
			if (fields[i]!=player && fields[i]!=otherPlayer(player)) {
				return false;
			}
		}
		return true;	
	}

	public static String [] gameOver() {
		String [] result = {"Game Over"};
		System.out.println("Game Over");
		return result;
	}

	public static boolean win(String[]fields) {
		if ((fields[0]==fields[1] && fields[0]==fields[2]) ||
				(fields[3]==fields[4] && fields[3]==fields[5]) ||
				(fields[6]==fields[7] && fields[6]==fields[8])||
				(fields[0]==fields[3] && fields[0]==fields[6])||
				(fields[1]==fields[4] && fields[1]==fields[7])||
				(fields[2]==fields[5] && fields[2]==fields[8])||
				(fields[0]==fields[4] && fields[0]==fields[8])||
				(fields[2]==fields[4] && fields[2]==fields[6]))	{
			return true;
		}
		return false;
	}

	public static int scanInt() {
		Scanner in = new Scanner(System.in);
		while (true) {
			if (in.hasNextInt()) {
				break;
			}
			String word = in.next();
			System.err.println(word + " is not a number. Try again:");
		}
		int x = in.nextInt();
		if (x > 9 || x < 1) {
			System.out.println("That's not between 1-9. Try again:");
			return scanInt();
		}
		return x;
	}
}