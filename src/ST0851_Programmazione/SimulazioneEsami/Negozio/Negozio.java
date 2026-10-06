package ST0851_Programmazione.SimulazioneEsami.Negozio;

public class Negozio {
  int id;
  String nome;
  double conto;
  int numeroProdotti;
  Prodotto[] prodotti = new Prodotto[100];

  public Negozio(int id, String nome) {
    this.id = id;
    this.nome = nome.toUpperCase();
    this.numeroProdotti = 0;
  }

  public String toString() {
    String res = "";
    res += this.id + ": " + this.nome + "\n";
    for (int i = 0; i < this.numeroProdotti; i++) {
      res += this.prodotti[i].toString() + "\n";
    }
    return res;
  }

  public boolean equals(Negozio n) {
    if (this.id == n.id && !this.nome.equals(n.nome))
      throw new DatiErrati("Due negozi con stesso id ma nome diverso!");
    return this.id == n.id; // && this.nome.equals(n.nome)
  }

}
