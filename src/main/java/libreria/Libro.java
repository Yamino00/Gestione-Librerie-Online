package libreria;

// Questa classe rappresenta un libro
// Ci ho messo dentro tutte le info che servono: ISBN, autore, prezzo, ecc.
public class Libro {
    private String ISBN; // Questo è tipo il codice a barre del libro, lo identifica in modo univoco
    private String autoreLibro;
    private String editoreLibro;
    private double prezzoLibro; // Ho usato double perché il prezzo può avere i decimali tipo 19.99
    private String titoloLibro;
    private String categoriaLibro; // Es: Fantasy, Giallo, Romanzo...
    private int annoPubblicazione;
    private String lingua;
    
    // Costruttore vuoto - serve per creare l'oggetto "vuoto"
    public Libro() {
    }
    
    // Costruttore completo - passo tutti i parametri in una botta sola
    // Sì, è lungo ma così posso creare un libro con tutte le info subito
    public Libro(String ISBN, String autoreLibro, String editoreLibro, double prezzoLibro, 
                 String titoloLibro, String categoriaLibro, int annoPubblicazione, String lingua) {
        this.ISBN = ISBN;
        this.autoreLibro = autoreLibro;
        this.editoreLibro = editoreLibro;
        this.prezzoLibro = prezzoLibro;
        this.titoloLibro = titoloLibro;
        this.categoriaLibro = categoriaLibro;
        this.annoPubblicazione = annoPubblicazione;
        this.lingua = lingua;
    }
    
    // Getter e Setter - la solita roba per accedere alle variabili private
    public String getISBN() {
        return ISBN;
    }
    
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }
    
    public String getAutoreLibro() {
        return autoreLibro;
    }
    
    public void setAutoreLibro(String autoreLibro) {
        this.autoreLibro = autoreLibro;
    }
    
    public String getEditoreLibro() {
        return editoreLibro;
    }
    
    public void setEditoreLibro(String editoreLibro) {
        this.editoreLibro = editoreLibro;
    }
    
    public double getPrezzoLibro() {
        return prezzoLibro;
    }
    
    public void setPrezzoLibro(double prezzoLibro) {
        this.prezzoLibro = prezzoLibro;
    }
    
    public String getTitoloLibro() {
        return titoloLibro;
    }
    
    public void setTitoloLibro(String titoloLibro) {
        this.titoloLibro = titoloLibro;
    }
    
    public String getCategoriaLibro() {
        return categoriaLibro;
    }
    
    public void setCategoriaLibro(String categoriaLibro) {
        this.categoriaLibro = categoriaLibro;
    }
    
    public int getAnnoPubblicazione() {
        return annoPubblicazione;
    }
    
    public void setAnnoPubblicazione(int annoPubblicazione) {
        this.annoPubblicazione = annoPubblicazione;
    }
    
    public String getLingua() {
        return lingua;
    }
    
    public void setLingua(String lingua) {
        this.lingua = lingua;
    }
    
    // toString - per stampare il libro in modo carino
    @Override
    public String toString() {
        return "Libro{" +
                "ISBN='" + ISBN + '\'' +
                ", titoloLibro='" + titoloLibro + '\'' +
                ", autoreLibro='" + autoreLibro + '\'' +
                ", prezzoLibro=" + prezzoLibro +
                ", categoriaLibro='" + categoriaLibro + '\'' +
                ", annoPubblicazione=" + annoPubblicazione +
                '}';
    }
}
