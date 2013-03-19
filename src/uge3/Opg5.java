package uge3;

public class Opg5 {
	private static final double DEPOSITED = 100;
	public static void main(String[] str) {
		double balance = 1000;
		System.out.println("Year: Balance: Interest: New deposit: New Balance");
		for(int i=1;i<=25;i++) {
			System.out.print(i+ "\t" + (int)balance + "\t" + (int)(balance*0.065) + "\t" + DEPOSITED + "\t");
			balance += balance*0.065 + DEPOSITED;
			System.out.println((int)balance);	
		}
	}
}