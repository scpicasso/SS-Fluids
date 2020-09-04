package tp2;

import java.io.IOException;
import java.io.Writer;

public class LatticeGasSimulator {
	
	static long t = 0;
	
	static final short A = 0x01;
	static final short B = 0x02;
	static final short C = 0x04;
	static final short D = 0x08;
	static final short E = 0x10;
	static final short F = 0x20;
	static final short S = 0x40;
	static final short R = 0x80;

	static short[] direction = {A, B, C, D, E, F};
	short[] collisionTable;
	
	public LatticeGasSimulator() {
		collisionTable = new short[256];
		

		//no change in configuration
		for(int i = 0; i < 64; i++) {
			collisionTable[i] = (short) i;
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
			collisionTable[i] = m;
		}

		//no change in configuration
		for(int i = 128; i < 192; i++) {
			collisionTable[i] = (short) i;
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
			collisionTable[i] = m;
		}

		collisionTable[A+D] = (short) (B+E);
		collisionTable[A+D+R] = (short) (C+F+R);
		collisionTable[B+E] = (short) (A+D);
		collisionTable[B+E+R] = (short) (C+F+R);
		collisionTable[C+F] = (short) (B+E);
		collisionTable[C+F+R] = (short) (A+D+R);

		collisionTable[A+C+E] = (short) (B+D+F);
		collisionTable[A+C+E+R] = (short) (B+D+F+R);
		collisionTable[B+D+F] = (short) (A+C+E);
		collisionTable[B+D+F+R] = (short) (A+C+E+R);

		collisionTable[A+B+D+E] = (short) (B+C+E+F);
		collisionTable[A+B+D+E+R] = (short) (A+C+D+F+R);
		collisionTable[A+C+D+F] = (short) (A+B+D+E);
		collisionTable[A+C+D+F+R] = (short) (B+C+E+F+R);
		collisionTable[B+C+E+F] = (short) (A+B+D+E);
		collisionTable[B+C+E+F+R] = (short) (A+C+D+F+R);
		
		collisionTable[A+C+E+R] = (short) (B+D+F+R);
		collisionTable[B+D+F] = (short) (A+C+E);
		collisionTable[B+D+F+R] = (short) (A+C+E+R);			
	}	
	
	public short[][] getFutureNodes(short[][] currentNodes, int size) {
		
		short[][] futureNodes = new short[size][size]; 
		
        for (int i = 0; i < size ; i++) {
            for (int j = 0; j < size ; j++) {
                        	
            	Short delta = collisionTable[currentNodes[i][j]];
            	
            	// Nothing to check
            	if (delta == 0) {
            		continue;
            	}
                        	            	
            	// S
            	futureNodes[i][j] |= (delta & S);
            	// R
            	futureNodes[i][j] |= (delta & R);
            	
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
