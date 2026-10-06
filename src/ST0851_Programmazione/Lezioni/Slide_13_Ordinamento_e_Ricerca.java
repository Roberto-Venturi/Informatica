package ST0851_Programmazione.Lezioni;

import java.util.Arrays;

public class Slide_13_Ordinamento_e_Ricerca {

  public static int ricercaBinariaRV(int[] a, int x) {
    int i = 1;  //Mancante nella lezione: non compilava
    int j = a.length - 1;
    int k = (i + j) / 2;
    while ((i != j) && (a[k] != x)) {
      if (a[k] == x) return k;
      if (a[k] > x) {
        j = k;
      } else {
        i = k;
      }
      k = (i + j) / 2;
      if (i == k) i++;  //Mancante nella lezione: loop senza fine se elemento non trovato
    }
    return ((a[k] == x) ? k : -1);
  }


  public static int[] insertionSort(int[] a) {
    int[] res = new int[a.length];
    for (int i = 0; i < a.length; i++) {
      int j = trovaIndiceDoveInserire(res, a[i], i);
      inserisciLM(res, j, a[i]);
    }
    return res;
  }


  public static int trovaIndiceDoveInserire(int[] a, int v, int last) {
    for (int i = 0; i < last; i++) {
      if (a[i] > v) return i;
    }
    return last;
  }

  public static void inserisciLM(int[] a, int i, int x) {
    if ((i < 0) || (i >= a.length)) {
      return;
    }
    for (int j = i; j < a.length; j++) {
      int tmp = a[j];
      a[j] = x;
      x = tmp;
    }
  }

  // Versione secondo me migliore
  public static void inserisciRV(int[] a, int i, int x) {
    if ((i < 0) || (i >= a.length)) {
      return;
    }
    for (int j = a.length - 1; j > i; j--) {
      a[j] = a[j - 1];
    }
    a[i] = x;
  }

  public static void selectionSort(int[] a) {
    for (int i = 0; i < a.length; i++) {
      int m = i;
      for (int j = i + 1; j < a.length; j++) {
        if (a[m] > a[j]) {
          m = j;
        }
      }
      if (i != m) swap(a, i, m);
    }
  }

  public static void swap(int[] a, int i, int j) {
    int temp = a[i];
    a[i] = a[j];
    a[j] = temp;
  }


  public static void mergeSortRV(int[] a) {
    mergeSortRV(a, 0, a.length - 1);
  }

  // merge two CONSECUTIVE parts of array a (from, middle) + (middle + 1, to)
  public static void mergeRV(int[] a, int from, int middle, int to) {
    //StdOut.println("   (mergeRV(" + Arrays.toString(a) + ", " + from + ", " + middle + ", " + to + ")");
    int[] c = new int[to - from + 1];
    int ic = 0;
    int ia = from;
    int ib = middle + 1;
    while (ia <= middle) {
      while (ib <= to && a[ib] < a[ia]) {
        c[ic++] = a[ib++];
      }
      c[ic++] = a[ia++];
    }
    while (ib < to) {
      c[ic++] = a[ib++];
    }
    //copy temporary array to original array
    for (int i = 0; i < ic; i++) {
      a[from + i] = c[i];
    }
  }

  public static void mergeSortRV(int[] a, int from, int to) {
    //StdOut.println("(mergeSortRV(" + Arrays.toString(a) + ", " + from + ", " + to + ")");
    if (from < to) {
      int idx = (to + from) / 2;
      mergeSortRV(a, from, idx);
      mergeSortRV(a, idx + 1, to);
      mergeRV(a, from, idx, to);
      int[] aa = new int[]{};
    } //else return
  }
  /*
    mergesort({3, 2, 1, 9, 5, 6, 4, 8, 7});
    ms(0, 8)
    ms(0, 4)                                           ms(5, 8)
    ms(0, 2)                       ms(3, 4)            ms(5, 6)           ms(7, 8)
    ms(0, 1)            ms(2, 2)   ms(3, 3) ms(4, 4)   ms(5, 5) ms(6,6)   ms(7, 7) ms(8, 8)
    ms(0, 0) ms (1,1)


    mergesort({3, 2, 1, 9, 5, 6, 4, 8});
    ms(0, 7)
    ms(0, 3)                                ms(5, 8)
    ms(0, 1)            ms(2, 3)            ms(5, 6)           ms(7, 8)
    ms(0, 0) ms(1, 1)   ms(2, 2) ms(3, 3)   ms(5, 5) ms(6,6)   ms(7, 7) ms(8,8)

   */


  public static void mergeSort(int[] a, int from, int to) {
    if (from < to) {
      mergeSort(a, from, (to - from / 2));
      mergeSort(a, (to + from) / 2, to);
      //merge(a, from, (from + to) / 2, to);
      int[] aa = new int[]{};
    }

  }

  //  public static int[] mergesort(int[] a) {
//    if (a.length <= 1) return a;
//    return merge(copy(a,0, a.length/2));
//  }
//
//  public static int[] copy(int[] a, int left, int right) {
//    int[] res = new int[right - left];
//    for (int i = left;  i < right; i++) {
//      res[i - left] = a[i];
//    }
//    return res;
//  }

  public static int[] merge(int[] a, int[] b) {
    int[] c = new int[a.length + b.length];
    int ic = 0;
    int ia = 0;
    int ib = 0;
    while (ia < a.length) {
      while (ib < b.length && b[ib] < a[ia]) {
        c[ic++] = b[ib++];
      }
      c[ic++] = a[ia++];
    }
    while (ib < b.length) {
      c[ic++] = b[ib++];
    }
    return c;
  }

  //------------------------//
  //----- METHODS TEST -----//
  //------------------------//

  static void searchTest() {
    int[] a0 = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31};
    StdOut.println(Arrays.toString(a0) + "   4 => " + ricercaBinariaRV(a0, 4));
    StdOut.println(Arrays.toString(a0) + "  11 => " + ricercaBinariaRV(a0, 11));
    StdOut.println(Arrays.toString(a0) + "  34 => " + ricercaBinariaRV(a0, 34));
    StdOut.println(Arrays.toString(a0) + "  17 => " + ricercaBinariaRV(a0, 17));
    StdOut.println(Arrays.toString(a0) + "   1 => " + ricercaBinariaRV(a0, 1));
  }

  static void sortTest() {
    int[] a1 = {3, 2, 1, 9, 5, 6, 4, 8, 7};
    StdOut.println(Arrays.toString(a1));
    StdOut.println(Arrays.toString(insertionSort(a1)));

    StdOut.println("-----");
    StdOut.println(Arrays.toString(a1));
    selectionSort(a1);
    StdOut.println(Arrays.toString(a1));

    StdOut.println("-----");
    int[] a2 = {1, 3, 4, 9};
    int[] a3 = {2, 5, 6, 7, 8};
    StdOut.println(Arrays.toString(a2) + "   +   " + Arrays.toString(a3));
    StdOut.println(Arrays.toString(merge(a2, a3)));
  }

  static void mergeRV_mergeSortRV_Test() {
    StdOut.println("-----");
    int[] a4 = {0, 1, 2, 7, 9, 3, 4, 5, 6, 8};
    StdOut.println(Arrays.toString(a4));
    mergeRV(a4, 0, 4, 9);
    StdOut.println(Arrays.toString(a4) + " with mergeRV(a, 0, 4, 9)");

    StdOut.println("-----");
    int[] a5 = {3, 2, 1, 9, 5, 6, 4, 8, 7};
    StdOut.println(Arrays.toString(a5));
    mergeSortRV(a5);
    StdOut.println(Arrays.toString(a5) + " with mergeSortRV(a)");
  }

  public static void timingTest() {
    final int COUNT = 100000;
    StdOut.println("----- " +COUNT + " SORT TIMING -----");
    double tStart;
    int[] original = {3, 2, 1, 9, 5, 6, 4, 8, 7};
    int[] work = null, work2 = null;
    //--- INSERTION SORT ---
    tStart = System.currentTimeMillis();
    for (int round = 0; round < COUNT; round++) {
      work = Arrays.copyOf(original, original.length);
      work2 = insertionSort(work);
    }
    StdOut.println(Arrays.toString(work2) + "   INSERTION time " + (System.currentTimeMillis() - tStart));
    //--- SELECTION SORT ---
    tStart = System.currentTimeMillis();
    for (int round = 0; round < COUNT; round++) {
      work = Arrays.copyOf(original, original.length);
      selectionSort(work);
    }
    StdOut.println(Arrays.toString(work) + "   SELECTION time " + (System.currentTimeMillis() - tStart));
    //--- MERGE SORT ---
    tStart = System.currentTimeMillis();
    for (int round = 0; round < COUNT; round++) {
      work = Arrays.copyOf(original, original.length);
      mergeSortRV(work);
    }
    StdOut.println(Arrays.toString(work) + "   MERGE time " + (System.currentTimeMillis() - tStart));

  }

  public void main(String args[]) {
    //searchTest(); StdOut.println("-----");
    //sortTest(); StdOut.println("-----");
    mergeRV_mergeSortRV_Test();
    //timingTest();

  }

}
