package map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UseanKaannoksenSanakirja {

    /**
     * Tämä tietorakenne sisältää kaikki sanat ja niiden käännökset.
     * 
     * TODO: Alusta uusi HashMap ja aseta se tähän muuttujaan
     */
    private Map<String, List<String>> sanakirja = new HashMap<>();

    /*
     * Lisää annnetulle sanalle uuden käännöksen.
     */
    public void lisaa(String sana, String kaannos) {
        /*
         * Tarkasta ensin, onko käännettävälle sanalle olemassa jo käännöksiä esim.
         * containsKey-metodilla. Jos käännöksiä ei ole, lisää sanalle arvoksi uusi
         * lista, jossa on vain annettu ensimmäinen käännös. Muussa tapauksessa lisää
         * annettu käännös uutena olemassa olevalle listalle.
         */
    	
    	if (sanakirja.containsKey(sana)) {
    		List<String> kaannokset = sanakirja.get(sana);
    		kaannokset.add(kaannos);
    	} else {
    		List<String> lisaaKaannokset = new ArrayList<>();
    		lisaaKaannokset.add(kaannos);
    		
    		sanakirja.put(sana, lisaaKaannokset);
    	}
    	
    }

    /**
     * Palautaa annetun sanan kaikki tunnetut käännökset. Jos sanalle ei ole
     * yhtäkään käännöstä, metodi palauttaa tyhjän listan.
     */
    public List<String> kaanna(String sana) {
        // TODO: käytä containsKey, get ja muita Mapin metodeja hakeaksesi käännökset
    	if (this.sanakirja.containsKey(sana)) return new ArrayList<>(sanakirja.get(sana));
    	
        return new ArrayList<>();
    }

    /**
     * Poista annetun sanan kaikki käännökset sanakirjasta
     */
    public void poista(String sana) {
        // TODO: poista sanat Mapin remove-metodilla
    	if (sanakirja.containsKey(sana)) sanakirja.remove(sana);
    }
}