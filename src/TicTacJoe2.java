import java.util.Random;
import java.util.Scanner;

public class TicTacJoe2 {

	public static void main(String[] args) {

		String [] fields = fieldsGenerator();

		System.out.println("===============================");
		System.out.println(" TicTacJoe 2 - 2 Version 0.005");
		System.out.println("===============================");

		int[] gameResult = new int[2];
		String start_player = choosePlayer();
		printGameField(fields);
		makeMove(start_player,fields, gameResult);
	}

	public static String choosePlayer() {
		System.out.println("Choose Player (X/O):");
		String player;
		Scanner in = new Scanner(System.in);
		player = in.nextLine().toString();
		if (player.equals("x") || player.equals("X")) {
			return "X"; 
		}        
		if (player.equals("o") || player.equals("O")) {
			return "O"; 
		}
		else {        	
			System.out.println("Wrong Input!");
			return choosePlayer();
		} 
	}

	public static String otherPlayer(String player) {
		if (player == "X") {
			return "O";
		}		
		if (player == "O") {
			return "X";
		}
		String problem_player = "Something went wrong.";
		return problem_player;
	}

	public static String [] fieldsGenerator() {
		int m = 9;
		String [] fields = new String[m];
		for (int i = 0; i<m;i++) {	
			fields[i]=Integer.toString(i+1);
		}
		return fields;
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

	public static String [] makeMove(String player, String[] fields, int[] gameResult) {
		System.out.print("Player: " +player+", ");
		int enter_number;		
		Scanner in = new Scanner(System.in);		
		System.out.print("Enter Number (1-9):");		
		enter_number = scanInt();
		if (fields[enter_number-1]==player || fields[enter_number-1]==otherPlayer(player)){
			System.out.println("");
			System.out.println(" + + + Already occupied. Try again. + + +");
			printGameField(fields);
			return makeMove(player,fields, gameResult);
		}

		else {fields[enter_number-1]=player;
		printGameField(fields);
		if (boardFull(player, fields)==true) {
			System.out.println("Board Full   ---  GAME OVER!");
			return gameOver(player, fields, gameResult);
		}
		if (win(fields)==true) {
			System.out.println("Player "+player+" wins!");
		    counter(player, gameResult);
			return gameOver(otherPlayer(player), fields, gameResult);
		}
		}
		return makeMove(otherPlayer(player),fields, gameResult);		
	}		

	//returns true when board full / false if not full
	public static boolean boardFull(String player, String[] fields) {
		for (int i=0;i<fields.length;i++) {
			if (fields[i]!=player && fields[i]!=otherPlayer(player)) {
				return false;
			}
		}
		return true;	
	}

	public static String [] gameOver(String player, String[] fields, int[] gameResult) {
		String [] gameOver = {"Game Over"};
		System.out.println("Play Again (y/n?):");
		String play_again_input;
		Scanner in = new Scanner(System.in);
		play_again_input = in.nextLine().toString();
		if (play_again_input.equals("y")) {
			printGameField(fieldsGenerator());
			return makeMove(player,fieldsGenerator(), gameResult); 
		}
		if (play_again_input.equals("n")) {        	
			System.out.println("The End!");
			if (gameResult[0]>gameResult[1]) {
				System.out.println("Player X Is The Ultimate Winner Of The Game!");
			}
			else {System.out.println("Player O Is The Ultimate Winner Of The Game!");}
			return gameOver;
			}
			
		else {        	
			System.out.println("Wrong Input!");
			return gameOver(player, fields, gameResult);
		}	
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
	
	public static int [] counter(String player, int[] gameResult) {
		
		if (player.equals("X")) {        	
			gameResult[0]+=1;}		
		else if (player.equals("O")) {        	
			gameResult[1]+=1;}

		System.out.println(" ");
		System.out.println("Player X:"+ gameResult[0]+" - Player O:"+ gameResult[1]);
		System.out.println(" ");
		return gameResult;
		
	}
}