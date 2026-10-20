//package it.unicam.cs.asdl.es4;
package ST0853_Algoritmi_e_strutture_dati.es4;


/**
 * Descrive un servizio offerto da un'aula attraverso una superclasse astratta.
 * <p>Il codice identifica logicamente la facility: {@code equals} e
 * {@code hashCode} non dipendono né dalla descrizione né dalla sottoclasse.
 * Il metodo astratto {@link #satisfies(Facility)} delega alle sottoclassi
 * la regola di soddisfacimento della richiesta. Uguaglianza e soddisfacimento
 * sono quindi due relazioni distinte.
 */
public abstract class Facility {

    private final String codice;

    private final String descrizione;

    /**
     * Inizializza i dati comuni a tutte le sottoclassi.
     * @param codice identificativo della facility
     * @param descrizione descrizione testuale
     * @throws NullPointerException se uno dei parametri è null
     */
    public Facility(String codice, String descrizione) {
       if (codice == null || descrizione == null)
          throw new NullPointerException("Codice o descrizione nulli");
        this.codice = codice;
        this.descrizione = descrizione;
    }

    /**
     * Restituisce il codice che determina l'identità logica.
     * @return codice della facility
     */
    public String getCodice() {
        return codice;
    }

    /**
     * Restituisce la descrizione, non rilevante per l'identità logica.
     * @return descrizione
     */
    public String getDescrizione() {
        return descrizione;
    }

    /**
     * Calcola un hash coerente con equals usando il solo codice.
     * @return hash del codice identificativo
     */
    @Override
    public int hashCode() {
        return codice.hashCode();
    }

    /**
     * Definisce l'uguaglianza per tutte le sottoclassi in base al codice.
     * <p>Due facility con stesso codice sono uguali anche se di sottoclassi
     * diverse o con quantità diverse; non confondere con {@code satisfies}.
     * @param obj oggetto da confrontare
     * @return true se è una Facility con lo stesso codice
     */
    @Override
    public boolean equals(Object obj) {
       if (obj == null) return false;
       if (!(obj instanceof Facility)) return false;
       if (this.codice.equals(((Facility)obj).codice)) return true;
       return false;
    }

    /**
     * Produce una rappresentazione testuale dei dati comuni.
     * @return stringa descrittiva della facility
     */
    @Override
    public String toString() {
        return "Facility [codice=" + codice + ", descrizione=" + descrizione
                + "]";
    }

    /**
     * Stabilisce se la facility disponibile soddisfa una richiesta.
     * <p>È un metodo astratto: la regola concreta dipende dalla sottoclasse
     * effettiva che riceve la chiamata (selezione posticipata).
     * @param o facility richiesta
     * @return true se la richiesta è soddisfatta
     * @throws NullPointerException se o è null
     */
    public abstract boolean satisfies(Facility o);

}
