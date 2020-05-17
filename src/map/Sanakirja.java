package map;

import java.util.HashMap;
import java.util.Map;

public class Sanakirja {
	private Map<String, String> kaannokset = new HashMap<>();
	
	public String kaanna(String sana) {
		return this.kaannokset.get(sana);
	}
	
	public void lisaa(String sana, String kaannos) {
		this.kaannokset.put(sana, kaannos);
	}
	
}
