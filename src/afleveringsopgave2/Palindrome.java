package afleveringsopgave2;

import java.util.Scanner;

public class Palindrome {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in); // Create the Scanner instance
		System.out.print("Enter line to check: ");
		String text = scanner.nextLine(); // Read text submitted by the user
		System.out.print("\"" + text + "\" is ");
		if(!checkPalindrome(text)) // Check if it's a Palindrome
			System.out.print("not ");
		System.out.println("a palindrome!");
	}
	
	/**
	 * This function is used to check the user inputs a Palindrome 
	 * @param The String to check
	 * @return Returns true if it's a Palindrome
	 */
	private static boolean checkPalindrome(String input) {
		input = input.toLowerCase().replaceAll("[^a-z]", ""); // Remove everything that is not a letter using a regex
		if(input.equals("")) // Check if the string is empty. For instance if the user inputs only numbers
			return false;
		return new StringBuilder(input).reverse().toString().equals(input); // Check if the input is equal to the reverse using the StringBuilder class
	}
}