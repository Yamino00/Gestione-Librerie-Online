package libreria;

import java.util.*;

// Questa classe fa tutte le analisi sui dati delle librerie
// È tipo il cervello del programma, qui ci sono tutti i calcoli
public class AnalisiLibrerie {
    private List<Libreria> librerie;
    private List<Libro> libri;
    
    // Costruttore - salvo le liste così posso usarle in tutti i metodi
    public AnalisiLibrerie(List<Libreria> librerie, List<Libro> libri) {
        this.librerie = librerie;
        this.libri = libri;
    }
    
    // Punto 4: Libreria con il maggior numero di libri
    // Giro tutte le librerie e vedo quale ha più roba
    public Libreria libreriaConPiuLibri() {
        Libreria libreriaMax = null;
        int maxLibri = 0;
        
        for (Libreria libreria : librerie) {
            int numeroLibri = libreria.getLibri().size();
            if (numeroLibri > maxLibri) {
                maxLibri = numeroLibri;
                libreriaMax = libreria;
            }
        }
        
        return libreriaMax;
    }
    
    // Punto 5: Libro con il prezzo più alto
    // Cerco il libro più costoso di tutti
    public Libro libroConPrezzoMassimo() {
        Libro libroMax = null;
        double prezzoMax = 0;
        
        for (Libro libro : libri) {
            if (libro.getPrezzoLibro() > prezzoMax) {
                prezzoMax = libro.getPrezzoLibro();
                libroMax = libro;
            }
        }
        
        return libroMax;
    }
    
    // Punto 6: Libro/i più venduti (presenti in più librerie)
    // Qui conto quante volte ogni libro appare nelle varie librerie
    // Quelli che appaiono di più sono tipo i bestseller
    public List<Libro> libriPiuVenduti() {
        // Conta quante volte ogni ISBN appare nelle librerie
        // Ho usato una HashMap perché è comoda per contare le occorrenze
        Map<String, Integer> conteggioISBN = new HashMap<>();
        
        for (Libreria libreria : librerie) {
            for (String isbn : libreria.getLibri()) {
                // getOrDefault è fichissimo! Se l'ISBN non c'è ancora, parte da 0
                conteggioISBN.put(isbn, conteggioISBN.getOrDefault(isbn, 0) + 1);
            }
        }
        
        // Trova il massimo numero di presenze
        int maxPresenze = 0;
        for (int presenze : conteggioISBN.values()) {
            if (presenze > maxPresenze) {
                maxPresenze = presenze;
            }
        }
        
        // Trova tutti i libri con il massimo numero di presenze
        // Potrebbero essere più di uno con lo stesso numero!
        List<Libro> libriPiuVenduti = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : conteggioISBN.entrySet()) {
            if (entry.getValue() == maxPresenze) {
                // Trova il libro corrispondente all'ISBN
                for (Libro libro : libri) {
                    if (libro.getISBN().equals(entry.getKey())) {
                        libriPiuVenduti.add(libro);
                        break; // break perché una volta trovato posso fermarmi
                    }
                }
            }
        }
        
        return libriPiuVenduti;
    }
    
    // Punto 7: Categoria più frequente
    // Vedo quale categoria di libri è più popolare nelle librerie
    public String categoriaPiuFrequente() {
        // Conta le categorie in TUTTE le librerie
        Map<String, Integer> conteggioCategorie = new HashMap<>();
        
        for (Libreria libreria : librerie) {
            for (String isbn : libreria.getLibri()) {
                // Trova il libro corrispondente
                for (Libro libro : libri) {
                    if (libro.getISBN().equals(isbn)) {
                        String categoria = libro.getCategoriaLibro();
                        conteggioCategorie.put(categoria, conteggioCategorie.getOrDefault(categoria, 0) + 1);
                        break;
                    }
                }
            }
        }
        
        // Trova la categoria più frequente
        String categoriaMax = null;
        int maxOccorrenze = 0;
        
        for (Map.Entry<String, Integer> entry : conteggioCategorie.entrySet()) {
            if (entry.getValue() > maxOccorrenze) {
                maxOccorrenze = entry.getValue();
                categoriaMax = entry.getKey();
            }
        }
        
        return categoriaMax;
    }
    
    // Punto 8: Anno con maggior numero di libri pubblicati
    // Qui guardo in che anno hanno stampato più libri
    public int annoConPiuPubblicazioni() {
        Map<Integer, Integer> conteggioAnni = new HashMap<>();
        
        // Conto quanti libri per ogni anno
        for (Libro libro : libri) {
            int anno = libro.getAnnoPubblicazione();
            conteggioAnni.put(anno, conteggioAnni.getOrDefault(anno, 0) + 1);
        }
        
        // Trovo l'anno con più pubblicazioni
        int annoMax = 0;
        int maxPubblicazioni = 0;
        
        for (Map.Entry<Integer, Integer> entry : conteggioAnni.entrySet()) {
            if (entry.getValue() > maxPubblicazioni) {
                maxPubblicazioni = entry.getValue();
                annoMax = entry.getKey();
            }
        }
        
        return annoMax;
    }
    
    // Punto 9: Per ogni libreria, il libro meno venduto (meno presente)
    // Questa è un po' complicata: per ogni libreria devo trovare quale dei suoi libri
    // è quello che appare meno frequentemente in TUTTE le librerie in generale
    public Map<String, Libro> libroMenoVendutoPerLibreria() {
        Map<String, Libro> risultati = new HashMap<>();
        
        // Ciclo su ogni libreria
        for (Libreria libreria : librerie) {
            // Conta quante volte ogni libro della libreria appare in TUTTE le librerie
            Map<String, Integer> conteggioISBN = new HashMap<>();
            
            for (String isbn : libreria.getLibri()) {
                int presenze = 0;
                // Conto in quante librerie appare questo ISBN
                for (Libreria lib : librerie) {
                    if (lib.getLibri().contains(isbn)) {
                        presenze++;
                    }
                }
                conteggioISBN.put(isbn, presenze);
            }
            
            // Trova l'ISBN con il minor numero di presenze
            String isbnMenoVenduto = null;
            int minPresenze = Integer.MAX_VALUE; // Parto dal valore massimo possibile
            
            for (Map.Entry<String, Integer> entry : conteggioISBN.entrySet()) {
                if (entry.getValue() < minPresenze) {
                    minPresenze = entry.getValue();
                    isbnMenoVenduto = entry.getKey();
                }
            }
            
            // Trova il libro corrispondente all'ISBN meno venduto
            if (isbnMenoVenduto != null) {
                for (Libro libro : libri) {
                    if (libro.getISBN().equals(isbnMenoVenduto)) {
                        risultati.put(libreria.getNomeLibreria(), libro);
                        break; // Una volta trovato esco dal ciclo
                    }
                }
            }
        }
        
        return risultati;
    }
}
