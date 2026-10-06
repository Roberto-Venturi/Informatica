import java.util.Arrays;

public class Ex_1_4_7 {
  public static void main(String[] args) {
    int[] a = new int[10];
    for (int i = 0; i < 10; i++)
      a[i] = 9 - i;               /* 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 */
    System.out.println(Arrays.toString(a));
    for (int i = 0; i < 10; i++)
      a[i] = a[a[i]];             /* 0, 1, 2, 3, 4, 4, 3, 2, 1, 0 */
    System.out.println(Arrays.toString(a));
  }
}
