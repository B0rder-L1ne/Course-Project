package com.mycompany.simpletranslator;

import java.io.*; // Import für Dateioperationen, BufferedReader und Writer
import java.util.ArrayList; // Import für dynamische Listen
import java.util.Scanner; // Import für Benutzereingaben

public class SimpleTranslatorV3 {

    // Konstanten für den Dateinamen
    // Diese Konstante gibt den Namen der Datei an, in der die Wörter gespeichert werden.
    // Sie wird verwendet, um sicherzustellen, dass der Dateiname überall im Programm einheitlich ist.
    private static final String FILE_NAME = "word_pairs.txt";

    public static void main(String[] args) {
        // Listen für Wörter initialisieren
        ArrayList<String> itaWords = new ArrayList<>(50); // Liste für italienische Wörter
        ArrayList<String> gerWords = new ArrayList<>(50); // Liste für deutsche Wörter

        // Wörter aus der Datei laden
        // Hier werden die Wörterpaare aus einer Textdatei eingelesen und in den Listen gespeichert.
        // Wenn die Datei nicht existiert, wird eine Fehlermeldung ausgegeben, und die Listen bleiben leer.
        loadWordsFromFile(itaWords, gerWords);

        // Scanner für Benutzereingaben initialisieren
        // Der Scanner dient dazu, Eingaben vom Benutzer zu lesen, z. B. Menüauswahl oder neue Wörter.
        Scanner scanner = new Scanner(System.in);
        System.out.println("Willkommen zum Italienisch-Deutsch Übersetzer!");

        // Menü anzeigen und Eingaben verarbeiten
        // Das Hauptmenü wird dem Benutzer gezeigt, und dessen Eingaben werden verarbeitet.
        showMenu(scanner, itaWords, gerWords);

        // Wörter in die Datei speichern
        // Nach Abschluss des Programms werden alle Wörterpaare in der Datei gespeichert.
        saveWordsToFile(itaWords, gerWords);

        // Scanner schließen, um Ressourcen freizugeben
        scanner.close();
    }

    // Methode zum Laden der Wörter aus einer Datei
    private static void loadWordsFromFile(ArrayList<String> itaWords, ArrayList<String> gerWords) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            // Datei Zeile für Zeile lesen
            // Jede Zeile der Datei enthält ein italienisch-deutsches Wortpaar im Format "italienisch=deutsch".
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("=", 2); // Zeile in italienisches und deutsches Wort teilen
                if (parts.length == 2) { // Nur gültige Einträge berücksichtigen
                    itaWords.add(parts[0].trim()); // Italienisches Wort hinzufügen
                    gerWords.add(parts[1].trim()); // Deutsches Wort hinzufügen
                }
            }
        } catch (IOException e) {
            // Nachricht anzeigen, falls Datei nicht gelesen werden konnte
            // Dies tritt auf, wenn die Datei nicht existiert oder nicht lesbar ist.
            System.out.println("Wörter konnten nicht geladen werden. Es wird mit den Standardwerten gestartet.");
        }
    }

    // Methode zum Speichern der Wörter in eine Datei
    private static void saveWordsToFile(ArrayList<String> itaWords, ArrayList<String> gerWords) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            // Alle Wörterpaare in die Datei schreiben
            // Die Wörter werden zeilenweise im Format "italienisch=deutsch" gespeichert.
            for (int i = 0; i < itaWords.size(); i++) {
                writer.write(itaWords.get(i) + "=" + gerWords.get(i)); // Wörter im Format "italienisch=deutsch"
                writer.newLine(); // Neue Zeile beginnen
            }
        } catch (IOException e) {
            // Nachricht anzeigen, falls Datei nicht gespeichert werden konnte
            // Dies tritt auf, wenn die Datei nicht beschreibbar ist oder ein Fehler beim Schreiben auftritt.
            System.out.println("Fehler beim Speichern der Wörter.");
        }
    }

    // Methode zur Anzeige des Menüs und Verarbeitung der Benutzereingaben
    public static void showMenu(Scanner scanner, ArrayList<String> itaWords, ArrayList<String> gerWords) {
        boolean running = true; // Variable, um das Programm am Laufen zu halten

        while (running) { // Schleife für das Menü
            // Menüoptionen anzeigen
            // Der Benutzer sieht hier die verschiedenen Aktionen, die er ausführen kann.
            System.out.println("\nHauptmenü:");
            System.out.println("1: Italienisch -> Deutsch");
            System.out.println("2: Deutsch -> Italienisch");
            System.out.println("3: Neues Wortpaar hinzufügen");
            System.out.println("4: Beenden");
            System.out.print("Bitte wählen: ");

            // Benutzerwahl einlesen
            // Die Auswahl des Benutzers wird hier eingelesen und als String verarbeitet.
            String auswahl = scanner.nextLine();

            // Aktionen basierend auf der Benutzerwahl durchführen
            // Je nach Auswahl wird eine der folgenden Aktionen ausgeführt.
            switch (auswahl) {
                case "1" -> { // Übersetzung Italienisch -> Deutsch
                    System.out.print("Bitte geben Sie ein italienisches Wort ein: ");
                    String itaEingabe = scanner.nextLine().trim(); // Benutzer-Eingabe bereinigen
                    String gefundenDeutsch = translateItaToGer(itaWords, gerWords, itaEingabe); // Übersetzung suchen
                    // Übersetzung ausgeben oder Fehler anzeigen
                    System.out.println(gefundenDeutsch != null
                            ? "Die deutsche Übersetzung von '" + itaEingabe + "' ist: " + gefundenDeutsch
                            : "Wort nicht im Wörterbuch gefunden.");
                }
                case "2" -> { // Übersetzung Deutsch -> Italienisch
                    System.out.print("Bitte geben Sie ein deutsches Wort ein: ");
                    String gerEingabe = scanner.nextLine().trim(); // Benutzer-Eingabe bereinigen
                    String gefundenItalienisch = translateGerToIta(gerWords, itaWords, gerEingabe); // Übersetzung suchen
                    // Übersetzung ausgeben oder Fehler anzeigen
                    System.out.println(gefundenItalienisch != null
                            ? "Die italienische Übersetzung von '" + gerEingabe + "' ist: " + gefundenItalienisch
                            : "Wort nicht im Wörterbuch gefunden.");
                }
                case "3" -> { // Neues Wortpaar hinzufügen
                    System.out.print("Neues italienisches Wort: ");
                    String neuesItaWort = scanner.nextLine().trim(); // Neues italienisches Wort einlesen
                    System.out.print("Deutsche Übersetzung: ");
                    String neuesGerWort = scanner.nextLine().trim(); // Neue deutsche Übersetzung einlesen
                    itaWords.add(neuesItaWort); // Italienisches Wort zur Liste hinzufügen
                    gerWords.add(neuesGerWort); // Deutsches Wort zur Liste hinzufügen
                    System.out.println("Das Wortpaar wurde hinzugefügt.");
                }
                case "4" -> { // Programm beenden
                    // Beenden der Programmschleife
                    System.out.println("Programm wird beendet. Auf Wiedersehen!");
                    running = false; // Schleife beenden
                }
                default -> System.out.println("Ungültige Auswahl. Bitte erneut versuchen."); // Fehlerhafte Eingabe behandeln
            }
        }
    }

    // Methode zur Übersetzung von Italienisch nach Deutsch
    public static String translateItaToGer(ArrayList<String> itaWords, ArrayList<String> gerWords, String word) {
        // Durch die italienische Wortliste iterieren
        // Hier wird nach dem eingegebenen Wort in der Liste gesucht.
        for (int i = 0; i < itaWords.size(); i++) {
            if (itaWords.get(i).equalsIgnoreCase(word)) { // Wörter vergleichen, Groß-/Kleinschreibung ignorieren
                return gerWords.get(i); // Entsprechendes deutsches Wort zurückgeben
            }
        }
        return null; // Null zurückgeben, wenn kein Treffer gefunden wurde
    }

    // Methode zur Übersetzung von Deutsch nach Italienisch
    public static String translateGerToIta(ArrayList<String> gerWords, ArrayList<String> itaWords, String word) {
        // Durch die deutsche Wortliste iterieren
        // Hier wird nach dem eingegebenen Wort in der Liste gesucht.
        for (int i = 0; i < gerWords.size(); i++) {
            if (gerWords.get(i).equalsIgnoreCase(word)) { // Wörter vergleichen, Groß-/Kleinschreibung ignorieren
                return itaWords.get(i); // Entsprechendes italienisches Wort zurückgeben
            }
        }
        return null; // Null zurückgeben, wenn kein Treffer gefunden wurde
    }
}
