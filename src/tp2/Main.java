package tp2;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {
	private static String input_file = "input.txt";
	private static int size = 200;
	private static int step, stepl;
	private static boolean corte;
	private static double auxValues[];
	private static int valuesIdx = 0;
	
	//input formato: cantidad de particulas, particula n + posicion + direccion
	//outputs formato: cantidad de particulas + posicion, nombre del archivo
	//output_t=n.xyz 
	public static void main(String[] args) throws IOException {
		List<Particle> particles = null;
		FileParser fp = new FileParser();
		auxValues = new double[500];
		
		short[][] nodes;
		short[][] auxNodes = new short [size][];
		
		try {
	    	particles = fp.getParticles(input_file);            
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
		}
				
		LatticeGasSimulator fluidSimulator = new LatticeGasSimulator();
		nodes = NodeManager.assignNodes(size, particles, 50);
		
		
		for (int i = 0; i < 2000; i++) {
			writeParticlesToFile(particles, nodes, fp.getN(), size);
		    nodes = fluidSimulator.getFutureNodes(nodes, size);		        
		}

		
		
//		try (Writer writer3 = new BufferedWriter(new OutputStreamWriter( new FileOutputStream("tiempoCorte20001.txt"), "utf-8"))) {
//		
//			corte = false;
//			step = 0;
//			for (int k = 0; k < size; k++) {
//				auxNodes[k] = nodes[k].clone();        
//			}
//			try (Writer writer1 = new BufferedWriter(new OutputStreamWriter( new FileOutputStream("fpa4.txt"), "utf-8"))) {
//			try (Writer writer2 = new BufferedWriter(new OutputStreamWriter( new FileOutputStream("fpb4.txt"), "utf-8"))) {
//				for (int i = 0; i < 10000; i++) {
//					writeParticleFractionFile(particles, auxNodes, size, writer1, writer2, writer3);
//				    auxNodes = fluidSimulator.getFutureNodes(auxNodes, size);		
//				}
//			} 	
//			}
//
//		
//		}
				
	}
	
	public static void writeParticlesToFile(List<Particle> particles, short[][] nodes, int n, int size) throws UnsupportedEncodingException, FileNotFoundException, IOException {
		
		try (Writer writer = new BufferedWriter(new OutputStreamWriter( new FileOutputStream("output_t" + step + ".txt"), "utf-8"))) {
			writer.write(String.valueOf(nodes.length * nodes[0].length) + "\n" + "\n");
	        for (int i = 0; i < size ; i++) {
	            for (int j = 0; j < size ; j++) {
	            		int dirCount = (nodes[i][j] & 0x01) == 0x01 ? 1:0 + (nodes[i][j] & 0x02) == 0x02 ? 1:0;
	            		dirCount +=	(nodes[i][j] & 0x04) == 0x04 ? 1:0 + (nodes[i][j] & 0x08) == 0x08 ? 1:0; 
	            		dirCount += (nodes[i][j] & 0x10) == 0x10 ? 1:0 + (nodes[i][j] & 0x20) == 0x20 ? 1:0;

	            		writer.write(String.valueOf(j) + " " + String.valueOf(i) + " " + String.valueOf(nodes[i][j]) + " " + String.valueOf(dirCount) + "\n");
	            }
	        } 		    		
		}			    	
	    step++;
	}
	
	public static void writeParticleFractionFile(List<Particle> particles, short[][] nodes, int size, Writer w1, Writer w2, Writer w3) throws UnsupportedEncodingException, FileNotFoundException, IOException {
		
		double n = 0, a = 0, b = 0, fpa = 0, fpb = 0;
		
	    for (int i = 0; i < size ; i++) {
	        for (int j = 0; j < size ; j++) {
	        		int dirCount = (nodes[i][j] & 0x01) == 0x01 ? 1:0 + (nodes[i][j] & 0x02) == 0x02 ? 1:0;
	        		dirCount +=	(nodes[i][j] & 0x04) == 0x04 ? 1:0 + (nodes[i][j] & 0x08) == 0x08 ? 1:0; 
	        		dirCount += (nodes[i][j] & 0x10) == 0x10 ? 1:0 + (nodes[i][j] & 0x20) == 0x20 ? 1:0;
	        		        		
	        		n += dirCount;
	        		
	        		if (j >= size/2) {
	            		b += dirCount;
	            	} else {
	            		a += dirCount;
	            	}
	        }
	    } 	

	    fpa = a/n;
	    fpb = b/n;
		w1.write(String.valueOf(step) + "\t" + String.valueOf(fpa) + "\n");
		w2.write(String.valueOf(step) + "\t" + String.valueOf(fpb) + "\n");
//		w1.write(String.valueOf(fpa) + "\n");
//		w2.write(String.valueOf(fpb) + "\n");
	    
		
		auxValues[valuesIdx++] = fpa;
		
		
		if (valuesIdx == 500)
			valuesIdx = 0;
		
		
		if (calculateSD(auxValues) < 0.01 && !corte) {
    		w3.write(String.valueOf(step) + " ");
			System.out.println(step);	
			corte = true;
		}
		
//	    // Condicion de corte 
//	    if (fpa <= 0.5 && corte % 2 == 0) {
//	    	System.out.println(step);
//	    	corte++;
//	    }
//    
//	    if (fpa >= 0.5 && corte % 2 != 0) {
//	    	corte++;
//	    	if (corte == 4) {
//	    		w3.write(String.valueOf(step) + " ");
//    			System.out.println(step);
//	    	}
//	    }
	    step++;
	}
	
    public static double calculateSD(double numArray[]) {
        double sum = 0.0, standardDeviation = 0.0;
        int length = numArray.length;

        for(double num : numArray) {
            sum += num;
        }

        double mean = sum/length;

        for(double num: numArray) {
            standardDeviation += Math.pow(num - mean, 2);
        }

        return Math.sqrt(standardDeviation/length);
    }
}