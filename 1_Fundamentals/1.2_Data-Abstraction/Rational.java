package z_1;

import edu.princeton.cs.algs4.StdOut;

/*
 * Exercise 1.2.16 and 1.2.17
 */

public class Rational {
	
	private long numerator;
	private long denominator;
	
	private Rational(long numerator,long denominator){
		 if (denominator == 0)
	           throw new RuntimeException("Denominator cannot be zero.");
		 
		long g=gcd(numerator,denominator);
		
		this.numerator=numerator/g;
		this.denominator=denominator/g;
		
		if(Math.abs(this.numerator)>Integer.MAX_VALUE||Math.abs(this.denominator)>Integer.MAX_VALUE){
			throw new RuntimeException("Overflow error");
		}
	}
	
	static private long gcd(long p,long q){
		if(p==0)return q;
		long r=q%p;
		return gcd(r,p);
	}
	
	private long getNumerator(){
		return numerator;
	}
	
	private long getDenominator(){
		return denominator;
	}
	
	private Rational plus(Rational r){
		return new Rational(this.numerator*r.denominator+r.numerator*this.denominator,this.denominator*r.denominator);
	}
	
	public Rational minus(Rational b)
    {
        return new Rational(this.numerator * b.denominator - b.numerator * this.denominator,
                            this.denominator * b.denominator);
    }
    
    public Rational times(Rational b)
    {
        return new Rational(this.numerator * b.numerator, this.denominator * b.denominator);
    }
    
    public Rational divides(Rational b)
    {
        return new Rational(this.numerator * b.denominator, this.denominator * b.numerator);
    }
    
    public boolean Myequals(Rational that) {
        return (this.numerator == that.numerator) && (this.denominator == that.denominator);    
    }
    
    public String toString()
    {
        return numerator + "/" + denominator;
    }
    
	public static void main(String[] args){
		Rational r1=new Rational(4,24);
		System.out.println(r1.getNumerator()+"/"+r1.getDenominator());
		
		Rational r2=new Rational(3,9);
		System.out.println(r2.plus(r1).getNumerator()+"/"+r1.plus(r2).getDenominator());
		
		
		StdOut.printf("%s - %s = %s (%s)\n", r1, r2, r1.minus(r2),
                r1.minus(r2).Myequals(new Rational(2, 21)));        

		StdOut.printf("%s * %s = %s (%s)\n", r1, r2, r1.times(r2),
                r1.times(r2).Myequals(new Rational(8, 21)));        

		StdOut.printf("%s / %s = %s (%s)\n", r1, r2, r1.divides(r2),
                r1.divides(r2).Myequals(new Rational(1, 2)));
		StdOut.println();
	}

}
