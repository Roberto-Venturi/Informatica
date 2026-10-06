package ST0851_Programmazione.SimulazioneEsami.CorsaDiCavalli;

public class Cavallo {
  final String nome;
  int eta;
  int numeroVittorie;
  int numeroPartecipazioni;

  public Cavallo(String nome, int eta, int numeroVittorie, int numeroPartecipazioni) {
    if (nome == null || nome.length() < 4)
      throw new IllegalArgumentException("Cavallo: nome mancante o troppo corto");
    for (int i = 0; i < nome.length(); i++) {
      char c = nome.toUpperCase().charAt(i);
      if (c < 'A' || c > 'Z')
        throw new IllegalArgumentException("Cavallo: nome non alfabetico");
    }
    if (eta < 5)
      throw new IllegalArgumentException("Cavallo: età non valida (inferiore a 5)");
    /* Io aggiungerei anche i seguenti 2 controlli
    if (numeroVittorie < 0)
      throw new IllegalArgumentException("Cavallo: numeroVittorie negativo");
    if (numeroPartecipazioni < 0)

      throw new IllegalArgumentException("Cavallo: numeroPartecipazioni negativo");
     */
    this.nome = nome;
    this.eta = eta;
    this.numeroVittorie = numeroVittorie;
    this.numeroPartecipazioni = numeroPartecipazioni;
  }

  public String getNome() {
    return nome;
  }

  public int getEta() {
    return eta;
  }

  public void setEta(int eta) {
    this.eta = eta;
  }

  public int getNumeroVittorie() {
    return numeroVittorie;
  }

  public void setNumeroVittorie(int numeroVittorie) {
    this.numeroVittorie = numeroVittorie;
  }

  public int getNumeroPartecipazioni() {
    return numeroPartecipazioni;
  }

  public void setNumeroPartecipazioni(int numeroPartecipazioni) {
    this.numeroPartecipazioni = numeroPartecipazioni;
  }

  public float tassoDiVittoria() {
    if (getNumeroPartecipazioni() == 0)
      return 0;
    return (float)getNumeroVittorie() * 100 / (float) getNumeroPartecipazioni();
  }

  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof Cavallo))
      return false;
    // Nella soluzione vi è un test sui nomi null ma non può esistere l'oggetto Cavallo con nome null
    return this.nome.equals(((Cavallo) o).nome);
  }

}
