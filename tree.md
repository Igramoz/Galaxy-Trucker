src/                              // Root del progetto  
├── main/                        // Contiene il punto d’ingresso dell’applicazione  
│   └── Main.java                // Avvia il gioco e gestisce l’interfaccia a prompt  
├── model/                       // Rappresenta il dominio del gioco e gli oggetti di stato  
│   ├── Game.java                // Stato complessivo della partita e gestione dei turni  
│   ├── Player.java              // Rappresenta il giocatore (nave, risorse, punteggio)  
│   ├── Coordinate.java          // Gestisce le posizioni nella griglia della nave  
│   ├── Merce.java               // Rappresenta la merce con colore e valore  
│   ├── Dadi.java                // Simula i 2 dadi per generare numeri casuali  
│   ├── Connettori.java          // Gestisce i connettori tra i componenti della nave  
│   ├── equipaggio/              // Gestisce le pedine/equipaggio del gioco  
│   │   ├── Pedina.java          // Rappresenta una pedina da inserire nelle cabine  
│   │   └── TipoPedina.java      // Enum: ASTRONAUTA, ALIENO_VIOLA, ALIENO_MARRONE (bonus)  
│   └── enums/                   // Enum utilizzati nel dominio  
│       ├── ColoreMerce.java     // Enum per i colori della merce: ROSSA, GIALLA, VERDE, BLU  
│       └── TipoTubo.java        // Enum per i tipi di tubo (se necessario)  
├── componenti/                  // Componenti della nave (in italiano)  
│   ├── AbstractComponent.java   // Classe base per tutti i componenti della nave  
│   ├── TubiFognari.java         // Componente: Tubi fognari  
│   ├── TrivellaAlPlasma.java    // Componente: Trivella al plasma  
│   ├── Scaldabagni.java         // Componente: Scaldabagni  
│   ├── UnitaAbitative.java      // Componente: Unità abitative standard  
│   ├── UnitaAbitativeCentrale.java  // Componente: Unità abitative centrale  
│   ├── ScompartoDiStoccaggio.java   // Componente: Scomparto di stoccaggio (ROSSO/BLU)  
│   ├── SovrastrutturaAliena.java     // Componente: Sovrastruttura aliena  
│   └── GeneratoreDiScudi.java   // Componente: Generatore di scudi  
├── cartaAvventura/              // Carte avventura che influenzano il gioco  
│   ├── AbstractCarta.java       // Classe base per tutte le carte avventura  
│   ├── PianetiCarta.java        // Carta: Pianeti  
│   ├── StazioneAbbandonataCarta.java  // Carta: Stazione abbandonata  
│   ├── NaveAbbandonataCarta.java      // Carta: Nave abbandonata  
│   ├── ContrabbandieriCarta.java       // Carta: Contrabbandieri  
│   ├── SpazioApertoCarta.java          // Carta: Spazio aperto  
│   ├── PioggiaDiMeteoritiCarta.java    // Carta: Pioggia di meteoriti  
│   ├── ZonaDiGuerraCarta.java          // Carta: Zona di guerra  
│   ├── PolvereStellareCarta.java       // Carta: Polvere stellare  
│   ├── PiratiCarta.java                // Carta: Pirati  
│   ├── SchiavistiCarta.java            // Carta: Schiavisti  
│   ├── EpidemiaCarta.java              // Carta: Epidemia  
│   └── SabotaggioCarta.java            // Carta: Sabotaggio  
├── controllers/                 // Gestione del flusso di gioco e interazione utente  
│   ├── GameController.java      // Coordina l’avvio ed evoluzione della partita  
│   ├── BuildController.java     // Gestisce la costruzione/configurazione della nave  
│   ├── CartaController.java     // Gestisce la distribuzione e l’applicazione delle carte  
│   └── UIController.java        // Gestisce l’interfaccia testuale (prompt)  
├── services/                    // Logica di business e operazioni complesse  
│   ├── ScoreService.java        // Calcola punteggi e gestisce penalità  
│   ├── EventService.java        // Gestisce eventi casuali e programmati (es. volo)  
│   ├── BuildService.java        // Genera ed estrae randomicamente i componenti della nave; gestisce componenti prenotati e scartati  
│   ├── CartaService.java        // Genera ed estrae randomicamente le carte avventura  
│   └── DistruzioneComponentiService.java  // Funzione complessa per distruggere i componenti della nave (accesso agli attributi della nave)  
└── util/                        // Utility comuni per funzioni generiche, logging, file e numeri casuali  
    ├── Util.java                // Funzioni generiche di supporto  
    ├── LoggerUtil.java          // Gestione del logging  
    ├── FileUtil.java            // Operazioni di salvataggio/caricamento dati  
    └── RandomUtil.java          // Genera numeri casuali per dadi, eventi e estrazioni  
