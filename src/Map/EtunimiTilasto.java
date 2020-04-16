package Map;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class EtunimiTilasto {

	public static void main(String[] args) {
		
		Scanner lukija = new Scanner(System.in);
		
		Map<String, Integer> mappi = luoTilastoTiedostosta();
		
		
		while(true) {
			System.out.print("Anna etunimi: ");
			String nimi = lukija.nextLine();
			if (nimi.equalsIgnoreCase("lopeta")) { lukija.close(); System.exit(0); }
			
			int lkm = mappi.getOrDefault(nimi, 0);
			
			System.out.println(nimi + ": " + lkm + " kappaletta\n");
		}
		
	}
	
	public static Map<String, Integer> luoTilastoTiedostosta() {
		// Luetaan tiedosto TiedostonLukija luokalla ja tehdään siitä lista.
		TiedostonLukija csvlukija = new TiedostonLukija("etunimet.csv");
		List<String> rivit = csvlukija.lueRivit();
		
		Map<String, Integer> mappi = new HashMap<String, Integer>();
		
		for (String rivi : rivit) {
			String[] osat = rivi.split(";");
			String nimi = osat[0];
			Integer maara = Integer.parseInt(osat[1].replaceAll(" ", ""));
			
			if (mappi.containsKey(nimi)) {
				int vanhamaara = mappi.get(nimi);
				mappi.put(nimi, maara + vanhamaara);
			} else {
				mappi.put(nimi, maara);				
			}
			
		}
		return mappi;
	}

}
