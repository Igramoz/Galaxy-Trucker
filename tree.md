```markdown
src/
├── main/
│   └── Main.java
|
├── eccezioni/
│   ├── ComponenteNonIstanziabileException.java
│   ├── CaricamentoNonConsentitoException.java
│   ├── ComponentePienoException.java
│   ├── ComponenteVuotoException.java
│   └── GiocatoreNonSpostabileException.java
│
├── model/
│   ├── Giocatore.java
│   ├── carte/
│   │   ├── TipoCarta.java
│   │   ├── Carta.java
│   │   ├── StazioneAbbandonata.java
│   │   ├── NaveAbbandonata.java
│   │   ├── SpazioAperto.java
│   │   ├── PioggiaDiMeteoriti.java
│   │   ├── ZonaDiGuerra.java
│   │   ├── PolvereStellare.java
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
│   │   |    ├── Nemico.java
│   │   |    ├── Pirati.java
│   │   |    ├── Schiavisti.java
│   │   |    └── Contrabbandieri.java
│       └── colpi/
│   │        ├── Colpo.java
│   │        └── GestoreColpi.java
│   ├── componenti/
│   │   ├── TipoComponente.java
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
│   │   ├── TipoTubo.java
│   │   └── TipoPedina.java
|   |
|   ├── nave/
│   |	├── Nave.java
│   |	├── TipoNave.java
│   |	├── ValidatorePosizione.java
│   |	├── AnalizzatoreNave.java
│   |	├── GestoreComponenti.java
│   |	├── VerificatoreImpatti.java
│   |	└── Distruttore.java
│	|
|   └── planciaDiVolo/
│   |	├── Plancia.java
│   |	└── TipoPlancia.java
|   |
|   └── titoli/
│   	├── Titolo.java
│   	├── TipoTitolo.java
│   	├── ColoreTitolo.java
│   	├── TrasportatoreSupremo.java
│   	└── Batterista.java
|
├── partita/
│   ├── LivelliPartita.java
│   ├── Partita.java
│   ├── ModalitaGioco.java
│   └── fasiGioco/
│       ├── Inizializzazione.java
│       ├── Volo.java
│       ├── FineVolo.java
│       ├── FineGioco.java
│       ├── composizioneNave/
│       |   ├── ComposizioneNave.java
│       |   └── ManagerTurnoComposizione.java
|       |
│       └── volo/
│           ├── Volo.java
│           └── ManagerVolo.java
│
├── grafica/
│   ├── Colore.java
│   ├── CostantiGrafica.java
│   ├── GraficaConfig.java
│   ├── TextAligner.java
│   └── renderer/
│   |   ├── CarteRenderer.java
│   |   ├── ComponenteRenderer.java
│   |   ├── PlanciaRenderer.java
│   |   └── NaveRenderer.java
│   └── formattatori/
│       ├── FormattatoreGrafico.java
│       └── Formattabile.java
│
├── io/
│   ├── GestoreIO.java
│   └── InterfacciaUtente.java
│
│
├── servizi/
│   ├── ServizioAssemblaggio.java
│   ├── ServizioCarte.java
│   └── SerivizioDadi.java
│
└── util/
    ├── Util.java
    ├── Coppia.java
    └── layout/
    |   ├── Coordinate.java
    |   └── Direzione.java
    └── random/
        ├── Dado.java
        └── RandomUtil.java

```