package Map;

import java.util.TreeMap;

public class Lempinimet {

	public static void main(String[] args) {
		TreeMap<String, String> mappi = new TreeMap<String, String>();
		
		mappi.put("Teemu", "The Finnish Flash");
		mappi.put("Jari", "Litti");
		mappi.put("Kaisa-Leena", "Kappa");
		
		System.out.println(mappi);

	}

}
