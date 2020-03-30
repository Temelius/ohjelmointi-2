package Periytyminen;

public class Opiskelija extends Henkilo {
	
	private int opintopisteet;
	
	public static void main(String[] args) {
		Opiskelija olli = new Opiskelija("Olli", "Ida Albergintie 1 00400 Helsinki");
		System.out.println(olli);
		System.out.println("opintopisteitä " + olli.opintopisteita());

		olli.opiskele();

		System.out.println("opintopisteitä " + olli.opintopisteita());
		
	}

	public Opiskelija(String nimi, String osoite) {
		super(nimi, osoite);
	}
	
	public void opiskele() {
		this.opintopisteet += 1;
	}
	
	public int opintopisteita() {
		return opintopisteet;
	}

}
