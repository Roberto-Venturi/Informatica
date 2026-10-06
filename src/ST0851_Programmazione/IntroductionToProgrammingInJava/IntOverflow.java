public class IntOverflow {
    public static void main(String[] args) {
        double a = 2147483647;
        System.out.println("a=" + a);
        System.out.println("a+1=" + (a+1));
        System.out.println("2-a=" + (2-a));
        System.out.println("-2-a=" + (-2-a));
        System.out.println("2*a=" + (2*a));
        System.out.println("4*a=" + (4*a));	}
}