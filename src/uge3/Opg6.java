package uge3;

public class Opg6 {
	public static void main(String[] str) {
		quadratic(1,3,2);		
	}
	public static void quadratic(double a, double b, double c) {
		double temp = (Math.sqrt((b*b)-4*a*c));
		System.out.println("x=" + (int)((-b+temp)/(2*a)) + " x=" + (int)((-b-temp)/(2*a)));
	}
}