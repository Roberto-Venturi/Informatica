package ST0851_Programmazione.Lezioni;

public class Slide_06_Selezione {

  /*
      Risolvere i seguenti esercizi facendo uso esclusivamente delle caratteristiche di Java
      illustrate fino a questo blocco di slide.
  1.  Scrivere un programma che legge un intero e determina se è pari o dispari.
  */
  public static void slide_06_1() {
    int i = (int) (Math.random() * 100) + 1;
    if (i % 2 == 0) {
      StdOut.println(i + " è pari");
    } else {
      StdOut.println(i + " è dispari");
    }
  }

  /*
  2.  Risolvere l’esercizio precedente senza fare uso del comando if.
      Vedi esercizio Slide04_5_pd
  3.  Scrivere un programma che legge un intero e ne calcola il valore assoluto.
  */
  public static void slide_06_3() {
    int i = (int) (Math.random() * 100) - 50;
    if (i >= 0) {
      StdOut.println("Il valore assoluto di " + i + " è " + i);
    } else {
      StdOut.println("Il valore assoluto di " + i + " è " + -i);
    }
  }
  /*
  4.  Scrivere un programma che legge un intero x e stampa x in base 16 se 0 <= x <= 255
      e la stringa Fuori scala altrimenti.
  */
  public static void slide_06_4() {
    int x = (int) (Math.random() * 100) +1;
    if (x < 0 || x > 255) {
      StdOut.println(x + " è fuori scala");
    } else {
      int i1 = x / 16;
      char c1, c0;
      if (i1 < 10)
        c1 = (char) ('0' + i1);
      else
        c1 = (char) ('A' + i1 - 10);
      int i0 = x % 16;
      if (i0 < 10)
        c0 = (char) ('0' + i0);
      else
        c0 = (char) ('A' + i0 - 10);

      StdOut.println( x + " in base 10 = " + c1 + c0 + " in base 16");
    }
  }

  /*
  5.  Scrivere un programma che legge un intero e determina se è un quadrato perfetto,
      ovvero se esiste un intero y tale che x = y al quadrato.
  */
  public static void slide_06_5() {
    int a = (int) (Math.random() * 1000);
    int b = (int) Math.sqrt(a);
    if (b * b == a) {
      StdOut.println(a + " = " + b + "^2");
    } else {
      StdOut.println(a + " non è un quadrato perfetto");
    }
  }

  /*
  6.  Scrivere un programma che legge gli estremi di due intervalli chiusi [a,b] e [c,d]
      (si può assumere a<=b e c<=d) e determina se è [a,b] U [c,d] è un intervallo.
      In tal caso, il programma deve mostrarne gli estremi.
   */
  static void slide_06_6() {
    int a = (int) (Math.random() * 1000);
    int b = (int) (Math.random() * (1000 - a)) + a;
    int c = (int) (Math.random() * 1000);
    int d = (int) (Math.random() * (1000 - c)) + c;
    StdOut.println("Inputs: " + a + "..." + b + " U " + c + "..." + d);
    /* Casi possibili:
    //A B c d   -   A c B d   -   A c d B
    //c d A B   -   c A d B   -   c A B d
     */
    if (a <= c) { //A ? ? ?
      if (b < c) { //A B   C D
        StdOut.println("Non è un intervallo");
      } else { //A BC D o A C B D
        if (b < d) {  //A C B D
          StdOut.println("Intervallo (AcBd): " + a + ", " + d);
        } else {  //A C D B
          StdOut.println("Intervallo (AcdB): " + a + ", " + b);
        }
      }
    } else {  //C ? ? ?
      if (d < a) { //C D   A B
        StdOut.println("Non è un intervallo");
      } else {  //C AD B o C A D B
        if (d < b) {  //C A D B
          StdOut.println("Intervallo (cAdB): " + c + ", " + b);
        } else {  //C A B D
          StdOut.println("Intervallo (cABd): " + c + ", " + d);
        }
      }
    }
  }





  static void main(String[] args) {
    slide_06_1();
    StdOut.println("----- 3 -----");
    slide_06_3();
    StdOut.println("----- 4 -----");
    slide_06_4();
    StdOut.println("----- 5 -----");
    slide_06_5();
    StdOut.println("----- 6 -----");
    slide_06_6();
  }
}
