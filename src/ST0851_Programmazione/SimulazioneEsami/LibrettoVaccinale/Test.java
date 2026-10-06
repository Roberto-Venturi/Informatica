package ST0851_Programmazione.SimulazioneEsami.LibrettoVaccinale;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class Test {

  /*
    Dal testo del compito ho interpretato la frase (fine secondo gruppo di testo)
    "Si noti che l'identificativo di una dose deve essere univoco per il tipo di vaccino" come segue:
    Se una data persona può assumere una sola dose di un certo vaccino, non ha senso il
    campo "intervallo" in quanto non è possibile fare un richiamo dello stesso vaccino
    quindi suppongo che il richieamo abbia un identificativo diverso e sia, in effetti,
    un diverso vaccino.

    Ho inoltre aggiunto alle classi Vaccino un campo "data" per dare senso al campo "intervallo",
    ma ho aggiunto un secondo costruttore per lasciare quello previsto dal compito.
    In fase di somministrazione del vaccino, qualora sia valorizzata la data,
    vengono validati gli "intervalli" dei vaccini già assunti
   */

  static void main(String[] args) {
    //Persona badName = new Persona("Fa", 31, 3);
  /*  Persona roberto = new Persona("Roberto", 65, Persona.MASCHILE);
    Persona rosa = new Persona("Rosa", 12, Persona.FEMMINILE);
    roberto.eseguiDoseVaccino(new VaccinoA(11, "Pfaizer", 15));
    roberto.eseguiDoseVaccino(new VaccinoB(12, "Piolla", 12, "Intramuscolo"));
    roberto.eseguiDoseVaccino(new VaccinoA(110, "Pfaizer", 15));
    System.out.println(roberto);
    try {
      rosa.eseguiDoseVaccino(new VaccinoA(110, "Pfaizer", 15));
    } catch (DoseNonSomministrabile e) {
      System.out.println(e.getMessage());
    }
    try {
      rosa.eseguiDoseVaccino(new VaccinoB(112, "Pfaizer", 15, "per bocca"));
    } catch (DoseNonSomministrabile e) {
      System.out.println(e.getMessage());
    }
 */
    // Test con caampo data //
    Calendar cData1 = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"), Locale.ITALY);
    Calendar cData2 = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"), Locale.ITALY);
    Calendar cData3 = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"), Locale.ITALY);
    SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd");
    cData1.add(Calendar.DAY_OF_MONTH, -30);
    cData2.add(Calendar.DAY_OF_MONTH, -10);
    System.out.println(date_format.format(cData1.getTime()) + "\n"
        + date_format.format(cData2.getTime()) + "\n"
        + date_format.format(cData3.getTime()) + "\n");
    //
    Persona mario = new Persona("Mario", 30, Persona.MASCHILE);
    Vaccino v1 = new VaccinoB(cData1, 11, "Pfaizer", 11, "Per bocca");
    mario.eseguiDoseVaccino(v1);
    System.out.println(mario);
    VaccinoA v2 = new VaccinoA(cData2, 12, "Piolla", 12);
    mario.eseguiDoseVaccino(v2);
    System.out.println(mario);
    Vaccino v3 = new VaccinoB(cData3, 110, "Estroza", 15, "Iniezione");
    mario.eseguiDoseVaccino(v3);
    System.out.println(mario);
  }

}
