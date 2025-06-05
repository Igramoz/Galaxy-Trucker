
# Struttura delle cartelle

```
src/
  ├── controller/
  │   ├── partita/
  │   │   ├── fasiGioco/
  │   │   │   ├── composizioneNave/
  │   │   │   │   ├── ComposizioneNave.java
  │   │   │   │   └── ManagerTurnoComposizione.java
  │   │   │   ├── FineGioco.java
  │   │   │   ├── FineVolo.java
  │   │   │   ├── Inizializzazione.java
  │   │   │   └── volo/
  │   │   │       ├── ManagerDiVolo.java
  │   │   │       └── Volo.java
  │   │   ├── LivelliPartita.java
  │   │   ├── ModalitaGioco.java
  │   │   └── Partita.java
  │   └── servizi/
  │       ├── ServizioAssemblaggio.java
  │       ├── ServizioCarte.java
  │       ├── ServizioDadi.java
  │       └── ServizioTitoli.java
  ├── eccezioni/
  │   ├── CaricamentoNonConsentitoException.java
  │   ├── ComponenteNonIstanziabileException.java
  │   ├── ComponentePienoException.java
  │   ├── ComponenteVuotoException.java
  │   ├── GiocatoreNonSpostabileException.java
  │   └── StringaTroppoLungaException.java
  ├── main/
  │   └── Main.java
  ├── model/
  │   ├── carte/
  │   │   ├── Carta.java
  │   │   ├── colpo/
  │   │   │   ├── Colpo.java
  │   │   │   └── GestoreColpi.java
  │   │   ├── criteriEffetti/
  │   │   │   ├── Criterio.java
  │   │   │   ├── CriterioConEffetto.java
  │   │   │   └── Effetto.java
  │   │   ├── Epidemia.java
  │   │   ├── NaveAbbandonata.java
  │   │   ├── nemici/
  │   │   │   ├── Contrabbandieri.java
  │   │   │   ├── Nemico.java
  │   │   │   ├── Pirati.java
  │   │   │   └── Schiavisti.java
  │   │   ├── pianeti/
  │   │   │   ├── CartaPianeti.java
  │   │   │   └── Pianeta.java
  │   │   ├── PioggiaDiMeteoriti.java
  │   │   ├── PolvereStellare.java
  │   │   ├── Sabotaggio.java
  │   │   ├── SpazioAperto.java
  │   │   ├── StazioneAbbandonata.java
  │   │   ├── TipoCarta.java
  │   │   └── ZonaDiGuerra.java
  │   ├── componenti/
  │   │   ├── cabine/
  │   │   │   ├── CabinaDiEquipaggio.java
  │   │   │   └── CabinaPartenza.java
  │   │   ├── cannoni/
  │   │   │   ├── Cannone.java
  │   │   │   └── CannoneDoppio.java
  │   │   ├── Componente.java
  │   │   ├── Contenitore.java
  │   │   ├── GeneratoreDiScudi.java
  │   │   ├── ModuloSupportoAlieni.java
  │   │   ├── motori/
  │   │   │   ├── Motore.java
  │   │   │   └── MotoreDoppio.java
  │   │   ├── stive/
  │   │   │   ├── Stiva.java
  │   │   │   └── StivaSpeciale.java
  │   │   ├── TipoComponente.java
  │   │   ├── Tubo.java
  │   │   └── VanoBatteria.java
  │   ├── enums/
  │   │   ├── TipoMerce.java
  │   │   ├── TipoPedina.java
  │   │   └── TipoTubo.java
  │   ├── Giocatore.java
  │   ├── nave/
  │   │   ├── AnalizzatoreNave.java
  │   │   ├── Distruttore.java
  │   │   ├── GestoreComponenti.java
  │   │   ├── Nave.java
  │   │   ├── TipoNave.java
  │   │   ├── ValidatorePosizione.java
  │   │   └── VerificatoreImpatti.java
  │   ├── planciaDiVolo/
  │   │   ├── Plancia.java
  │   │   └── TipoPlancia.java
  │   └── titoli/
  │       ├── Batterista.java
  │       ├── CapitanoDaCrociera.java
  │       ├── ColoreTitolo.java
  │       ├── MastroCorridoista.java
  │       ├── MastroIngegnere.java
  │       ├── TipoTitolo.java
  │       ├── Titolo.java
  │       ├── TrasportatoreSupremo.java
  │       └── Xenoquartiermastro.java
  ├── util/
  │   ├── layout/
  │   │   ├── Coordinate.java
  │   │   └── Direzione.java
  │   ├── random/
  │   │   ├── Dado.java
  │   │   └── RandomUtil.java
  │   └── Util.java
  └── view/
      ├── Colore.java
      ├── CostantiGrafica.java
      ├── formattatori/
      │   ├── Formattabile.java
      │   └── FormattatoreGrafico.java
      ├── GraficaConfig.java
      ├── io/
      │   ├── GestoreIO.java
      │   └── InterfacciaUtente.java
      ├── renderer/
      │   ├── CarteRenderer.java
      │   ├── ComponenteRenderer.java
      │   ├── NaveRenderer.java
      │   ├── PlanciaRenderer.java
      │   └── TitoliRenderer.java
      └── TextAligner.java
```
