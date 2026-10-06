package ST0851_Programmazione.Lezioni;

public class Slide_05_Variabili {

  /*
  1.  Trovare un numero negativo di tipo int il cui narrowing a byte è positivo
      e un numero positivo di tipo int il cui narrowing a short è negativo.
      Scrivere un programma per verificare le risposte.
  */
  public static void slide_05_1() {
    int i = -129; //11111111 11111111 11111111 01111111
    byte b =  (byte) i; // 01111111
    StdOut.println("i=" + i + " b=" + b);
  }

  /*
  2.  Le stringhe risultanti dalla valutazione di "1" + 2 + 1 e 1 + 2 + "1" non sono uguali
      nonostante la somma tra numeri interi e la concatenazione tra stringhe siano operazioni
      associative. Perché?
      Perchè sono operazioni diverse tra loro e non esiste la proprietà associativa tra di esse

  3.  Scrivere un programma che calcoli e stampi circonferenza e area di un cerchio di raggio
      1024, usando una ragionevole approssimazione di π
  */
  public static void slide_05_3() {
    int r = 1024;
    double pi = 3.1415;
    double area = r * r * pi;
    double circo = 2 * r * pi;
    StdOut.println("r=" + r + " area=" + area + " circo=" + circo);
  }

  /*
  4.  Scrivere un programma che calcoli e stampi il valore di n *(n-1) / 2
      dove n è una variabile inizializzata con un numero intero positivo a piacere
  */
  public static void slide_05_4() {
    int i = (int) (Math.random() * 100) + 1;
    double target = i * (double) (i-1) / 2;
    StdOut.println("i=" + i + " target=" + target);
  }

  /*
  5.  Usando esclusivamente gli operatori illustrati nelle slide precedenti, scrivere un programma
      che stampi P se x è pari e D se x è dispari, dove x è una variabile di tipo int
      inizializzata con un valore a piacere.
      Suggerimento: iniziare con una variante dell’esercizio in cui il programma stampa Q invece
      di D quando x è dispari.
   */
  public static void slide_05_5_pq() {
    int i = (int) (Math.random() * 100) + 1;
    int r = i % 2;  //r=0 se pari r=1 se dispari
    char pod = (char) ('P' + r); // P se pari, Q se dispari
    StdOut.println("i=" + i + " " + pod);
  }

  public static void slide_05_5_pd() {
    int i = (int) (Math.random() * 100) + 1;
    int r = i % 2;  //r=0 se pari r=1 se dispari
    char pod = (char) ('P' + r * ('D' - 'P')); // P se pari, D se dispari
    StdOut.println("i=" + i + " " + pod);
  }

  static void main(String[] args) {
    slide_05_1();
    StdOut.println("----- 3 -----");
    slide_05_3();
    StdOut.println("----- 4 -----");
    slide_05_4();
    StdOut.println("----- 5 P Q -----");
    slide_05_5_pq();
    StdOut.println("----- 5 P D -----");
    slide_05_5_pd();

  }

}
