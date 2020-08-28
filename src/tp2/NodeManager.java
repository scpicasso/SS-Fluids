package tp2;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import ss.Particle;

public class NodeManager {
	List<Particle> particles = null;
	private int size;
	Set<Particle>[][] cells; 
	
	
	public NodeManager (int size, List<Particle> particles) {
		this.particles = particles;
		this.size = size;
		
		cells = new HashSet[size][size]; 
    	
        for (int i = 0; i < size ; i++) {
            for (int j = 0; j < size ; j++) {
            	cells[i][j] = new HashSet<Particle>();
            }
        }  
	}
	
	public void assignNodes() {
		
        for (Particle p : particles){
            int i = (int) p.getY();
            int j = (int) p.getX();
            Set<Particle> currentCell = cells[i][j];
            p.setYIndex(i);
            p.setXIndex(j);
            currentCell.add(p);
        }
		
		
		
		for (Particle p: particles) {
			
		}
		
	}

}
