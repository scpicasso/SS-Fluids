//package tp2;
//
//public class System{
//	static short A = 0x01;
//	static short B = 0x02;
//	static short C = 0x04;
//	static short D = 0x08;
//	static short E = 0x10;
//	static short F = 0x20;
//	static short S = 0x40;
//	static short R = 0x80;
//
//	static short[] direction = {A,B,C,D,E,F};
//
//	static final double sqr = Math.sqrt(3)/2;
//
//	//  E   F     |   4   5
//    //   \ /      |    \ /
//    // D--O--A    |  3--O--0
//    //   / \      |    / \
//    //  C   B     |   2   1
//
//    double velocities[][] = 
//    {
//    	{1.0, 0.0},
//    	{0.5, sqr},
//    	{-0.5,sqr},
//    	{-1.0, 0.0},
//    	{-0.5, -sqr},
//    	{0.5, -sqr}
//    };
//
//	public short[] createSystem() {
//		short[] collision_system = new short[256];
//		
//
//		//no change in configuration
//		for(int i = 0; i < 64; i++) {
//			collision_system[i] = (short) i;
//		}
//
//		//solid, bounce back
//		for(int i = 0; i < 128; i++) {
//			short m = S;
//			for(short k: positions) {
//				if(i & k != 0) {
//					m = m | bounceBack(k);
//				}
//			}
//			collision_system[i] = m;
//		}
//
//		//no change in configuration
//		for(int i = 0; i < 192; i++) {
//			collision_system[i] = (short) i;
//		}
//
//		//solid, bounce back
//		for(int i = 192; i < 256; i++) {
//			short m = S;
//			for(short k: positions) {
//				if(i & k != 0) {
//					m = m | bounceBack(k);
//				}
//			}
//			collision_system[i] = m;
//		}
//
//		collision_system[A+D] = (short) (B+E);
//		collision_system[A+D+R] = (short) (C+F+R);
//		collision_system[B+E] = (short) (A+D);
//		collision_system[B+E+R] = (short) (C+F+R);
//		collision_system[C+F] = (short) (B+E);
//		collision_system[C+F+R] = (short) (A+D+R);
//
//		collision_system[A+C+E] = (short) (B+D+F);
//		collision_system[A+C+E+R] = (short) (B+D+F+R);
//		collision_system[B+D+F] = (short) (A+C+E);
//		collision_system[B+D+F+R] = (short) (A+C+E+R);
//
//		collision_system[A+B+D+E] = B+C+E+F;
//		collision_system[A+B+D+E+R] = A+C+D+F+R;
//		collision_system[A+C+D+F] = A+B+D+E;
//		collision_system[A+C+D+F+R] = B+C+E+F+R;
//		collision_system[B+C+E+F] = A+B+D+E;
//		collision_system[B+C+E+F+R] = A+C+D+F+R;
//		
//		collision_system[A+C+E+R] = B+D+F+R;
//		collision_system[B+D+F] = A+C+E;
//		collision_system[B+D+F+R] = A+C+E+R;	
//
//		return collision_system;
//	}
//
//	public short bounceBack(short direction) {
//		switch(direction) {
//			case A: 
//				return D;
//			case B: 
//				return E;
//			case C:
//				return F;
//			case D:
//				return A;
//			case E:
//				return B;
//			case F:
//				return C;
//		}
//	}
//}
