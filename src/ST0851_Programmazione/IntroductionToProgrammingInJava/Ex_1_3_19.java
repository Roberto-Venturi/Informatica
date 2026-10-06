import Progetti_Personali._RvLib;

public class Ex_1_3_19 {

    public static void main(String[] args) {
        final char[] alpha = {'0', '1', '2', '3', '4', '5' ,'6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        long i = _RvLib.longInput(args, 0);
        int k = _RvLib.intInput(args, 1);
        StringBuilder rev = new StringBuilder();
        long q = i;
        while (q >= k ) {
            int r = (int) q % k;
            System.out.printf("q=%d  k=%d  r=%d\r\n", q, k, r);
            q -= r;
            q /=k;
            rev.append(alpha[r]);
        }
        rev.append(alpha[(int)q]);
        System.out.println(rev.reverse());
    }
}
