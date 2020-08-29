package tp2;

public class LatticeGasSimulator {
	
	public static int computeFHP(short[][] currentNodes, int size) {
		
		short[][] futureNodes = new short[size][size]; 
		
        for (int i = 0; i < size ; i++) {
            for (int j = 0; j < size ; j++) {
            	
            	short delta = 0; // computeCollision(currentNodes[i][j]);
            	
            	// A
            	futureNodes[i][j + 1] |= (currentNodes[i][j] & 1) | (delta & 1);
            	
            	// B
            	if (i % 2 == 0) {
            		futureNodes[i - 1][j + 1] |= (currentNodes[i][j] & 2) + delta; 
            	} else {
            		futureNodes[i - 1][j] |= (currentNodes[i][j] & 2) + delta; 
            	}
            	           	
            }
        } 
		
        return 0;
	}

}
