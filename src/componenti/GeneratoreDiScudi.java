package componenti;

import java.util.Map;

import model.enums.Direzione;
import model.enums.TipoTubo;

public class GeneratoreDiScudi extends Componente {


    private static int istanze = 0;
    private boolean statoScudo;
    private Direzione direzione1;
    private Direzione direzione2;



    public GeneratoreDiScudi(Map<Direzione, TipoTubo> tubiIniziali, Direzione d1, Direzione d2) {

        //
        super(TipoComponente.SCUDO, tubiIniziali);
        this.statoScudo = false;
        this.direzione1 = d1;
        this.direzione2 = d2;
    }

    public GeneratoreDiScudi(GeneratoreDiScudi g) { // costruttore di copia

        //TODO decrementare istanze
        super(g);
        this.statoScudo = g.statoScudo;
        this.direzione1 = g.direzione1;
        this.direzione2 = g.direzione2;
    }

    //TOOD crea copia 

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

    private void setDirezione1(Direzione d1) {
        this.direzione1 = d1;
    }

    private void setDirezione2(Direzione d2) {
        this.direzione2 = d2;
    }


    @Override
    public void ruota() {

        //TODO ruotano i tubi e le direzioni degli scudi
        
    }
    
}



