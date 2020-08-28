package tp2;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import ss.Particle;

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
            int i = (int) p.getY();
            int j = (int) p.getX();

            short dir = p.getVelocity();
            cells[i][j] |=  dir;
//            Set<Particle> currentCell = cells[i][j];
            p.setYIndex(i);
            p.setXIndex(j);
//            currentCell.add(p);
        }
        
        return cells;
				
	}

}
