scrivere il file .puml basandosi su questo sito: https://plantuml.com/class-diagram

# Galaxy Trucker - Gruppo 38

**Galaxy Trucker** è un gioco da tavolo in cui i giocatori costruiscono astronavi assemblando componenti spaziali in tempo reale per trasportare merci attraverso lo spazio, affrontando imprevisti, pericoli e sfide interstellari.  
Questa repository ospita una versione digitale testuale sviluppata dal Gruppo 38 nell’ambito dell’esame di Programmazione ad Oggetti presso l’Università degli Studi di Bergamo.

---

## Autori – Gruppo 38
- Matteo Baccanelli  
- Michele Bisignano  
- Marco Magri  
- Henry Iannella Jones  
- Michele Messina  

---

## Obiettivo del progetto

L’obiettivo principale è stato quello di applicare il paradigma della programmazione ad oggetti per modellizzare e digitalizzare il gioco “Galaxy Trucker”.  
Particolare attenzione è stata rivolta alla progettazione delle classi, all’incapsulamento dei comportamenti e alla corretta gestione delle relazioni tra oggetti, secondo i principi SOLID.

---

## Struttura del progetto

Il progetto è suddiviso in package con responsabilità distinte e ben separate:

- `model`: contiene le entità principali del dominio di gioco  
- `grafica`: gestione della visualizzazione testuale e formattazione dell’output  
- `io`: input/output e interazione con l’utente  
- `partita`: gestione delle fasi della partita e del flusso di gioco  
- `servizi`: logiche di supporto (mazzo, dadi, titoli, componenti, ecc.)  
- `util`: funzioni e classi di utilità generiche  
- `eccezioni`: eccezioni personalizzate per una gestione robusta degli errori

---

## Come eseguire il progetto

1. Clona il repository  
   ```sh
   git clone https://github.com/michele-bisignano/Galaxy-Trucker.git
   ```

2. Importa il progetto in un IDE compatibile con Java.

3. Esegui la classe `main.Main` per avviare il gioco.

---

## Riferimenti

Per maggiori informazioni sul gioco originale, consultare la pagina ufficiale di Cranio Creations:  
[https://www.craniocreations.it/prodotto/galaxy-trucker](https://www.craniocreations.it/prodotto/galaxy-trucker)

---

Progetto accademico sviluppato per l’esame di Programmazione ad Oggetti – Università degli Studi di Bergamo  
Gruppo 38