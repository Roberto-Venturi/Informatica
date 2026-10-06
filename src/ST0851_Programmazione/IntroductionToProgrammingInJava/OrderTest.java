public class OrderTest {
    public static void main(String[] args) {
        if (args.length < 3)
            System.out.println("Mi aspetto 3 numeri!");
        else {
            double a, b, c;
            a = Double.parseDouble(args[0]);
            b = Double.parseDouble(args[1]);
            c = Double.parseDouble(args[2]);
            if (a<b && b<c) {
                System.out.println(a +" < " + b + " < " + c);
            } else if (a>b && b>c) {
                System.out.println(a +" > " + b + " > " + c);
            } else {
                System.out.println(a +", " + b + ", " + c + " are not ordered");
            }
        }
    }
}