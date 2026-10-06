package ST0851_Programmazione.SimulazioneEsami.Biblioteca;

public class Biblioteca {
  Libro[] libri;
  boolean[] prestati;
  int libriPresenti = 0;
  float mora;

  public Biblioteca(int max, float mora) {
    if (max <= 0)
      throw new IllegalArgumentException("max <= 0");
    if (mora <= 0)
      throw new IllegalArgumentException("mora <= 0");
    libri = new Libro[max];
    prestati = new boolean[max];
    this.mora = mora;
  }

  public boolean aggiungiLibro(Libro libro) {
    if (libro == null)
      throw new IllegalArgumentException("aggiungiLibro: libro è null");
    if (libriPresenti >= libri.length)
      return false;
    prestati[libriPresenti] = false;
    libri[libriPresenti] = libro;
    libriPresenti++;
    return true;
  }

  public String[][] daiTutti() {
    String[][] tutti = new String[libriPresenti][2];
    for (int i = 0; i < libriPresenti; i++) {
      tutti[i][0] = libri[i].getTitolo();
      tutti[i][1] = libri[i].getAutore();
    }
    return tutti;
  }

  public boolean prestaLibro(String titolo, String autore) {
    Libro libro = new Libro(titolo, autore); //throws exception for titolo or autore not valid
    for (int i = 0; i < libriPresenti; i++) {
      if (libro.equals(libri[i]) && !prestati[i]) {
        prestati[i] = true;
        return true;
      }
    }
    return false;
  }

  public float restituisciLibro(String titolo, String autore, int giorniDiRitardo) {
    Libro libro = new Libro(titolo, autore); //throws exception for titolo or autore not valid
    if (giorniDiRitardo < 0)
      throw new IllegalArgumentException("giorniDiRitardo<0");
    for (int i = 0; i < libriPresenti; i++) {
      if (libro.equals(libri[i]) && prestati[i] == true) {
        prestati[i] = false;
        return mora * giorniDiRitardo;
      }
    }
    return -1;
  }

}
