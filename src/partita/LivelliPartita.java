package partita;


import model.nave.TipoNave;
public enum LivelliPartita {
    LIVELLO_1( 1, TipoNave.NAVE_1), 
    LIVELLO_2( 2, TipoNave.NAVE_2), 
    LIVELLO_3( 3, TipoNave.NAVE_3);
	
	private final int numeroLivello;
	private final TipoNave tipoNave;
	
	
	LivelliPartita(int numeroLivello, TipoNave tipoNave) {
		this.numeroLivello = numeroLivello;
		this.tipoNave = tipoNave;
	}
	
	public int getNumeroLivello() {
		return numeroLivello;
	}
	public TipoNave getTipoNave() {
		return tipoNave;
	}
	
}
