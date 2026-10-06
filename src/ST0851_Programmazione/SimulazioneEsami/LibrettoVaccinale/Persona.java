package ST0851_Programmazione.SimulazioneEsami.LibrettoVaccinale;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class Persona {
  static final int MASCHILE = 0;
  static final int FEMMINILE = 1;
  String nome;
  int eta;      // 0 < eta < 99 previsto dal compito; forse 99 è poco:
  // papà festeggierà 100 anni a febbraio e si vaccinerà contro il covid
  int sesso;    //MASCHILE | FEMMINILE
  int effettuati = 0; //numero di vaccini assunti
  Vaccino[] vaccini = new Vaccino[100]; //elenco dei vaccini assunti

  public Persona(String nome, int eta, int sesso) {
    if (nome == null || (nome.trim()).length() < 2) {
      throw new DatiErrati("nome nullo o con meno di 2 caratteri");
    }
    this.nome = nome.trim();
    if (eta < 0 || eta > 99) {
      throw new DatiErrati("età non valida (da 0 a 99)");
    }
    this.eta = eta;
    if (sesso != MASCHILE && sesso != FEMMINILE) {
      throw new DatiErrati("sesso non valido (MASCHILE, FEMMINILE)");
    }
    this.sesso = sesso;
  }

  public void eseguiDoseVaccino(Vaccino dose) {
    if (dose == null) {
      throw new DatiErrati("vaccino non indicato");
    }
    if (dose instanceof VaccinoA) {
      if (eta < 14)
        throw new DoseNonSomministrabile("il paziente ha meno di 14 anni");
      for (int i = 0; i < effettuati; i++) {
        if (vaccini[i] instanceof VaccinoA && vaccini[i].getIdentificativo() == dose.getIdentificativo())
          throw new DoseNonSomministrabile("vaccinoA " + dose.getIdentificativo() + " già somministrato");
      }
    } // un else sarebbe più pulito dato che i vaccini sono solo 2; meglio un altro if
    if (dose instanceof VaccinoB) {
      if (sesso == MASCHILE && eta < 18)
        throw new DoseNonSomministrabile("il paziente maschio ha meno di 18 anni");
      if (sesso == FEMMINILE && eta < 60)
        throw new DoseNonSomministrabile("il paziente femmina ha meno di 60 anni");
      for (int i = 0; i < effettuati; i++) {
        if (vaccini[i] instanceof VaccinoB && vaccini[i].getIdentificativo() == dose.getIdentificativo())
          throw new DoseNonSomministrabile("vaccinoB " + dose.getIdentificativo() + " già somministrato");
      }
    }
    /*
      codice non richiesto dal compito, aggiunto per utilizzare e verificare i campi "data" e "intervallo"
     */
    if (dose.getData() != null) { //se la somministrazione corrente ha la data
      Calendar cDose = dose.getData();
      for (int i = 0; i < effettuati; i++) {
        if (vaccini[i].getData() != null) {
          Calendar cMin = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
          cMin.setTime(vaccini[i].getData().getTime());
          cMin.add(Calendar.DAY_OF_MONTH, vaccini[i].getIntervallo());
          if (cMin.compareTo(cDose) > 0) {
            String msg = "il precedente vaccino";
            if (vaccini[i] instanceof VaccinoA)
              msg += "A";
            else
              msg += "B";
            msg += " del " + (new SimpleDateFormat("yyyy-MM-dd").format(vaccini[i].getData().getTime()))
                +" richiede " +vaccini[i].getIntervallo() + " giorni di intervallo";
          throw new DoseNonSomministrabile(msg);
          }
        }
      }
    }
    /* Codice da eseguire comunque */
    if (effettuati < 100) {
      vaccini[effettuati++] = dose;
    }

  }

  public String toString() {
    String res = "";
    res += "Nome: " + this.nome;
    res += ", Eta: " + this.eta;
    res += ", Sesso: " + (this.sesso == MASCHILE ? "MASCHILE" : "FEMMINILE");
    res += ", Vaccini: " + this.effettuati;
    res += "\n";
    for (int i = 0; i < effettuati; i++) {
      res += "- " + this.vaccini[i].toString() + "\n";
    }
    return res;
  }

}
