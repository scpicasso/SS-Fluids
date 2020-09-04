package tp2;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class NodeManager {

	public static short[][] assignNodes(int size, List<Particle> particles, int slitWidth) {
		int aux = 0;
		short[][] nodes = new short[size][size];    
		
		for (Particle p : particles){
            int i = (int) Math.round(p.getY());
            int j = (int) Math.round(p.getX());
            
            short dir = p.getVelocity();
            
            if ((nodes[i][j] | dir) == nodes[i][j]) {
            	
            	if (i < size - 1) {
            		i++;
            	} else if (j < size - 1) {
            		j++;
            	}
            		
            } 
            
            nodes[i][j] |=  dir;
            p.setYIndex(i);
            p.setXIndex(j);
            
        }

		// Mark solids
        for (int i = 0; i < size; i++) {
        	nodes[i][0] |= 64;
        	nodes[i][size - 1] |= 64;
        	int mid = (int)size/2 - 1;
        	if (i < size/2 - slitWidth/2 || i >= size/2 + slitWidth/2) {
        		nodes[i][mid] |= 64; //binary for solid	
        	}
        }
        for (int j = 0; j < size; j++) {
        	nodes[0][j] |= 64;
        	nodes[size - 1][j] |= 64;
        }
                        
        return nodes;
				
	}

}
