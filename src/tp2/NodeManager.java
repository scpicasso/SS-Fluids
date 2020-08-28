package tp2;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class NodeManager {
//	List<Particle> particles = null;
//	private int size;
//	short[][] cells; 
	
	
//	public NodeManager (int size, List<Particle> particles) {
//		this.particles = particles;
//		this.size = size;		
//		cells = new short[size][size];     	
//	}
	
	public static short[][] assignNodes(int size, List<Particle> particles) {
		
		short[][] cells = new short[size][size];    
		
        for (Particle p : particles){
            int i = (int) Math.round(p.getY());
            int j = (int) Math.round(p.getX());
            
            short dir = p.getVelocity();
            cells[i][j] |=  dir;
            p.setYIndex(i);
            p.setXIndex(j);
        }
        
        return cells;
				
	}

}
