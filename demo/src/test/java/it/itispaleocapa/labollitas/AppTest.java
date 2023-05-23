package it.itispaleocapa.labollitas;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProgettoTest {

    public Progetto progetto;

    @BeforeEach
    void setUp() {
        progetto = new Progetto();
    }

    @Test
    void testAggiungiPersonale() {
        Personale personale = new Tecnico("1", "Labollita", "Samuele", 2021, "informatica-telecomunicazioni", true);
        progetto.aggiungiPersonale(personale);
        assertEquals(1, progetto.personale.size());
        assertEquals(personale, progetto.personale.get(0));
    }

    @Test
    void testAggiungiPersonaleConMembroNullo() {
        assertThrows(PersonaleNulloException.class, () -> progetto.aggiungiPersonale(null));
        assertEquals(0, progetto.personale.size());
    }

    @Test
    void testGetPersonale() {
        Personale tecnico = new Tecnico("1", "Labollita", "Samuele", 2021, "informatica-telecomunicazioni", true);
        Personale funzionario = new Funzionario("2", "Bianchi", "Laura", 2020, 5);
        progetto.aggiungiPersonale(tecnico);
        progetto.aggiungiPersonale(funzionario);

        List<Personale> personale = progetto.getPersonale(p -> p.getAnnoAssunzione() >= 2020);
        assertEquals(2, personale.size());
        assertEquals(tecnico, personale.get(0));
        assertEquals(funzionario, personale.get(1));
    }

    @Test
    void testGetPersonaleConFiltroVuoto() {
        Personale tecnico = new Tecnico("1", "Labollita", "Samuele", 2021, "informatica-telecomunicazioni", true);
        Personale funzionario = new Funzionario("2", "Bianchi", "Laura", 2020, 5);
        progetto.aggiungiPersonale(tecnico);
        progetto.aggiungiPersonale(funzionario);

        List<Personale> personale = progetto.getPersonale(p -> false);
        assertEquals(0, personale.size());
    }

    @Test
    void testGetCostoComplessivo() {
        Personale tecnico = new Tecnico("1", "Labollita", "Samuele", 2021, "informatica-telecomunicazioni", true);
        Personale funzionario = new Funzionario("2", "Bianchi", "Laura", 2020, 5);
        progetto.aggiungiPersonale(tecnico);
        progetto.aggiungiPersonale(funzionario);

        double costoComplessivo = progetto.getCostoComplessivo();
        assertEquals(40 + 70, costoComplessivo);
    }

    @Test
    void testCalcolaCostoOrarioConTecnicoInterno() {
        Tecnico tecnico = new Tecnico("1", "Labollita", "Samuele", 2021, "informatica-telecomunicazioni", true);
        double costoOrario = progetto.calcolaCostoOrario(tecnico);
        assertEquals(40 + (2023 - 2021), costoOrario);
    }

    @Test
    void testCalcolaCostoOrarioConTecnicoEsterno() {
        Tecnico tecnico = new Tecnico("1", "Labollita", "Samuele", 2021, "altro", false);
        double costoOrario = progetto.calcolaCostoOrario(tecnico);
        assertEquals(50, costoOrario);
    }

    @Test
    void testCalcolaCostoOrarioConFunzionario() {
        Funzionario funzionario = new Funzionario("2", "Bianchi", "Laura", 2020, 5);
        double costoOrario = progetto.calcolaCostoOrario(funzionario);
        assertEquals(70, costoOrario);
    }

    @Test
    void testCalcolaCostoOrarioConDirigente() {
        Dirigente dirigente = new Dirigente("3", "Verdi", "Giuseppe", 2019);
        double costoOrario = progetto.calcolaCostoOrario(dirigente);
        assertEquals(100, costoOrario);
    }

    @Test
    void testCalcolaCostoOrarioConMembroNonRiconosciuto() {
        Personale membroNonRiconosciuto = new Personale("4", "Gialli", "Paolo", 2022);
        assertThrows(PersonaleNulloException.class, () -> progetto.calcolaCostoOrario(membroNonRiconosciuto));
    }
}
