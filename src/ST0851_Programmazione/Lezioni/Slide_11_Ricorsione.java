package ST0851_Programmazione.Lezioni;

public class Slide_11_Ricorsione {

  /*
  1. Riscrivere i metodi ricorsivi fattoriale e fibonacci usando
     espressioni (e non comandi) condizionali.
   */
  static int fattoriale(int n) {
    return (n == 0) ? 1 : n * fattoriale(n - 1);
  }

  static int fibonacci(int k) {
    return (k == 0) ? 0 :
        (k == 1) ? 1 : fibonacci(k - 1) + fibonacci(k - 2);
  }

  /*
  2. Si consideri la seguente definizione “potenza di un numero”:
     (a alla n) = 1 se n = 0 - altrimenti = a * (a alla n-1)
     Seguendo questa definizione, realizzare un metodo potenza con argomenti a di tipo
     double ed n di tipo int e valore di ritorno di tipo double che calcoli (a alla n).
     Qual è il “costo” (in termini di moltiplicazioni) per calcolare usando questo metodo?
      È possibile fare meglio modificando il metodo?
      ---
      potenzaR "costa" n moltiplicazioni
      potenza R2 "costa" n/2 + 1 moltiplicazioni

      --- \/ non richiesto \/ ---
      potenzaI "costa" n moltiplicazioni ma non intasa lo stack
   */

  static double potenzaR(double a, int n) {
    return (n == 0 ? 1 : a * potenzaR(a, n - 1));
  }

  static double potenzaR2(double a, int n) {
    int n2 = n / 2;
    n -= n2;
    return potenzaR(a, n) * potenzaR(a, n2);
  }

  static double potenzaI(double base, int esp) {
    double res = 1;
    for (int i = 1; i <= esp; i++) {
      res *= base;
    }
    return res;
  }

  /*
  3. Scrivere un metodo ricorsivo per convertire un numero intero non negativo in base 2.
     Ripetere l’esercizio considerando però la conversione in base 16
     e sfruttando al meglio il principio di astrazione procedurale.
   */

  static String toBase2(int n1) {
    return (n1 >= 2 ? toBase2(n1 / 2) + (n1 % 2) : "" + n1);
  }

  static char toCharN(int n1) {
    return (char) (n1 < 10 ? '0' + n1 : n1 < 36 ? 'A' + n1 - 10 : '?');
  }

  static String toBaseN(int n1, int base) {
    if (n1 < 0)
      return "-" + toBaseN(-n1, base);
    if (n1 < base) {
      if (n1 < 10)
        return "" + n1;
      else
        return "" + toCharN(n1);
    }
    return toBaseN(n1 / base, base) + toCharN(n1 % base);
  }

  static void toBase() {
    int base = 2;
    for (int ii = 0; ii < 37; ii += 5) {
      StdOut.print(ii + " => " + toBaseN(ii, base) + "   ");
      StdOut.print(ii + 1 + " => " + toBaseN(ii + 1, base) + "   ");
      StdOut.print(ii + 2 + " => " + toBaseN(ii + 2, base) + "   ");
      StdOut.print(ii + 3 + " => " + toBaseN(ii + 3, base) + "   ");
      StdOut.print(ii + 4 + " => " + toBaseN(ii + 4, base));
      StdOut.println();
    }
    base = 8;
    for (int ii = 0; ii < 37; ii += 5) {
      StdOut.print(ii + " => " + toBaseN(ii, base) + "   ");
      StdOut.print(ii + 1 + " => " + toBaseN(ii + 1, base) + "   ");
      StdOut.print(ii + 2 + " => " + toBaseN(ii + 2, base) + "   ");
      StdOut.print(ii + 3 + " => " + toBaseN(ii + 3, base) + "   ");
      StdOut.print(ii + 4 + " => " + toBaseN(ii + 4, base));
      StdOut.println();
    }
    base = 16;
    for (int ii = 0; ii < 37; ii += 5) {
      StdOut.print(ii + " => " + toBaseN(ii, base) + "   ");
      StdOut.print(ii + 1 + " => " + toBaseN(ii + 1, base) + "   ");
      StdOut.print(ii + 2 + " => " + toBaseN(ii + 2, base) + "   ");
      StdOut.print(ii + 3 + " => " + toBaseN(ii + 3, base) + "   ");
      StdOut.print(ii + 4 + " => " + toBaseN(ii + 4, base));
      StdOut.println();
    }
  }
  /*
  4. Senza utilizzare comandi iterativi, definire un metodo primo per determinare se un
     numero n (l’argomento del metodo) è primo.
     Suggerimento: definire e invocare un metodo ausiliario con due argomenti n e k
      che verifichi se n ha divisori propri compresi tra k e n.
   */

  static boolean hasDivider(int n, int k) {  //con n > k
    if (k < 2)
      return true;
    if (n % k == 0)
      return false;
    return hasDivider(n, k - 1);
    }

  static boolean hasDivider(int n, int a, int b) {  // a <= b
    if ((b < a) || (n < a)) return false;
    if (n % a == 0) return true;
    return hasDivider(n, a + 1, n / a);
  }

  static boolean isPrime(int n1) {
    return !hasDivider(n1, 2, n1 - 1);
  }

  public static void main(String[] args) {
    double base = Math.random() * 100;
    int esp = (int) (Math.random() * 10);
    StdOut.println(esp + "! = " + fattoriale(esp));
    StdOut.println("fibonacci(" + esp + ") = " + fibonacci(esp));
    StdOut.println("----- 2 -----");
    StdOut.println(base + " alla " + esp + " = " + potenzaR(base, esp));
    StdOut.println(base + " alla " + esp + " = " + potenzaR2(base, esp));
    StdOut.println(base + " alla " + esp + " = " + potenzaI(base, esp));
    StdOut.println("----- 3 -----");
    int a = (int) (Math.random() * 100);
    int b = (int) (Math.random() * 35) + 1;
    StdOut.print(a + " => " + toBaseN(a, b) + " in base " + b);
    StdOut.println("----- 4 -----");
    for (int i = 1; i < 100; i++) {
      if(isPrime(i))
        StdOut.println(i + " è un numero primo");
    }
  }


}
