package ST0851_Programmazione.SimulazioneEsami.CorsaDiCavalli;/*
    Implementare due classi:
    Cavallo.java
    CorsaDiCavalli.java
    Cavallo è una classe con le seguenti proprietà, tutte gestite da appositi getter e setter (il nome è immutabile):
    - String nome
    - int eta
    - int numeroVittorie
    - int numeroPartecipazioni
    ed espone i seguenti metodi:
    - float tassoDiVittoria (in centesimi)
    - boolean equals(Object o) i valori da usare per equals sono solo il nome
    Ha inoltre un unico costruttore che accetta tutti i parametri e si assicura che abbia almeno 5 anni 
    ed un nome alfabetico composto da almeno 4 lettere

    CorsaDiCavalli ha un costruttore che accetta un numero intero e un float. 
    l'intero deve essere maggiore di 0 e definisce il numero massimo dei cavalli che la gara può gestire.
    il float definisce la lunghezza del percorso da percorrere.
    Espone anche i seguenti metodi
    - boolean garaIniziata()
    - boolean garaFinita()
    - boolean aggiungiCavallo(Cavallo c) 
    torna true se ha aggiunto un cavallo, e segna la partecipazione sul cavallo.
    torna false se non lha aggiunto perché la corsa era piena o il cavallo era già presente (tramite equals)
    lancia un eccezione se è null o se la gara è iniziata
    - void avanzaCavallo(String nomeCavallo, float distanza) 
    se nessun cavallo ha qual nome, se la stringa è null, se la gara era finita o la quantita <= 0, lancia un eccezione
    avanza la posizione del cavallo della quantita passata (partono da 0)
    se la gara non era iniziata, inizia la gara
    se il cavallo supera la lunghezza del percorso, concludi la gara e segna la vittoria al cavallo

    Non e' permesso l'uso delle classi della libreria standard di java, salvo String e le sottoclassi di Exception
*/



public class App {

    // DECOMMETA IL BLOCCO QUI SOTTO PER ESEGUIRE I TEST

    private static int testCounter = 1;

    private static void printSuccess() {
        System.out.println("TEST N." + testCounter + " SUCCESS");
        testCounter++;
    }

    private static void printFail() {
        System.out.println("TEST N." + testCounter + " FAIL");
        testCounter++;
    }

    private static void assertTrue(boolean cond) {
        if (cond) printSuccess(); else printFail();
    }

    private static void assertFalse(boolean cond) {
        if (!cond) printSuccess(); else printFail();
    }

    private static void assertIntEquals(int expected, int actual) {
        if (expected == actual) printSuccess(); else printFail();
    }

    private static void assertFloatEquals(float expected, float actual, float eps) {
        float diff = expected - actual;
        if (diff < 0) diff = -diff;
        if (diff <= eps) printSuccess(); else printFail();
    }

    private static void assertNotNull(Object o) {
        if (o != null) printSuccess(); else printFail();
    }

    private static void assertThrows(Runnable r) {
        try {
            r.run();
            printFail();
        } catch (Exception e) {
            printSuccess();
        }
    }

    private static Cavallo makeValidHorse(String nome, int eta, int vittorie, int partecipazioni) {
        return new Cavallo(nome, eta, vittorie, partecipazioni);
    }

    public static void main(String[] args) {

        assertThrows(new Runnable() { public void run() { new Cavallo(null, 5, 0, 0); } });
        assertThrows(new Runnable() { public void run() { new Cavallo("", 5, 0, 0); } });
        assertThrows(new Runnable() { public void run() { new Cavallo("A", 5, 0, 0); } });
        assertThrows(new Runnable() { public void run() { new Cavallo("Abc", 5, 0, 0); } });
        assertThrows(new Runnable() { public void run() { new Cavallo("Abc1", 5, 0, 0); } });
        assertThrows(new Runnable() { public void run() { new Cavallo("Abc!", 5, 0, 0); } });
        assertThrows(new Runnable() { public void run() { new Cavallo("A bcd", 5, 0, 0); } });
        assertThrows(new Runnable() { public void run() { new Cavallo("Ciao", 4, 0, 0); } });
        assertThrows(new Runnable() { public void run() { new Cavallo("Test", -1, 0, 0); } });

        Cavallo c1 = makeValidHorse("Lampo", 5, 0, 0);
        assertNotNull(c1);
        assertTrue("Lampo".equals(c1.getNome()));
        assertIntEquals(5, c1.getEta());
        assertIntEquals(0, c1.getNumeroVittorie());
        assertIntEquals(0, c1.getNumeroPartecipazioni());
        assertFloatEquals(0.0f, c1.tassoDiVittoria(), 0.0001f);

        c1.setEta(6);
        c1.setNumeroPartecipazioni(10);
        c1.setNumeroVittorie(2);
        assertIntEquals(6, c1.getEta());
        assertIntEquals(2, c1.getNumeroVittorie());
        assertIntEquals(10, c1.getNumeroPartecipazioni());
        assertFloatEquals(20.0f, c1.tassoDiVittoria(), 0.0001f);

        Cavallo c2 = makeValidHorse("Lampo", 9, 7, 11);
        Cavallo c3 = makeValidHorse("Fulmine", 9, 7, 11);
        assertTrue(c1.equals(c2));
        assertFalse(c1.equals(c3));
        assertFalse(c1.equals(null));
        assertFalse(c1.equals("Lampo"));

        Cavallo c4 = makeValidHorse("Zorro", 8, 3, 3);
        assertFloatEquals(100.0f, c4.tassoDiVittoria(), 0.0001f);

        Cavallo c5 = makeValidHorse("Ninja", 7, 1, 3);
        assertFloatEquals((1.0f * 100.0f) / 3.0f, c5.tassoDiVittoria(), 0.0001f);

        Cavallo c6 = makeValidHorse("Orfeo", 10, 0, 12);
        assertFloatEquals(0.0f, c6.tassoDiVittoria(), 0.0001f);

        assertThrows(new Runnable() { public void run() { new CorsaDiCavalli(0, 100.0f); } });
        assertThrows(new Runnable() { public void run() { new CorsaDiCavalli(-3, 100.0f); } });

        CorsaDiCavalli gara1 = new CorsaDiCavalli(2, 50.0f);
        assertNotNull(gara1);
        assertFalse(gara1.garaIniziata());
        assertFalse(gara1.garaFinita());
        Cavallo g1h1 = makeValidHorse("Argo", 6, 0, 0);
        Cavallo g1h2 = makeValidHorse("Boris", 7, 0, 0);
        Cavallo g1h3 = makeValidHorse("Argo", 9, 10, 10);

        int p0 = g1h1.getNumeroPartecipazioni();
        assertTrue(gara1.aggiungiCavallo(g1h1));
        assertIntEquals(p0 + 1, g1h1.getNumeroPartecipazioni());
        assertFalse(gara1.garaIniziata());
        assertFalse(gara1.garaFinita());

        int p1 = g1h2.getNumeroPartecipazioni();
        assertTrue(gara1.aggiungiCavallo(g1h2));
        assertIntEquals(p1 + 1, g1h2.getNumeroPartecipazioni());

        int pDup = g1h3.getNumeroPartecipazioni();
        assertFalse(gara1.aggiungiCavallo(g1h3));
        assertIntEquals(pDup, g1h3.getNumeroPartecipazioni());

        Cavallo extra = makeValidHorse("Cobra", 6, 0, 0);
        int pExtra = extra.getNumeroPartecipazioni();
        assertFalse(gara1.aggiungiCavallo(extra));
        assertIntEquals(pExtra, extra.getNumeroPartecipazioni());

        assertThrows(new Runnable() { public void run() { gara1.aggiungiCavallo(null); } });

        assertThrows(new Runnable() { public void run() { gara1.avanzaCavallo(null, 1.0f); } });
        assertThrows(new Runnable() { public void run() { gara1.avanzaCavallo("NonEsiste", 1.0f); } });
        assertThrows(new Runnable() { public void run() { gara1.avanzaCavallo("Argo", 0.0f); } });
        assertThrows(new Runnable() { public void run() { gara1.avanzaCavallo("Argo", -2.0f); } });

        assertFalse(gara1.garaIniziata());
        gara1.avanzaCavallo("Argo", 10.0f);
        assertTrue(gara1.garaIniziata());
        assertFalse(gara1.garaFinita());

        assertThrows(new Runnable() { public void run() { gara1.aggiungiCavallo(makeValidHorse("Dante", 6, 0, 0)); } });

        gara1.avanzaCavallo("Boris", 20.0f);
        assertTrue(gara1.garaIniziata());
        assertFalse(gara1.garaFinita());

        int vBeforeArgo = g1h1.getNumeroVittorie();
        gara1.avanzaCavallo("Argo", 41.0f);
        assertTrue(gara1.garaFinita());
        assertIntEquals(vBeforeArgo + 1, g1h1.getNumeroVittorie());

        assertThrows(new Runnable() { public void run() { gara1.avanzaCavallo("Boris", 1.0f); } });
        assertThrows(new Runnable() { public void run() { gara1.avanzaCavallo("Argo", 1.0f); } });

        CorsaDiCavalli gara2 = new CorsaDiCavalli(3, 30.0f);
        Cavallo a = makeValidHorse("Ares", 6, 0, 0);
        Cavallo b = makeValidHorse("Brio", 6, 0, 0);
        Cavallo d = makeValidHorse("Drago", 6, 0, 0);

        int pa = a.getNumeroPartecipazioni();
        int pb = b.getNumeroPartecipazioni();
        int pd = d.getNumeroPartecipazioni();

        assertTrue(gara2.aggiungiCavallo(a));
        assertTrue(gara2.aggiungiCavallo(b));
        assertTrue(gara2.aggiungiCavallo(d));

        assertIntEquals(pa + 1, a.getNumeroPartecipazioni());
        assertIntEquals(pb + 1, b.getNumeroPartecipazioni());
        assertIntEquals(pd + 1, d.getNumeroPartecipazioni());

        assertFalse(gara2.garaIniziata());
        assertFalse(gara2.garaFinita());

        gara2.avanzaCavallo("Brio", 5.0f);
        assertTrue(gara2.garaIniziata());
        assertFalse(gara2.garaFinita());

        gara2.avanzaCavallo("Ares", 10.0f);
        gara2.avanzaCavallo("Drago", 12.0f);
        assertTrue(gara2.garaIniziata());
        assertFalse(gara2.garaFinita());

        int vb = b.getNumeroVittorie();
        gara2.avanzaCavallo("Brio", 25.0f);
        assertTrue(gara2.garaFinita());
        assertIntEquals(vb + 1, b.getNumeroVittorie());

        assertThrows(new Runnable() { public void run() { gara2.avanzaCavallo("Ares", 1.0f); } });
        assertThrows(new Runnable() { public void run() { gara2.avanzaCavallo("Drago", 1.0f); } });
        CorsaDiCavalli gara3 = new CorsaDiCavalli(1, 10.0f);
        Cavallo solo = makeValidHorse("Sole", 5, 0, 0);
        int ps = solo.getNumeroPartecipazioni();
        assertTrue(gara3.aggiungiCavallo(solo));
        assertIntEquals(ps + 1, solo.getNumeroPartecipazioni());
        assertFalse(gara3.garaIniziata());
        assertFalse(gara3.garaFinita());

        int vs0 = solo.getNumeroVittorie();
        gara3.avanzaCavallo("Sole", 10.0f);
        assertTrue(gara3.garaIniziata());
        assertTrue(gara3.garaFinita());
        assertIntEquals(vs0 + 1, solo.getNumeroVittorie());

        CorsaDiCavalli gara4 = new CorsaDiCavalli(2, 100.0f);
        Cavallo hA = makeValidHorse("Alpha", 9, 0, 0);
        Cavallo hB = makeValidHorse("Beta", 9, 0, 0);

        assertTrue(gara4.aggiungiCavallo(hA));
        assertTrue(gara4.aggiungiCavallo(hB));

        assertFalse(gara4.garaIniziata());
        gara4.avanzaCavallo("Alpha", 1.0f);
        assertTrue(gara4.garaIniziata());
        assertFalse(gara4.garaFinita());

        gara4.avanzaCavallo("Beta", 99.0f);
        assertFalse(gara4.garaFinita());

        int vAlpha = hA.getNumeroVittorie();
        gara4.avanzaCavallo("Alpha", 200.0f);
        assertTrue(gara4.garaFinita());
        assertIntEquals(vAlpha + 1, hA.getNumeroVittorie());

        CorsaDiCavalli gara5 = new CorsaDiCavalli(2, 40.0f);
        Cavallo x = makeValidHorse("Xeno", 6, 0, 0);
        Cavallo y = makeValidHorse("Yuri", 6, 0, 0);
        assertTrue(gara5.aggiungiCavallo(x));
        assertTrue(gara5.aggiungiCavallo(y));

        int vx = x.getNumeroVittorie();
        gara5.avanzaCavallo("Yuri", 39.0f);
        assertFalse(gara5.garaFinita());
        gara5.avanzaCavallo("Xeno", 40.0f);
        assertTrue(gara5.garaFinita());
        assertIntEquals(vx + 1, x.getNumeroVittorie());

        Cavallo eq1 = makeValidHorse("Same", 6, 0, 0);
        Cavallo eq2 = makeValidHorse("Same", 10, 99, 99);
        Cavallo eq3 = makeValidHorse("Diff", 10, 99, 99);
        assertTrue(eq1.equals(eq2));
        assertFalse(eq1.equals(eq3));

        eq1.setNumeroPartecipazioni(0);
        eq1.setNumeroVittorie(0);
        assertFloatEquals(0.0f, eq1.tassoDiVittoria(), 0.0001f);

        eq1.setNumeroPartecipazioni(5);
        eq1.setNumeroVittorie(5);
        assertFloatEquals(100.0f, eq1.tassoDiVittoria(), 0.0001f);

        eq1.setNumeroPartecipazioni(5);
        eq1.setNumeroVittorie(2);
        assertFloatEquals(40.0f, eq1.tassoDiVittoria(), 0.0001f);
    }
}