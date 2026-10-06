package ST0851_Programmazione.SimulazioneEsami.Ferramenta;

public abstract class Articolo {
  float prezzoUnitario;
  int quantita;

  public Articolo() {
    this.prezzoUnitario = 0.01F;
    this.quantita = 0;
  }
  public Articolo(float prezzoUnitario) {
    this.prezzoUnitario = prezzoUnitario;
    this.quantita = 0;
  }
  public Articolo(float prezzoUnitario, int quantita) {
    this.prezzoUnitario = prezzoUnitario;
    this.quantita = quantita;
  }

  public float getPrezzoUnitario() {
    return prezzoUnitario;
  }

  public void setPrezzoUnitario(float prezzoUnitario) {
    this.prezzoUnitario = prezzoUnitario;
  }

  public int getQuantita() {
    return quantita;
  }

  public void setQuantita(int quantita) {
    this.quantita = quantita;
  }
}
