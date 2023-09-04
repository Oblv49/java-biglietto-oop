package org.biglietto.oop;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class Biglietto {
    //VARIABLES
    private int km;
    private int eta;

    private LocalDate date;
    private boolean dataFlessibile;

    private LocalDate dataDiScadenza;

    //CONSTANT
    private static final BigDecimal COSTO_AL_KM = new BigDecimal("0.21");
    private static final BigDecimal SCONTO_OVER65 = new BigDecimal("0.40");
    private static final BigDecimal SCONTO_MINORENNI = new BigDecimal("0.20");
    private static final int DURATA_NORMALE = 30;
    private static final int DURATA_FLESSIBILE = 90;

    //CONSTRUCTOR
    public Biglietto(int km, int eta, LocalDate date, boolean dataFlessibile) {
        if (!isValidKm(km) || !isValidEta(eta)) {
            throw new IllegalArgumentException("I dati inseriti non sono validi.");
        }
        this.km = km;
        this.eta = eta;
        this.date = date;
        this.dataFlessibile = dataFlessibile;
        this.dataDiScadenza = calcolaDatadiScadenza();
    }

    //get & set
    //get
    public int getKm() {
        return km;
    }
    public int getEta() {
        return eta;
    }
    public LocalDate getDataDiScadenza() {
        return dataDiScadenza;
    }

    //set
    public void setKm(int km) {
        this.km = km;
    }
    public void setEta(int eta) {
        this.eta = eta;
    }

    //METHOD
    public BigDecimal calcolaPrezzo() {
        BigDecimal prezzo = COSTO_AL_KM.multiply(BigDecimal.valueOf(km));
        BigDecimal sconto = calcolaSconto();
        BigDecimal scontoApplicato = prezzo.multiply(sconto);

        BigDecimal prezzoFinale = prezzo.subtract(scontoApplicato);

        if(dataFlessibile) {
            BigDecimal costoDataFlessibile = BigDecimal.valueOf(0.10);
            BigDecimal costoExtra = prezzoFinale.multiply(costoDataFlessibile);
            prezzoFinale = prezzoFinale.add(costoExtra);
        }

        return prezzoFinale.setScale(2, RoundingMode.HALF_UP);
    }

    public LocalDate calcolaDatadiScadenza() {
        if (dataFlessibile) {
            return date.plusDays(DURATA_FLESSIBILE);
        }
        return date.plusDays(DURATA_NORMALE);
    }

    //METHOD PRIVATE
    private boolean isValidKm(int km) {
        return km > 0;
    }
    private boolean isValidEta(int eta) {
        return eta > 0;
    }
    private BigDecimal calcolaSconto() {
        if (eta >= 65) {
            return SCONTO_OVER65;
        } else if (eta < 18) {
            return SCONTO_MINORENNI;
        } else {
            return BigDecimal.ZERO;
        }

    }
}
