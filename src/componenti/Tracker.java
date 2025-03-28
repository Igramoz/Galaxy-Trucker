package componenti;

import model.Nave;
import model.Coordinate;
import java.util.HashSet;
import java.util.Set;

public class Tracker {

    // Variabili per tenere traccia dello stato
    private Nave nave;
    private int turno;
    private int pezziDistruttiTotali;
    private Set<Coordinate> coordinateDistrutte;

    // Costruttore
    public Tracker(Nave nave) {
        this.nave = nave;
        this.turno = 0;
        this.pezziDistruttiTotali = 0;
        this.coordinateDistrutte = new HashSet<>();
    }

    // Aumenta il turno e aggiorna lo stato del gioco
    public void avanzamentoTurno() {
        turno++;
    }

    // Tiene traccia dei pezzi distrutti
    public void aggiornaPezziDistrutti(Coordinate coordinate) {
        if (!coordinateDistrutte.contains(coordinate)) {
            pezziDistruttiTotali++;
            coordinateDistrutte.add(coordinate);
        }
    }

    // Verifica se il gioco è finito (ad esempio, tutti i pezzi sono distrutti)
    public boolean isGameOver() {
        return pezziDistruttiTotali >= nave.getGrigliaComponenti().length * nave.getGrigliaComponenti()[0].length;
    }

    // Ottiene il punteggio del giocatore (puoi personalizzare la logica del punteggio)
    public int getPunteggio() {
        return pezziDistruttiTotali;
    }

    // Metodo per gestire la distruzione di un componente
    public void distruggiComponente(Coordinate coordinate) {
        // Esegui la distruzione del componente sulla nave
        //nave.distruggiSingoloComponente(coordinate); // DA RIMUOVERE LA FUNZIONE CHIAMA ERRORE
        aggiornaPezziDistrutti(coordinate);
    }

    // Ritorna il turno attuale del gioco
    public int getTurno() {
        return turno;
    }

    // Ritorna il numero totale di pezzi distrutti
    public int getPezziDistruttiTotali() {
        return pezziDistruttiTotali;
    }

    // Ritorna le coordinate dei componenti distrutti
    public Set<Coordinate> getCoordinateDistrutte() {
        return coordinateDistrutte;
    }
}
