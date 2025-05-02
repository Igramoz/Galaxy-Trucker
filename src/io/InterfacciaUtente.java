package io;

import java.util.List;

import model.componenti.Componente;
import util.layout.Coordinate;

public interface InterfacciaUtente {

    // Metodi di stampa
    void stampa(String riga);
    void stampa(String[] righeDaStampare);
    void stampa(List<String> righeDaStampare);
    void aCapo();

    // Input da utente
    int leggiIntero();
    String leggiTesto();
    Coordinate leggiCoordinate();

    // Menu e selezione
    int stampaMenu(String[] menu);
    <T extends Enum<T>> T scegliEnum(Class<T> enumerato);
    Componente menuComponenti(java.util.List<Componente> componenti);

}
