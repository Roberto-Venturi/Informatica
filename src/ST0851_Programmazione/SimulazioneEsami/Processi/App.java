package ST0851_Programmazione.SimulazioneEsami.Processi;/*
    Implementare una classe chiamata Processo

    la classe ha le seguenti proprietà, con i relativi getter:
    - priorita (long) definisce quanto un Processo sia importante
    - peso (int) definisce quante risorse un processo può occupare
    - terminato (boolean) definisce se il processo ha finito o deve essere eseguito

    la classe possiede solo un costruttore, che accetta i parametri prioria e peso
    si assicura anche che peso sia maggiore di 0
    prioria può essere negativa
    terminato è sempre false alla costruzione
    la classe espone i seguenti metodi

    boolean aggiungiProcesso(Processo p)
    aggiunge un processo, che viene inteso come sottoprocesso. lancia un eccezione se p è null
    non aggiungilo e torna false se, con la sua aggiunta, la somma del peso dei sottoprocessi superasse quella del processo corrente.
    altrimenti torna true

    Processo daiProssimoProcesso()
    torna il sottoprocesso con la priorita piu alta e non concluso
    se non ne ha nessuno, torna se stesso se non è concluso
    in caso contrario, torna null

    void prestaRisorse(int risorse)
    se risorse è <= 0, lancia un'eccezione
    se il processo non ha sottoprocessi da terminare, sottrae le risorse al suo peso e, se arriva a 0, viene segnato come concluso.
    se ha sottoprocessi, sottrae le risorse sia al proprio peso che ai processi figli, andando in ordine di priorità

    es. il processo p0, con peso 50, ha tre sottoprocessi
    - p1, con peso 10 e priorita 21
    - p2, con peso 30 e priorita 1
    - p3, con peso 5 e priorita -5
    chiamando prestaRisorse(11)
    1) p1 va a 0 di peso e diventa concluso
    2) p2 vede il suo peso scendere a 29
    3) p0 vede il suo peso scendere a 39
    (si ricorda che abbiamo seguito l'ordine di priorita)
    se poi chiamando prestaRisorse(37)
    1) p2 va a 0 di peso e diventa concluso
    2) p3 va a 0 di peso e diventa concluso
    3) p0 vede il suo peso scendere a 2
*/



// DECOMMENTA PER TESTARE


import java.util.Objects;

public class App {

    private static int passed = 0;
    private static int failed = 0;

    public static void main(String[] args) {
        System.out.println("=== TEST SU Processo ===");

        testCostruttorePesoValidoEPrioritaNegativaAmmessa();
        testCostruttorePesoNonValidoLanciaEccezione();
        testTerminatoFalseAllaCostruzione();

        testAggiungiProcessoNullLanciaEccezione();
        testAggiungiProcessoSuperaSommaPesiRitornaFalse();
        testAggiungiProcessoSommaPesiEsattamenteUgualeRitornaTrue();
        testAggiungiProcessoSommaPesiMinoreRitornaTrue();

        testDaiProssimoProcessoConFigliScegliePrioritaMassimaNonTerminata();
        testDaiProssimoProcessoSeFigliTuttiTerminatiTornaSeStessoSeNonTerminato();
        testDaiProssimoProcessoSeTuttoTerminatoTornaNull();

        testPrestaRisorseArgomentoNonValidoLanciaEccezione();
        testPrestaRisorseSenzaFigliScalaSoloSeStessoEConclude();
        testPrestaRisorseConFigliEsegueScenarioEsempio();
        testPrestaRisorseConFigliRisorseMoltoGrandiNonVaSottoZeroEConclude();
        testPrestaRisorseOrdineDiPrioritaStabileSuNonTerminati();
        testDaiProssimoProcessoDopoPrestaRisorseCambiaCorrettaSelezione();

        System.out.println();
        System.out.println("=== RISULTATI ===");
        System.out.println("Passati: " + passed);
        System.out.println("Falliti: " + failed);

        if (failed > 0) {
            System.out.println("Esito finale: CI SONO FALLIMENTI");
        } else {
            System.out.println("Esito finale: TUTTI I TEST PASSATI");
        }
    }

    private static void testCostruttorePesoValidoEPrioritaNegativaAmmessa() {
        String name = "Costruttore: accetta priorità negativa e peso > 0, terminato=false";
        try {
            Processo p = new Processo(-10L, 7);
            assertEquals(name + " (priorità)", -10L, p.getPriorita());
            assertEquals(name + " (peso)", 7, p.getPeso());
            assertEquals(name + " (terminato)", false, p.isTerminato());
            pass(name);
        } catch (Throwable t) {
            fail(name, "Non doveva lanciare eccezioni. Ricevuto: " + t);
        }
    }

    private static void testCostruttorePesoNonValidoLanciaEccezione() {
        String name = "Costruttore: peso <= 0 deve lanciare eccezione";
        assertThrows(name + " (peso=0)", RuntimeException.class, () -> new Processo(0L, 0));
        assertThrows(name + " (peso=-3)", RuntimeException.class, () -> new Processo(0L, -3));
        pass(name);
    }

    private static void testTerminatoFalseAllaCostruzione() {
        String name = "Costruttore: terminato deve essere sempre false alla costruzione";
        try {
            Processo p = new Processo(5L, 1);
            assertEquals(name, false, p.isTerminato());
            pass(name);
        } catch (Throwable t) {
            fail(name, "Non doveva lanciare eccezioni. Ricevuto: " + t);
        }
    }

    private static void testAggiungiProcessoNullLanciaEccezione() {
        String name = "aggiungiProcesso: se p è null deve lanciare eccezione";
        Processo root = new Processo(1L, 10);
        assertThrows(name, RuntimeException.class, () -> root.aggiungiProcesso(null));
        pass(name);
    }

    private static void testAggiungiProcessoSuperaSommaPesiRitornaFalse() {
        String name = "aggiungiProcesso: se la somma pesi sottoprocessi supererebbe il peso del padre, ritorna false";
        try {
            Processo root = new Processo(1L, 10);
            Processo c1 = new Processo(5L, 6);
            Processo c2 = new Processo(3L, 5);

            boolean ok1 = root.aggiungiProcesso(c1);
            assertEquals(name + " (prima aggiunta deve riuscire)", true, ok1);

            boolean ok2 = root.aggiungiProcesso(c2);
            assertEquals(name + " (seconda aggiunta deve fallire)", false, ok2);

            Processo next = root.daiProssimoProcesso();
            assertSame(name + " (non deve aver aggiunto il secondo figlio, quindi next deve essere c1)", c1, next);

            pass(name);
        } catch (Throwable t) {
            fail(name, "Eccezione inattesa: " + t);
        }
    }

    private static void testAggiungiProcessoSommaPesiEsattamenteUgualeRitornaTrue() {
        String name = "aggiungiProcesso: somma pesi sottoprocessi uguale al peso del padre è consentita (true)";
        try {
            Processo root = new Processo(1L, 10);
            Processo c1 = new Processo(2L, 4);
            Processo c2 = new Processo(3L, 6);

            assertEquals(name + " (aggiunta c1)", true, root.aggiungiProcesso(c1));
            assertEquals(name + " (aggiunta c2)", true, root.aggiungiProcesso(c2));

            Processo next = root.daiProssimoProcesso();
            assertSame(name + " (priorità maggiore tra c1 e c2)", c2, next);

            pass(name);
        } catch (Throwable t) {
            fail(name, "Eccezione inattesa: " + t);
        }
    }

    private static void testAggiungiProcessoSommaPesiMinoreRitornaTrue() {
        String name = "aggiungiProcesso: somma pesi sottoprocessi minore del peso del padre ritorna true";
        try {
            Processo root = new Processo(1L, 100);
            Processo c1 = new Processo(2L, 10);
            Processo c2 = new Processo(3L, 20);
            Processo c3 = new Processo(-5L, 30);

            assertEquals(name + " (c1)", true, root.aggiungiProcesso(c1));
            assertEquals(name + " (c2)", true, root.aggiungiProcesso(c2));
            assertEquals(name + " (c3)", true, root.aggiungiProcesso(c3));

            Processo next = root.daiProssimoProcesso();
            assertSame(name + " (priorità massima deve essere c2)", c2, next);

            pass(name);
        } catch (Throwable t) {
            fail(name, "Eccezione inattesa: " + t);
        }
    }

    private static void testDaiProssimoProcessoConFigliScegliePrioritaMassimaNonTerminata() {
        String name = "daiProssimoProcesso: con figli, ritorna il figlio non terminato con priorità massima";
        try {
            Processo root = new Processo(0L, 50);
            Processo p1 = new Processo(21L, 10);
            Processo p2 = new Processo(1L, 30);
            Processo p3 = new Processo(-5L, 5);

            root.aggiungiProcesso(p1);
            root.aggiungiProcesso(p2);
            root.aggiungiProcesso(p3);

            assertSame(name + " (prima scelta)", p1, root.daiProssimoProcesso());

            root.prestaRisorse(10);
            assertEquals(name + " (p1 deve essere terminato dopo 10 risorse)", true, p1.isTerminato());
            assertSame(name + " (dopo p1 terminato, scelta deve essere p2)", p2, root.daiProssimoProcesso());

            pass(name);
        } catch (Throwable t) {
            fail(name, "Eccezione inattesa: " + t);
        }
    }

    private static void testDaiProssimoProcessoSeFigliTuttiTerminatiTornaSeStessoSeNonTerminato() {
        String name = "daiProssimoProcesso: se tutti i figli sono terminati, ritorna se stesso se non terminato";
        try {
            Processo root = new Processo(10L, 6);
            Processo c1 = new Processo(1L, 2);
            Processo c2 = new Processo(2L, 3);

            root.aggiungiProcesso(c1);
            root.aggiungiProcesso(c2);

            root.prestaRisorse(2);
            root.prestaRisorse(3);

            assertEquals(name + " (c1 terminato)", true, c1.isTerminato());
            assertEquals(name + " (c2 terminato)", true, c2.isTerminato());
            assertEquals(name + " (root non deve essere terminato per forza)", false, root.isTerminato());

            Processo next = root.daiProssimoProcesso();
            assertSame(name + " (se stesso)", root, next);

            pass(name);
        } catch (Throwable t) {
            fail(name, "Eccezione inattesa: " + t);
        }
    }

    private static void testDaiProssimoProcessoSeTuttoTerminatoTornaNull() {
        String name = "daiProssimoProcesso: se non ci sono figli non terminati e anche se stesso è terminato, ritorna null";
        try {
            Processo root = new Processo(10L, 3);

            assertSame(name + " (inizialmente, deve tornare se stesso)", root, root.daiProssimoProcesso());

            root.prestaRisorse(3);
            assertEquals(name + " (root terminato)", true, root.isTerminato());

            Processo next = root.daiProssimoProcesso();
            assertEquals(name + " (null atteso)", null, next);

            pass(name);
        } catch (Throwable t) {
            fail(name, "Eccezione inattesa: " + t);
        }
    }

    private static void testPrestaRisorseArgomentoNonValidoLanciaEccezione() {
        String name = "prestaRisorse: se risorse <= 0 lancia eccezione";
        Processo p = new Processo(0L, 5);
        assertThrows(name + " (risorse=0)", RuntimeException.class, () -> p.prestaRisorse(0));
        assertThrows(name + " (risorse=-1)", RuntimeException.class, () -> p.prestaRisorse(-1));
        pass(name);
    }

    private static void testPrestaRisorseSenzaFigliScalaSoloSeStessoEConclude() {
        String name = "prestaRisorse: senza figli scala il proprio peso e conclude a 0";
        try {
            Processo p = new Processo(0L, 5);

            p.prestaRisorse(2);
            assertEquals(name + " (peso dopo 2)", 3, p.getPeso());
            assertEquals(name + " (non terminato)", false, p.isTerminato());

            p.prestaRisorse(3);
            assertEquals(name + " (peso a 0)", 0, p.getPeso());
            assertEquals(name + " (terminato)", true, p.isTerminato());

            pass(name);
        } catch (Throwable t) {
            fail(name, "Eccezione inattesa: " + t);
        }
    }

    private static void testPrestaRisorseConFigliEsegueScenarioEsempio() {
        String name = "prestaRisorse: scenario esempio (ordine priorità, scalatura su figli + padre)";
        try {
            Processo p0 = new Processo(0L, 50);
            Processo p1 = new Processo(21L, 10);
            Processo p2 = new Processo(1L, 30);
            Processo p3 = new Processo(-5L, 5);

            assertEquals(name + " (aggiungi p1)", true, p0.aggiungiProcesso(p1));
            assertEquals(name + " (aggiungi p2)", true, p0.aggiungiProcesso(p2));
            assertEquals(name + " (aggiungi p3)", true, p0.aggiungiProcesso(p3));

            p0.prestaRisorse(11);

            assertEquals(name + " (p1 terminato)", true, p1.isTerminato());
            assertEquals(name + " (p1 peso)", 0, p1.getPeso());

            assertEquals(name + " (p2 peso deve essere 29)", 29, p2.getPeso());
            assertEquals(name + " (p2 non terminato)", false, p2.isTerminato());

            assertEquals(name + " (p0 peso deve essere 39)", 39, p0.getPeso());
            assertEquals(name + " (p0 non terminato)", false, p0.isTerminato());

            p0.prestaRisorse(37);

            assertEquals(name + " (p2 terminato)", true, p2.isTerminato());
            assertEquals(name + " (p2 peso)", 0, p2.getPeso());

            assertEquals(name + " (p3 terminato)", true, p3.isTerminato());
            assertEquals(name + " (p3 peso)", 0, p3.getPeso());

            assertEquals(name + " (p0 peso deve essere 2)", 2, p0.getPeso());
            assertEquals(name + " (p0 non terminato)", false, p0.isTerminato());

            pass(name);
        } catch (Throwable t) {
            fail(name, "Eccezione inattesa: " + t);
        }
    }

    private static void testPrestaRisorseConFigliRisorseMoltoGrandiNonVaSottoZeroEConclude() {
        String name = "prestaRisorse: con figli, se risorse sono tante, pesi non vanno sotto 0 e tutti possono concludersi";
        try {
            Processo root = new Processo(0L, 10);
            Processo a = new Processo(5L, 3);
            Processo b = new Processo(4L, 4);

            root.aggiungiProcesso(a);
            root.aggiungiProcesso(b);

            root.prestaRisorse(100);

            assertEquals(name + " (a terminato)", true, a.isTerminato());
            assertEquals(name + " (b terminato)", true, b.isTerminato());
            assertEquals(name + " (root terminato)", true, root.isTerminato());

            assertEquals(name + " (a peso 0)", 0, a.getPeso());
            assertEquals(name + " (b peso 0)", 0, b.getPeso());
            assertEquals(name + " (root peso 0)", 0, root.getPeso());

            assertEquals(name + " (daiProssimoProcesso null)", null, root.daiProssimoProcesso());

            pass(name);
        } catch (Throwable t) {
            fail(name, "Eccezione inattesa: " + t);
        }
    }

    private static void testPrestaRisorseOrdineDiPrioritaStabileSuNonTerminati() {
        String name = "prestaRisorse: sottrae ai figli in ordine di priorità (verifica con traccia di effetti attesi)";
        try {
            Processo root = new Processo(0L, 20);
            Processo high = new Processo(100L, 5);
            Processo mid = new Processo(10L, 5);
            Processo low = new Processo(-10L, 5);

            root.aggiungiProcesso(low);
            root.aggiungiProcesso(mid);
            root.aggiungiProcesso(high);

            root.prestaRisorse(6);

            assertEquals(name + " (high deve essere terminato)", true, high.isTerminato());
            assertEquals(name + " (mid deve aver perso 1)", 4, mid.getPeso());
            assertEquals(name + " (low invariato)", 5, low.getPeso());
            assertEquals(name + " (root deve aver perso 6)", 14, root.getPeso());

            pass(name);
        } catch (Throwable t) {
            fail(name, "Eccezione inattesa: " + t);
        }
    }

    private static void testDaiProssimoProcessoDopoPrestaRisorseCambiaCorrettaSelezione() {
        String name = "daiProssimoProcesso: dopo prestaRisorse, seleziona il figlio ancora non terminato con priorità massima";
        try {
            Processo root = new Processo(0L, 30);
            Processo a = new Processo(50L, 2);
            Processo b = new Processo(40L, 10);

            root.aggiungiProcesso(a);
            root.aggiungiProcesso(b);

            assertSame(name + " (prima a)", a, root.daiProssimoProcesso());

            root.prestaRisorse(2);

            assertEquals(name + " (a terminato)", true, a.isTerminato());
            assertSame(name + " (poi b)", b, root.daiProssimoProcesso());

            pass(name);
        } catch (Throwable t) {
            fail(name, "Eccezione inattesa: " + t);
        }
    }

    private static void assertEquals(String label, Object expected, Object actual) {
        if (!Objects.equals(expected, actual)) {
            fail(label, "Atteso: " + expected + " | Trovato: " + actual);
            throw new AssertionError(label);
        } else {
            System.out.println("[OK] " + label + " -> " + actual);
        }
    }

    private static void assertEquals(String label, int expected, int actual) {
        if (expected != actual) {
            fail(label, "Atteso: " + expected + " | Trovato: " + actual);
            throw new AssertionError(label);
        } else {
            System.out.println("[OK] " + label + " -> " + actual);
        }
    }

    private static void assertEquals(String label, long expected, long actual) {
        if (expected != actual) {
            fail(label, "Atteso: " + expected + " | Trovato: " + actual);
            throw new AssertionError(label);
        } else {
            System.out.println("[OK] " + label + " -> " + actual);
        }
    }

    private static void assertSame(String label, Object expected, Object actual) {
        if (expected != actual) {
            fail(label, "Atteso stessa istanza: " + id(expected) + " | Trovato: " + id(actual));
            throw new AssertionError(label);
        } else {
            System.out.println("[OK] " + label + " -> stessa istanza (" + id(actual) + ")");
        }
    }

    private static void assertThrows(String label, Class<? extends Throwable> expectedType, Runnable action) {
        try {
            action.run();
            fail(label, "Attesa eccezione " + expectedType.getSimpleName() + " ma non è stata lanciata");
            throw new AssertionError(label);
        } catch (Throwable t) {
            if (expectedType.isInstance(t)) {
                System.out.println("[OK] " + label + " -> lanciata " + t.getClass().getSimpleName());
            } else {
                fail(label, "Attesa " + expectedType.getSimpleName() + " ma ricevuta " + t.getClass().getSimpleName() + ": " + t);
                throw new AssertionError(label);
            }
        }
    }

    private static void pass(String testName) {
        passed++;
        System.out.println(" PASS: " + testName);
        System.out.println();
    }

    private static void fail(String testName, String reason) {
        failed++;
        System.out.println(" FAIL: " + testName);
        System.out.println("   Motivo: " + reason);
    }

    private static String id(Object o) {
        if (o == null) return "null";
        return o.getClass().getSimpleName() + "@" + Integer.toHexString(System.identityHashCode(o));
    }
}

