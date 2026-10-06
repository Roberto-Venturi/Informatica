package ST0851_Programmazione.Lezioni;

/**
 * Gioco del Mastermind come presentato nella slide 13
 * Ho aggiunto le righe di stampa che ritengo necessarie o utili
 * all'utilizzo del programma da parte di chi non lo ha scritto.
 *
 * Nel package <i>Progetti_Personali/Mastermind</i> la mia versione
 */
public class Slide_13_Mastermind {

  public static void main(String[] args) {
    //ho aggiunto le istruzioni di stampa per chiarezza all'utilizzatore
    StdOut.println("===== Giochiamo a Mastermind! =====\n");
    StdOut.print("Il codice da indovinare quante cifre avrà? ");
    int n = scegliLunghezzaSequenza();
    StdOut.println("Ora scegli la sequenza da indovinare (poi ... dimenticala)");
    int[] segreto = inserisciSequenza(n);
    StdOut.println("\nBene: iniziamo!\n");
    int[] risultato = {0, 0};
    while (risultato[0] < n) {
      int[] guess = inserisciSequenza(n);
      risultato = confronta(segreto, guess);
      //l'istruzione seguente mancava nella slide ed è fondamentale
      StdOut.println("Neri: " + risultato[0] + " bianchi: " + risultato[1]);
    }
    StdOut.println("Sequenza indovinata!");
  }

  public static int[] inserisciSequenza(int n) {
    int[] guess = new int[n];
    for (int i = 0; i < n; i++) {
      StdOut.print("Inserire l'elemento in posizione " + i + ":");
      guess[i] = StdIn.readInt();
    }
    return guess;
  }

  public static int scegliLunghezzaSequenza() {
    StdOut.print("Inserisci un valore intero (maggiore di 0): ");
    return StdIn.readInt();
  }

  public static int[] confronta(int[] segreto, int[] guess) {
    int[] risultato = {0, 0};
    risultato[0] = contaCorretti(segreto, guess);
    risultato[1] = contaPosizioneSbagliata(segreto, guess);
    return risultato;
  }

  public static int contaCorretti(int[] segreto, int[] guess) {
    int c = 0;
    for (int i = 0; i < segreto.length; i++) {
      if (segreto[i] == guess[i]) {
        c++;
      }
    }
    return c;
  }

  private static int contaPosizioneSbagliata(int[] segreto, int[] guess) {
    boolean[] elementiUsati = new boolean[segreto.length];
    int c = 0;
    for (int i = 0; i < guess.length; i++) {
      boolean found = false;
      for (int j = 0; (j < segreto.length) && !found; j++) {
        if (!elementiUsati[j]
            && (i != j)
            && (guess[i] == segreto[j])
            && (guess[j] != segreto[j])) {
          c++;
          elementiUsati[j] = true;
          found = true;
        }
      }
    }
    return c;
  }

}
