package libreria;

import java.util.List;

// Questa classe rappresenta una libreria online
// Ho messo tutte le info importanti: nome, sito, titolare e i libri che vende
public class Libreria {
    private String nomeLibreria;
    private String sitoLibreria;
    private String titolareLibreria;
    private List<String> libri; // Lista di ISBN dei libri - uso gli ISBN perché sono tipo il codice univoco di ogni libro
    
    // Costruttore vuoto - serve a Java per creare l'oggetto senza passare niente
    public Libreria() {
    }
    
    // Costruttore con parametri - questo invece crea l'oggetto già con tutti i dati
    public Libreria(String nomeLibreria, String sitoLibreria, String titolareLibreria, List<String> libri) {
        this.nomeLibreria = nomeLibreria;
        this.sitoLibreria = sitoLibreria;
        this.titolareLibreria = titolareLibreria;
        this.libri = libri;
    }
    
    // Getter e Setter - questi metodi servono per leggere e modificare le variabili private
    // Li ho fatti tutti anche se magari non li uso tutti, ma così sono a posto
    public String getNomeLibreria() {
        return nomeLibreria;
    }
    
    public void setNomeLibreria(String nomeLibreria) {
        this.nomeLibreria = nomeLibreria;
    }
    
    public String getSitoLibreria() {
        return sitoLibreria;
    }
    
    public void setSitoLibreria(String sitoLibreria) {
        this.sitoLibreria = sitoLibreria;
    }
    
    public String getTitolareLibreria() {
        return titolareLibreria;
    }
    
    public void setTitolareLibreria(String titolareLibreria) {
        this.titolareLibreria = titolareLibreria;
    }
    
    public List<String> getLibri() {
        return libri;
    }
    
    public void setLibri(List<String> libri) {
        this.libri = libri;
    }
    
    // toString - quando stampo l'oggetto vedo queste info invece dell'indirizzo di memoria
    @Override
    public String toString() {
        return "Libreria{" +
                "nomeLibreria='" + nomeLibreria + '\'' +
                ", titolareLibreria='" + titolareLibreria + '\'' +
                ", numeroLibri=" + (libri != null ? libri.size() : 0) + // Controllo che libri non sia null, sennò crasha
                '}';
    }
}
