package it.itispaleocapa.labollitas;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for simple App.
 */
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProgettoTest {

    private Progetto progetto;

    @BeforeEach
    void setUp() {
        progetto = new Progetto();
    }

    @Test
    void testAggiungiPersonale() throws PersonaleNulloException {
        Personale dirigente = new Dirigente("D1", "Rossi", "Mario", 2010);
        progetto.aggiungiPersonale(dirigente);

        Assertions.assertEquals(1, progetto.getPersonale().size());
        Assertions.assertEquals(dirigente, progetto.getPersonale().get(0));
    }

    @Test
    void testAggiungiPersonale_NullPersonale() {
        Assertions.assertThrows(PersonaleNulloException.class, () -> {
            progetto.aggiungiPersonale(null);
        });
    }

    @Test
    void testGetCostoComplessivo() throws PersonaleNulloException {
        Personale dirigente = new Dirigente("D1", "Rossi", "Mario", 2010);
        Personale tecnico1 = new Tecnico("T1", "Verdi", "Luigi", 2015, "informatica-telecomunicazioni", true);
        Personale tecnico2 = new Tecnico("T2", "Bianchi", "Giuseppe", 2018, "elettronica-automazione", false);
        Personale funzionario1 = new Funzionario("F1", "Gialli", "Anna", 2012, 8);
        Personale funzionario2 = new Funzionario("F2", "Neri", "Marco", 2010, 12);

        progetto.aggiungiPersonale(dirigente);
        progetto.aggiungiPersonale(tecnico1);
        progetto.aggiungiPersonale(tecnico2);
        progetto.aggiungiPersonale(funzionario1);
        progetto.aggiungiPersonale(funzionario2);

        double costoComplessivo = 100 + 40 + 50 + 70 + 80;
        Assertions.assertEquals(costoComplessivo, progetto.getCostoComplessivo());
    }

    @Test
    void testCalcolaCostoOrario_TecnicoInternoInformaticaTelecomunicazioni() {
        Tecnico tecnico = new Tecnico("T1", "Verdi", "Luigi", 2015, "informatica-telecomunicazioni", true);
        double costoOrario = 40 + (2023 - 2015);
        Assertions.assertEquals(costoOrario, progetto.calcolaCostoOrario(tecnico));
    }

    @Test
    void testCalcolaCostoOrario_TecnicoEsternoElettronicaAutomazione() {
        Tecnico tecnico = new Tecnico("T1", "Verdi", "Luigi", 2018, "elettronica-automazione", false);
        double costoOrario = 50;
        Assertions.assertEquals(costoOrario, progetto.calcolaCostoOrario(tecnico));
    }

    @Test
    void testCalcolaCostoOrario_FunzionarioJunior() {
        Funzionario funzionario = new Funzionario("F1", "Gialli", "Anna", 2012, 5);
        double costoOrario = 70;
        Assertions.assertEquals(costoOrario, progetto.calcolaCostoOrario(funzionario));
    }

    @Test
    void testCalcolaCostoOrario_FunzionarioSenior() {
        Funzionario funzionario = new Funzionario("F1", "Gialli", "Anna", 2010, 12);
        double costoOrario = 80;
        Assertions.assertEquals(costoOrario, progetto.calcolaCostoOrario(funzionario));
    }

    @Test
    void testCalcolaCostoOrario_Dirigente() {
        Dirigente dirigente = new Dirigente("D1", "Rossi", "Mario", 2010);
        double costoOrario = 100;
        Assertions.assertEquals(costoOrario, progetto.calcolaCostoOrario(dirigente));
    }
}
