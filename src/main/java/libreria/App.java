package libreria;

import org.bson.Document;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// Questa è la classe principale del programma, da qui parte tutto!
public class App {
    public static void main(String[] args) {
        // Stampo un titolo figo per far vedere che il programma è partito
        System.out.println("=== ANALISI LIBRERIE ONLINE ===\n");
        
        // Ok, qui mi collego a MongoDB che gira sulla mia macchina
        // Ho usato localhost perché il database è sul mio pc, sulla porta 27017 (quella di default)
        String connectionString = "mongodb://localhost:27017";
        String databaseName = "Librerie"; // Questo è il nome del mio database
        
        // Creo il manager che mi gestisce tutta la roba di MongoDB, così non devo scrivere sempre lo stesso codice
        MongoDBManager dbManager = new MongoDBManager(connectionString, databaseName);
        
        try {
            // Punto 3: Trasformare le collection in liste di oggetti Java
            // Qui prendo i dati dal database e li metto in delle liste Java normali
            // È tipo una conversione da "linguaggio database" a "linguaggio Java" :)
            System.out.println("Caricamento dati dal database...");
            List<Libreria> librerie = dbManager.getLibrerie();
            List<Libro> libri = dbManager.getLibri();
            
            // Stampo quanti ne ho caricati, così so se è andato tutto bene
            System.out.println("Librerie caricate: " + librerie.size());
            System.out.println("Libri caricati: " + libri.size());
            System.out.println();
            
            // Creo l'oggetto che fa tutte le analisi fichissime sui dati
            AnalisiLibrerie analisi = new AnalisiLibrerie(librerie, libri);
            
            // Punto 4: Libreria con più libri
            // Qui cerco quale libreria ha più roba in catalogo
            System.out.println("--- PUNTO 4 ---");
            Libreria libreriaMax = analisi.libreriaConPiuLibri();
            System.out.println("Libreria con più libri: " + libreriaMax.getNomeLibreria() + 
                             " (" + libreriaMax.getLibri().size() + " libri)");
            System.out.println();
            
            // Punto 5: Libro con prezzo più alto
            // Voglio sapere quale libro costa un botto! Curiosità pura
            System.out.println("--- PUNTO 5 ---");
            Libro libroMax = analisi.libroConPrezzoMassimo();
            System.out.println("Libro con prezzo più alto: " + libroMax.getTitoloLibro() + 
                             " - €" + libroMax.getPrezzoLibro());
            System.out.println();
            
            // Punto 6: Libri più venduti
            // Questi sono i libri che si trovano in un sacco di librerie, tipo i bestseller
            System.out.println("--- PUNTO 6 ---");
            List<Libro> libriPiuVenduti = analisi.libriPiuVenduti();
            System.out.println("Libro/i più venduti:");
            for (Libro libro : libriPiuVenduti) {
                System.out.println("  - " + libro.getTitoloLibro() + " (ISBN: " + libro.getISBN() + ")");
            }
            System.out.println();
            
            // Punto 7: Categoria più frequente
            System.out.println("--- PUNTO 7 ---");
            String categoriaFrequente = analisi.categoriaPiuFrequente();
            System.out.println("Categoria più frequente: " + categoriaFrequente);
            System.out.println();
            
            // Punto 8: Anno con più pubblicazioni
            // In che anno hanno stampato più libri in assoluto?
            System.out.println("--- PUNTO 8 ---");
            int annoMax = analisi.annoConPiuPubblicazioni();
            System.out.println("Anno con più pubblicazioni: " + annoMax);
            System.out.println();
            
            // Punto 9: Libro meno venduto per libreria
            // Per ogni libreria trovo quello che se la passa male, poverino
            System.out.println("--- PUNTO 9 ---");
            Map<String, Libro> libriMenoVenduti = analisi.libroMenoVendutoPerLibreria();
            System.out.println("Libro meno venduto per libreria:");
            for (Map.Entry<String, Libro> entry : libriMenoVenduti.entrySet()) {
                System.out.println("  - " + entry.getKey() + ": " + entry.getValue().getTitoloLibro());
            }
            System.out.println();
            
            // Punto 10: Salvataggio risultati su MongoDB
            // Ok, ora prendo tutti i risultati e li salvo nel database
            // Ho usato Document perché è tipo un oggetto JSON che MongoDB capisce
            System.out.println("--- PUNTO 10 ---");
            Document risultati = new Document();
            
            // Libreria con più libri - creo un "sotto-documento" con le info
            risultati.append("libreriaConPiuLibri", new Document()
                .append("nome", libreriaMax.getNomeLibreria())
                .append("numeroLibri", libreriaMax.getLibri().size()));
            
            // Libro con prezzo massimo - stesso discorso
            risultati.append("libroConPrezzoMassimo", new Document()
                .append("titolo", libroMax.getTitoloLibro())
                .append("prezzo", libroMax.getPrezzoLibro())
                .append("ISBN", libroMax.getISBN()));
            
            // Libri più venduti - qua serve una lista di documenti
            List<Document> docLibriVenduti = new ArrayList<>();
            for (Libro libro : libriPiuVenduti) {
                docLibriVenduti.add(new Document()
                    .append("titolo", libro.getTitoloLibro())
                    .append("ISBN", libro.getISBN()));
            }
            risultati.append("libriPiuVenduti", docLibriVenduti);
            
            // Categoria più frequente - questa è facile, è solo una stringa
            risultati.append("categoriaPiuFrequente", categoriaFrequente);
            
            // Anno con più pubblicazioni - anche questo è semplice, un numero
            risultati.append("annoConPiuPubblicazioni", annoMax);
            
            // Libri meno venduti per libreria - altra lista di documenti
            List<Document> docMenoVenduti = new ArrayList<>();
            for (Map.Entry<String, Libro> entry : libriMenoVenduti.entrySet()) {
                docMenoVenduti.add(new Document()
                    .append("libreria", entry.getKey())
                    .append("libro", entry.getValue().getTitoloLibro())
                    .append("ISBN", entry.getValue().getISBN()));
            }
            risultati.append("libriMenoVendutiPerLibreria", docMenoVenduti);
            
            // E ora... SALVO TUTTO! Questo inserisce tutto quanto nel database
            dbManager.salvaRisultati(risultati);
            System.out.println("Risultati salvati nella collection 'ResultsTestBigData'!");
            
        } catch (Exception e) {
            // Se qualcosa va storto, almeno vedo l'errore invece di crashare random
            System.err.println("Errore durante l'analisi: " + e.getMessage());
            e.printStackTrace(); // Questo mi stampa tutto l'errore, utile per capire dove ho sbagliato
        } finally {
            // Chiusura della connessione
            // Questo blocco finally viene eseguito SEMPRE, anche se c'è un errore
            dbManager.chiudiConnessione();
            System.out.println("\nConnessione al database chiusa.");
        }
    }
}

