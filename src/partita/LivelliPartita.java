package partita;


import model.nave.TipoNave;
import model.planciaDiVolo.TipoPlancia;

public enum LivelliPartita {
    LIVELLO_1( 1, TipoNave.NAVE_1, TipoPlancia.LIVELLO_1), 
    LIVELLO_2( 2, TipoNave.NAVE_2, TipoPlancia.LIVELLO_2), 
    LIVELLO_3( 3, TipoNave.NAVE_3, TipoPlancia.LIVELLO_3);
	
	private final int numeroLivello;
	private final TipoNave tipoNave;
	private final TipoPlancia tipoPlancia;
	
	
	LivelliPartita(int numeroLivello, TipoNave tipoNave, TipoPlancia tipoPlancia) {
		this.numeroLivello = numeroLivello;
		this.tipoNave = tipoNave;
		this.tipoPlancia = tipoPlancia;
	}
	
	public int getNumeroLivello() {
		return numeroLivello;
	}
	public TipoNave getTipoNave() {
		return tipoNave;
	}
	public TipoPlancia getTipoPlancia() {
		return tipoPlancia;
	}
	
}
