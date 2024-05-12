package be.kuleuven.boekhoudenassistent.domain;

import java.time.LocalDate;

//Documentsegment boekhouding (BSEG)
public class Bseg {

    // Bedrijfsnummer
    private String BUKRS; // CHAR 4

    // Documentnummer boekhoudingsdocument
    private String BELNR; // CHAR 10

    // Boekjaar
    private String GJAHR; // CHAR 4

    // Nummer van boekingsregel in boekhoudingsdoc
    private String BUZEI; // CHAR 3

    // Identificatie van boekingsregel
    private String BUZID; // CHAR 1

    // Datum van vereffening
    private LocalDate AUGDT; // DATUM 8

    // Invoerdatum van de vereffening
    private LocalDate AUGCP; // DATUM 8

    // Documentnummer van vereffeningsdocument
    private String AUGBL; // CHAR 10

    // Boekingssleutel
    private String BSCHL; // CHAR 2

    // getters

    public String getBUKRS() {
        return BUKRS;
    }

    public String getBELNR() {
        return BELNR;
    }

    public String getGJAHR() {
        return GJAHR;
    }

    public String getBUZEI() {
        return BUZEI;
    }

    public String getBUZID() {
        return BUZID;
    }

    public LocalDate getAUGDT() {
        return AUGDT;
    }

    public LocalDate getAUGCP() {
        return AUGCP;
    }

    public String getAUGBL() {
        return AUGBL;
    }

    public String getBSCHL() {
        return BSCHL;
    }
}
