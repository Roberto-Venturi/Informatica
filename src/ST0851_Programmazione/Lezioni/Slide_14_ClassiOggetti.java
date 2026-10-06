package ST0851_Programmazione.Lezioni;

public class Slide_14_ClassiOggetti {

  public static class Orologio {
    int ore = 0;
    int minuti = 0;

    public Orologio(int ore, int minuti) {
      if (ore > 0 && ore < 23) {
        this.ore = ore;
      }
      if (minuti > 0 && minuti < 59) {
        this.minuti = minuti;
      }
    }

    public int get_ora() {
      return ore;
    }

    public int get_minuti() {
      return minuti;
    }

    public void tick() {
      minuti++;
      if (minuti > 59) {
        ore++;
        minuti = 0;
      }
      if (ore > 23) {
        ore = 0;
      }
    }

  } //Orologio end

  public static class Recipiente {
    private int volume;     // 0 <= volume private int contenuto;
    private int contenuto;  // 0 <= contenuto <= volume ...
    private boolean aperto;

    public Recipiente(int volume) { // quantita >= 0
      this.volume = volume;
      this.contenuto = 0;
      this.aperto = false;
    }

    public int get_volume() {
      return volume;
    }

    public int get_contenuto() {
      return contenuto;
    }

    public int capacita() {
      return volume - contenuto;
    }

    public void apri() {
      aperto = true;
    }

    public void chiudi() {
      aperto = false;
    }

    public void aggiungi(int quantita) { // 0 <= quantita
      if (aperto)
        contenuto = Math.min(contenuto + quantita, volume);
    }

    public void rimuovi(int quantita) { // 0 <= quantita
      if (aperto)
        contenuto = Math.max(contenuto - quantita, 0);
    }
  }  //Recipiente End

  /**
   * Dopo 38 anni da bancario :-D mi è venuto spontaneo,
   * in mancanza di un fido, limitare il prelievo al saldo
   * e ritornare il valore prelevato (che può essere inferiore al richiesto).
   */
  public static class ContoCorrente {
    private int saldo;
    private int saldoMax;

    public ContoCorrente(int saldo) {
      this.saldo = saldo;
      this.saldoMax = saldo;
    }

    public int get_saldo() {
      return saldo;
    }

    public int get_saldoMax() {
      return saldoMax;
    }

    public void deposita(int cifra) {
      this.saldo += cifra;
      if (saldo > saldoMax)
        this.saldoMax = saldo;
    }

    public int preleva(int cifra) {
      int disponibilita = Math.min(saldo, cifra);
      this.saldo -= disponibilita;
      return disponibilita;
    }
  } //ContoCorrente End

  public static class Primes {
    private int current;

    public Primes() {
      current = 1;
    }

    public int next() {
      for (int i = current + 1; i < Integer.MAX_VALUE; i++) {
        if (this.isPrime(i)) {
          current = i;
          break;
        }  //else try next integer
      }
      //all primes < MAX_VALUE returned: what to do?
      return current;
    }

    private boolean isPrime(int number) {
      for (int i = 2; i <= number / 2; i++) {
        if (number % i == 0) {
          return false;
        }
      }
      return true;
    }

  } //Primes End


  static void main(String[] args) {
    StdOut.println("===== OROLOGIO =====");
    Orologio o1 = new Orologio(20, 58);
    StdOut.println("        Ore " + o1.get_ora() + ":" + o1.get_minuti());
    o1.tick();
    StdOut.println("tick => Ore " + o1.get_ora() + ":" + o1.get_minuti());
    o1.tick();
    StdOut.println("tick => Ore " + o1.get_ora() + ":" + o1.get_minuti());
    StdOut.println("===== RECIPIENTE =====");
    Recipiente r1 = new Recipiente(10);
    StdOut.println("Nuovo recipiente(10) => Capacità " + r1.capacita());
    r1.aggiungi(4);
    StdOut.println("Aggiungi(4) => Capacità " + r1.capacita());
    r1.apri();
    r1.aggiungi(4);
    StdOut.println("Apri() + Aggiungi(4) => Capacità " + r1.capacita());
    StdOut.println("===== CONTO CORRENTE =====");
    ContoCorrente cc1 = new ContoCorrente(20);
    StdOut.println("new Conto(20) => Saldo " + cc1.get_saldo() + " (max " + cc1.get_saldoMax() +")");
    cc1.deposita(2);
    StdOut.println("deposita(2) => Saldo " + cc1.get_saldo() + " (max " + cc1.get_saldoMax() +")");
    cc1.deposita(8);
    StdOut.println("deposita(8) => Saldo " + cc1.get_saldo() + " (max " + cc1.get_saldoMax() +")");
    int contante = cc1.preleva(18);
    StdOut.println("preleva(18) => Saldo " + cc1.get_saldo() + " (max " + cc1.get_saldoMax() +") contante " + contante);
    contante = cc1.preleva(18);
    StdOut.println("preleva(18) => Saldo " + cc1.get_saldo() + " (max " + cc1.get_saldoMax() +") contante " + contante);
    StdOut.println("===== PRIMES =====");
    Primes p1 = new Primes();
    for (int i = 0; i < 10; i++) {
      StdOut.println("Next prime is " + p1.next());
    }
  }

}
