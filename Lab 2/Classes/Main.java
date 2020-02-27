package eg.edu.alexu.csd.datastructure.hangman;
public class Main {
	public static void main(String[] args) throws Exception {
		Hangman game = new Hangman();
		game.readFile();
		game.secretWord = game.selectRandomSecretWord();
		String word = game.guess('\0');
		java.util.Scanner input = new java.util.Scanner(System.in);
		System.out.print("How many wrong guesses do you want? ");
		game.setMaxWrongGuesses(input.nextInt());
		while(game.notSolved) {
			System.out.println("Your wrong guesses : "+ game.wrongGuesses);
			System.out.println(" 		the word is : " + word);
			System.out.print("Guess a character : ");
			word = game.guess( (input.next()).charAt(0) );
		}
		input.close();
		System.out.print("\n\n 		!! Congratulations !! \n 		!! You guessed it  !! \n\n");
	}
}