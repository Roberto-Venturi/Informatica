import Progetti_Personali._RvLib;

public class Ex_1_3_33_ISBN {

    public static void main(String[] args) {
        String s09 = _RvLib.stringInput(args, 0); // "020131452";
        if (s09.length() == 10) {
            s09 = s09.substring(0, 8);
        }
        if (s09.length() != 9) {
            System.out.println("Input the first 9 chars of ISBN code!");
        } else {
            int sum = 0;
            for (int i = 0; i < 9; i++) {
                sum += (10-i) * (s09.charAt(i) - '0');
                //System.out.println("sum=" +sum);
            }
            int q = 11 - sum % 11;
            //System.out.println("q=" +q);
            String msg = "Last digit is ";
            if (q==10) {
                msg += 'x';
            } else {
                msg += q;
            }
            System.out.println(msg);
        }
    }
}
