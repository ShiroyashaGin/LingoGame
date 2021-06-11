package shiro.com;
import java.util.*;
import java.util.Scanner;

public class GameLogic {
	String[] debugWords = {"abcda"};
	String[] words = {"owner",
	                  "phone",
	                  "ratio",
	                  "death",
	                  "bonus",
	                  "media",
	                  "movie",
	                  "power",
	                  "bread",
	                  "basis",
	                 "drama",
	                 "honey",
	                 "steak",
	                  "shirt",
	                 "union",
	                  "queen",
	                 "skill",
	                  "music",
	                  "chest",
	                  "photo"};
	//PROTOCOL:
	//in charArray, [0] not in word, [1] char in correct position, [2] char in word [?] undetermined.
	boolean debugEnabled = true;
	
	String wordToGuess = "abcda";
	String guessedWord;
	char[] checkResult;
	String result = "";
	Scanner sc = new Scanner(System.in);
	
	//Stats
	int tries = 0;
	
	GameLogic(){
		GameSetup();
		GameLoop();
	}
	
	void GameSetup() {
		System.out.println("--------------------------- \n "
				+ "----------5 letter Lingo----------\n"
				+ "0 = Letter not in Word\n"
				+ "1 = Letter in correct position \n"
				+ "2 = Letter in the wrong position\n"
				+ "---------------------------");
		wordToGuess = GetRandomWordFromArray(debugWords);
		tries = 0;
	}
	
	void GameLoop() {
		tries++;
		checkResult = "?????".toCharArray();
		System.out.println("Input your word:");
		//System.out.println("Guess: " + GetWordFromUser());
		if(debugEnabled) {
			System.out.println(wordToGuess);
		}
			
		guessedWord = GetWordFromUser();
		checkResult = CheckLettersMatchingPositions(guessedWord, wordToGuess, checkResult);
		DisplayResultStep(new String(checkResult));
		checkResult = CheckLetterInWord(guessedWord, wordToGuess, checkResult);
		DisplayResultStep(new String(checkResult));
		
		if(GuessIsCorrect(checkResult)) {
			System.out.println("Game Finished in " + tries + " tries" + "\n----------------- ");
		}
		GameLoop();
	}
	
	char[] CheckLettersMatchingPositions(String firstWord, String secondWord, char[] resultStringAdditive) {
		int wordLength = firstWord.length() < secondWord.length() ? firstWord.length() : secondWord.length();
		char[] firstWordInChars = firstWord.toCharArray();
		char[] secondWordInChars = secondWord.toCharArray();
		
		for(int i = 0; i < wordLength; i++) {
			if(firstWordInChars[i] == secondWordInChars[i]) {
				resultStringAdditive[i]= '1';
			}
		}
		return resultStringAdditive;
	}
	
	char[] CheckLetterInWord(String firstWord, String secondWord, char[] resultStringAdditive) {
		int wordLength = firstWord.length() < secondWord.length() ? firstWord.length() : secondWord.length();
		char[] firstWordInChars = firstWord.toCharArray();
		char[] secondWordInChars = secondWord.toCharArray();
		char[] tempResult = resultStringAdditive;
		
		for(int i = 0; i < wordLength; i++) {
			char currentLetter = firstWord.charAt(i);
			
			if(tempResult[i] == '1')
				continue;
			
			for(int o = 0; o < wordLength; o++) {
				if(Character.isDigit(tempResult[o])) {
					continue;
				}
				if(firstWordInChars[i] == secondWordInChars[o]) {
					tempResult[i]= '2';
				}
			}
		}
		return tempResult;
	}
	
	boolean GuessIsCorrect(char[] result){
		for(char c : result) {
			if(c != '1')
				return false;
		}
		return true;
	}

	String GetRandomWordFromArray(String[] words) {
		Random random = new Random();
		int randomIndex = random.nextInt(words.length);
		return words[randomIndex];
	}
	
	void DisplayResultStep(String result) {
		System.out.println(result + "\n-----------------");
	}
	
	String GetWordFromUser(){
		return sc.next();
	}
}
