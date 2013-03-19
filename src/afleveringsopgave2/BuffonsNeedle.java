package afleveringsopgave2;

import java.util.Scanner;

public class BuffonsNeedle {
	static final double LENGTH = 1; // This is the distance of the needle
	static final double MAXDISTANCE = LENGTH*2; // And this is the distance between each line
	static long success = 0; // This counter is used to count the number of times a needle crosses the line
	static final String error = "Please enter a positive integer."; // Error message

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in); // Create the Scanner instance
		System.out.print("Enter number of iterations: ");
		try {
			long iterations = scanner.nextLong(); // This is the number of iterations
			if(iterations > 0) { // Check if it's positive
				for (int i = 0; i < iterations; i++) { // Use the for-loop to run the "simulation" the number of iterations
					double distance = Math.random()*MAXDISTANCE; // Get the distance from the bottom of the needle to the line
					double angle = Math.random()*Math.PI; // Calculate the angle in radians from 0-¹
					double number = distance + Math.sin(angle)*LENGTH; // Calculate the opposite side using the sine function
					if(number >= MAXDISTANCE) // If it's larger than the maximum distance then it must be crossing the line
						success++; // Increment the counter
				}
				System.out.print(iterations + " / " + success + " = " + (double)iterations/(double)success); // Print the final result
			} else
				System.out.print(error); // Print the error message if it's not an unsigned integer 		
		}
		catch(Exception e) { // This catch is used if the user doesn't enter an integer
			System.out.print(error);
		}
	}
}