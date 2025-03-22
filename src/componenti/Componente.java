package componenti;

public abstract class Componente {
	private final TipoComponente tipo;
	private final boolean richiedeEnergia;
	private int maxEnergia;
	// attributo (array forse) per le direzioni possibili?

	public Componente(TipoComponente t, boolean usaEnergia, int energia) {
		tipo = t;
		richiedeEnergia = usaEnergia;
		setMaxEnergia(energia);
	}

	public Componente(Componente com) { // costruttore di copia
		this.tipo = com.tipo;
		this.richiedeEnergia = com.richiedeEnergia;
		this.maxEnergia = com.maxEnergia;
	}

	public TipoComponente getTipo() {
		return tipo;
	}

	public boolean getRichiestaEnergia() {
		return richiedeEnergia;
	}

	public int getMaxEnergia() {
		return maxEnergia;
	}

	public void setMaxEnergia(int maxEnergia) {
		this.maxEnergia = maxEnergia;
	}

	// nei componenti in cui ciò è possibile, la funzione dovrà fare maxEnergia--;,
	// controllando se il tipo può
	// consumare energia e se maxEnergia > 0
	public abstract void consumaEnergia();

	// serve la classe Direzione per gestire le direzioni dei tubi

	public boolean equals(Componente other) {
		/*if (tipo == other.tipo &&) {
			&& controllo direzione uguale di tutti i tubi (manca la classe direzione).
		}*/
		return false;
	}
}