```markdown
src/
├── main/
│   └── Main.java
│
├── model/
│   ├── Giocatore.java
│   ├── Dado.java
│   ├── carte/
│   │   ├── Carta.java
│   │   ├── PianetiCarta.java
│   │   ├── StazioneAbbandonataCarta.java
│   │   ├── NaveAbbandonataCarta.java
│   │   ├── ContrabbandieriCarta.java
│   │   ├── SpazioApertoCarta.java
│   │   ├── PioggiaDiMeteoritiCarta.java
│   │   ├── ZonaDiGuerraCarta.java
│   │   ├── PolvereStellareCarta.java
│   │   ├── PiratiCarta.java
│   │   ├── SchiavistiCarta.java
│   │   ├── EpidemiaCarta.java
│   │   └── SabotaggioCarta.java
│   ├── colpi/
│   │   ├── Cannonata.java
│   │   └── Meteorite.java
│   ├── componenti/
│   │   ├── Componente.java
│   │   ├── Tubo.java
│   │   ├── Cannone.java
│   │   │   └── CannoneDoppio.java
│   │   ├── GeneratoreDiScudi.java
│   │   ├── CabinaEquipaggio.java
│   │   ├── CabinaPartenza.java
│   │   ├── Stiva.java
│   │   │   └── StivaSpeciale.java
│   │   ├── VanoBatteria.java
│   │   ├── ModuloSupportoAlieni.java
│   │   ├── Motore.java
│   │       └── MotoreDoppio.java
│   ├── enums/
│   │   ├── TipoMerce.java
│   │   ├── Direzione.java
│   │   ├── TipoTubo.java
│   │   ├── TipoCarta.java
│   │   ├── TipoComponente.java
│   │   └── TipoPedina.java
|
├── nave/
│   ├── Nave.java
│   ├── TipoNave.java
│   ├── ValidatorePosizione.java
│   ├── VerificatoreImpatti.java
│   └── Distruttore.java
│
├── partita/
│   ├── LivelliPartita.java
│   ├── Partita.java
│   ├── ModalitaGioco.java
│   └── fasiGioco/
│       ├── Inizializzazione.java
│       ├── Volo.java
│       ├── FineVolo.java
│       ├── FineGioco.java
│       └── composizioneNave/
│           ├── ComposizioneNave.java
│           └── ManagerTurnoComposizione.java
│
├── grafica/
│   ├── Colore.java
│   ├── CostantiGrafica.java
│   ├── FormattatoreGrafico.java
│   ├── GraficaConfig.java
│   ├── TextAligner.java
│   └── renderer/
│       ├── CarteRenderer.java
│       ├── ComponenteRenderer.java
│       └── NaveRenderer.java
│
├── io/
│   └── GestoreIO.java
│
├── planciaDiVolo/
│   ├── Plancia.java
│   └── TipoPlancia.java
│
├── servizi/
│   ├── ServizioPunteggio.java
│   ├── ServizioEventi.java
│   ├── ServizioAssemblaggio.java
│   ├── ServizioCarte.java
│   └── ServizioDistruzioneComponenti.java
│
└── util/
    ├── Util.java
    ├── RandomUtil.java
    ├── Coordinate.java
    └── Coppia.java

```