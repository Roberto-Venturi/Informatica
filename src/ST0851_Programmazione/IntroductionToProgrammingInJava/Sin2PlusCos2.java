import Progetti_Personali._RvLib;

public class Sin2PlusCos2 {
    public static void main(String[] args) {
        double a=0, sina, cosa;
        a = _RvLib.doubleInput(args, 0);
        sina=Math.sin(a);
        cosa=Math.cos(a);
        System.out.print("a=" + a  + " Si2(" + a +")=" + sina);
        System.out.print(" Cos(" + a + ")=" +cosa);
        System.out.print(" Sin2(" + a +")+Cos2(" + a + ")=");
        System.out.println(sina*sina +cosa*cosa);
    }
}