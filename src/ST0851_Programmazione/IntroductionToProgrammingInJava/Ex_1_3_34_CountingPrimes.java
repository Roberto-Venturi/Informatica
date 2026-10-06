import Progetti_Personali._RvLib;

public class Ex_1_3_34_CountingPrimes {

    public static boolean isPrime(int number) {
        if (number < 2) {
            return false;
        }
        int i;
        for (i = 2; i <= number / i; i++)
            if (number % i == 0) break;
        return (i > number / i);
    }

    public static void main(String[] args) {
        int number = _RvLib.intInput(args, 0);
        long start = System.currentTimeMillis();
        for (int j = 1; j <= number; j++) {
            if (isPrime(j)) {
                System.out.print(j + " ");
            }
        }
        long elapsed = System.currentTimeMillis() - start;
        System.out.println("\r\n elapsed  " + elapsed + " ms");
    }
}
