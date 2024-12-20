package com.mycompany.simpletranslator;

import java.util.ArrayList;
import java.util.Scanner;

public class SimpleTranslatorV2 {

    public static void main(String[] args) {
        // Dynamische Listen für Wörter, die während der Laufzeit gefüllt werden
        ArrayList<String> itaWords = new ArrayList<>(50); // Liste für italienische Wörter
        ArrayList<String> gerWords = new ArrayList<>(50); // Liste für deutsche Wörter

        // Initiale Wörter hinzufügen
        String[] initialItaWords = {
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

        String[] initialGerWords = {
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

        // Hinzufügen der initialen Wörter in die Listen
        for (int i = 0; i < initialItaWords.length; i++) {
            itaWords.add(initialItaWords[i]); // Italienisches Wort zur Liste hinzufügen
            gerWords.add(initialGerWords[i]); // Deutsches Wort zur Liste hinzufügen
        }

        Scanner scanner = new Scanner(System.in); // Scanner für Benutzereingaben initialisieren

        System.out.println("Willkommen zum Italienisch-Deutsch Übersetzer!");
        showMenu(scanner, itaWords, gerWords); // Menülogik in Methode ausgelagert

        scanner.close(); // Scanner schließen, um Ressourcen freizugeben
    }

    // Methode zur Anzeige des Menüs und Verarbeitung der Eingaben
    public static void showMenu(Scanner scanner, ArrayList<String> itaWords, ArrayList<String> gerWords) {
        boolean running = true; // Variable für den Zustand des Programms

        while (running) { // Hauptprogrammschleife für das Menü
            // Menü anzeigen
            System.out.println("\nHauptmenü:");
            System.out.println("1: Italienisch -> Deutsch");
            System.out.println("2: Deutsch -> Italienisch");
            System.out.println("3: Neues Wortpaar hinzufügen");
            System.out.println("4: Beenden");
            System.out.print("Bitte wählen: ");

            String auswahl = scanner.nextLine(); // Benutzerwahl einlesen

            switch (auswahl) {
                case "1" -> { // Italienisch -> Deutsch Übersetzung
                    System.out.print("Bitte geben Sie ein italienisches Wort ein: ");
                    String itaEingabe = scanner.nextLine().trim(); // Benutzer-Eingabe bereinigen

                    String gefundenDeutsch = translateItaToGer(itaWords, gerWords, itaEingabe); // Übersetzung abrufen

                    if (gefundenDeutsch != null) {
                        System.out.println("Die deutsche Übersetzung von '" + itaEingabe + "' ist: " + gefundenDeutsch);
                    } else {
                        System.out.println("Wort nicht im Wörterbuch gefunden.");
                    }
                }
                case "2" -> { // Deutsch -> Italienisch Übersetzung
                    System.out.print("Bitte geben Sie ein deutsches Wort ein: ");
                    String gerEingabe = scanner.nextLine().trim(); // Benutzer-Eingabe bereinigen

                    String gefundenItalienisch = translateGerToIta(gerWords, itaWords, gerEingabe); // Übersetzung abrufen

                    if (gefundenItalienisch != null) {
                        System.out.println("Die italienische Übersetzung von '" + gerEingabe + "' ist: " + gefundenItalienisch);
                    } else {
                        System.out.println("Wort nicht im Wörterbuch gefunden.");
                    }
                }
                case "3" -> { // Neues Wortpaar hinzufügen
                    System.out.print("Neues italienisches Wort: ");
                    String neuesItaWort = scanner.nextLine().trim(); // Neues italienisches Wort einlesen

                    System.out.print("Deutsche Übersetzung: ");
                    String neuesGerWort = scanner.nextLine().trim(); // Neue deutsche Übersetzung einlesen

                    itaWords.add(neuesItaWort); // Neues italienisches Wort zur Liste hinzufügen
                    gerWords.add(neuesGerWort); // Neue deutsche Übersetzung zur Liste hinzufügen

                    System.out.println("Das Wortpaar wurde hinzugefügt.");
                }
                case "4" -> { // Programm beenden
                    System.out.println("Programm wird beendet. Auf Wiedersehen!");
                    running = false; // Programmlauf beenden
                }
                default -> System.out.println("Ungültige Auswahl. Bitte erneut versuchen."); // Fehlerbehandlung bei falscher Eingabe
            }
        }
    }

    // Methode zum Übersetzen von Italienisch nach Deutsch
    public static String translateItaToGer(ArrayList<String> itaWords, ArrayList<String> gerWords, String word) {
        for (int i = 0; i < itaWords.size(); i++) { // Iteration durch die Liste der italienischen Wörter
            if (itaWords.get(i).equalsIgnoreCase(word)) { // Vergleich der Wörter, Groß-/Kleinschreibung ignorieren
                return gerWords.get(i); // Rückgabe des entsprechenden deutschen Wortes
            }
        }
        return null; // Rückgabe null, falls keine Übersetzung gefunden wurde
    }

    // Methode zum Übersetzen von Deutsch nach Italienisch
    public static String translateGerToIta(ArrayList<String> gerWords, ArrayList<String> itaWords, String word) {
        for (int i = 0; i < gerWords.size(); i++) { // Iteration durch die Liste der deutschen Wörter
            if (gerWords.get(i).equalsIgnoreCase(word)) { // Vergleich der Wörter, Groß-/Kleinschreibung ignorieren
                return itaWords.get(i); // Rückgabe des entsprechenden italienischen Wortes
            }
        }
        return null; // Rückgabe null, falls keine Übersetzung gefunden wurde
    }
}
