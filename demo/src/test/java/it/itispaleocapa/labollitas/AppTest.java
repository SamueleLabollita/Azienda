package it.itispaleocapa.labollitas;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for simple App.
 */
class ProgettoTest {

    private Progetto progetto;

    @BeforeEach
    void setUp() {
        progetto = new Progetto();
    }

    @Test
    void aggiungiPersonaleSingoloMembro() {
        Personale membro = new Tecnico("T001", "Labollita", "Samuele", 2018, "informatica-telecomunicazioni", true);
        progetto.aggiungiPersonale(membro);
        List<Personale> personale = progetto.getPersonale();
        assertEquals(1, personale.size());
        assertTrue(personale.contains(membro));
    }

    @Test
    void aggiungiPersonaleNullMembro() {
        assertThrows(IllegalArgumentException.class, () -> progetto.aggiungiPersonale(null));
    }

    @Test
    void getCostoComplessivoNoPersonale() {
        double costoComplessivo = progetto.getCostoComplessivo();
        assertEquals(0, costoComplessivo);
    }

    @Test
    void getCostoComplessivoSingleMember() {
        Personale membro = new Tecnico("T001", "Labollita", "Samuele", 2018, "informatica-telecomunicazioni", true);
        progetto.aggiungiPersonale(membro);
        double costoComplessivo = progetto.getCostoComplessivo();
        double expectedCosto = 40 + (2023 - 2018);
        assertEquals(expectedCosto, costoComplessivo);
    }

    @Test
    void getCostoComplessivoMultipleMembers() {
        Personale tecnico1 = new Tecnico("T001", "Labollita", "Samuele", 2018, "informatica-telecomunicazioni", true);
        Personale tecnico2 = new Tecnico("T002", "Verdi", "Luca", 2019, "elettronica-automazione", false);
        Personale funzionario1 = new Funzionario("F001", "Bianchi", "Anna", 2015, 8);
        progetto.aggiungiPersonale(tecnico1);
        progetto.aggiungiPersonale(tecnico2);
        progetto.aggiungiPersonale(funzionario1);
        double costoComplessivo = progetto.getCostoComplessivo();
        double expectedCosto = 40 + (2023 - 2018) + 50 + 70;
        assertEquals(expectedCosto, costoComplessivo);
    }

    @Test
    void calcolaCostoOrarioTecnicoInformaticaInterno() {
        Tecnico tecnico = new Tecnico("T001", "Labollita", "Samuele", 2018, "informatica-telecomunicazioni", true);
        double costoOrario = progetto.calcolaCostoOrario(tecnico);
        double expectedCosto = 40 + (2023 - 2018);
        assertEquals(expectedCosto, costoOrario);
    }

    @Test
    void calcolaCostoOrarioTecnicoElettronicaEsterno() {
        Tecnico tecnico = new Tecnico("T001", "Labollita", "Samuele", 2019, "elettronica-automazione", false);
        double costoOrario = progetto.calcolaCostoOrario(tecnico);
        double expectedCosto = 50;
        assertEquals(expectedCosto, costoOrario);
    }

    @Test
    void calcolaCostoOrarioFunzionarioJunior() {
        Funzionario funzionario = new Funzionario("F001", "Bianchi", "Anna", 2015, 7);
        double costoOrario = progetto.calcolaCostoOrario(funzionario);
        double expectedCosto = 70;
        assertEquals(expectedCosto, costoOrario);
    }

    @Test
    void calcolaCostoOrarioFunzionarioSenior() {
        Funzionario funzionario = new Funzionario("F001", "Bianchi", "Anna", 2008, 12);
        double costoOrario = progetto.calcolaCostoOrario(funzionario);
        double expectedCosto = 80;
        assertEquals(expectedCosto, costoOrario);
    }

    @Test
    void calcolaCostoOrarioDirigente() {
        Dirigente dirigente = new Dirigente("D001", "Gialli", "Giuseppe", 2005);
        double costoOrario = progetto.calcolaCostoOrario(dirigente);
        double expectedCosto = 100;
        assertEquals(expectedCosto, costoOrario);
    }
}
