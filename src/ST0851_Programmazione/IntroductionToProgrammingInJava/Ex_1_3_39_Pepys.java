import Progetti_Personali._RvLib;

import static java.lang.System.exit;

public class Ex_1_3_39_Pepys {

    public static boolean roll1dice() {
        for (int ii = 0; ii < 6; ii++) {
            int dice = 1 + (int) (Math.random() * 6);
            //System.out.print(dice + "  ");
            if (dice == 1) {
                return true;
            }
        }
        return false;
    }

    public static boolean roll2dices() {
        for (int ii = 0; ii < 12; ii++) {
            int dice1 = 1 + (int) (Math.random() * 6);
            int dice2 = 1 + (int) (Math.random() * 6);
            //System.out.print(dice1 + "," + dice2 + "  ");
            if (dice1 == 1 && dice2 == 1) {
                return true;
            }
        }
        return false;
    }

    public static int doTest() {
        int turns = 0;
        boolean b1 = false, b2 = false;
        do {
            turns++;
            b1 = roll1dice();
            b2 = roll2dices();
        } while (!b1 && !b2);
        if (b1 && b2) {
            System.out.println("Got 1 on a dice AND 1 on two dices in " + turns + " turns");
            return 0;
        } else if (b1) {
            System.out.println("Got 1 on a dice in " + turns + " turns");
            return 1;
        } else /* b2*/  {
            System.out.println("Got 1 on two dices in " + turns + " turns");
            return 2;
        }
    }

    public static void main(String[] args) {
        int testNum = _RvLib.intInput(args, 0);
        if (testNum <= 0) {
            System.out.println("Invalid input!");
            exit(0);
        }
        int[] goals = new int[3];
        for (int ii = 0; ii < testNum; ii++) {
            goals[doTest()]++;
        }
        System.out.println("1 on a dice = " + (goals[0]+goals[1]) + "/" +testNum +"   1 on two dice = " + (goals[0]+goals[2]) + "/" +testNum);
    }

}
