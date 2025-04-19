package planciaDiVolo;

public enum TipoPlancia {

    LIVELLO_1(18, new int[]{4, 2, 1, 0}), // Posizioni per LIVELLO_1
    LIVELLO_2(24, new int[]{6, 3, 1, 0}), // Posizioni per LIVELLO_2
    LIVELLO_3(34, new int[]{9, 5, 2, 0}); // Posizioni per LIVELLO_3

    private final int lunghezza; // lunghezza della plancia in base alla modalita di gioco 
    private final int[] posizioni; //posizioni iniziali dei giocatori nella plancia // massimo 4 giocatori

    TipoPlancia(int lunghezza, int[] posizioni) {
        this.lunghezza = lunghezza;
        this.posizioni = posizioni;
    }

    public int getLunghezza() {
        return lunghezza;
    }

    public int[] getPosizioni() {
        return posizioni;
    }   

}

/*
 * livello 1
 * 18 spazi
 *
 * 4 3 2 _ 1 _ _ _
 * _             _
 * _ _ _ _ _ _ _ _
 *
 */
/*
 * livello 2
 * 24 spazi
 *
 * 4 3 _ 2 _ _ 1 _ _ _
 * _                 _
 * _                 _
 * _ _ _ _ _ _ _ _ _ _
 *
 */
/*
 * livello 3
 * 34 spazi
 *
 * 4 _ 3 _ _ 2 _ _ _ 1 _ _ _ _ 
 * _                         _
 * _                         _
 * _                         _
 * _ _ _ _ _ _ _ _ _ _ _ _ _ _
 *
 */
