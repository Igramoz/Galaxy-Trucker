package model;

//import della costante size

import util.Util;

public class Coordinate {
	public static final int MIN_COORDINATA = 0;
	public static final int MAX_COORDINATA = Util.SIZE;
	
	
    private final int x;
    private final int y;

    public Coordinate(int x, int y) {
    	
    	//controllo se le coordinate sono comprese tra 0 e 9 (compresi)
    	
    	if((x < MIN_COORDINATA || x >= MAX_COORDINATA) || (y<MIN_COORDINATA | y>=MAX_COORDINATA)) {
    		
    		throw new IllegalArgumentException("Le coordinate devono essere comprese tra " + MIN_COORDINATA + "e " + (MAX_COORDINATA - 1));
    	}
    	
        this.x = x;
        this.y = y;
    }
    
    public Coordinate(Coordinate c) {  //costruttore di copia
		this(c.getX(), c.getY());
	}
    

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
        
    }

    @Override
    public boolean equals(Object c) {
        if (this == c) return true;
        if (c == null || !(c instanceof Coordinate)) return false;

        return this.x == ((Coordinate) c).x && this.y == ((Coordinate) c).y;
    }
}
