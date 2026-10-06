public class Ex_1_3_13_Fibonacci {
    public static void main(String[] args) {
        int f = 0, g = 1;
        for (int i = 0; i <= 15; i++) {
            System.out.println("i=" + i +"\tf=" + f +"\tg=" + g);
            f = f + g;
            g = f - g;
        }
    }
}
