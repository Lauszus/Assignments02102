package afleveringsopgave4.opgave2.Simon;

public class Complex {
	private double re;
	private double im;
	
	public Complex(){
		re=0;
		im=0;
	}


	public Complex(double re, double im){
		this.re = re;
		this.im= im;

	}

	public Complex(Complex z){
		this.re= z.re;
		this.im = z.im;
	}
	public double getRe(Complex z){
		return re;
	}
	public double getIm(Complex z){
		return im;
	}
	public double abs(Complex z){
		return Math.sqrt(re*re + im*im);
	}
	public Complex plus(Complex other){
		return new Complex(re + other.re, im+ other.im);

	}
	public Complex times(Complex other){
		return new Complex(re * other.re - im * other.im, other.re*im+re*other.im);
	}
	public String toString(){
		if(im >= 0){
			return (this.re +" + "+ this.im+"i");
		}else{
			return (this.re + " "+this.im+"i");
		}
	}

}


