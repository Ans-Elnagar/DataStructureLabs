package eg.edu.alexu.csd.datastructure.hangman;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
public class Hangman implements IHangman {
	
	private String[] dictionary;
	private int maxWrongGuesses=1;
	int wrongGuesses = 0; // The value is zero by default
	String secretWord = "";
	int lengthOfSecretWord;
	String guessedLetters = "";
	boolean notSolved = true;
	void readFile(){
		try {
			File tempFile = new File("HangmanWords.txt");
			java.util.Scanner fileReader = new java.util.Scanner(tempFile);
			ArrayList<String> fileWords = new ArrayList<String>();
			while(fileReader.hasNext()) {
				fileWords.add(fileReader.next());
			}
			fileReader.close();
			int numberOfWords = fileWords.size();
			String[] words = new String[numberOfWords];
			for (int i=0; i<numberOfWords; i++) {
				words[i] = fileWords.get(i);
			}
			setDictionary(words);
		}catch (FileNotFoundException e) {
			System.out.println("Could not Read file HangmanWords.txt");
		}
	}
	
	public void setDictionary(String[] words){
		dictionary = words; // now dictionary points to the words array
	}
	
	public String selectRandomSecretWord() {
		String word = dictionary[(int) Math.random() * dictionary.length];
		lengthOfSecretWord = word.length();
		return word;
	}
	
	public String guess(Character c) throws Exception{
		if (wrongGuesses == maxWrongGuesses) {
			throw new Exception("Reached Max of wrong guesses.");
		}
		for (int i=0; i<lengthOfSecretWord; i++) {
			if (! Character.isLetter(secretWord.charAt(i))) {
				throw new Exception("Buggy secret word");
			}
		}
		if (c != '\0') {
			if (Character.isLetter(c)) {
				c = Character.toLowerCase(c);
			} // if not a letter, then it isn't in the secretWord
			if(secretWord.contains(c+"")) {
				if (!(guessedLetters.contains(c+""))) {
					guessedLetters += c;
				}
			} else {
				wrongGuesses++;
			}
		}
		String guessedWord = "";
		notSolved = false;
		for (int i=0; i<lengthOfSecretWord; i++) {
			if(guessedLetters.contains(secretWord.charAt(i)+"")) {
				guessedWord += secretWord.charAt(i);
			} else {
				guessedWord += '-';
				notSolved = true;
			}
		}
		return guessedWord;
	}
	
	public void setMaxWrongGuesses(Integer max) {
		maxWrongGuesses = max;
	}
}