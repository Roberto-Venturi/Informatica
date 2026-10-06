package ST0851_Programmazione.SimulazioneEsami.LibrettoVaccinale;

import java.util.Calendar;

public class VaccinoA extends Vaccino {

  public VaccinoA(int id, String azienda, int intervallo) {
    super(id, azienda, intervallo);
  }

  public VaccinoA(Calendar data, int identificativo, String azienda, int intervallo) {
    super(data, identificativo, azienda, intervallo);
  }

  public String toString() {
    return "VaccinoA " +  super.toString();
  }

}
