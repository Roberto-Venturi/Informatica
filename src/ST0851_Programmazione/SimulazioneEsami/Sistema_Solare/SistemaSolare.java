package ST0851_Programmazione.SimulazioneEsami.Sistema_Solare;

/**
 * @author Roberto Venturi aka Roven60
 */
public class SistemaSolare {

  Pianeta[] pianeti;
  int pianetiPresenti;

  /**
   *
   * @param numPianeti numero massimo dei pianeti presenti nel sistema solare
   */
  public SistemaSolare(int numPianeti) {
    if (numPianeti < 1) {
      throw new IllegalArgumentException("Numero di pianeti nullo o negativo");
    }
    pianeti = new Pianeta[numPianeti];
    pianetiPresenti = 0;
  }

  /**
   *
   * @param p pianeta da inserire nel sistema solare
   * @return true se l'inserimento è andato a buon fine
   */
  public boolean nuovo(Pianeta p) {
    if (p == null || pianetiPresenti == pianeti.length) {
      return false;
    }
    //Cerchiamo la posizione ove inserire il nuovo pianeta
    int idx = 0;
    for (; idx < pianetiPresenti; idx++) {
      if (pianeti[idx].getDistanza() > p.getDistanza()) {
        //Se esiste almeno un pianeta più lontano, li sposto tutti
        for (int j = pianetiPresenti; j > idx; j--) {
          pianeti[j] = pianeti[j - 1];
        }
        break;
        // ed ho il posto per il nuovo pianeta
      }
      //se tutti i pianeti inseriti sono più vicini, idx=pianetiPresenti
    } // se non vi sono pianeti, idx=0
    pianeti[idx] = p;
    pianetiPresenti++;
    return true;
  }

  /**
   *
   * @return numero dei pianeti attualmente presenti nel sistema solare (alias di getNumero)
   */
  public int getPianetiPresenti() {
    return pianetiPresenti;
  }

  /**
   *
   * @return numero dei pianeti attualmente presenti nel sistema solare (alias di getPianetiPresenti)
   */
  public int getNumero() {
    return pianetiPresenti;
  }

  /**
   *
   * @param num numero ordinale del pianeta cercato;
   *            se i pianeti presenti sono in numero inferiore, viene generata una eccezione
   * @return restituisce il pianeta
   */
  public Pianeta getPianeta(int num) {
    if (num < 0 || num > pianetiPresenti) {
      throw new IllegalArgumentException("Numero di pianeta negativo");
    }
    if (num >= pianetiPresenti) {
      throw new IllegalArgumentException("Numero di pianeta maggiore del numero pianeti presenti");
    }
    return pianeti[num];
  }

  /**
   *
   * @return restituisce il pianeta più distante dalla stella o <i>null</i> se non sono presenti pianeti
   */
  public Pianeta getMaxPianeta() {
    if (pianetiPresenti == 0) {
      return null;
    }
    int idx = 0;
    for (int i = 1; i < pianetiPresenti; i++) {
      if (pianeti[i].getMassa() > pianeti[idx].getMassa()) {
        idx = i;
      }
    }
    return pianeti[idx];
  }


  /*
    I 2 metodi seguenti sono stati aggiunti per puro sfizio.
    Mi è chiaro che, in base a dove verrà utilizzato questo "pacchetto", potrà essere opportuno rimuoverlo
    per non appesantire il codice nel caso di ambienti con poca memoria.
   */

  /**
   *
   * @return un testo multilinea con la rappresentazione grafica (testuale) del sistema solare
   *          con la distanza tra i pianeti in scala a quella memorizzata nel sistema
   */
  public String toGraph() {
    return toGraph(80);
  }

  public String toGraph(int cols) {
    cols -= pianeti[pianetiPresenti - 1].getNome().length();
    char[] chars = new char[cols];
    chars[0] = 'S';
    for (int i = 1; i < cols; i++) {
      chars[i] = '.';
    }
    double maxDistanza = pianeti[pianetiPresenti - 1].getDistanza();
    String res = "";
    for (int i = 0; i < pianetiPresenti; i++) {
      int col = (int) Math.round(pianeti[i].getDistanza() * cols / maxDistanza);
      chars[col - 1] = pianeti[i].getNome().charAt(0);
      res += " ".repeat(col - 1) + pianeti[i].getNome() + "\r\n";
    }
    res = new String(chars) + "\r\n" + res;
    return res;
  }

}
