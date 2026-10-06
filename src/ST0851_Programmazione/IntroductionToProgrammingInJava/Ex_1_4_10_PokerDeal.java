import Progetti_Personali._RvLib;

import static java.lang.System.exit;

public class Ex_1_4_10_PokerDeal {

  public static void main(String[] args) {
    int deals = _RvLib.intInput(args, 0);
    if (deals < 1) {
      System.out.println("Deals must be positive");
      exit(-1);
    }
    if (deals > 10) {
      System.out.println("with a 52 cards desk, no more than 10 deals");
      exit(-1);
    }

    // ----- Desk initialization ----- //
    char[] suit = {'♥', '♦', '♣', '♠'};
    char[] rank = {'1', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K'};
    String[] deck = new String[suit.length * rank.length];
    for (int i = 0; i < suit.length; i++)
      for (int j = 0; j < rank.length; j++) {
        deck[rank.length * i + j] = "" + suit[i] + rank[j];
      }
    //_RvLib.printStringArray(deck); exit(0);

    boolean[] dealed = new boolean[suit.length * rank.length];
    for (; deals > 0; deals--) {
      // ----- Dealing 5 cards ----- //
      String[] cardsHand = new String[5];
      int cardIdx = (int) (Math.random() * deck.length);  /* first card */
      for (int ii = 0; ii < 5; ii++) {
        while (dealed[cardIdx]) {
          cardIdx = (int) (Math.random() * deck.length);
        }
        cardsHand[ii] = deck[cardIdx];
        dealed[cardIdx] = true;
      }
      _RvLib.stringArrayPrint(cardsHand); System.out.println();
    }
  }

}
