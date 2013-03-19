package afleveringsopgave3;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PrimeFactors {
	private static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		while (true) {			
			try {
				System.out.print("Enter integer greater than 1 (0 to terminate): ");
				long number = scanner.nextLong();
				if (number == 0)
					break;
				System.out.print("List of prime factors: ");
				System.out.println(getPrimeFactors(number));
			} catch(InputMismatchException e) {
				System.out.println("Please type a valid integer!");
				break;
			}
		}
	}

	private static String getPrimeFactors(long n) {
		String output = "";
		for (int i = 2; i <= n; i++) {
			while (n % i == 0) { // Check if the remainder is 0 
				output += Integer.toString(i) + ","; // Add the number to the string
				n /= i;
			}
		}
		return output.substring(0, output.lastIndexOf(','));
	}
}
