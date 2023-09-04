package org.biglietto.oop;

import java.math.BigDecimal;

public class Tratta {
    //variables
    private String partenza;
    private String arrivo;
    private int km;

    //constructor
    public Tratta(int id, String partenza, String arrivo, int km) {
        this.partenza = partenza;
        this.arrivo = arrivo;
        this.km = km;
    }


    //get and set
        //getter
        public String getPartenza() {
            return partenza;
        }
        public String getArrivo() {
            return arrivo;
        }
        public int getKm() {
            return km;
        }

    //tostring
    @Override
    public String toString() {
        return "Partenza='" + partenza +
                ", arrivo='" + arrivo +
                ", km=" + km;
    }
}
