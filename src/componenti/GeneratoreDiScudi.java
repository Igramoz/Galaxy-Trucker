package componenti;

public class GeneratoreDiScudi extends Componente {

    private boolean statoScudo;

    public GeneratoreDiScudi() {
        super(TipoComponente.SCUDO, true, 0);
    }

    public GeneratoreDiScudi(int energia) {
        super(TipoComponente.SCUDO, true, energia);
    }

    public void AttivaScudo() {
        this.statoScudo = true;
    }

    public void DisattivaScudo() {
        this.statoScudo = false;
    }

    public boolean getAttivo() {
        return statoScudo;
    }



    @Override
    public void consumaEnergia() {
        if (getRichiestaEnergia() && getMaxEnergia() > 0) {
            setMaxEnergia(getMaxEnergia() - 1);
        }
    }

    @Override
    public boolean equals(Componente other) {
        if (getTipo() == other.getTipo()) {
            return true;
        }
        return false;
    }
    
}
