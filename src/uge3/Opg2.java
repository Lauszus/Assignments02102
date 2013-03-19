package uge3;

import java.util.Scanner;

public class Opg2 {
	public static void main(String[] str) {
		Scanner console = new Scanner(System.in);
		processName(console);
	}
	public static void processName(Scanner console) {
		System.out.print("Please enter your full name: ");
		String name = console.nextLine();
		String split[] = name.split(" ");
		System.out.print("Your name in reverse order is " + split[split.length-1] + ", ");
		for(int i=0;i<split.length-1;i++)
			System.out.print(split[i] + " ");
	}
}