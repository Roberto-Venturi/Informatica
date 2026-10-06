import Progetti_Personali._RvLib;

public class Ex_1_3_14 {

    public static void main(String[] args) {
        int num = _RvLib.intInput(args, 0);
        if (num < 0) {
            System.out.println("Errore: il numero deve essere positivo!");
        } else if (num == 0) {
            System.out.println("1");
        } else {
            if (num > 31) {
                num = 31;
                System.out.println("2^32 overflows Integer capacity in Java");
            }
            for (int i = 1; i <= num; i++) {
                System.out.printf("%d - %d  ", i, (int) Math.pow(2.0, (double) i));
                System.out.println();
            }
        }
    }
}
