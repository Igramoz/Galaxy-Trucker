package controller.partita;

public enum ModalitaGioco {
	VOLO_SINGOLO,
	TRASVOLATA_INTERGALATTICA;
	
	LivelliPartita livelliPartita;
	
	public LivelliPartita getlivelloPartita() {
		return livelliPartita;
	}
	
	public void setlivelloPartita(LivelliPartita livelliPartita) {
		this.livelliPartita = livelliPartita;
	}
}
