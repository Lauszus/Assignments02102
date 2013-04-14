package Mads;

public class Complex {
	private double re;
	private double im;
	
	public Complex(){
		this.re=0;
		this.im=0;
	}

	public Complex(double re, double im){
		this.re=re;
		this.im=im;
	}

	public Complex(Complex z){
		this.re=z.re;
		this.im=z.im;
	}

	public double getRe(){
		return re;
	}

	public double getIm(){
		return im;
	}

	public double abs(){
		return Math.sqrt(re*re+im*im);
	}
	
	public Complex plus(Complex other){
		return new Complex(this.re + other.re, this.im + other.im);
	}
	
	
	public Complex times(Complex other){
		return new Complex(this.re*other.re-this.im*other.im, this.im*other.re+this.re*other.im);
	}
	
	public String toString(){
		return (this.re+" + "+this.im+"i");
	}
	

}
