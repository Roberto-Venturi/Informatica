package ST0851_Programmazione.SimulazioneEsami.CorsaSoluzione;

public class CorsaDiCavalli {
    private float distanza;
    private Cavallo[] cavalli;
    private int counter;
    // distanza percorsa da ogni cavallo
    private float[] distanze;
    // stato della gara
    private boolean start, end;

    public CorsaDiCavalli(int cav, float dist){
        // almeno un cavallo e distanza non negativa
        if(cav < 1 || dist < 0)
            throw new IllegalArgumentException();
        cavalli = new Cavallo[cav];
        counter = 0;
        distanze = new float[cav];
        distanza = dist;
        start = false;
        end = false;
    }

    public boolean garaIniziata(){
        return start;
    }

    boolean garaFinita(){
        return end;
    }

    boolean aggiungiCavallo(Cavallo c){
        // non null, gara non iniziata
        if(c == null || start)
            throw new IllegalArgumentException();

        // controllo spazio disponibile
        if(counter == cavalli.length)
            return false;

        // il cavallo non deve essere presente
        if(findCavallo(c.getNome()) != -1)
            return false;

        cavalli[counter++] = c;
        // salvo la sua partecipazione
        c.setNumeroPartecipazioni(c.getNumeroPartecipazioni()+1);
        return true;
    }
    void avanzaCavallo(String nomeCavallo, float dist){
        // nome non null, distanza positiva, gara non finita
        if(nomeCavallo == null || dist <=0 || end)
            throw new IllegalArgumentException();
        int index = findCavallo(nomeCavallo);
        // cavallo deve essere presente
        if(index == -1)
            throw new IllegalArgumentException();
        // aggiorno stato gara
        start = true;
        Cavallo c = cavalli[index];
        // aggiorno distanza percorsa dal cavallo
        distanze[index] += dist;
        // controllo se ha vinto
        if(distanze[index] >= this.distanza){
            // aggiorno stato gara
            end = true;
            // aggiorno vittorie del cavallo
            c.setNumeroVittorie(c.getNumeroVittorie()+1);
        }
    }

    /**
     * Cerca un cavallo per nome
     * @param name
     * @return l'indice del cavallo nell'array, -1 se non trovato
     */
    private int findCavallo(String name){
        // scorro l'array dei cavalli aggiunti
        for (int i = 0; i < counter; i++) {
            // controllo se il nome corrisponde
            if(cavalli[i].getNome().equals(name))
                // torno l'indice
                return i;
        }
        return -1;
    }
}
