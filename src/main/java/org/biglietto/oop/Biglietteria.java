package org.biglietto.oop;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Biglietteria {
    public static void main( String[] args ) {
        boolean vuoiContinuare = true;
        Scanner scanner = new Scanner(System.in);
        while (vuoiContinuare) {
            try {
                System.out.print("Inserisci il numero di km: ");
                int km = scanner.nextInt();
                System.out.print("Inserisci l'età del passeggero: ");
                int eta = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Vuoi che la data sia Flessibile? (\"SI\"/\"NO\")");
                String rispostaDataFlessibile = scanner.nextLine();
                boolean dataFlessibile = rispostaDataFlessibile.equalsIgnoreCase("Si");
                // Creazione del biglietto
                LocalDate dataOdierna = LocalDate.now();
                Biglietto biglietto = new Biglietto(km, eta, dataOdierna, dataFlessibile);
                // Calcola e stampa il prezzo del biglietto e la data di scadenza
                BigDecimal prezzo = biglietto.calcolaPrezzo();
                System.out.println("Il prezzo del biglietto è: " + prezzo + "€");
                System.out.println("Data di scadenza: " + biglietto.getDataDiScadenza());
                vuoiContinuare = false;

            } catch (InputMismatchException e) {
                System.out.println("Errore: Inserisci un valore valido per km e età.");
                scanner.nextLine();
                System.out.println("Inserisci \"SI\" se vuoi riprovare. ");
                String risposta = scanner.nextLine();
                if(!risposta.equalsIgnoreCase("Si")) {
                    vuoiContinuare = false;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Errore: " + e.getMessage());
                vuoiContinuare = false;
            }
        }
    }
}
