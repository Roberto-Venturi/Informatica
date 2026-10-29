//package it.unicam.cs.asdl.es5;
package ST0853_Algoritmi_e_strutture_dati.es5;

import java.util.HashSet;
import java.util.Set;

/**
 * Gestisce un insieme di aule identificato tramite {@code Set<Aula>} e
 * permette di selezionare quelle libere che soddisfano le dotazioni richieste.
 * <p>La classe esemplifica il polimorfismo e la ricerca con {@code for-each}
 * su una {@link java.util.HashSet}. L’unicità delle aule dipende dai
 * contratti {@code equals}/{@code hashCode} della classe {@link Aula}.
 *
 * @author Luca Tesei
 *
 */
public class GestoreAule {

   private final Set<Aula> aule;

   /**
    * Crea un gestore vuoto.
    */
   public GestoreAule() {
      this.aule = new HashSet<Aula>();
   }

   /**
    * Aggiunge un’aula al gestore.
    * <p>Il valore restituito da {@code Set.add} segnala se il gestore è cambiato.
    *
    * @param a una nuova aula
    * @return true se l'aula è stata aggiunta, false se era già presente.
    * @throws NullPointerException se l'aula passata è nulla
    */
   public boolean addAula(Aula a) {
      if (a == null)
         throw new NullPointerException();
      return aule.add(a);
   }

   /**
    * Restituisce l’insieme delle aule gestite.
    * <p>La collezione restituita è quella interna e può essere modificata
    * dal chiamante: il contratto originale non prevede copie difensive.
    *
    * @return riferimento all’insieme delle aule
    */
   public Set<Aula> getAule() {
      return aule;
   }

   /**
    * Seleziona le aule che soddisfano tutte le dotazioni richieste e sono libere
    * nell’intervallo specificato.
    * <p>Il risultato è un nuovo {@code HashSet}: modificare il risultato
    * non cambia l’insieme delle aule gestite. Il controllo delle dotazioni
    * delega a {@link Aula#satisfiesFacilities(Set)} e quello temporale a
    * {@link Aula#isFree(TimeSlot)}.
    *
    * @param requestedFacilities insieme di facilities richieste che
    *                            un'aula deve soddisfare
    * @param ts                  il time slot in cui un'aula deve essere
    *                            libera
    * @return l'insieme di tutte le aule gestite da questo gestore che
    * soddisfano tutte le facilities richieste e sono libere nel time
    * slot indicato. Se non ci sono aule che soddisfano i requisiti
    * viene restituito un insieme vuoto.
    * @throws NullPointerException se una qualsiasi delle informazioni
    *                              passate è nulla
    */
   public Set<Aula> cercaAuleLibere(Set<Facility> requestedFacilities,
                                    TimeSlot ts) {
      if (requestedFacilities == null)
         throw new NullPointerException();
      if (ts == null)
         throw new NullPointerException();
      Set<Aula> auleOk = new HashSet<>();
      for (Aula a : aule) {
         if (a.satisfiesFacilities(requestedFacilities) && a.isFree(ts)) {
            auleOk.add(a);
         }
      }
      return auleOk;
   }

}
