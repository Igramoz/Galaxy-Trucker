       src/                              // Root del progetto
        ├── main/                        // Contiene il punto d’ingresso dell’applicazione
        |   └── Main.java                // Avvia il gioco e gestisce l’interfaccia a prompt
        |
        ├── model/                       // Rappresenta il dominio del gioco e gli oggetti di stato
        |    ├── Game.java                // Stato complessivo della partita e gestione dei turni
        |    ├── Player.java              // Rappresenta il giocatore (nave, risorse, punteggio)
        |    ├── Coordinate.java          // Gestisce le posizioni nella griglia della nave
        |    ├── Merce.java               // Rappresenta la merce con colore e valore
        |    ├── Dadi.java                // Simula i 2 dadi per generare numeri casuali
        |    ├── Connettori.java
        |    ├── equipaggio/
        |    |   ├── Pedina.java
        |    |   └── TipoPedina.java      // Enum: ASTRONAUTA, ALIENO_VIOLA, ALIENO_MARRONE (bonus)
        |    └── enums/
        |       ├── ColoreMerce.java     // Enum per i colori della merce: ROSSA, GIALLA, VERDE, BLU
        |       └── TipoTubo.java
        |
        ├── componenti/
        |    ├── AbstractComponent.java
        |    ├── TubiFognari.java
        |    ├── TrivellaAlPlasma.java
        |    ├── Scaldabagni.java
        |    ├── UnitaAbitative.java
        |    ├── UnitaAbitativeCentrale.java
        |    ├── ScompartoDiStoccaggio.java
        |    ├── SovrastrutturaAliena.java
        |    └── GeneratoreDiScudi.java
        |
        ├── cartaAvventura/
        |   ├── AbstractCarta.java
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
        |   ├── GameController.java      // Coordina l’avvio ed evoluzione della partita
        |   ├── BuildController.java     // Gestisce la costruzione/configurazione della nave
        |   ├── CartaController.java     // Gestisce la distribuzione e l’applicazione delle carte
        |   └── UIController.java        // Gestisce l’interfaccia testuale (prompt)
        |
        ├── services/                    // Logica di business e operazioni complesse
        |   ├── ScoreService.java        // Calcola punteggi e gestisce penalità
        |   ├── EventService.java        // Gestisce eventi casuali e programmati (es. volo)
        |   ├── BuildService.java        // Genera ed estrae randomicamente i componenti della nave; gestisce componenti prenotati e scartati
        |   ├── CartaService.java        // Genera ed estrae randomicamente le carte avventura
        |   └── DistruzioneComponentiService.java  // Funzione complessa per distruggere i componenti della nave (accesso agli attributi della nave)
        |
        └── util/
            ├── Util.java                // Funzioni generiche di supporto
            ├── LoggerUtil.java          // Gestione del logging
            ├── FileUtil.java            // Operazioni di salvataggio/caricamento dati
            └── RandomUtil.java          // Genera numeri casuali per dadi, eventi e estrazioni
