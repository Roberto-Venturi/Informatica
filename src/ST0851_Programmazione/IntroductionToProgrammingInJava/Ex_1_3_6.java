public class Ex_1_3_6 {
    public static void  For1 () {
        System.out.println ("for (i = 0, j = 0; i < 10; i++) j += i;");
        int i, j;
        for (i = 0, j = 0; i < 10; i++) {
            j += i;
            System.out.print(j + "  ");
        }
        System.out.println();
        System.out.println();
    }

    public static void  For2 () {
        System.out.println ("for (i = 0, j = 1; i < 10; i++) j += j;");
        int i, j;
        for (i = 0, j = 1; i < 10; i++) {
            j += j;
            System.out.print(j + "  ");
        }
        System.out.println();
        System.out.println();
    }

    public static void  For3 () {
        System.out.println ("for (j = 0; j < 10; j++) j += j;");
        int i, j;
        for (j = 0; j < 10; j++) {
            j += j;
            System.out.print(j + "  ");
        }
        System.out.println();
        System.out.println();
    }

    public static void  For4 () {
        System.out.println ("for (i = 0, j = 0; i < 10; i++) j += j++;");
        int i, j;
        for (i = 0, j = 0; i < 10; i++) {
            j += j++;
            System.out.print(j + "  ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        For1();
        For2();
        For3();
        For4();
    }
}
