package ST0851_Programmazione.SimulazioneEsami.Negozio;

public class Prodotto {
  int id;
  String nome;
  float costo;
  float prezzo;     //prezzo di vendita - selling price
  int quantita;

  public Prodotto(int id, String nome) {
    this.id = id;
    this.nome = nome.toLowerCase();
    this.prezzo = 0;
    this.costo = 0;
    this.quantita = 0;
  }

  public Prodotto(int id, String nome, float prezzo, float costo) {
    if (prezzo < costo)
      throw new DatiErrati("prezzo inferiore al costo.");
    this(id, nome);
    this.prezzo = prezzo;
    this.costo = costo;
  }

  public int getId() {
    return id;
  }

  public String getNome() {
    return nome;
  }

  public float getPrezzo() {
    return prezzo;
  }

  public void setPrezzo(float prezzo) {
    this.prezzo = prezzo;
  }

  public float getCosto() {
    return costo;
  }

  public void setCosto(float costo) {
    this.costo = costo;
  }

  public String toString() {
    String res = "";
    res += this.id + ": " + this.nome + " da " + this.costo + " a " + this.prezzo + "\n";
    return res;
  }

  public boolean equals(Prodotto p) {
    if (this.id == p.id && !this.nome.equals(p.nome))
      throw new DatiErrati("Due prodotti con stesso id ma nome diverso!");
    return this.id == p.id; // && this.nome.equals(p.nome);
  }


}
