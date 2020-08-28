package tp2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

import ss.Particle;

import java.util.List;

public class FileParser {

	
	
	private int particleCount;
	private int lengthSize;
	private LinkedList<Particle> particles;


	public FileParser() {
		this.particles = new LinkedList<Particle>();
	}
	
    public List<Particle> getParticles(String filePath) throws FileNotFoundException {
        
    	FileInputStream fis = new FileInputStream(filePath);  
        Scanner sc = new Scanner(fis);
        particleCount = sc.nextInt();
//        lengthSize = sc.nextInt();
        for (int i = 0; i < particleCount; i++) {
        	double x = sc.nextDouble();
        	double y = sc.nextDouble();
        	short velocity = (short) sc.nextInt();
            Particle particle = particles.poll();
            particle.setX(x);
            particle.setY(y);

            particles.add(new Particle(i, x, y, velocity));
        }    
        
		return particles;
       
	}

	public int getL() {
		return lengthSize;
	}
	
	public int getN() {
		return particleCount;
	}
}
