package ST0851_Programmazione.SimulazioneEsami.Libri;

public class NegozioLibri {
  private Libro[] inventario = new Libro[5];
  private int numeroLibri = 0;

  public NegozioLibri() {
  }

  public Libro[] getInventario() {
    return inventario;
  }

  public int getNumeroLibri() {
    return numeroLibri;
  }

  public void aggiungiLibro(Libro libro) throws ArrayIndexOutOfBoundsException {
    if (numeroLibri < inventario.length) {
      inventario[numeroLibri] = libro;
      numeroLibri++;
    } else
      throw new ArrayIndexOutOfBoundsException();
  }

  public void rimuoviLibro(int indice) throws ArrayIndexOutOfBoundsException {
    if (indice >= inventario.length || indice < 0)
      throw new ArrayIndexOutOfBoundsException();
    // else //
    for (int i = indice; i < inventario.length - 1; i++) {
      inventario[i] = inventario[i + 1];
    }
    numeroLibri--;
  }

  @Override
  public String toString() {
    String res = "";
    for (int i = 0; i < numeroLibri; i++) {
      res += inventario[i].getTitolo()
          + inventario[i].getAutore()
          + inventario[i].getPrezzo()
          + inventario[i].getCopieDisponibili();

    }
    return res;
  }

  public int libriDisponibili() {
    int res = 0;
    for (int i = 0; i < numeroLibri; i++) {
      res += inventario[i].getCopieDisponibili();
    }
    return res;
  }

  public void applicaScontoATutti(double perc) {
    for (int i = 0; i < numeroLibri; i++) {
      inventario[i].setPrezzo((int)(inventario[i].getPrezzo()*100) * (1 - perc) / 100);
    }
  }

  public Libro[] ordinaPerPrezzo() {
    Libro[] res = new Libro[numeroLibri];
    boolean[] contato = new boolean[numeroLibri];
    for (int resIdx = 0; resIdx < numeroLibri; resIdx++) {
      int libriIdx = 0;
      double prezzo = Double.MAX_VALUE;
      for (int i = 0; i < numeroLibri; i++) {
        if (!contato[i] && inventario[i].getPrezzo() < prezzo) {
          libriIdx = i;
          prezzo = inventario[i].getPrezzo();
        }
      }
      res[resIdx] = inventario[libriIdx];
      contato[libriIdx] = true;
    }
    return res;
  }

  public Libro libroMaxCopie() {
    int idx = 0;
    for (int i = 0; i < numeroLibri; i++) {
      if (inventario[i].getCopieDisponibili() > inventario[idx].getCopieDisponibili())
        idx = i;
    }
    return inventario[idx];
  }

}
