package uge1;
public class Opg4 {
	public static void main(String[] args) {
		rocketTopBottom();
		stars();
		sides();
		stars();
		usa();
		stars();
		sides();
		stars();
		rocketTopBottom();
	}

	public static void rocketTopBottom() {
		System.out.println("   /\\\t\t   /\\");
		System.out.println("  /  \\\t\t  /  \\");
		System.out.println(" /    \\\t\t /    \\");

	}

	public static void stars() {
		System.out.print("+------+");
		System.out.print("\t");
		System.out.println("+------+");
	}

	public static void sides() {
		for (int i = 0; i < 2; i++) {
			System.out.print("|      |");
			System.out.print("\t");
			System.out.println("|      |");
		}

	}

	public static void usa() {
		System.out.print("|United|");
		System.out.print("\t");
		System.out.println("|United|");
		System.out.print("|States|");
		System.out.print("\t");
		System.out.println("|States|");
	}

}
