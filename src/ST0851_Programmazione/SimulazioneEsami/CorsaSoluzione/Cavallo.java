package ST0851_Programmazione.SimulazioneEsami.CorsaSoluzione;

public class Cavallo {
    private final String nome;
    private int eta, numeroVittorie, numeroPartecipazioni;

    public Cavallo(String nome, int eta, int numeroVittorie, int numeroPartecipazioni) {
        // il cavallo deve essere almeno di 5 anni per partecipare
        if(numeroPartecipazioni < 0 || numeroVittorie < 0 || eta < 5)
            throw new IllegalArgumentException();
        if(nome == null || nome.length() < 4)
            throw new IllegalArgumentException();
        for (int i = 0; i < nome.length(); i++) {
            if(!Character.isAlphabetic(nome.charAt(i)))
                throw new IllegalArgumentException();
        }
        this.eta = eta;
        this.nome = nome;
        this.numeroPartecipazioni = numeroPartecipazioni;
        this.numeroVittorie = numeroVittorie;
    }

    public String getNome() {
        return nome;
    }

    public int getEta() {
        return eta;
    }

    public int getNumeroVittorie() {
        return numeroVittorie;
    }

    public int getNumeroPartecipazioni() {
        return numeroPartecipazioni;
    }

    public void setEta(int eta) {
        this.eta = eta;
    }

    public void setNumeroVittorie(int numeroVittorie) {
        this.numeroVittorie = numeroVittorie;
    }

    public void setNumeroPartecipazioni(int numeroPartecipazioni) {
        this.numeroPartecipazioni = numeroPartecipazioni;
    }

    public float tassoDiVittoria(){
        // se non ha vinto torno 0, evito divisione per 0
        return numeroVittorie == 0 ? 0f : (100.0f * numeroVittorie) / numeroPartecipazioni;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cavallo other = (Cavallo) obj;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } 
        // l'equals avviene solo sul nome
        return this.nome.equals(other.nome);
    }

    
    
}
