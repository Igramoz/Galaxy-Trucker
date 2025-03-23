# Documentazione del Progetto Galaxy Trucker

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
- **Metodi pubblici:**
  - `public static boolean contieneCoordinata(List<Set<Coordinate>> listaCoordinateComponentiControllati, Coordinate coordinate)`: Controlla se nel set di coordinate è presente una coordinata. Restituisce true se è presente.
    - **Input:** `List<Set<Coordinate>> listaCoordinateComponentiControllati`, `Coordinate coordinate`
    - **Output:** `boolean` - True se la coordinata è presente, altrimenti False

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
  - `public void incrementaPezziDistrutti()`: Incrementa il contatore dei pezzi distrutti.
    - **Input:** Nessuno.
    - **Output:** Nessuno.

---

## Package: componenti

---

### Classe: Componente
- **Attributi pubblici:** Nessuno
- **Metodi pubblici:**
  - `public Componente(TipoComponente t, boolean usaEnergia, int energia)`: Costruttore della classe Componente.
    - **Input:** `TipoComponente t`, `boolean usaEnergia`, `int energia`
    - **Output:** Nessuno.
  - `public Componente(Componente com)`: Costruttore di copia della classe Componente.
    - **Input:** `Componente com`
    - **Output:** Nessuno.
  - `public TipoComponente getTipo()`: Restituisce il tipo di componente.
    - **Input:** Nessuno.
    - **Output:** `TipoComponente`
  - `public boolean getRichiestaEnergia()`: Restituisce se il componente richiede energia.
    - **Input:** Nessuno.
    - **Output:** `boolean`
  - `public int getMaxEnergia()`: Restituisce l'energia massima del componente.
    - **Input:** Nessuno.
    - **Output:** `int`
  - `public void setMaxEnergia(int maxEnergia)`: Imposta l'energia massima del componente.
    - **Input:** `int maxEnergia`
    - **Output:** Nessuno.
  - `public abstract void consumaEnergia()`: Metodo astratto per consumare energia.
    - **Input:** Nessuno.
    - **Output:** Nessuno.
  - `public boolean equals(Componente other)`: Confronta l'uguaglianza tra due componenti.
    - **Input:** `Componente other`
    - **Output:** `boolean`

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

## Package: utils

---

### Classe: Direzioni

### Metodi

- `direzioneTubo()`
  - Metodo che gestisce le direzioni del tubo all'interno della nave.
