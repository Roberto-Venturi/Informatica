//package it.unicam.cs.asdl.es3;
package ST0853_Algoritmi_e_strutture_dati.es3;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Objects;

/**
 * Rappresenta un intervallo di tempo continuo delimitato da un istante di
 * inizio e da un istante di fine.
 * <p/ >
 * Gli oggetti di questa classe sono immutabili: dopo la costruzione gli istanti
 * di inizio e fine non possono cambiare. Poiché {@link GregorianCalendar} è
 * invece una classe mutabile, l'implementazione deve usare copie difensive per
 * evitare che una modifica effettuata dall'esterno possa alterare lo stato del
 * time slot.
 * <p/ >
 * L'esercizio usa questa classe per applicare in modo coordinato i concetti di
 * uguaglianza logica, codice hash e ordinamento naturale. Due time slot sono
 * uguali quando hanno esattamente gli stessi istanti di inizio e fine, cioè gli
 * stessi valori temporali espressi in millisecondi; il
 * codice hash deve usare gli stessi campi e {@link #compareTo(TimeSlot)} deve
 * restituire zero se e solo se i due oggetti sono uguali.
 * <p/ >
 * Non sono ammessi time slot di durata nulla o negativa: l'istante di inizio
 * deve precedere strettamente quello di fine.
 *
 * @author Luca Tesei
 */
public class TimeSlot implements Comparable<TimeSlot> {

   /**
    * Soglia di tolleranza, espressa in minuti, usata da
    * {@link #overlapsWith(TimeSlot)}. Due time slot sono considerati
    * sovrapposti solo se la loro sovrapposizione è strettamente maggiore di
    * questa soglia.
    */
   public static final int MINUTES_OF_TOLERANCE_FOR_OVERLAPPING = 5;

   private final GregorianCalendar start;

   private final GregorianCalendar stop;

   /**
    * Costruisce un time slot compreso tra gli istanti {@code start} e
    * {@code stop}.
    * <p>
    * Per garantire l'immutabilità, l'implementazione deve memorizzare copie
    * degli oggetti {@link GregorianCalendar} ricevuti e non i riferimenti
    * originali.
    *
    * @param start l'istante iniziale del time slot
    * @param stop  l'istante finale del time slot
    * @throws NullPointerException     se {@code start} o {@code stop} è
    *                                  {@code null}
    * @throws IllegalArgumentException se {@code start} è uguale o successivo
    *                                  a {@code stop}
    */
   public TimeSlot(GregorianCalendar start, GregorianCalendar stop) {
      if (start == null)
         throw new NullPointerException("start = null");
      if (stop == null)
         throw new NullPointerException("stop = null");
      if (start.after(stop))
         throw new IllegalArgumentException("start after stop");
      if (start.equals(stop))
         throw new IllegalArgumentException("start and stop are equal");
      this.start = (GregorianCalendar) start.clone();
      this.stop = (GregorianCalendar) stop.clone();
   }

   /**
    * Restituisce l'istante iniziale del time slot.
    * <p>
    * Viene restituita una copia difensiva: modificare il calendario ottenuto
    * non deve modificare lo stato di questo oggetto.
    *
    * @return una copia dell'istante iniziale
    */
   public GregorianCalendar getStart() {
      return (GregorianCalendar) start.clone();
   }

   /**
    * Restituisce l'istante finale del time slot.
    * <p>
    * Viene restituita una copia difensiva: modificare il calendario ottenuto
    * non deve modificare lo stato di questo oggetto.
    *
    * @return una copia dell'istante finale
    */
   public GregorianCalendar getStop() {
      return (GregorianCalendar) stop.clone();
   }

   /**
    * Verifica l'uguaglianza logica tra questo time slot e un altro oggetto.
    * Due time slot sono uguali se e solo se rappresentano esattamente lo stesso
    * intervallo, cioè gli istanti di inizio e fine rappresentano rispettivamente
    * gli stessi valori temporali (ad esempio confrontando i millisecondi dal
    * riferimento temporale usato da Java).
    *
    * @param obj l'oggetto con cui confrontare questo time slot
    * @return {@code true} se {@code obj} è un time slot con gli stessi istanti
    * di inizio e fine, {@code false} altrimenti
    */
   @Override
   public boolean equals(Object obj) {
      if (obj == null)
         return false;
      if (!(obj instanceof TimeSlot))
         return false;
      TimeSlot tsObj = (TimeSlot) obj;
      return this.start.equals(tsObj.start) && this.stop.equals(tsObj.stop);
   }

   /**
    * Restituisce un codice hash coerente con {@link #equals(Object)}.
    * <p>
    * Poiché l'uguaglianza dipende dagli istanti di inizio e fine, anche il
    * codice hash deve essere calcolato a partire da entrambi questi campi.
    * Oggetti uguali secondo {@code equals} devono sempre produrre lo stesso
    * codice hash.
    *
    * @return il codice hash di questo time slot
    */
   @Override
   public int hashCode() {
      return Objects.hash(this.stop, this.start);
   }

   /**
    * Confronta questo time slot con un altro secondo l'ordinamento naturale
    * della classe. Un time slot precede un altro se inizia prima; a parità di
    * istante iniziale, precede quello che termina prima.
    * <p>
    * L'ordinamento deve essere compatibile con {@link #equals(Object)}: il
    * metodo restituisce zero se e solo se i due time slot hanno lo stesso
    * inizio e la stessa fine.
    *
    * @param o il time slot con cui effettuare il confronto
    * @return un valore negativo se questo time slot precede {@code o}, zero se
    * i due time slot sono uguali, un valore positivo se questo time
    * slot segue {@code o}
    * @throws NullPointerException se {@code o} è {@code null}
    */
   @Override
   public int compareTo(TimeSlot o) {
      if (o == null)
         throw new NullPointerException("o= null");
      if (this.start.before(o.start))
         return -1;
      if (this.start.after(o.start))
         return 1;
      if (this.stop.before(o.stop))
         return -1;
      if (this.stop.after(o.stop))
         return 1;
      return 0;
   }

   /**
    * Determina la durata in minuti dell'intersezione tra questo time slot e
    * quello passato.
    * <p>
    * Se i due intervalli non hanno istanti in comune, oppure si toccano
    * soltanto in un estremo, il metodo restituisce {@code -1}. Una
    * sovrapposizione positiva inferiore a un minuto produce invece
    * {@code 0}. Se la durata dell'intersezione non è un numero esatto di
    * minuti, vengono ignorati secondi e millisecondi residui: il risultato è
    * quindi arrotondato per difetto.
    * <p>
    * Il risultato deve essere simmetrico: per ogni coppia di time slot
    * {@code a} e {@code b}, {@code a.getMinutesOfOverlappingWith(b)} deve
    * coincidere con {@code b.getMinutesOfOverlappingWith(a)}.
    *
    * @param o il time slot da confrontare con questo
    * @return i minuti interi di sovrapposizione, oppure {@code -1} se non
    * esiste una sovrapposizione di durata positiva
    * @throws NullPointerException     se {@code o} è {@code null}
    * @throws IllegalArgumentException se il numero di minuti di
    *                                  sovrapposizione supera {@link Integer#MAX_VALUE}
    */
   public int getMinutesOfOverlappingWith(TimeSlot o) {
      double overlappingMillis;
      if (o == null)
         throw new NullPointerException("o= null");
      if (this.start.after(o.stop) || o.start.after(this.stop))
         return -1;  // intervalli distinti
      if (this.start.equals(o.stop)  || o.start.equals(this.stop))
         return -1;   // intervalli che si toccano
      if (this.start.before(o.start)) {
         if (o.stop.before(this.stop)) { // o interno a this
            overlappingMillis = (o.stop.getTimeInMillis() - o.start.getTimeInMillis());
         } else {   //overlap parziale
            overlappingMillis = (this.stop.getTimeInMillis() - o.start.getTimeInMillis());
         }
      } else if (this.start.after(o.start)) {
         if (this.stop.before(o.stop)) { // this interno a o
            overlappingMillis = (this.stop.getTimeInMillis() - this.start.getTimeInMillis());
         } else {   //overlap parziale
            overlappingMillis = (o.stop.getTimeInMillis() - this.start.getTimeInMillis());
         }
      } else { //stesso inizio
         if (this.stop.before(o.stop))
            overlappingMillis = (this.stop.getTimeInMillis() - this.start.getTimeInMillis());
         else
            overlappingMillis = (o.stop.getTimeInMillis() - this.start.getTimeInMillis());
      }
      overlappingMillis /= 60000;   //non più millis ma minuti
      if  (overlappingMillis > Integer.MAX_VALUE)
         throw new IllegalArgumentException("overlapping millis = " + overlappingMillis);
      return (int) overlappingMillis;
   }

   /**
    * Determina se questo time slot è sovrapposto a un altro oltre la soglia di
    * tolleranza definita da {@link #MINUTES_OF_TOLERANCE_FOR_OVERLAPPING}.
    * <p>
    * La soglia è stretta: una sovrapposizione di esattamente 5 minuti non è
    * sufficiente, mentre una sovrapposizione di 6 minuti è considerata tale.
    * Il metodo può essere implementato riutilizzando il servizio offerto da
    * {@link #getMinutesOfOverlappingWith(TimeSlot)}.
    *
    * @param o il time slot con cui verificare la sovrapposizione
    * @return {@code true} se la sovrapposizione è strettamente maggiore della
    * soglia di tolleranza, {@code false} altrimenti
    * @throws NullPointerException se {@code o} è {@code null}
    */
   public boolean overlapsWith(TimeSlot o) {
      if (o == null)
         throw new NullPointerException("o= null");
      return getMinutesOfOverlappingWith(o) > MINUTES_OF_TOLERANCE_FOR_OVERLAPPING;
   }

   /**
    * Restituisce una rappresentazione testuale del time slot nel formato
    * {@code [giorno/mese/anno ora.minuti - giorno/mese/anno ora.minuti]}.
    * Secondi e millisecondi non vengono rappresentati.
    * <p>
    * Esempi:
    * <ul>
    * <li>{@code [4/11/2019 11.0 - 4/11/2019 13.0]}</li>
    * <li>{@code [10/11/2019 11.15 - 10/11/2019 23.45]}</li>
    * </ul>
    * Il metodo restituisce una stringa e non effettua alcuna stampa sullo
    * standard output.
    *
    * @return la rappresentazione testuale di questo time slot
    */
   @Override
   public String toString() {
      // Converti Calendar in String usando .getTime()
      SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH.mm");
      String startString = sdf.format(start.getTime());
      String stopString = sdf.format(stop.getTime());
      if (startString.startsWith("0"))
         startString = startString.substring(1);
      if (stopString.startsWith("0"))
         stopString = stopString.substring(1);
      if (startString.endsWith("0"))
         startString = startString.substring(0, startString.length() - 1);
      if (stopString.endsWith("0"))
         stopString = stopString.substring(0, stopString.length() - 1);
      return "[" + startString + " - " + stopString + "]";
   }
}
