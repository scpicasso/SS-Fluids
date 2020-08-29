package tp2;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.LinkedList;
import java.util.List;

public class Main {
	private static String input_file = "input.txt";
	private static int size = 200;
	private static int step;
	
	//input formato: cantidad de particulas, particula n + posicion + direccion
	//outputs formato: cantidad de particulas + posicion, nombre del archivo
	//output_t=n.xyz 
	public static void main(String[] args) throws IOException {
		List<Particle> particles = null;
		FileParser fp = new FileParser();

		double start;
		double end;	
		short[][] nodes;
		
		try {
	    	particles = fp.getParticles(input_file);            
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
		}
				
		nodes = NodeManager.assignNodes(size, particles, 50);
		writeParticlesToFile(particles, nodes, fp.getN(), size);
//	    LatticeGasSimulator.computeFHP(nodes, size);
		
	}
	
	public static void writeParticlesToFile(List<Particle> particles, short[][] nodes, int n, int size) throws UnsupportedEncodingException, FileNotFoundException, IOException {
		
		try (Writer writer = new BufferedWriter(new OutputStreamWriter( new FileOutputStream("output_t" + step + ".txt"), "utf-8"))) {
			writer.write(String.valueOf(n) + "\n" + "\n");
			
	        for (int i = 0; i < size ; i++) {
	            for (int j = 0; j < size ; j++) {
	            	if (nodes[i][j] != 0) {
	            		writer.write(String.valueOf(j) + " " + String.valueOf(i) + " " + String.valueOf(nodes[i][j]) + "\n");
	            	}
	            }
	        } 		    		
		}
		
	    	
	    	
	    step++;
	}
}