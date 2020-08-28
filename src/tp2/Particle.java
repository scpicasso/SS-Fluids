package tp2;

import java.util.HashSet;
import java.util.Set;

public class Particle implements Comparable<Particle> {
	
    private double x;
    private double y;
    private double radius;
    private int property;
    private short velocity;

    private int xIndex;
    private int yIndex;
	private int id;
	private Set<Particle> neighbours;

    public Particle(int id, double x, double y, double radius, int property) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.property = property;
        this.neighbours = new HashSet<Particle>();
    }
    
    Particle(int id, double radius, int property) {
        this.id = id;
        this.radius = radius;
        this.property = property;
        this.neighbours = new HashSet<Particle>();
    }
    
    
    Particle(int id, double x, double y, short velocity) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.velocity = velocity;
//        this.neighbours = new HashSet<Particle>();
    }
    
    public  double getDistanceTo(Particle p) {
        return Math.sqrt(Math.pow(x - p.getX(), 2) +
                Math.pow(y - p.getY(), 2)) - this.radius - p.getRadius();
    }
    
    public double getPeriodicDistanceTo(Particle p, int L) {

        double deltaX = Math.abs(this.x - p.x);
        if (deltaX > L / 2)
        	deltaX = L - deltaX;

        double deltaY = Math.abs(this.y - p.y);
        if (deltaY > L / 2)
        	deltaY = L - deltaY;

        return Math.sqrt(Math.pow(deltaX,2) + Math.pow(deltaY,2));
    }
    
    public void addNeighbour(Particle neighbour) {
        this.neighbours.add(neighbour);
    }
    
    public short getVelocity() {
        return velocity;
    }
    
    public double getRadius() {
        return radius;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getProperty() {
        return this.property;
    }

    public void setX(double x){
        this.x = x;
    }

    public void setY(double y){
        this.y = y;
    }

    public int getXIndex() {
        return xIndex;
    }

    public int getYIndex() {
        return yIndex;
    }

    public void setXIndex(int xIndex) {
        this.xIndex = xIndex;
    }

	public void setYIndex(int yIndex) {
        this.yIndex = yIndex;
    }

	public int getId() {
		return this.id;
	}
	
	public Set<Particle> getNeighbours() {
		return this.neighbours;
	}
	
    @Override
    public boolean equals(Object o) {
        if (this == o) {
        	return true;
        }
        if (o == null || getClass() != o.getClass()) {
        	return false;
        }

        Particle particle = (Particle) o;

        return id == particle.id;
    }
    
    @Override
    public int hashCode() {
        return id;
    }

	@Override
	public int compareTo(Particle p) {
		return this.id - p.getId();
	}

	@Override
	public String toString () {
		String ret = " " + this.id;
//		ret += this.id;
		return ret;
	}

	public int getColor() {
		return this.property;
	}

	public void setColor(int color) {
		this.property = color;
		
	}

}
