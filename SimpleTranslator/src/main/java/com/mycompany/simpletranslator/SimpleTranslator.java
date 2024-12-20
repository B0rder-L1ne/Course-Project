package com.mycompany.simpletranslator;

import java.util.Scanner; // Scanner wird für die Benutzereingabe benötigt

public class SimpleTranslator {

    // Methode zum Übersetzen von Italienisch nach Deutsch
    public static String translateItaToGer(String[] itaWords, String[] gerWords, String word) {
        for (int i = 0; i < itaWords.length; i++) {
            // Vergleich unabhängig von der Groß-/Kleinschreibung
            if (itaWords[i].equalsIgnoreCase(word)) {
                return gerWords[i];
            }
        }
        return null; // Falls nicht gefunden
    }

    // Methode zum Übersetzen von Deutsch nach Italienisch
    public static String translateGerToIta(String[] gerWords, String[] itaWords, String word) {
        for (int i = 0; i < gerWords.length; i++) {
            // Vergleich unabhängig von der Groß-/Kleinschreibung
            if (gerWords[i].equalsIgnoreCase(word)) {
                return itaWords[i];
            }
        }
        return null; // Falls nicht gefunden
    }

    public static void main(String[] args) {
        // 50 italienische Wörter
        String[] itaWords = {
            "acqua", "aiuto", "amico", "amore", "arrivederci",
            "bambino", "bello", "bene", "buongiorno", "caffè",
            "cane", "capire", "casa", "ciao", "città",
            "come", "dentro", "dire", "dove", "famiglia",
            "fame", "finestra", "forchetta", "grazie", "grande",
            "gusto", "ieri", "il", "lui", "luce",
            "mare", "martello", "mercato", "oggi", "ora",
            "pane", "perché", "persona", "piatto", "porta",
            "pronto", "ragazzo", "scuola", "sempre", "sereno",
            "settimana", "soldi", "strada", "tempo", "vino"
        };

        // 50 deutsche Wörter
        String[] gerWords = {
            "Wasser", "Hilfe", "Freund", "Liebe", "Auf Wiedersehen",
            "Kind", "Schön", "Gut", "Guten Tag", "Kaffee",
            "Hund", "Verstehen", "Haus", "Hallo", "Stadt",
            "Wie", "Drinnen", "Sagen", "Wo", "Familie",
            "Hunger", "Fenster", "Gabel", "Danke", "Groß",
            "Geschmack", "Gestern", "Der", "Er", "Licht",
            "Meer", "Hammer", "Markt", "Heute", "Jetzt",
            "Brot", "Warum", "Person", "Teller", "Tür",
            "Bereit", "Junge", "Schule", "Immer", "Heiter",
            "Woche", "Geld", "Straße", "Zeit", "Wein"
        };

        // Scanner für die Benutzereingabe initialisieren
        Scanner scanner = new Scanner(System.in);

        // Variable, um das Programm in einer Schleife laufen zu lassen
        boolean running = true;

        // Begrüßung des Benutzers
        System.out.println("Willkommen zum Italienisch-Deutsch Übersetzer!");

        // Hauptschleife des Programms
        while (running) {
            // Hauptmenü anzeigen
            System.out.println("\nHauptmenü:");
            System.out.println("1: Italienisch -> Deutsch");
            System.out.println("2: Deutsch -> Italienisch");
            System.out.println("3: Beenden");
            System.out.print("Bitte wählen: ");

            // Benutzerauswahl lesen
            String auswahl = scanner.nextLine();

            // Auswahl basierend auf der Eingabe des Benutzers
            switch (auswahl) {
                case "1" -> {
                    // Benutzer nach einem italienischen Wort fragen
                    System.out.print("Bitte geben Sie ein italienisches Wort ein: ");
                    String itaEingabe = scanner.nextLine().trim(); // Eingabe ohne toLowerCase

                    // Übersetzung suchen
                    String gefundenDeutsch = translateItaToGer(itaWords, gerWords, itaEingabe);

                    // Übersetzung anzeigen oder Fehler melden
                    if (gefundenDeutsch != null) {
                        System.out.println("Die deutsche Übersetzung von '" + itaEingabe + "' ist: " + gefundenDeutsch);
                    } else {
                        System.out.println("Wort nicht im Wörterbuch gefunden.");
                    }
                }
                case "2" -> {
                    // Benutzer nach einem deutschen Wort fragen
                    System.out.print("Bitte geben Sie ein deutsches Wort ein: ");
                    String gerEingabe = scanner.nextLine().trim(); // Eingabe ohne toLowerCase

                    // Übersetzung suchen
                    String gefundenItalienisch = translateGerToIta(gerWords, itaWords, gerEingabe);

                    // Übersetzung anzeigen oder Fehler melden
                    if (gefundenItalienisch != null) {
                        System.out.println("Die italienische Übersetzung von '" + gerEingabe + "' ist: " + gefundenItalienisch);
                    } else {
                        System.out.println("Wort nicht im Wörterbuch gefunden.");
                    }
                }
                case "3" -> {
                    // Programm beenden
                    System.out.println("Programm wird beendet. Auf Wiedersehen!");
                    running = false; // Schleife beenden
                }
                default -> {
                    // Fehlermeldung bei ungültiger Auswahl
                    System.out.println("Ungültige Auswahl. Bitte erneut versuchen.");
                }
            }
        }

        // Scanner schließen
        scanner.close();
    }
}
