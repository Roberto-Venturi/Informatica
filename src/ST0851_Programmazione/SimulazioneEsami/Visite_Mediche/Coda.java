package ST0851_Programmazione.SimulazioneEsami.Visite_Mediche;

/**
 * Coda delle visite prenotate (per un certo giorno|appuntamento?)
 *
 * @author Roberto Venturi aka Roven60
 */
public class Coda {

  private Visita[] coda;        //elenco delle visite prenotate
  private int pazienti;         //numero massimo delle visite prenotabili
  private int prenotazioni = 0; //numero di prenotazioni presenti in coda

  /**
   *
   * @param max numero massimo di visite prenotabili
   */
  public Coda(int max) {
    if (max <= 0) {
      throw new IllegalArgumentException("Numero non valido di visite gestite");
    }
    pazienti = max;
    coda = new Visita[max];
  }

  /**
   *
   * @param visita  visita da aggiungere alla coda
   * @return        true se l'inserimento è stato eseguito,
   *                false altrimenti (elenco visite completo, visita non valida, ...)
   */
  public boolean inserimento(Visita visita) {
    if (visita == null || prenotazioni >= pazienti) {
      return false;
    }
    coda[prenotazioni++] = visita;
    return true;
  }

  /**
   * Metodo per avere la prossima visita in coda
   *
   * @return  la prossima Visita (prenotazione) in coda
   */
  public Visita estrazione() {
    if (prenotazioni == 0) {
      return null;
    }
    Visita res = coda[0];
    for (int i = 1; i < prenotazioni; i++) {
      coda[i - 1] = coda[i];
    }
    prenotazioni--;
    return res;
  }

  /**
   * Metodo per cancellare una prenotazione visita
   *
   * @param nome    nome del paziente richiedente visita
   * @param cognome conome del paziente richiedente visita
   * @return        <i>true</i> se la cancellazione è andata a buon fine, <i>false</i> altrimenti
   */
  public boolean cancellazione(String nome, String cognome) {
    for (int i = 0; i < prenotazioni; i++) {
      if (coda[i].getNome().equals(nome) && coda[i].getCognome().equals(cognome)) {
        for (int j = i; j < prenotazioni; j++) {
          coda[j] = coda[j + 1];
        }
        prenotazioni--;
        return true;
      }
    }
    return false;
  }


}