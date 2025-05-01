```markdown
src/
├── main/
│   └── Main.java
│
├── model/
│   ├── Giocatore.java
│   ├── carte/
│   │   ├── TipoCarta.java
│   │   ├── Carta.java
│   │   ├── CartaPianeti.java
│   │   ├── StazioneAbbandonata.java
│   │   ├── NaveAbbandonataCarta.java
│   │   ├── SpazioAperto.java
│   │   ├── PioggiaDiMeteoriti.java
│   │   ├── ZonaDiGuerra.java
│   │   ├── PolvereStellare.java
│   │   ├── Schiavisti.java
│   │   ├── Epidemia.java
│   │   ├── Sabotaggio.java
│   │   ├── criteriEffetti/
│   │   |    ├── Criterio.java
│   │   |    ├── Effetto.java
│   │   |    └── CriterioConEffetto.java
│   │   ├── pianeti/
│   │   |    ├── CartaPianeti.java
│   │   |    └── Pianeta.java
│   │   |── nemiciAvanzati/
│   │   |    ├── NemicoAvanzato.java
│   │   |    ├── Pirati.java
│   │   |    ├── Schiavisti.java
│   │   |    └── Contrabbandieri.java
│       └── colpi/
│   │        ├── Colpo.java
│   │        └── GestoreColpo.java
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
|   |
|    ├── nave/
│   |		├── Nave.java
│   |		├── TipoNave.java
│   |		├── ValidatorePosizione.java
│   |		├── GestoreComponenti.java
│   |		├── VerificatoreImpatti.java
│   |		└── Distruttore.java
│	 |
|    └── planciaDiVolo/
│   		├── Plancia.java
│   		└── TipoPlancia.java

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