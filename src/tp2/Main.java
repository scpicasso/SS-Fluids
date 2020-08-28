package tp2;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import ss.FileParser;
import ss.Particle;

public class Main{
	private static String input_file = "input.txt";
	private static int size = 200;

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
//			System.out.println("File not found.");
		}
		
		
		nodes = NodeManager.assignNodes(size, particles);
	    
		
	}
}