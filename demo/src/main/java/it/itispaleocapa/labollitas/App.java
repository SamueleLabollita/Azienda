package it.itispaleocapa.labollitas;

/**
 * Hello world!
 */
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

class PersonaleNulloException extends IllegalArgumentException {
    
}

class Personale {
    public String id;
    public String cognome;
    public String nome;
    public int annoAssunzione;

    public Personale(String id, String cognome, String nome, int annoAssunzione) {
        this.id = id;
        this.cognome = cognome;
        this.nome = nome;
        this.annoAssunzione = annoAssunzione;
    }

    public String getId() {
        return id;
    }

    public String getCognome() {
        return cognome;
    }

    public String getNome() {
        return nome;
    }

    public int getAnnoAssunzione() {
        return annoAssunzione;
    }
}

class Tecnico extends Personale {
    public String areaCompetenza;
    public boolean interno;

    public Tecnico(String id, String cognome, String nome, int annoAssunzione, String areaCompetenza, boolean interno) {
        super(id, cognome, nome, annoAssunzione);
        this.areaCompetenza = areaCompetenza;
        this.interno = interno;
    }

    public String getAreaCompetenza() {
        return areaCompetenza;
    }

    public boolean isInterno() {
        return interno;
    }
}

class Funzionario extends Personale {
    public int anniEsperienza;

    public Funzionario(String id, String cognome, String nome, int annoAssunzione, int anniEsperienza) {
        super(id, cognome, nome, annoAssunzione);
        this.anniEsperienza = anniEsperienza;
    }

    public int getAnniEsperienza() {
        return anniEsperienza;
    }
}

class Dirigente extends Personale {
    public Dirigente(String id, String cognome, String nome, int annoAssunzione) {
        super(id, cognome, nome, annoAssunzione);
    }
}



class Progetto {
    public List<Personale> personale;

    public Progetto() {
        personale = new ArrayList<>();
    }

    public void aggiungiPersonale(Personale membro) throws PersonaleNulloException {
        if (membro == null) {
            throw new PersonaleNulloException();
        }
        personale.add(membro);
    }

    public List<Personale> getPersonale() {
        return new ArrayList<>(personale);
    }

    public double getCostoComplessivo() {
        double costoComplessivo = personale.stream()
                .mapToDouble(this::calcolaCostoOrario)
                .sum();

        return costoComplessivo;
    }

    public double calcolaCostoOrario(Personale membro) throws PersonaleNulloException{
        if (membro instanceof Tecnico) {
            Tecnico tecnico = (Tecnico) membro;
            double costoBase = tecnico.getAreaCompetenza().equals("informatica-telecomunicazioni") ? 40 : 50;
            double costoOrario = costoBase;
            if (tecnico.isInterno()) {
                int anniTrascorsi = 2023 - tecnico.getAnnoAssunzione();
                costoOrario += anniTrascorsi;
            }
            return costoOrario;
        } else if (membro instanceof Funzionario) {
            Funzionario funzionario = (Funzionario) membro;
            double costoOrario = funzionario.getAnniEsperienza() < 10 ? 70 : 80;
            return costoOrario;
        } else if (membro instanceof Dirigente) {
            return 100;
        } else {
            throw new PersonaleNulloException();
        }
    }
}

