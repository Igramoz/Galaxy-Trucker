package model;

//import della costante size

import util.Util;

public class Coordinate {
    private final int x;
    private final int y;

    public Coordinate(int x, int y) {
    	
    	//controllo se le coordinate sono comprese tra 0 e 10 (compresi)
    	
    	if((x < 0 || x > Util.SIZE) || (y<0 | y>Util.SIZE)) {
    		
    		throw new IllegalArgumentException("Le coordinate devono essere comprese tra [0 e 10]");
    	}
    	
        this.x = x;
        this.y = y;
    }
    

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isEqual(Coordinate c) {
        return x == c.getX() && y == c.getY();
    }
}
