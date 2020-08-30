package tp2;

public class LatticeGasSimulator {
	
	static final short A = 0x01;
	static final short B = 0x02;
	static final short C = 0x04;
	static final short D = 0x08;
	static final short E = 0x10;
	static final short F = 0x20;
	static final short S = 0x40;
	static final short R = 0x80;

	static short[] direction = {A, B, C, D, E, F};
	short[] collision_system;
	
	public LatticeGasSimulator() {
		collision_system = new short[256];
		

		//no change in configuration
		for(int i = 0; i < 64; i++) {
			collision_system[i] = (short) i;
		}

		//solid, bounce back
		for(int i = 64; i < 128; i++) {
			short m = S;
			for(short k: direction) {
				if((i & k) != 0) {
					short bounce = bounceBack(k);
					m = (short) (m | bounce);
				}
			}
			collision_system[i] = m;
		}

		//no change in configuration
		for(int i = 128; i < 192; i++) {
			collision_system[i] = (short) i;
		}

		//solid, bounce back
		for(int i = 192; i < 256; i++) {
			short m = S;
			for(short k: direction) {
				if((i & k) != 0) {
					short bounce = bounceBack(k);
					m = (short) (m | bounce);
				}
			}
			collision_system[i] = m;
		}

		collision_system[A+D] = (short) (B+E);
		collision_system[A+D+R] = (short) (C+F+R);
		collision_system[B+E] = (short) (A+D);
		collision_system[B+E+R] = (short) (C+F+R);
		collision_system[C+F] = (short) (B+E);
		collision_system[C+F+R] = (short) (A+D+R);

		collision_system[A+C+E] = (short) (B+D+F);
		collision_system[A+C+E+R] = (short) (B+D+F+R);
		collision_system[B+D+F] = (short) (A+C+E);
		collision_system[B+D+F+R] = (short) (A+C+E+R);

		collision_system[A+B+D+E] = (short) (B+C+E+F);
		collision_system[A+B+D+E+R] = (short) (A+C+D+F+R);
		collision_system[A+C+D+F] = (short) (A+B+D+E);
		collision_system[A+C+D+F+R] = (short) (B+C+E+F+R);
		collision_system[B+C+E+F] = (short) (A+B+D+E);
		collision_system[B+C+E+F+R] = (short) (A+C+D+F+R);
		
		collision_system[A+C+E+R] = (short) (B+D+F+R);
		collision_system[B+D+F] = (short) (A+C+E);
		collision_system[B+D+F+R] = (short) (A+C+E+R);			
	}	
	
	public short[][] getFutureNodes(short[][] currentNodes, int size) {
		
		short[][] futureNodes = new short[size][size]; 
		
        for (int i = 0; i < size ; i++) {
            for (int j = 0; j < size ; j++) {
            	
            	Short delta = collision_system[currentNodes[i][j]];
            	
            	// S
            	futureNodes[i][j] |= (delta & S);
            	// R
            	futureNodes[i][j] |= (delta & R);
            	
            	// Nothing to check
            	if (delta == 0) {
            		continue;
            	}
            	
            	if (j < size - 1) {
                	// A
                	futureNodes[i][j + 1] |= (delta & A);            		
            	}

            	
            	if (i > 0) {
                	// B
                	if (i % 2 == 0) {
                		if (j < size - 1) 
                			futureNodes[i - 1][j + 1] |= (delta & B); 
                	} else {
                		futureNodes[i - 1][j] |= (delta & B); 
                	}
                	           	
                	// C
                	if (i % 2 == 0) {
                		futureNodes[i - 1][j] |= (delta & C); 
                	} else {
                		if (j > 0)
                			futureNodes[i - 1][j - 1] |= (delta & C); 
                	}            		
            	}

            	if (j > 0) {
                	// D
                	futureNodes[i][j - 1] |= (delta & D);            		
            	}
            	
            	if (i < size - 1) {                	           	
                	// E
                	if (i % 2 == 0) {
                		futureNodes[i + 1][j] |= (delta & E); 
                	} else {
                		if (j > 0)
                			futureNodes[i + 1][j - 1] |= (delta & E); 
                	}            	
                	
                	// F
                	if (i % 2 == 0) {
                		if (j < size - 1) 
                			futureNodes[i + 1][j + 1] |= (delta & F); 
                	} else {
                		futureNodes[i + 1][j] |= (delta & F); 
                	}
            	}
            	    
            }
        } 
		
        return futureNodes;
	}
	
	private short bounceBack(short direction) {
		short ret = 0;
		switch(direction) {
			case A: 
				return D;
			case B: 
				return E;
			case C:
				return F;
			case D:
				return A;
			case E:
				return B;
			case F:
				return C;
		}
		return ret;
	}


}
