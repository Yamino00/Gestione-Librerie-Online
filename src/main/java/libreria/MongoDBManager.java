package libreria;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

// Questa classe gestisce tutta la roba di MongoDB
// Così non devo scrivere sempre lo stesso codice per connettermi e prendere i dati
public class MongoDBManager {
    private MongoClient mongoClient; // Il client per connettermi a MongoDB
    private MongoDatabase database; // Il database su cui lavoro
    
    // Costruttore - connessione a MongoDB
    // Quando creo l'oggetto, mi connetto subito al database
    public MongoDBManager(String connectionString, String databaseName) {
        this.mongoClient = MongoClients.create(connectionString);
        this.database = mongoClient.getDatabase(databaseName);
    }
    
    // Metodo per ottenere tutte le librerie come lista di oggetti
    // Prende i dati dal database e li trasforma in oggetti Java che posso usare facilmente
    public List<Libreria> getLibrerie() {
        List<Libreria> librerie = new ArrayList<>();
        MongoCollection<Document> collection = database.getCollection("Libreria");
        
        // Giro tutti i documenti della collection
        for (Document doc : collection.find()) {
            Libreria libreria = new Libreria();
            libreria.setNomeLibreria(doc.getString("nomeLibreria"));
            libreria.setSitoLibreria(doc.getString("sitoLibreria"));
            libreria.setTitolareLibreria(doc.getString("titolareLibreria"));
            
            // Conversione della lista di libri da numeri a stringhe
            // A volte MongoDB salva gli ISBN come numeri, quindi li converto in stringhe
            List<String> libriISBN = new ArrayList<>();
            List<?> libriDoc = doc.getList("libri", Object.class);
            if (libriDoc != null) {
                for (Object isbn : libriDoc) {
                    libriISBN.add(String.valueOf(isbn)); // String.valueOf converte qualsiasi cosa in stringa
                }
            }
            libreria.setLibri(libriISBN);
            
            librerie.add(libreria);
        }
        
        return librerie;
    }
    
    // Metodo per ottenere tutti i libri come lista di oggetti
    // Anche qui, trasformo i documenti MongoDB in oggetti Java
    public List<Libro> getLibri() {
        List<Libro> libri = new ArrayList<>();
        MongoCollection<Document> collection = database.getCollection("Libro");
        
        for (Document doc : collection.find()) {
            Libro libro = new Libro();
            libro.setISBN(doc.getString("ISBN"));
            libro.setAutoreLibro(doc.getString("autoreLibro"));
            libro.setEditoreLibro(doc.getString("editoreLibro"));
            
            // Gestione del prezzo (può essere stringa o numero)
            // Questo è un casino perché a volte MongoDB salva i numeri come stringhe
            // Quindi controllo il tipo e lo converto di conseguenza
            Object prezzoObj = doc.get("prezzoLibro");
            if (prezzoObj instanceof String) {
                libro.setPrezzoLibro(Double.parseDouble((String) prezzoObj));
            } else if (prezzoObj instanceof Number) {
                libro.setPrezzoLibro(((Number) prezzoObj).doubleValue());
            }
            
            libro.setTitoloLibro(doc.getString("titoloLibro"));
            libro.setCategoriaLibro(doc.getString("categoriaLibro"));
            
            // Gestione dell'anno (può essere stringa o numero)
            // Stesso discorso del prezzo, devo gestire entrambi i casi
            Object annoObj = doc.get("annoPubblicazione");
            if (annoObj instanceof String) {
                libro.setAnnoPubblicazione(Integer.parseInt((String) annoObj));
            } else if (annoObj instanceof Number) {
                libro.setAnnoPubblicazione(((Number) annoObj).intValue());
            }
            
            libro.setLingua(doc.getString("lingua"));
            
            libri.add(libro);
        }
        
        return libri;
    }
    
    // Metodo per salvare un documento nella collection dei risultati
    // Questo inserisce i risultati finali nel database
    public void salvaRisultati(Document risultati) {
        MongoCollection<Document> collection = database.getCollection("ResultsTestBigData");
        collection.insertOne(risultati); // insertOne aggiunge un singolo documento
    }
    
    // Metodo per chiudere la connessione
    public void chiudiConnessione() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }
}
