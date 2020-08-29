package tp2;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class NodeManager {

	public static short[][] assignNodes(int size, List<Particle> particles, int slitWidth) {
		
		short[][] nodes = new short[size][size];    
		
        for (int i = 0; i < size; i++) {
        	int mid = (int)size/2 - 1;
        	if (i < size/2 - slitWidth/2 || i >= size/2 + slitWidth/2) {
        		nodes[i][mid] = 64; //binary for solid	
        	}
        }
		
		for (Particle p : particles){
            int i = (int) Math.round(p.getY());
            int j = (int) Math.round(p.getX());
            
            short dir = p.getVelocity();
            nodes[i][j] |=  dir;
            p.setYIndex(i);
            p.setXIndex(j);
        }
                
        return nodes;
				
	}

}
