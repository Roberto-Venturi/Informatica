package ST0851_Programmazione.SimulazioneEsami.LibrettoVaccinale;

import java.util.Calendar;

public class VaccinoB extends Vaccino {
  private String somministrazione;  //metodo di somministrazione

  public VaccinoB(int id, String azienda, int intervallo, String somministrazione) {
    super(id, azienda, intervallo);
    if (somministrazione == null || (somministrazione.trim()).isEmpty()) {
      throw new DatiErrati("somministrazione non valorizzata");
    }
    this.somministrazione = somministrazione.trim();
  }

  public VaccinoB(Calendar data, int identificativo, String azienda, int intervallo, String somministrazione) {
    super(data, identificativo, azienda, intervallo);
    if (somministrazione == null || (somministrazione.trim()).isEmpty()) {
      throw new DatiErrati("somministrazione non valorizzata");
    }
    this.somministrazione = somministrazione.trim();
  }

  public String toString() {
    return "VaccinoB " + super.toString() + ", somministrazione: " + this.somministrazione;
  }


}
