package afleveringsopgave2;

import java.util.Scanner;

public class RomanNumerals {
	static final String error = "Please enter a positive integer."; // Error message
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in); // Create the Scanner instance
		System.out.print("Enter positive integer to convert: ");
		try {
			int value = scanner.nextInt(); // Read the integer
			if(value > 0) // Make sure it's positive
				System.out.println(value + " = " + intToRomanNumeral(value)); // Print the result
			else // If not print an error message
				System.out.println(error);
		} catch(Exception e) { // If the user doesn't enter a integer this catch will print an error message
			System.out.println(error);			
		}
	}
	
	/**
	 * This function is used to convert the integer into Roman numerals
	 * @param The integer to convert 
	 * @return Returns the Roman numeral as a String 
	 */
	private static String intToRomanNumeral(int value) {
		/** 
		 * We use a while-loop to check if it's larger than a specific value
		 * This is necessary since there are some special rules for: 4,9,40,90 etc.
		 */
		String string = "";
		while(value >= 1000) {
			string += "M";
			value -= 1000;
		}
		while(value >= 900) {
			string += "CM";
			value -= 900;
		}
		while(value >= 500) {
			string += "D";
			value -= 500;
		}
		while(value >= 400) {
			string += "CD";
			value -= 400;
		}
		while(value >= 100) {
			string += "C";
			value -= 100;
		}
		while(value >= 90) {
			string += "XC";
			value -= 90;
		}
		while(value >= 50) {
			string += "L";
			value -= 50;
		}
		while(value >= 40) {
			string += "XL";
			value -= 40;
		}
		while(value >= 10) {
			string += "X";
			value -= 10;
		}
		while(value >= 9) {
			string += "IX";
			value -= 9;
		}
		while(value >= 5) {
			string += "V";
			value -= 5;
		}
		while(value >= 4) {
			string += "IV";
			value -= 4;
		}
		while(value >= 1) {
			string += "I";
			value -= 1;
		}
		return string;
	}
}