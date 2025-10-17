<div align="center">

# 📚 Gestore Librerie Online

### Sistema di Analisi Dati per Librerie con MongoDB e Java

![Java](https://img.shields.io/badge/Java-8%2B-orange?style=for-the-badge&logo=java)
![MongoDB](https://img.shields.io/badge/MongoDB-4.0%2B-green?style=for-the-badge&logo=mongodb)
![Maven](https://img.shields.io/badge/Maven-3.6%2B-red?style=for-the-badge&logo=apache-maven)
![License](https://img.shields.io/badge/License-MIT-blue?style=for-the-badge)

*Un progetto Big Data per l'analisi intelligente di cataloghi e vendite di librerie online*

[Caratteristiche](#-caratteristiche) • [Installazione](#-installazione) • [Utilizzo](#-utilizzo) • [Struttura](#-struttura-del-progetto) • [API](#-funzionalità-implementate)

</div>

---

## 🎯 Panoramica

**Gestore Librerie Online** è un'applicazione Java che analizza i dati di un network di librerie online utilizzando MongoDB come database NoSQL. Il sistema esegue analisi statistiche avanzate su cataloghi, prezzi, vendite e tendenze editoriali, fornendo insights preziosi per il business.

### ✨ Perché questo progetto?

- 📊 **Analisi Big Data**: Elaborazione di grandi volumi di dati di librerie e libri
- 🔍 **Business Intelligence**: Identificazione di trend e pattern di vendita
- 💾 **NoSQL Database**: Utilizzo di MongoDB per gestione flessibile dei dati
- 🎓 **Progetto Didattico**: Codice chiaro e commentato, perfetto per imparare

---

## 🚀 Caratteristiche

### Analisi Disponibili

| Feature | Descrizione |
|---------|-------------|
| 📖 **Libreria Top** | Identifica la libreria con il maggior numero di libri in catalogo |
| 💰 **Libro Premium** | Trova il libro con il prezzo più elevato |
| 🏆 **Bestseller** | Scopre i libri più venduti (presenti in più librerie) |
| 📚 **Categoria Dominante** | Determina la categoria di libri più frequente |
| 📅 **Anno d'Oro** | Identifica l'anno con il maggior numero di pubblicazioni |
| 📉 **Libri di Nicchia** | Per ogni libreria, trova il libro meno venduto |
| 💾 **Persistenza Dati** | Salva automaticamente i risultati in MongoDB |

---

## 🛠️ Tecnologie Utilizzate

- **Java 8+**: Linguaggio di programmazione principale
- **MongoDB**: Database NoSQL per la persistenza dei dati
- **Maven**: Gestione dipendenze e build automation
- **MongoDB Java Driver**: Connettività nativa MongoDB

---

## 📋 Prerequisiti

Prima di iniziare, assicurati di avere installato:

- ☕ **Java JDK 8** o superiore
- 🔨 **Apache Maven 3.6+**
- 🍃 **MongoDB 4.0+** (in esecuzione su `localhost:27017`)
- 📦 Un database MongoDB chiamato **"Librerie"** con le collection:
  - `Libreria` (dati delle librerie)
  - `Libro` (catalogo completo dei libri)

---

## 📥 Installazione

### 1️⃣ Clona il Repository

```bash
git clone https://github.com/tuousername/gestionelibrerieonline.git
cd gestionelibrerieonline
```

### 2️⃣ Configura MongoDB

Assicurati che MongoDB sia in esecuzione:

```bash
# Windows
net start MongoDB

# Linux/Mac
sudo systemctl start mongod
```

### 3️⃣ Importa i Dati di Test (Opzionale)

```bash
mongoimport --db Librerie --collection Libreria --file Librerie.Libreria.json
mongoimport --db Librerie --collection Libro --file Librerie.Libro.json
```

### 4️⃣ Compila il Progetto

```bash
mvn clean compile
```

---

## 🎮 Utilizzo

### Esecuzione Standard

```bash
mvn exec:java -Dexec.mainClass="libreria.App"
```

### Esecuzione con Package

```bash
mvn clean package
java -jar target/gestionelibrerieonline-1.0-SNAPSHOT.jar
```

### Output di Esempio

```
=== ANALISI LIBRERIE ONLINE ===

Caricamento dati dal database...
Librerie caricate: 5
Libri caricati: 50

--- PUNTO 4 ---
Libreria con più libri: La Feltrinelli (25 libri)

--- PUNTO 5 ---
Libro con prezzo più alto: Il Signore degli Anelli - €45.90

--- PUNTO 6 ---
Libro/i più venduti:
  - Harry Potter e la Pietra Filosofale (ISBN: 978-8831003384)

...

Risultati salvati nella collection 'ResultsTestBigData'!
Connessione al database chiusa.
```

---

## 📂 Struttura del Progetto

```
gestionelibrerieonline/
├── 📄 pom.xml                          # Configurazione Maven
├── 📄 README.md                        # Questo file
├── 📄 RIEPILOGO.md                     # Documentazione dettagliata
├── 📄 run.ps1                          # Script di esecuzione Windows
├── 📄 Librerie.Libreria.json          # Dati di esempio (Librerie)
├── 📄 Librerie.Libro.json             # Dati di esempio (Libri)
└── 📁 src/
    ├── 📁 main/java/libreria/
    │   ├── 📝 App.java                 # Main class - Entry point
    │   ├── 📝 Libro.java               # Model - Entità Libro
    │   ├── 📝 Libreria.java            # Model - Entità Libreria
    │   ├── 📝 MongoDBManager.java      # Database Manager
    │   └── 📝 AnalisiLibrerie.java     # Business Logic - Analisi
    └── 📁 test/java/libreria/
        └── 📝 AppTest.java              # Unit Tests
```

### 📦 Componenti Principali

#### `Libro.java`
Classe POJO che rappresenta un libro con tutte le sue proprietà (ISBN, titolo, autore, prezzo, categoria, ecc.)

#### `Libreria.java`
Classe POJO che rappresenta una libreria online con nome, sito, titolare e catalogo libri

#### `MongoDBManager.java`
Gestisce la connessione a MongoDB e le operazioni CRUD, trasformando documenti in oggetti Java

#### `AnalisiLibrerie.java`
Contiene la business logic con tutti i metodi di analisi statistica sui dati

#### `App.java`
Classe principale che orchestra l'esecuzione di tutte le analisi e la presentazione dei risultati

---

## ⚙️ Funzionalità Implementate

- [x] **Punto 1**: Configurazione schema MongoDB
- [x] **Punto 2**: Gestione entità con Maven Project
- [x] **Punto 3**: Trasformazione collection in liste di oggetti Java
- [x] **Punto 4**: Identificazione libreria con maggior numero di libri
- [x] **Punto 5**: Identificazione libro con prezzo più alto
- [x] **Punto 6**: Identificazione libro/i più venduti (presenti in più librerie)
- [x] **Punto 7**: Identificazione categoria più frequente
- [x] **Punto 8**: Identificazione anno con maggior numero di pubblicazioni
- [x] **Punto 9**: Identificazione per ogni libreria del libro meno venduto
- [x] **Punto 10**: Persistenza risultati in collection "ResultsTestBigData"

---

## 🔧 Configurazione

### Modifica Connessione MongoDB

Modifica il file `App.java` per personalizzare la connessione:

```java
String connectionString = "mongodb://localhost:27017";  // Cambia host/porta
String databaseName = "Librerie";                       // Cambia nome database
```

### Dipendenze Maven

Le principali dipendenze sono gestite in `pom.xml`:

```xml
<dependency>
    <groupId>org.mongodb</groupId>
    <artifactId>mongodb-driver-sync</artifactId>
    <version>4.11.1</version>
</dependency>
```

---

## 📊 Formato Dati

### Struttura Collection `Libreria`

```json
{
  "nomeLibreria": "La Feltrinelli",
  "sitoLibreria": "https://www.lafeltrinelli.it",
  "titolareLibreria": "Mario Rossi",
  "libri": ["978-8831003384", "978-8804666943", ...]
}
```

### Struttura Collection `Libro`

```json
{
  "ISBN": "978-8831003384",
  "titoloLibro": "Harry Potter e la Pietra Filosofale",
  "autoreLibro": "J.K. Rowling",
  "editoreLibro": "Salani",
  "prezzoLibro": 12.90,
  "categoriaLibro": "Fantasy",
  "annoPubblicazione": 1998,
  "lingua": "Italiano"
}
```

---

## 🤝 Contribuire

Le contribuzioni sono benvenute! Ecco come puoi aiutare:

1. 🍴 Fai un Fork del progetto
2. 🔨 Crea un branch per la tua feature (`git checkout -b feature/AmazingFeature`)
3. 💾 Committa le modifiche (`git commit -m 'Add some AmazingFeature'`)
4. 📤 Pusha il branch (`git push origin feature/AmazingFeature`)
5. 🎉 Apri una Pull Request

---

## 📝 License

Questo progetto è distribuito sotto licenza MIT. Vedi il file `LICENSE` per maggiori dettagli.

---

## 👨‍💻 Autore

**Federico** - Progetto Big Data per la gestione e analisi di librerie online

---

## 🙏 Ringraziamenti

- MongoDB per l'eccellente database NoSQL
- Community Java per le librerie open source
- Tutti coloro che contribuiranno al progetto

---

<div align="center">

### ⭐ Se questo progetto ti è stato utile, lascia una stella!

**Made with ❤️ and ☕**

</div>
