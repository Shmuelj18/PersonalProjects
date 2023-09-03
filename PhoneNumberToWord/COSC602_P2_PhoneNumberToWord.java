/**
 * 
 */
package project;

import java.io.*;
import java.util.*;

import structure.MyArrayList;

/**
 * @author Shmuel Jacobsen
 *
 */
public class COSC602_P2_PhoneNumberToWord {
	public static void test() {
		// Take phone number and send error if it contains 1 or 0.
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter a 7 digit phone number without dashes or spaces:");
		String phoneNumber = scanner.nextLine();
		if (phoneNumber.contains("0") || phoneNumber.contains("1")) {
			System.out.println("Please ensure your phone number does not contain a 0 or 1 and run again.");
			return;
		}

		// Read dictionary file and add all 7 letter words to array list.
		String wordFile = "../COSC602_P2_EnglishWordList.txt";
		MyArrayList wordsOf7 = new MyArrayList();

		try {
			BufferedReader dictionary = new BufferedReader(new FileReader(wordFile));
			String word = dictionary.readLine();

			while (word != null) {
				if (word.length() == 7) {
					wordsOf7.append(word);
					word = dictionary.readLine();
				}
				word = dictionary.readLine();
			}
			dictionary.close();
		} catch (FileNotFoundException fnfe) {
			System.err.println("Input file" + wordFile + "not found.");
		} catch (IOException ioe) {
			System.err.println("Input file reading error.");
		}

		char[] numberInArray = phoneNumber.toCharArray();
		MyArrayList finalList = new MyArrayList();
		for (int i = 0; i < numberInArray.length; i++) {
			MyArrayList temp = new MyArrayList();
			if (numberInArray[i] == '2') {
				for (int j = 0; j < wordsOf7.size(); j++) {
					char[] firstLetter = wordsOf7.elementAt(j).toString().toCharArray();
					if (firstLetter[i] == 'a' || firstLetter[i] == 'b' || firstLetter[i] == 'c') {
						temp.append(wordsOf7.elementAt(j));
					}
				}
				wordsOf7 = temp;
			} else if (numberInArray[i] == '3') {
				for (int j = 0; j < wordsOf7.size(); j++) {
					char[] firstLetter = wordsOf7.elementAt(j).toString().toCharArray();
					if (firstLetter[i] == 'd' || firstLetter[i] == 'e' || firstLetter[i] == 'f') {
						temp.append(wordsOf7.elementAt(j));
					}
				}
				wordsOf7 = temp;
			} else if (numberInArray[i] == '4') {
				for (int j = 0; j < wordsOf7.size(); j++) {
					char[] firstLetter = wordsOf7.elementAt(j).toString().toCharArray();
					if (firstLetter[i] == 'g' || firstLetter[i] == 'h' || firstLetter[i] == 'i') {
						temp.append(wordsOf7.elementAt(j));
					}
				}
				wordsOf7 = temp;
			} else if (numberInArray[i] == '5') {
				for (int j = 0; j < wordsOf7.size(); j++) {
					char[] firstLetter = wordsOf7.elementAt(j).toString().toCharArray();
					if (firstLetter[i] == 'j' || firstLetter[i] == 'k' || firstLetter[i] == 'l') {
						temp.append(wordsOf7.elementAt(j));
					}
				}
				wordsOf7 = temp;
			} else if (numberInArray[i] == '6') {
				for (int j = 0; j < wordsOf7.size(); j++) {
					char[] firstLetter = wordsOf7.elementAt(j).toString().toCharArray();
					if (firstLetter[i] == 'm' || firstLetter[i] == 'n' || firstLetter[i] == 'o') {
						temp.append(wordsOf7.elementAt(j));
					}
				}
				wordsOf7 = temp;
			} else if (numberInArray[i] == '7') {
				for (int j = 0; j < wordsOf7.size(); j++) {
					char[] firstLetter = wordsOf7.elementAt(j).toString().toCharArray();
					if (firstLetter[i] == 'p' || firstLetter[i] == 'q' || firstLetter[i] == 'r'
							|| firstLetter[i] == 's') {
						temp.append(wordsOf7.elementAt(j));
					}
				}
				wordsOf7 = temp;
			} else if (numberInArray[i] == '8') {
				for (int j = 0; j < wordsOf7.size(); j++) {
					char[] firstLetter = wordsOf7.elementAt(j).toString().toCharArray();
					if (firstLetter[i] == 't' || firstLetter[i] == 'u' || firstLetter[i] == 'v') {
						temp.append(wordsOf7.elementAt(j));
					}
				}
				wordsOf7 = temp;
			} else if (numberInArray[i] == '9') {
				for (int j = 0; j < wordsOf7.size(); j++) {
					char[] firstLetter = wordsOf7.elementAt(j).toString().toCharArray();
					if (firstLetter[i] == 'w' || firstLetter[i] == 'x' || firstLetter[i] == 'y'
							|| firstLetter[i] == 'z') {
						temp.append(wordsOf7.elementAt(j));
					}
				}
				wordsOf7 = temp;
			}
			finalList = temp;
		}

		if (finalList.size() > 0) {
			for (int j = 0; j < finalList.size(); j++) {
				System.out.println("Word " + j + " : " + finalList.elementAt(j));
			}
			System.out.println("You have " + finalList.size() + " word(s) associated with this number.");
		} else
			System.out.println("There are no words that match this number.");
	}
}
