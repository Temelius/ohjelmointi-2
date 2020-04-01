package Periytyminen;

public class Henkilo {
	
	private String nimi;
	private String osoite;
	
	public static void main(String[] args) {
		Henkilo ada = new Henkilo("Ada Lovelace", "Korsontie 1 03100 Vantaa");
		Henkilo esko = new Henkilo("Esko Ukkonen", "Mannerheimintie 15 00100 Helsinki");

		System.out.println(ada);
		System.out.println(esko);

	}
	
	public Henkilo(String nimi, String osoite) {
		this.nimi = nimi;
		this.osoite = osoite;
	}
	
	public String getNimi() {
		return this.nimi;
	}
	
	public String getOsoite() {
		return this.osoite;
	}
	
	@Override
	public String toString() {
		return this.nimi + "\n  " + this.osoite;
	}

}
