# Documentazione Classi del Progetto Galaxy Trucker

---

## Package: main

---

### Classe: Main
- **Attributi pubblici:** Nessuno
- **Metodi pubblici:**
  - `public static void main(String[] args)`: Punto di ingresso principale dell'applicazione.
    - **Input:** `String[] args` - Argomenti della riga di comando.
    - **Output:** Nessuno.

---

## Package: util

---

### Classe: Util
- **Attributi pubblici:**
  - `public final static int SIZE = 12`: Costante che rappresenta la dimensione della griglia.
  - `public final static int OFFSET = 2`: Differenza tra numeri visti dall'utente e utilizzati dal computer.
- **Metodi pubblici:**
  - `public static boolean contieneCoordinata(List<Set<Coordinate>> listaCoordinateComponentiControllati, Coordinate coordinate)`: Controlla se nel set di coordinate è presente una coordinata. Restituisce true se è presente.
    - **Input:** `List<Set<Coordinate>> listaCoordinateComponentiControllati`, `Coordinate coordinate`
    - **Output:** `boolean` - True se la coordinata è presente, altrimenti False.
  - `public static Direzione ruotaDirezione(Direzione direzione)`: Ruota la direzione in senso antiorario.
    - **Input:** `Direzione direzione`
    - **Output:** `Direzione` - La nuova direzione dopo la rotazione.

---

## Package: model

---

### Classe: Nave
- **Attributi pubblici:** Nessuno
- **Metodi pubblici:**
  - `public Componente[][] getGrigliaComponenti()`: Restituisce una copia della griglia dei componenti.
    - **Input:** Nessuno.
    - **Output:** `Componente[][]` - COPIA della griglia dei componenti.
  - `public Componente getComponente(Coordinate coordinate)`: Restituisce il componente alla posizione specificata.
    - **Input:** `Coordinate coordinate` - Le coordinate del componente.
    - **Output:** `Componente` - Il componente alla posizione specificata.
  - `public void setComponente(Componente componente, Coordinate coordinate)`: Imposta il componente alla posizione specificata.
    - **Input:** `Componente componente`, `Coordinate coordinate` - Il componente da impostare e le sue coordinate.
    - **Output:** Nessuno.
  - `public boolean setComponentiPrenotati(Componente componente)`: Aggiunge un componente alla lista dei componenti prenotati.
    - **Input:** `Componente componente` - Il componente da prenotare.
    - **Output:** `boolean` - True se il componente è stato prenotato con successo, altrimenti False.
  - `public Componente[] getComponentiPrenotati()`: Restituisce una copia della lista dei componenti prenotati.
    - **Input:** Nessuno.
    - **Output:** `Componente[]` - Copia della lista dei componenti prenotati.
  - `public boolean rimuoviComponentiPrenotati(Componente componente)`: Rimuove un componente dalla lista dei componenti prenotati.
    - **Input:** `Componente componente` - Il componente da rimuovere.
    - **Output:** `boolean` - True se il componente è stato rimosso con successo, altrimenti False.
  - `public int getPezziDistrutti()`: Restituisce il numero corrente di pezzi distrutti.
    - **Input:** Nessuno.
    - **Output:** `int` - Il numero di pezzi distrutti.
  - `public void incrementaPezziDistrutti()`: Incrementa il contatore dei pezzi distrutti.
    - **Input:** Nessuno.
    - **Output:** Nessuno.
  - `public void distruggiSingoloComponente(Coordinate coordinate)`: Distrugge un singolo componente alla posizione specificata. Può essere chiamato solo da `EventService`.
    - **Input:** `Coordinate coordinate` - Le coordinate del componente da distruggere.
    - **Output:** Nessuno.


### Classe: Coordinate
- **Attributi pubblici:** Nessuno
- **Metodi pubblici:**
  - `public Coordinate(int x, int y)`: Costruttore che inizializza le coordinate.
    - **Input:** `int x`, `int y` - Le coordinate da inizializzare.
    - **Output:** Nessuno.
  - `public Coordinate(Coordinate c)`: Costruttore di copia.
    - **Input:** `Coordinate c` - L'oggetto `Coordinate` da copiare.
    - **Output:** Nessuno.
  - `public int getX()`: Restituisce il valore della coordinata x.
    - **Input:** Nessuno.
    - **Output:** `int` - Il valore della coordinata x.
  - `public int getY()`: Restituisce il valore della coordinata y.
    - **Input:** Nessuno.
    - **Output:** `int` - Il valore della coordinata y.
  - `public boolean isEqual(Coordinate c)`: Confronta se le coordinate sono uguali.
    - **Input:** `Coordinate c` - L'oggetto `Coordinate` da confrontare.
    - **Output:** `boolean` - True se le coordinate sono uguali, altrimenti False.

---

## Package: model.enums

---

### Enum: Direzione
- **Costanti:**
  - `SOPRA`: Rappresenta la direzione sopra.
  - `DESTRA`: Rappresenta la direzione destra.
  - `SINISTRA`: Rappresenta la direzione sinistra.
  - `SOTTO`: Rappresenta la direzione sotto.


### Enum: TipoMerce
- **Costanti:**
  - `BLU(1)`: Costante che rappresenta il tipo di merce blu con valore 1.
  - `VERDE(2)`: Costante che rappresenta il tipo di merce verde con valore 2.
  - `GIALLO(3)`: Costante che rappresenta il tipo di merce giallo con valore 3.
  - `ROSSO(4)`: Costante che rappresenta il tipo di merce rosso con valore 4.
- **Metodi pubblici:**
  - `public int getValore()`: Restituisce il valore del tipo di merce.
    - **Input:** Nessuno.
    - **Output:** `int` - Il valore del tipo di merce.


### Enum: TipoTubo
- **Costanti:**
  - `NESSUNO("", "")`: Rappresenta l'assenza di un tubo.
  - `SINGOLO("|", "-")`: Rappresenta un tubo singolo con sigle orizzontali e verticali.
  - `DOPPIO("||", "=")`: Rappresenta un tubo doppio con sigle orizzontali e verticali.
  - `UNIVERSALE("#", "#")`: Rappresenta un tubo universale con sigle orizzontali e verticali.
- **Attributi privati:**
  - `private final String siglaOrizzontale`: Sigla da usare quando il tubo è orizzontale.
  - `private final String siglaVerticale`: Sigla da usare quando il tubo è verticale.
- **Costruttori privati:**
  - `private TipoTubo()`: Costruttore predefinito che inizializza le sigle vuote.
    - **Input:** Nessuno.
    - **Output:** Nessuno.
  - `private TipoTubo(String siglaOrizzontale, String siglaVerticale)`: Costruttore che inizializza le sigle orizzontali e verticali.
    - **Input:** `String siglaOrizzontale`, `String siglaVerticale` - Le sigle da inizializzare.
    - **Output:** Nessuno.
- **Metodi pubblici:**
  - `public String toString(Direzione direzione)`: Restituisce la sigla corrispondente alla direzione specificata.
    - **Input:** `Direzione direzione` - La direzione del tubo.
    - **Output:** `String` - La sigla corrispondente alla direzione specificata.

---

## Package: componenti

---

### Classe: Cannone
- **Attributi pubblici:**
  - `public static final int limiteIstanziabili`: Limite massimo di istanze di `Cannone` che possono essere create.
  - `public static int istanze`: Contatore delle istanze create di `Cannone`.
- **Attributi privati:**
  - `private float potenzaFuoco`: La potenza di fuoco del cannone.
  - `private Direzione direzione`: La direzione in cui il cannone spara.
- **Costruttori:**
  - `public Cannone(TipoComponente tipo, Map<Direzione, TipoTubo> tubiIniziali)`: Costruttore che inizializza il cannone.
    - **Input:** `TipoComponente tipo`, `Map<Direzione, TipoTubo> tubiIniziali` - Il tipo di componente e i tubi iniziali.
    - **Output:** Nessuno.
- **Metodi privati:**
  - `private int gestisciPotenzaDiFuoco(TipoComponente tipo)`: Gestisce la potenza di fuoco in base al tipo di componente.
    - **Input:** `TipoComponente tipo` - Il tipo di componente.
    - **Output:** `int` - La potenza di fuoco.
- **Metodi pubblici:**
  - `public void ruota()`: Ruota il cannone e aggiorna la potenza di fuoco in base alla nuova direzione.
    - **Input:** Nessuno.
    - **Output:** Nessuno.
  - `public Direzione getDirezione()`: Restituisce la direzione del cannone.
    - **Input:** Nessuno.
    - **Output:** `Direzione` - La direzione del cannone.
  - `public float getPotenzaFuoco()`: Restituisce la potenza di fuoco del cannone.
    - **Input:** Nessuno.
    - **Output:** `float` - La potenza di fuoco del cannone.
  - `public int getIstanze()`: Restituisce il numero di istanze create di `Cannone`.
    - **Input:** Nessuno.
    - **Output:** `int` - Il numero di istanze create.
  - `protected void incrementaIstanze()`: Incrementa il contatore delle istanze create di `Cannone`.
    - **Input:** Nessuno.
    - **Output:** Nessuno.
  - `public void resetIstanze()`: Resetta il contatore delle istanze create di `Cannone`.
    - **Input:** Nessuno.
    - **Output:** Nessuno.
  - `protected void decrementaIstanze()`: Decrementa il contatore delle istanze create di `Cannone`.
    - **Input:** Nessuno.
    - **Output:** Nessuno.


### Classe: Componente
- **Attributi protetti:**
  - `protected final TipoComponente tipo`: Il tipo del componente.
  - `protected Map<Direzione, TipoTubo> tubi`: Mappa delle direzioni e dei rispettivi tubi.
- **Costruttori:**
  - `public Componente(TipoComponente tipo, Map<Direzione, TipoTubo> tubiIniziali)`: Costruttore che inizializza il componente con il tipo e i tubi iniziali.
    - **Input:** `TipoComponente tipo`, `Map<Direzione, TipoTubo> tubiIniziali` - Il tipo di componente e i tubi iniziali.
    - **Output:** Nessuno.
  - `public Componente(Componente componente)`: Costruttore di copia.
    - **Input:** `Componente componente` - Il componente da copiare.
    - **Output:** Nessuno.
- **Metodi pubblici:**
  - `public Componente clone()`: Metodo che dovrà essere override dalle sottoclassi.
    - **Input:** Nessuno.
    - **Output:** `Componente` - Restituisce null.
  - `public boolean equals(Componente altroComponente)`: Confronta se due componenti sono uguali.
    - **Input:** `Componente altroComponente` - L'altro componente da confrontare.
    - **Output:** `boolean` - True se i componenti sono uguali, altrimenti False.
  - `public void ruota()`: Ruota i tubi in senso antiorario.
    - **Input:** Nessuno.
    - **Output:** Nessuno.
  - `public abstract int getIstanze()`: Metodo astratto per ottenere il numero di istanze.
    - **Input:** Nessuno.
    - **Output:** `int` - Il numero di istanze.
  - `public abstract void resetIstanze()`: Metodo astratto per resettare il numero di istanze.
    - **Input:** Nessuno.
    - **Output:** Nessuno.
  - `protected abstract void incrementaIstanze()`: Metodo astratto per incrementare il numero di istanze.
    - **Input:** Nessuno.
    - **Output:** Nessuno.
  - `protected abstract void decrementaIstanze()`: Metodo astratto per decrementare il numero di istanze.
    - **Input:** Nessuno.
    - **Output:** Nessuno.
  - `public TipoComponente getTipo()`: Restituisce il tipo del componente.
    - **Input:** Nessuno.
    - **Output:** `TipoComponente` - Il tipo del componente.
  - `public TipoTubo getTubo(Direzione direzione)`: Restituisce il tubo nella direzione specificata.
    - **Input:** `Direzione direzione` - La direzione del tubo.
    - **Output:** `TipoTubo` - Il tubo nella direzione specificata.
  - `public Map<Direzione, TipoTubo> getTubi()`: Genera una copia dei tubi.
    - **Input:** Nessuno.
    - **Output:** `Map<Direzione, TipoTubo>` - La copia dei tubi.
  - `public int getMaxIstanze()`: Restituisce il numero massimo di istanze.
    - **Input:** Nessuno.
    - **Output:** `int` - Il numero massimo di istanze.


### Classe: Stiva
- **Attributi pubblici:** Nessuno
- **Metodi pubblici:**
  - `public Stiva(Map<Direzione, TipoTubo> tubiIniziali, int scomparti, boolean speciale)`: Costruttore privato della classe Stiva.
    - **Input:** `Map<Direzione, TipoTubo> tubiIniziali`, `int scomparti`, `boolean speciale`
    - **Output:** Nessuno.
  - `public Stiva(Stiva stiva)`: Costruttore di copia della classe Stiva.
    - **Input:** `Stiva stiva`
    - **Output:** Nessuno.
  - `public TipoMerce[] getMerci()`: Restituisce una copia dell'array delle merci.
    - **Input:** Nessuno.
    - **Output:** `TipoMerce[]` - Copia dell'array delle merci.
  - `public boolean setMerci(TipoMerce merce)`: Aggiunge una merce alla stiva. Restituisce false se la stiva è piena o se la merce non può essere immagazzinata.
    - **Input:** `TipoMerce merce`
    - **Output:** `boolean` - True se la merce è stata aggiunta con successo, altrimenti False.
  - `public int valoreMerci()`: Calcola il valore della merce trasportata nella stiva.
    - **Input:** Nessuno.
    - **Output:** `int` - Valore totale della merce trasportata.
  - `public int getLimiteIstanziabili()`: Restituisce il limite massimo di istanze della stiva.
    - **Input:** Nessuno.
    - **Output:** `int`
  - `public int getIstanze()`: Restituisce il numero corrente di istanze della stiva.
    - **Input:** Nessuno.
    - **Output:** `int`
  - `public int getScomparti()`: Restituisce il numero di scomparti della stiva.
    - **Input:** Nessuno.
    - **Output:** `int`
  - `public boolean isSpeciale()`: Restituisce se la stiva è speciale.
    - **Input:** Nessuno.
    - **Output:** `boolean`


### Classe: StivaSpeciale
- **Attributi privati:**
  - `private static int istanze`: Contatore delle istanze create di `StivaSpeciale`.
- **Costruttori:**
  - `public StivaSpeciale(Map<Direzione, TipoTubo> tubiIniziali, int scomparti)`: Costruttore che inizializza la stiva speciale con i tubi iniziali e il numero di scomparti.
    - **Input:** `Map<Direzione, TipoTubo> tubiIniziali`, `int scomparti` - I tubi iniziali e il numero di scomparti.
    - **Output:** Nessuno.
  - `public StivaSpeciale(StivaSpeciale stiva)`: Costruttore di copia.
    - **Input:** `StivaSpeciale stiva` - La stiva speciale da copiare.
    - **Output:** Nessuno.
- **Metodi pubblici:**
  - `public Componente clone()`: Clona la stiva speciale.
    - **Input:** Nessuno.
    - **Output:** `Componente` - Il clone della stiva speciale.
  - `public boolean setMerci(TipoMerce merce)`: Imposta il tipo di merce nella stiva speciale.
    - **Input:** `TipoMerce merce` - Il tipo di merce da impostare.
    - **Output:** `boolean` - True se la merce è stata impostata con successo, altrimenti False.
  - `public int getIstanze()`: Restituisce il numero di istanze create di `StivaSpeciale`.
    - **Input:** Nessuno.
    - **Output:** `int` - Il numero di istanze create.
  - `public void incrementaIstanze()`: Incrementa il contatore delle istanze create di `StivaSpeciale`.
    - **Input:** Nessuno.
    - **Output:** Nessuno.
  - `public void decrementaIstanze()`: Decrementa il contatore delle istanze create di `StivaSpeciale`.
    - **Input:** Nessuno.
    - **Output:** Nessuno.
  - `public void resetIstanze()`: Resetta il contatore delle istanze create di `StivaSpeciale`.
    - **Input:** Nessuno.
    - **Output:** Nessuno.
- **Metodi protetti:**
  - `protected boolean isMerceAggiungibile(TipoMerce merce)`: Controlla se il tipo di merce è aggiungibile nella stiva speciale.
    - **Input:** `TipoMerce merce` - Il tipo di merce da controllare.
    - **Output:** `boolean` - True se la merce è aggiungibile, altrimenti False.



### Classe: GeneratoreDiScudi
- **Attributi privati:**
  - `private static int istanze`: Contatore delle istanze create di `GeneratoreDiScudi`.
  - `private boolean statoScudo`: Stato dello scudo (attivo o disattivo).
  - `private Direzione direzione1`: Prima direzione dello scudo.
  - `private Direzione direzione2`: Seconda direzione dello scudo.
- **Costruttori:**
  - `public GeneratoreDiScudi(Map<Direzione, TipoTubo> tubiIniziali)`: Costruttore che inizializza il generatore di scudi con i tubi iniziali.
    - **Input:** `Map<Direzione, TipoTubo> tubiIniziali` - I tubi iniziali.
    - **Output:** Nessuno.
  - `public GeneratoreDiScudi(GeneratoreDiScudi g)`: Costruttore di copia.
    - **Input:** `GeneratoreDiScudi g` - Il generatore di scudi da copiare.
    - **Output:** Nessuno.
- **Metodi pubblici:**
  - `public void attivaScudo()`: Attiva lo scudo.
    - **Input:** Nessuno.
    - **Output:** Nessuno.
  - `public void disattivaScudo()`: Disattiva lo scudo.
    - **Input:** Nessuno.
    - **Output:** Nessuno.
  - `public boolean getstatoScudo()`: Restituisce lo stato dello scudo.
    - **Input:** Nessuno.
    - **Output:** `boolean` - Lo stato dello scudo.
  - `public Direzione getDirezione1()`: Restituisce la prima direzione dello scudo.
    - **Input:** Nessuno.
    - **Output:** `Direzione` - La prima direzione dello scudo.
  - `public Direzione getDirezione2()`: Restituisce la seconda direzione dello scudo.
    - **Input:** Nessuno.
    - **Output:** `Direzione` - La seconda direzione dello scudo.
  - `public void ruota()`: Ruota il generatore di scudi e aggiorna le direzioni.
    - **Input:** Nessuno.
    - **Output:** Nessuno.
  - `public Componente clone()`: Clona il generatore di scudi.
    - **Input:** Nessuno.
    - **Output:** `Componente` - Il clone del generatore di scudi.
  - `public int getIstanze()`: Restituisce il numero di istanze create di `GeneratoreDiScudi`.
    - **Input:** Nessuno.
    - **Output:** `int` - Il numero di istanze create.
  - `public void resetIstanze()`: Resetta il contatore delle istanze create di `GeneratoreDiScudi`.
    - **Input:** Nessuno.
    - **Output:** Nessuno.
  - `protected void incrementaIstanze()`: Incrementa il contatore delle istanze create di `GeneratoreDiScudi`.
    - **Input:** Nessuno.
    - **Output:** Nessuno.
  - `protected void decrementaIstanze()`: Decrementa il contatore delle istanze create di `GeneratoreDiScudi`.
    - **Input:** Nessuno.
    - **Output:** Nessuno.



### Enum: TipoComponente
- **Valori Enum:**
  - `CABINA_EQUIPAGGIO("CE", 17)`: Cabina per l'equipaggio, massimo 17 istanze.
  - `CABINA_PARTENZA("CP", 4)`: Cabina di partenza, massimo 4 istanze.
  - `CANNONE_SINGOLO("CS", 25)`: Cannone singolo, massimo 25 istanze.
  - `CANNONE_DOPPIO("CD", 11)`: Cannone doppio, massimo 11 istanze.
  - `MOTORE_SINGOLO("MS", 21)`: Motore singolo, massimo 21 istanze.
  - `MOTORE_DOPPIO("MD", 9)`: Motore doppio, massimo 9 istanze.
  - `SCUDO("S", 8)`: Scudo, massimo 8 istanze.
  - `STIVA("M", 25)`: Stiva (Magazzino), massimo 25 istanze.
  - `STIVA_SPECIALE("MS", 9)`: Stiva speciale, massimo 9 istanze.
  - `VANO_BATTERIA("B", 17)`: Vano batteria, massimo 17 istanze.
  - `SOVRASTRUTTURA_ALIENA_VIOLA("SAV", 6)`: Sovrastruttura aliena viola, massimo 6 istanze.
  - `SOVRASTRUTTURA_ALIENA_MARRONE("SAM", 6)`: Sovrastruttura aliena marrone, massimo 6 istanze.
  - `TUBO("T", 8)`: Tubo, massimo 8 istanze.
- **Attributi privati:**
  - `private final String sigla`: Sigla del tipo di componente.
  - `private final int maxIstanze`: Numero massimo di istanze del tipo di componente.
- **Costruttori:**
  - `private TipoComponente(String s, int maxIstanze)`: Costruttore che inizializza la sigla e il numero massimo di istanze.
    - **Input:** `String s`, `int maxIstanze` - La sigla e il numero massimo di istanze.
    - **Output:** Nessuno.
- **Metodi pubblici:**
  - `public String toString()`: Restituisce la sigla del tipo di componente.
    - **Input:** Nessuno.
    - **Output:** `String` - La sigla del tipo di componente.
  - `public int getMaxIstanze()`: Restituisce il numero massimo di istanze del tipo di componente.
    - **Input:** Nessuno.
    - **Output:** `int` - Il numero massimo di istanze.



### Enum: TipoComponente
- **Valori Enum:**
  - `CABINA_EQUIPAGGIO("CE", 17)`: Cabina per l'equipaggio, massimo 17 istanze.
  - `CABINA_PARTENZA("CP", 4)`: Cabina di partenza, massimo 4 istanze.
  - `CANNONE_SINGOLO("CS", 25)`: Cannone singolo, massimo 25 istanze.
  - `CANNONE_DOPPIO("CD", 11)`: Cannone doppio, massimo 11 istanze.
  - `MOTORE_SINGOLO("MS", 21)`: Motore singolo, massimo 21 istanze.
  - `MOTORE_DOPPIO("MD", 9)`: Motore doppio, massimo 9 istanze.
  - `SCUDO("S", 8)`: Scudo, massimo 8 istanze.
  - `STIVA("M", 25)`: Stiva (Magazzino), massimo 25 istanze.
  - `STIVA_SPECIALE("MS", 9)`: Stiva speciale, massimo 9 istanze.
  - `VANO_BATTERIA("B", 17)`: Vano batteria, massimo 17 istanze.
  - `SOVRASTRUTTURA_ALIENA_VIOLA("SAV", 6)`: Sovrastruttura aliena viola, massimo 6 istanze.
  - `SOVRASTRUTTURA_ALIENA_MARRONE("SAM", 6)`: Sovrastruttura aliena marrone, massimo 6 istanze.
  - `TUBO("T", 8)`: Tubo, massimo 8 istanze.
- **Attributi privati:**
  - `private final String sigla`: Sigla del tipo di componente.
  - `private final int maxIstanze`: Numero massimo di istanze del tipo di componente.
- **Costruttori:**
  - `private TipoComponente(String s, int maxIstanze)`: Costruttore che inizializza la sigla e il numero massimo di istanze.
    - **Input:** `String s`, `int maxIstanze` - La sigla e il numero massimo di istanze.
    - **Output:** Nessuno.
- **Metodi pubblici:**
  - `public String toString()`: Restituisce la sigla del tipo di componente.
    - **Input:** Nessuno.
    - **Output:** `String` - La sigla del tipo di componente.
  - `public int getMaxIstanze()`: Restituisce il numero massimo di istanze del tipo di componente.
    - **Input:** Nessuno.
    - **Output:** `int` - Il numero massimo di istanze.

---

## Package: controller

---

### Classe: EventService

### Metodi

- `distruggiComponenti(Nave nave, Coordinate coordinate)`
  - Distrugge i componenti di una nave a partire da una determinata coordinata.
  
- `controllaConnessioneComponente(Nave nave, Coordinate coordinate, Set<Coordinate> coordinateComponentiControllati)`
  - Controlla quali componenti della nave sono connessi partendo da una determinata coordinata.
  
- `controllaConnessioneInDirezione(Nave nave, Componente componente, Coordinate coordinate, Componente.DirezioneTubo direzione, Set<Coordinate> coordinateComponentiControllati)`
  - Controlla la connessione dei componenti della nave in una direzione specifica.
  
- `distruggiComponentiNonCollegate(Nave nave, Set<Coordinate> coordinateComponentiControllati)`
  - Distrugge i componenti della nave che non sono collegati a partire da una determinata coordinata.
  
- `contieneCoordinata(List<Set<Coordinate>> coordinateComponentiControllatiList, Coordinate coordinate)`
  - Restituisce `true` se nella lista di set è presente una coordinata.

---

### Classe: Direzioni

### Metodi

- `direzioneTubo()`
  - Metodo che gestisce le direzioni del tubo all'interno della nave.
 
--

## Package: model.enums

---

### Classe: TipoMerce
- **Attributi pubblici:**
  - `BLU(1)`: Costante che rappresenta il tipo di merce blu con valore 1.
  - `VERDE(2)`: Costante che rappresenta il tipo di merce verde con valore 2.
  - `GIALLO(3)`: Costante che rappresenta il tipo di merce giallo con valore 3.
  - `ROSSO(4)`: Costante che rappresenta il tipo di merce rosso con valore 4.
- **Metodi pubblici:**
  - `public int getValore()`: Restituisce il valore del tipo di merce.
    - **Input:** Nessuno.
    - **Output:** `int` - Il valore del tipo di merce.

---
