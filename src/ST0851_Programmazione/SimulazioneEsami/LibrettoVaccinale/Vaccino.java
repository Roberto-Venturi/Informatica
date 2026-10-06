package ST0851_Programmazione.SimulazioneEsami.LibrettoVaccinale;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public abstract class Vaccino {
  private final Calendar data;   //Campo non previsto dal compito ma che ritengo essenziale
                                  //per dare senso al successivo campo intervallo
  private final int identificativo;
  private final String azienda;         //azienda farmaceutica
  private final int intervallo;         //giorni di intervallo prima del richiamo o altro vaccino
  // 10 < intervallo

  /**
   * Costruttore richiesto dal compito
   * @param identificativo del vaccino
   * @param azienda farmaceutica
   * @param intervallo in giorni per il richiamo
   */
  public Vaccino(int identificativo, String azienda, int intervallo) {
    this(null, identificativo, azienda, intervallo);
  }

  /**
   * Costruttore NON richiesto dal compito
   * @param data di somministrazione
   * @param identificativo del vaccino
   * @param azienda farmaceutica
   * @param intervallo in giorni per il richiamo
   */
  public Vaccino(Calendar data, int identificativo, String azienda, int intervallo) {
    this.data = data;
    this.identificativo = identificativo;
    if (azienda == null || (azienda.trim()).isEmpty()) {
      throw new DatiErrati("azienda non valorizzata");
    }
    this.azienda = azienda.trim();
    if (intervallo <= 10) {
      throw new DatiErrati("intervallo non valido (minimo 11 gg.)");
    }
    this.intervallo = intervallo;
  }

  public Calendar getData() {
    return data;
  }

  public int getIdentificativo() {
    return identificativo;
  }

  public String getAzienda() {
    return azienda;
  }

  public int getIntervallo() {
    return intervallo;
  }

  public String toString() {
    String res = "";
    res += "identificativo: " + this.identificativo;
    res += ", azienda: " + this.azienda;
    res += (data != null ? ", data: " + new SimpleDateFormat("yyyy-MM-dd").format(this.data.getTime()) : "");
    res += ", intervallo: " + this.intervallo;
    return res;
  }

}
