package model.enums;

import model.Coordinate;

public enum TipoNave {
	LIVELLO_1(new boolean[][] {
		{false, false, false, false, false, false, false, false, false, false, false, false},
		{false, false, false, false, false, false, false, false, false, false, false, false},
		{false, false, false, false, false, false, false, false, false, false, false, false},
		{false, false, false, false, false, true , false, false, false, false, false, false},
		{false, false, false, false, true , true , true , false, false, false, false, false},
		{false, false, false, true , true , true , true , true , false, false, false, false},
		{false, false, false, true , true , true , true , true , false, false, false, false},
		{false, false, false, true , true , false, true , true , false, false, false, false},
		{false, false, false, false, false, false, false, false, false, false, false, false},
		{false, false, false, false, false, false, false, false, false, false, false, false},
		{false, false, false, false, false, false, false, false, false, false, false, false},
	}),
	LIVELLO_2(new boolean[][]{
		{false, false, false, false, false, false, false, false, false, false, false, false},
		{false, false, false, false, false, false, false, false, false, false, false, false},
		{false, false, false, false, false, false, false, false, false, false, false, false},
		{false, false, false, false, true , false, true , false, false, false, false, false},
		{false, false, false, true , true , true , true , true , false, false, false, false},
		{false, false, true , true , true , true , true , true , true , false, false, false},
		{false, false, true , true , true , true , true , true , true , false, false, false},
		{false, false, true , true , true , false, true , true , true , false, false, false},
		{false, false, false, false, false, false, false, false, false, false, false, false},
		{false, false, false, false, false, false, false, false, false, false, false, false},
		{false, false, false, false, false, false, false, false, false, false, false, false},
	}),
	LIVELLO_3(new boolean[][] {
	    {false, false, false, false, false, false, false, false, false, false, false, false},
	    {false, false, false, false, false, false, false, false, false, false, false, false},
	    {false, false, false, false, false, false, false, false, false, false, false, false},
	    {false, false, false, false, false, false, true,  false, false, false, false, false},
	    {false, false, false, false, false, true,  true,  true,  false, false, false, false},
	    {false, false, true,  false, true,  true,  true,  true,  true,  false, true,  false},
	    {false, false, true,  true,  true,  true,  true,  true,  true,  true,  true,  false},
	    {false, false, true,  true,  true,  true,  true,  true,  true,  true,  true,  false},
	    {false, false, true,  true,  false, true,  true,  true,  false, true,  true,  false},
	    {false, false, false, false, false, false, false, false, false, false, false, false},
	    {false, false, false, false, false, false, false, false, false, false, false, false},
	    {false, false, false, false, false, false, false, false, false, false, false, false}
	});
	private final boolean[][] schemaNave;
	
	TipoNave(boolean[][] schemaNave) {
			this.schemaNave = schemaNave;
	}
	
	public boolean[][] getschemaNave() {
		return this.schemaNave;
	}	
	
	public boolean isPosizionabile(Coordinate c) {				
		return schemaNave[c.getX()][c.getY()];
	}
}
