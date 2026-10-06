package ST0851_Programmazione.IntroductionToProgrammingInJava;

import Progetti_Personali._RvLib;

public class Ex_1_4_3_PrimeSieve {
  public static void main(String[] args) { // Print the number of primes <= N.
    int N = _RvLib.intInput(args, 0);
    boolean[] isPrime = new boolean[N + 1];
    for (int i = 2; i <= N; i++)
      isPrime[i] = true;
    for (int i = 2; i <= N / i; i++) {
      if (isPrime[i]) { // Mark multiples of i as nonprime.
        for (int j = i; j <= N / i; j++)
          isPrime[i * j] = false;
      }
    }
// Count the primes.
    int primes = 0;
    for (int i = 2; i <= N; i++)
      if (isPrime[i]) primes++;
    System.out.println(primes);
  }

}
