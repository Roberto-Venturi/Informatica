import Progetti_Personali._RvLib;

public class RandomInt {
    public static void main(String[] args) {
        int N = _RvLib.intInput(args, 0);
        double r = Math.random(); // uniform between 0 and 1
        int n = (int) (r * N); // uniform between 0 and N-1
        System.out.println(n);
    }
}