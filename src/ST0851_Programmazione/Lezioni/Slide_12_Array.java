package ST0851_Programmazione.Lezioni;

import java.util.Arrays;

public class Slide_12_Array {

  /*
  1. Definire il metodo void inverti(int[] a) che inverte l’ordine degli elementi in a.
     Ripetere l’esercizio, questa volta definendo un metodo int[] inverti(int[] a) che
     non modifica a.
   */

  static void inverti(int[] a) {
    for (int i = 0; i < a.length / 2; i++) {
      int buf = a[i];
      a[i] = a[a.length - i - 1];
      a[a.length - i - 1] = buf;
    }
  }

  static int[] invertib(int[] a) {
    int[] b = new int[a.length];
    for (int i = 0; i < a.length; i++)
      b[a.length - i - 1] = a[i];
    return b;
  }

  /*
  2. Definire i metodi int min_array(int[] a) e int max_array(int[] a)
     che determinano rispettivamente l’elemento più piccolo e più grande di a.
   */
  static int min_array(int[] a) {
    int min = Integer.MAX_VALUE;
    for (int i = 0; i < a.length; i++) {
      if (a[i] < min)
        min = a[i];
    }
    return min;
  }

  static int max_array(int[] a) {
    int max = Integer.MIN_VALUE;
    for (int i = 0; i < a.length; i++) {
      if (a[i] > max)
        max = a[i];
    }
    return max;
  }

  /*
  3. Date due sequenze a1,…, am e b1,…, bn
  la loro concatenazione è la sequenza a1,…, am, b1,…, bn
  Definire un metodo int[] concatena(int[] a, int[] b) che calcola la concatenazione di a e b.
   */
  static int[] concatena(int[] a, int[] b) {
    int[] result = new int[a.length + b.length];
    for (int i = 0; i < a.length; i++) {
      result[i] = a[i];
    }
    for (int i = 0; i < b.length; i++) {
      result[a.length + i] = b[i];
    }
    return result;
  }




  /*
  4. Date due sequenze a1,…, am e b1,…, bn di elementi sui quali è definita una
     nozione d’ordine <= diciamo che la prima sequenza è lessicograficamente minore
     o uguale della seconda se (m ≤ n) e (ai ≤ bi) per ogni 1 ≤ i ≤ m.
     Definire un metodo boolean le(int[] a, int[] b) che determina se a
     è lessicograficamente minore o uguale a b.
   */

  static boolean le(int[] a, int[] b) {
    if (a.length > b.length)
      return false;
    for (int i = 0; i < a.length; i++) {
      if (a[i] > b[i])
        return false;
    }
    return true;
  }

  /*
  5. Difficile! Definire un metodo int[][] permutazioni(int n) che calcola tutte
     le permutazioni dei numeri interi da 1 a n.
     Suggerimento: definire un metodo ausiliario int[] inserisci(int k, int[] a, int i)
     che crea un array uguale ad a ad eccezione del fatto che l’elemento k
     è stato inserito in posizione i.
     ---
     Vedi classe "Slide_12_Array_Permutazioni"
   */


  public static void main(String[] args) {
    int[] uno = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    StdOut.println("----- 1a ----- ");
    StdOut.println(Arrays.toString(uno));
    inverti(uno);
    StdOut.println(Arrays.toString(uno));
    uno = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    StdOut.println("----- 1b ----- ");
    StdOut.println(Arrays.toString(invertib(uno)));
    StdOut.println(Arrays.toString(uno));
    StdOut.println("----- 2 ----- ");
    for (int i = 0; i < uno.length; i++)
      uno[i] = (int) (Math.random() * 100);
    StdOut.println(Arrays.toString(uno));
    StdOut.println("il minimo è  " + min_array(uno) + " ed il massimo è " + max_array(uno));
    StdOut.println("----- 3 ----- ");
    int[] due = new int[5];
    for (int i = 0; i < due.length; i++)
      due[i] = (int) (Math.random() * 100);
    StdOut.println(Arrays.toString(uno) + " + " + Arrays.toString(due));
    StdOut.println(Arrays.toString(concatena(uno, due)));
    StdOut.println("----- 4 ----- ");
    int[] tre = new int[3];
    for (int i = 0; i < tre.length; i++)
      tre[i] = (int) (Math.random() * 100);
    StdOut.println(Arrays.toString(tre) + ", " + Arrays.toString(due)
      + (le(tre, due) ? " IS le" : " is NOT le"));
  }


}
