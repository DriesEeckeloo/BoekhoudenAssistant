package be.kuleuven.boekhoudenassistant.domain;
import java.time.LocalDate;
import java.time.LocalTime;

public class Bkpf {

    // Bedrijfsnummer
    private String BUKRS; // CHAR 4

    // Documentnummer boekhoudingsdocument
    private String BELNR; // CHAR 10

    // Boekjaar
    private String GJAHR; // CHAR 4

    // Documentsoort
    private String BLART; // CHAR 2

    // Documentdatum in document
    private LocalDate BLDAT; // DATUM 8

    // Boekingsdatum in document
    private LocalDate BUDAT; // DATUM 8

    // Boekmaand
    private String MONAT; // CHAR 2

    // Dag waarop boekhoudingsdocument is ingevoerd
    private LocalDate CPUDT; // DATUM 8

    // Tijd waarop gegevens zijn ingevoerd
    private LocalTime CPUTM; // TIJD 6

    // Getters

    public String getBUKRS() {
        return BUKRS;
    }

    public String getBELNR() {
        return BELNR;
    }

    public String getGJAHR() {
        return GJAHR;
    }

    public String getBLART() {
        return BLART;
    }

    public LocalDate getBLDAT() {
        return BLDAT;
    }

    public LocalDate getBUDAT() {
        return BUDAT;
    }

    public String getMONAT() {
        return MONAT;
    }

    public LocalDate getCPUDT() {
        return CPUDT;
    }

    public LocalTime getCPUTM() {
        return CPUTM;
    }
}
