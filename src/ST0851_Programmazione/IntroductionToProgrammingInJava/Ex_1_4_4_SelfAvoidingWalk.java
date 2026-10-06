import Progetti_Personali._RvLib;

public class Ex_1_4_4_SelfAvoidingWalk {

  static int size = 15; //dimensione della griglia
  static int tries = 20;  //numero di test da eseguire

  public static void main(String[] args) {
    // Do T random self-avoiding walks in an N-by-N lattice

    int deadEnds = 0;

    for (int t = 0; t < tries; t++) {
      boolean[][] a = new boolean[size][size];
      int x = size / 2, y = size / 2;
      while (x > 0 && x < size - 1 && y > 0 && y < size - 1) { // Check for dead end and make a random move.
        a[x][y] = true;
        if (a[x - 1][y] && a[x + 1][y] && a[x][y - 1] && a[x][y + 1]) {
          deadEnds++;
          break;
        }
        double r = Math.random();
        if (r < 0.25) {
          if (!a[x + 1][y]) x++;
        } else if (r < 0.50) {
          if (!a[x - 1][y]) x--;
        } else if (r < 0.75) {
          if (!a[x][y + 1]) y++;
        } else /* if (r < 1.00) */ {
          if (!a[x][y - 1]) y--;
        }
      }
      _RvLib.booleanMatrixPrint(a);
    }
    System.out.println(100 * deadEnds / tries + "% dead ends");
  }

}
