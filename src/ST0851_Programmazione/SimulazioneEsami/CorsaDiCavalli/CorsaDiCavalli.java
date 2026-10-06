package ST0851_Programmazione.SimulazioneEsami.CorsaDiCavalli;

public class CorsaDiCavalli {
  Cavallo[] cavalli;
  float[] distanze;
  int partecipanti = 0;
  boolean iniziata = false;
  boolean finita = false;
  float lunghezzaCorsa;

  public CorsaDiCavalli(int numCavalli, float lunghezza) {
    if (numCavalli <= 0)
      throw new IllegalArgumentException("Numero cavalli <= 0");
    /* io metterei anche questo controllo
    if (lunghezza <= 0)
      throw new IllegalArgumentException("Lunghezza <= 0");
     */
    this.cavalli = new Cavallo[numCavalli];
    this.distanze = new float[numCavalli];
    this.lunghezzaCorsa = lunghezza;
  }

  public boolean garaIniziata() {
    return iniziata;
  }

  public boolean garaFinita() {
    return finita;
  }

  public boolean aggiungiCavallo(Cavallo c) {
    if (c == null)
      throw new IllegalArgumentException("AggiungiCavallo: cavallo non fornito");
    if (this.iniziata)
      throw new IllegalArgumentException("AggiungiCavallo: gara già iniziata");
    if (partecipanti >= cavalli.length)
      return false;
    for (int i = 0; i < partecipanti; i++) {
      if (cavalli[i].equals(c))
        return false;
    }
    c.setNumeroPartecipazioni(c.getNumeroPartecipazioni() + 1);
    cavalli[partecipanti++] = c;
    return true;
  }

  public void avanzaCavallo(String nomeCavallo, float distanza) {
    if (nomeCavallo == null)
      throw new IllegalArgumentException("avanzaCavallo: nomeCavallo non fornito");
    int idx = 0;
    for (; idx < partecipanti; idx++) {
      if (cavalli[idx].getNome().equals(nomeCavallo))
        break;
    }
    if (idx >= partecipanti)
      throw new IllegalArgumentException("avanzaCavallo: nomeCavallo non trovato");
    if (this.finita)
      throw new IllegalArgumentException("avanzaCavallo: gara terminata");
    if (distanza <= 0)
      throw new IllegalArgumentException("avanzaCavallo: distanza non fornita");
    //--- parametri corretti e cavallo presente //
    distanze[idx] += distanza;
    iniziata = true;
    if (distanze[idx] >= lunghezzaCorsa) {
      this.finita = true;
      cavalli[idx].setNumeroVittorie(cavalli[idx].getNumeroVittorie() +1);
    }
  }

}
