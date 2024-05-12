package be.kuleuven.boekhoudenassistent.domain;
import java.time.LocalDate;
import java.time.LocalTime;

//Documentkop voor financiële boekhouding (BKPF)
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

    public Bkpf(String BUKRS, String BELNR, String GJAHR, String BLART, LocalDate BLDAT, LocalDate BUDAT, String MONAT, LocalDate CPUDT, LocalTime CPUTM) {
        this.BUKRS = BUKRS;
        this.BELNR = BELNR;
        this.GJAHR = GJAHR;
        this.BLART = BLART;
        this.BLDAT = BLDAT;
        this.BUDAT = BUDAT;
        this.MONAT = MONAT;
        this.CPUDT = CPUDT;
        this.CPUTM = CPUTM;
    }

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

    // Setters

    public void setBUKRS(String BUKRS) {
        this.BUKRS = BUKRS;
    }

    public void setBELNR(String BELNR) {
        this.BELNR = BELNR;
    }

    public void setGJAHR(String GJAHR) {
        this.GJAHR = GJAHR;
    }

    public void setBLART(String BLART) {
        this.BLART = BLART;
    }

    public void setBLDAT(LocalDate BLDAT) {
        this.BLDAT = BLDAT;
    }

    public void setBUDAT(LocalDate BUDAT) {
        this.BUDAT = BUDAT;
    }

    public void setMONAT(String MONAT) {
        this.MONAT = MONAT;
    }

    public void setCPUDT(LocalDate CPUDT) {
        this.CPUDT = CPUDT;
    }

    public void setCPUTM(LocalTime CPUTM) {
        this.CPUTM = CPUTM;
    }

    @Override
    public String toString() {
        return "{" +
                "BUKRS='" + BUKRS + '\'' +
                ", BELNR='" + BELNR + '\'' +
                ", GJAHR='" + GJAHR + '\'' +
                ", BLART='" + BLART + '\'' +
                ", BLDAT=" + BLDAT +
                ", BUDAT=" + BUDAT +
                ", MONAT='" + MONAT + '\'' +
                ", CPUDT=" + CPUDT +
                ", CPUTM=" + CPUTM +
                '}';
    }
}
