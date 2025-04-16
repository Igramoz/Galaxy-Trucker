package partita.fasiGioco;

// TODO valutare se rimuovere
public enum FaseGioco {
	INIZIALIZZAZIONE, // quanti giocatori? Nomi? Colori? modalità di gioco?
	COMPOSIZIONE_NAVE, // I giocatori compongono la nave
	VOLO, // gioco effettivo
	ASSEGNAZIONE_CREDITI, // vengono assegnati i crediti (bisogna fare in modo che se non si è in
							// trasvolata intergalattica, i crediti vengano assegnati anche per la nave più
							// bella)
	ASSEGNAZIONE_TITOLI, // dopo che vengono assegnati i crediti, ricorda che i crediti vencono assegnati
							// per la nave più bella in seguito ai titoli, si assegnano i titoli,
	FINE; // si annunciano i vincitori, si chiede se si vuole rigiocare
}