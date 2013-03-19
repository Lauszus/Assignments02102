package uge3;

public class Opg3 {
	public static void main(String[] str) {
		System.out.println(repl("test",3));
	}
	public static String repl(String input, int n) {
		String output = "";
		for(int i=0;i<n;i++)
			output += input;
		return output;
	}
}