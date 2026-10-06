import Progetti_Personali._RvLib;

public class NewtonSqr {
    public static void main(String[] args) {
        double c = _RvLib.doubleInput(args, 0);
        double epsilon = 1e-15;
        double t = c;
        while (Math.abs(t - c / t) > epsilon * t) { // Replace t by the average of t and c/t.
            System.out.println(t + "  ");
            t = (c / t + t) / 2.0;
        }
        System.out.printf("Sqr(%g) = %g", c, t);
    }
}
