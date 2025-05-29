package controller.partita;


import model.nave.TipoNave;
import model.planciaDiVolo.TipoPlancia;

public enum LivelliPartita {
    LIVELLO_1(1, new int[]{2, 0, 0}, TipoNave.NAVE_1, TipoPlancia.LIVELLO_1), 
    LIVELLO_2(2, new int[]{1, 2, 0}, TipoNave.NAVE_2, TipoPlancia.LIVELLO_2), 
    LIVELLO_3(3, new int[]{1, 1, 2}, TipoNave.NAVE_3, TipoPlancia.LIVELLO_3);

    private final int numeroLivello;
    private final int[] cartePerLivello;
    private final TipoNave tipoNave;
    private final TipoPlancia tipoPlancia;

    LivelliPartita(int numeroLivello, int[] cartePerLivello, TipoNave tipoNave, TipoPlancia tipoPlancia) {
        this.numeroLivello = numeroLivello;
        this.cartePerLivello = cartePerLivello;
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

	/**
	 * esempio: per se voglio leggere il numero di carte di livello 2 in una partita di livello 3 devo fare:
	 * LivelliPartita.Livello_3.getCartePerLivllo(LivelliPartita.Livello_2);
	 */
	public int getCartePerLivello(LivelliPartita lvl) {
		return cartePerLivello[lvl.ordinal()];
	}
	
}
