import Progetti_Personali._RvLib;

public class Ex_1_4_6_BooleanMatrixPrint {

  public static void main(String[] args) {
    boolean[][] bm = new boolean[10][10];  /* ATTENTION: 10 is maximum size */
    for (int i = 0; i < bm.length; i++) {
      for (int j = 0; j < bm[i].length; j++) {
        bm[i][j] = Math.random() < 0.5;
      }
    }
  _RvLib.booleanMatrixPrint(bm, true);
  }


}
