package src;

public class Particle implements Comparable<Particle> {
	
    private double x;
    private double y;
    private short direction;
    private double vx;
    private double vy;
    private int property;

	private int id;

    public Particle(int id, double x, double y, double vx, double vy, short direction, int property) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.direction = direction;
        this.property = property;
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