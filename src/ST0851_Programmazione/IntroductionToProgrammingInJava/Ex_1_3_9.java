import Progetti_Personali._RvLib;

public class Ex_1_3_9 {

    public static void main(String[] args) {
        int num = _RvLib.intInput(args, 0);
        double sum = 0;
        for (int i = 1; i <= num; i++) {
            double rand = Math.random();
            sum += rand;
            System.out.printf("Randdom()=%g average=%g", rand, sum/i);
            System.out.println();
        }
        System.out.printf("Average is %g\r\n", (sum/num));
    }
}
