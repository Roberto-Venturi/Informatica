import Progetti_Personali._RvLib;

public class Gambler {
    public static void main(String[] args) { // Run T experiments that start with $stake
// and terminate on $0 or $goal.
        System.out.println("INPUTS: initial stake, walkaway goal, number of trials");
        int stake = _RvLib.intInput(args, 0);
        int goal = _RvLib.intInput(args, 1);
        int T = _RvLib.intInput(args, 2);
        int bets = 0;
        int wins = 0;
        for (int t = 0; t < T; t++) { // Run one experiment.
            int cash = stake;
            while (cash > 0 && cash < goal) { // Simulate one bet.
                bets++;
                if (Math.random() < 0.5) cash++;
                else cash--;
            } // Cash is either 0 (ruin) or $goal (win).
            if (cash == goal) wins++;
        }
        System.out.println(100 * wins / T + "% wins");
        System.out.println("Avg # bets: " + bets / T);
    }
}
