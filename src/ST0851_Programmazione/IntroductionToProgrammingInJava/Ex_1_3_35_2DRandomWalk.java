import Progetti_Personali._RvLib;

import static java.lang.System.exit;

public class Ex_1_3_35_2DRandomWalk {

    public static void main(String[] args) {
        int maxXY = _RvLib.intInput(args, 0);
        if (maxXY <= 0) {
            System.out.println("Invalid input!");
            exit(0);
        }
        int x = 0, y = 0, moves = 0, move = 0;
        do {
            move = (int) (Math.random() * 4);
            moves++;
            switch (move) {
                case 0:
                    x++;
                    if (x >= maxXY) {
                        System.out.print("\r\nTouched right boundary ");
                    }
                    break;
                case 1:
                    x--;
                    if (x <= -maxXY) {
                        System.out.print("\nTouched left boundary ");
                    }
                    break;
                case 2:
                    y++;
                    if (y >= maxXY) {
                        System.out.print("\nTouched top boundary ");
                    }
                    break;
                case 3:
                    y--;
                    if (y <= -maxXY) {
                        System.out.print("\nTouched bottom boundary ");
                    }
                    break;
            }
            //System.out.print(x + "," + y + "  ");
        } while (x > -maxXY && x < maxXY && y > -maxXY && y < maxXY);
        System.out.println("in " + moves + " moves");
    }
}
