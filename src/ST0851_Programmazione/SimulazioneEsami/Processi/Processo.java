package ST0851_Programmazione.SimulazioneEsami.Processi;

public class Processo {
  long priorita;  //quanto un Processo sia importante
  int peso;   //quante risorse un processo può occupare
  boolean terminato;  //il processo ha finito o deve essere eseguito
  /* Per la lista dei processi figli uso un array in quanto al corso ci è stata
  spiegata solo questa struttura dati; preferirei un ArrayList */
  Processo[] sottoProcessi;  //lista dei processi figli tenuta in ordine di priorità
  int processi; //numero dei sottoProcessi


  public Processo(long priorita, int peso) {
    if (peso <= 0)
      throw new IllegalArgumentException("peso negativo");
    this.priorita = priorita;
    this.peso = peso;
    this.terminato = false;
    sottoProcessi = new Processo[peso]; //al massimo n sottoprocessi di peso 1
    processi = 0;
  }

  public long getPriorita() {
    return priorita;
  }

  public int getPeso() {
    return peso;
  }

  public boolean isTerminato() {
    return terminato;
  }

  public boolean aggiungiProcesso(Processo p) {
    if (p == null)
      throw new IllegalArgumentException("sottoProcesso null");
    int pesoAttuale = 0;
    for (int i = 0; i < processi; i++) {
      pesoAttuale += sottoProcessi[i].peso;
    }
    if (pesoAttuale + p.peso > this.peso)
      return false;
    //aggiungo il processo alla lista, in ordine di priorità (l'ultimo = max priorità)
    int i = 0;
    for (; i < processi; i++) {
      if (p.priorita < sottoProcessi[i].priorita) {
        for (int j = processi; j > i; j--) {
          sottoProcessi[j] = sottoProcessi[j - 1];
        }
        break;
      }
    }
    sottoProcessi[i] = p;
    processi++;
    return true;
  }

  public Processo daiProssimoProcesso() {
    for (int i = processi - 1; i >= 0; i--) {
      if (!sottoProcessi[i].terminato) {
        return sottoProcessi[i];
      }
    }
    if (!this.terminato)
      return this;
    return null;
  }

  public void prestaRisorse(int risorse) {
    if (risorse <= 0)
      throw new IllegalArgumentException("risorse negativo");
    int residuo = risorse;
    if (processi == 0) {
      peso -= risorse;
      if (peso == 0)
        terminato = true;
      return;
    }
    for (int i = processi - 1; i >= 0; i--) {
      if (!sottoProcessi[i].terminato) {
        if (sottoProcessi[i].peso >= residuo) {
          sottoProcessi[i].peso -= residuo;
          residuo = 0;
          if (sottoProcessi[i].peso == 0)
            sottoProcessi[i].terminato = true;
        } else {
          residuo -= sottoProcessi[i].peso;
          sottoProcessi[i].peso = 0;
          sottoProcessi[i].terminato = true;
        }
        if (residuo == 0)
          break;
      }
    }
    this.peso -= risorse;
    if (this.peso <= 0) {
      this.peso = 0;
      this.terminato = true;
    }
  }


}
