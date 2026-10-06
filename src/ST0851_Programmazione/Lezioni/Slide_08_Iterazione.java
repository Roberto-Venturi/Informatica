package ST0851_Programmazione.Lezioni;

public class Slide_08_Iterazione {

  /*
  1. Verificare se un numero è primo o no
  */
  public static boolean slide_08_1() {
    int x = (int) (Math.random() * 100) + 1;
    for (int i = 2; i <= x / 2; i++) {
      if (x % i == 0) {
        StdOut.println(x + " NON è un numero primo");
        return false;
      }
    }
    StdOut.println(x + " è un numero primo");
    return true;
  }

  /*
  2. Determinare il numero di bit a 1 nella rappresentazione in base 2
     di un numero intero non negativo
  */
  public static void slide_08_2() {
    int x = (int) (Math.random() * 100);
    int bit1 = 0;
    int x2 = x;
    while (x > 0) {
      bit1 += x % 2;
      x = x / 2;
    }
    StdOut.println(Integer.toBinaryString(x2));  // per verifica
    StdOut.println("La rappresentazione di " + x2 + " ha " + bit1 + " bit a 1");
  }

  /*
  3. Calcolare il prodotto di due numeri non negativi usando somme successive
  */
  public static void slide_08_3() {
    int x = (int) (Math.random() * 10) + 1;
    int y = (int) (Math.random() * 10) + 1;
    if (x < y) {
      int temp = x;
      x = y;
      y = temp;
    }
    int x2 = x;
    for (int i = 2; i <= y; i++) {
      x2 += x;
    }
    StdOut.println(x + " * " + y + " = " + x2);
  }

  /*
  4. Calcolare quoziente e resto della divisione tra due numeri
     positivi usando differenze successive
  */
  public static void slide_08_4() {
    int x = (int) (Math.random() * 100) + 1;
    int y = (int) (Math.random() * 10) + 1;
    int q = 0;
    int x2 = x;
    while (x2 > y) {
      x2 -= y;
      q++;
    }
    StdOut.print(x + " / " + y + " = " + q);
    if (x2 > 0) {
      StdOut.print(" con resto " + x2);
    }
    StdOut.println();
  }

  /*
  5. Riscrivere il programma che calcola la media aritmetica
     usando un ciclo while anziché il do-while.
     Evidenziare pregi e difetti delle due versioni.
     ---
     Non rilevo pregi o difetti significativi.
     L'unica differenza delle due versioni sottostanti è
     l'inizializzazione della variabile x (e, ovviamente, il ciclo)
  */
  public static void media() {
    int somma = 0;
    int n = 0;
    int x; // ATTENZIONE: x non è inizializzata
    do {
      StdOut.print("Dammi un numero (<=0 per terminare): ");
      x = StdIn.readInt();
      if (x > 0) {
        somma = somma + x;
        n++;
      }
    } while (x > 0);
    if (n == 0)
      StdOut.println("Nessun numero introdotto");
    else
      StdOut.println("Media = " + (double) somma / n);
  }

  public static void slide_08_5() {
    int somma = 0;
    int n = 0;
    int x = 1;
    while (x > 0) {
      StdOut.print("Dammi un numero (<=0 per terminare): ");
      x = StdIn.readInt();
      if (x > 0) {
        somma = somma + x;
        n++;
      }
    }
    if (n == 0)
      StdOut.println("Nessun numero introdotto");
    else
      StdOut.println("Media = " + (double) somma / n);
  }


  /*
  6. Scrivere un programma che converte
     un numero intero non negativo in base 2
  */
  public static void slide_08_6() {
    int x = (int) (Math.random() * 100);
    String res = "";
    int x2 = x;
    while (x2 > 0) {
      res = (x2 % 2) + res;
      x2 = x2 / 2;
    }
    StdOut.println(x + " in base 2 = " + res);
  }

  static void main() {
    for (int i = 1; i <= 20; i++) {
      slide_08_1();
    }
    StdOut.println("----- 2 -----");
    slide_08_2();
    StdOut.println("----- 3 -----");
    slide_08_3();
    StdOut.println("----- 4 -----");
    slide_08_4();
    StdOut.println("----- 5 -----");
    //media();
    //slide_08_5();
    StdOut.println("----- 6 -----");
    slide_08_6();
  }

}
