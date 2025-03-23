package componenti;

import model.enums.Direzione;

public class GeneratoreDiScudi extends Componente {

    private boolean statoScudo;
    private Direzione direzione1;
    private Direzione direzione2;



    public GeneratoreDiScudi() {
        super(TipoComponente.SCUDO, true, 0);
    }

    public GeneratoreDiScudi(int energia) {
        super(TipoComponente.SCUDO, true, energia);
    }

    public GeneratoreDiScudi(int energia, Direzione d1, Direzione d2) {
        super(TipoComponente.SCUDO, true, energia);
        this.direzione1 = d1;
        this.direzione2 = d2;
    }

    public GeneratoreDiScudi(GeneratoreDiScudi g) { // costruttore di copia
        super(g);
        this.statoScudo = g.statoScudo;
        this.direzione1 = g.direzione1;
        this.direzione2 = g.direzione2;
    }

    public void attivaScudo() {
        this.statoScudo = true;
    }

    public void disattivaScudo() {
        this.statoScudo = false;
    }

    public boolean getstatoScudo() {
        return statoScudo;
    }

    public Direzione getDirezione1() {
        return direzione1;
    }

    public Direzione getDirezione2() {
        return direzione2;
    }

    public void setDirezione1(Direzione d1) {
        this.direzione1 = d1;
    }

    public void setDirezione2(Direzione d2) {
        this.direzione2 = d2;
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


//TODO controllo sull l'energia non puo diminuire al di sotto dello zero
