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
- **Metodi pubblici:** Nessuno

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
  - `public void distruggiSingoloComponente(Coordinate coordinate)`: Distrugge il componente alla posizione specificata.
    - **Input:** `Coordinate coordinate` - Le coordinate del componente da distruggere.
    - **Output:** Nessuno.
