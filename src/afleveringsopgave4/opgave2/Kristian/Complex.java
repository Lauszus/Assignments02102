package afleveringsopgave4.opgave2.Kristian;

public class Complex {
	private double re;
	private double im;

	public Complex() {
		re = 0;
		im = 0;
	}

	public Complex(double re, double im) {
		this.re = re;
		this.im = im;
	}

	public Complex(Complex z) {
		this.re = z.re;
		this.im = z.im;
	}

	public double getRe() {
		return re;
	}

	public double getIm() {
		return im;
	}

	public double abs() {
		return Math.sqrt(re * re + im * im);
	}

	public Complex plus(Complex other) {
		return new Complex(re + other.re, im + other.im);
	}

	public Complex times(Complex other) {
		return new Complex(re * other.re - im * other.im, im * other.re + re * other.im);
	}

	public String toString() {
		String sign, strIm, strRe;
		double absIm = Math.abs(im);
		if (im < 0)
			sign = " - ";
		else
			sign = " + ";

		if (re % 1 == 0 && im % 1 == 0) {
			strRe = Integer.toString((int) re);
			strIm = Integer.toString((int) absIm);
		} else if (re % 1 == 0) {
			strRe = Integer.toString((int) re);
			strIm = Double.toString(absIm);
		} else if (im % 1 == 0) {
			strRe = Double.toString(re);
			strIm = Integer.toString((int) absIm);

		} else {
			strRe = Double.toString(re);
			strIm = Double.toString(absIm);
		}

		if (absIm == 1)
			strIm = "";

		return (strRe + sign + strIm + "i");
	}
}
