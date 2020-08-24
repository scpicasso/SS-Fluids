package src;

public class Main{
	private static String input_file = "input.txt"
	private static int size = 200;

	//input formato: cantidad de particulas, particula n + posicion + direccion
	//outputs formato: cantidad de particulas + posicion, nombre del archivo
	//output_t=n.xyz 
	public static void main(String[] args) throws IOException {
		List<Particle> particles = null;
		FileParser fp = new FileParser();
		double start;
		double end;
}