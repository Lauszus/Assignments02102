package uge3;

public class Opg4 {
	public static void main(String[] str) {
		printPowersOf2(10);
	}
	public static void printPowersOf2(int number) {
		for(int i=0;i<=number;i++)
			System.out.print((int)Math.pow(2,i) + " ");
	}
}