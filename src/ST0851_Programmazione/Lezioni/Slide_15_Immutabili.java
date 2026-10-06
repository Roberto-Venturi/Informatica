package ST0851_Programmazione.Lezioni;

public class Slide_15_Immutabili {

  public static class Rational {
    private int numerator;
    private int denominator;

    public Rational() {
      this(0, 1);
    }

    public Rational(int numerator) {
      this(numerator, 1);
    }

    public Rational(int numerator, int denominator) {
      if (denominator < 0) {
        this.numerator = -numerator;
        this.denominator = -denominator;
      } else {
        this.numerator = numerator;
        this.denominator = denominator;
      }
    }

    private int mcd(int a, int b) {
      int mcd = 1;
      if (a > b) {
        mcd = b;
      } else {
        mcd = a;
      }
      for (; mcd > 0; mcd--) {
        if (b % mcd == 0 && a % mcd == 0) {
          break;
        }
      }
      return mcd;
    }


    private int mcm(int a, int b) {
      int mcm = a * b / mcd(a, b);
      return mcm;
    }


    public int getNumerator() {
      return numerator;
    }

    public int getDenominator() {
      return denominator;
    }

    public double to_double() {
      return (double) numerator / denominator;
    }

    public Rational negate() {
      return new Rational(-numerator, denominator);
    }

    public Rational reciprocal() {
      return new Rational(denominator, numerator);
    }

    public Rational add(Rational x) {
      return new Rational(numerator * x.denominator + denominator * x.numerator, denominator * x.denominator);
    }

    public Rational mul(Rational x) {
      return new Rational(numerator * x.numerator,
          denominator * x.denominator);
    }

    public Rational sub(Rational x) {
      return this.add(x.negate());
    }

    public Rational div(Rational x) {
      return this.mul(x.reciprocal());
    }

    public int compare(Rational x) {
      if (this.to_double() < x.to_double())
        return -1;
      if (this.to_double() == x.to_double())
        return 0;
      return 1;
    }

    public Rational simplify() {
      int mcd = mcd(numerator, denominator);
      return new Rational(numerator / mcd, denominator / mcd);
    }

    public String toString() {
      if (denominator == 1)
        return Integer.toString(numerator);
      else
        return numerator + "/" + denominator;
    }
  }

  static void main(String[] args) {
    Rational a = new Rational(1, 2); // 1/2 Rational
    Rational b = new Rational(2, 3);// 2/3
    StdOut.println("A = " + a + "   b = " + b );
    StdOut.println("A + B = " + a.add(b));
    StdOut.println("A - B = " + a.sub(b));
    StdOut.println("A * B = " + a.mul(b));
    StdOut.println("A / B = " + a.div(b));
    StdOut.println("a.compare(B) = " + a.compare(b));
    b = new Rational(3, 9);
    StdOut.println("symplify(" + b.toString() + ") = " + b.simplify());

  }


}
