import java.util.Arrays;

public class Ex_1_4_13_MatrixTranspose {

  public static void main(String[] args) {
    int rows = 5, cols = 5; /* ATTENTION: 10 is maximum size */
    // ----- Matrix initializazion ----- //
    String[][] texts = new String[rows][cols];
    for (int row = 0; row < rows; row++) {
      for (int col = 0; col < cols; col++) {
        texts[row][col] = "" + row + col;
      }
      System.out.println(Arrays.toString(texts[row]));
    }
    System.out.println("----------------");
    for (int row = 0; row < rows; row++) {
      for (int col = row; col < cols; col++) {
        String temp = texts[row][col];
        texts[row][col] = texts[col][row];
        texts[col][row] = temp;
      }
      System.out.println(Arrays.toString(texts[row]));
    }
  }

}
