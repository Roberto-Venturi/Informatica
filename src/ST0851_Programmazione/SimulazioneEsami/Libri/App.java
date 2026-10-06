package ST0851_Programmazione.SimulazioneEsami.Libri;

public class App {
  public static void main(String[] args) throws Exception  {

    int totPunti = 0;
    int punteggio = 0;

    Libro l_1 = new Libro("Titolo_1","Autore_1", 4 , 10);
    Libro l_2 = new Libro("Titolo_2","Autore_2", 6 , 12.5);
    Libro l_3 = new Libro("Titolo_3","Autore_3", 7 , 14);
    Libro l_4 = new Libro("Titolo_4","Autore_4", 2 , 16.5);
    Libro l_5 = new Libro("Titolo_5","Autore_5", 8 , 18);
    Libro l_6 = new Libro("Titolo_6","Autore_6", 7 , 20.5);

    NegozioLibri n = new NegozioLibri();

    //Aggiungi libro----------------------------------------------------------------------------------- 1
    try{
      totPunti ++;
      n.aggiungiLibro(l_1); //0
      n.aggiungiLibro(l_2); //1
      n.aggiungiLibro(l_3); //2
      n.aggiungiLibro(l_4); //3
      n.aggiungiLibro(l_5); //4
      if(n.getInventario()[0].equals(l_1) &&
          n.getInventario()[1].equals(l_2) &&
          n.getInventario()[2].equals(l_3) &&
          n.getInventario()[3].equals(l_4) &&
          n.getInventario()[4].equals(l_5)){
        System.out.println("OK! I libri sono stati aggiunti!\n");
        punteggio++;}
      else{ System.out.println("FAIL! I libri non sono stati aggiunti correttamente.\n");}
    } catch(Exception e ){
      System.out.println("FAIL! Eccezione: aggiungiLibro() --> "+ e.getMessage()+ "\n");
    }

    //Aggiungi libro extra ---------------------------------------------------------------------------- 2
    try {
      totPunti ++;
      n.aggiungiLibro(l_6);
    } catch (Exception e) {
      System.out.println("OK! Eccezione: aggiungiLibro() --> "+e.getMessage()+ "\n");
      punteggio++;
    }

    //Aggiungi libro null ----------------------------------------------------------------------------- 3
    try {
      totPunti ++;
      n.aggiungiLibro(null);
    } catch (Exception e) {
      System.out.println("OK! Eccezione: aggiungiLibro() --> "+e.getMessage()+ "\n");
      punteggio++;
    }

    //Rimuovi libro ---------------------------------------------------------------------------------- 4
    try{
      totPunti ++;
      n.rimuoviLibro(2); // Dovrebbe rimuovere autore3
      if (n.getNumeroLibri()==4 && n.getInventario()[2].equals(l_4)) {
        System.out.println("OK! Libro rimosso correttamente.\n");
        punteggio++;
      }
      else{ System.out.println("FAIL! Libro non è stato rimosso correttamente.\n");}
    } catch(Exception e ){
      System.out.println("FAIL! Eccezione: rimuoviLibro() --> "+ e.getMessage()+ "\n");
    }

    //Libri Disponibili ------------------------------------------------------------------------------ 5
    try{
      totPunti ++;
      n.libriDisponibili();
      if(n.libriDisponibili() == 20){
        System.out.println("OK! I libri disponibili sono 20.\n");
        punteggio++;
      }
      else{ System.out.println("FAIL! Le disponibilità sono sbagliate.\n");}
    } catch (Exception e) {
      System.out.println("FAIL! Eccezione: libriDisponibili() --> "+ e.getMessage()+ "\n");
    }

    //Ordina per Prezzo ------------------------------------------------------------------------------ 6
    try{
      totPunti ++;
      Libro[] arr = n.ordinaPerPrezzo();
      if(arr[0].getPrezzo()==10 &&
          arr[1].getPrezzo()==12.5 &&
          arr[2].getPrezzo()==16.5 &&
          arr[3].getPrezzo()==18){
        System.out.println("OK! Il riordinamento è andato a buon fine.\n");
        punteggio++;
      }
      else{ System.out.println("FAIL! Il riordinamento non è andato a buon fine.\n");}
    } catch (Exception e) {
      System.out.println("FAIL! Eccezione: ordinaPerPrezzo() --> "+ e.getMessage()+ "\n");
    }

    //Applica Sconto --------------------------------------------------------------------------------- 7
    try { totPunti ++;
      n.applicaScontoATutti(2);
    } catch (Exception e) {
      System.out.println("OK! Eccezione: sconto() --> " +e.getMessage()+ "\n");
      punteggio++;
    }

    //Applica Sconto --------------------------------------------------------------------------------- 8
    try { totPunti ++;
      n.applicaScontoATutti(0.3);
      System.out.println("[[" +n.getInventario()[2].getPrezzo() +"]]");
      if(n.getInventario()[0].getPrezzo() == 7.0 &&
          n.getInventario()[1].getPrezzo() == 8.75 &&
          n.getInventario()[2].getPrezzo() == 11.55 &&
          n.getInventario()[3].getPrezzo() > 12.6 &&
          n.getInventario()[3].getPrezzo() < 12.62){

        System.out.println("OK! Lo sconto è stato applicato.\n");
        punteggio++;
      } else{ System.out.println("FAIL! Lo sconto non è stato applicato correttamente.\n");}
    } catch (Exception e) {
      System.out.println("FAIL!  Eccezione: sconto() --> "+ e.getMessage()+ "\n");
    }



    //Libro con MAx disponibilità --------------------------------------------------------------------- 9

    try{totPunti ++;
      if( n.libroMaxCopie().equals(l_5)){
        System.out.println("OK! Ricerca libro con il maggior numero di copie corretta.\n");
        punteggio++;
      } else { System.out.println("FAIL! Ricerca libro con il maggior numero di copie sbagliata.\n");}
    } catch(Exception e){
      System.out.println("FAIL!  Eccezione: libroMaxCopie() --> "+ e.getMessage()+ "\n");
    }


    //Rimuovi libro ---------------------------------------------------------------------------------- 10
    try{
      totPunti ++;
      n.rimuoviLibro(5);

    } catch(Exception e ){
      System.out.println("OK! Eccezione: rimuoviLibro() --> "+ e.getMessage()+ "\n");
      punteggio ++;
    }



    System.out.println("Punti ["+punteggio + "/" + totPunti +"]");

  }
}