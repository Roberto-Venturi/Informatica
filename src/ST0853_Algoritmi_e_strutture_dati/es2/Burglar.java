//package it.unicam.cs.asdl.es2;
package ST0853_Algoritmi_e_strutture_dati.es2;

/**
 * Modella uno scassinatore che cerca la combinazione di una
 * {@link CombinationLock} mediante forza bruta.
 * <p>
 * Lo scassinatore lavora sulla stessa cassaforte ricevuta al momento della
 * costruzione e deve interagire con essa esclusivamente attraverso la sua API
 * pubblica. La combinazione non e' quindi disponibile direttamente: deve essere
 * scoperta producendo tentativi di apertura.
 * </p>
 * <p>
 * La ricerca considera, in ordine lessicografico, tutte le combinazioni da
 * {@code AAA} a {@code ZZZ}. Un tentativo corrisponde alla prova completa di una
 * singola combinazione. L'oggetto mantiene nel proprio stato anche il numero di
 * tentativi effettuati nell'ultima ricerca conclusa con successo.
 * </p>
 *
 * @author Luca Tesei
 */
public class Burglar {

   private final CombinationLock laCassa;
   private String combination = null;
   private int attempts = -1;

   /**
    * Costruisce uno scassinatore associato alla cassaforte indicata. La
    * costruzione non effettua ancora alcun tentativo di apertura.
    *
    * @param aCombinationLock la cassaforte da scassinare
    * @throws NullPointerException se {@code aCombinationLock} e' {@code null}
    */
   public Burglar(CombinationLock aCombinationLock) {
      if (aCombinationLock == null)
         throw new NullPointerException("aCombinationLock is null");
      laCassa = aCombinationLock;
   }

   /**
    * Cerca la combinazione della cassaforte mediante forza bruta, provando in
    * ordine lessicografico tutte le combinazioni da {@code AAA} a {@code ZZZ}.
    * <p>
    * Prima di iniziare la ricerca la cassaforte deve essere posta nello stato
    * chiuso tramite la sua API pubblica. Per ogni combinazione candidata lo
    * scassinatore imposta le tre posizioni e tenta l'apertura. La ricerca
    * termina non appena la cassaforte risulta aperta.
    * </p>
    * <p>
    * Al termine della ricerca la cassaforte e' aperta e
    * {@link #getAttempts()} restituisce il numero di combinazioni provate nella
    * ricerca appena conclusa.
    * </p>
    *
    * @return la combinazione segreta trovata; non puo' essere {@code null}
    */
   public String findCombination() {
      laCassa.lock();
      boolean found = false;
      attempts = 0;  //inizializzato a -1
      for (char i1 = 'A'; i1 <= 'Z'; i1++) {
         for (char i2 = 'A'; i2 <= 'Z'; i2++) {
            for (char i3 = 'A'; i3 <= 'Z'; i3++) {
               laCassa.setPosition(i1);
               laCassa.setPosition(i2);
               laCassa.setPosition(i3);
               attempts++;
               //System.out.printf("*** %3d: %c %c %c ***\r\n", attempts, i1, i2, i3);
               laCassa.open();
               if (laCassa.isOpen()) {
                  combination = "" + i1 + i2 + i3;
                  found = true;
               }
               if (found)
                  break;
            }
            if (found)
               break;
         }
         if (found)
            break;
      }
      if (combination == null)
         throw new RuntimeException("Combination not found");
      return combination;
   }

   /**
    * Restituisce il numero di tentativi effettuati dall'ultima chiamata a
    * {@link #findCombination()} conclusa con successo.
    *
    * @return il numero di combinazioni provate, oppure {@code -1} se questo
    * scassinatore non ha ancora completato una ricerca
    */
   public long getAttempts() {
      return attempts;
   }
}
