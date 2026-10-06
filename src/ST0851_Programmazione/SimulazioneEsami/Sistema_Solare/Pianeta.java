package ST0851_Programmazione.SimulazioneEsami.Sistema_Solare;

/**
 * @author Roberto Venturi aka Roven60
 */
public class Pianeta {

  String nome;
  double massa;
  double distanza;


  /**
   *
   * @param nome  nome del pianeta
   * @param massa massa del pianeta espressa in ???
   * @param distanza distanza dalla stella espressa in ???
   */
  public Pianeta(String nome, double massa, double distanza) {
    if (nome == null || nome.equals("")) {
      throw new IllegalArgumentException("Nome nullo");
    }
    this.nome = nome;
    if (massa < 0 ) {
      throw new IllegalArgumentException("Massa nulla");
    }
    this.massa = massa;
    if (distanza < 0 ) {
      throw new IllegalArgumentException("Distanza nulla");
    }
    this.distanza = distanza;
  }

  public String getNome() {
    return nome;
  }

  public double getMassa() {
    return massa;
  }

  public double getDistanza() {
    return distanza;
  }

  public String toString() {
    return "Pianeta: " + nome + " massa: " + massa + " distanza: " + distanza +"\n";
  }

}
