package ST0851_Programmazione.Lezioni;

public class Slide_09_Metodi {

  /*
  1. Definire due metodi mcd ed mcm per calcolare rispettivamente
     MCD ed MCM di due numeri interi positivi
   */
  static int mcd(int a, int b) {
    int mcd;
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

  static int mcm(int a, int b) {
    int mcm = a * b / mcd(a, b);
    return mcm;
  }

  /*
  2. Definire due metodi pari e dispari, ciascuno con
     un argomento di tipo int e tipo di ritorno boolean,
     per determinare se un numero è, appunto, pari o dispari.
     Evitare quanto più possibile la duplicazione di codice
   */

  static boolean pari(int n1) {
    return (n1 % 2 == 0);
  }

  static boolean dipari(int n1) {
    return !pari(n1);
  }

  /*
  3. Definire un metodo round con un argomento di tipo double
     e tipo di ritorno int che arrotonda il suo argomento
     al numero intero più vicino
   */
  static int round(double d1) {
    return (int) (d1 + 0.5);
  }

  /*
  4. Definire un metodo hex con un argomento x di tipo int e tipo di ritorno String
     che converte x nella sua rappresentazione in base 16.
     Tenere in considerazione la possibilità che x sia negativo
   */

  static char hexChar(int n1) {
    if (n1 < 0 || n1 > 15)
      return '?';
    if (n1 < 10)
      return (char) ('0' + n1);
    else
      return (char) ('A' + n1 - 10);
  }

  static String hex(int n1) {
    String result = "";
    while (n1 > 0) {
      result = hexChar(n1 % 16) + result;
      n1 = n1 / 16;
    }
    return result;
  }

  /*
  5. Definire un metodo primo con un argomento x di tipo int e tipo di ritorno boolean
     che determina se x è primo
  */
  static boolean primo(int n1) {
    for (int i = 2; i <= n1 / 2; i++) {
      if (n1 % i == 0)
        return false;
    }
    return true;
  }

  /*
  6. Definire un metodo stampa_primi con un argomento n di tipo int che stampa tutti i
     numeri primi compresi tra 1 ed n, estremi inclusi.
     Utilizzare il metodo primo dell’esercizio precedente
   */
  static void stampa_primi(int n1) {
    for (int i = 1; i <= n1; i++) {
      if (primo(i))
        StdOut.println(i);
    }
  }

  public static void main(String[] args) {
    int a = (int) (Math.random() * 100);
    int b = (int) (Math.random() * 100);
    StdOut.println("mcd di " + a + ", " + b + " = " + mcd(a, b));
    StdOut.println("mcm di " + a + ", " + b + " = " + mcm(a, b));
    StdOut.println("----- 2 -----");
    if (pari(a))
      StdOut.println(a + " è un numero pari");
    if (dipari(a))
      StdOut.println(a + " è un numero dispari");
    StdOut.println("----- 3 -----");
    double d1 = Math.random() * 100 + 1;
    StdOut.println(d1 + " si arrotonda a " + round(d1));
    StdOut.println("----- 4 -----");
    StdOut.println(a + " in base 16 = " + hex(a));
    StdOut.println("----- 5 -----");
    StdOut.println(a + (primo(a) ? "" : " NON") + " è un numero primo");
    StdOut.println("----- 6 -----");
    StdOut.println("Numeri primi da 1 a " + a);
    stampa_primi(a);

  }


}
