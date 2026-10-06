package ST0851_Programmazione.Lezioni;

import java.util.Arrays;

public class Slide_12_Array_Permutazioni {

  //Scritta dal prof durante la lezione: necessito della registrazione per riscriverla
  public static int[] aggiungiElemento(int[] a, int j, int v) {
    return inserisci(v, a, j);
  }

  //Scritta dal prof durante la lezione: necessito della registrazione per riscriverla
  public static int[][] generaPermutazioni(int[] a) {
    int[][] permutazioni = new int[][]{{}};
    int[][] nuovePermutazioni = null;
    for (int i = 0; i < a.length; i++) {
      nuovePermutazioni = new int[permutazioni.length * (i + 1)][];
      int w = 0;
      for (int j = 0; j < permutazioni.length; j++) {
        for (int k = 0; k < permutazioni[j].length; k++) {
          nuovePermutazioni[w++] = aggiungiElemento(permutazioni[j], k, a[i]);
        }
      }
    }
    return nuovePermutazioni;
  }

  // return given array (1 item bigger)
  // with "value" inserted in position idx
  static int[] inserisci(int value, int[] a, int idx) {
    if (idx > a.length) return null;
    int[] result = new int[a.length + 1];
    for (int j = 0; j < idx; j++) result[j] = a[j];
    result[idx] = value;
    for (int j = idx; j < a.length; j++) result[j + 1] = a[j];
    return result;
  }

  static int[][] permutazioniIndice(int n) {
    if (n < 0) {
      return null;
    }
    if (n == 0) {
      return new int[][]{{}};
    }
    // recursively evaluate method with 1 less index
    int[][] sub = permutazioniIndice(n - 1);
    assert sub != null; //to avoid compiler warning
    // now create a new (external) bigger array
    int[][] res = new int[sub.length * n][];
    // and fill with shorter array more new index in every position
    for (int ri = 0; ri < n; ri++) {
      for (int si = 0; si < sub.length; si++) {
        res[n * si + ri] = inserisci(n, sub[si], ri);
      }
    }
    return res;
  }

  static int[][] permutazioniContenuto(int[] inta) {
    if (inta == null) {
      return null;
    }
    if (inta.length == 0) {
      return new int[][]{{}};
    }
    // recursively evaluate method with an array without last element
    int[] intaPrev = new int[inta.length - 1];
    for (int i = 0; i < intaPrev.length; i++)
      intaPrev[i] = inta[i];
    int[][] sub = permutazioniContenuto(intaPrev);
    // now create a new (external) bigger array
    assert sub != null; //to avoid compiler warning
    int[][] res = new int[sub.length * inta.length][];
    // and fill with shorter array more new element in every position
    for (int ii = 0; ii < inta.length; ii++) {
      for (int si = 0; si < sub.length; si++) {
        res[inta.length * si + ii] = inserisci(inta[inta.length - 1], sub[si], ii);
      }
    }
    return res;
  }

  static void main(String[] args) {
    int[][] r1 = permutazioniIndice(3);
    for (int i = 0; i < r1.length; i++) {
      StdOut.println(Arrays.toString(r1[i]));
    }

    StdOut.println("=====");
    int[] r2 = {15, -37, 21};
    int[][] r3 = permutazioniContenuto(r2);
    for (int i = 0; i < r3.length; i++) {
      StdOut.println(Arrays.toString(r3[i]));
    }
  }


}
