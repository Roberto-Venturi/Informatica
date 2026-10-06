package ST0851_Programmazione.IntroductionToProgrammingInJava;

public class Ex_1_4_20 {

  static double[] dist = new double[13];

  static void calculate(int times) {
    for (int time = 0; time < times; time++) {
      for (int i = 1; i <= 6; i++)
        for (int j = 1; j <= 6; j++)
          dist[i + j] += 1.0;
    }
    for (int k = 1; k <= 12; k++) {
      dist[k] /= 36.0;
      dist[k] /= times;
    }
  }

  private static void arrayPrint() {
    for (int i = 1; i < dist.length; i++)
      System.out.println(i + ": " + dist[i]);
  }

  static void main(String[] args) {
    calculate(100);
    arrayPrint();
  }

}
