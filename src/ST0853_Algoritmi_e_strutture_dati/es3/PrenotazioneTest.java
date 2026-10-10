//package it.unicam.cs.asdl.es3;
package ST0853_Algoritmi_e_strutture_dati.es3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import java.util.GregorianCalendar;
import org.junit.jupiter.api.Test;

/**
 * Test JUnit per {@link Prenotazione}.
 * <p>
 * I test insistono in particolare sulla coerenza tra uguaglianza logica,
 * codice hash e ordinamento naturale: i campi che identificano logicamente una
 * prenotazione sono aula e time slot, mentre docente e motivo descrivono altre
 * informazioni dello stato e possono cambiare senza modificarne l'identità.
 *
 * @author Luca Tesei
 */
class PrenotazioneTest {

    private static GregorianCalendar date(int year, int month, int day,
                                          int hour, int minute) {
        return new GregorianCalendar(year, month, day, hour, minute);
    }

    private static TimeSlot slot(int startHour, int startMinute, int stopHour,
                                 int stopMinute) {
        return new TimeSlot(date(2019, 10, 4, startHour, startMinute),
                date(2019, 10, 4, stopHour, stopMinute));
    }

    @Test
    void constructorRejectsNullParameters() {
        TimeSlot ts = slot(11, 0, 13, 0);

        // Il contratto del costruttore vieta null per ciascuno dei quattro campi.
        assertThrows(NullPointerException.class,
                () -> new Prenotazione(null, ts, "Luca Tesei", "Lezione"));
        assertThrows(NullPointerException.class,
                () -> new Prenotazione("B1", null, "Luca Tesei", "Lezione"));
        assertThrows(NullPointerException.class,
                () -> new Prenotazione("B1", ts, null, "Lezione"));
        assertThrows(NullPointerException.class,
                () -> new Prenotazione("B1", ts, "Luca Tesei", null));
    }

    @Test
    void constructorAndGettersPreserveState() {
        TimeSlot ts = slot(11, 0, 13, 0);
        Prenotazione p = new Prenotazione("B1", ts, "Luca Tesei",
                "Lezione ASDL");

        // Verifichiamo che il costruttore inizializzi tutti i componenti dello stato.
        assertEquals("B1", p.getAula());
        assertEquals(ts, p.getTimeSlot());
        assertEquals("Luca Tesei", p.getDocente());
        assertEquals("Lezione ASDL", p.getMotivo());
    }

    @Test
    void settersChangeOnlyMutableDescriptiveFields() {
        Prenotazione p = new Prenotazione("B1", slot(11, 0, 13, 0),
                "Docente A", "Motivo A");

        p.setDocente("Docente B");
        p.setMotivo("Motivo B");

        assertEquals("Docente B", p.getDocente());
        assertEquals("Motivo B", p.getMotivo());

        // Anche i setter mantengono l'invariante che nessun campo sia null.
        assertThrows(NullPointerException.class, () -> p.setDocente(null));
        assertThrows(NullPointerException.class, () -> p.setMotivo(null));
    }

    @Test
    void equalsUsesOnlyRoomAndTimeSlot() {
        TimeSlot ts1 = slot(11, 0, 13, 0);
        TimeSlot ts2 = slot(11, 0, 13, 0);

        Prenotazione p1 = new Prenotazione("B1", ts1, "Docente A",
                "Lezione");
        Prenotazione p2 = new Prenotazione("B1", ts2, "Docente B",
                "Esame");

        // Sono oggetti distinti nello heap, ma rappresentano la stessa prenotazione.
        assertNotSame(p1, p2);
        assertEquals(p1, p2);
        assertEquals(p2, p1); // simmetria di equals

        // Docente e motivo non determinano l'identità logica della prenotazione.
        p1.setDocente("Docente C");
        p1.setMotivo("Riunione");
        assertEquals(p1, p2);
    }

    @Test
    void equalsDistinguishesRoomAndTimeSlot() {
        Prenotazione reference = new Prenotazione("B1", slot(11, 0, 13, 0),
                "A", "B");
        Prenotazione differentRoom = new Prenotazione("C1",
                slot(11, 0, 13, 0), "A", "B");
        Prenotazione differentSlot = new Prenotazione("B1",
                slot(14, 0, 16, 0), "A", "B");

        assertNotEquals(reference, differentRoom);
        assertNotEquals(reference, differentSlot);
        assertNotEquals(reference, null);
        assertNotEquals(reference, "B1");
    }

    @Test
    void hashCodeIsConsistentWithEquals() {
        Prenotazione p1 = new Prenotazione("B1", slot(11, 0, 13, 0),
                "Docente A", "Lezione");
        Prenotazione p2 = new Prenotazione("B1", slot(11, 0, 13, 0),
                "Docente B", "Esame");

        // Il contratto di hashCode richiede: oggetti uguali -> stesso hash code.
        assertEquals(p1, p2);
        assertEquals(p1.hashCode(), p2.hashCode());

        // Cambiare campi esclusi da equals non deve cambiare il codice hash.
        int before = p1.hashCode();
        p1.setDocente("Altro docente");
        p1.setMotivo("Altro motivo");
        assertEquals(before, p1.hashCode());
    }

    @Test
    void compareToOrdersFirstByTimeSlot() {
        Prenotazione earlier = new Prenotazione("Z9", slot(9, 0, 10, 0),
                "A", "B");
        Prenotazione later = new Prenotazione("A1", slot(11, 0, 12, 0),
                "A", "B");

        // L'aula non conta se i time slot sono diversi: viene confrontato prima il tempo.
        assertTrue(earlier.compareTo(later) < 0);
        assertTrue(later.compareTo(earlier) > 0);
    }

    @Test
    void compareToUsesRoomWhenTimeSlotsAreEqual() {
        Prenotazione b1 = new Prenotazione("B1", slot(11, 0, 13, 0),
                "A", "B");
        Prenotazione m1 = new Prenotazione("M1", slot(11, 0, 13, 0),
                "C", "D");

        // A parità di time slot si usa l'ordinamento naturale delle String delle aule.
        assertTrue(b1.compareTo(m1) < 0);
        assertTrue(m1.compareTo(b1) > 0);
    }

    @Test
    void compareToIsCompatibleWithEquals() {
        Prenotazione p1 = new Prenotazione("B1", slot(11, 0, 13, 0),
                "Docente A", "Lezione");
        Prenotazione p2 = new Prenotazione("B1", slot(11, 0, 13, 0),
                "Docente B", "Esame");

        // Docente e motivo non partecipano né a equals né all'ordinamento naturale.
        assertEquals(p1, p2);
        assertEquals(0, p1.compareTo(p2));
        assertEquals(0, p2.compareTo(p1));
    }

    @Test
    void compareToRejectsNull() {
        Prenotazione p = new Prenotazione("B1", slot(11, 0, 13, 0),
                "A", "B");
        assertThrows(NullPointerException.class, () -> p.compareTo(null));
    }

    @Test
    void toStringRepresentsAllStateFields() {
        Prenotazione p = new Prenotazione("B1", slot(11, 0, 13, 0),
                "Luca Tesei", "Lezione ASDL");

        assertEquals(
                "Prenotazione [aula = B1, time slot =[4/11/2019 11.0 - 4/11/2019 13.0], docente=Luca Tesei, motivo=Lezione ASDL]",
                p.toString());
    }
}
