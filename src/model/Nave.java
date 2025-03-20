package model;

import java.util.Arrays;
import util.Util;

public class Nave {
    
    private Componente[][] grigliaComponenti; 
    private Componente[] componentiPrenotati;
    private int pezziDistrutti; 
    
    // Costruttore
    public Nave() {
        grigliaComponenti = new Componente[Util.SIZE][Util.SIZE];
        componentiPrenotati = new Componente[2];
        pezziDistrutti = 0;
    }
    
    // Metodi griglia componenti
    public Componente[][] getGrigliaComponenti() {
        Componente[][] copiaGrigliaComponenti = new Componente[Util.SIZE][Util.SIZE];
        for (int x = 0; x < Util.SIZE; x++) {
            for (int y = 0; y < Util.SIZE; y++) {
                if (grigliaComponenti[x][y] != null) {
                    copiaGrigliaComponenti[x][y] = new Componente(grigliaComponenti[x][y]);
                }
            }
        }       
        return copiaGrigliaComponenti;
    }
    
    public Componente getComponente(Coordinate coordinate) {
        return grigliaComponenti[coordinate.getX()][coordinate.getY()];
    }
    
    public void setComponente(Componente componente, Coordinate coordinate) {
        grigliaComponenti[coordinate.getX()][coordinate.getY()] = componente;
    }
    
    // Metodi componenti prenotati
    public boolean setComponentiPrenotati(Componente componente) {
        for (int i = 0; i < componentiPrenotati.length; i++) {
            if (componentiPrenotati[i] == null) {
                componentiPrenotati[i] = componente;
                return true;
            }
        }
        return false;
    }
    
    public Componente[] getComponentiPrenotati() {
        return Arrays.copyOf(componentiPrenotati, componentiPrenotati.length);
    }
    
    public boolean rimuoviComponentiPrenotati(Componente componente) {
        for (int i = 0; i < componentiPrenotati.length; i++) {
            if (componente.equals(componentiPrenotati[i])) {
                componentiPrenotati[i] = null;
                return true;
            }
        }
        return false;
    }
    
    // Metodo pezzi distrutti
    public void incrementaPezziDistrutti() {
        pezziDistrutti++;
    }
    
    public void distruggiSingoloComponente(Coordinate coordinate) {
        if (grigliaComponenti[coordinate.getX()][coordinate.getY()] != null) {
            grigliaComponenti[coordinate.getX()][coordinate.getY()] = null;
            incrementaPezziDistrutti();
        }
    }
}