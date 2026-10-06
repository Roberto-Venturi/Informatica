import java.util.Arrays;

public class Ex_1_4_8 {
  public static void main(String[] args) {
    int N = 10;
    int[] a = new int[N];
    a[0] = 1;
    a[1] = 1;                   /* 0 1 0 0 0 0 0 0 0 0 */
    for (int i = 2; i < N; i++)
      a[i] = a[i-1] + a[i-2];   /* 0 1 1 2 3 5 8 13 21 34 55 */
    System.out.println(Arrays.toString(a));
  }
}
