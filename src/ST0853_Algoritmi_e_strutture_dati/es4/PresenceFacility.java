//package it.unicam.cs.asdl.es4;
package ST0853_Algoritmi_e_strutture_dati.es4;


/**
 * Facility che rappresenta la presenza di un servizio, senza quantità.
 * <p>La sottoclasse riutilizza lo stato e l'identità logica definiti da
 * {@link Facility}, ma implementa la propria regola di soddisfacimento:
 * un'altra {@code PresenceFacility} con lo stesso codice.
 */
public class PresenceFacility extends Facility {

    /**
     * Costruisce una facility di presenza delegando l'inizializzazione a super.
     * @param codice codice identificativo
     * @param descrizione descrizione
     * @throws NullPointerException se un argomento è null
     */
    public PresenceFacility(String codice, String descrizione) {
        super(codice, descrizione);
    }

    /**
     * Verifica una richiesta di presenza con lo stesso codice.
     * @param o facility richiesta
     * @return true solo se o è una PresenceFacility con lo stesso codice
     * @throws NullPointerException se o è null
     */
    @Override
    public boolean satisfies(Facility o) {
       if (o == null) throw new NullPointerException();
        if (!(o instanceof PresenceFacility)) return false;
        if (getCodice().equals(((PresenceFacility)o).getCodice())) return true;
        return false;
    }

}
