```markdown
 src/                              // Root del progetto
├── main/                         // Contiene il punto d’ingresso dell’applicazione
│   └── Main.java                 // Avvia il gioco e gestisce l’interfaccia a prompt
│
├── model/                        // Rappresenta il dominio del gioco e gli oggetti di stato
│   ├── Partita.java              // Stato complessivo della partita e gestione dei turni
│   ├── Nave.java                 // Rappresenta il giocatore (nave, risorse, punteggio)
│   ├── Giocatore.java            // Rappresenta il giocatore (nave, risorse, punteggio)
│   ├── Coordinate.java           // Gestisce le posizioni nella griglia della nave
│   ├── Dado.java                 // Simula i 2 dadi per generare numeri casuali
│   ├── equipaggio/
│   │   ├── Pedina.java
│   │   └── TipoPedina.java       // Enum: ASTRONAUTA, ALIENO_VIOLA, ALIENO_MARRONE (bonus)
│   └── enums/
│       ├── TipoMerce.java        // Enum per i colori della merce: ROSSA, GIALLA, VERDE, BLU
│       ├── Direzione.java        // Enum per la direzione: SOPRA, DESTRA, SOTTO, SINISTRA
│       └── TipoTubo.java
│
├── componenti/
│   ├── Componente.java
│   ├── Tubo.java
│   ├── Cannone.java
│   │   └── CannoneDoppio.java
│   ├── GeneratoreDiScudi.java
│   ├── CabinaEquipaggio.java
│   ├── CabinaPartenza.java
│   ├── Stiva.java
│   │   └── StivaSpeciale.java
│   ├── VanoBatteria.java
│   ├── ModuloSupportoAlieni.java
│   └── Motore.java
│       └── MotoreDoppio.java
│
├── partita/
│   ├── LivelliPartita.java       // Funzioni per stampare
│   ├── Partita.java              // Funzioni per stampare
│   └── fasidigioco/
│       ├── Inizializzazione.java // Prima fase di gioco
│       ├── FineVolo.java         // Vengono assegnati i crediti
│       ├── Volo.java             // Core del gioco
│       └── composizione/
│           ├── ComposizioneNave.java		// Seconda fase di gioco
│           └── ManagerTurno.java 		// Gestisce il singolo turno
│
├── nave/
│   ├── Nave.java
│   ├── tipoNave.java             // Enum tipoPlancia
│   ├── ValidatorePosizione.java	// controlla se si può inserire il pezzo
│   └── Distruttore.java          // Gestisce la distruzione della nave
│
├── cartaAvventura/
│   ├── Carta.java
│   ├── PianetiCarta.java
│   ├── StazioneAbbandonataCarta.java
│   ├── NaveAbbandonataCarta.java
│   ├── ContrabbandieriCarta.java
│   ├── SpazioApertoCarta.java
│   ├── PioggiaDiMeteoritiCarta.java
│   ├── ZonaDiGuerraCarta.java
│   ├── PolvereStellareCarta.java
│   ├── PiratiCarta.java
│   ├── SchiavistiCarta.java
│   ├── EpidemiaCarta.java
│   └── SabotaggioCarta.java
│
├── services/                     // Logica di business e operazioni complesse
│   ├── ServizioPunteggio.java           // Calcola punteggi e gestisce penalità
│   ├── ServizioEventi.java              // Gestisce eventi casuali e programmati (es. volo)
│   ├── ServizioAssemblaggio.java        // Genera ed estrae randomicamente i componenti della nave
│   ├── ServizioCarte.java               // Genera ed estrae randomicamente le carte avventura
│   └── ServizioDistruzioneComponenti.java // Distrugge componenti della nave
│
├── io/
│   ├── GestoreOutput.java        // Funzioni per stampare
│   └── GestoreInput.java         // Funzioni per gli input
│
├── grafica/                      // Gestione della grafica del gioco
│   ├── NaveRenderer.java         // Disegna la nave con la legenda
│   ├── ComponentiRenderer.java   // I componenti
│   ├── FormattatoreGrafico.java // Si occupa del formato della stampa
│   ├── TextAligner.java          // Si occupa dell'allineamento delle stringhe
│   └── GraficaConfig.java        // Configurazione delle costanti grafiche
│
└── util/                         // Funzioni generiche di supporto
    ├── Util.java                 // Funzioni generiche di supporto
    └── RandomUtil.java           // Genera numeri casuali per dadi, eventi e estrazioni


```
