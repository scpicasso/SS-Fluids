
public class System{
	static short A = 0x01;
	static short B = 0x02;
	static short C = 0x04;
	static short D = 0x08;
	static short E = 0x10;
	static short F = 0x20;
	static short S = 0x40;
	static short R = 0x80;

	public short[] createSystem() {
		short[] collision_system = new short[256];
		
		//mapping solids presence
		for(int i=64; i<=127; i++) {
			collision_system[i] = i;
		}
		for(int j=192; j<=255; j++) {
			collision_system[j] = j;
		}

		collision_system[A+D] = B+E;
		collision_system[A+D+R] = C+F+R;
		collision_system[B+E] = A+D;
		collision_system[B+E+R] = C+F+R;
		collision_system[C+F] = B+E;
		collision_system[C+F+R] = A+D+R;

		collision_system[A+C+E] = B+D+F;
		collision_system[A+C+E+R] = B+D+F+R;
		collision_system[B+D+F] = A+C+E;
		collision_system[B+D+F+R] = A+C+E+R;

		collision_system[A+B+D+E] = B+C+E+F;
		collision_system[A+B+D+E+R] = A+C+D+F+R;
		collision_system[A+C+D+F] = A+B+D+E;
		collision_system[A+C+D+F+R] = B+C+E+F+R;
		collision_system[B+C+E+F] = A+B+D+E;
		collision_system[B+C+E+F+R] = A+C+D+F+R;
		
		collision_system[A+C+E+R] = B+D+F+R;
		collision_system[B+D+F] = A+C+E;
		collision_system[B+D+F+R] = A+C+E+R;	

		return collision_system;
	}

	public bounceBack(short direction) {
		switch(direction) {
			case A: 
				return D;
			case B: 
				return E;
			case C:
				return F;
			case D:
				return A;
			case E:
				return B;
			case F:
				return C;
		}
	}
}
