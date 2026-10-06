//package es1.src.main.java.it.unicam.cs.asdl.es1;
package ST0853_Algoritmi_e_strutture_dati.es1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Front-end testuale dell'applicazione per la risoluzione di equazioni di
 * secondo grado modificabili.
 * <p>
 * La classe rappresenta il livello di interazione con l'utente: legge dati
 * dallo standard input, li converte in valori numerici, richiama esclusivamente
 * l'API pubblica della business logic e presenta i risultati sullo standard
 * output. La classe {@link EquazioneSecondoGradoModificabileConRisolutore}, al
 * contrario, non contiene codice di input/output.
 * <p>
 * <strong>Parte dell'esercitazione:</strong> questa versione contiene
 * intenzionalmente un bug nella gestione dei tentativi di inserimento. Se per
 * uno dei coefficienti è necessario ripetere l'input almeno una volta, il
 * programma può rimanere in un ciclo di richiesta anche dopo un successivo
 * inserimento valido. Il bug deve essere individuato tramite lettura/debug del
 * codice e corretto senza spostare nel front-end responsabilità proprie della
 * business logic.
 *
 * @author Luca Tesei
 */
public class EquazioniModificabiliTextualFrontEnd {

    /* Deve essere coerente con la soglia usata dalla business logic per a. */
    private static final double EPSILON = 1.0E-15;

    /**
     * Avvia il front-end testuale. Per ogni equazione legge i tre coefficienti,
     * risolve l'equazione tramite la business logic, stampa il risultato e
     * chiede all'utente se desidera continuare.
     *
     * @param args argomenti della linea di comando; non vengono utilizzati
     */
    public static void main(String[] args) {
        EquazioneSecondoGradoModificabileConRisolutore eq = null;
        boolean terminate = false;
        BufferedReader input = new BufferedReader(
                new InputStreamReader(System.in));

        // Valori iniziali non significativi: vengono sostituiti dall'input.
        double a = 1;
        double b = 1;
        double c = 1;

        while (!terminate) {
            // Lettura e validazione del coefficiente a.
            boolean retry;
            do {
                retry = false;
                System.out.println(
                        "Inserisci il valore del parametro a e premi INVIO");
                try {
                    String aInput = input.readLine();
                    a = Double.parseDouble(aInput);
                    if (Math.abs(a) < EPSILON) {
                        System.out.println(
                                "Errore: Il valore del parametro a non può essere zero! Ritenta...");
                        retry = true;
                    }
                } catch (IOException e) {
                    System.err.println("Errore di Input/Output!");
                    System.exit(1);
                } catch (NumberFormatException e) {
                    System.out.println(
                            "Errore: Il valore del parametro a deve essere un numero! Ritenta...");
                    retry = true;
                }
            } while (retry);

            // Lettura e validazione del coefficiente b.
            do {
              retry = false;
                System.out.println(
                        "Inserisci il valore del parametro b e premi INVIO");
                try {
                    String bInput = input.readLine();
                    b = Double.parseDouble(bInput);
                } catch (IOException e) {
                    System.err.println("Errore di Input/Output!");
                    System.exit(1);
                } catch (NumberFormatException e) {
                    System.out.println(
                            "Errore: Il valore del parametro b deve essere un numero! Ritenta...");
                    retry = true;
                }
            } while (retry);

            // Lettura e validazione del coefficiente c.
            do {
                retry = false;
                System.out.println(
                        "Inserisci il valore del parametro c e premi INVIO");
                try {
                    String cInput = input.readLine();
                    c = Double.parseDouble(cInput);
                } catch (IOException e) {
                    System.err.println("Errore di Input/Output!");
                    System.exit(1);
                } catch (NumberFormatException e) {
                    System.out.println(
                            "Errore: Il valore del parametro c deve essere un numero! Ritenta...");
                    retry = true;
                }
            } while (retry);

            // Il front-end usa soltanto l'API pubblica della business logic.
            if (eq == null)
                eq = new EquazioneSecondoGradoModificabileConRisolutore(a, b,
                        c);
            else {
                eq.setA(a);
                eq.setB(b);
                eq.setC(c);
            }

            eq.solve();
            SoluzioneEquazioneSecondoGrado sol = eq.getSolution();
            System.out.println(sol);

            System.out.println(
                    "Vuoi risolvere un'altra equazione? Inserisci 's' o 'S' per continuare, qualsiasi altro carattere per uscire e premi INVIO");
            String resp = null;
            try {
                String responseInput = input.readLine();
                resp = responseInput.trim().toUpperCase();
            } catch (IOException e) {
                System.err.println("Errore di Input/Output!");
                System.exit(1);
            }
            if (!resp.equals("S"))
                terminate = true;
        }
    }

    /*
     * CONSEGNA DELL'ESERCITAZIONE
     *
     * 1) l'origine del malfunzionamento era nella gestione della variabile booleana retry
     *    che veniva inizializzata a false soltanto all'inizio fuori dai cicli di gestione dell'input
     * 2) pertanto appena valorizzata true per qualsiasi motivo, restava indefinitamente
     *    in tale stato
     * 3) la variabile retry viene dichiarata prima dei cicli di gestione input
     *    e messa a false all'interno di ogni ciclo
     */
}
