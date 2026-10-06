//package it.unicam.cs.asdl.es1;
//package es1.src.main.java.it.unicam.cs.asdl.es1;
package ST0853_Algoritmi_e_strutture_dati.es1;

/**
 * Rappresenta un'equazione di secondo grado modificabile e incorpora il
 * servizio necessario a calcolarne le soluzioni reali.
 * <p>
 * Questa classe è un esempio di oggetto di <em>business logic</em>: mantiene
 * uno stato interno, espone un'API pubblica e non interagisce direttamente con
 * l'utente. L'input e l'output sono responsabilità di un eventuale front-end.
 * <p>
 * A differenza di {@link EquazioneSecondoGrado}, questa classe è
 * <strong>mutabile</strong>: i coefficienti possono essere modificati tramite
 * i metodi {@link #setA(double)}, {@link #setB(double)} e
 * {@link #setC(double)}. La soluzione eventualmente calcolata appartiene però
 * allo stato precedente; per questo ogni modifica invalida la soluzione
 * corrente e rende necessario invocare nuovamente {@link #solve()}.
 * <p>
 * Il campo logico {@code solved} descrive quindi una proprietà dello stato:
 * vale {@code true} solo quando {@code lastSolution} è la soluzione dei
 * coefficienti correnti. Tentare di ottenere una soluzione non aggiornata è
 * un errore di stato e viene segnalato tramite {@link IllegalStateException},
 * senza effettuare input/output.
 *
 * @author Template: Luca Tesei, Implementation: Collettiva da Esercitazione a Casa
 */
public class EquazioneSecondoGradoModificabileConRisolutore {

  /*
   * Soglia numerica utilizzata per stabilire se il coefficiente a è
   * sufficientemente vicino a zero da non definire un'equazione di secondo
   * grado.
   */
  private static final double EPSILON = 1.0E-15;

  /* Stato corrente dell'equazione. */
  private double a;
  private double b;
  private double c;

  /* Indica se lastSolution è valida per i coefficienti correnti. */
  private boolean solved;

  /* Ultima equazione costruita, ovvero, equazione con gli ultimi parametri inseriti */
  private EquazioneSecondoGrado e;

  /* Ultima soluzione calcolata; è significativa solo se solved è true. */
  private SoluzioneEquazioneSecondoGrado lastSolution;

  /**
   * Costruisce un'equazione di secondo grado modificabile con i coefficienti
   * indicati.
   * <p>
   * Subito dopo la costruzione l'equazione non è ancora risolta: per poter
   * ottenere una soluzione è necessario invocare {@link #solve()}.
   *
   * @param a coefficiente del termine {@code x^2}; deve soddisfare
   *          {@code |a| >= EPSILON}
   * @param b coefficiente del termine {@code x}
   * @param c termine noto
   * @throws IllegalArgumentException se {@code |a| < EPSILON}
   */
  public EquazioneSecondoGradoModificabileConRisolutore(double a, double b,
                                                        double c) {
    setA(a);
    setB(b);
    setC(c);
    solved = false;
  }

  /**
   * Restituisce il valore corrente del coefficiente del termine di secondo
   * grado.
   *
   * @return il coefficiente corrente {@code a}
   */
  public double getA() {
    return a;
  }

  /**
   * Modifica il coefficiente del termine di secondo grado.
   * <p>
   * Una modifica dello stato invalida l'eventuale soluzione precedentemente
   * calcolata: dopo l'operazione {@link #isSolved()} deve restituire
   * {@code false} finché non viene chiamato di nuovo {@link #solve()}.
   *
   * @param a nuovo valore del coefficiente {@code a}; deve soddisfare
   *          {@code |a| >= EPSILON}
   * @throws IllegalArgumentException se {@code |a| < EPSILON}
   */
  public void setA(double a) {
    e = new EquazioneSecondoGrado(a, 0, 0);  /* senza duplicare il test */
    this.a = a;
    solved = false;
  }

  /**
   * Restituisce il valore corrente del coefficiente del termine di primo
   * grado.
   *
   * @return il coefficiente corrente {@code b}
   */
  public double getB() {
    return b;
  }

  /**
   * Modifica il coefficiente del termine di primo grado.
   * <p>
   * Dopo la modifica l'eventuale soluzione precedentemente calcolata non è
   * più valida per lo stato corrente e deve quindi essere ricalcolata.
   *
   * @param b nuovo valore del coefficiente {@code b}
   */
  public void setB(double b) {
    this.b = b;
    solved = false;
  }

  /**
   * Restituisce il valore corrente del termine noto.
   *
   * @return il coefficiente corrente {@code c}
   */
  public double getC() {
    return c;
  }

  /**
   * Modifica il termine noto.
   * <p>
   * Dopo la modifica l'eventuale soluzione precedentemente calcolata non è
   * più valida per lo stato corrente e deve quindi essere ricalcolata.
   *
   * @param c nuovo valore del coefficiente {@code c}
   */
  public void setC(double c) {
    this.c = c;
    solved = false;
  }

  /**
   * Determina se l'equazione, nello stato corrente, è già stata risolta.
   *
   * @return {@code true} se la soluzione memorizzata è valida per i
   * coefficienti correnti, {@code false} altrimenti
   */
  public boolean isSolved() {
    return solved;
  }

  /**
   * Calcola le soluzioni reali dell'equazione definita dai coefficienti
   * correnti.
   * <p>
   * Il discriminante {@code delta = b^2 - 4ac} determina i tre casi da
   * gestire: nessuna soluzione reale se {@code delta < 0}, due soluzioni
   * coincidenti se {@code delta = 0}, due soluzioni reali distinte se
   * {@code delta > 0}. Il risultato deve essere rappresentato tramite un
   * oggetto {@link SoluzioneEquazioneSecondoGrado}.
   * <p>
   * Se l'equazione è già stata risolta e nessun coefficiente è cambiato, il
   * metodo non deve ricalcolare inutilmente la soluzione.
   */
  public void solve() {
    if(solved)
      return;
    e = new EquazioneSecondoGrado(a, b, c);
    double delta = getB() * getB() - 4 * getA() * getC();
    // Caso delta uguale a zero entro la soglia scelta.
    if (Math.abs(delta) < EPSILON) {
      // Il valore di ritorno rappresenta due soluzioni coincidenti.
      lastSolution = new SoluzioneEquazioneSecondoGrado(e, (-e.getB()) / (2 * e.getA()));
      // Caso delta negativo: non esistono soluzioni reali.
    } else {
      if (delta < 0) {
        // Il valore di ritorno rappresenta una soluzione vuota.
        lastSolution = new SoluzioneEquazioneSecondoGrado(e);
        // Caso delta positivo: si calcolano due soluzioni reali distinte.
      } else {
        double tmp = Math.sqrt(delta);
        // L'oggetto risultato raccoglie entrambe le soluzioni calcolate.
        lastSolution = new SoluzioneEquazioneSecondoGrado(e,
            (-e.getB() + tmp) / (2 * e.getA()),
            (-e.getB() - tmp) / (2 * e.getA()));
      }
    }
    solved = true;
  }

  /**
   * Restituisce la soluzione valida per i coefficienti correnti.
   * <p>
   * Il metodo non risolve implicitamente l'equazione: il chiamante deve prima
   * invocare {@link #solve()}. Questo permette di distinguere chiaramente
   * l'operazione che modifica lo stato calcolando una soluzione da quella che
   * si limita a leggere il risultato già disponibile.
   *
   * @return la soluzione calcolata per i coefficienti correnti
   * @throws IllegalStateException se l'equazione non è stata ancora risolta
   *                               oppure se almeno un coefficiente è stato modificato dopo
   *                               l'ultima chiamata a {@code solve()}
   */
  public SoluzioneEquazioneSecondoGrado getSolution() {
    if (solved)
      return lastSolution;
    /* else */
    throw new IllegalStateException("Not solved yet!");
  }
}
