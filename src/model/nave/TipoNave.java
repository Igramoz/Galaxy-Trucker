package model.nave;

import util.Coordinate;

public enum TipoNave {
	NAVE_1(new boolean[][] {
	    { false, false, false, false, false, false, false, false, false, false, false },
	    { false, false, false, false, false, false, false, false, false, false, false },
	    { false, false, false, false, false, false, false, false, false, false, false },
	    { false, false, false, false, false,  true,  true,  true, false, false, false },
	    { false, false, false, false,  true,  true,  true,  true, false, false, false },
	    { false, false, false,  true,  true,  true,  true, false, false, false, false },
	    { false, false, false, false,  true,  true,  true,  true, false, false, false },
	    { false, false, false, false, false,  true,  true,  true, false, false, false },
	    { false, false, false, false, false, false, false, false, false, false, false },
	    { false, false, false, false, false, false, false, false, false, false, false },
	    { false, false, false, false, false, false, false, false, false, false, false },
	    { false, false, false, false, false, false, false, false, false, false, false }
	}),

	NAVE_2(new boolean[][] {
	    { false, false, false, false, false, false, false, false, false, false, false },
	    { false, false, false, false, false, false, false, false, false, false, false },
	    { false, false, false, false, false,  true,  true,  true, false, false, false },
	    { false, false, false, false,  true,  true,  true,  true, false, false, false },
	    { false, false, false,  true,  true,  true,  true,  true, false, false, false },
	    { false, false, false, false,  true,  true,  true, false, false, false, false },
	    { false, false, false,  true,  true,  true,  true,  true, false, false, false },
	    { false, false, false, false,  true,  true,  true,  true, false, false, false },
	    { false, false, false, false, false,  true,  true,  true, false, false, false },
	    { false, false, false, false, false, false, false, false, false, false, false },
	    { false, false, false, false, false, false, false, false, false, false, false },
	    { false, false, false, false, false, false, false, false, false, false, false }
	}),

	NAVE_3(new boolean[][] {
	    { false, false, false, false, false, false, false, false, false, false, false, false },
	    { false, false, false, false,  true,  true,  true,  true, false, false, false, false },
	    { false, false, false, false, false,  true,  true,  true, false, false, false, false },
	    { false, false, false, false,  true,  true,  true, false, false, false, false, false },
	    { false, false, false,  true,  true,  true,  true,  true, false, false, false, false },
	    { false, false,  true,  true,  true,  true,  true,  true, false, false, false, false },
	    { false, false, false,  true,  true,  true,  true,  true, false, false, false, false },
	    { false, false, false, false,  true,  true,  true, false, false, false, false, false },
	    { false, false, false, false, false,  true,  true,  true, false, false, false, false },
	    { false, false, false, false,  true,  true,  true,  true, false, false, false, false },
	    { false, false, false, false, false, false, false, false, false, false, false, false },
	    { false, false, false, false, false, false, false, false, false, false, false, false }
	});


	private final boolean[][] schemaNave;
	private final int capacitaComponenti;

	TipoNave(boolean[][] schemaNave) {
		this.schemaNave = schemaNave;
		capacitaComponenti = trueCounter(schemaNave);
	}

	public boolean[][] getschemaNave() {
		return this.schemaNave;
	}
	
	public int getCapacitaComponenti() {
		return capacitaComponenti;
	}

	public boolean isPosizionabile(Coordinate c) {
		return schemaNave[c.getX()][c.getY()];
	}
	
	private int trueCounter(boolean[][] matrice) {
		 int count = 0;
		    for (int i = 0; i < matrice.length; i++) {
		        for (int j = 0; j < matrice[i].length; j++) {
		            if (matrice[i][j]) {
		                count++;
		            }
		        }
		    }
		    return count;
	}
	
}
