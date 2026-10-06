package ST0851_Programmazione.SimulazioneEsami.Libri;

public class Libro {
  private String titolo;
  private String autore;
  private double prezzo;
  private int copieDisponibili;

  public Libro() {
    titolo = "";
    autore = "";
    prezzo = 0;
    copieDisponibili = 0;
  }

  public Libro(String titolo, String autore, int copieDisponibili, double prezzo) {
    this.titolo = titolo;
    this.autore = autore;
    this.prezzo = prezzo;
    this.copieDisponibili = copieDisponibili;
  }

  public String getTitolo() {
    return titolo;
  }

  public void setTitolo(String titolo) {
    if (titolo != null)
      this.titolo = titolo;
  }

  public String getAutore() {
    return autore;
  }

  public void setAutore(String autore) {
    if (autore != null)
      this.autore = autore;
  }

  public double getPrezzo() {
    return prezzo;
  }

  public void setPrezzo(double prezzo) {
    if (prezzo > 0)
      this.prezzo = prezzo;
  }

  public int getCopieDisponibili() {
    return copieDisponibili;
  }

  public void setCopieDisponibili(int copieDisponibili) {
    this.copieDisponibili = copieDisponibili;
  }

  @Override
  public String toString() {
    return "[" + titolo + "] "
        + "di " + autore + " "
        + "€ " + prezzo
        + " (" + copieDisponibili + ")";
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Libro other = (Libro) obj;
    return (autore.equals(other.getAutore())
        && titolo.equals(other.getTitolo()));
  }

}
