package partita;

public enum ModalitaGioco {
	VOLO_SINGOLO,
	TRASVOLATA_INTERGALATTICA;
	
	LivelloPartita livelloPartita;
	
	public LivelloPartita getlivelloPartita() {
		return livelloPartita;
	}
	
	public void setlivelloPartita(LivelloPartita livelloPartita) {
		this.livelloPartita = livelloPartita;
	}
}
