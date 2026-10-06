import Progetti_Personali._RvLib;

import static java.lang.System.exit;

public class RandomTest {

    public static void main(String[] args) {
        int loops = _RvLib.intInput(args, 0);
        if (loops <= 0) {
            System.out.println("Invalid input!");
            exit(0);
        }
        int [] hits = new int [4];
        for (int i = 0; i < loops; i++) {
            int move = (int) (Math.random() * 4);
            hits[move] ++;
        }
        System.out.println("After " + loops + " turns: " + hits[0] + ", " + hits[1] + ", " + hits[2] + ", " + hits[3] + ", " );
    }
}
