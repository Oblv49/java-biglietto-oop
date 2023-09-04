package org.biglietto.oop;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BiglietteriaWhitDB {
    public static void main(String[] args) {
        boolean vuoiContinuare = true;
        Scanner scanner = new Scanner(System.in);

        while (vuoiContinuare) {
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_treni", "root", "Nuova_password1!!")) {
                String query = "SELECT * FROM tratte";
                PreparedStatement prepareStatement = connection.prepareStatement(query);

                List<Tratta> tratte = new ArrayList<>();

                try (ResultSet rs = prepareStatement.executeQuery()) {
                    while (rs.next()) {
                        int id = rs.getInt("id");
                        String partenza = rs.getString("partenza");
                        String arrivo = rs.getString("arrivo");
                        int km = rs.getInt("km");

                        tratte.add(new Tratta(id, partenza, arrivo, km));
                    }
                } catch (SQLException e) {
                    System.out.println("Errore nell'iterazione del ResultSet.");
                }

                System.out.println("Tratte disponibili: ");

                for (int i = 0; i < tratte.size(); i++) {
                    System.out.println(i + " - " + tratte.get(i));
                }

                System.out.print("Seleziona tratta: ");
                int selection = Integer.parseInt(scanner.nextLine());

                System.out.print("Inserisci Età: ");
                int age = Integer.parseInt(scanner.nextLine());

                System.out.print("Inserisci Flessibile (si/no): ");
                boolean flexible = scanner.nextLine().equalsIgnoreCase("si");

                try {
                    Tratta trattaScelta = tratte.get(selection);
                    Biglietto biglietto = new Biglietto(trattaScelta.getKm(), age, LocalDate.now(), flexible);

                    System.out.println("Prezzo del biglietto: " + biglietto.calcolaPrezzo() +"€");
                    System.out.println("Data di scadenza del biglietto: " + biglietto.getDataDiScadenza());
                    vuoiContinuare = false;
                } catch (RuntimeException e) {
                    System.out.println("Hai inserito dati non validi, riprova.");
                }
            } catch (SQLException e) {
                System.out.println("Connessione al database non riuscita.");
            }
        }
    }
}
