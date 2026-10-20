//package it.unicam.cs.asdl.es4;
package ST0853_Algoritmi_e_strutture_dati.es4;


/**
 * Rappresenta la prenotazione di un {@link Aula} per un {@link TimeSlot}.
 * <p>L'identità logica dipende solo da aula e intervallo; docente e motivo
 * possono cambiare senza modificare {@code equals} e {@code hashCode}.
 * L'ordinamento naturale confronta prima gli intervalli, poi le aule.
 */
public class Prenotazione implements Comparable<Prenotazione> {

    private final Aula aula;

    private final TimeSlot timeSlot;

    // Campo modificabile escluso dall'identità logica.
    private String docente;

    private String motivo;

    /**
     * Costruisce una prenotazione con identità fissata da aula e time slot.
     * @param aula aula prenotata
     * @param timeSlot intervallo prenotato
     * @param docente docente responsabile
     * @param motivo motivo della prenotazione
     * @throws NullPointerException se un argomento è null
     */
    public Prenotazione(Aula aula, TimeSlot timeSlot, String docente,
                        String motivo) {
       if (aula == null || timeSlot == null || docente == null || motivo == null)
          throw new NullPointerException(); // io farei 4 if separati con messaggi circostanziati
       this.aula = aula;
       this.timeSlot = timeSlot;
       this.docente = docente;
       this.motivo = motivo;
    }

    /**
     * Restituisce l'aula che partecipa all'identità logica.
     * @return aula della prenotazione
     */
    public Aula getAula() {
        return aula;
    }

    /**
     * Restituisce l'intervallo della prenotazione.
     * @return time slot
     */
    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    /**
     * Restituisce il docente, attributo modificabile non identitario.
     * @return docente
     */
    public String getDocente() {
        return docente;
    }

    /**
     * Restituisce il motivo, attributo modificabile non identitario.
     * @return motivo
     */
    public String getMotivo() {
        return motivo;
    }

    /**
     * Modifica il docente senza cambiare l'identità logica.
     * @param docente nuovo docente; il contratto originale del setter ammette null
     */
    public void setDocente(String docente) {
        this.docente = docente;
    }

    /**
     * Modifica il motivo senza cambiare l'identità logica.
     * @param motivo nuovo motivo; il contratto originale del setter ammette null
     */
    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    /**
     * Calcola l'hash usando solo aula e time slot, come equals.
     * @return codice hash della prenotazione
     */
    @Override
    public int hashCode() {
        // TODO implementare
        return -1;
    }

    /**
     * Confronta soltanto aula e time slot, non docente e motivo.
     * @param obj oggetto da confrontare
     * @return true se la prenotazione ha la stessa aula e lo stesso intervallo
     */
    @Override
    public boolean equals(Object obj) {
        // TODO implementare
        return false;
    }

    /**
     * Ordina prima per time slot, poi per aula; coerente con equals.
     * @param o altra prenotazione
     * @return risultato del confronto lessicografico
     * @throws NullPointerException se o è null
     */
    @Override
    public int compareTo(Prenotazione o) {
        // TODO implementare
        return -1;
    }

    /**
     * Restituisce una rappresentazione testuale della prenotazione.
     * @return descrizione testuale, senza stampare sullo standard output
     */
    @Override
    public String toString() {
        return "Prenotazione [aula = " + aula + ", time slot =" + timeSlot
                + ", docente=" + docente + ", motivo=" + motivo + "]";
    }

}
