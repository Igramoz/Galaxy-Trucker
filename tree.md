```markdown
         src/                              // Root del progetto
        ├── main/                        // Contiene il punto d’ingresso dell’applicazione
        |   └── Main.java                // Avvia il gioco e gestisce l’interfaccia a prompt
        |
        ├── model/                       // Rappresenta il dominio del gioco e gli oggetti di stato
        |    ├── Partita.java               // Stato complessivo della partita e gestione dei turni
        |    ├── Nave.java               // Rappresenta il giocatore (nave, risorse, punteggio)
        |    ├── Giocatore.java          // Rappresenta il giocatore (nave, risorse, punteggio)
        |    ├── Coordinate.java         // Gestisce le posizioni nella griglia della nave
        |    ├── Dado.java               // Simula i 2 dadi per generare numeri casuali
        |    ├── Connettore.java
        |    ├── equipaggio/
        |    |   ├── Pedina.java
        |    |   └── TipoPedina.java      // Enum: ASTRONAUTA, ALIENO_VIOLA, ALIENO_MARRONE (bonus)
        |    └── enums/
        |       ├── TipoMerce.java     // Enum per i colori della merce: ROSSA, GIALLA, VERDE, BLU
        |       ├── Direzione.java     // Enum per la direzione: SOPRA, DESTRA, SOTTO, SINISTRA
        |       ├── tipoNave.java     // Enum tipoPlancia
        |       └── TipoTubo.java
        |
        ├── componenti/
        |    ├── Componente.java
        |    ├── Tubo.java
        |    ├── Cannone.java
        |    |	    └── CannoneDoppio.java
        |    ├── GeneratoreDiScudi.java
        |    ├── CabinaEquipaggio.java
        |    ├── CabinaPartenza.java
        |    ├── Stiva.java
 	|    |	    └── StivaSpeciale.java
        |    ├── VanoBatteria.java
        |    ├── ModuloSupportoAlieni.java
        |    └── Motore.java
        |    	    └── MotoreDoppio.java
	|
	|
	├── nave/
        |   ├── Nave.java
        |   └── Distruttore.java
        |
        ├── cartaAvventura/
        |   ├── Carta.java
        |   ├── PianetiCarta.java
        |   ├── StazioneAbbandonataCarta.java
        |   ├── NaveAbbandonataCarta.java
        |   ├── ContrabbandieriCarta.java
        |   ├── SpazioApertoCarta.java
        |   ├── PioggiaDiMeteoritiCarta.java
        |   ├── ZonaDiGuerraCarta.java
        |   ├── PolvereStellareCarta.java
        |   ├── PiratiCarta.java
        |   ├── SchiavistiCarta.java
        |   ├── EpidemiaCarta.java
        |   └── SabotaggioCarta.java
        |
        ├── controllers/                 // Gestione del flusso di gioco e interazione utente
        |   ├── ControllorePartita.java      // Coordina l’avvio ed evoluzione della partita
        |   ├── ControlloreAssemblaggio.java     // Gestisce la costruzione/configurazione della nave
        |   ├── ControlloreCarte.java     // Gestisce la distribuzione e l’applicazione delle carte
        |   └── ControlloreUI.java        // Gestisce l’interfaccia testuale (prompt)
        |
        ├── services/                    // Logica di business e operazioni complesse
        |   ├── ServizioPunteggio.java        // Calcola punteggi e gestisce penalità
        |   ├── ServizioEventi.java        // Gestisce eventi casuali e programmati (es. volo)
        |   ├── ServizioAssemblaggio.java        // Genera ed estrae randomicamente i componenti della nave; gestisce componenti prenotati e scartati
        |   ├── ServizioCarte.java        // Genera ed estrae randomicamente le carte avventura
        |   └── ServizioDistruzioneComponenti.java  // Funzione complessa per distruggere i componenti della nave (accesso agli attributi della nave)
        |
        ├── grafica/                     // Gestione della grafica del gioco
        |   ├── ConvertitoreGrafico.java  // Associa i simboli grafici ai componenti
	|   ├── FormattatoreGrafico.java 	  // Si occupa del formato della stampa.
	|   ├── TextAligner.java 	  // Si occupa dell'allineamento delle stringhe.
	|   ├── GraficaConfig.java 	  // Configurazione delle costanti grafiche
        |   └── GestoreGrafica.java      // Gestisce la stampa a video dei componenti e dell'interfaccia grafica
        |
        └── util/                        // Funzioni generiche di supporto
            ├── Util.java                // Funzioni generiche di supporto
            ├── LoggerUtil.java          // Gestione del logging
            ├── FileUtil.java            // Operazioni di salvataggio/caricamento dati
            └── RandomUtil.java          // Genera numeri casuali per dadi, eventi e estrazioni

```
